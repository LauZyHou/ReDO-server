package com.ecnu.redo.controller;

import com.ecnu.redo.core.PhaseNode;
import com.ecnu.redo.entity.TagMatrixDTO;
import com.ecnu.redo.input.FileParser;
import com.ecnu.redo.input.CostMatrix;
import com.ecnu.redo.input.ea.xmi1.EAXMIFileParser;
import com.ecnu.redo.service.DevelopingSequencePerformService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.xml.internal.bind.api.impl.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import java.io.File;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;

@Controller
//@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "",methods = {})
public class DevelopingSequencePerformController {
    @Autowired
    DevelopingSequencePerformService developingSequencePerformService;

    /**
     * Warn: No params check whether is a square number
     *
     * @param src
     * @return
     */
    private double[][] change1DimArrayTo2Dim(double[] src) {
        int retSize = (int) Math.round(Math.sqrt(src.length));
        double[][] ret = new double[retSize][retSize];
        int i = 0, j = 0;
        for (int ii = 0; ii < retSize * retSize; ii++) {
            ret[i][j] = src[ii];
            j++;
            if (j == retSize) {
                j = 0;
                i++;
            }
        }
        return ret;

    }

    @PostMapping("/refactor")
    @ResponseBody
    public List<TagMatrixDTO> uploadMatrix(@RequestParam(value = "matrix") double[] m) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            double[][] matrix = change1DimArrayTo2Dim(m);
            Logger.getGlobal().info(Arrays.deepToString(matrix));

            PhaseNode rootNode = developingSequencePerformService.performRefactoring(matrix);

            List<PhaseNode> nodes = developingSequencePerformService.listAllNodes(rootNode);
            List<TagMatrixDTO> ret = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                PhaseNode rn = nodes.get(i);
                double[][] res = rn.getCostMatrix();
                String[] meaning = rn.getChildData();
                String name = rn.getData();
                TagMatrixDTO tagMatrixDTO = new TagMatrixDTO(res, meaning, name);
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
    public TagMatrixDTO uploadFile(@RequestParam(value = "file") MultipartFile fileList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Logger.getGlobal().info(fileList.getOriginalFilename().toString());
            File f = File.createTempFile(System.currentTimeMillis() + "",
                    fileList.getOriginalFilename().substring(fileList.getOriginalFilename().lastIndexOf(".")));
            fileList.transferTo(f);

            FileParser fp = new EAXMIFileParser();
            CostMatrix rm = fp.parseFile(f);
            TagMatrixDTO tagMatrixDTO = new TagMatrixDTO(rm, fileList.getOriginalFilename());
            Logger.getGlobal().info(rm.toString());
            return tagMatrixDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/calculate-complexity")
    @ResponseBody
    public Map<String, Double> calculateCost(@RequestParam(value = "matrix") double[] m) {
        try {
            double[][] matrix = change1DimArrayTo2Dim(m);
//            RefactorNode rootNode = developingSequencePerformService.performRefactoring(matrix);
            double value = developingSequencePerformService.calculateComplexity(matrix);
            Map<String, Double> res = new HashMap<>();
            res.put("complexity", value);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
