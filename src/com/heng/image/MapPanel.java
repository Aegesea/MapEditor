package com.heng.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 地图编辑器面板
 * @author Administrator
 *
 */
public class MapPanel extends JPanel implements MouseListener{

	//	private JFrame frame;
	private MessagePanel panel;
	private ImageIcon imageIcon;
	private int gridWidth;
	private int gridHeight;
	private int gridWidthNum;//横向格子数
	private int gridHeightNum;//纵向格子数
	private int[][] mapArray;
	private int tempWidth,tempHeight;//宽高临时变量
	private Graphics2D g;


	public MapPanel(MessagePanel panel){
//		this.frame = this.frame;
		this.panel = panel;
		setPreferredSize(new Dimension(1090, 700));
//		this.setBackground(Color.GREEN);
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (imageIcon != null) {
			g.drawImage(imageIcon.getImage(), 0, 0, null);
		}
		gridWidthNum = panel.getWidthNum();//横向格子数
		gridHeightNum = panel.getHeightNum();//纵向格子数
		if(mapArray == null || gridWidthNum != tempWidth || gridHeightNum != tempHeight){
			mapArray = new int[gridWidthNum][gridHeightNum];//地图数组
			tempWidth = gridWidthNum;
			tempHeight = gridHeightNum;
			System.out.println("实例化数组");
		}
		if(gridWidthNum != 0 && gridHeightNum != 0){
			gridWidth = imageIcon.getIconWidth()/gridWidthNum;
			gridHeight = imageIcon.getIconHeight()/gridHeightNum;
			for (int i = 0; i < gridWidthNum + 1; i++) {
				g.drawLine(gridWidth * i, 0, gridWidth * i, imageIcon.getIconHeight());
			}
			for (int j = 0; j < gridHeightNum + 1; j++) {
				g.drawLine(0, gridHeight * j, imageIcon.getIconWidth(), gridHeight* j);
			}
		}
		if(mapArray != null){
			for (int i = 0; i < mapArray.length; i++) {
				for (int j = 0; j < mapArray[0].length; j++) {
					if(mapArray[i][j] == 1){
						g.setColor(Color.RED);
						g.fillOval(i * gridWidth, j * gridHeight, gridWidth, gridWidth);
						g.setColor(Color.WHITE);
					}
					else{
//						g.drawOval(i * gridWidth, j * gridHeight, gridWidth, gridWidth);

					}
				}
			}
		}

	}

	public void setImageIcon(ImageIcon imageIcon){
		this.imageIcon = imageIcon;
//		gridHeight = imageIcon.getIconHeight()
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		if(gridWidth != 0 && gridHeight != 0){
			int i = event.getX()/gridWidth;//列
			int j = event.getY()/gridHeight;//行
			panel.setLocation(i, j);
			//重新给数组赋值
			if(mapArray[i][j] == 1){
				mapArray[i][j] = 0;
			}else{
				mapArray[i][j] = 1;
			}
			repaint();
		}else{
			showTip("请输入宽高再进行地图编辑！");
		}
	}

	public int[][] getMapArray(){
		return mapArray;
	}

	private void showTip(String msg){
		JOptionPane.showMessageDialog(this,
				msg, "系统信息", JOptionPane.INFORMATION_MESSAGE);
	}

}
