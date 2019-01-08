package knight;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

public class Games{
	
	static int n = 0, kR = 0, kC = 0, gR = 0, gC = 0;
	static Queue<Node> queue = new LinkedList<>();
	static Stack stack = new Stack();
	static String path = "", traversed = "";
	static String[][] board;
	static String[][] visited;
	
	public static void main(String args[]){
		setup();
		visited = blankBoard();
		Node root = new Node(kC, kR, n);
		BFS(root, gC, gR);
		visited = blankBoard();
		root = new Node(kC, kR, n);
		DFS(root, gC, gR);
		visited = blankBoard();
		String shortestPath = dijkstra();
		System.out.print("\nDijkstra " + shortestPath);
	}

private static String dijkstra() {
		String d = "";
		Coor goal = new Coor(gC, gR);
		int lowest = 9999, shortest = 9999, max = n*n;
		dNode root = new dNode(kC, kR, n);
		PriorityQueue<dNode> pQueue = new PriorityQueue(n*n);
		pQueue.add(root);
		while(!pQueue.isEmpty()){
			pQueue.remove();
			for(int i = 0; i < root.moves.length; i++){
				dNode next = new dNode(root.moves[i].x, root.moves[i].y, n, root);
				pQueue.add(next);
			}visited[root.getY()][root.getX()] = "[ V]";
			int h = root.getMoves().length;
			for(int k = 0; k < h; k++){
				//System.out.println("Priority Queue: " + pQueue);
				if(max > 0){
					dNode choose = pQueue.remove();
					max -= 1;
					if(choose.getJumps() < shortest && visited[choose.getY()][choose.getX()] != "[ V]"){
						root = choose;
						shortest = choose.getJumps();
					}
					if(choose.isMatch(goal) && choose.getJumps() < lowest){
						d = ("Shortest Path: " + choose.getPath());
						lowest = choose.getJumps();
					}	
				}else if (max == 0){
					//arrayPrint2D(visited, n, n);
					break;
				}
			}//end for
			//System.out.println("Priority Queue: " + pQueue);
			pQueue.add(root);
			shortest = 9999;
			if(max == 0){
				if(d == "")
					d = "Shortest Path not found";
				break;
			}
		}//end while
		return d;
	}

private static void BFS(Node root, int findX, int findY) {
	path = "";
	int c = 0;
	queue.add(root);
	while(!queue.isEmpty()){
		Node test = (Node)queue.remove();
		if(test.getX()==findX && test.getY()==findY){
			traversed += test.getLoc();
			System.out.println("\nBFS Nodes Traversed(" + c +"):" + traversed);
			path = test.getPath();
			System.out.println("Breadth First Path: " + path);
			break;
		}//end if
		traversed += test.getLoc();
		c++;
		for(int i = 0; i < test.moves.length; i++){
			int aX=test.moves[i].getX(), aY=test.moves[i].getY();
			if(visited[aY][aX] != "[ V]"){
				Node node = new Node(aX,aY, n, test);
				visited[aY][aX] = "[ V]";
				queue.add(node);
			}//end if
		}//end for
	}//end while		
}//end bfs

private static void DFS(Node root, int findX, int findY) {
	path = "";
	int c = 0;
	stack.push(root);
	while(!stack.isEmpty()){
		Node test = (Node)stack.pop();
		if(test.getX()==findX && test.getY()==findY){
			traversed += test.getLoc();
			System.out.println("\nDFS Nodes Traversed(" + c +"):" + traversed);
			path = test.getPath();
			System.out.println("Depth First Path: " + path);
			break;
		}//end if
		traversed += test.getLoc();
		c++;
		for(int i = 0; i < test.moves.length; i++){
			int aX=test.moves[i].getX(), aY=test.moves[i].getY();
			if(visited[aY][aX] != "[ V]"){
				Node node = new Node(aX,aY, n, test);
				visited[aY][aX] = "[ V]";
				stack.push(node);
			}//end if
		}//end for
	}//end while		
}//end bfs

	private static String[][] blankBoard() {
		String[][] board = new String[n+1][n+1];
		String temp="[  ]";
		board[0][0] = temp;
		for(int i = 1; i < n+1; i++){
			board[0][i] = ("["+(String.format("%02d", i))+"]");
			board[i][0] = ("["+(String.format("%02d", i))+"]");
		}
		for(int i=1; i<n+1; i++){
			for(int j=1; j<n+1; j++){
					board[i][j] = temp;
			}
		}
			//arrayPrint2D(board,n+1,n+1);
		return board;
	}
	
	public static void setup(){
		Scanner scanner = new Scanner(System.in);
		int c = 1;		
		
		while(c==1){
		System.out.print("Enter an N between 3 and 200:");
		n = scanner.nextInt();
		if(n>=3 && n<=200)
			c = 2;
		else
			System.out.print("N must be a value between 3 and 200. Re-enter:");
		}
		
		while(c==2){
			System.out.print("Give row position of Knight:");
			kR = scanner.nextInt();
			if(kR <= n)
				c = 3;
			else
				System.out.print("Knight row must be on board, Re-enter:");
		}
		
		while(c==3){
			System.out.print("Give column position of Knight:");
			kC = scanner.nextInt();
			if(kC <= n)
				c = 4;
			else
				System.out.print("Knight column must be on board, Re-enter:");
		}
		
		while(c==4){
			System.out.print("Give row position of Gold:");
			gR = scanner.nextInt();
			if(gR <= n)
				c = 5;
			else
				System.out.print("Gold row must be on board, Re-enter:");
		}
		
		while(c==5){
			System.out.print("Give column position of Gold:");
			gC = scanner.nextInt();
			if(gC <= n)
				c = 6;
			else
				System.out.print("Gold column must be on board, Re-enter:");
		}
		
		scanner.close();
		
		System.out.println("Board size: " + n + "\nKnight location: " + 
				kR + ", " + kC + "\nGold location: " + gR + ", " + gC);
	}
	
	public static void arrayPrint2D(String a[][], int height, int width){
		for(int i = 0; i<height; i++){
			for(int j = 0; j<width; j++){
				System.out.print(a[i][j] + " ");
			}
		System.out.print("\n");
		}
	}
}

//	public static void breadthFirst(){
//		path = "";
//		
//		//initialize agent coordinates		
//		int r = kR-1;
//		int c = kC-1;
//		int b = 0;
//		
//		//add possible moves
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < n; j++){
//				if((i==r-2&&j==c-1) || (i==r-2&&j==c+1) ||(i==r+2&&j==c-1) ||(i==r+2&&j==c+1) ||(i==r-1&&j==c-2) || (i==r-1&&j==c+2) ||(i==r+1&&j==c-2) ||(i==r+1&&j==c+2)){
//					if(visited[i][j]!="v"){
//						Node node = new Node(i,j);
//						queue.add(node);
//						visited[i][j]="v";
//					}
//				}
//			}
//		}
//		
//		//check moves in queue
//		while(!queue.isEmpty()){
//			Node test = (Node)queue.remove();
//			int x = test.getX(), y = test.getY();
//			path += ("(" + (x+1)+ ", " + (y+1) + ") ");
//			if(x == gR-1 && y == gC-1)
//				break;
//			else{
//				for(int i = 0; i < n; i++){
//					for(int j = 0; j < n; j++){
//						if((i==x-2&&j==y-1) || (i==x-2&&j==y+1) ||(i==x+2&&j==y-1) ||(i==x+2&&j==y+1) ||(i==x-1&&j==y-2) || (i==x-1&&j==y+2) ||(i==x+1&&j==y-2) ||(i==x+1&&j==y+2)){
//							if(visited[i][j]!="v"){
//								Node node = new Node(i,j);
//								queue.add(node);
//								visited[i][j]="v";
//							}
//						}
//					}
//				}//adds children of checked node to end of queue
//			}
//			board[x][y] = ("[" + (String.format("%02d", b+1)) + "]");
//			b++;
//		}
//		
//		System.out.println(path);
//		arrayPrint2D(board, n, n);
//	}
//	
//	public static void depthFirst(){
//		path = "";
//		
//		//initialize agent coordinates		
//		int r = kR-1;
//		int c = kC-1;
//		int b = 0;
//		
//		//add possible moves
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < n; j++){
//				if((i==r-2&&j==c-1) || (i==r-2&&j==c+1) ||(i==r+2&&j==c-1) ||(i==r+2&&j==c+1) ||(i==r-1&&j==c-2) || (i==r-1&&j==c+2) ||(i==r+1&&j==c-2) ||(i==r+1&&j==c+2)){
//					if(visited[i][j]!="v"){
//						Node node = new Node(i,j);
//						stack.push(node);
//						visited[i][j]="v";
//					}
//				}
//			}
//		}
//		
//		//check moves in queue
//		while(!stack.isEmpty()){
//			Node test = (Node)stack.pop();
//			int x = test.getX(), y = test.getY();
//			path += ("(" + (x+1)+ ", " + (y+1) + ") ");
//			if(x == gR-1 && y == gC-1)
//				break;
//			else{
//				for(int i = 0; i < n; i++){
//					for(int j = 0; j < n; j++){
//						if((i==x-2&&j==y-1) || (i==x-2&&j==y+1) ||(i==x+2&&j==y-1) ||(i==x+2&&j==y+1) ||(i==x-1&&j==y-2) || (i==x-1&&j==y+2) ||(i==x+1&&j==y-2) ||(i==x+1&&j==y+2)){
//							if(visited[i][j]!="v"){
//								Node node = new Node(i,j);
//								stack.push(node);
//								visited[i][j]="v";
//							}
//						}
//					}
//				}//adds children of checked node to end of queue
//			}
//			board[x][y] = ("[" + (String.format("%02d", b+1)) + "]");
//			b++;
//		}
//		
//		System.out.println(path);
//		arrayPrint2D(board, n, n);
//	}
//	


//public static void queueMoves(int r, int c){
//for(int i = 0; i < n; i++){
//	for(int j = 0; j < n; j++){
//		if((i==r-2&&j==c-1) || (i==r-2&&j==c+1) ||(i==r+2&&j==c-1) ||(i==r+2&&j==c+1) ||(i==r-1&&j==c-2) || (i==r-1&&j==c+2) ||(i==r+1&&j==c-2) ||(i==r+1&&j==c+2)){
//			Node node = new Node(i,j);
//			node.setVisited(true);
//			queue.add(node);
//		}
//	}
//}
//}
//
//private static String[][] generateBoard() {
//	String[][] board = new String[n][n];
//	String temp="[  ]";
//	for(int i=0; i<n; i++){
//		for(int j=0; j<n; j++){
//			if(i+1 == kR && j+1 == kC){
//				board[i][j] = "[ k]";
//			}else if(i+1 == gR && j+1 == gC){
//				board[i][j] = "[ g]";
//			}else{
//				board[i][j] = temp;
//			}
//		}
//	}
//	for(int i=0; i<n; i++){
//		for(int j=0; j<n; j++){
//		System.out.print(board[i][j]);
//		}
//		System.out.println("");
//	}
//	return board;
//}
//	public static void main(String args[]){
//
//setup();
//visited = blankBoard();
//visited[kR-1][kC-1] = "v";
//
////run breadth first search
//System.out.println("Running BFS");
//board = generateBoard();
//breadthFirst();
//
//visited = blankBoard();
//visited[kR-1][kC-1] = "v";
//
////run depth first search
//System.out.println("\n\nRunning DFS");
//board = generateBoard();
//depthFirst();
//}
//
