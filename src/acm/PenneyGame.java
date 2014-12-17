package friedman.acm;

import java.util.Arrays;
import java.util.Scanner;

public class PenneyGame{

	public static int getSeqTimes(char[] array, String playerSequence){
		int count = 0;
		for(int i=0; i<array.length-2;i++){
			char one = array[i];
			char two = array[i+1];
			char three = array[i+2];
			String currentSequence = String.valueOf(one) + String.valueOf(two) + String.valueOf(three) ;
			if(currentSequence.equalsIgnoreCase(playerSequence)){
				count++;
			}
		}
		return count;
	}

	public static void main(String [] args){

		StringBuilder build = new StringBuilder();

		Scanner console = new Scanner(System.in);

		int numSeqs = Integer.parseInt(console.nextLine());

		int seqNum;
		String seq;
		char[] splitUp;
		for(int i = 0; i < numSeqs; i++){
			seqNum = Integer.parseInt(console.nextLine());
			seq = console.nextLine();

			build.append(seqNum + " ");

			splitUp = new char[40];

			for(int j = 0; j < splitUp.length; j++){
				splitUp[j] = seq.charAt(j);
			}

			build.append(getSeqTimes(splitUp, "TTT")+ " ");
			build.append(getSeqTimes(splitUp, "TTH")+ " ");
			build.append(getSeqTimes(splitUp, "THT")+ " ");
			build.append(getSeqTimes(splitUp, "THH")+ " ");
			build.append(getSeqTimes(splitUp, "HTT")+ " ");
			build.append(getSeqTimes(splitUp, "HTH")+ " ");
			build.append(getSeqTimes(splitUp, "HHT")+ " ");
			build.append(getSeqTimes(splitUp, "HHH")+ "\n");

			Arrays.fill(splitUp, ' ');
		}
		System.out.println(build.toString());
		console.close();
	}

}