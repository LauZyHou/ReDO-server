package com.ecnu.redo.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class PhaseNode {
    private String data;
    private List<PhaseNode> nodes; // RefactorNode.data type
    private double[][] costMatrix;
    public PhaseNode(String data){
        this.nodes=new ArrayList<PhaseNode>();
        this.data=data;
    }
    private boolean combined=false;

    public String[] getChildData(){
        String[] res=new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            res[i]=nodes.get(i).getData();
        };
        return res;
    }

    public void addChild(PhaseNode child){
        this.nodes.add(child);
    }

    public String getData() {
        return data;
    }

    public List<PhaseNode> getNodes() {
        return nodes;
    }

    public void setData() {
        headNameGeneration(nodes.get(0).getData());
    }

    public double[][] getCostMatrix() {
        return costMatrix;
    }



    /**
     * It can be optimized to return the integer if hashing function is designed pretty well.
     * Will be supported later
     * @param s
     * @return
     */
    public void headNameGeneration(String s){
        combined=true;
        this.data= 'C'+s;
    }

    public boolean isCombined() {
        return combined;
    }




    public int countAllSubNodes(){
        if(nodes.isEmpty()) return 1;
        else return nodes.stream().mapToInt((a) -> a.countAllSubNodes()).sum() +1;
    }
    public List<String> findAllLeafNodes() {
        if (nodes.isEmpty()) return Arrays.asList(data);
        else{
            List<String> ret = nodes.stream().flatMap((a) -> a.findAllLeafNodes().stream()).collect(Collectors.toList());
        return ret;
    }
    }

    public int countCurrentSubNodes(){
        return nodes.size();
    }

    private int [] convertFromNodesMeaningToMatrixIndex(List<String> searchLabel, List<String> columnMeaning){
        int[] positions=new int[searchLabel.size()];
        for(int i=0;i<searchLabel.size();i++){
            String s=searchLabel.get(i);
            int pos=columnMeaning.indexOf(s);
            if(pos==-1){
                throw new IllegalArgumentException("Unmatched between conlumnMeaning = " + searchLabel.toString()+
                        " all leaves from this node.");
            }
            else{
                positions[i]=pos;
            }
        }
        return positions;
    }

    public void generateStructuredComplexityMatrix(double[][] unstructuredMatrix, List<String> columnMeaning) {
        if(nodes.isEmpty()){
            costMatrix =unstructuredMatrix;
            return;
        }
        Map<PhaseNode,Double> moduleComplexity=new ConcurrentHashMap<>();
        /// Can parallel!
        for(PhaseNode r:nodes){
            List<String> subnodes=r.findAllLeafNodes();
            int[] nodesReadyForExtract=convertFromNodesMeaningToMatrixIndex(subnodes,columnMeaning);
            double[][] subMatrix=new double[nodesReadyForExtract.length][nodesReadyForExtract.length];
            for(int i=0;i<nodesReadyForExtract.length;i++){
                for(int j=0;j<nodesReadyForExtract.length;j++){
                    subMatrix[i][j]= unstructuredMatrix[nodesReadyForExtract[i]][nodesReadyForExtract[j]];
                }
            }
            List<String> subMatrixTag=new ArrayList<>();
            for(int i=0;i<nodesReadyForExtract.length;i++){
               subMatrixTag.add( columnMeaning.get( nodesReadyForExtract[i]) );
            }
            r.generateStructuredComplexityMatrix(subMatrix,subMatrixTag);
            double subMatrixComplexity=GeneticAlgorithm.cal_alpha(subMatrix)+GeneticAlgorithm.cal_data(subMatrix);
            moduleComplexity.put(r,(Double)subMatrixComplexity);
        }
        constructComplexityMatrix(unstructuredMatrix, columnMeaning, moduleComplexity);
    }

    /**
     * Try to construct cost matrix.
     * Optimize: matrix is symmetrics-- a new Java Class to store, get and set.
     * Complexity is O(n*n*m*m), n is the column of unstructuredMatrix, m is the column of costMatrix in this class.
     * @param unstructuredMatrix
     * @param columnMeaning
     * @param costComplexity
     */
    private void constructComplexityMatrix(double[][] unstructuredMatrix, List<String> columnMeaning, Map<PhaseNode, Double> costComplexity) {
        costMatrix =new double[nodes.size()][nodes.size()];
        for(int i = 0; i< costMatrix.length; i++){
            for(int j = 0; j< costMatrix.length; j++){
                if(i==j){
                    Double p=costComplexity.get(nodes.get(i));
                    costMatrix[i][j]=p;
                }
                else{
                    int [] sources=convertFromNodesMeaningToMatrixIndex(nodes.get(i).findAllLeafNodes(),columnMeaning);
                    int [] destinations=convertFromNodesMeaningToMatrixIndex(nodes.get(j).findAllLeafNodes(),columnMeaning);
                    double summary=0;
                    for(int ii=0;ii<sources.length;ii++) {
                        for (int jj = 0; jj < destinations.length; jj++) {
                            summary+=unstructuredMatrix[ sources[ii]][destinations[jj]];
                        }
                    }
                    costMatrix[i][j]=summary;
                }
            }
        }
    }

}
