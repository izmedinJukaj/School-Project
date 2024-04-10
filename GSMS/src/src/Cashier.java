import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cashier {
    private VBox pane = new VBox();
    CustomMenu menu = new CustomMenu();
    private InventoryUI inventoryUI = new InventoryUI();
    private LoginUI loginUI=new LoginUI();
    private POS_UI posUI=new POS_UI();
    private SaleReportUI saleReportUI=new SaleReportUI();
    Stage cashierStage=new Stage();
    Scene scene=new Scene(pane);
    public Cashier() {
        menu.buildCashierMenuBar();
        setMenuHandlers();
        pane.getChildren().addAll(menu.getTopMenu(), posUI.getPOSpane());
        cashierStage.setTitle("Cashier");
        cashierStage.setScene(scene);
    }

    public Stage createCashierStage() {
        return cashierStage;
    }

    private void setMenuHandlers() {
    	menu.getProductsMenuItem().setOnAction(e -> {pane.getChildren().set(1, inventoryUI.getInventoryPane());});
        menu.getPointOfSaleMenuItem().setOnAction(e->{pane.getChildren().set(1, posUI.getPOSpane());});
        menu.getSalesMenuItem().setOnAction(e -> {pane.getChildren().set(1, saleReportUI.getSaleReportPane());});
        menu.getProductsMenuItem().setOnAction(e -> {
            pane.getChildren().clear();
            pane.getChildren().addAll(menu.getTopMenu(), inventoryUI.getInventoryPane());
        });

    }

}

