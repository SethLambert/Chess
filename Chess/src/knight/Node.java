package knight;

import knight.Coor;

public class Node {

		int x;//column
	    int y;//row
	    int n;//board size
	    String loc = "";
	    String path = "";
	    boolean visited = false;
		Coor[] moves;
	    Node parent;
	    
	    Node(int x, int y, int n, Node parent) {
	        this.x = x;
	        this.y = y;
	        this.n = n;
	        this.parent = parent;
	        setMoves();
	        this.loc = ("(" + x + ", " + y + ")");
	        this.path = (parent.getPath() + "->" + loc);
	    }   

		public Node(int x, int y, int n) {
	        this.x = x;
	        this.y = y;
	        this.n = n;
	        setMoves();
	        this.loc = ("(" + x + ", " + y + ")");
	        this.path = loc;
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
		
		public boolean isVisited(){
			return visited;
		}
		
		public void setVisited(boolean w){
			this.visited = w;
		}
		
	    public Coor[] getMoves() {
			return moves;
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
		
		void printMoves(){
			System.out.print("Moves from (" + x + ", " + y + "): " );
			for(int i = 0; i < moves.length; i++){
				System.out.print("(" + moves[i].x + ", " + moves[i].y + ") " );
			}
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}
