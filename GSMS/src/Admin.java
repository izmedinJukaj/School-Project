import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin {
    private VBox pane=new VBox();
    CustomMenu menu=new CustomMenu();
    private InventoryUI inventoryUI=new InventoryUI();
    private UsersUI usersUI=new UsersUI();
    private CustomersUI customersUI=new CustomersUI();
    private POS_UI posUI=new POS_UI();
    private SaleReportUI saleReportUI=new SaleReportUI();
    LoginUI loginUI=new LoginUI();
    Data data=new Data();
    Product newProduct=new Product();
    Scene scene=new Scene(pane);
    Stage adminStage=new Stage();
    public Admin() {
    	adminStage.setWidth(690);
        menu.buildAdminMenuBar();
        setMenuHandlers();
        pane.getChildren().addAll(menu.getTopMenu(), usersUI.getUsersPane());
        adminStage.setTitle("Administrator");
        adminStage.setScene(scene);
    }

    public Stage createAdminStage() {
        return  adminStage;
    }
    
    private void setMenuHandlers() {
        menu.getProductsMenuItem().setOnAction(e -> {pane.getChildren().set(1, inventoryUI.getInventoryPane());});
        menu.getShowUsersMenuItem().setOnAction(e -> {pane.getChildren().set(1, usersUI.getUsersPane());});
        menu.getCustomersMenuItem().setOnAction(e -> {pane.getChildren().set(1, customersUI.getCustomersPane());});
        menu.getPointOfSaleMenuItem().setOnAction(e->{pane.getChildren().set(1, posUI.getPOSpane());});
        menu.getSalesMenuItem().setOnAction(e -> {pane.getChildren().set(1, saleReportUI.getSaleReportPane());});
    }
    
}
