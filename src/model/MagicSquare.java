package model;

/**
 * <b> Laboratorio unidad 1 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class MagicSquare {
	
	/**
	* The orientation in which the algorithm is going to work.
	*/
	public static final String NORTHEAST = "Northeast";
	
	/**
	* The orientation in which the algorithm is going to work.
	*/ 
	public static final String NORTHWEST = "Northwest";
	
	/**
	* The orientation in which the algorithm is going to work.
	*/ 
	public static final String SOUTHEAST = "Southeast";
	
	/**
	* The orientation in which the algorithm is going to work.
	*/
	public static final String SOUTHWEST = "Southwest";
	
	/**
	* The starting position of the algorithm, chosen by the user.
	*/
	public static final String NORTH = "North";
	
	/**
	* The starting position of the algorithm, chosen by the user.
	*/
	public static final String SOUTH = "South";
	
	/**
	* The starting position of the algorithm, chosen by the user.
	*/
	public static final String EAST = "East";
	
	/**
	* The starting position of the algorithm, chosen by the user.
	*/
	public static final String WEST = "West";
	
	/**
	* The starting position of the algorithm.
	*/
	private String direction;
	
	/**
	* The dimensions of the magic square that the user wants to generate(only odd sizes are allowed).
	*/
	private int size;
	
	/**
	* The magic square which is displayed to the user once all the configurations are selected.
	*/
	private int[][] square;
	
	/**
	* The starting position of the algorithm, chosen by the user.
	*/
	private String orientation;
	
	/**
	* The row of the magic square in where the algorithm is going to begin at.
	*/
	private int startRow;
	
	/**
	* The column of the magic square in where the algorithm is going to begin at.
	*/
	private int startColumn;
	
	/**
	* Initializes a new MagicSquare.
	* @param direction The starting position of the algorithm.
	* @param size The size of the magic square.
	*/
	public MagicSquare(String direction, int size, String orientation, int startRow, int startColumn) {
		this.direction = direction;
		this.size = size;
		this.orientation = orientation;
		this.startRow = startRow;
		this.startColumn = startColumn;
		
		square = new int[size][size];
	}

	/**
	* This function calculates the magic constant of the magic square depending of the size.
	* @return A long number representing the magic constant.
	*/
	public long calculateMagicConstant() {
		long value = (((long) Math.pow(this.size, 2) + 1) * this.size) / 2;
		return value;
	}
	
	/**
	* This function calculates the positions of the numbers in the gridpane that have to be highlighted with another color.<br>
	* Pre: The magic square matrix has been initialized.
	* @param m The row that has to be highlighted.
	* @param n The column that has to be highlighted.
	* @return An array of integer numbers representing the indexes of the gridpane that have to be highlighted.
	*/
	public int[] calculatePos(int m, int n) {
		int[] a = new int[this.size + this.size + 2];
		int counter = 0, value = 0;
		for(int i = m; i < m+1; i++) {
			for(int j = 0; j < this.size; j++) {
				value = ((i+1) * size) - size;
				value += j;
				a[counter] = value;
				counter++;
			}
		}
		
		for(int i = 0; i < this.size; i++) {
			for(int j = n; j < n+1; j++) {
				value = ((i+1) * size) - size;
				value += j;
				a[counter] = value;
				counter++;
			}
		}
		a[a.length-2] = m; a[a.length-1] = n;
		return a;
	}
	
	/**
	* This function calculates the value of the start row and the start column depending on the starting position.<br>
	* Post: The startRow and startColumn attributes have been initialized.
	*/
	public void calculateStarts() {
		if(direction.equals(NORTH)) {
			this.startRow = 0;
			this.startColumn = this.size / 2;
		}
		else if(direction.equals(EAST)) {
			this.startRow = this.size / 2;
			this.startColumn = this.size - 1;
		}
		else if(direction.equals(SOUTH)) {
			this.startRow = this.size - 1;
			this.startColumn = this.size / 2;
		}
		else {
			this.startRow = this.size / 2;
			this.startColumn = 0;
		}
	}
	
	/**
	* This function fills the magic square depending on the orientation and starting position of the algorithm.
	* @return An array of integers representing the values of the gridpane that have to be filled with their respective number.<br>
	* Post: The magic square matrix has been filled.
	*/
	public int[] solve() {
	
		int[] values = new int[size*size];
		calculateStarts();
		square[startRow][startColumn] = 1;
		int value = ((startRow+1) * size) - size;
		value += startColumn;
		values[0] = value;
		
		if(orientation.equals(NORTHEAST))
			northEastern(values);
		else if(orientation.equals(NORTHWEST))
			northWestern(values);
		else if(orientation.equals(SOUTHEAST))
			southEastern(values);
		else
			southWestern(values);
		return values;
	}
	
	/**
	* Function which fills the algorithm in the north eastern orientation.
	* @param values The array of integers that is going to be filled to have the values of the gridpane.
	*/
	public void northEastern(int[] values) {
	
		int i = 1;
		int posx = startRow;
		int posy = startColumn;
		int tempx = 0, tempy = 0;
		while(i++ < values.length) {
			tempx = posx - 1;
			tempy = posy + 1;
			if(tempx < 0) 
				tempx = posx + (this.size - 1);
			if(tempy > this.size - 1) 
				tempy = 0;
			if(this.square[tempx][tempy] == 0) {
				posx = tempx;
				posy = tempy;
			}
			else {
				if(direction.equals(NORTH)) 
					posx++;
				else
					posy--;
			}
			this.square[posx][posy] = i;
			int value = ((posx+1) * size) - size;
			value += posy;
			values[i-1] = value;
		}
	}
	
	/**
	* Function which fills the algorithm in the north western orientation.
	* @param values The array of integers that is going to be filled to have the values of the gridpane.
	*/
	public void northWestern(int[] values) {
		
		int i = 1;
		int posx = startRow;
		int posy = startColumn;
		int tempx = 0, tempy = 0;
		while(i++ < values.length) {
			tempx = posx - 1;
			tempy = posy - 1;
			if(tempx < 0) 
				tempx = posx + (this.size - 1);
			if(tempy < 0) 
				tempy = posy + (this.size - 1);
			if(this.square[tempx][tempy] == 0) {
				posx = tempx;
				posy = tempy;
			}
			else {
				if(direction.equals(NORTH)) 
					posx++;
				else
					posy++;
			}
			this.square[posx][posy] = i;
			int value = ((posx+1) * size) - size;
			value += posy;
			values[i-1] = value;
		}
	}
	
	/**
	* Function which fills the algorithm in the south eastern orientation.
	* @param values The array of integers that is going to be filled to have the values of the gridpane.
	*/
	public void southEastern(int[] values) {
		
		int i = 1;
		int posx = startRow;
		int posy = startColumn;
		int tempx = 0, tempy = 0;
		while(i++ < values.length) {
			tempx = posx + 1;
			tempy = posy + 1;
			if(tempx > this.size - 1)
				tempx = 0;
			if(tempy > this.size - 1)
				tempy = 0;
			if(this.square[tempx][tempy] == 0) {
				posx = tempx;
				posy = tempy;
			}
			else {
				if(direction.equals(SOUTH)) 
					posx--;
				else
					posy--;
			}
			this.square[posx][posy] = i;
			int value = ((posx+1) * size) - size;
			value += posy;
			values[i-1] = value;
		}
	}
	
	/**
	* Function which fills the algorithm in the south western orientation.
	* @param values The array of integers that is going to be filled to have the values of the gridpane.
	*/
	public void southWestern(int[] values) {
		
		int i = 1;
		int posx = startRow;
		int posy = startColumn;
		int tempx = 0, tempy = 0;
		while(i++ < values.length) {
			tempx = posx + 1;
			tempy = posy - 1;
			if(tempx > this.size - 1)
				tempx = 0;
			if(tempy < 0) 
				tempy = posy + (this.size - 1);
			if(this.square[tempx][tempy] == 0) {
				posx = tempx;
				posy = tempy;
			}
			else {
				if(direction.equals(WEST)) 
					posy++;
				else
					posx--;
			}
			this.square[posx][posy] = i;
			int value = ((posx+1) * size) - size;
			value += posy;
			values[i-1] = value;
		}
	}

	/**
	* This function modifies the magic square matrix.<br>
	* Post: There is a new magic square matrix now.
	* @param square The new magic square matrix.
	*/
	public void setSquare(int[][] square) {
		this.square = square;
	}

	public int[][] getSquare() {
		return this.square;
	}
		
	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @return the startColumn
	 */
	public int getStartColumn() {
		return startColumn;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @return the orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	* This function modifies the starting position of the algorithm.<br>
	* Post: The algorithm now has a new starting position.
	* @param direction The new starting position.
	*/
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	* This function obtains the size of the magic square.
	* @return An integer representing the dimensions of the magic square.
	*/
	public int getSize() {
		return size;
	}

	/**
	* This function modifies the size of the magic square matrix.<br>
	* Post: The magic square has a new size now.
	* @param size The new size of the magic square.
	*/
	public void setSize(int size) {
		this.size = size;
	}

	/**
	* This function modifies the orientation of the fill algorithm.<br>
	* Post: There is a new orientation for the fill algorithm now.
	* @param orientation The new orientation of the algorithm.
	*/
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}	
}
