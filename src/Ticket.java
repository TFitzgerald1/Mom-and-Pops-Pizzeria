import java.text.DecimalFormat;
import java.util.LinkedList;

public class Ticket {
	public LinkedList<Pizza> activePizzas = new LinkedList<Pizza>();
	public LinkedList<Sides> activeSides = new LinkedList<Sides>();
	public double subTotal;
	public double taxTotal;
	public double totalAmount;
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	
	public String calculatesubTotal() {
		subTotal = 0;
		double multiplyer;
		for (Pizza i : activePizzas) {
			if (i.size == "small") {
				multiplyer = 0.5;
				subTotal = subTotal + 4 + (multiplyer * (i.toppings.size() - 1));
			}
			if (i.size == "medium") {
				multiplyer = 0.75;
				subTotal = subTotal + 6 + (multiplyer * (i.toppings.size() - 1));
			}
			if (i.size == "large") {
				multiplyer = 1;
				subTotal = subTotal + 8 + (multiplyer * (i.toppings.size() - 1));
			}
			if (i.size == "X-Large") {
				multiplyer = 1.25;
				subTotal = subTotal + 10 + (multiplyer * (i.toppings.size() - 1));
			}
		}
		for (Sides i : activeSides) {
			if (i.drinkFlavor != "none") {
				subTotal += 1;
			}
			for (String h : i.nonDrinkSides) {
				if (h == "breadsticks") {
					subTotal += 4;
				}
				if (h == "Breadstick Bites") {
					subTotal += 2;
				}
				if (h == "Big Chocolate Chip Cookie") {
					subTotal += 4;
				}
			}
		}
		return Double.toString(subTotal);
	}
	
	public String calculatetaxTotal() {
		taxTotal = .06 * subTotal;
		return df.format(taxTotal);
	}
	
	public String calculateTotal() {
		totalAmount = taxTotal + subTotal;
		return df.format(totalAmount);
	}
	
	public String toString() {

		String returnDisp = "<html>";
		for(Pizza i : activePizzas) {
			returnDisp = returnDisp + i.toString() + " <br><br>";
		}
		for(Sides h : activeSides) {
			returnDisp = returnDisp + h.toString() + " <br><br>";
		}
		returnDisp = returnDisp + "</html>";
		return returnDisp;
	}
}
