package com.rj.service;

import com.rj.model.DeptEntity;

import java.util.List;

/**
 * @author ruanjin
 * @since 2019/5/22 16:05
 */
public interface DeptService {

    List<DeptEntity> list();

    void update();

    void updateError();
}
