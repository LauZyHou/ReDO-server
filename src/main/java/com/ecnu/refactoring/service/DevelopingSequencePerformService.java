package com.ecnu.refactoring.service;

import com.ecnu.refactoring.core.BraceToTree;
import com.ecnu.refactoring.core.GeneticAlgorithm;
import com.ecnu.refactoring.core.PhaseNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

@Service
public class DevelopingSequencePerformService {
    public static Stack<PhaseNode> store=new Stack<>();
    /**
     * Step 1: Convert from matrix to structured components, which is displayed as String.
     * Step 2: Convert from string to Tree structure.
     * Step 3: Generate the complexity matrix in each RefactorNode object.
     *  Label of each column is from 0 to n-1, which is manually-defined in specific algorithm.
     *
     * @param matrix The original matrix of complexity
     * @return The head node of refactored structure, which is filled with complexity matrix
     */
    public PhaseNode performRefactoring(double[][] matrix){
        String res1;
        System.out.println(Arrays.deepToString(matrix));
        res1= GeneticAlgorithm.refactor(matrix);
        System.out.println(res1);
        BraceToTree braceToTree=new BraceToTree();
        PhaseNode res = braceToTree.convert(res1);
        List<String> label=new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            label.add(String.valueOf(i));
        }
        res.generateStructuredComplexityMatrix(matrix,label);
        store.push(res);
        return res;
    }

    public List<PhaseNode> listAllNodes(PhaseNode rn){
        List<PhaseNode> ret=new ArrayList<>();
        ret.add(rn);
        List<PhaseNode> children = rn.getNodes();
        for(int i=0;i<children.size();i++){
            ret.addAll(listAllNodes(children.get(i)));
        }


        return ret;

    }

    public double calculateComplexity(double[][] matrix) {
        return GeneticAlgorithm.cal_alpha(matrix)+GeneticAlgorithm.cal_data(matrix);
    }
}
