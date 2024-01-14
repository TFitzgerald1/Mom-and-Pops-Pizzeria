
public class Customer {
	public String firstName;
	public String lastName;
	public String phone;
	public String email;
	public String address;
	public String payment;
	public String userName;
	public String password;
	
	Customer(String inFirst, String inLast, String inPhone, String inEmail, String inAdd, String inPay, String inUser, String inPass){
		firstName = inFirst;
		lastName = inLast;
		phone = inPhone;
		email = inEmail;
		address = inAdd;
		payment = inPay;
		userName = inUser;
		password = inPass;
	}

	
	public String toString() {
		String dispOrder = firstName + ", " + lastName + ", " + phone + ", " + email + ", " + address + ", " + payment + ", " + userName + ", " + password;
		return dispOrder;
	}
}
