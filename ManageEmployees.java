import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ManageEmployees {
    static HashMap<String,DefaultMutableTreeNode> mp=new HashMap<>();
    static DefaultMutableTreeNode root;
    static JTree tree;
    static JFrame frame;
    JButton trace =new JButton("Trace");
    static DefaultMutableTreeNode selectedNode;
    JTextField tfnew=new JTextField();
    JButton btnDelete=new JButton("Delete");
    JPanel panel =new JPanel();
    ManageEmployees()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root = new DefaultMutableTreeNode("Root");
        mp.put("Root",root);
        System.out.println(mp.get("Root"));
        tree = new JTree(root);
        JButton dbt =new JButton("Add");
        JButton btnAdd=new JButton("ADD");
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                System.out.println(selectedNode.getChildCount());

            }
        });
        dbt.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        InputDialog Dlg = new InputDialog(frame);
                        Dlg.setVisible(true);
                    }
                });
        trace.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        TraceDialog Dlg = new TraceDialog(frame);
                        Dlg.setVisible(true);
                    }
                });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectedNode==null)
                {
                    JOptionPane.showMessageDialog(frame,"No node selected");
                    return;
                }
                AddDialog Dlg = new AddDialog(frame);
                Dlg.setVisible(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectedNode != tree.getModel().getRoot())
                {
                    DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                    DefaultMutableTreeNode parent=(DefaultMutableTreeNode)selectedNode.getParent();

                    while(selectedNode.getChildCount()>0)
                    {
                        System.out.println(selectedNode.getChildAt(0));
                        parent.add((DefaultMutableTreeNode)selectedNode.getChildAt(0));
                    }
                    model.removeNodeFromParent(selectedNode);

                    model.reload();
                }
            }
        });

        tree.repaint();
        panel.setBounds(295,0,90,120);
        panel.add(tree);
        tree.setBounds(0,40,400,300);
        panel.add(dbt);
        panel.add(trace);
        panel.add(btnAdd);
        panel.add(btnDelete);
        frame.add(tree);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    static void update (String a,String b)
    {
        DefaultMutableTreeNode node=ManageEmployees.mp.get(b);
        DefaultMutableTreeNode newNode=new DefaultMutableTreeNode(a);
        ManageEmployees.mp.put(a,newNode);
        System.out.println(node);
        node.add(newNode);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
    }
    static String traceIt(String name)
    {
        TreeNode node=mp.get(name);
        if(node==null)
        {
            return "No Employee found!";
        }
        String temp="->",str;
        str=node.toString();
        if(node!=root) {
            node = node.getParent();
            while (true) {
                str += temp;
                str += node.toString();
                if(node==root)
                {
                    break;
                }
                node = node.getParent();
            }
        }
        return str;
        //JOptionPane.showMessageDialog(frame,str);
    }
    static void f(String name)
    {
        JOptionPane.showMessageDialog(frame,traceIt(name));
    }
    static void focus(String s)
    {
        DefaultTreeModel model = (DefaultTreeModel) ManageEmployees.tree.getModel();
        TreeNode[] nodes = model.getPathToRoot(ManageEmployees.mp.get(s));
        TreePath path = new TreePath(nodes);
        System.out.println(path.toString());
        ManageEmployees.tree.setExpandsSelectedPaths(true);
        ManageEmployees.tree.setSelectionPath(new TreePath(nodes));
    }
    public static void main(String args[]) {
        new ManageEmployees();
    }
}