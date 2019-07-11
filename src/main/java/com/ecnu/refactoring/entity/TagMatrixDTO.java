package com.ecnu.refactoring.entity;

import javax.annotation.sql.DataSourceDefinition;

public class TagMatrixDTO {
    double[][] matrix;
    String[] tag;
    String name;
    public TagMatrixDTO(){

    }

    public TagMatrixDTO(double[][] matrix, String[] tag,String name) {
        if(matrix.length!=tag.length){return;}
        if(matrix.length!=matrix[0].length){return;}
        this.matrix = matrix;
        this.tag = tag;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }
}
