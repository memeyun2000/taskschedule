package com.sec.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sec.schedule.dao.TableModelDao;
import com.sec.schedule.entity.TableModel;
import com.sec.schedule.model.CompositeIdTableModel;
import com.sec.schedule.model.PageInfo;

@Controller
public class ModelController {

    @Autowired
    TableModelDao tableModelDao;

    @RequestMapping("/model/modelList")
    public String modelList(@RequestParam(required = false, defaultValue = "0") String newSearchFlag,
            @ModelAttribute(value = "tableModel") TableModel tableModel,
            @ModelAttribute(value = "pageInfo") PageInfo pageInfo, 
            Model model) {
        List<TableModel> modelList = tableModelDao.findAll();
        

        model.addAttribute("modelList", modelList);
        return "/modelList";
    }


    @RequestMapping(value = "/model/saveModel",method = RequestMethod.POST)
    public String saveModel(
            @RequestParam String databaseName,
            @RequestParam String tableSchema,
            @RequestParam String tableName,
            @RequestParam String descr,
            @RequestParam String tableType,
            @RequestParam String memo
            ){
        System.out.println("save model ++++++++++++++++++++++++");
        CompositeIdTableModel id = new CompositeIdTableModel();
        id.setDatabaseName(databaseName);
        id.setTableSchema(tableSchema);
        id.setTableName(tableName);
        
        TableModel tableModel = new TableModel();
        tableModel.setId(id);
        tableModel.setTableType(tableType);
        tableModel.setDescr(descr);
        tableModel.setMemo(memo);

        tableModelDao.save(tableModel);


        return "forward:/model/modelList";
    }
}