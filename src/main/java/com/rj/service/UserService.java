package com.rj.service;

import com.rj.model.UserEntity;

import java.util.List;

/**
 * @author ruanjin
 * @since 2019/5/22 16:03
 */
public interface UserService {

    List<UserEntity> list();

    void update();

    void updateError();
}
