package friedman.acm;

import java.util.Scanner;

public class RepeatingCharacters {
	
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		//System.out.println("How many lines you would line to enter");
		int numLines = Integer.parseInt(console.nextLine());
		
		String[] splitUp;// = new String[];
		
		StringBuilder build = new StringBuilder();
		String ui;
		
		for(int z = 0; z < numLines; z++){
			ui = console.nextLine();
			
			splitUp = ui.split(" ");
			build.append(splitUp[0] + " ");
				for(int k = 0; k < splitUp[2].length(); k++){
					for(int x = 0; x < Integer.parseInt(splitUp[1]); x++){
						build.append(splitUp[2].charAt(k));
					}
				}
				build.append("\n");
		}
		
		System.out.println(build.toString());
		console.close();
	}
	

}
