package com.ecnu.refactoring.desktop;

import com.ecnu.refactoring.core.RefactorNode;
import com.ecnu.refactoring.service.DesktopDemoShowService;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class GraphDisplayWindow extends JFrame {
    private static final long serialVersionUID = 1537251478935267819L;

    private JToolBar subGraphClickBar;
    private JLabel picture;
    private JPanel contentPane;
    private Stack<RefactorNode> history;
    //Very bad design!!!
    private String[] columnMeaning;
    private GraphGeneration graphGeneration;
    private static final int WINDOW_WIDTH=1000;
    private static final int WINDOW_HEIGHT=1000;


    /**
     * Just initial the window, and run {@method displayNodeGraph} method.
     * @param displayNode initial node
     */
    public GraphDisplayWindow(RefactorNode displayNode,String[] columnMeaning) {
        // Title bar
        super("Graph Display");
        // respond to the window system asking us to quit
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.history=new Stack<>();
        this.columnMeaning=columnMeaning;
        // Window Layout
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setResizable(true);
        subGraphClickBar = new JToolBar();

        // Content Layout
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());


        // initial necessary object
        graphGeneration = new GraphGeneration();

        displayNodeGraph(displayNode);
    }

    public void displayNodeGraph(RefactorNode displayNode) {
        contentPane.removeAll();
        System.out.println(displayNode.getNodes().size());
        BufferedImage image = graphGeneration.generateByRefactorNode(displayNode,columnMeaning);

        // Set Layout
        picture = new JLabel(new ImageIcon(image));
        picture.setBounds(0,0,fitWindowSize(image.getWidth(), image.getHeight()).width,fitWindowSize(image.getWidth(), image.getHeight()).height);
//        picture.setPreferredSize();
        setPictureLayout();
        setBarButtons(displayNode);

        setBarLayout();
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);

        contentPane.repaint();
        System.out.println("finished");
    }

    private void setBarLayout() {
        // Layout the content pane.
        subGraphClickBar.setPreferredSize(new Dimension(WINDOW_WIDTH, 30));
        subGraphClickBar.setFloatable(false);
        subGraphClickBar.setOrientation(SwingConstants.HORIZONTAL);
        contentPane.add(subGraphClickBar, BorderLayout.NORTH);
    }

    private void setPictureLayout() {
        // Layout the content pane.
        //picture.setSize(fitWindowSize(picture.getWidth(), picture.getHeight()));
        DragListener listener=new DragListener();
        picture.addMouseListener(listener);
        picture.addMouseMotionListener(listener);
        // add() is valid only when contentPane is empty.
        contentPane.add(picture, BorderLayout.CENTER);

    }

    private Dimension fitWindowSize(int width, int height) {

        if(width*1.0/WINDOW_WIDTH>=height*1.0/WINDOW_HEIGHT){ //compress by width, leave height
            height=(int)Math.round( WINDOW_WIDTH*1.0/width*height);
            width=WINDOW_WIDTH;
        }
        else{
            width=(int)Math.round( WINDOW_HEIGHT*1.0/height*width);
            height=WINDOW_HEIGHT;
        }
        return new Dimension(width,height);
    }

    private void setBarButtons(RefactorNode refactorNode) {
        List<RefactorNode> nodes=refactorNode.getNodes();
        subGraphClickBar.removeAll();

        JButton fileUp = new JButton("Upload File...");
        fileUp.setEnabled(true);
        fileUp.addActionListener(e->{
            JFileChooser fileDialog=new JFileChooser();
            fileDialog.showDialog(this,"Choose XML File");
            DesktopDemoShowService.show( fileDialog.getSelectedFile(),this);
        });
        subGraphClickBar.add(fileUp);

        // Extract column meaning from node.
        String[] columnNames=new String[refactorNode.getNodes().size()];
        for(int i=0;i<refactorNode.getNodes().size();i++){
            String str=refactorNode.getNodes().get(i).getData();
            if(!refactorNode.getNodes().get(i).isCombined()) {
                int index = Integer.parseInt(str);
                columnNames[i]= columnMeaning[index];
            }
            else{
                columnNames[i]=str;
            }
        }


        for (int i = 0; i < nodes.size(); i++) {
            RefactorNode t = nodes.get(i);
//            JButton button = new JButton(nodes.get(i).getData());
            JButton button = new JButton(columnNames[i]);
            if(t.isCombined())  button.setEnabled(true);
            else button.setEnabled(false);
            button.addActionListener(e -> {
                button.setEnabled(false);
                history.push(refactorNode);
                displayNodeGraph(t);
            });
            subGraphClickBar.add(button);
        }
        subGraphClickBar.addSeparator();
        if (!history.empty()) {
            JButton button = new JButton("Back");
            button.setEnabled(true);
            button.addActionListener(e -> {

                RefactorNode his= history.pop();
                displayNodeGraph(his);

            });
            subGraphClickBar.add(button);
        }

    }

    class DragListener implements MouseInputListener {
        Point point = new Point(0, 0);

        public void mousePressed(MouseEvent e) {
            point = SwingUtilities.convertPoint(picture, e.getPoint(), picture.getParent());
            picture.repaint();
        }

        public void mouseDragged(MouseEvent e) {
            Point newPoint = SwingUtilities.convertPoint(picture, e.getPoint(), picture.getParent());
            picture.setLocation(picture.getX() + (newPoint.x - point.x), picture.getY() + (newPoint.y - point.y));
            point = newPoint;
            picture.repaint();
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
        }
    }
}

