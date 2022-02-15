package overriding;

public class Exam_Permanent extends Exam_Employee {
	private int pay;
	private int bonus;
	
	public Exam_Permanent() { }
	public Exam_Permanent(String name, int pay,int bonus) {
		this.name = name;
		this.pay = pay;
		this.bonus = bonus;
	}
	@Override
	int getPay() {
		return pay + bonus;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
}
