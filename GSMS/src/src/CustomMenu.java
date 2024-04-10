import javafx.scene.control.*;

public class CustomMenu {

    private MenuBar topMenu=new MenuBar();
    private Menu inventory=new Menu("Inventory");
    private MenuItem products=new MenuItem("Products");
    private Menu admin=new Menu("Admin");
    private MenuItem showUsers=new MenuItem("Users");
    private MenuItem customers=new MenuItem("Customers");
    private Menu cashier=new Menu("Cashier");
    private MenuItem pointOfSale=new MenuItem("POS");
    private MenuItem sales=new MenuItem("Sales");
    private Menu moreMenu=new Menu("More");
    private MenuItem logOut=new MenuItem("Log out");
    public CustomMenu() {
    }
    protected void buildAdminMenuBar() {
        topMenu.getMenus().addAll(cashier, inventory, admin, moreMenu);
        inventory.getItems().addAll(products);
        admin.getItems().addAll(showUsers, customers);
        cashier.getItems().addAll(pointOfSale, sales);
        moreMenu.getItems().addAll(logOut);
    }
    protected void buildCashierMenuBar() {
        topMenu.getMenus().addAll(cashier, inventory, moreMenu);
        inventory.getItems().addAll(products);
        cashier.getItems().addAll(pointOfSale, sales);
        moreMenu.getItems().addAll(logOut);
    }
    
    public MenuBar getTopMenu() {
        return topMenu;
    }

    public MenuItem getProductsMenuItem() {
        return products;
    }

    public MenuItem getShowUsersMenuItem() {
        return showUsers;
    }

    public MenuItem getCustomersMenuItem() {
        return customers;
    }

    public MenuItem getPointOfSaleMenuItem() {
        return pointOfSale;
    }

    public MenuItem getSalesMenuItem() {
        return sales;
    }

    public MenuItem getLogOutMenuItem() {
        return logOut;
    }

}
