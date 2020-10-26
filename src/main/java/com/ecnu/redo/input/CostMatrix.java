package com.ecnu.redo.input;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class CostMatrix {
    double[][] costMatrix;
    String[] columnMeaning;

    public CostMatrix(double[][] costMatrix, String[] columnMeaning) {
        this.costMatrix = costMatrix;
        this.columnMeaning = columnMeaning;
    }

    @Override
    public String toString() {
        return "RefactorMatrix{" +
                "costMatrix=" + Arrays.deepToString(costMatrix) +
                ", columnMeaning=" + Arrays.toString(columnMeaning) +
                '}';
    }
}
