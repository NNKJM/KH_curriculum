package abstracts;

public class Exam_Temporary1 extends Exam_Employee {
	
	private int time;
	private int pay;
	
	public Exam_Temporary1() { }
	public Exam_Temporary1(String name, int time, int pay) {
		this.name = name;
		this.time = time;
		this.pay = pay;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	
	@Override
	int getPays(){
		return time * pay;
	}
}
