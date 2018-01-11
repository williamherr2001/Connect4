import java.util.Scanner;

public class connect4 {
	static int t = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int ysize = 6, xsize = 7;
		char[][] b = new char[ysize][xsize];
		for(int i = 0; i<ysize;i++) {
			for(int j = 0; j<xsize; j++) {
				b[i][j] = '_';
			}
		}
		t=0;
		boolean tie = gameplay(b, t, in);
		if(tie)System.out.println("It's a tie. Will Herrington wins since he made the game.");
		else System.out.println("Player #"+(t%2+1)+" is the winner! Congratulations!");
		displayBoard(b, true);
	}
	
	
	public static void displayBoard(char[][] b, boolean win) {
		for(int row = 0; row<b.length; row++) {
			for(int col = 0; col<b[0].length;col++) {
				System.out.print(b[row][col]+" ");
			}
			System.out.println();
		}
		if(!win) System.out.println("1 2 3 4 5 6 7 ");
		return;
	}
	
	public static boolean gameplay(char[][] b, int t, Scanner in) {
		boolean tie;
		while(true) {
			displayBoard(b, false);
			System.out.println("Player #"+(t%2+1)+ " please make your move.");
			int move = in.nextInt()-1;
			boolean notFull = true;
			for(int i = b.length-1; i>=0; i--) {
				
				if(b[i][move] == '_') {
					if(t%2==0) {
						b[i][move] = 'X';
					} else if(t%2==1) {
						b[i][move] = 'O';
					}
					break;
				} else if(i==0) {
					notFull = false;
				}
				
			}
			if(!notFull) {
				System.out.println("That column is full. Please Choose another column.");
				gameplay(b, t, in);
			}
			if(checkWinStatus(b)) {
				tie = false;
				break;
			}
			if(t==41) {
				tie = true;
				break;
			}
			t++;
			connect4.t++;
		}
		return tie;
	}		
	
	
	public static boolean checkWinStatus(char[][] b) {
		// 69 possible win conditions.
		// 24 diagonal win conditions.
		// 24 horizontal win conditions.
		// 21 vertical win conditions.
		
		//horizontal win
		for(int row = 0; row<b.length;row++) {
			for(int four = 0; four<b[0].length-3; four++) {
				if(b[row][four]=='X' && b[row][four+1]=='X' && b[row][four+2]=='X' && b[row][four+3]=='X')return true;
				else if(b[row][four]=='O' && b[row][four+1]=='O' && b[row][four+2]=='O' && b[row][four+3]=='O')return true;
			}
		}
		
		//vertical win
		for(int col = 0; col<b.length;col++) {
			for(int four = 0; four<b[0].length-4; four++) {
				if(b[four][col]=='X' && b[four+1][col]=='X' && b[four+2][col]=='X' && b[four+3][col]=='X')return true;
				if(b[four][col]=='O' && b[four+1][col]=='O' && b[four+2][col]=='O' && b[four+3][col]=='O')return true;
			}
		}
		return false;
	}
}
