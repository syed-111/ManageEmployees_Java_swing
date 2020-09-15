import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class InputDialog extends JDialog {

    private JTextField tfName;
    private JTextField tfManager;
    private JLabel lbName;
    private JLabel lbManager;
    private JButton btnAdd;
    private JButton btnCancel;

    public InputDialog(Frame parent) {
        super(parent, "Entry", true);
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

        lbManager = new JLabel("Manager Name: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbManager, cs);

        tfManager = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfManager, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnAdd = new JButton("Add");

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node=ManageEmployees.mp.get(tfManager.getText());
                if(node==null)
                {
                    JOptionPane.showMessageDialog(InputDialog.this,
                            "No Employee found!",
                            "",
                            JOptionPane.ERROR_MESSAGE);
                    tfName.setText("");
                    tfManager.setText("");
                }
                else
                ManageEmployees.update(tfName.getText(),tfManager.getText());
                ManageEmployees.focus(tfName.getText());
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