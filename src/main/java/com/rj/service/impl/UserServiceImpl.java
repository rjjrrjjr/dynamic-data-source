package com.rj.service.impl;

import com.rj.mapper.UserEntityMapper;
import com.rj.model.UserEntity;
import com.rj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author ruanjin
 * @since 2019/5/22 16:06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public List<UserEntity> list() {
        return userEntityMapper.selectByExample(null);
    }

    @Override
    public void update() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setAge(new Random().nextInt(99));
        userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    @Override
    @Transactional
    public void updateError() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setAge(new Random().nextInt(99));
        userEntityMapper.updateByPrimaryKeySelective(userEntity);
        int a = 1/0;
    }
}
