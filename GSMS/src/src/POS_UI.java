import java.util.ArrayList;

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
	ObservableList<Transaction> transaction=FXCollections.observableArrayList();
	
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
	
	private HBox pane=new HBox();
	private GridPane infoPane=new GridPane();
	
	public POS_UI() {
		
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
		infoPane.add(confirmBT, 1, 5);
		
		infoPane.setHgap(5);
		infoPane.setVgap(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setSpacing(5);
		
		pane.getChildren().addAll(createTransactionTable(), infoPane);
		
		addProductBT.setOnAction(e -> {
		    boolean found = false;

		    for (int i=0; i<transaction.size(); i++) {
		        if (productNameTF.getText().equals(transaction.get(i).getName())) {
		            transaction.get(i).setQuantity(transaction.get(i).getQuantity() + 1);
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
		                    data.getInventory().get(j).setQuantity(inventoryQuantity-1);
		                    found=true;
		                } else {
		                    System.out.println("Out of stock");
		                }
		                break;
		            }
		        }
		    }
		});
		
		searchBT.setOnAction(e->{setCustomerPointsTF();});
		
		applyBT.setOnAction(e->{
			if(!totalBeforeDiscTF.getText().isEmpty())
				applyDiscount();
		});
		
		confirmBT.setOnAction(e->{
			//being worked on
		});
	}
	
	private void setCustomerPointsTF() {
	    for (int i = 0; i < data.getCustomers().size(); i++) {
	        if (data.getCustomers().get(i).getPhone().equals(customerPhoneTF.getText())) {
	            customerPointsTF.setText(String.valueOf(data.getCustomers().get(i).getPoints()));
	            return;
	        }
	    }
	    customerPhoneTF.clear();
	}
	
	private void applyDiscount() {
		if(Double.parseDouble(customerPointsTF.getText())==1000) {
			double total = Double.parseDouble(totalBeforeDisc.getText());
			double percentage=5;
			double totalDisc=total-(total*percentage/100);
			totalAfterDiscTF.setText(String.valueOf(totalDisc));
		}
	}
	
	private TableView<Transaction> createTransactionTable() {
        TableView<Transaction> table=new TableView<>();
        table.setItems(transaction);

        TableColumn nameCol=new TableColumn("Name");
        TableColumn quantityCol=new TableColumn("Quantity");
        TableColumn priceCol=new TableColumn("Price");
        TableColumn totalCol=new TableColumn("Total");
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("total"));

        table.getColumns().addAll(nameCol, quantityCol, priceCol, totalCol);

        return table;
    }
	
	public HBox getPOSpane() {
		return pane;
	}
	
}
