package friedman.acm2;


import java.util.Scanner;

public class RascalTriangle{
	
	public static void main(String[] args){
		
		Scanner console = new Scanner(System.in);
		StringBuilder build = new StringBuilder();
		
		//System.out.println("Enter number of entries");
		int numSeqs = Integer.parseInt(console.nextLine());
		
		String ui;
		String[] splitUp;
		int[] nums;
		int term;
		int rowNum;
		int rowTermNum;
		int diagNum;
		int diagTermNum;
		
		for(int z = 0; z < numSeqs; z++){
			ui = console.nextLine();
			
			splitUp = ui.split(" ");
			nums = new int[splitUp.length];
			for(int j = 0; j < splitUp.length; j++){
				nums[j] = Integer.parseInt(splitUp[j]);
			}
			
			rowNum = nums[1];
			rowTermNum = nums[2];
			diagNum = rowTermNum;
			diagTermNum = rowNum - rowTermNum;
			
			if(rowTermNum == 0){
				term = 1;
			}
			else{
				term = diagNum * diagTermNum + 1;
			}

			
			
			build.append(nums[0] + " " + term + "\n");
		}
	
		System.out.println(build.toString());
		console.close();
	}
	
	
}