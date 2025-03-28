
public class Map {
	private Tile[][] map;
	int room;

	public Map(int r, int c, int room) {
		map = new Tile[r][c];
		this.room = room;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				set(i, j, new Tile(i, j, room, '.'));
			}
		}
	}
	
	public void set(int r, int c, Tile tile) {
		map[r][c] = tile;
	}
	
	public Tile get(int r, int c) {
		// return dummy tile if its out of bounds
		if (r < 0 || c < 0 || r >= map.length || c >= map[0].length) {
			return new Tile(r, c, room, '@');
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
