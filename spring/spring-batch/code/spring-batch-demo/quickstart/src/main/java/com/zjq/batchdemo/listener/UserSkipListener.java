package com.zjq.batchdemo.listener;

import com.zjq.batchdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserSkipListener implements SkipListener<User,User> {
    @Override
    public void onSkipInRead(Throwable throwable) {

    }

    @Override
    public void onSkipInWrite(User user, Throwable throwable) {
        log.info("skip user : {}",user);
    }

    @Override
    public void onSkipInProcess(User user, Throwable throwable) {

    }
}
