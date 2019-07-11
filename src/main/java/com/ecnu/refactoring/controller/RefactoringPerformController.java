package com.ecnu.refactoring.controller;

import com.ecnu.refactoring.core.RefactorNode;
import com.ecnu.refactoring.entity.TagMatrixDTO;
import com.ecnu.refactoring.service.RefactoringPerformService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RefactoringPerformController {
    @Autowired
    RefactoringPerformService refactoringPerformService;
    @PostMapping("/uploadMatrix")
    @ResponseBody
    public TagMatrixDTO uploadMatrix(@RequestParam(value="matrix[]") String m) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            double[][] matrix = objectMapper.readValue(m, double[][].class);
            RefactorNode rootNode = refactoringPerformService.performRefactoring(matrix);
            double[][] res = rootNode.getComplexityMatrix();
            String[] meaning = rootNode.getChildData();
            return new TagMatrixDTO(res, meaning, rootNode.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TagMatrixDTO seeDetail(@RequestParam(value="detailNode") String m) {
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            String nodeName=objectMapper.readValue(m,String.class);
            RefactorNode r= RefactoringPerformService.store.pop();
            RefactoringPerformService.store.push(r);
            RefactorNode targetNode;
            for(int i=0;i<r.getNodes().size();i++){
                if(r.getNodes().get(i).getData()==nodeName) targetNode=r.getNodes().get(i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }





}
