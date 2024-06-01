package se_lab1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
class MyFrame extends JFrame  {
    private static final long serialVersionUID = -6904245993409935448L;
    private static final int WIDTH = 520;
    private static final int height = 550;
    PicDisplayPanel picPanel;

    public MyFrame() {
        try {
            String feel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(feel);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        this.setBackground(Color.WHITE);
        this.setTitle("Flow chart");
        this.setSize(520, 550);
        this.setDefaultCloseOperation(3);
        this.setExtendedState(6);
        this.setLocationRelativeTo((Component)null);
        this.setLayout(new BorderLayout());
        Container c = this.getContentPane();
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("文件(F)");
        mFile.setMnemonic('F');
        JMenuItem miOpen = new JMenuItem("打开(O)");
        miOpen.setMnemonic('O');
        JMenuItem miReset = new JMenuItem("回复默认图(R)");
        miReset.setMnemonic('O');
        mFile.add(miOpen);
        mFile.add(miReset);
        miReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Lab1.readInFile();
                } catch (NullPointerException var3) {
                }

            }
        });
        miOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", new String[]{"txt"});
                fc.setFileFilter(filter);
                fc.setDialogTitle("Choose file");
                fc.setFileSelectionMode(0);
                fc.showOpenDialog(Lab1.f);
                File filename = fc.getSelectedFile();
                if (filename != null) {
                    Lab1.fileUrl = fc.getSelectedFile().getAbsolutePath();
                    Lab1.readInFile();
                }

            }
        });
        mb.add(mFile);
        this.setJMenuBar(mb);
        JPanel mainPanel = new JPanel(new GridLayout());
        PicDisplayPanel picPanel = new PicDisplayPanel();
        JScrollPane sp = new JScrollPane(picPanel);
        sp.validate();
        mainPanel.add(sp);
        FunctionPanel funcPanel = new FunctionPanel();
        mainPanel.add(funcPanel);
        c.add(mainPanel);
        this.setVisible(true);
    }

}
// 这个MyFrame类是一个继承自JFrame的自定义窗口类，主要用于创建并显示应用程序的主界面。其主要功能包括：

// 设置窗口外观：尝试将窗口外观设置为系统默认外观。
// 设置窗口属性：设置窗口背景颜色、标题、大小、关闭操作、最大化状态以及相对位置。
// 创建菜单栏：包括“文件(F)”菜单，菜单中有两个选项：“打开(O)”和“回复默认图(R)”。
// 打开(O)：使用文件选择器选择一个文本文件，并读取其内容。
// 回复默认图(R)：调用Lab1类中的readInFile方法读取默认文件。
// 创建并添加主面板：主面板使用GridLayout布局，包含两个子面板：
// 图片显示面板：PicDisplayPanel，放置在带滚动条的面板中。
// 功能面板：FunctionPanel，包含多个选项卡，每个选项卡对应不同的功能模块。
// 显示窗口：将主面板添加到内容面板，并将窗口设置为可见。
// 这个类通过构造方法完成了窗口的初始化、菜单栏和主面板的创建与布局，以及各种功能的集成，为用户提供了一个图形界面，方便操作和显示数据。