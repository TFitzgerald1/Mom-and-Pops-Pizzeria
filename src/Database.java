
import java.io.*;
import java.util.*;

public class Database {
	public LinkedList<Customer> customerRecords = new LinkedList<Customer>();
	
	public void retrieveCustomerData() {
		try {
			File file = new File("bin/customerDoc.txt");
			BufferedReader br;
			
			br = new BufferedReader(new FileReader(file));
			
			String input;
			while ((input = br.readLine()) != null) {
				String[] CustArr = input.split(", ");
				Customer currentCustomer = new Customer(CustArr[0],CustArr[1],CustArr[2],CustArr[3],CustArr[4],CustArr[5], CustArr[6],CustArr[7]);
				customerRecords.add(currentCustomer);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveCustomerData() {
		try {
			File file = new File("bin/customerDoc.txt");
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (Customer i : customerRecords) {
				bw.write(i.toString());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//checks the username and password with each entry in the database to find a match
	public Customer findCustomer(String inUser, String inPass) {
		for (Customer current : customerRecords) {
			if (current.userName.equals(inUser) && current.password.equals(inPass)) {
				return current;
			}
		}
		return null;
	}
}
