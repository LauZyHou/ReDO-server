package core;

import com.kitfox.svg.A;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.matchers.Contains;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class RefactorNodeTest {
    static RefactorNode res;
    @BeforeClass
    public static void bb(){
        BraceToTree braceToTree=new BraceToTree();
        res = braceToTree.convert("(1,(10,(30,40)),(3,4,5))");
    }
    @Test
    public void countTest() {
        assertEquals(11,res.countAllSubNodes());
        assertEquals(3,res.countCurrentSubNodes());
    }
    @Test
    public void findLeavesFromRoot(){
        assertThat(res.findAllLeafNodes(), Matchers.containsInAnyOrder(new String[]{"1","10","30","40","3","4","5"}));
    }
    @Test
    public void findLeavesFromLeaf(){
        assertThat(res.getNodes().get(0).findAllLeafNodes(), Matchers.containsInAnyOrder(new String[]{"1"}));
    }
    @Test
    public void findLeavesFromSubnodes(){
        assertThat(res.getNodes().get(1).findAllLeafNodes(), Matchers.containsInAnyOrder(new String[]{"10","30","40"}));
    }

}