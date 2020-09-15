import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TraceDialog extends JDialog {

    private JTextField tfName;
    private JLabel lbName;
    private JButton btnAdd;
    private JButton btnCancel;

    public TraceDialog(Frame parent) {
        super(parent, "Entry", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbName = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbName, cs);

        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfName, cs);


        panel.setBorder(new LineBorder(Color.GRAY));

        btnAdd = new JButton("Trace");

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //model.reload();
                ManageEmployees.focus(tfName.getText());
                //ManageEmployees.tree.setSelectionPath(new TreePath(nodes));
                //ManageEmployees.f(tfName.getText());
                dispose();
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnAdd);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}