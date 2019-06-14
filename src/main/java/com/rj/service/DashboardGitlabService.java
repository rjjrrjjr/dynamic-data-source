package com.rj.service;


import com.rj.model.DashboardGitlabEntity;

import java.util.List;

/**
 * @author ruanjin
 * @since 2019/6/3 15:26
 */
public interface DashboardGitlabService {

    List<DashboardGitlabEntity> list();

    void insert();

}
