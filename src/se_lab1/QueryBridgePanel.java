package se_lab1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class QueryBridgePanel extends JPanel {
    private static final long serialVersionUID = 7264749733574435443L;
    JTextField tfWord1 = new JTextField(112);
    JLabel lbWord1 = new JLabel("单词1: ");
    JTextField tfWord2 = new JTextField(112);
    JLabel lbWord2 = new JLabel("单词2: ");
    JLabel lbRst = new JLabel();
    JButton btnQB = new JButton("开始查询");

    public QueryBridgePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.btnQB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    QueryBridgePanel.this.lbRst.setText(Lab1.t.queryBridgeWords(QueryBridgePanel.this.tfWord1.getText(), QueryBridgePanel.this.tfWord2.getText()));
                } catch (Exception var3) {
                }

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.setLayout(new GridBagLayout());
        this.add(this.lbWord1, gbc);
        gbc.gridy = 1;
        this.add(this.tfWord1, gbc);
        gbc.gridy = 2;
        this.add(this.lbWord2, gbc);
        gbc.gridy = 3;
        this.add(this.tfWord2, gbc);
        gbc.gridy = 4;
        this.add(this.btnQB, gbc);
        gbc.gridy = 5;
        this.add(this.lbRst, gbc);
    }
}
// 注释