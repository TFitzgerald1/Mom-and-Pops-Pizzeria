import java.util.LinkedList;

public class Sides {
	public String drinkFlavor;
	private String drinkSize;
	public LinkedList<String> nonDrinkSides = new LinkedList<String>();
	
	Sides(String inFlavor, String inSize, LinkedList<String> inSides) {
		drinkFlavor = inFlavor;
		drinkSize = inSize;
		for (String i : inSides) {
			nonDrinkSides.add(i);
		}
	}
	
	public String toString() {
		String line = "";
		if (drinkSize == "none" || drinkFlavor == "none") { 
			for (String h : nonDrinkSides) {
				line = line + h + ", ";
			}
		} else {
			line = drinkSize + " sized " + drinkFlavor + " soda with: <br>"; 
			for (String h : nonDrinkSides) {
				line = line + h + ", ";
			}
		}
		
		return line;
	}
}
