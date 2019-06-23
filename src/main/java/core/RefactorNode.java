package core;

import java.util.ArrayList;
import java.util.List;

public class RefactorNode {
    private String data;
    private List<RefactorNode> nodes; // RefactorNode.data type
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
}
