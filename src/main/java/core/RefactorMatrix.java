package core;

public class RefactorMatrix {
    double[][] data;

    public RefactorMatrix(double[][] originalData){
        data=originalData;
    }
    public RefactorMatrix(double[][] originalData,String fu){
        BraceToTree braceToTree=new BraceToTree();
        RefactorNode refactorNode= braceToTree.convert(GeneticAlgorithm.refactor(originalData));
        data=new double[ refactorNode.countAllSubNodes()][refactorNode.countAllSubNodes()];
        for(int i=0;i<originalData.length;i++){
            for(int j=0;j<originalData.length;j++){
                data[i][j]=originalData[i][j];
            }
            for(int j=originalData.length;j<data.length;j++){
                data[i][j]=-1;
            }
        }
        for(int i=originalData.length;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                data[i][j]=-1;
            }
        }
        //refactorNode.generateSubMatrix(data);
    }
}
