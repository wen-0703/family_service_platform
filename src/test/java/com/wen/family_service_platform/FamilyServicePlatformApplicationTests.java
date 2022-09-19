package com.wen.family_service_platform;

import com.wen.bean.FcBuilding;
import com.wen.bean.TblCompany;
import com.wen.mapper.TblCompanyMapper;
import com.wen.service.EstateService;
import com.wen.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class FamilyServicePlatformApplicationTests {

    @Autowired
    LoginService loginService;

    @Resource
    EstateService estateService;

    @Test
    void contextLoads() {
        List<FcBuilding> fcBuildings = estateService.selectBuildingByEstateCode("1");
        System.out.println(fcBuildings);
    }

}
