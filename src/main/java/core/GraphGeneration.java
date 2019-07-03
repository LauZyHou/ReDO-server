package core;
import guru.nidi.graphviz.*;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.service.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.slf4j.impl.*;
import sun.net.www.protocol.mailto.MailToURLConnection;

import java.io.File;
import java.net.URL;
import java.util.Collections;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class GraphGeneration {

    public void generateGraphFromFile() throws Exception {
        StaticLoggerBinder staticLoggerBinder=StaticLoggerBinder.getSingleton();

        Graphviz graphviz= Graphviz.fromFile(new File(this.getClass().getResource("/cluster.gv").toURI()));


        Renderer renderer= graphviz.render(Format.PNG);

        renderer.toFile(new File(this.getClass().getResource("/").toURI().getPath()+"/cluster.png"));

    }

    public void generate(RefactorMatrix refactorMatrix, String[] columnNames) throws Exception {
        if(refactorMatrix.data.length!=columnNames.length){
            throw new IllegalArgumentException("Cannot solve the meanings of each column.");
        }
        StaticLoggerBinder staticLoggerBinder=StaticLoggerBinder.getSingleton();

        Node[] nodes=new Node[columnNames.length];
        for(int i=0;i<columnNames.length;i++){
            nodes[i]=Factory.node(columnNames[i]);
        }
        Graph g=graph("res").directed();
        for(int i=0;i<columnNames.length;i++){
            for(int j=0;j<columnNames.length;j++){
                g.with(
                nodes[i].link(
                        nodes[j]
//                ).with( Label.of(Double.toString( refactorMatrix.data[i][j])))
                )
                );
            }
        }


//         final Graph g = graph("ex2").directed().with(
//                nodes[i].link(
//                        to(printf).with(Style.BOLD, Label.of("100 times"), Color.RED)
//                 ),
//
//           );

//        Node aa=Factory.node("c");
//        Node bb=Factory.node("b");
//        bb.linkTo(aa);



        Graphviz graphviz;
        graphviz=Graphviz.fromGraph(g);


        Renderer renderer= graphviz.render(Format.PNG);
        Graphviz.useEngine(Collections.singletonList(new GraphvizJdkEngine()));
        renderer.toFile(new File(this.getClass().getResource("/").toURI().getPath()+"/cluster.png"));

    }





}
