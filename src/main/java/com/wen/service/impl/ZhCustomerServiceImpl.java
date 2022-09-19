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
    public List<ZhCustomer> selectCustomer(String column,String value,String customerType) {
        ZhCustomer temp = new ZhCustomer();

        if("customer_code".equals(column)){
            temp.setCustomerCode(value);
        }
        else if ("customer_name".equals(column)){
            temp.setCustomerName(value);
        }
        else if("certificate_number".equals(column)){
            temp.setCertificateNumber(value);
        }
        else if("phone_number".equals(column)){
            temp.setPhoneNumber(value);
        }
        else if("正式业主".equals(customerType)){
            temp.setCustomerType(customerType);
        }
        else if("临时业主".equals(customerType)){
            temp.setCustomerType(customerType);
        }
        List<ZhCustomer> zhCustomers = zhCustomerMapper.selectAll(temp);
        return zhCustomers;
    }
}
