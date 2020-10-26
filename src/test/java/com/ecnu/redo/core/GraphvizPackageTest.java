/*
 * Copyright © 2015 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecnu.redo.core;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.GraphvizJdkEngine;
import guru.nidi.graphviz.model.*;
import org.junit.Test;

import java.io.File;
import java.util.Collections;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.engine.Format.PNG;
import static guru.nidi.graphviz.engine.Format.SVG;
import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Link.to;

public class GraphvizPackageTest {

   @Test

    public void ex2() throws Exception{
        final Node
                init = node("init"),
                execute = node("execute"),
                compare = node("compare").with(Shape.RECTANGLE, Style.FILLED, Color.hsv(.7, .3, 1.0)),
                make_string = node("make_string"),
                printf = node("printf");
        final Graph g = graph("ex2").directed().with(
                node("main").with(Shape.RECTANGLE).link(
                        to(node("parse").link(execute)).with("weight", 8),
                        to(init).with(Style.DOTTED),
                        node("cleanup"),
                        to(printf).with(Style.BOLD, Label.of("100 times"), Color.RED)),
                execute.link(graph().with(make_string, printf), to(compare).with(Color.RED)),
                init.link(make_string.with(Label.of("make a\nstring"))));
        Graphviz.useEngine(Collections.singletonList(new GraphvizJdkEngine()));
        Graphviz.fromGraph(g).render(SVG).toFile(new File(this.getClass().getResource("/").toURI().getPath()+"/ex2generation.svg"));
    }
@Test
    public void ex3()throws Exception {
        final Node
                a = node("a").with(Shape.polygon(5), attr("peripheries", 3), Color.LIGHTBLUE, Style.FILLED),
                c = node("c").with(Shape.polygon(4), Label.of("hello world")),
                d = node("d").with(Shape.INV_TRIANGLE),
                e = node("e").with(Shape.polygon(4));
        final Graph g = graph("ex3").directed().with(
                a.link(node("b").link(c, d)),
                e);
//        Graphviz.fromGraph(g).render(SVG).toString();
    Graphviz.useEngine(Collections.singletonList(new GraphvizJdkEngine()));
    Graphviz.fromGraph(g).render(PNG).toFile(new File(this.getClass().getResource("/").toURI().getPath()+"/ex3generation.png"));
   }


}