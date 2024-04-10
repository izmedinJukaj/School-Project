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

public class InventoryUI {
	Data data=new Data();
	private ObservableList<Product> productsData=FXCollections.observableArrayList(data.getInventory());
	//adding products to inventory
	private Label addProductLabel=new Label("Add new product");
	private Label productNameLabel=new Label("Product name");
	private TextField productNameTF=new TextField();
	private Label productPriceLabel=new Label("Price");
	private TextField productPriceTF=new TextField();
	private Label productQuantityLabel=new Label("Quantity");
	private TextField productQuantityTF=new TextField();
	private Button addProductBT=new Button("Add Product");
	//removing products
	private Label removeProductLabel=new Label("Remove product");
	private Label productName1=new Label("Product name");
	private TextField productName1TF=new TextField();
	private Button removeProductBT=new Button("Remove Product");
	
	HBox pane=new HBox();
	VBox addRemovePane=new VBox();
	GridPane addPane=new GridPane();
	GridPane removePane=new GridPane();
	public InventoryUI() {
		
		pane.getChildren().addAll(createProductTable(), addRemovePane);
		addRemovePane.getChildren().addAll(addProductLabel, addPane, removeProductLabel, removePane);
		
		addPane.add(productNameLabel, 0, 0);
		addPane.add(productPriceLabel, 0, 1);
		addPane.add(productQuantityLabel, 0, 2);
		addPane.add(productNameTF, 1, 0);
		addPane.add(productPriceTF, 1, 1);
		addPane.add(productQuantityTF, 1, 2);
		addPane.add(addProductBT, 1, 3);
		
		removePane.add(productName1, 0, 0);
		removePane.add(productName1TF, 1, 0);
		removePane.add(removeProductBT, 1, 1);
		
		addPane.setVgap(5);
		addPane.setHgap(5);
		removePane.setVgap(5);
		removePane.setHgap(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setSpacing(5);
		
		addProductBT.setOnAction(e -> {
			Product newProduct=new Product();
		    newProduct.setName(productNameTF.getText());
		    int quantity = Integer.parseInt(productQuantityTF.getText());
		    newProduct.setQuantity(quantity);
		    Double price = Double.parseDouble(productPriceTF.getText());
		    newProduct.setPrice(price);

		    boolean productExists = false;

		    for (int i = 0; i < data.getInventory().size(); i++) {
		        if (productNameTF.getText().equals(data.getInventory().get(i).getName())) {
		            data.getInventory().get(i).setQuantity(quantity + data.getInventory().get(i).getQuantity());
		            data.writeDataToFile();
		            productExists = true;
		            break; 
		        }
		    }

		    if (!productExists) {
		        data.getInventory().add(newProduct);
		        data.writeDataToFile();
		    }

		    productNameTF.clear();
		    productQuantityTF.clear();
		    productPriceTF.clear();
		});
		
		removeProductBT.setOnAction(e->{
			for(int i=0;i<data.getInventory().size();i++) {
				if(productName1TF.getText().equals(data.getInventory().get(i).getName())) {
					data.getInventory().remove(i);
				}
			}
			productName1TF.clear();
		});
		
	}
	
	private TableView<Product> createProductTable() {
        TableView<Product> table=new TableView<>();
        table.setItems(productsData);

        TableColumn nameCol=new TableColumn("Name");
        TableColumn quantityCol=new TableColumn("Quantity");
        TableColumn priceCol=new TableColumn("Price");
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        table.getColumns().addAll(nameCol, quantityCol, priceCol);

        return table;
    }
	
	public HBox getInventoryPane() {
		return pane;
	}
}
