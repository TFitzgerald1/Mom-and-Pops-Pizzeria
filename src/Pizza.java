import java.util.LinkedList;

public class Pizza {
		private String crust;
		public String size;
		public LinkedList<String> toppings = new LinkedList<String>();
		
		
		Pizza(String inCrust, String inSize, LinkedList<String> inToppings){
			crust = inCrust;
			size = inSize;
			for(String i : inToppings) {
				toppings.add(i);
			}
		}
		
		public String toString() {
			String dispOrder = size + " pizza with " + crust + " crust <br> toppings: cheese, "; 
					for (String h : toppings) {
						dispOrder = dispOrder + h + ", ";
					}
			return dispOrder;
		}
}
