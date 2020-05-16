package com.ecnu.refactoring.service;

import com.ecnu.refactoring.core.PhaseNode;
import com.ecnu.refactoring.desktop.GraphDisplayWindow;
import com.ecnu.refactoring.input.CostMatrix;
import com.ecnu.refactoring.input.ea.xmi1.EAXMIFileParser;

import javax.swing.*;
import java.io.File;

public class DesktopDemoShowService {
    static DevelopingSequencePerformService developingSequencePerformService = new DevelopingSequencePerformService();

    public static void initialShow(){


            String[] m=new String[1];
            m[0]="Example";
            double[][] com=new double[1][1];
            com[0][0]=1;

            PhaseNode r = developingSequencePerformService.performRefactoring(com);
            GraphDisplayWindow graphDisplayWindow = new GraphDisplayWindow(r,m);
            graphDisplayWindow.setVisible(true);
            return;


    }
    public static void show(File f, JFrame frame){
        EAXMIFileParser parser=new EAXMIFileParser();
        CostMatrix refactorMatrix;
        try {
            refactorMatrix= parser.parseFile(f);
            PhaseNode refactorNode = developingSequencePerformService.performRefactoring(refactorMatrix.getCostMatrix());
            frame.setEnabled(false);
            frame.setVisible(false);
            frame = new GraphDisplayWindow(refactorNode,refactorMatrix.getColumnMeaning());
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
