package com.rj.controller;

import com.rj.model.DashboardGitlabEntity;
import com.rj.model.DeptEntity;
import com.rj.model.UserEntity;
import com.rj.service.DashboardGitlabService;
import com.rj.service.DeptService;
import com.rj.service.UserDeptService;
import com.rj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruanjin
 * @since 2019/5/22 16:08
 */
@RestController
@RequestMapping("/dy")
@Slf4j
public class UDController {

    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;

    @Autowired
    DashboardGitlabService dashboardGitlabService;

    @Autowired
    UserDeptService userDeptService;

    @GetMapping("/list/user")
    List<UserEntity> listUser(){
        return userService.list();
    }

    @GetMapping("/list/dept")
    List<DeptEntity> listDept(){
        return deptService.list();
    }

    @GetMapping("/list/all")
    List<Object> listAll(){
        List result = new ArrayList<>();
        result.addAll(userService.list());
        result.addAll(deptService.list());
        return result;
    }

    @PostMapping("/update/user")
    Boolean updateUser(){
        try {
            userService.update();
        } catch (Exception e) {
            log.info("ERROR: update user appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/error/user")
    Boolean updateUserError(){
        try {
            userService.updateError();
        } catch (Exception e) {
            log.info("ERROR: update user appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/dept")
    Boolean updateDept(){
        try {
            deptService.update();
        } catch (Exception e) {
            log.info("ERROR: update dept appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/error/dept")
    Boolean updateDeptError(){
        try {
            deptService.updateError();
        } catch (Exception e) {
            log.info("ERROR: update dept appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/all")
    Boolean updateAll(){
        try {
            userDeptService.update();
        } catch (Exception e) {
            log.info("ERROR: update dept & user appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/error/half")
    Boolean updateErrorHalf(){
        try {
            userDeptService.updateErrorHalf();
        } catch (Exception e) {
            log.info("ERROR: update dept & user appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @PostMapping("/update/error/whole")
    Boolean updateErrorWhole(){
        try {
            userDeptService.updateErrorWhole();
        } catch (Exception e) {
            log.info("ERROR: update dept & user appear error [{}]", e.getMessage());
            return false;
        }
        return true;
    }

    @GetMapping("/dashboard/list")
    List<DashboardGitlabEntity> dashboardList(){
        return dashboardGitlabService.list();
    }

    @GetMapping("/dashboard/save")
    Boolean dashboardSave(){
        try {
            dashboardGitlabService.insert();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
