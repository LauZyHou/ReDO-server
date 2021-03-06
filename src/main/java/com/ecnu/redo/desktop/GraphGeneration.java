package com.ecnu.redo.desktop;
import com.ecnu.redo.core.PhaseNode;
import guru.nidi.graphviz.engine.*;
import org.slf4j.impl.StaticLoggerBinder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;


public class GraphGeneration {

    public BufferedImage generateGraphFromFile(File file) throws Exception {
        StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();
        Graphviz graphviz = Graphviz.fromFile(file);
        Renderer renderer = graphviz.render(Format.SVG);
        BufferedImage image=renderer.toImage();
        return image;
    }


    public BufferedImage generate(double[][] refactorMatrix, String[] columnNames) throws Exception {
        if (refactorMatrix.length != columnNames.length) {
            throw new IllegalArgumentException("Cannot solve the meanings of each column.");
        }
        StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();

        StringBuilder generateCommand = new StringBuilder();
        generateCommand.append("graph{\n");
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i]="\""+columnNames[i]+"\\n"+String.valueOf(refactorMatrix[i][i])+"\"";
        }
        for (int i = 0; i < columnNames.length; i++) {

            for (int j = i+1; j < columnNames.length ; j++) {
                if (refactorMatrix[i][j] != 0.0D&&i!=j)
                    generateCommand.append( columnNames[i] + "--" + columnNames[j] + "[label=\"" + Double.toString(refactorMatrix[i][j]) + "\"]\n");
            }
        }
        generateCommand.append("}");
        Graphviz.releaseEngine();
        Graphviz graphviz = Graphviz.fromString(generateCommand.toString());
        Renderer renderer = graphviz.render(Format.SVG);
        Graphviz.useEngine(Collections.singletonList(new GraphvizJdkEngine()));
        BufferedImage image=renderer.toImage();
        return image;
    }

    public BufferedImage generateByRefactorNode(PhaseNode showNode, String[] columnMeaning) {
        double[][]matrix= showNode.getCostMatrix();
        String[] columnNames=new String[showNode.getNodes().size()];
        for(int i=0;i<showNode.getNodes().size();i++){
            String str=showNode.getNodes().get(i).getData();
            if(!showNode.getNodes().get(i).isCombined()) {
                int index = Integer.parseInt(str);
                columnNames[i]= columnMeaning[index];
            }
            else{
                columnNames[i]=str;
            }
        }
        try {
            return generate(matrix,columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
