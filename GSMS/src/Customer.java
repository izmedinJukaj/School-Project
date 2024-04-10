import java.io.Serializable;

public class Customer implements Serializable {
	private String name;
	private String lastName;
	private String phone;
	private Double points;
	
	public Customer() {
	}
	public Customer(String name, String lastName, String phone) {
		this.name=name;
		this.lastName=lastName;
		this.phone=phone;
		this.points=0.0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public Double getPoints() {
        return this.points != null ? this.points.doubleValue() : 0.0;
    }
	
	public void setPoints(double points) {
		this.points=points;
	}
}
