import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UsersUI {
	Data data=new Data();
	
	private Label addUserLabel=new Label("Add new user");
	private Label usernameLabel=new Label("Username");
	private TextField usernameTF=new TextField();
	private Label passwordLabel=new Label("Password");
	private TextField passwordTF=new TextField();
	private RadioButton adminRB=new RadioButton("Admin");
	private RadioButton cashierRB=new RadioButton("Cashier");
	private Button addUserBT=new Button("Add User");
	
	private Label removeUserLabel=new Label("Remove user");
	private Label username1Label=new Label("Username");
	private TextField username1TF=new TextField();
	private Button removeUserBT=new Button("Remove user");
	
	private HBox pane=new HBox();
	private HBox radioButtonsPane=new HBox();
	private VBox addRemovePane=new VBox();
	private GridPane addPane=new GridPane();
	private GridPane removePane=new GridPane();
	private ToggleGroup group=new ToggleGroup();
	public UsersUI() {
		
		adminRB.setToggleGroup(group);
		cashierRB.setToggleGroup(group);
		pane.getChildren().addAll(createUserTable(), addRemovePane);
		addRemovePane.getChildren().addAll(addUserLabel, addPane, removeUserLabel, removePane);
		addPane.add(usernameLabel, 0, 0);
		addPane.add(passwordLabel, 0, 1);
		addPane.add(usernameTF, 1, 0);
		addPane.add(passwordTF, 1, 1);
		addPane.add(radioButtonsPane, 1, 2);
		addPane.add(addUserBT, 1, 3);
		radioButtonsPane.getChildren().addAll(adminRB, cashierRB);
		removePane.add(username1Label, 0, 0);
		removePane.add(username1TF, 1, 0);
		removePane.add(removeUserBT, 1, 1);
		
		addPane.setHgap(5);
		addPane.setVgap(5);
		removePane.setHgap(5);
		removePane.setVgap(5);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setSpacing(5);
		
		addUserBT.setOnAction(e->{
			User newUser=new User();
			newUser.setUsername(usernameTF.getText());
			newUser.setPassword(passwordTF.getText());
			if(adminRB.isSelected()) {
				newUser.setRank("admin");
			}
			else if(cashierRB.isSelected()) {
				newUser.setRank("cashier");
			}
			data.getUsers().add(newUser);
			data.writeDataToFile();
			usernameTF.clear();
			passwordTF.clear();
		});
		
		removeUserBT.setOnAction(e->{
			for(int i=0;i<data.getUsers().size();i++) {
				if(username1TF.getText().equals(data.getUsers().get(i).getUsername())) {
					data.getUsers().remove(i);
					data.writeDataToFile();
				}
			}
			username1TF.clear();
		});
	}
	private ObservableList<User> usersData=FXCollections.observableArrayList(data.getUsers());
	private TableView<User> createUserTable() {
        TableView<User> table=new TableView<>();
        table.setItems(usersData);

        TableColumn usernameCol=new TableColumn("Username");
        TableColumn passwordCol=new TableColumn("Password");
        TableColumn rankCol=new TableColumn("Rank");
        
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("rank"));
        

        table.getColumns().addAll(usernameCol, passwordCol, rankCol);

        return table;
    }
	public HBox getUsersPane() {
		return pane;
	}
}
