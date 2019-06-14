package com.rj.mapper;

import com.rj.model.DeptEntity;
import com.rj.model.DeptEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeptEntityMapper {
    int countByExample(DeptEntityExample example);

    int deleteByExample(DeptEntityExample example);

    int deleteByPrimaryKey(Long deptNo);

    int insert(DeptEntity record);

    int insertSelective(DeptEntity record);

    List<DeptEntity> selectByExampleWithRowbounds(DeptEntityExample example, RowBounds rowBounds);

    List<DeptEntity> selectByExample(DeptEntityExample example);

    DeptEntity selectByPrimaryKey(Long deptNo);

    int updateByExampleSelective(@Param("record") DeptEntity record, @Param("example") DeptEntityExample example);

    int updateByExample(@Param("record") DeptEntity record, @Param("example") DeptEntityExample example);

    int updateByPrimaryKeySelective(DeptEntity record);

    int updateByPrimaryKey(DeptEntity record);
}