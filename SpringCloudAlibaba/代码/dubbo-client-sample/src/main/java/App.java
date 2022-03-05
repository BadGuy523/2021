import com.zjq.service.ILoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 主类
 * @author: BadGuy
 * @date: 2022-02-19 12:11
 **/
public class App {
    public static void main(String[] args) throws InterruptedException {
        // 只引入了接口包，跨进程调用，需要远程调用，可使用RPC通信方式
        ILoginService iLoginService = null;
        // 获取xml解析上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/application.xml");
        iLoginService = context.getBean(ILoginService.class);
        String str = iLoginService.login("ad", "");
        System.out.println(str);
    }
}
