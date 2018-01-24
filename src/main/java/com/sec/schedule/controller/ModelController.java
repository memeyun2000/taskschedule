package com.sec.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sec.schedule.dao.TableModelDao;
import com.sec.schedule.entity.TableModel;
import com.sec.schedule.model.PageInfo;

@Controller
public class ModelController {

    @Autowired
    TableModelDao tableModelDao;

    @RequestMapping("modelList")
    public String modelList(@RequestParam(required = false, defaultValue = "0") String newSearchFlag,
            @ModelAttribute(value = "tableModel") TableModel tableModel,
            @ModelAttribute(value = "pageInfo") PageInfo pageInfo, 
            Model model) {
        List<TableModel> modelList = tableModelDao.findAll();
        

        model.addAttribute("modelList", modelList);
        return "modelList";
    }
}