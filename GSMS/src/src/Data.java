import java.io.*;
import java.util.ArrayList;

public class Data {
	private ArrayList<User> users;
	private ArrayList<Product> inventory;
	//private ArrayList<Sale> sales;
	private ArrayList<Customer> customers;
	
	public Data() {
		try(ObjectInputStream input=new ObjectInputStream(new FileInputStream("users.txt"))){
			users=(ArrayList<User>) input.readObject();
		} catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		try(ObjectInputStream input=new ObjectInputStream(new FileInputStream("inventory.txt"))){
			inventory=(ArrayList<Product>) input.readObject();
		} catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		/*try(ObjectInputStream input=new ObjectInputStream(new FileInputStream("sales.txt"))){
			sales=(ArrayList<Sale>) input.readObject();
		} catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		try(ObjectInputStream input=new ObjectInputStream(new FileInputStream("customers.txt"))){
			customers=(ArrayList<Customer>) input.readObject();
		} catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	public ArrayList<Product> getInventory(){
		return inventory;
	}
	/*public ArrayList<Sale> getSales(){
		return sales;
	}*/
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	
	
	public void writeDataToFile() {
		try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("users.txt"))){
			output.writeObject(users);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("inventory.txt"))){
			output.writeObject(inventory);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		/*try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("sales.txt"))){
			output.writeObject(sales);
		} catch(IOException e) {
			e.printStackTrace();
		}*/
		
		try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream("customers.txt"))){
			output.writeObject(customers);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

