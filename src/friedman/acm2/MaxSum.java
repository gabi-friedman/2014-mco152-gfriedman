package friedman.acm2;

import java.util.Scanner;

public class MaxSum{

	public static int maxSubRec(int[][] rec){
		int arrLen = rec.length;
		int[][] temp = new int[arrLen][arrLen];

		for(int i = 0; i < arrLen; i++){
			for(int j = 0; j < arrLen; j++){
				if(j == 0){
					temp[j][i] = rec[j][i];
				}
				else{
					temp[j][i] = rec[j][i] + temp[j-1][i];
				}
			}
		}

		int maxSum = rec[0][0];

		int[] sum = new int[arrLen];
		int[] pos = new int[arrLen];

		//this method is called the KANDANE method
		for(int i = 0; i < arrLen; i++){
			for(int k = i; k < arrLen; k++){
				setArrayToZero(sum);	
				setArrayToZero(pos);
				int tempMax = 0;

				sum[0] = temp[k][0] - (i==0 ? 0 : temp[i-1][0]);
				for (int j = 1; j < arrLen; j++) {                    
					if (sum[j-1] > 0){
						sum[j] = sum[j-1] + temp[k][j] - (i==0 ? 0 : temp[i-1][j]);
						pos[j] = pos[j-1];
					}else{
						sum[j] = temp[k][j] - (i==0 ? 0 : temp[i-1][j]);
						pos[j] = j;
					}
					if (sum[j] > sum[tempMax]){
						tempMax = j;
					}
				}
				if (sum[tempMax] > maxSum){
					maxSum = sum[tempMax];
				}   
			}
		}
		return maxSum;
	}

	private static void setArrayToZero(int[] array) {
		for(int i = 0; i < array.length; i++){
			array[i] = 0;		
		}
	}

	public static void main(String[] args){

		Scanner console = new Scanner(System.in);

		//System.out.println("Enter number of sequences");
		int num = console.nextInt();

		int[][] rec = new int[num][num];
		int max;

		for(int i = 0; i < num; i++){
			for(int j = 0; j < num; j++){
				rec[i][j] = console.nextInt();
			}
		}

		max = maxSubRec(rec);
		System.out.println(max);
		

		console.close();

	}

}