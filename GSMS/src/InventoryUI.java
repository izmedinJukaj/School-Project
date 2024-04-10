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
	//modifying products
	private Label mdoifyProductLabel=new Label("Modify product");
	private Label productName2Label=new Label("Product name");
	private Label newQuantityLabel=new Label("New quantity");
	private Label newPriceLabel=new Label("New price");
	private TextField productName2TF=new TextField();
	private TextField newQuantityTF=new TextField();
	private TextField newPriceTF=new TextField();
	private Button modifyBT=new Button("Modify");

	TableView table=new TableView();
	HBox pane=new HBox();
	VBox addRemovePane=new VBox();
	GridPane modifyPane=new GridPane();
	GridPane addPane=new GridPane();
	GridPane removePane=new GridPane();
	public InventoryUI() {
		
		table=createProductTable();
		table.setPrefWidth(185);
		pane.getChildren().addAll(table, addRemovePane);
		addRemovePane.getChildren().addAll(addProductLabel, addPane, removeProductLabel, removePane, mdoifyProductLabel, modifyPane);
		
		modifyPane.add(productName2Label, 0, 0);
		modifyPane.add(productName2TF, 1, 0);
		modifyPane.add(newQuantityLabel, 0, 1);
		modifyPane.add(newQuantityTF, 1, 1);
		modifyPane.add(newPriceLabel, 0, 2);
		modifyPane.add(newPriceTF, 1, 2);
		modifyPane.add(modifyBT, 1, 3);
		
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
		
		modifyPane.setHgap(5);
		modifyPane.setVgap(5);
		addPane.setVgap(5);
		addPane.setHgap(5);
		removePane.setVgap(5);
		removePane.setHgap(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setSpacing(15);
		
		addProductBT.setOnAction(e -> {
			Product newProduct=new Product();
		    newProduct.setName(productNameTF.getText());
		    int quantity = Integer.parseInt(productQuantityTF.getText());
		    newProduct.setQuantity(quantity);
		    Double price = Double.parseDouble(productPriceTF.getText());
		    newProduct.setPrice(price);

		    boolean productExists = false;

		    for (int i = 0; i < productsData.size(); i++) {
		        if (productNameTF.getText().equals(productsData.get(i).getName())) {
		            productsData.get(i).setQuantity(quantity + productsData.get(i).getQuantity());
		            data.getInventory().get(i).setQuantity(quantity+data.getInventory().get(i).getQuantity());
		            data.writeDataToFile();
		            productExists = true;
		            break; 
		        }
		    }

		    if (!productExists) {
		        productsData.add(newProduct);
		        data.getInventory().add(newProduct);
		        data.writeDataToFile();
		    }

		    productNameTF.clear();
		    productQuantityTF.clear();
		    productPriceTF.clear();
		});
		
		removeProductBT.setOnAction(e->{
			for(int i=0;i<productsData.size();i++) {
				if(productName1TF.getText().equals(productsData.get(i).getName())) {
					productsData.remove(i);
					data.getInventory().remove(i);
					data.writeDataToFile();
				}
			}
			productName1TF.clear();
		});
	
		modifyBT.setOnAction(e -> {
		        int newQuantity = newQuantityTF.getText().isEmpty() ? 0 : Integer.parseInt(newQuantityTF.getText());
		        double newPrice = newPriceTF.getText().isEmpty() ? 0.0 : Double.parseDouble(newPriceTF.getText());

		        for (int i = 0; i < productsData.size(); i++) {
		            if (productName2TF.getText().equals(productsData.get(i).getName())) {
		                if (newQuantityTF.getText().isEmpty()) {
		                    // Update only the price
		                    productsData.get(i).setPrice(newPrice);
		                    data.getInventory().get(i).setPrice(newPrice);
		                } else if (newPriceTF.getText().isEmpty()) {
		                    // Update only the quantity
		                    productsData.get(i).setQuantity(newQuantity);
		                    data.getInventory().get(i).setQuantity(newQuantity);
		                } else {
		                    // Update both quantity and price
		                    productsData.get(i).setQuantity(newQuantity);
		                    data.getInventory().get(i).setQuantity(newQuantity);
		                    productsData.get(i).setPrice(newPrice);
		                    data.getInventory().get(i).setPrice(newPrice);
		                }
		                table.refresh();
		            }
		        }
		        productName2TF.clear();
		        newQuantityTF.clear();
		        newPriceTF.clear();
		        data.writeDataToFile();
		});
		
	}
	
	public TableView<Product> createProductTable() {
        TableView<Product> table=new TableView<>();
        table.setItems(productsData);

        TableColumn nameCol=new TableColumn("Name");
        TableColumn quantityCol=new TableColumn("Quantity");
        TableColumn priceCol=new TableColumn("Price");
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        table.getColumns().addAll(nameCol, quantityCol, priceCol);

        return table;
    }
	public TableView<Product> getTable(){
		return table;
	}
	
	public ObservableList<Product> getProductsData(){
		return productsData;
	}
	public HBox getInventoryPane() {
		return pane;
	}
}
