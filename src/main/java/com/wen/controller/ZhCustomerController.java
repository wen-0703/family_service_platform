package com.wen.controller;


import com.wen.bean.ZhCustomer;
import com.wen.service.ZhCustomerService;
import com.wen.result.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业主信息表 前端控制器
 * </p>
 *
 * @author lian
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/zhCustomer")
public class ZhCustomerController {
    @Resource
    private ZhCustomerService zhCustomerService;
    /**
     * 新增业主信息
     * @param zhCustomer 前端传递的业主信息
     * @return 新增是否成功
     */
    @PostMapping("/insertCustomer")
    public R selectCustomer(ZhCustomer zhCustomer){
        System.out.println("insertCustomer");
        Integer i = zhCustomerService.insertCustomer(zhCustomer);
        if(i == 1){
            return new R("插入成功");
        }
        return new R("业主编码已存在");
    }

    /**
     * 查询全部业主信息
     * @return 查询返回全部的业主信息
     */
    @RequestMapping("/selectCustomer")
    public R selectCustomer(@RequestBody Map<String,Object> params){
        System.out.println("selectCustomer");
        String column = params.get("column").toString();
        String value = params.get("value").toString();
        System.out.println(column + ":" + value);
        List<ZhCustomer> zhCustomers = zhCustomerService.selectCustomer(column,value);
        return new R(zhCustomers);
    }
}

