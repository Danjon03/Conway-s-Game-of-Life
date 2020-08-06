package gol;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class LifePanel extends JPanel implements ActionListener, MouseListener, KeyListener
{
	
	int XPanel = 1920;
	int YPanel = 1080;
	int size = 10;
	int xWidth = XPanel / size;
	int yHeight = YPanel / size;
	int [][] life = new int [xWidth][yHeight];
	int[][] beforeLife = new int[xWidth][yHeight];
	
	
	
	
	int[][] userInput = new int [xWidth][yHeight];
	
	boolean starts = false;
	
	int mouseX;
	int mouseY;
	
	public LifePanel()
	{
		setSize(XPanel, YPanel);
		setLayout(null);
		setBackground(Color.BLACK);
		addMouseListener(this);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(this);
		new Timer( 80, this).start();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.getHeight() + " " + screenSize.getWidth());
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		//System.out.println("in paintComponent");
		super.paintComponent(g);
		//g.setColor(Color.ORANGE);
		
		
		grid(g);
		//spawn(g);
		
		
		if(starts == true)
		{
			display(g);
		}
		else if(starts == false)
		{
			userInputMarker(g);
		}
		
	}
	
	private void userInputMarker(Graphics g)
	{
		g.setColor(Color.GREEN);
		for(int x = 0; x < userInput.length; x++)
		{
			for(int y = 0; y < (yHeight); y++)
			{
				if(userInput[x][y] == 1)
				{
					
					g.fillRect(x * size, y * size, size, size);
				
				}
			}
		}
	}
	
	
	
	private void grid(Graphics g)
	{
		//System.out.println("in grid");
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
			//System.out.println("in spawn");
			for(int x = 0; x < life.length; x++)
			{
				for(int y = 0; y < (yHeight); y++)
				{
					if((int)(Math.random() * 5) == 0)
					{
						beforeLife[x][y] = 1;
					}
				}
			}
			starts = false;
		}
	}
	
	private void display(Graphics g)
	{
		g.setColor(Color.GREEN);
		
		//System.out.println("display");
		
		
		
			
		
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

	

	

	
	public void mouseClicked(MouseEvent e) {
		//System.out.println("in mouseClicked");
		if(starts == false)
		{
			int x =(e.getX() - (e.getX() % size)) / size;
		
			
			int y = (e.getY() - (e.getY() % size)) / size;
			System.out.println(x + ", " + y);
			
			userInput[x][y] = 1;
			
			mouseX = x;
			mouseY = y;
			
			repaint();
			
		}
		
		
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	public void keyTyped(KeyEvent e) {

		
		
	}



	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			System.out.println("enter");
			
			for(int x = 0; x < xWidth; x++ )
			{
					for(int y = 0; y < (yHeight); y++)
					{
						beforeLife[x][y] = userInput[x][y];
						System.out.println(beforeLife[x][y] + ", " + userInput[x][y]);
						repaint();
					}
			}
			
			
			starts = true;
		}
	}



	
	public void keyReleased(KeyEvent e) {
		
	}


}
