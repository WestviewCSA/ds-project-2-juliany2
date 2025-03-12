import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
	
	static Map grid;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		readMap("test1.txt");
	}
	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			grid = new Map(numRows, numCols);

			int rowIndex = 0;
			
			Tile[][] input = new Tile[numRows][numCols];
			
			// process the map
			while (scanner.hasNextLine() && rowIndex < numRows) {
				// grab a line (one row)
				String row = scanner.nextLine();
				
				if (row.length() > 0) {
					for (int i = 0; i < numCols && i < row.length(); i++) {
						char el = row.charAt(i);
						Tile obj = new Tile(rowIndex, i, el);
						input[rowIndex][i] = new Tile(rowIndex, i, el);
						grid.set(rowIndex, i, obj);
					}
				}
				
				
				rowIndex++;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static void readCoordinates(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
