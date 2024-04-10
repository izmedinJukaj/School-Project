import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI {
    private VBox pane=new VBox(10);
    private GridPane gridPane=new GridPane();
    private HBox hBoxPane=new HBox(10);

    private Label successLabel=new Label("");
    private Label usernameLabel=new Label("Username");
    private Label passwordLabel=new Label("Password");

    private Button loginButton=new Button("Login");

    private TextField usernameTF=new TextField();
    private PasswordField passwordTF=new PasswordField();
    
    private Scene scene=new Scene(pane);
    public LoginUI() {
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(usernameTF, 1, 0);
        gridPane.add(passwordTF, 1, 1);
        gridPane.add(loginButton, 1, 2);

        pane.getChildren().addAll(gridPane, successLabel);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        usernameTF.setAlignment(Pos.BASELINE_RIGHT);
        passwordTF.setAlignment(Pos.BASELINE_RIGHT);
        
    }
    
	public Scene createLoginScene() {
        return scene;
    }
    public Button getLoginButton() {
        return loginButton;
    }
    public TextField getUsernameTF() {
        return usernameTF;
    }
    public PasswordField getPasswordTF() {
        return passwordTF;
    }
    public Label getSuccessLabel() {
        return successLabel;
    }
}
