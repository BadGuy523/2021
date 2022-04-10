package com.zjq.batchdemo.dao.origin;

import com.zjq.batchdemo.model.User;

import java.util.List;

public interface OriginDao {
    List<User> queryUserList();
}
