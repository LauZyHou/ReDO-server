package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class RefactorNodeTest {

    @Test
    public void countTest() {
        BraceToTree braceToTree=new BraceToTree();
        RefactorNode res = braceToTree.convert("(1,(10,(30,40)),(3,4,5))");
        assertEquals(11,res.countAllSubNodes());
        assertEquals(3,res.countCurrentSubNodes());
    }
}