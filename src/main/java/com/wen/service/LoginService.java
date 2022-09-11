package com.wen.service;

import com.wen.bean.TblUserRecord;

public interface LoginService {
    TblUserRecord login(String username, String password);
}
