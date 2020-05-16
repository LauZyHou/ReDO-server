package com.ecnu.refactoring.core;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class BraceToTreeTest {

    BraceToTree braceToTree;


    @BeforeClass
    public static void bb(){
        Logger.getGlobal().setLevel(Level.SEVERE);
    }
    @Before
    public void init() {


        Answer<String> answer = new Answer<String>() {

            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArgument(0).toString() + 'c';
            }
        };
        //when( braceToTree.headNameGeneration(anyString())).thenAnswer(answer);
    }

    public String refactorNodePrint(PhaseNode rn) {
        if (rn.getNodes().size() == 0) {
            return rn.getData();
        }
        StringBuilder ret = new StringBuilder();
        ret.append("[");
        for (int i = 0; i < rn.getNodes().size() - 1; i++) {
            ret.append(refactorNodePrint(rn.getNodes().get(i)) + ", ");
        }

        ret.append(refactorNodePrint(rn.getNodes().get(rn.getNodes().size() - 1)));
        ret.append(']');
        return rn.getData() + ret.toString();
    }

    @Test
    public void convertTest() {
        PhaseNode res = braceToTree.convert("(1,(10,(30,40)),(3,4,5))");
        assertEquals("1c[1, 10c[10, 30c[30, 40]], 3c[3, 4, 5]]", refactorNodePrint(res));
    }

    @Test
    public void convertWithoutOuterBraceTest() {
        PhaseNode res = braceToTree.convert("1,(10,(30,40)),(3,4,5)");
        assertEquals("1c[1, 10c[10, 30c[30, 40]], 3c[3, 4, 5]]", refactorNodePrint(res));
    }

    @Test
    public void convertTest2() {
        PhaseNode res = braceToTree.convert("((((1,2,9),0,3,7),4,10),5,6,8,11,12)");
        assertEquals("1cccc[1ccc[1cc[1c[1, 2, 9], 0, 3, 7], 4, 10], 5, 6, 8, 11, 12]", refactorNodePrint(res));
    }

    @Test
    public void convertWithLotsOfBrace() {
        PhaseNode res = braceToTree.convert("(1),(2),((3),(4,((((5)))))),(13,14,15)");
        assertEquals("1cc[1c[1], 2c[2], 3cc[3c[3], 4c[4, 5cccc[5ccc[5cc[5c[5]]]]]], 13c[13, 14, 15]]", refactorNodePrint(res));
    }

}

// (1),(   2),((   3),(4   ,((((   5))))  )),(13,14,15)");