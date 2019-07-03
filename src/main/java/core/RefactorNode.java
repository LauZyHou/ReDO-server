package core;

import java.util.ArrayList;
import java.util.List;


public class RefactorNode {
    private String data;
    private List<RefactorNode> nodes; // RefactorNode.data type
    private double[][] complexityMatrix;
    public RefactorNode(String data){
        this.nodes=new ArrayList<RefactorNode>();
        this.data=data;
    }
    public void addChild(RefactorNode child){
        this.nodes.add(child);
    }

    public String getData() {
        return data;
    }

    public List<RefactorNode> getNodes() {
        return nodes;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RefactorNode{" +
                "data='" + data + '\'' +
                ", nodes=" + nodes +
                '}';
    }


    public int countAllSubNodes(){
        if(nodes.isEmpty()) return 1;
        else return nodes.stream().mapToInt((a) -> a.countAllSubNodes()).sum() +1;
    }

    public int countCurrentSubNodes(){
        return nodes.size();
    }

    public void generateSubMatrix(double[][] data) {
        for(RefactorNode r:nodes){
            if(r.getNodes().isEmpty()){

            }
        }
    }
}
