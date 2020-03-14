package com.ecnu.refactoring.controller;

import com.ecnu.refactoring.core.RefactorNode;
import com.ecnu.refactoring.entity.TagMatrixDTO;
import com.ecnu.refactoring.input.FileParser;
import com.ecnu.refactoring.input.RefactorMatrix;
import com.ecnu.refactoring.input.ea.xmi1.EAXMIFileParser;
import com.ecnu.refactoring.service.RefactoringPerformService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.*;

//import java.io.File;
import java.io.File;
import java.net.URI;
import java.util.*;
import java.util.logging.Logger;

@Controller
//@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class RefactoringPerformController {
    @Autowired
    RefactoringPerformService refactoringPerformService;

    /**
     * Warn: No params check whether is a square number
     * @param src
     * @return
     */
    private double[][] change1DimArrayTo2Dim(double[] src){
        int retSize=(int)Math.round(Math.sqrt(src.length));
        double[][] ret = new double[retSize][retSize];
        int i=0, j=0;
        for(int ii=0;ii<retSize*retSize;ii++){
            ret[i][j]=src[ii];j++;
            if(j==retSize){j=0;i++;}
        }
        return ret;

    }

    @PostMapping("/refactor")
    @ResponseBody
    public List<TagMatrixDTO> uploadMatrix(@RequestParam(value="matrix") double[] m) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            double[][] matrix=change1DimArrayTo2Dim(m);
            Logger.getGlobal().info(Arrays.deepToString(matrix));

            RefactorNode rootNode = refactoringPerformService.performRefactoring(matrix);

            List<RefactorNode> nodes= refactoringPerformService.listAllNodes(rootNode);
            List<TagMatrixDTO> ret=new ArrayList<>();
            for(int i=0;i<nodes.size();i++){
                RefactorNode rn=nodes.get(i);
                double[][] res = rn.getComplexityMatrix();
                String[] meaning = rn.getChildData();
                String name=rn.getData();
                TagMatrixDTO tagMatrixDTO=new TagMatrixDTO(res, meaning, name);
                ret.add(tagMatrixDTO);
                Logger.getGlobal().info(tagMatrixDTO.toString());
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/fileUpload")
    @ResponseBody
    public TagMatrixDTO uploadFile(@RequestParam(value="file") MultipartFile fileList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Logger.getGlobal().info(fileList.getOriginalFilename().toString());
            File f=File.createTempFile(System.currentTimeMillis()+"",
                    fileList.getOriginalFilename().substring(fileList.getOriginalFilename().lastIndexOf(".")));
            fileList.transferTo(f);

            FileParser fp=new EAXMIFileParser();
            RefactorMatrix rm= fp.parseFile(f);
            TagMatrixDTO tagMatrixDTO=new TagMatrixDTO(rm,fileList.getOriginalFilename());
            Logger.getGlobal().info(rm.toString());
            return tagMatrixDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/calculate-complexity")
    @ResponseBody
    public Map<String,Double> calculateComplexity(@RequestParam(value="matrix") double[] m) {
        try {
            double[][] matrix=change1DimArrayTo2Dim(m);
            RefactorNode rootNode = refactoringPerformService.performRefactoring(matrix);
            double value=refactoringPerformService.calculateComplexity(matrix);
            Map<String,Double> res=new HashMap<>();
            res.put("complexity",value);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
