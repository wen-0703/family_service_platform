package com.wen.mapper;

import com.wen.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TblCompanyMapper extends BaseMapper<TblCompany> {
    /**
     * 查询公司信息方法
     * @return 公司信息
     */
    @Select("SELECT id,company_full_name FROM tbl_company")
    List<TblCompany> selectCompany();
}
