import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String rank;
	
	//Constructors
	public User() {
	}
	public User(String username, String password, String rank) {
		this.username=username;
		this.password=password;
		this.rank=rank;
	}
	
	//Getters & setters
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank=rank;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}

	//To String
	public String toString() {
		return "Username "+this.username+" | Rank "+this.rank;
	}
}
