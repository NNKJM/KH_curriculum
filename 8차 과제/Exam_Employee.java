package abstracts;

public abstract class Exam_Employee {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	abstract int getPays();

}
