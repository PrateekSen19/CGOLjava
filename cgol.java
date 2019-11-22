import java.util.Scanner;

public class cgol extends Thread {

	private int m, n;
	private int[][] Arr ;
	private int[][] FGeneration = new int[80][80];

	public cgol(int m, int n, int[][] Arr) {
		this.m=m;
		this.n=n;
		this.Arr = Arr;
	
	}
	
	
	@Override
	public void run() {
		
	//Time, which have been calculating
				long Ctime = System.nanoTime();
				int a,b,c,d;

				
				
	// For loop for every cell
				for(a=1; a<m-1; a++){
					for(b=1; b<m-1; b++){

						
						
						
	// Calculating aliveNeighbours
						int aliveNeighbours = 0;
						for(c=-1; c<=1; c++)
						for(d=-1; d<=1; d++)
						aliveNeighbours = aliveNeighbours + Arr[a+c][b+d];

						
						aliveNeighbours = aliveNeighbours - Arr[a][b];

						
						// Cell dies when lonely
						if((Arr[a][b] == 1) && (aliveNeighbours < 2)){
							FGeneration[a][b] = 0;
						}

						// // Cell dies due to over population
						else if((Arr[a][b] == 1) && (aliveNeighbours > 3)){
							FGeneration[a][b] = 0;
						}

						// Cell born due to 3 adjacent cells are alive
						else if((Arr[a][b] == 0) && (aliveNeighbours ==3)){
							FGeneration[a][b] = 1;
						}

						// Remains the same
						else{
							FGeneration[a][b] = Arr[a][b];
						}
					}
				}

				System.out.println();
				System.out.println("Next Generation is:");
				

				// Print Next Generation
				for(c=0; c<m; c++){
					for(d=0; d<n; d++){
						System.out.print(FGeneration[c][d] + " ");
					}
					System.out.println();
				}
				
				// It takes Next Generation in main array (Arr) which will print Future Generations
			
				for (c = 0; c < m; c++) {
					for (d = 0; d < n; d++) {
						Arr[c][d] = FGeneration[c][d];
					}
				}
				//Calculating the time to execute the next generation of CGOL
				long time = System.nanoTime() - Ctime;
		        System.out.println("Execution time for next generation: " + time + " ns");
		        System.out.println("Enter a to continue the game: ");
			}
	
	
public static void main(String[] args) {
		
		
		//Input is being given by the user
	
				Scanner sc = new Scanner(System.in);
				
				
				int m, n;
				
				char Continue;
				
				System.out.println("Input the no. of rows in an array:");
				
				//Input the row from the user
				
				m = sc.nextInt();

				System.out.println("Input the no. of columns in an array:");
				
				//Input the column from the user
				
				n = sc.nextInt();
				
				//Array have been declared which stores the initial enteries.
				
				int[][] Arr = new int[m][n];

				System.out.println("Enter the initial values that should be in 0 and 1:");
				
				
				//Initial enteries have been taken
				
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						Arr[i][j] = sc.nextInt();
					}
				}
				
				
				
				// function is calling and printing next generation
				
				do {
					
					//Object has been created of cgol class
					cgol mn = new cgol(m,n,Arr);
					
					//Starting the thread
					mn.start();
					
					//It continues the game for future generations
					Continue = sc.next().charAt(0);
				} while (Continue == 'a');

				//If the values are different then a, it ends the game
				System.out.println("Game Ends!!");
				sc.close();
			}
	
	}
	
	
