import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class C4GUI extends JFrame
implements ActionListener  
{

	JPanel mainPanel = new JPanel();
	JPanel miniPanel[][] = new JPanel[7][6]; 
	JLabel gameLabel = new JLabel("     Tic Tac Toe      ");
	JLabel label[][] = new JLabel[7][6];
	JButton playButtons[] = new JButton[7];
	ImageIcon a = new ImageIcon(new ImageIcon("C40").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	ImageIcon b = new ImageIcon(new ImageIcon("C41").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	ImageIcon c = new ImageIcon(new ImageIcon("C42").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
	int count[] = new int[7];
	int turn;
	C4 c4;
	
	//Declare constants
	final int SIZE_WIDTH_INTEGER = 900;
	final int SIZE_HEIGHT_INTEGER = 900;
	//final int SIZE_BUTTON = 300;
	
	public C4GUI()
	{
		instantiate();
		addComponents();
		add(mainPanel);
		repaint();
		revalidate();
		this.setSize(SIZE_WIDTH_INTEGER, SIZE_HEIGHT_INTEGER);
		this.setVisible(true);
	}
	
	public void instantiate()
	{
		turn = 0;
		c4 = new C4();
		mainPanel.setLayout(new GridLayout(7,7));
		mainPanel.setBackground(Color.WHITE);
		for(int i = 0; i < 7; i++)
		{
			count[i] = 0;
			playButtons[i] = new JButton();
			playButtons[i].addActionListener(this);
			for(int j = 0; j < 6; j++)
			{
				miniPanel[i][j] = new JPanel();
				miniPanel[i][j].setLayout(new GridLayout(1,1));
				miniPanel[i][j].setBackground(Color.gray);
				label[i][j] = new JLabel();
				label[i][j].setIcon(a);
				miniPanel[i][j].add(label[i][j]);
			}
		}
	}
	
	public void addComponents()
	{
		for(int j = 5; j  > -1; j--)
			for(int i = 0; i < 7; i++)
				mainPanel.add(miniPanel[i][j]);
		
		for(int i = 0; i < 7; i++)
			mainPanel.add(playButtons[i]);
		repaint();
		revalidate();
	}
	
	public void	actionPerformed(ActionEvent e)
	{
		Object sourceObject = e.getSource();
		ImageIcon p = new ImageIcon();
		if(turn%2 == 0)
			p = b;
		else
			p = c;
		int num = -1;
		for(int i = 0; i < 7; i++)
			if(sourceObject == playButtons[i])
				num = i;
		if(count[num] <= 5)
		{
			c4.play(num);
			label[num][count[num]++].setIcon(p);
			repaint();
			revalidate();	
			if(c4.checkWinner(num))
			{
				for(int i = 0; i < 7; i++)
					playButtons[i].setEnabled(false);
				System.out.println("GAME OVER");
				System.out.println("Programmed by: Sameer Khan");
			}
			turn++;
		}
		else
			playButtons[num].setEnabled(false);
		if(c4.full())
		{
			for(int i = 0; i < 7; i++)
				playButtons[i].setEnabled(false);
			System.out.println("GAME OVER");
			System.out.println("Programmed by: Sameer Khan");

		}
		
	}
	
	
	public static void main(String[] args) 
	{
		C4GUI c = new C4GUI();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
