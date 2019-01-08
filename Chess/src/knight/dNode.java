package knight;

public class dNode implements Comparable<dNode> {
	
	int x, y, n, jumps, children;
	Coor coor;
	Coor[] moves;
	dNode parent;
	String path = "";
	
	public dNode(int x, int y, int n) {
		this.x = x;
		this.y = y;
		this.coor = new Coor(x, y);
		this.n = n;
		setMoves();
		this.jumps = 0;
		this.children = moves.length;
		this.path = coor.toString();
	}
	public dNode(int x, int y, int n, dNode parent) {
		this.x = x;
		this.y = y;
		this.coor = new Coor(x, y);
		this.n = n;
		setMoves();
		this.children = moves.length;
		this.parent = parent;
		this.jumps = parent.jumps + 1;
		this.path = (parent.coor.toString() + "->" + coor.toString()); 
	}
	
	public void setMoves(){
		Coor moves[] = new Coor[8];
		int mX = 0, mY = 0, i = 0;
		
		mX = x + 1;
		mY = y + 2;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x + 1;
		mY = y - 2;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x + 2;
		mY = y + 1;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x + 2;
		mY = y - 1;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x - 1;
		mY = y + 2;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x - 1;
		mY = y - 2;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x - 2;
		mY = y + 1;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}
		mX = x - 2;
		mY = y - 1;
		if(mX > 0 && mX < n+1 && mY > 0 && mY < n+1){
			moves[i] = new Coor(mX, mY);
			i++;
		}

		Coor moves2[] = new Coor[i];
		for(int j = 0; j < i; j++)
			moves2[j] = moves[j];
		
		this.moves = moves2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getJumps() {
		return jumps;
	}

	public void setJumps(int jumps) {
		this.jumps = jumps;
	}

	public Coor getCoor() {
		return coor;
	}

	public void setCoor(Coor coor) {
		this.coor = coor;
	}

	public dNode getParent() {
		return parent;
	}

	public void setParent(dNode parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Coor[] getMoves() {
		return moves;
	}
	@Override
	public int compareTo(dNode other) {
		if(getJumps()==other.getJumps())
			return 0;
		else if(getJumps()>other.getJumps())
			return 1;
		else
			return -1;
	}
	
	public String toString(){
		String a = ("[" + getJumps() + "]" + getCoor().toString());
		return a;
	}
	
	public boolean isMatch(dNode f){
		return (getCoor().isMatch(f.getCoor()));
	}
	
	public boolean isMatch(Coor f){
		return (getCoor().isMatch(f));
	}
	
}
