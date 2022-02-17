package abstracts;

public class Exam_Permanent1 extends Exam_Employee {
	
	private int salary;
	private int bonus;
	
	public Exam_Permanent1() { }
	public Exam_Permanent1(String name, int salary, int bonus) {
		this.name = name;
		this.salary = salary;
		this.bonus = bonus;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	int getPays() {
		return salary + bonus;
	}

}
