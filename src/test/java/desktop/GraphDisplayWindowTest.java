package desktop;

import core.RefactorNode;
import org.junit.Test;
import service.RefactoringPerformService;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphDisplayWindowTest {
    @Test
    public void simpleTest() {
        Logger.getGlobal().setLevel(Level.SEVERE);

        double[][] simpleMatrix = {{3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0},
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
        RefactoringPerformService refactoringPerformService = new RefactoringPerformService();
        RefactorNode refactorNode = refactoringPerformService.performRefactoring(simpleMatrix);
        GraphDisplayWindow graphDisplayWindow = new GraphDisplayWindow(refactorNode);
        graphDisplayWindow.setVisible(true);
        while (true) {
        }
    }

    @Test
    public void pressureTest() {
        Logger.getGlobal().setLevel(Level.SEVERE);
        long start=System.currentTimeMillis();
        double[][] pressureMatrix = new double[100][100];
        for (int i = 0; i < pressureMatrix.length;i++) {
            pressureMatrix[i][i] = Math.random() * 50;
            for (int j = i+1; j < pressureMatrix.length; j++) {
                pressureMatrix[i][j] = Math.random() * 50;
                pressureMatrix[j][i]=pressureMatrix[i][j];
            }
        }

        RefactoringPerformService refactoringPerformService = new RefactoringPerformService();
        RefactorNode refactorNode = refactoringPerformService.performRefactoring(pressureMatrix);
        GraphDisplayWindow graphDisplayWindow = new GraphDisplayWindow(refactorNode);
        graphDisplayWindow.setVisible(true);
        long end=System.currentTimeMillis();
        Logger.getGlobal().severe("Duration: "+(end-start));
        while (true) {
        }
    }

    @Test
    public void pressureSparseMatrixTest() {
        Logger.getGlobal().setLevel(Level.SEVERE);
        long start=System.currentTimeMillis();
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

        RefactoringPerformService refactoringPerformService = new RefactoringPerformService();
        RefactorNode refactorNode = refactoringPerformService.performRefactoring(pressureMatrix);
        GraphDisplayWindow graphDisplayWindow = new GraphDisplayWindow(refactorNode);
        graphDisplayWindow.setVisible(true);
        long end=System.currentTimeMillis();
        Logger.getGlobal().severe("Duration: "+(end-start));
        while (true) {
        }
    }
}