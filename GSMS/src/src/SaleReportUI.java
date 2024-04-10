import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SaleReportUI {
	Data data=new Data();
	private ObservableList<Transaction> transactionData=FXCollections.observableArrayList();
	
	private Label totalLabel=new Label("Total");
	private TextField totalTF=new TextField();
	private HBox totalPane=new HBox();
	private VBox pane=new VBox();
	public SaleReportUI() {
		totalPane.getChildren().addAll(totalLabel, totalTF);
		pane.getChildren().addAll(createTransactionTable(), totalPane);
		
		totalPane.setSpacing(5);
		pane.setSpacing(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		
		setTotalRevenue();
	}
	private TableView<Transaction> createTransactionTable() {
        TableView<Transaction> table=new TableView<>();
        table.setItems(transactionData);

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
	
	private void setTotalRevenue() {
		for(int i=0; i<transactionData.size();i++) {
			double totalRev=transactionData.get(i).getTotal();
			totalTF.setText(String.valueOf(totalRev));
		}
	}
	
	public VBox getSaleReportPane() {
		return pane;
	}
	
}
