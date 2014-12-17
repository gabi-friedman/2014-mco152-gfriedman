package friedman.triangle;

import java.util.Scanner;

class oldTriangle {

	public static void main( String args[]) {

		Scanner console = new Scanner(System.in);
		System.out.println("How many rows in the triangle?");
		int rows = console.nextInt(); 

		int firstSpace;
		int secondSpace;
		String star = "*";
		String space = " ";
		int lastRow = rows * 2 - 1;
		
		StringBuilder fullTri = new StringBuilder();
		

		for (int i = 1; i <= rows - 1; i++ ) {
			
			firstSpace = (lastRow - 2 * i +1) / 2;

			for(int j = 1; j <= firstSpace; j++){
				fullTri.append(space);
			} 

			fullTri.append(star);

			secondSpace = i * 2 -3; 
			if( i != 1 ){
				for(int k = 1;  k <= secondSpace; k++){
					fullTri.append(space);
				}
			}
			if (i != 1){
				fullTri.append(star); 
			}
			fullTri.append(System.getProperty("line.separator"));
		}

		for(int i = 0; i < lastRow; i++){
			fullTri.append(star);
		}

	}
	
	
}