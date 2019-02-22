package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class MagicSquareTest {
	
	private MagicSquare mySquare;
	
	private void setupScenary1() {}
	
	private void setupScenary2() {
		mySquare = new MagicSquare(MagicSquare.NORTH, 5, MagicSquare.NORTHEAST, 0, 2);
	}
	
	private void setupScenary3() {
		mySquare = new MagicSquare(MagicSquare.EAST, 7, MagicSquare.SOUTHEAST, 3, 6);
	}
	
	private void setupScenary4() {
		mySquare = new MagicSquare(MagicSquare.SOUTH, 3, MagicSquare.SOUTHWEST, 2, 1);
	}
	
	private void setupScenary5() {
		mySquare = new MagicSquare(MagicSquare.WEST, 9, MagicSquare.NORTHWEST, 4, 0);
	}
	
	@Test
	void magicSquareTest() {
		setupScenary1();
		String direction = MagicSquare.SOUTH;
		int size = 3;
		String orientation = MagicSquare.SOUTHEAST;
		int startRow = 2;
		int startColumn = 1;
		MagicSquare testSquare = new MagicSquare(direction, size, orientation, startRow, startColumn);
		
		assertNotNull("The magic square matrix is null", testSquare.getSquare());
		assertTrue("The name direction is not assigned correctly", direction.equals(testSquare.getDirection()));
		assertTrue("The name size is not assigned correctly", size == testSquare.getSize());
		assertTrue("The name orientation is not assigned correctly", orientation.equals(testSquare.getOrientation()));
		assertTrue("The name start row is not assigned correctly", startRow == testSquare.getStartRow());
		assertTrue("The name start column is not assigned correctly", startColumn == testSquare.getStartColumn());
	}
	
	@Test
	void magicConstantTest1() {
		setupScenary2();
		long constant = mySquare.calculateMagicConstant();
		
		assertTrue("The magic constant is not correct", constant > 0 && constant > mySquare.getSize());
	}

	@Test
	void solveTest1() {
		setupScenary2();
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
	
	@Test
	void solveTest2() {
		setupScenary3();
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
	
	@Test
	void northEasternTest() {
		setupScenary2();
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
	
	@Test
	void southEasternTest() {
		setupScenary3();
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
	
	@Test
	void southWesternTest() {
		setupScenary4();
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
	
	@Test
	void northWestern() {
		setupScenary5();
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
	
}
