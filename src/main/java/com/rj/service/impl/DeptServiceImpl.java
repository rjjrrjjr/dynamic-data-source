package com.rj.service.impl;

import com.rj.mapper.DeptEntityMapper;
import com.rj.model.DeptEntity;
import com.rj.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author ruanjin
 * @since 2019/5/22 16:07
 */
@Service public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptEntityMapper deptEntityMapper;

    @Override
    public List<DeptEntity> list() {
        return deptEntityMapper.selectByExample(null);
    }

    @Override
    public void update() {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptNo(6L);
        deptEntity.setDeptName("ruanlian" + new Random().nextInt(99));
        deptEntityMapper.updateByPrimaryKeySelective(deptEntity);
    }

    @Override
    @Transactional
    public void updateError() {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDeptNo(6L);
        deptEntity.setDeptName("ruanlian" + new Random().nextInt(99));
        deptEntityMapper.updateByPrimaryKeySelective(deptEntity);
        int a = 1/0;
    }
}
