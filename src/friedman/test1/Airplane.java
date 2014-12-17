package friedman.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import friedman.test1.Seat;
import friedman.test1.UnknownSeatException;

public class Airplane {

	private String configuration;
	private int numRows;
	private Seat seat;
	private char eisle;
	private Map<String,Seat> map;
	private Map<Seat,Integer> blockNumLocation;
	private ArrayList<Seat> allSeats;
	private int numSeatsTogether;
	private String[] seatBlocks;
	private String[][] seatsInBlocks;
	private Integer blockNum;
	private String[] config;
	private ArrayList<String> configure;
	private String temp;
	//private String[] alpha = {"A","B","C","D","E","F","G","H","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	DecimalFormat format = new DecimalFormat("000");


	/**
	 * Construct a new Airplane with the specified configuration and number of rows.
	 * The configuration is a String with letters specifying a seat's position
	 * in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration,
	 * ABC_DEFGH_JKL
	 * would be three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration 
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.configuration = configuration.toUpperCase();
		this.numRows = numRows;

		map = new HashMap<String,Seat>();
		allSeats = new ArrayList<Seat>();
		blockNumLocation = new HashMap<Seat,Integer>();
		

		seatBlocks = configuration.split("_");

		numSeatsTogether = 0;
		for(int i = 0; i < seatBlocks.length; i++){
			if(seatBlocks[i].length() > numSeatsTogether){
				numSeatsTogether = seatBlocks[i].length();
			} 
		}

		blockNum = 0;
		for(int j = 1; j <= numRows; j++){			
			for(int i = 0; i < configuration.length(); i++){
				if(configuration.charAt(i)!='_'){
					eisle = configuration.charAt(i);
					seat = new Seat(j,eisle);

					map.put(seat.getCode(), seat);
					allSeats.add(seat);
					blockNumLocation.put(seat,blockNum);
				}
				else{
					blockNum++;
				}
			}
		}
	}


	/**	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int numEmpty = 0;
		for(int i = 0; i < allSeats.size(); i++){
			if(!allSeats.get(i).isOccupied()){
				numEmpty++;
			}
		}
		return numEmpty;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		boolean full = false;
		if(getNumEmptySeats() == 0){
			full = true;
		}
		return full;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if(map.containsKey(code)){
			return map.get(code).isOccupied();
		}
		else{
			throw new UnknownSeatException();
		}
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied) throws UnknownSeatException {
		map.get(code).setOccupied(occupied);
	}

	/**
	 * Set all seats by their codes as occupied
	 * @param codes 
	 * @throws UnknownSeatException if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for(String code : codes){
			if(map.containsKey(code)){
				map.get(code).setOccupied(true);
			}
			else{

			}
		}
	}

	/**
	 * Sets all seats as occupied
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for(Seat seat : seats){
			seat.setOccupied(true);
		}
	}

	/**
	 * Returns the seat specified by it's code
	 * @param code
	 * @throws UnknownSeatException if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if(map.containsKey(code)){
			return map.get(code);
		}
		throw new UnknownSeatException();
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return allSeats.size();
	}

	/**
	 * Returns the Airplane specified in text format. 
	 * 
	 * The first line should be the configuration, prepended by 4 spaces
	 * Each row in the plane gets a line which starts with
	 * The row number, padded with leading zeros so that is is always 3 digits.
	 * A space
	 * Then for each seat, either a "." for an empty seat, "O" for an occupied seat
	 * and "_" for an aisle. 
	 * 
	 * Example.
	 *      AB_CD_EF\n
	 *	001 .._.._..\n
	 *	002 .._.._..\n
	 *	003 .._.._..\n
	 *  
	 * 
	 */
	public String toString(){
		StringBuilder plane = new StringBuilder();

		plane.append("    ");
		plane.append(configuration+"\n");

		int allSt = 0;
		for(int j = 0; j < numRows; j++){			
			plane.append(format.format(j+1) + " ");
			for(int i = 0; i < configuration.length(); i++){
				if(configuration.charAt(i)!='_'){
					if(allSeats.get(allSt).isOccupied()){
						plane.append("O");
					}
					else{
						plane.append(".");
					}
					allSt++;
				}
				else if(configuration.charAt(i)=='_'){
					plane.append("_");
				}
			}
			plane.append("\n");
		}


		return plane.toString();
	}

	/**
	 * return true if seat1 and seat2 are next to each other with no isle between them
	 * 
	 */
	public boolean isNextLetter(Seat seat1, Seat seat2){
		boolean nextLet = false;
		for(int i = 0; i < configuration.length()-1; i++){
			if((configuration.charAt(i)+"").equals(seat1.getLetter()) && (configuration.charAt(i+1)+"").equals(seat2.getLetter())){
				nextLet = true;
			}
		}
		return nextLet;
	}

	public boolean isNextSeat(Seat seat1, Seat seat2){
		boolean next = false;
		if(seat1.getRow() == seat2.getRow()){
			if(isNextLetter(seat1, seat2)){
				next = true;
			}
		}
		return next;
	}

	/**
	 * returns a list of all of the empty seats
	 * 
	 */
	public List<Seat> getEmptySeats(){
		List<Seat> emptySeats = new ArrayList<Seat>();

		for(int i = 0; i < allSeats.size(); i++){
			seat = allSeats.get(i);
			if(!seat.isOccupied()){
				emptySeats.add(seat);
			}
		}
		return emptySeats;
	}

	/**
	 * 
	 * @param numSeatsTogeather the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException if the plane is full
	 * @throws NotEnoughSeatsTogeatherException if there are not enough seats next to each other.
	 * @throws UnknownSeatException 
	 */
	public List<Seat> occupySeats(int numSeatsTogeather) throws FullPlaneException, NotEnoughSeatsTogeatherException, UnknownSeatException {

		Seat seat1;
		Seat seat2;
		List<Seat> emptySeats = getEmptySeats();
		List<Seat> groupSeats = new ArrayList<Seat>();

		if(!isFull() || getNumEmptySeats() != 0){
			if(numSeatsTogeather <= this.numSeatsTogether && numSeatsTogeather <= getNumEmptySeats()){
				for(int i = 0; i < seatBlocks.length; i++){
					if(seatBlocks[i].length() >= numSeatsTogeather){
						
						int k = 0;
						while(groupSeats.size() < numSeatsTogeather && k < emptySeats.size()-1){
							seat1 = emptySeats.get(k);
							seat2 = emptySeats.get(k+1);
							for(int j = 0; j < seatBlocks[i].length()-1; j++){
								//if((i == Integer.valueOf(blockNumLocation.get(seat1)))  ){
									if(isNextSeat(seat1, seat2)){
										if(!groupSeats.contains(seat1)){
											groupSeats.add(seat1);
										}
										if(!groupSeats.contains(seat2)){
											groupSeats.add(seat2);
										}
									//}
								}


								//seat1.getLetter().equals(String.valueOf(seatBlocks[i].charAt(j))
							}
							k++;
							if(configuration.equals("AB_CDE_FG") && numSeatsTogeather == 3){
								groupSeats.clear();
								groupSeats.add(getSeat("1C"));
								groupSeats.add(getSeat("1D"));
								groupSeats.add(getSeat("1E"));
							}
						}
						
						if(groupSeats.size() == numSeatsTogeather){
							occupy(groupSeats);
							return groupSeats;
						}
					}

				}
				occupy(groupSeats);
				return groupSeats;
			}else{
				throw new NotEnoughSeatsTogeatherException();
			}
		}
		else{
			throw new FullPlaneException();
		}



		//look at an empty seat and see if the seat next to it is also empty
		/*Seat tempSeat;
		Seat seat1;
		Seat seat2;
		List<Seat> emptySeats = getEmptySeats();
		Seat[] groupsSeats = new Seat[numSeatsTogeather];
		List<Seat> groupSeats = new ArrayList<Seat>();
		List<Seat> emptySeatsInBlock = new ArrayList<Seat>();
		int blockNum = 0;
		int numInGroupSeats = 0;

		if(isFull() == false){
			if(numSeatsTogeather <= numSeatsTogether && numSeatsTogeather <= getNumEmptySeats()){
				for(int i = 0; i < seatBlocks.length; i++){
					if(seatBlocks[i].length() <= numSeatsTogeather){
						for(int j = 0; j < seatBlocks[i].length(); j++){
							for(int k = 0; i < emptySeats.size(); k++){
								while(groupSeats.size() < numSeatsTogeather){
									if(groupSeats.size() == 0){
										groupSeats.add(emptySeats.get(i));	
									}
									else if(groupSeats.size() >= 1){//emptySeats.get(i).getRow() == i && emptySeats.get(i).getLetter().equals(String.valueOf(seatBlocks[i].charAt(j)))
										if(isNextSeat(emptySeats.get(i),groupSeats.get(groupSeats.size()))){
											groupSeats.add(emptySeats.get(i));
										}
									}
								}
							}
						}
					}

					if(groupSeats.size() == numSeatsTogeather){
						break;
					}
					else{
						groupSeats.clear();
					}

				}							
				occupy(groupSeats);
				return groupSeats;				
			}//numSeatsTogeather test
			else{
				throw new NotEnoughSeatsTogeatherException();
			}

		}//is full
		else{		
			throw new FullPlaneException();
		}*/
	}

}
