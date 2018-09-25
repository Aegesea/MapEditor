package com.heng.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 地图信息设置面板
 * @author 黎荣恒
 *
 */
public class MessagePanel extends JPanel implements ActionListener{

	private JLabel widthLabel;
	private JLabel hightLabel;
	private JLabel fileNameLabel;
	private JLabel locLabel;
	private JTextField widthField;
	private JTextField heightField;
	private JTextField fileName;//文件名输入框
	private JButton gridBtn;
	private JButton copyBtn;
	private MyFrame frame;
	private int width = 0;
	private int height = 0;

	public MessagePanel(MyFrame frame){
		this.frame = frame;
		init();
	}

	/**
	 * 初始化函数
	 */
	private void init(){
		setPreferredSize(new Dimension(200, 720));
		this.setBackground(Color.GRAY);

		widthLabel = new JLabel("地图宽：");
		hightLabel = new JLabel("地图高:");
		fileNameLabel = new JLabel("文件名：");
		JLabel label = new JLabel("                                                       ");
		JLabel label1 = new JLabel("                                                       ");
		locLabel = new JLabel();
		widthField = new JTextField(10);
		heightField = new JTextField(10);
		fileName = new JTextField(10);
		gridBtn = new JButton("生成地图网格");
		copyBtn = new JButton("生成地图文件");
		gridBtn.addActionListener(this);
		copyBtn.addActionListener(this);
		add(widthLabel);
		add(widthField);
		add(hightLabel);
		add(heightField);
		add(gridBtn);
		add(label1);
		add(fileNameLabel);
		add(fileName);
		add(copyBtn);

		add(locLabel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getActionCommand().equals("生成地图网格"))
		{
			width = Integer.parseInt(widthField.getText());
			height = Integer.parseInt(heightField.getText());
			frame.repaint();
		}
		else if(event.getActionCommand().equals("生成地图文件"))
		{
			String n_fileName = fileName.getText();
			if(n_fileName == null || n_fileName.equals("")){
				showTip("文件名不能为空！");
			} else {
				try {
					int[][] mapArray = frame.getMapArray();
					if(mapArray == null || mapArray.length == 0 || mapArray[0].length == 0){
						showTip("地图并没有数据，请输入数据！");
					}
					else{
						CreateFile.creatTxtFile(n_fileName);
						String str = mapArray.length + "," + mapArray[0].length + "\r\n";

						for (int i = 0; i < mapArray.length; i++) {
							for (int j = 0; j < mapArray[0].length; j++) {
								str = str + mapArray[i][j] + ",";
							}
							str = str + "\r\n";
						}
						boolean b = CreateFile.writeTxtFile(str);
						if(b){
							showTip("文件生成在D盘目录中！");
						}else{
							showTip("文件生成失败！");
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getWidthNum(){
		return width;
	}

	public int getHeightNum(){
		return height;
	}

	private void showTip(String msg){
		JOptionPane.showMessageDialog(this,
				msg, "系统信息", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setLocation(int x, int y){
		System.out.println("x = " + x + ", y = " + y);
		locLabel.setText("数组位置：x = " + x + ", y = " + y);
	}

}
