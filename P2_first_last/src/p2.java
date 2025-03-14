import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p2 {
	
	static Map grid[];
	
	static int numRows, numCols, numRooms;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		readCoordinates("test1.txt");
	}
	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			numRows = scanner.nextInt();
			numCols = scanner.nextInt();
			numRooms = scanner.nextInt();
			
			grid = new Map[numRooms];

			int rowIndex = 0;
			
			for (int room = 0; room < numRooms; room++) {
				grid[room] = new Map(numRows, numCols);
							
				// process the map
				while (scanner.hasNextLine() && rowIndex < numRows) {
					// grab a line (one row)
					String row = scanner.nextLine();
					
					if (row.length() > 0) {
						for (int i = 0; i < numCols && i < row.length(); i++) {
							char el = row.charAt(i);
							Tile obj = new Tile(rowIndex, i, el);
							grid[room].set(rowIndex, i, obj);
						}
					}
					
					
					rowIndex++;
				}
				
				grid[room].print();
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static void readCoordinates(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			numRows = scanner.nextInt();
			numCols = scanner.nextInt();
			numRooms = scanner.nextInt();
			
			grid = new Map[numRooms];
			
			for (int room = 0; room < numRooms; room++) {
				grid[room] = new Map(numRows, numCols);
			}
				
				
			while (scanner.hasNextLine()) {
				// grab a line
				String row = scanner.nextLine();
				
				if (row.length() > 0) {
					
					char el = row.charAt(0);
					int r = Character.getNumericValue(row.charAt(2));
					int c = Character.getNumericValue(row.charAt(4));
					int k = Character.getNumericValue(row.charAt(6));
					
					grid[k].set(r, c, new Tile(r, c, el));
				
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static void queueSolve() {
		for (int room = 0; room < numRooms; room++) {
			Queue<Tile> q = new LinkedList<>();
			
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					if (grid[room].get(i, j).getType() == 'W') {
						q.add(grid[room].get(i, j));
					}
				}
			}
			
			while (!q.isEmpty()) {
				Tile cur = q.remove();
			}
		}
	}
}
