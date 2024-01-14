import java.util.LinkedList;

public class Order {
	private String crust;
	private String size;
	private LinkedList<String> toppings = new LinkedList<String>();
	private String drinkFlavor;
	private String drinkSize;
	private LinkedList<String> sides = new LinkedList<String>();
	
	Order(String inCrust, String inSize, LinkedList<String> inToppings){
		crust = inCrust;
		size = inSize;
		for (String i : inToppings) {
			toppings.add(i);
		}
	}
}
