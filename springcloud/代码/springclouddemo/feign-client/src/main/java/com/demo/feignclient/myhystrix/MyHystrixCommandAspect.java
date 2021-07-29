package com.demo.feignclient.myhystrix;

import com.netflix.discovery.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
  * @Description: Hystrx切面（手写Hystrix简易实现）
  * @Author: zhangjunqiang
  * @Date: 2021/7/29 23:08
  * @version v1.0
  */
@Aspect
@Component
public class MyHystrixCommandAspect {

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Pointcut(value = "@annotation(MyHystrixCommand)")
    public void pointCut() {

    }

    @Around(value = "pointCut()&&@annotation(hystrixCommand)")
    public Object doPointCut(ProceedingJoinPoint joinPoint,MyHystrixCommand hystrixCommand) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException, ExecutionException, TimeoutException {
        int timeout = hystrixCommand.timeout();
        Future<Object> future = executorService.submit(() -> {
            try {
                return joinPoint.proceed(); // 执行目标方法
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
        Object result = null;
        try {
            // 通过future的get方法判断是否超时
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
            if (StringUtils.isBlank(hystrixCommand.fallback())) {
                throw e;
            }
            // 调用fallback
            result = invokeFallback(joinPoint, hystrixCommand.fallback());
        }
        return result;
    }

    private Object invokeFallback(ProceedingJoinPoint joinPoint,String fallback) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        // 以上时获取被代理的方法和Method
        // 得到fallback方法
        Method fallbackMethod = null;
        try {
            fallbackMethod = joinPoint.getTarget().getClass().getMethod(fallback, parameterTypes);
            fallbackMethod.setAccessible(true);
            // 完成反射调用
            return fallbackMethod.invoke(joinPoint.getTarget(),joinPoint.getArgs());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
