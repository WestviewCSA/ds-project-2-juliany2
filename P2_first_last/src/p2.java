import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
	
	static Map grid;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		readCoordinates("test1.txt");
	}
	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();

			int rowIndex = 0;
			
			grid = new Map(numRows, numCols);
						
			// process the map
			while (scanner.hasNextLine() && rowIndex < numRows) {
				// grab a line (one row)
				String row = scanner.nextLine();
				
				if (row.length() > 0) {
					for (int i = 0; i < numCols && i < row.length(); i++) {
						char el = row.charAt(i);
						Tile obj = new Tile(rowIndex, i, el);
						grid.set(rowIndex, i, obj);
					}
				}
				
				
				rowIndex++;
			}
			
			grid.print();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static void readCoordinates(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			grid = new Map(numRows, numCols);
			
			
			
			while (scanner.hasNextLine()) {
				// grab a line
				String row = scanner.nextLine();
				
				if (row.length() > 0) {
					
					char el = row.charAt(0);
					int r = Character.getNumericValue(row.charAt(2));
					int c = Character.getNumericValue(row.charAt(4));
					int k = Character.getNumericValue(row.charAt(6));
					
					grid.set(r, c, new Tile(r, c, el));
				
				}
			}
			
			grid.print();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
