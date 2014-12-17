package friedman.triangle;

public class Triangle {
	private StringBuilder triOutput;

	public Triangle(int height){
		triOutput = new StringBuilder();
		int firstSpace;
		int secondSpace;
		String stars = ("*");
		int lastRow = height * 2 - 1;

		for (int i = 1; i <= height-1; i++ ) {
			firstSpace = (lastRow - 2 * i +1) / 2;

			for(int j = 1; j <= firstSpace; j++){
				triOutput.append(" ");
			} 

			triOutput.append(stars);
			secondSpace = i * 2 -3; 
			if( i != 1 ){
				for(int k = 1;  k <= secondSpace; k++){
					triOutput.append(" ");
				}
			}
			if (i != 1){
				triOutput.append(stars);
			}
			triOutput.append("");
			triOutput.append("\n");
		}
		for(int l = 1; l <= lastRow; l++){
			triOutput.append(stars);
		}
	}

	public String toString(){
		return triOutput.toString();
	}

}
