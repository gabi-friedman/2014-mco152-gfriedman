package friedman.acm;

import java.util.Arrays;
import java.util.Scanner;

public class NthLargestValue {

	public static void main(String[] args){
		
		Scanner console = new Scanner(System.in);
		StringBuilder build = new StringBuilder();
		
		//System.out.println("Enter number of sequences");
		int numSeqs = Integer.parseInt(console.nextLine());
		
		String ui;
		String[] splitUp;
		int[] nums;
		for(int z = 0; z < numSeqs; z++){
			ui = console.nextLine();
			
			splitUp = ui.split(" ");
			nums = new int[splitUp.length];
			for(int j = 0; j < splitUp.length; j++){
				nums[j] = Integer.parseInt(splitUp[j]);
			}
			Arrays.sort(nums, 1, nums.length);
			
			build.append(nums[0] + " " + nums[8] + "\n");
		}
	
		System.out.println(build.toString());
		console.close();
	}
}
