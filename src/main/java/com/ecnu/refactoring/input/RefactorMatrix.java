package com.ecnu.refactoring.input;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RefactorMatrix {
    double[][] refactorMatrix;
    String[] columnMeaning;

    public RefactorMatrix(double[][] refactorMatrix, String[] columnMeaning) {
        this.refactorMatrix = refactorMatrix;
        this.columnMeaning = columnMeaning;
    }


}
