import java.util.Random;


//method boolean -call it with the most recent move and tells if that piece was in a winning pattern
//initializes board
public class ConnectFourLogic {
	//Initialize 
	final int colNum=7;
	final int rowNum=6;
	final int bottomRow= rowNum - 1;
	final char[][] board;
	
	char[] playerSymbols;
	
	
	//Clears board at the beginning of the game AND at the end
	public void clearBoard(){
		
		//loop across rowNum and colNum
		for(int row=0;row < rowNum;row++) {
			for(int col=0;col < colNum;col++) {
				
				board[col][row]=' ';	
			}	
		}
		
	}
	
	//CONSTRUCTOR - Passes in the player's choice of char
	public ConnectFourLogic(char one, char two) {
		playerSymbols = new char[] {one, two};
		
		board = new char[colNum][rowNum];
		clearBoard();
		
	}
	
	public void drawBoard() {
		//loop across rowNum and colNum
		for(int row=0;row < rowNum;row++) {
			for(int col=0;col < colNum;col++) {
				
				System.out.print(board[col][row]);
			}
			System.out.println();
		}
		System.out.println("0123456");
	}
	
	private boolean dropPiece(int column, int player) {
		for (int row = rowNum - 1; row >= 0; --row) {
			if (board[column][row]==' '){
				board[column][row] = playerSymbols[player];
	            return true;
			}
		}
		
		return false;
	}
	
	public boolean playerTurn(int column) {
		return dropPiece(column, 0);
	}
	
	//cpu makes move.  returns if cpu won 
	public boolean cpuTurn() {
		Random random = new Random();
		int column = random.nextInt(7);
		
		dropPiece(column, 1);
		return didWin(column);
	}
	
	//check the top row
	public boolean isBoardFull() {
		int columnsFull = 0;
		
		for (int column = 0; column < colNum; ++column) {
			if(board[column][0] != ' ') {
				++columnsFull;
			}
		}
		return (columnsFull == 7);
		
	}
	
	//vertically, diagonal or horizontal
	public boolean didWin(int col) {
		//start at the top of the board and "fall" downward until you find a non-air piece
		int row = 0;
		for (; row < rowNum; ++row) {
			if (board[col][row] != ' ') {
				break;
			}
		}
		
		//System.out.printf("didWin() row: %d col %d\n", row, col);
		
		char piece = board[col][row];
		
		//row and col now contain the location of the most recent piece played=true
		
		//test if a vertical pattern is met
		if (row <= rowNum - 4
				&& board[col][row + 1] == piece
				&& board[col][row + 2] == piece
				&& board[col][row + 3] == piece) {
			//vertical pattern found
			System.out.println("Vertical Match Found.");
			return true;
		}
		
		//horizontal win
		
		// '/' win
		int matches = 1;
		
		//loop down and to the left
		for(int i = 1; i < rowNum - row; i++) {
			int x = col - i;
			int y = row + i;
			
			if (x < 0 || x >= colNum) {
				break;
			}
			
			if (board[x][y] != piece) {
				break;
			}
			
			++matches;
		}
		
		//Diagonal: loop up and to the right
		for(int i = 1; i < row; i++) {
			int x = col + i;
			int y = row - i;
			
			if (x < 0 || x >= colNum) {
				break;
			}
			
			if (board[x][y] != piece) {
				break;
			}
			
			++matches;
		}
		
		if (matches >= 4) {
			System.out.println("'/' Pattern found");
			return true;
		}
			

		// '\' Diagonal win- top left to bottom right pattern
		
	
		
		return false;
	}
}