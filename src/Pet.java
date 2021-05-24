import java.io.Serializable;

public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	String name;
	int age;
	
	public Pet (String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public int getAge () {
		return this.age;
	}
	
	public void setAge (int age) {
		this.age = age;
	}
}
