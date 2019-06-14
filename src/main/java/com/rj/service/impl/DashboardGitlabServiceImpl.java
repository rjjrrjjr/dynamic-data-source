package com.rj.service.impl;

import com.rj.mapper.DashboardGitlabEntityMapper;
import com.rj.model.DashboardGitlabEntity;
import com.rj.service.DashboardGitlabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ruanjin
 * @since 2019/6/3 15:31
 */
@Service
public class DashboardGitlabServiceImpl implements DashboardGitlabService {


    @Autowired
    DashboardGitlabEntityMapper dashboardGitlabEntityMapper;

    @Override
    public List<DashboardGitlabEntity> list() {
        return dashboardGitlabEntityMapper.selectByExample(null);
    }

    @Override
    public void insert() {
        DashboardGitlabEntity dashboardGitlabPo = new DashboardGitlabEntity();
        dashboardGitlabPo.setGroup("devops");
        dashboardGitlabPo.setDate(new Date());
        dashboardGitlabPo.setCommit(new Random().nextInt(100));
        dashboardGitlabPo.setUserCount(new Random().nextInt(100));
        dashboardGitlabEntityMapper.insert(dashboardGitlabPo);
    }
}
