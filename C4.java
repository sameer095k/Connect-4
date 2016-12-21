import java.util.*;
public class C4 
{

	char board[][] = new char[7][6];
	int count[] = new int[7];
	int turn = 0;
	int marker[] = new int[7];
	Scanner scan = new Scanner(System.in);
	
	public C4()
	{
		instantiate();
	}
	
	public void instantiate()
	{
		for(int i = 0; i < 7; i++)
		{
			count[i] = 0;
			marker[i] = i;
			for(int j = 0; j < 6; j++)
				board[i][j] = '-';
		}	
	}
	
	public void printBoard()
	{
		for(int j = 5; j  > -1; j--)
		{
			for(int i = 0; i < 7; i++)
				System.out.print("  "+ board[i][j] + "");
			System.out.println();
		}
		for(int i = 0; i < 7; i++)
			System.out.print("  " + marker[i] + "");
		System.out.println();
	}
	
	public int play(int x)
	{
		while(x > 6 || x < 0)
		{
			System.out.println("Not Valid Entry");
			System.out.print("Enter a Different Column #");
			x = scan.nextInt();
		}
		
		while(count[x] > 5)
		{
			System.out.println("Column " + x + " is full");
			System.out.print("Enter a Different Column #");
			x = scan.nextInt();
		}
		
		int t = turn%2;
		char c;
		
		if(t==0)
			c = 'X';
		else
			c = 'O';
		

		board[x][count[x]] = c;
		turn++;
		return x;
	}
	
	public boolean full()
	{
		for(int i = 0; i < 7; i++)
			if(count[i]!=5)
				return false;
		return true;
	}
	
	public boolean checkWinner(int x)
	{
		char c = board[x][count[x]];
		int y = count[x]++;
		int count = 1;
		//Check Horizontal
		//Left
		for(int i = 1; i <= x; i++)
		{
			if(board[x][y] == board[x-i][y])
				count++;
			else
				break;
		}
		//Right
		for(int i = 1; i <= 6 - x; i++)
		{
			if(board[x][y] == board[x+i][y])
				count++;
			else
				break;	
		}
		
		if(count == 4)
		{
			winner(c);
			return true;
		}
		
		//Check Vertical
		count = 1; 
		
		//South
		for(int i = 1; i <= y; i++)
			if(board[x][y] == board[x][y-i])
				count++;
			else
				break;
		
		//North
		for(int i = 1; i <= 5 - y; i++)
			if(board[x][y] == board[x][y+i])
				count++;
			else
				break;	
		
		if(count == 4)
		{
			winner(c);
			return true;
		}
		
		//Check Left Horizontal
		count = 1;
		
		//Left and South
		for(int i = 1; i <= x && i <=y; i++)
			if(board[x][y] == board[x-i][y-i])
				count++;
			else
				break;
		
		//Right and North
		for(int i = 1; i <= 6 - x && i <= 5-y; i++)
			if(board[x][y] == board[x+i][y+i])
				count++;
			else
				break;	
		
		if(count == 4)
		{
			winner(c);
			return true;
		}
		
		//Check Right Horizontal
		
		count = 1;
		
		//Left and North
		for(int i = 1; i <= x && i <= 5-y; i++)
			if(board[x][y] == board[x-i][y+i])
				count++;
			else
				break;
		
		//Right and South
		for(int i = 1; i <= 6 - x && i <= y; i++)
			if(board[x][y] == board[x+i][y-i])
				count++;
			else
				break;	
		
		if(count == 4)
		{
			winner(c);
			return true;
		}
		return false;
	}
	
	public void winner(char c)
	{
		String s;
		if(c == 'X')
			s = "RED";
		else 
			s = "Yellow";
		System.out.println("WINNER IS " + s);
	}
	
	/*public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		C4 c = new C4();
		c.printBoard();
		int x;
		do{
			System.out.print("Please Enter a Column #: ");
			x = scan.nextInt();
			x = c.play(x);
			c.printBoard();
			if(c.checkWinner(x))
				break;
		}while(!c.full());
		if(c.full())
			System.out.println("TIE GAME, BOARD FULL");
		System.out.println("Game Over");
		System.out.println("Programmed By: Sameer Khan");
		

	}*/

}
