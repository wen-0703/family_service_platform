package com.wen.mapper;

import com.wen.bean.ZhCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 业主信息表 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-09-03
 */
public interface ZhCustomerMapper extends BaseMapper<ZhCustomer> {
    /**
     * 当没有参数时查询全部业主信息
     * 当有参数时，根据条件查询业主信息
     * @return 查询的一条或多条业主信息
     */
    List<ZhCustomer> selectAll();

}
