package com.swing.test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MsgLoadFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = -1189035634361220261L;
    JFrame mainframe;
    JPanel panel;
    //������ص�Label��ǩ
    JLabel infilepath_label = new JLabel("�����汾��:");
    JLabel outfilepath_label = new JLabel("������·��:");
    JLabel outlogpath_label = new JLabel("������·��:");    
    //������ص��ı���
    JTextField infilepath_textfield = new JTextField(20);
    JTextField outfilepath_textfield = new JTextField(20);
    JTextField outlogpath_textfield = new JTextField(20);
    //�����������Լ�����ı���
    JScrollPane jscrollPane;
    JTextArea outtext_textarea = new JTextArea();
    //������ť
    JButton infilepath_button = new JButton("...");
    JButton outfilepath_button = new JButton("...");
    JButton outlogpath_button = new JButton("..."); 
    JButton start_button = new JButton("����");

    public void show(){
        mainframe = new JFrame("IT��ǹ��");
        // Setting the width and height of frame
        mainframe.setSize(575, 550);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);//�̶������С

        Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰� 
        Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ� 
        int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
        int height = mainframe.getHeight(); //��ȡ���ڸ߶�
        int width = mainframe.getWidth(); //��ȡ���ڿ��
        mainframe.setLocation(screenWidth-width/2, screenHeight-height/2);//���������õ���Ļ���в�             
        //������У�c��Component��ĸ�����
        //mainframe.setLocationRelativeTo(c);   
        Image myimage=kit.getImage("/resource/skull.png"); //��tool��ȡͼ��
        mainframe.setIconImage(myimage);
        initPanel();//��ʼ�����
        mainframe.add(panel);
        mainframe.setVisible(true);
    }
     /* ������壬��������� HTML �� div ��ǩ
     * ���ǿ��Դ��������岢�� JFrame ��ָ��λ��
     * ��������ǿ�������ı��ֶΣ���ť�����������
     */
    public void initPanel(){
        this.panel = new JPanel();
        panel.setLayout(null);
        //this.panel = new JPanel(new GridLayout(3,2)); //����3��3�е�����     
        /* ������������������λ�á�
         * setBounds(x, y, width, height)
         * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
         */
        infilepath_label.setBounds(10,20,120,25);
        infilepath_textfield.setBounds(120,20,400,25);
        infilepath_button.setBounds(520,20, 30, 25);
        this.panel.add(infilepath_label);
        this.panel.add(infilepath_textfield);
        this.panel.add(infilepath_button);

        outfilepath_label.setBounds(10,50,120,25);
        outfilepath_textfield.setBounds(120,50,400,25);
        outfilepath_button.setBounds(520,50, 30, 25);
        this.panel.add(outfilepath_label);
        this.panel.add(outfilepath_textfield);
        this.panel.add(outfilepath_button);

        outlogpath_label.setBounds(10,80,120,25);
        outlogpath_textfield.setBounds(120,80,400,25);
        outlogpath_button.setBounds(520,80, 30, 25);
        this.panel.add(outlogpath_label);
        this.panel.add(outlogpath_textfield);
        this.panel.add(outlogpath_button);

        start_button.setBounds(10,120,80,25);
        this.panel.add(start_button);

        outtext_textarea.setEditable(false);
        outtext_textarea.setFont(new Font("�꿬��", Font.BOLD, 16));
        jscrollPane = new JScrollPane(outtext_textarea);
        jscrollPane.setBounds(10,170, 550, 330);
        this.panel.add(jscrollPane);
        //���Ӷ�������
        infilepath_button.addActionListener(this);
        outfilepath_button.addActionListener(this);
        outlogpath_button.addActionListener(this);
        start_button.addActionListener(this);
    }
    /**
     * ����������������
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub
        System.out.println(event.getActionCommand());
        if(event.getSource() == start_button){
            //ȷ�϶Ի��򵯳�       
            int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ʼת��?", "ȷ��", 0);//YES_NO_OPTION
            if (result == 1) {//�ǣ�0����1��ȡ����2
                return;
            }
            System.out.println(infilepath_textfield.getText());
            if (infilepath_textfield.getText().equals("") || outfilepath_textfield.getText().equals("")
                    || outlogpath_textfield.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "·������Ϊ��", "��ʾ", 2);//������ʾ�Ի���warning
                return;
            }else{
                outtext_textarea.setText("");
                String infilepath = infilepath_textfield.getText();
                String outfilepath = outfilepath_textfield.getText();
                String outlogpath = outlogpath_textfield.getText();
                outtext_textarea.setText("�˴��������Ϣ(����־)");
                result = JOptionPane.showConfirmDialog(null, "�Ƿ����־�ļ�?", "ȷ��", 0);//YES_NO_OPTION
                if (result == 0) {//�ǣ�0����1��ȡ����2
                    try {
                            @SuppressWarnings("unused")
                            Process process = Runtime.getRuntime().exec("cmd.exe  /c notepad "+outlogpath+"\\log.log");//����cmd����ʹ�ü��±����ļ�
                        } catch (IOException e) {
                            e.printStackTrace();
                        }   
                }
            }       
        }else{
            //�ж�����ѡ��ť����Ӧ����
            if(event.getSource() == infilepath_button) {
                File file = openChoseWindow(JFileChooser.FILES_ONLY);
                if(file == null)
                    return;
                infilepath_textfield.setText(file.getAbsolutePath());
                outfilepath_textfield.setText(file.getParent()+"\\out");
                outlogpath_textfield.setText(file.getParent()+"\\log");
            }else if(event.getSource() == outfilepath_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY);
                if(file == null)
                    return;
                outfilepath_textfield.setText(file.getAbsolutePath()+"\\out");
            }else if(event.getSource() == outlogpath_button){
                File file = openChoseWindow(JFileChooser.DIRECTORIES_ONLY);
                if(file == null)
                    return;
                outlogpath_textfield.setText(file.getAbsolutePath()+"\\log");
            }           
        }
    }
    /**
     * ��ѡ���ļ����ڲ������ļ�
     * @param type
     * @return
     */
    public File openChoseWindow(int type){
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(type);//ѡ����ļ�����(�ļ���or�ļ�)  
        jfc.showDialog(new JLabel(), "ѡ��");  
        File file=jfc.getSelectedFile();
        return file;
    }
    public void windowClosed(WindowEvent arg0) {         
        System.exit(0);
    } 
    public void windowClosing(WindowEvent arg0) { 
        System.exit(0);
    }
}