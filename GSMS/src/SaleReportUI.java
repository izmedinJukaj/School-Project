import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SaleReportUI {
	Data data=new Data();
	private ObservableList<Transaction> transactionHistory=FXCollections.observableArrayList(data.getSales());
	
	private Label totalLabel=new Label("Total");
	private TextField totalTF=new TextField();
	private Button clearBT=new Button("Clear");
	private HBox totalPane=new HBox();
	private VBox pane=new VBox();
	private TableView table=new TableView();
	
	private HBox box=new HBox();
	public SaleReportUI() {
		table=createTransactionTable();
		table.setPrefWidth(230);
		totalPane.getChildren().addAll(totalLabel, totalTF, clearBT);
		pane.getChildren().addAll(table, totalPane);
		box.getChildren().addAll(pane);		
		totalPane.setSpacing(5);
		pane.setSpacing(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		
		clearBT.setOnAction(e->{
			totalTF.clear();
			data.getSales().clear();
			transactionHistory.clear();
			table.refresh();
		});
		
		setTotalRevenue();
	}
	public TableView<Transaction> createTransactionTable() {
        TableView<Transaction> table=new TableView<>();
        table.setItems((ObservableList<Transaction>) transactionHistory);
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
	
	private void setTotalRevenue() {
		double total=0.0;
		for(int i=0; i<transactionHistory.size();i++) {
			total=total+transactionHistory.get(i).getTotal();
			totalTF.setText(String.valueOf(total));
		}
	}
	
	public ObservableList<Transaction> getTransactionHistory(){
		return transactionHistory;
	}
	public TableView<Transaction> getTable(){
		return table;
	}
	
	public HBox getSaleReportPane() {
		return box;
	}
	
}
