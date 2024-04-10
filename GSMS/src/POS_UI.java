import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class POS_UI {
	Data data=new Data();
	SaleReportUI sales=new SaleReportUI();
	CustomersUI customers=new CustomersUI();
	InventoryUI inventory=new InventoryUI();
	ObservableList<Transaction> transaction=FXCollections.observableArrayList();
	private TableView<Transaction> table;
	private Label productName=new Label("Product Name");
	private Label customerPhone=new Label("Customer's Phone");
	private Label totalBeforeDisc=new Label("Total");
	private Label totalAfterDisc=new Label("Total after discount");
	private Label customerPoints=new Label("Customer points");
	
	private TextField productNameTF=new TextField();
	private TextField customerPhoneTF=new TextField();
	private TextField totalBeforeDiscTF=new TextField();
	private TextField totalAfterDiscTF=new TextField();
	private TextField customerPointsTF=new TextField();
	
	private Button addProductBT=new Button("Add");
	private Button confirmBT=new Button("Confirm");
	private Button searchBT=new Button("Search");
	private Button applyBT=new Button("Apply");
	private Button clearBT=new Button("Clear");
	
	private HBox transactionControl=new HBox();
	private HBox pane=new HBox();
	private GridPane infoPane=new GridPane();
	
	public POS_UI() {
		
		table = createTransactionTable();
		table.setPrefWidth(320);
		transactionControl.getChildren().addAll(confirmBT, clearBT);
		transactionControl.setSpacing(5);
		
		infoPane.add(productName, 0, 0);
		infoPane.add(productNameTF, 1, 0);
		infoPane.add(addProductBT, 2, 0);
		infoPane.add(customerPhone, 0, 1);
		infoPane.add(customerPhoneTF, 1, 1);
		infoPane.add(searchBT, 2, 1);
		infoPane.add(customerPoints, 0, 2);
		infoPane.add(customerPointsTF, 1, 2);
		infoPane.add(applyBT, 2, 2);
		infoPane.add(totalBeforeDisc, 0, 3);
		infoPane.add(totalBeforeDiscTF, 1, 3);
		infoPane.add(totalAfterDisc, 0, 4);
		infoPane.add(totalAfterDiscTF, 1, 4);
		infoPane.add(transactionControl, 1, 5);
		
		customerPointsTF.setEditable(false);
		
		infoPane.setHgap(5);
		infoPane.setVgap(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setSpacing(5);
		
		pane.getChildren().addAll(table, infoPane);
		
		//adding products to transaction list
		addProductBT.setOnAction(e -> {
			addProductsToTransaction();});
		
		//search customer
		searchBT.setOnAction(e->{
			setCustomerPointsTF();});
		
		//apply discount if points>=1000
		applyBT.setOnAction(e->{
			if(!totalBeforeDiscTF.getText().isEmpty())
				applyDiscount();
		});
		
		confirmBT.setOnAction(e->{
			confirm();
		});
		
		//clear table
		clearBT.setOnAction(e->{
			transaction.clear();
			totalBeforeDiscTF.clear();
			totalAfterDiscTF.clear();
			productNameTF.clear();
			customerPhoneTF.clear();
			customerPointsTF.clear();
			table.refresh();
		});
		
		
	}
	
	public TableView<Transaction> createTransactionTable() {
	       table=new TableView<>();
	        table.setItems(transaction);

	        TableColumn nameCol=new TableColumn("Name");
	        TableColumn quantityCol=new TableColumn("Quantity");
	        TableColumn priceCol=new TableColumn("Price");
	        TableColumn totalCol=new TableColumn("Total");
	        
	        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
	        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
	        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	        totalCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("total"));

	        table.getColumns().addAll(nameCol, quantityCol, priceCol, totalCol);

	        return table;
	    }
	
	private void addProductsToTransaction() {
		boolean found = false;

	    for (int i=0; i<transaction.size(); i++) {
	        if (productNameTF.getText().equals(transaction.get(i).getName())) {
	            transaction.get(i).setQuantity(transaction.get(i).getQuantity() + 1);
	            transaction.get(i).calculateTotal();
	            table.refresh();
	            found=true;
	            break;
	        }
	    }

	    if (!found) {
	        for (int j=0; j<data.getInventory().size(); j++) {
	            if (productNameTF.getText().equals(data.getInventory().get(j).getName())) {
	                int inventoryQuantity=data.getInventory().get(j).getQuantity();

	                if (inventoryQuantity>0) {
	                    transaction.add(new Transaction(data.getInventory().get(j).getName(), 1, data.getInventory().get(j).getPrice()));
	                    found=true;
	                } else {
	                    System.out.println("Out of stock");
	                }
	                break;
	            }
	        }
	    }
	    double total=0.0;
	    for(int i=0;i<transaction.size();i++) {
	    	total=total+transaction.get(i).getTotal();
	    }
	    totalBeforeDiscTF.setText(String.valueOf(total));
	}
	
	private void setCustomerPointsTF() {
	    for (int i = 0; i < data.getCustomers().size(); i++) {
	        if (data.getCustomers().get(i).getPhone().equals(customerPhoneTF.getText())) {
	            customerPointsTF.setText(String.valueOf(data.getCustomers().get(i).getPoints()));
	            return;
	        }
	    }
	}
	
	private void applyDiscount() {
	    String customerPointsText = customerPointsTF.getText();
	    double customerPoints = Double.parseDouble(customerPointsText);

	    if (customerPoints >= 1000) {
	        String totalBeforeDiscText = totalBeforeDiscTF.getText();
	        double total = Double.parseDouble(totalBeforeDiscText);
	        double percentage = 10;
	        double totalDisc = total - (total * percentage / 100);
	        totalAfterDiscTF.setText(String.valueOf(totalDisc));

	        // Subtract 1000 points from the customer
	        String customerPhoneText = customerPhoneTF.getText();
	        for (int i=0;i<data.getCustomers().size();i++) {
	            if (data.getCustomers().get(i).getPhone().equals(customerPhoneText)) {
	                data.getCustomers().get(i).setPoints(customerPoints-1000.0);
	                customers.getCustomersData().get(i).setPoints(customerPoints-1000.0);
	                customers.getTable().refresh();
	                data.writeDataToFile();
	                break;
	            }
	        }
	    }
	}
	
	private void confirm() {
	    for (Transaction transactionItem : transaction) {
	        // Update sales entries
	        boolean salesItemFound = false;
	        for (Transaction salesItem : sales.getTransactionHistory()) {
	            if (transactionItem.getName().equals(salesItem.getName())) {
	                salesItem.setQuantity(salesItem.getQuantity() + transactionItem.getQuantity());
	                salesItem.calculateTotal();
	                data.writeDataToFile();
	                sales.getTable().refresh();
	                salesItemFound = true;
	                break;
	            }
	        }
	        
	        for (Transaction salesItem : data.getSales()) {
	            if (transactionItem.getName().equals(salesItem.getName())) {
	                salesItem.setQuantity(salesItem.getQuantity() + transactionItem.getQuantity());
	                salesItem.calculateTotal();
	                data.writeDataToFile();
	                // Manually refresh the TableView
	                sales.getTable().refresh();
	                
	                salesItemFound = true;
	                break;
	            }
	        }
	        
	        if (!salesItemFound) {
	            Transaction newSalesItem = new Transaction(
	                    transactionItem.getName(),
	                    transactionItem.getQuantity(),
	                    transactionItem.getPrice()
	            );
	            sales.getTransactionHistory().add(newSalesItem);
	            data.getSales().add(newSalesItem);
	            sales.getTable().refresh();
	            data.writeDataToFile();
	        }

	        // Update inventory quantity
	        for (Product inventoryItem : data.getInventory()) {
	            if (transactionItem.getName().equals(inventoryItem.getName())) {
	                inventoryItem.setQuantity(inventoryItem.getQuantity() - transactionItem.getQuantity());
	                data.writeDataToFile();
	                inventory.getTable().refresh();
	                break;
	            }
	        }

	        // Update inventory quantity using enhanced for loop
	        for (Product inventoryItem : inventory.getProductsData()) {
	            if (transactionItem.getName().equals(inventoryItem.getName())) {
	                inventoryItem.setQuantity(inventoryItem.getQuantity() - transactionItem.getQuantity());
	                data.writeDataToFile();
	                inventory.getTable().refresh();
	            }
	        }

	        // Update sales in data
	        for (Transaction dataSalesItem : data.getSales()) {
	            if (transactionItem.getName().equals(dataSalesItem.getName())) {
	                dataSalesItem.setQuantity(dataSalesItem.getQuantity() + transactionItem.getQuantity());
	                dataSalesItem.calculateTotal();
	                data.writeDataToFile();
	                sales.getTable().refresh();
	                break;
	            }
	        }
	    }
	    
	    String customerPhoneText = customerPhoneTF.getText();

	    // Check if the customer phone is not empty
	    if (!customerPhoneText.isEmpty()) {
	        double totalPointsToAdd = 0.0;

	        // Determine the total points based on totalAfterDiscTF if it has text; otherwise, use totalBeforeDiscTF
	        if (!totalAfterDiscTF.getText().isEmpty()) {
	            totalPointsToAdd = Double.parseDouble(totalAfterDiscTF.getText());
	        } else if (!totalBeforeDiscTF.getText().isEmpty()) {
	            totalPointsToAdd = Double.parseDouble(totalBeforeDiscTF.getText());
	        }

	        // Find the customer with the specified phone number
	        for (Customer customer : data.getCustomers()) {
	            if (customer.getPhone().equals(customerPhoneText)) {
	                customer.setPoints(totalPointsToAdd+customer.getPoints());
	                customers.getTable().refresh();
	                data.writeDataToFile();
	                break;
	            }
	        }
	    }

	    // Clear the transaction list after confirming the sale
	    transaction.clear();

	    // Update the UI or perform any other necessary actions
	    table.refresh();
	    totalBeforeDiscTF.clear();
	    totalAfterDiscTF.clear();
	    customerPhoneTF.clear();
	    customerPointsTF.clear();

	}

	
	public HBox getPOSpane() {
		return pane;
	}
	
}
