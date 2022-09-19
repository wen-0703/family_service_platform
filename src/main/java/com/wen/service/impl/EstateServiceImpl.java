package com.wen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.bean.*;
import com.wen.mapper.*;
import com.wen.service.EstateService;
import com.wen.vo.CellMessage;
import com.wen.vo.UnitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstateServiceImpl implements EstateService {
    @Resource
    private FcEstateMapper fcEstateMapper;

    /**
     * 在做真正的数据插入之前应该判断楼宇的id不能重复
     * 如果重复，则不能插入
     * @param fcEstate
     * @return
     */
    @Override
    public Integer insertEstate(FcEstate fcEstate) {
        Integer result = 0;
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",fcEstate.getEstateCode());
        FcEstate fe = fcEstateMapper.selectOne(queryWrapper);
        // 如果数据不存在则正常添加
        if (fe == null) {
            result = fcEstateMapper.insert(fcEstate);
        }
        // 否则返回0
        return result;
    }

    @Autowired
    TblCompanyMapper companyMapper;
    @Override
    public List<TblCompany> selectCompany() {
        return companyMapper.selectCompany();
    }

    @Resource
    FcBuildingMapper fcBuildingMapper;

    @Override
    public List<FcBuilding> selectBuilding(Integer buildingNumber, String estateCode) {
        List<FcBuilding> fcBuildings = new ArrayList<>();
        for (int i = 0;i<buildingNumber;i++){
            FcBuilding fcBuilding = new FcBuilding();
            fcBuilding.setBuildingCode(estateCode+"B"+(i+1));
            fcBuilding.setBuildingName("第"+(i+1)+"号楼");
            fcBuilding.setEstateCode(estateCode);
            fcBuildingMapper.insert(fcBuilding);
            fcBuildings.add(fcBuilding);
        }
        return fcBuildings;
    }

    /**
     * 完成楼宇数据更新
     * @param fcBuilding
     * @return
     */
    @Override
    public Integer updateBuilding(FcBuilding fcBuilding) {
        Integer result = fcBuildingMapper.updateById(fcBuilding);
        return result;
    }

    @Resource
    private FcUnitMapper fcUnitMapper;

    @Override
    public List<FcUnit> selectUnit(UnitMessage unitMessage) {
        //定义返回的集合
        List<FcUnit> fcUnits = new ArrayList<>();
        //插入数据操作
        for (int i = 0;i<unitMessage.getUnitCount();i++){
            FcUnit fcUnit = new FcUnit();
            fcUnit.setBuildingCode(unitMessage.getBuildingCode());
            fcUnit.setUnitCode(unitMessage.getBuildingCode()+"U"+(i+1));
            fcUnit.setUnitName("第"+(i+1)+"单元");
            fcUnitMapper.insert(fcUnit);
            fcUnits.add(fcUnit);
        }
        return fcUnits;
    }

    @Override
    public Integer updateUnit(FcUnit fcUnit) {
        Integer result = fcUnitMapper.updateById(fcUnit);
        return result;
    }

    @Resource
    FcCellMapper fcCellMapper;

    @Override
    public List<FcCell> insertCell(CellMessage cellMessage) {
        List<FcCell> fcCells = new ArrayList<>();
        //楼层
        for(int i = 0;i<cellMessage.getStopFloor();i++){
            //房间
            for(int j = 0;j<cellMessage.getStopCellId();j++){
                FcCell fcCell = new FcCell();
                fcCell.setUnitCode(cellMessage.getUnitCode());//单元编码
                fcCell.setCellCode(cellMessage.getUnitCode()+"C"+(i+1)+"0"+j);// 房间编码C203
                fcCell.setCellName((i+1)+"0"+j);// 房间名称203
                fcCell.setFloorNumber(cellMessage.getStopFloor());//楼层数 获取结束楼层
                fcCellMapper.insert(fcCell);//插入数据
                fcCells.add(fcCell);
            }
        }
        return fcCells;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstate(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        //具体查询那几个列
        queryWrapper.select("building_code","building_name");
        List<FcBuilding> fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        return fcBuildings;
    }

    @Override
    public List<FcUnit> selectUnitByBuildingCode(String buildingCode) {
        List<FcUnit> fcUnits;
        QueryWrapper<FcUnit> queryWrapper;
        if(buildingCode.equals("")){
            queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*");
            fcUnits = fcUnitMapper.selectList(queryWrapper);
        }
        else {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("building_code", buildingCode);
            fcUnits = fcUnitMapper.selectList(queryWrapper);
        }
        return fcUnits;
    }

    @Override
    public List<FcCell> selectCell(String unitCode) {
        QueryWrapper<FcCell> queryWrapper;
        List<FcCell> fcCells;
        if (unitCode.equals("")){
            queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*");
            fcCells = fcCellMapper.selectList(queryWrapper);
        }
        else {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("unit_code", unitCode);
            fcCells = fcCellMapper.selectList(queryWrapper);
        }
        return fcCells;
    }

    @Override
    public List<FcEstate> selectEstate(String company) {
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company",company);
        List<FcEstate> estates = fcEstateMapper.selectList(queryWrapper);
        return estates;
    }

    @Override
    public List<FcEstate> selectAllEstate() {
        List<FcEstate> fcEstates = fcEstateMapper.selectAllEstate();
        return fcEstates;
    }

    @Override
    public List<FcBuilding> selectBuildingByEstateCode(String estateCode) {
        /*
        * 思路：当前端发送请求携带参数时，作条件查询，如果不携带参数则进行全量查询
        * */
        List<FcBuilding> fcBuildings;
        QueryWrapper<FcBuilding> queryWrapper;
        if (estateCode.equals("")){
            queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*");
            fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        }
        else {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("estate_code", estateCode);
            fcBuildings = fcBuildingMapper.selectList(queryWrapper);
        }
        return fcBuildings;
    }

    @Override
    public FcBuilding selectBuildingByEstateCodeAndBuildingCode(String buildingCode, String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        queryWrapper.eq("building_code",buildingCode);
        FcBuilding fcBuilding = fcBuildingMapper.selectOne(queryWrapper);
        return fcBuilding;
    }
}
