package com.ecnu.refactoring.desktop;

import com.ecnu.refactoring.desktop.GraphGeneration;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import static org.mockito.Mockito.spy;

public class GraphGenerationTest {
    @Test
    public void urlStringConvertTest(){
        URL url= this.getClass().getResource("/cluster.gv");
        Logger.getGlobal().info("File caught! The URL is "+ url.toString());
        try {
            File file=new File( url.toURI());
            Logger.getGlobal().info("Now the file is: "+ file);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void generateTest() throws Exception{

        double[][] data = new double[][]{{3.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.5D, 0.0D, 0.0D},
                {1.0D, 4.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 1.0D, 4.0D, 0.5D, 0.0D, 0.0D, 0.5D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 0.0D, 0.0D, 0.5D, 2.0D, 0.5D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 0.0D, 0.5D, 0.0D, 0.0D, 0.0D, 2.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 2.0D, 0.5D, 0.0D, 0.0D, 1.0D, 0.5D},
                {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.5D, 4.0D, 0.0D, 0.0D, 0.0D, 0.0D},
                {0.0D, 2.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 3.0D, 0.0D, 0.0D, 0.0D},
                {0.5D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.0D},
                {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.5D, 4.0D, 0.0D},
                {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.5D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D}};
        GraphGeneration graphGeneration=new GraphGeneration();

        String[] name={"a","b","c","d","e","f","g","h","i","j","k","l","m"}; //useless
        graphGeneration.generate(data,name);
    }

    @Test
    public void generateFromFileTest() throws Exception {
        GraphGeneration graphGeneration = new GraphGeneration();
        graphGeneration.generateGraphFromFile(new File(this.getClass().getResource("/cluster.gv").toURI()));
    }
}