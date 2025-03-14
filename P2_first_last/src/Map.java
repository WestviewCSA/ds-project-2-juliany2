
public class Map {
	private Tile[][] map;

	public Map(int r, int c) {
		map = new Tile[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				set(i, j, new Tile(i, j, '.'));
			}
		}
	}
	
	public void set(int r, int c, Tile tile) {
		map[r][c] = tile;
	}
	
	public Tile get(int r, int c) {
		// return dummy tile if its out of bounds
		if (r < 0 || c < 0 || r > map.length || c > map[0].length) {
			return new Tile(-1, -1, 'e');
		}
		return map[r][c];
	}
	
	public void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].getType());
			}
			System.out.println();
		}
	}
}
