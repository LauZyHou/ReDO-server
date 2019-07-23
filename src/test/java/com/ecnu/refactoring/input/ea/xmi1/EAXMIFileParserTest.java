package com.ecnu.refactoring.input.ea.xmi1;

import com.ecnu.refactoring.input.RefactorMatrix;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class EAXMIFileParserTest {

    @Test
    public void parseFile() {
        EAXMIFileParser parser=new EAXMIFileParser();
        try{
//            System.out.println( this.getClass().getResource("/testforuml11.xml").toString());
            parser.parseFile(new File( this.getClass().getResource("/testforuml11.xml").toURI()));
            assertEquals(6,parser.getClasses().size());
            assertEquals(6,parser.getConnectors().size());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void matrixTest() {
        EAXMIFileParser parser=new EAXMIFileParser();
        try{
            RefactorMatrix res= parser.parseFile(new File( this.getClass().getResource("/bb11.xml").toURI()));
            for(int i=0;i<res.getRefactorMatrix().length;i++){
                for (int j = 0; j < res.getRefactorMatrix().length; j++) {
                    System.out.print(res.getRefactorMatrix()[i][j]+" ");
                }
                System.out.println();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}