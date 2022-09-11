package com.wen.family_service_platform;

import com.wen.bean.TblUserRecord;
import com.wen.mapper.TblUserRecordMapper;
import com.wen.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FamilyServicePlatformApplicationTests {

    @Autowired
    LoginService loginService;

    @Resource
    TblUserRecordMapper mapper;

    @Test
    void contextLoads() {
        TblUserRecord  tblUserRecord = loginService.login("admin","c4ca4238a0b923820dcc509a6f75849b");
        // TblUserRecord tblUserRecord = mapper.login("admin","c4ca4238a0b923820dcc509a6f75849b");
        System.out.println("输出数据："+tblUserRecord);
    }

}
