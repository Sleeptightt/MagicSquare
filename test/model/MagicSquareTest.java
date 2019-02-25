package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import customExceptions.OutOfRangeSizeException;

class MagicSquareTest {
	
	
	/**
	 * The association between the magic square and it's test.
	 */
	private MagicSquare mySquare;
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery1() {}
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery2() {
		mySquare = new MagicSquare(MagicSquare.NORTH, 5, MagicSquare.NORTHEAST, 0, 2);
	}
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery3() {
		mySquare = new MagicSquare(MagicSquare.EAST, 7, MagicSquare.SOUTHEAST, 3, 6);
	}
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery4() {
		mySquare = new MagicSquare(MagicSquare.SOUTH, 3, MagicSquare.SOUTHWEST, 2, 1);
	}
	
	/**
	 * This function initializes a scenery.
	 */
	private void setupScenery5() {
		mySquare = new MagicSquare(MagicSquare.WEST, 9, MagicSquare.NORTHWEST, 4, 0);
	}
	
	/**
	 * This function tests whether or not the Magic Square constructor functions correctly.
	 */
	@Test
	void magicSquareTest() {
		setupScenery1();
		String direction = MagicSquare.SOUTH;
		int size = 3;
		String orientation = MagicSquare.SOUTHEAST;
		int startRow = 2;
		int startColumn = 1;
		MagicSquare testSquare = new MagicSquare(direction, size, orientation, startRow, startColumn);
		
		assertNotNull("The magic square matrix is null", testSquare.getSquare());
		assertTrue("The direction is not assigned correctly", direction.equals(testSquare.getDirection()));
		assertTrue("The size is not assigned correctly", size == testSquare.getSize());
		assertTrue("The orientation is not assigned correctly", orientation.equals(testSquare.getOrientation()));
		assertTrue("The start row is not assigned correctly", startRow == testSquare.getStartRow());
		assertTrue("The start column is not assigned correctly", startColumn == testSquare.getStartColumn());
	}
	
	/**
	 * This function tests whether or not the calculate magic constant function functions correctly.
	 */
	@Test
	void magicConstantTest1() {
		setupScenery2();
		long constant = mySquare.calculateMagicConstant();
		assertTrue("The magic constant is not correct", constant > 0 && constant > mySquare.getSize());
	}

	/**
	 * This function tests whether or not the calculate magic constant function functions correctly.
	 */
	@Test
	void magicConstantTest2() {
		setupScenery3();
		long constant = mySquare.calculateMagicConstant();
		assertTrue("The magic constant is not correct", constant > 0 && constant > mySquare.getSize());
	}
	
	/**
	 * This function tests whether or not the calculate magic constant function functions correctly.
	 */
	@Test
	void magicConstantTest3() {
		setupScenery4();
		long constant = mySquare.calculateMagicConstant();
		assertTrue("The magic constant is not correct", constant > 0 && constant > mySquare.getSize());
	}
	
	/**
	 * This function tests whether or not the calculate magic constant function functions correctly.
	 */
	@Test
	void magicConstantTest4() {
		setupScenery5();
		long constant = mySquare.calculateMagicConstant();
		assertTrue("The magic constant is not correct", constant > 0 && constant > mySquare.getSize());
	}
	
	/**
	 * This function tests whether or not all the magic square's numbers are different from each other.
	 */
	@Test
	void solveTest1() {
		setupScenery2();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				set.add(square[i][j]);
			}
		}
		
		assertTrue("There are repeated numbers", set.size() == (square.length * square.length));
	}
	
	/**
	 * This function tests whether or not all the magic square's numbers are different from each other.
	 */
	@Test
	void solveTest2() {
		setupScenery3();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				set.add(square[i][j]);
			}
		}
		
		assertTrue("There are repeated numbers", set.size() == (square.length * square.length));
	}
	
	/**
	 * This function tests whether or not all the magic square's numbers are different from each other.
	 */
	@Test
	void solveTest3() {
		setupScenery4();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				set.add(square[i][j]);
			}
		}
		
		assertTrue("There are repeated numbers", set.size() == (square.length * square.length));
	}
	
	/**
	 * This function tests whether or not all the magic square's numbers are different from each other.
	 */
	@Test
	void solveTest4() {
		setupScenery5();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				set.add(square[i][j]);
			}
		}
		
		assertTrue("There are repeated numbers", set.size() == (square.length * square.length));
	}
	
	/**
	 * This function tests whether or not the magic square gets solved correctly in the specified direction.
	 */
	@Test
	void northEasternTest() {
		setupScenery2();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		int[] rows = new int[square.length];
		int[] columns = new int[square.length];
		int[] diag = new int[2];
		int pointer = square.length-1;
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				rows[i] += square[i][j];
				columns[j] += square[i][j];
				if(i == j)
					diag[0] += square[i][j];
				if(j == pointer)
					diag[1] += square[i][j];
			}
			pointer--;
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(diag[0]); set.add(diag[1]);
		for(int i = 0; i < square.length; i++) {
			set.add(rows[i]); set.add(columns[i]);
		}	
		assertTrue("There are repeated numbers", set.size() == 1);
	}
	
	/**
	 * This function tests whether or not the magic square gets solved correctly in the specified direction.
	 */
	@Test
	void southEasternTest() {
		setupScenery3();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		int[] rows = new int[square.length];
		int[] columns = new int[square.length];
		int[] diag = new int[2];
		int pointer = square.length-1;
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				rows[i] += square[i][j];
				columns[j] += square[i][j];
				if(i == j)
					diag[0] += square[i][j];
				if(j == pointer)
					diag[1] += square[i][j];
			}
			pointer--;
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(diag[0]); set.add(diag[1]);
		for(int i = 0; i < square.length; i++) {
			set.add(rows[i]); set.add(columns[i]);
		}	
		assertTrue("There are repeated numbers", set.size() == 1);
	}
	
	/**
	 * This function tests whether or not the magic square gets solved correctly in the specified direction.
	 */
	@Test
	void southWesternTest() {
		setupScenery4();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		int[] rows = new int[square.length];
		int[] columns = new int[square.length];
		int[] diag = new int[2];
		int pointer = square.length-1;
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				rows[i] += square[i][j];
				columns[j] += square[i][j];
				if(i == j)
					diag[0] += square[i][j];
				if(j == pointer)
					diag[1] += square[i][j];
			}
			pointer--;
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(diag[0]); set.add(diag[1]);
		for(int i = 0; i < square.length; i++) {
			set.add(rows[i]); set.add(columns[i]);
		}	
		assertTrue("There are repeated numbers", set.size() == 1);
	}
	
	/**
	 * This function tests whether or not the magic square gets solved correctly in the specified direction.
	 */
	@Test
	void northWestern() {
		setupScenery5();
		mySquare.solve();
		int[][] square = mySquare.getSquare();
		int[] rows = new int[square.length];
		int[] columns = new int[square.length];
		int[] diag = new int[2];
		int pointer = square.length-1;
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				rows[i] += square[i][j];
				columns[j] += square[i][j];
				if(i == j)
					diag[0] += square[i][j];
				if(j == pointer)
					diag[1] += square[i][j];
			}
			pointer--;
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(diag[0]); set.add(diag[1]);
		for(int i = 0; i < square.length; i++) {
			set.add(rows[i]); set.add(columns[i]);
		}	
		assertTrue("There are repeated numbers", set.size() == 1);
	}
	
	/**
	 * This function tests whether or not an exception occurs when it's supposed to.
	 */
	@Test
	void setSizeTest1() {
		setupScenery2();
		int s = -1;
		boolean threw = false;
		try {
			mySquare.setSize(s);
		}catch(OutOfRangeSizeException e) {
			threw = true;
		}
		assertTrue("The exception was not thrown", threw);
		assertFalse("The size of the magic square was modified", mySquare.getSize() == s);
	}
	
	/**
	 * This function tests whether or not an exception occurs when it's supposed to.
	 */
	@Test
	void setSizeTest2() {
		setupScenery3();
		int s = 1001;
		boolean threw = false;
		try {
			mySquare.setSize(s);
		}catch(OutOfRangeSizeException e) {
			threw = true;
		}
		assertTrue("The exception was not thrown", threw);
		assertFalse("The size of the magic square was modified", mySquare.getSize() == s);
	}
	
	/**
	 * This function tests whether or not an exception occurs when it's supposed to.
	 */
	@Test
	void setSizeTest3() {
		setupScenery4();
		int s = 6;
		boolean threw = false;
		try {
			mySquare.setSize(s);
		}catch(OutOfRangeSizeException e) {
			threw = true;
		}
		assertTrue("The exception was not thrown", threw);
		assertFalse("The size of the magic square was modified", mySquare.getSize() == s);
	}
	
	/**
	 * This function tests whether or not an exception occurs when it's supposed to.
	 */
	@Test
	void setSizeTest4() {
		setupScenery5();
		int s = 15;
		boolean threw = false;
		try {
			mySquare.setSize(s);
		}catch(OutOfRangeSizeException e) {
			threw = true;
		}
		
		assertFalse("The exception was thrown", threw);
		assertTrue("The size of the magic square was not modified", mySquare.getSize() == s);
	}
}
