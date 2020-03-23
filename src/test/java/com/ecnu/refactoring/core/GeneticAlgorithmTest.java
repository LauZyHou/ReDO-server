package com.ecnu.refactoring.core;

//import com.ecnu.refactoring.core.GeneticAlgorithm;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

public class GeneticAlgorithmTest {
//    public com.ecnu.refactoring.core.GeneticAlgorithm geneticAlgorithm;
    @Before
    public void init() {

    }

    @Test
    public void sampleTestToCheckCorrectness() {
        double A[][] = {{3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0},
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
        String res = GeneticAlgorithm.refactor(A);
        assertEquals("((((1,2,9),0,3,7),4,10),5,6,8,11,12)", res);
    }
    @Test
    public void sampleTest2ToCheckCorrectness() {
        double A[][] = {{14.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {0.0, 4.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},{4.0, 4.0, 23.5, 0.0, 0.0, 4.0, 0.0, 0.0, 4.0, 4.0, 0.0, 1.0},
                {0.0, 0.0, 0.0, 4.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0},{0.0, 0.0, 0.0, 0.0, 7.5, 0.0, 0.0, 1.0, 0.0, 4.0, 0.0, 0.0},
                {0.0, 0.0, 4.0, 0.0, 0.0, 18.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3.5, 0.0, 0.0, 0.0, 4.0, 0.0},
                {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.0, 0.0, 4.0, 4.0, 0.0},{0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 6.5, 0.0, 0.0, 0.0},
                {0.0, 0.0, 4.0, 0.0, 4.0, 0.0, 0.0, 4.0, 0.0, 2.0, 0.0, 0.0},{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0, 4.0, 0.0, 0.0, 2.0, 0.0},
                {0.0, 0.0, 1.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0}};
        String res = GeneticAlgorithm.refactor(A);
        assertEquals("((((2,9,11),7),0,1,3,4,5,8,10),6)", res);
    }
    @Test
    public void randomPressureTest() {
        double A[][] = new double[100][100];
        for (int i = 0; i < A.length;i++) {
            for (int j = 0; j < A.length; j++) {
                int rd = 1;
                A[i][j] = rd * Math.random() * 5;
            }
        }
        String res = GeneticAlgorithm.refactor(A);
        //It should be something like this:
        //  ((((((95,0,1,2,7,11,12,13,15,19,20,21,26,29,33,34,36,37,38,40,41,43,44,48,49,51,55,56,58,60,61,63,67,68,70,74,75,78,80,84,85,86,89,93,94,96,99),0,1,2,5,7,8,10,11,12,14,16,19,20,22,26,27,28,29,30,31,32,33,34,35,36,38,39,40,41,42,44,45,46,47,50,51,52),3,6,9,21,23,25,37,43,48,49),4,15,17),18,24),13)
        //For simplicity, just check the parenthesis are matched.
        Stack<Byte> checkStack = new Stack<Byte>();
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == '(') {
                checkStack.push((byte) 1);
            } else if (res.charAt(i) == ')') {
                checkStack.pop();
            }
        }
        assertTrue(checkStack.empty());
    }
@Test
    public void sparseMatrixTest(){
        double[][] pressureMatrix = new double[100][100];

        for (int i = 0; i < pressureMatrix.length;i++) {
            pressureMatrix[i][i] = Math.random()*1.0 * 50;
            for (int j = 0; j < pressureMatrix.length; j++) {
                pressureMatrix[i][j]=0.0D;
            }
        }

        for (int i=0;i<200;i++) {
            Random random = new Random();
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            pressureMatrix[x][y] = Math.random() * 50;
            pressureMatrix[y][x] = pressureMatrix[x][y];

        }
    String res = GeneticAlgorithm.refactor(pressureMatrix);
    //For simplicity, just check the parenthesis are matched.
    Stack<Byte> checkStack = new Stack<Byte>();
    for (int i = 0; i < res.length(); i++) {
        if (res.charAt(i) == '(') {
            checkStack.push((byte) 1);
        } else if (res.charAt(i) == ')') {
            checkStack.pop();
        }
    }
    assertTrue(checkStack.empty());

    }
}