package gol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class LifePanel extends JPanel implements ActionListener
{
	
	int XPanel = 1920;
	int YPanel = 1080;
	int size = 10;
	int xWidth = XPanel / size;
	int yHeight = YPanel / size;
	int [][] life = new int [xWidth][yHeight];
	int[][] beforeLife = new int[xWidth][yHeight];
	
	
	
	boolean starts = true;
	
	
	public LifePanel()
	{
		setSize(XPanel, YPanel);
		setLayout(null);
		setBackground(Color.BLACK);
		new Timer( 80, this).start();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.getHeight() + " " + screenSize.getWidth());
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//g.setColor(Color.ORANGE);
		
		
		grid(g);
		spawn(g);
		display(g);
		
	}
	
	private void grid(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < life.length; i++)
		{
			g.drawLine(0, i * size, XPanel, i * size);
			g.drawLine(i * size, 0, i * size, YPanel);
		}
	}
	
	private void spawn(Graphics g) 
	{
		if(starts == true)
		{
			for(int x = 0; x < life.length; x++)
			{
				for(int y = 0; y < (yHeight); y++)
				{
					if((int)(Math.random() * 5) == 0)
					{
						beforeLife[x][y] = 1;
						System.out.println(beforeLife[x].toString() + ", " + beforeLife[y].toString() + ": " + beforeLife[x][y]);
					}
				}
			}
			starts = false;
		}
	}
	
	private void display(Graphics g)
	{
		g.setColor(Color.GREEN);
		
		copyArray();
		
		for(int x = 0; x < life.length; x++)
		{
			for(int y = 0; y < (yHeight); y++)
			{
				if(life[x][y] == 1)
				{
					g.fillRect(x * size, y * size, size, size);
				}
			}
		}
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		int alive;
		
		for(int x = 0; x < life.length; x++ )
		{
				for(int y = 0; y < (yHeight); y++)
				{
					 alive = check(x, y);
					 
					 if(alive == 3)
					 {
						 beforeLife[x][y] = 1;
					 }
					 else if(alive == 2 && life[x][y] == 1)
					 {
						 beforeLife[x][y] = 1;
					 }
					 else
					 {
						 beforeLife[x][y] = 0;
					 }
				}
		}
		
		repaint();
		
		
		
	}
	
	private int check(int x, int y)
	{
		int alive = 0;
		
		alive +=life[(x + xWidth-1) % xWidth][(y + yHeight-1) % yHeight];
		alive += life[x % xWidth][(y + yHeight-1) % yHeight];
		alive += life[(x + xWidth+1) % xWidth][(y + yHeight-1) % yHeight];
		alive += life[((x + xWidth) + xWidth-1) % xWidth][(y + yHeight) % yHeight];
		alive += life[(x + xWidth+1) % xWidth][(y + yHeight) % yHeight];
		alive += life[((x + xWidth) + xWidth-1) % xWidth][(y + yHeight +1) % yHeight];
		alive += life[((x + xWidth) + xWidth) % xWidth][(y + yHeight+1) % yHeight];
		alive += life[(x + xWidth+1) % xWidth][(y + yHeight+1) % yHeight];
		
		return alive;
	}
	
	private void copyArray()
	{
		for(int x = 0; x < life.length; x++ )
		{
				for(int y = 0; y < (yHeight); y++)
				{
					life[x][y] = beforeLife[x][y];
					 
				}
		}
	}
}
