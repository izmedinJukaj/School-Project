import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomersUI {
	Customer newCustomer=new Customer();
	Data data=new Data();
	private ObservableList<Customer> customersData=FXCollections.observableArrayList(data.getCustomers());
	//adding customers
	private Label addCustomerLabel=new Label("Add new customer");
	private Label customerNameLabel=new Label("First Name");
	private TextField customerNameTF=new TextField();
	private Label customerLastNameLabel=new Label("Last Name");
	private TextField customerLastNameTF=new TextField();
	private Label customerPhoneLabel=new Label("Phone");
	private TextField customerPhoneTF=new TextField();
	private Button addCustomerBT=new Button("Add customer");
	
	private HBox pane=new HBox();
	private VBox addRemovePane=new VBox();
	private GridPane addPane=new GridPane();
	
	public CustomersUI() {
		
		pane.getChildren().addAll(createCustomerTable(), addRemovePane);
		addRemovePane.getChildren().addAll(addCustomerLabel,addPane ,addCustomerBT);
		addPane.add(customerNameLabel, 0, 0);
		addPane.add(customerLastNameLabel, 0, 1);
		addPane.add(customerPhoneLabel, 0, 2);
		addPane.add(customerNameTF, 1, 0);
		addPane.add(customerLastNameTF, 1, 1);
		addPane.add(customerPhoneTF, 1, 2);
		addPane.add(addCustomerBT, 1, 3);
		
		pane.setSpacing(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
        addRemovePane.setSpacing(5);
        addPane.setHgap(5);
        addPane.setVgap(5);
        
        addCustomerBT.setOnAction(e->{
        	newCustomer.setName(customerNameTF.getText());
        	newCustomer.setLastName(customerLastNameTF.getText());
        	newCustomer.setPhone(customerPhoneTF.getText());
        	data.getCustomers().add(newCustomer);
        	data.writeDataToFile();
        	customerNameTF.clear();
        	customerLastNameTF.clear();
        	customerPhoneTF.clear();
        });
        
	}
	public HBox getCustomersPane() {
		return pane;
	}
	
	private TableView<Customer> createCustomerTable() {
        TableView<Customer> table=new TableView<>();
        table.setItems(customersData);
        
        TableColumn firstNameCol=new TableColumn("First Name");
        TableColumn lastNameCol=new TableColumn("Last Name");
        TableColumn phoneCol=new TableColumn("Phone");
        TableColumn pointsCol=new TableColumn("Points");
        
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<Customer, Double>("points"));
       
        table.getColumns().addAll(firstNameCol, lastNameCol, phoneCol, pointsCol);

        return table;
    }
	
}
