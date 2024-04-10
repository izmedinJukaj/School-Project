import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	User tempUser;
	LoginUI loginUI=new LoginUI();
	Admin admin=new Admin();
	Cashier cashier=new Cashier();
    Data data=new Data();
	
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
            loginUI.getLoginButton().setOnAction(e -> {
                boolean loginSuccessful = false;
                for (User user : data.getUsers()) {
                    if (loginUI.getUsernameTF().getText().equals(user.getUsername())
                            && loginUI.getPasswordTF().getText().equals(user.getPassword())) {
                        loginSuccessful = true;
                        tempUser=user;
                        break;
                    }
                }
                if (loginSuccessful) {
                    loginUI.getUsernameTF().clear();
                    loginUI.getPasswordTF().clear();
                    if(tempUser.getRank().equals("admin")) {
                    	admin.createAdminStage().show();
                    	primaryStage.close();
                    }
                    else {
                    	cashier.createCashierStage().show();
                    	primaryStage.close();
                    }
                } else {
                    loginUI.getSuccessLabel().setText("Wrong password or username!");
                }
            });
            
            admin.menu.getLogOutMenuItem().setOnAction(e->{
            	admin.createAdminStage().close();
            	primaryStage.show();
            });
            
            cashier.menu.getLogOutMenuItem().setOnAction(e->{
            	cashier.createCashierStage().close();
            	primaryStage.show();
            });
        primaryStage.setTitle("Grocery Store Management System");
        primaryStage.setScene(loginUI.createLoginScene());
        primaryStage.show();
        
    }
}