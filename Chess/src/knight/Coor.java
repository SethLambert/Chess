package knight;

public class Coor {

		int x;
		int y;

		public Coor(int x, int y){
			this.x = x;
			this.y = y;
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

	public String toString(){
			String s = ("(" + x + ", " + y + ")");
			return s;
	}
	
	public boolean isMatch(Coor a){
		return (a.getX()==getX() && a.getY()==getY());
	}

}
