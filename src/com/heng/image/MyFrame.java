package com.heng.image;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyFrame extends JFrame
{
	public MessagePanel messagePanel;//地图信息设置面板
	public MapPanel mapPanel;//地图编辑面板
//	private JLabel label;

	public MyFrame() {
		init();
	}

	public void init(){
		this.setTitle("地图编辑器");
		this.setResizable(false);
		this.setSize(1300, 720);
		this.setDefaultCloseOperation(3);
		messagePanel = new MessagePanel(this);
		mapPanel = new MapPanel(messagePanel);
//		label = new JLabel();
		JMenu fileMenu = new JMenu("选择地图");
		JMenuItem menuItem = new JMenuItem("打开");

		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				openFile();//打开文件方法,选地图
			}
		});

		JMenu msgMenu = new JMenu("设置地图信息");
		JMenuItem setItem = new JMenuItem("设置宽高");
		setItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//设置地图信息
				System.out.println("=====输入地图信息");

			}
		});

		JMenuBar bar = new JMenuBar();
		fileMenu.add(menuItem);
		msgMenu.add(setItem);
		bar.add(fileMenu);
		bar.add(msgMenu);
		this.setJMenuBar(bar);

//		mapPanel.add(label);
//		this.setLayout(new FlowLayout());
		this.add(messagePanel,BorderLayout.WEST);
		this.add(mapPanel, BorderLayout.EAST);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void openFile() {

		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		File file = chooser.getSelectedFile();
		if(file == null){
			return;
		}
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
			//判断所拿文件是否为图片，如果不是图片啃肯定是取不到其宽高的
			if(bi == null || bi.getWidth() <= 0 || bi.getHeight() <= 0){
				return;
			}
			String path = file.getPath();
			ImageIcon image = new ImageIcon(path);
			mapPanel.setImageIcon(image);
//            System.out.println("image :" + image.getIconWidth());
//            label.setIcon(image);           //设置JLabel的显示图片
//            label.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取地图数组
	 */
	public int[][] getMapArray(){
		return mapPanel.getMapArray();
	}
}
