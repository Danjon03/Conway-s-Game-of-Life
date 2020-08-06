package gol;

import javax.swing.JFrame;

public class newJFrame extends JFrame
{
	public newJFrame()
	{
		add(new LifePanel());
		setSize(1920, 1080);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args)
	{
		new newJFrame();
	}
	
	
}
