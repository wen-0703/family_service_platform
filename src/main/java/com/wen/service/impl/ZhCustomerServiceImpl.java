package com.wen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.bean.ZhCustomer;
import com.wen.mapper.ZhCustomerMapper;
import com.wen.service.ZhCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 业主信息表 服务实现类
 * </p>
 *
 * @author lian
 * @since 2022-09-03
 */
@Service
public class ZhCustomerServiceImpl extends ServiceImpl<ZhCustomerMapper, ZhCustomer> implements ZhCustomerService {

    @Resource
    private ZhCustomerMapper zhCustomerMapper;
    @Override
    public Integer insertCustomer(ZhCustomer zhCustomer) {
        Integer result = 0;
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_code",zhCustomer.getCustomerCode());
        ZhCustomer customer = zhCustomerMapper.selectOne(queryWrapper);
        if(customer == null){
            result = zhCustomerMapper.insert(zhCustomer);
        }
        return result;
    }

    @Override
    public List<ZhCustomer> selectCustomer(String column,String value) {
        List<ZhCustomer> zhCustomers;
        QueryWrapper<ZhCustomer> queryWrapper = new QueryWrapper<>();
        if ("".equals(column)){
            zhCustomers = zhCustomerMapper.selectAll();
        }
        else {
            queryWrapper.eq(column,value);
            zhCustomers = zhCustomerMapper.selectList(queryWrapper);
        }
        return zhCustomers;
    }
}
