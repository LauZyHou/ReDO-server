package com.ecnu.refactoring.service;

import com.ecnu.refactoring.core.RefactorNode;
import com.ecnu.refactoring.desktop.GraphDisplayWindow;
import com.ecnu.refactoring.input.RefactorMatrix;
import com.ecnu.refactoring.input.ea.xmi1.EAXMIFileParser;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DesktopDemoShowService {
    static RefactoringPerformService refactoringPerformService = new RefactoringPerformService();

    public static void initialShow(){


            String[] m=new String[1];
            m[0]="Example";
            double[][] com=new double[1][1];
            com[0][0]=1;

            RefactorNode r = refactoringPerformService.performRefactoring(com);
            GraphDisplayWindow graphDisplayWindow = new GraphDisplayWindow(r,m);
            graphDisplayWindow.setVisible(true);
            return;


    }
    public static void show(File f, JFrame frame){
        EAXMIFileParser parser=new EAXMIFileParser();
        RefactorMatrix refactorMatrix;
        try {
            refactorMatrix= parser.parseFile(f);
            RefactorNode refactorNode = refactoringPerformService.performRefactoring(refactorMatrix.getRefactorMatrix());
            frame.setEnabled(false);
            frame.setVisible(false);
            frame = new GraphDisplayWindow(refactorNode,refactorMatrix.getColumnMeaning());
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
