package friedman.morsecode;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {
	private String[] code;
	private String[] letters;
	Map<String,String> map;

	public MorseCode(){
		
		letters = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"," "};
		code = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..","-----",".----","..---","...--","....-",".....","-....","--...","---..","----.","/"};
		map = new HashMap<String,String>();
		
		for(int i = 0; i < letters.length; i++){
			String key = letters[i];
			String value = code[i];
			
			map.put(key, value);
			map.put(value, key);
		}
	}
	public String toMorseCode(String text){
		String newText = text.toUpperCase();
		StringBuilder morseWord = new StringBuilder();
		
		String[] listOfLetters =  newText.split("");
		
		for(int i = 1; i < listOfLetters.length; i++){
			morseWord.append(map.get(listOfLetters[i]));
			if(i < listOfLetters.length - 1){
				morseWord.append(" ");
			}
		}
		return morseWord.toString();
		
		/*
		for (int i = 0; i < newText.length(); i++){
			for(int j = 0; j < letters.length; j++){
				if(newText.charAt(i) == letters[j]){
					morseWord.append(code[j] + " ");
					break;
				}
			}
		}
		if(morseWord.length() > 0){
			morseWord.deleteCharAt(morseWord.length()-1);
		}
		return morseWord.toString();*/
	}
	public String toPlainText(String input){
		StringBuilder englishWord = new StringBuilder();
		String[] morseToTranslate = input.split(" ");
		
		for (int i = 0; i < morseToTranslate.length; i++){
			String letter = map.get(morseToTranslate[i]);
			englishWord.append(letter);
		}
		
		return englishWord.toString();
	}
	
}