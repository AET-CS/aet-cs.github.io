public class MagicSquare {

	private int[][] mySquare;

	
	/**
	 * Makes mySquare a copy of input matrix
	 */
	public MagicSquare(int[][] matrix) {
		mySquare = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				mySquare[i][j] = matrix[i][j];
			}
		}
	}
	
	/**
	 * Display mySquare on screen
	 */
	public void writeMatrix() {
		for (int i = 0; i < mySquare.length; i++) {
			for (int j = 0; j < mySquare[0].length; j++) {
				System.out.print(mySquare[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * @param row a row in in the matrix
	 * Precondition: 0 <= row < SIZE
	 * @return the sum of the elements in row
	 */
	public int sumRow(int row) {
		return 0;
	}
	
	
	/**
	 * @param col a column in the matrix
	 * Precondition: 0 <= col < SIZE
	 * @return the sum of elements in col
	 */
	public int sumCol(int col) {
		return 0;
	}
	
	/**
	 * @return the sum of elements in the major diagonal
	 */
	public int sumMajorDiag() {
		return 0;
	}
	
	/**
	 * @return the sum of the elements in the minor diagonal
	 */
	public int sumMinorDiag()
	{
		return 0;
	}
	
	/**
	 * Precondition: mySquare is a square matrix of integers,
	 * 				 which may or may not be a magic square.
	 * @return true if mySquare is a magic square, false otherwise
	 */
	public boolean isMagic()
	{
		return false;
	}
	
}