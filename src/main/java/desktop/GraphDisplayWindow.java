package desktop;

import core.GraphGeneration;
import core.RefactorNode;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphDisplayWindow extends JFrame {
    private static final long serialVersionUID = 1537251478935267819L;

    JToolBar subGraphClick;
    //List<JButton> subGraphButton;
    JLabel picture;
   final RefactorNode refactorNode;

    public GraphDisplayWindow(RefactorNode displayNode) {
        // Title bar
        super("Graph Display");
        // respond to the window system asking us to quit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(true);
        this.refactorNode = displayNode;
        displayNodeGraph(displayNode);
    }

    private void displayNodeGraph(RefactorNode displayNode) {
        GraphGeneration graphGeneration = new GraphGeneration();
        picture = new JLabel(new ImageIcon(graphGeneration.generateByRefactorNode(displayNode)));

        subGraphClick = new JToolBar();
        addWindowButtons(refactorNode.getNodes());
    }

    protected void addWindowButtons(List<RefactorNode> nodes) {
        for ( int i = 0; i < nodes.size(); i++) {
            addButton(nodes.get(i).getData(), null, event -> nodes.get(i), subGraphClick);
        }
        subGraphClick.addSeparator();
        addButton("Back", "Back to Parent", event -> {
            return;
        }, subGraphClick);
    }


    /**
     * Do not revise, it's the function to add a button.
     *
     * @param explicitText   the name explicit shown on the board
     * @param implicitText   the name implicit shown on the board (when the mouse touch the button, it'll show.)
     * @param actionListener the actions if clicking the button.
     */
    private JButton addButton(String explicitText, String implicitText, ActionListener actionListener, boolean enabled, javax.swing.JToolBar toolbar) {
        JButton button;
        button = new JButton(explicitText);
        button.setToolTipText(implicitText);
        button.setEnabled(enabled);
        // when this button is pushed it calls animationWindow.setMode(true)
        button.addActionListener(actionListener);
        toolbar.add(button);
        return button;
    }

    private JButton addButton(String explicitText, String implicitText, ActionListener actionListener, javax.swing.JToolBar toolbar) {
        return addButton(explicitText, implicitText, actionListener, true, toolbar);
    }


}

