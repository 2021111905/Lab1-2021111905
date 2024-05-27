package se_lab1;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

class NewTextPanel extends JPanel {
    private static final long serialVersionUID = -2862015187279261925L;
    JTextArea taText = new JTextArea(3, 40);
    JLabel lbText = new JLabel("新文本: ");
    JLabel lbRst = new JLabel();
    JButton btnG = new JButton("开始生成");

    public NewTextPanel() {
        this.taText.setBorder(new LineBorder(new Color(127, 157, 185), 1, false));
        this.taText.setLineWrap(true);
        this.btnG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    NewTextPanel.this.lbRst.setText(Lab1.t.generateNewText(NewTextPanel.this.taText.getText()));
                } catch (Exception var3) {
                }

            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.setLayout(new GridBagLayout());
        this.add(this.lbText, gbc);
        gbc.gridy = 1;
        this.add(this.taText, gbc);
        gbc.gridy = 4;
        this.add(this.btnG, gbc);
        gbc.gridy = 5;
        this.add(this.lbRst, gbc);
    }
}
