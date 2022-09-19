package com.wen.mapper;

import com.wen.bean.FcEstate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 楼盘信息 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2022-09-03
 */
public interface FcEstateMapper extends BaseMapper<FcEstate> {
    List<FcEstate> selectAllEstate();
}
