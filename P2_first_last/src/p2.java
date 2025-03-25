import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class p2 {
	
	// global variables for direction
	static int dirc[] = {0, 1, 0, -1};
	static int dirr[] = {1, 0, -1, 0};
	
	// stores the grid in an array of Maps
	static Map grid[];
	static LinkedList<Tile> path;
	
	// dimensions
	static int numRows, numCols, numRooms;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		readMap("test4.txt");
		queueSolve();
		
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
						rowIndex++;
					}
				}
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
			boolean visited[][] = new boolean[numRows][numCols];
			int distance[][] = new int[numRows][numCols];

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					distance[i][j] = -1;
					if (grid[room].get(i, j).getType() == 'W') {
						q.add(grid[room].get(i, j));
						visited[i][j] = true;
						distance[i][j] = 0;
					}
				}
			}

			// get shortest distance to each cell
			while (!q.isEmpty()) {
				Tile cur = q.remove();
				
				int r = cur.getRow();
				int c = cur.getCol();
				
				// try to go in all 4 directions
				for (int dir = 0; dir < 4; dir++) {
					int r0 = r + dirc[dir];
					int c0 = c + dirr[dir];
					
					// check if its a valid tile and empty
					if (grid[room].get(r0, c0).getType() != '@') {
						if (!visited[r0][c0]) {
							visited[r0][c0] = true;
							distance[r0][c0] = distance[r][c] + 1;
							q.add(grid[room].get(r0, c0));
						}
					}
				}
			}
			
			int er = -1, ec = -1;
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					char type = grid[room].get(i, j).getType();
					if (visited[i][j] && type == '$' || type == '|') {
						er = i;
						ec = j;
					}
				}
			}
			
			if (er == -1 && ec == -1) {
				System.out.println("The Wolverine Store is closed.");
				return;
			}
			
			path = new LinkedList<Tile>();
			while (distance[er][ec] != 0) {
				for (int dir = 0; dir < 4; dir++) {
					int r = er + dirc[dir];
					int c = ec + dirr[dir];
					
					if (r < 0 || c < 0 || r >= numRows || c >= numCols) {
						continue;
					}
					
					if (visited[r][c] && distance[r][c] == distance[er][ec] - 1) {
						if (grid[room].get(r, c).getType() == '.') {
							grid[room].get(r, c).setType('+');
							path.add(0, grid[room].get(r, c));
						}
						er = r;
						ec = c;
						break;
					}
				}
			}
			grid[room].print();
		}
	}
	
	public static void stackSolve() {
		for (int room = 0; room < numRooms; room++) {
			Stack<Tile> q = new Stack<>();
			boolean visited[][] = new boolean[numRows][numCols];
			int distance[][] = new int[numRows][numCols];

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					distance[i][j] = -1;
					if (grid[room].get(i, j).getType() == 'W') {
						q.add(grid[room].get(i, j));
						visited[i][j] = true;
						distance[i][j] = 0;
					}
				}
			}

			// get shortest distance to each cell
			while (!q.isEmpty()) {
				Tile cur = q.pop();
				
				int r = cur.getRow();
				int c = cur.getCol();
				
				// try to go in all 4 directions
				for (int dir = 0; dir < 4; dir++) {
					int r0 = r + dirc[dir];
					int c0 = c + dirr[dir];
					
					// check if its a valid tile and empty
					if (grid[room].get(r0, c0).getType() != '@') {
						if (!visited[r0][c0]) {
							visited[r0][c0] = true;
							distance[r0][c0] = distance[r][c] + 1;
							q.add(grid[room].get(r0, c0));
						}
					}
				}
			}
			
			int er = -1, ec = -1;
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numCols; j++) {
					char type = grid[room].get(i, j).getType();
					if (type == '$' || type == '|') {
						er = i;
						ec = j;
					}
				}
			}
			
			if (er == -1 && ec == -1) {
				System.out.println("The Wolverine Store is closed.");
				return;
			}
			
			path = new LinkedList<Tile>();
			while (distance[er][ec] != 0) {
				for (int dir = 0; dir < 4; dir++) {
					int r = er + dirc[dir];
					int c = ec + dirr[dir];
					
					if (r < 0 || c < 0 || r >= numRows || c >= numCols) {
						continue;
					}
					
					if (visited[r][c] && distance[r][c] == distance[er][ec] - 1) {
						if (grid[room].get(r, c).getType() == '.') {
							grid[room].get(r, c).setType('+');
							path.add(0, grid[room].get(r, c));
						}
						er = r;
						ec = c;
						break;
					}
				}
			}
			grid[room].print();
		}
	}
	
	public void printMap() {
		for (int room = 0; room < numRooms; room++) {
			grid[room].print();
		}
	}
	
	public void printCoordinates() {
		for (Tile t : path) {
			System.out.println("+ " + t.getRow() + " " + t.getCol() + " " + room);
		}
	}
}
