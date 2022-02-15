package overriding;

public class Exam_Temporary extends Exam_Employee {
	private int pay;
	private int time;
	public Exam_Temporary() { }
	public Exam_Temporary(String name, int time, int pay) {
		this.name = name;
		this.time = time;
		this.pay = pay;
	}
	
	@Override
	int getPay() {
		return pay*time;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	

}
