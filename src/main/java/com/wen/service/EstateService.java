package com.wen.service;

import com.wen.bean.*;
import com.wen.vo.CellMessage;
import com.wen.vo.UnitMessage;

import java.util.List;

public interface EstateService {
    List<TblCompany> selectCompany();

    Integer insertEstate(FcEstate fcEstate);

    List<FcBuilding> selectBuilding(Integer buildingNumber,String estateCode);

    Integer updateBuilding(FcBuilding fcBuilding);

    List<FcUnit> selectUnit(UnitMessage unitMessage);

    Integer updateUnit(FcUnit fcUnit);

    List<FcCell> insertCell(CellMessage cellMessage);

    List<FcBuilding> selectBuildingByEstate(String estateCode);

    List<FcUnit> selectUnitByBuildingCode(String buildingCode);

    List<FcCell> selectCell(String unitCode);

    List<FcEstate> selectEstate(String company);
}
