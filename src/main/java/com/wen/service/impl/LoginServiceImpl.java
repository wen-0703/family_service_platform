package com.wen.service.impl;

import com.wen.bean.TblUserRecord;
import com.wen.mapper.TblUserRecordMapper;
import com.wen.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private TblUserRecordMapper tblUserRecordMapper;

    @Override
    public TblUserRecord login(String username, String password) {
        return tblUserRecordMapper.login(username,password);
    }
}
