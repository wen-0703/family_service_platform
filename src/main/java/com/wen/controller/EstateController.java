package com.wen.controller;

import com.wen.bean.*;
import com.wen.result.R;
import com.wen.service.EstateService;
import com.wen.vo.CellMessage;
import com.wen.vo.UnitMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//房产控制器
@RestController
public class EstateController {
    @Resource
    EstateService service;
    @RequestMapping("/estate/selectCompany")
    public R selectCompany(){
        System.out.println("selectCompany");
        List<TblCompany> companies = service.selectCompany();
        return new R(companies);
    }

    @RequestMapping("/estate/insertEstate")
    public R insertEstate(FcEstate fcEstate){
        System.out.println("insertEstate");
        Integer result = service.insertEstate(fcEstate);
        if (result == 1){
            return new R(200,result.toString(),"插入成功");
        }
        else
            return new R("房产编码已存在");

    }

    @RequestMapping("/estate/selectBuilding")
    public R selectBuilding(Integer buildingNumber,String estateCode){
        System.out.println("selectBuilding");
        List<FcBuilding> fcBuildings = service.selectBuilding(buildingNumber,estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/updateBuilding")
    public R updateBuilding(FcBuilding fcBuilding){
        System.out.println("updateBuilding");
        Integer result = service.updateBuilding(fcBuilding);
        if(result == 1){
            return new R("更新数据成功！");
        }
        return new R("更新数据失败！");
    }

    @RequestMapping("/estate/selectUnit")
    public R selectUnit(@RequestBody UnitMessage[] unitMessages){
        System.out.println("selectUnit");
        List<FcUnit> allUnit = new ArrayList<>();
        for (UnitMessage unitMessage : unitMessages) {
            allUnit.addAll(service.selectUnit(unitMessage));
        }
        return new R(allUnit);
    }

    @RequestMapping("/estate/updateUnit")
    public R updateUnit(FcUnit fcUnit){
        System.out.println("updateUnit");
        Integer result = service.updateUnit(fcUnit);
        if(result == 1){
            return new R("更新数据成功！");
        }
        return new R("更新数据失败！");
    }

    @RequestMapping("/estate/insertCell")
    public R insertCell(@RequestBody CellMessage[] cellMessages){
        System.out.println("insertCell");
        ArrayList<FcCell> fcCells = new ArrayList<>();
        for (CellMessage cellMessage:cellMessages){
            List<FcCell> result = service.insertCell(cellMessage);
            fcCells.addAll(result);
        }

        return new R(fcCells);
    }

    @RequestMapping("/estate/selectBuildingByEstate")
    public R selectBuildingByEstate(String estateCode){
        System.out.println("selectBuildingByEstate");
        List<FcBuilding> fcBuildings = service.selectBuildingByEstate(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectUnitByBuildingCode")
    public R selectUnitByBuildingCode(String buildingCode){
        System.out.println("selectUnitByBuildingCode");
        List<FcUnit> fcUnits = service.selectUnitByBuildingCode(buildingCode);
        return new R(fcUnits);
    }

    @RequestMapping("/estate/selectCell")
    public R selectCell(String unitCode){
        System.out.println("selectCell");
        List<FcCell> fcCells = service.selectCell(unitCode);
        return new R(fcCells);
    }

    @RequestMapping("/estate/selectEstate")
    public R selectEstate(String company){
        System.out.println("selectEstate");
        List<FcEstate> estates = service.selectEstate(company);
        return new R(estates);
    }

    @RequestMapping("/estate/selectAllEstate")
    public R selectAllEstate(){
        System.out.println("selectAllEstate");
        List<FcEstate> fcEstates = service.selectAllEstate();
        return new R(fcEstates);
    }

    @RequestMapping("/estate/selectBuildingByEstateCode")
    public R selectBuildingByEstateCode(String estateCode){
        System.out.println("selectBuildingByEstateCode");
        List<FcBuilding> fcBuildings = service.selectBuildingByEstateCode(estateCode);
        return new R(fcBuildings);
    }

    @RequestMapping("/estate/selectBuildingByEstateCodeAndBuildingCode")
    public R selectBuildingByEstateCodeAndBuildingCode(String buildingCode,String estateCode){
        System.out.println("selectBuildingByEstateCodeAndBuildingCode");
        FcBuilding fcBuilding = service.selectBuildingByEstateCodeAndBuildingCode(buildingCode, estateCode);
        return new R(fcBuilding);
    }
}
