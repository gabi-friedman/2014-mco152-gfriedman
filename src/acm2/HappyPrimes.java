package friedman.acm2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class HappyPrimes {
	
	static ArrayList<Integer> alreadyGot;
	
	
	public static boolean isPrime(Integer num){
		boolean prime = true;
		int divBy = 2;
		
		if(num <= 1){
			prime = false;
		}
		else if(num > 1 ){
			while(divBy <= num/2){
				if(num % divBy == 0){
					prime = false;
				}
				divBy++;
			}
		}
		
		return prime;
	}
	
	public static int[] splitNum(Integer num){
		
		Stack<Integer> stack = new Stack<Integer>();
		
		while(num > 0){
			stack.push(num % 10);
			num = num / 10;
		}
		
		int[] digits = new int[stack.size()];
		for(int i = 0; i < digits.length; i++){
			digits[i] = stack.pop();
		}
		
		return digits;

	}
	
	public static int sqAndAdd(int[] digits){
		int total = 0;
		
		for(int i = 0; i < digits.length; i++){
			digits[i] = (int) Math.pow(digits[i], 2);
			total += digits[i];
		}
		
		return total;
	}
	
	public static boolean isHappy(Integer num){
		alreadyGot = new ArrayList<Integer>();
		alreadyGot.add(37);
		alreadyGot.add(58);
		alreadyGot.add(89);
		alreadyGot.add(145);
		alreadyGot.add(42);
		alreadyGot.add(20);
		alreadyGot.add(4);
		alreadyGot.add(16);
		int[] digits = splitNum(num);
		int ans = sqAndAdd(digits);
		
		if(isPrime(num)){
			while(ans != 1){
				if(alreadyGot.contains(ans)){
					return false;
				}
				alreadyGot.add(ans);
				digits = splitNum(ans);
				ans = sqAndAdd(digits);
			}
			if(ans == 1){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		
		Scanner console = new Scanner(System.in);
		StringBuilder build = new StringBuilder();
	
		//System.out.println("Enter number of sequences");
		int numSeqs = Integer.parseInt(console.nextLine());
		
		String ui;
		String[] splitUp;
		boolean happy;
		String yn = "";
		for(int z = 0; z < numSeqs; z++){
			ui = console.nextLine();

			splitUp = ui.split(" ");
			
			happy = isHappy(Integer.parseInt(splitUp[1]));
			if(happy){
				yn = "YES";
			}
			else{
				yn = "NO";
			}
			
			
			build.append(splitUp[0] + " " + splitUp[1] + " "  + yn +"\n");
		}
	
		System.out.println(build.toString());
		console.close();
		
	}
}
