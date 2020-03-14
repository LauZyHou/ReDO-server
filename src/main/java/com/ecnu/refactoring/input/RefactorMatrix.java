package com.ecnu.refactoring.input;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
public class RefactorMatrix {
    double[][] refactorMatrix;
    String[] columnMeaning;

    public RefactorMatrix(double[][] refactorMatrix, String[] columnMeaning) {
        this.refactorMatrix = refactorMatrix;
        this.columnMeaning = columnMeaning;
    }

    @Override
    public String toString() {
        return "RefactorMatrix{" +
                "refactorMatrix=" + Arrays.deepToString(refactorMatrix) +
                ", columnMeaning=" + Arrays.toString(columnMeaning) +
                '}';
    }
}
