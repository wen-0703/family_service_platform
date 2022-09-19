package com.wen.service;

import com.wen.bean.ZhCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 业主信息表 服务类
 * </p>
 *
 * @author lian
 * @since 2022-09-03
 */
public interface ZhCustomerService extends IService<ZhCustomer> {
    /**
     * 新增业主信息
     * @param zhCustomer 前端传递的业主信息
     * @return 新增是否成功
     */
    Integer insertCustomer(ZhCustomer zhCustomer);

    /**
     * 查询全部业主信息
     * @return 查询返回全部的业主信息
     */
    List<ZhCustomer> selectCustomer(String column,String value,String customerType);
}
