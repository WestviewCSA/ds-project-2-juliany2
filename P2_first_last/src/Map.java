
public class Map {
	private Tile[][] map;

	public Map(int r, int c) {
		map = new Tile[r][c];
	}
	
	public void set(int r, int c, Tile tile) {
		map[r][c] = tile;
	}
}
