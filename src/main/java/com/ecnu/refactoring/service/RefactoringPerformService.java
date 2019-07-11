package com.ecnu.refactoring.service;

import com.ecnu.refactoring.core.BraceToTree;
import com.ecnu.refactoring.core.GeneticAlgorithm;
import com.ecnu.refactoring.core.RefactorNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Map;

@Service
public class RefactoringPerformService {
    public static Stack<RefactorNode> store=new Stack<>();
    /**
     * Step 1: Convert from matrix to structured components, which is displayed as String.
     * Step 2: Convert from string to Tree structure.
     * Step 3: Generate the complexity matrix in each RefactorNode object.
     *  Label of each column is from 0 to n-1, which is manually-defined in specific algorithm.
     *
     * @param matrix The original matrix of complexity
     * @return The head node of refactored structure, which is filled with complexity matrix
     */
    public RefactorNode performRefactoring(double[][] matrix){
        String res1 = GeneticAlgorithm.refactor(matrix);
        BraceToTree braceToTree=new BraceToTree();
        RefactorNode res = braceToTree.convert(res1);
        List<String> label=new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            label.add(String.valueOf(i));
        }
        res.generateStructuredComplexityMatrix(matrix,label);
        store.push(res);
        return res;
    }
}
