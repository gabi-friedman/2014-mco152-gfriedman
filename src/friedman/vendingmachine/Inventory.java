package friedman.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	
	private ArrayList<Item> list;
	Map<String,Item> map;
	
	public Inventory() {
		map = new HashMap<String,Item>();
		list = new ArrayList<Item>();
	}
	
	public void load(String inventoryFilename) throws IOException {
		Scanner inputFile = new Scanner(new File(inventoryFilename));
		String[] temp = new String[4];
		String code;
		String name;
		double price;
		int quantity;
		Item item;
		
		while(inputFile.hasNext()){
			temp = inputFile.nextLine().split(",");
			code = temp[0];
			name = temp[1];
			price = Double.parseDouble(temp[2]);
			quantity = Integer.parseInt(temp[3]);
			
			item = new Item(code, name, price, quantity);
			list.add(item);
			map.put(code, item);
		}
		
		inputFile.close();
	}
	
	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if(map.containsKey(code)){	
			return map.get(code);
		}
		else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param item to add
	 */
	public void add(Item item) {
		map.put(item.getCode(),item);
		list.add(item);
	}
	
	/**
	 * Removes one from quantity of the specified item
	 * @param code
	 */
	public void removeOne( String code ) {
		if(map.containsKey(code)){
			map.get(code).decreaseQuantity();;
		}
	}
	
	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is at least one quantity, otherwise false.
	 */
	public boolean isEmpty( String code ) {
		if(map.containsKey(code)){
			int amt = map.get(code).getQuantity();
			if(amt == 0){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * Lists the items in the inventory one per line in the format
	 * code name @ price x quantity\n
	 */
	public String toString() {
		StringBuilder inventory = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		for(int i = 0; i < list.size(); i++){
			inventory.append(list.get(i).getCode());
			inventory.append(" ");
			inventory.append(list.get(i).getName());
			inventory.append(" @ ");
			inventory.append(formatter.format(list.get(i).getPrice()));
			inventory.append(" x ");
			inventory.append(list.get(i).getQuantity());
			inventory.append("\n");
			
			/*
			 * inventory.append(list(i).toString(); 
			 * */
		}
		return inventory.toString();
	}

}
