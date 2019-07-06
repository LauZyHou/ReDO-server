package core;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizJdkEngine;
import guru.nidi.graphviz.engine.Renderer;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Node;
import org.slf4j.impl.StaticLoggerBinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;


public class GraphGeneration {

    public Image generateGraphFromFile() throws Exception {
        StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();
        Graphviz graphviz = Graphviz.fromFile(new File(this.getClass().getResource("/cluster.gv").toURI()));
        Renderer renderer = graphviz.render(Format.PNG);
        Image image=renderer.toImage();
        return image;
//        renderer.toFile(new File(this.getClass().getResource("/").toURI().getPath() + "/cluster.png"));
    }


    public Image generate(double[][] refactorMatrix, String[] columnNames) throws Exception {
        if (refactorMatrix.length != columnNames.length) {
            throw new IllegalArgumentException("Cannot solve the meanings of each column.");
        }
        StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();
        Node[] nodes = new Node[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            nodes[i] = Factory.node(columnNames[i]);
        }
        StringBuilder generateCommand = new StringBuilder();
        generateCommand.append("digraph{\n");
        for (int i = 0; i < columnNames.length; i++) {
            //generateCommand.append("nodes["+i+"].link(");
            for (int j = 0; j < columnNames.length - 1; j++) {
                if (refactorMatrix[i][j] != 0.0D)
                    generateCommand.append("node" + i + "->node" + j + "[label=\"" + Double.toString(refactorMatrix[i][j]) + "\"]\n");
            }
        }
        generateCommand.append("}");
        Graphviz graphviz = Graphviz.fromString(generateCommand.toString());
        Renderer renderer = graphviz.render(Format.PNG);
        Graphviz.useEngine(Collections.singletonList(new GraphvizJdkEngine()));
        Image image=renderer.toImage();
        return image;
//        renderer.toFile(new File(this.getClass().getResource("/").toURI().getPath() + "/cluster.png"));
    }

    public Image generateByRefactorNode(RefactorNode showNode) {
        double[][]matrix= showNode.getComplexityMatrix();
        String[] columnNames=new String[showNode.getNodes().size()];
        for(int i=0;i<showNode.getNodes().size();i++){
            columnNames[i]=showNode.getNodes().get(i).getData();
        }
        try {
            return generate(matrix,columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
