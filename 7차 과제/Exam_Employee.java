package overriding;

public class Exam_Employee {
	
    String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    int getPay() {
		return 0;
	}

}
