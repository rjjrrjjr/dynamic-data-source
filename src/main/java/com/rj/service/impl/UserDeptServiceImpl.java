package com.rj.service.impl;

import com.rj.service.DeptService;
import com.rj.service.UserDeptService;
import com.rj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ruanjin
 * @since 2019/5/24 15:30
 */
@Service
public class UserDeptServiceImpl implements UserDeptService {

    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;

    @Override
    public void update() {
        deptService.update();
        userService.update();
    }

    @Override
    public void updateErrorHalf() {
        userService.update();
        int a = 1/0;
        deptService.update();
    }

    @Override
    @Transactional
    public void updateErrorWhole() {
        userService.update();
        deptService.update();
        int a = 1/0;
    }
}
