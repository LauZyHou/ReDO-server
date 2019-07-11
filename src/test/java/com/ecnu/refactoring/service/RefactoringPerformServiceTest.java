package com.ecnu.refactoring.service;

import com.ecnu.refactoring.core.RefactorNode;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RefactoringPerformServiceTest {



    @Test
    public void performRefactoring() {
        Logger.getGlobal().setLevel(Level.SEVERE);
        RefactoringPerformService refactoringPerformService=new RefactoringPerformService();
        double simpleMatrix[][] = {{3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0},
                {1, 4, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
                {0, 1, 4, 0.5, 0, 0, 0.5, 1, 0, 0, 0, 0, 0},
                {0, 0, 0.5, 1, 0.5, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0.5, 2, 0.5, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0.5, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0.5, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 2, 0.5, 0, 0, 1, 0.5},
                {0, 0, 0, 0, 0, 0, 0, 0.5, 4, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0.5, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0.5, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0, 0, 0, 1}
        };
        RefactorNode refactorNode= refactoringPerformService.performRefactoring(simpleMatrix);
        double[][] res=refactorNode.getComplexityMatrix();
        for(int i=0;i<res.length;i++){
            for (int j = 0; j < res.length; j++) {
                Logger.getGlobal().severe("res: "+i+j+res[i][j]);
            }
        }
//        System.out.println(.toString());
    }
}