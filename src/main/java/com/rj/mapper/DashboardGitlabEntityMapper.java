package com.rj.mapper;

import com.rj.model.DashboardGitlabEntity;
import com.rj.model.DashboardGitlabEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DashboardGitlabEntityMapper {
    int countByExample(DashboardGitlabEntityExample example);

    int deleteByExample(DashboardGitlabEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DashboardGitlabEntity record);

    int insertSelective(DashboardGitlabEntity record);

    List<DashboardGitlabEntity> selectByExampleWithRowbounds(DashboardGitlabEntityExample example, RowBounds rowBounds);

    List<DashboardGitlabEntity> selectByExample(DashboardGitlabEntityExample example);

    DashboardGitlabEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DashboardGitlabEntity record, @Param("example") DashboardGitlabEntityExample example);

    int updateByExample(@Param("record") DashboardGitlabEntity record, @Param("example") DashboardGitlabEntityExample example);

    int updateByPrimaryKeySelective(DashboardGitlabEntity record);

    int updateByPrimaryKey(DashboardGitlabEntity record);
}