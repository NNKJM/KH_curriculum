package Method;

import java.util.Scanner;

/* [처리 조건]
 *  1. 데이터 구조 : 이름(String), 학번(int), 학과(String), 전화번호 (String)
 *  2. 학생수 결정 : 키보드로 입력받는다.
 *  3. 메뉴 선택 : 다음 메뉴를 구성하여 프로그램을 관리한다.
 *  
 * [메뉴 구성]
 *  1. 학생 등록 : 키보드로 학생정보 입력 -> 배열저장
 *  2. 전체 출력 : 등록학생 전체출력
 *  3. 학생 조회 : 학번 입력 -> 이름, 학번, 학과, 전화번로 출력
 *  4. 정보 수정 : 학번 입력 -> 학과와 전화번호 수정
 *  5. 프로그램 종료 : 프로그램을 종료하시겠습니까? (Y/N) -> Y입력 종료, N입력 프로그램 반복
 *  6. 위 메뉴는 별도의 메서드로 정의해 놓고, 메뉴 선택 시 해당 메서드가 호출되어 실행하도록 하시오.
 */

public class Exam_01 {
	
	
	public static void saveStudent(String[] name, int[] id, String[] major, String[] addr, Scanner sc){
		for(int i=0; i<name.length; i++) {
			System.out.print((i+1)+"번째 학생의 이름을 입력하세요 : ");
			name[i] = sc.next();
			id[i] = 20220000 + i + 1;
			System.out.print((i+1)+"번째 학생의 학과를 입력하세요 : ");
			major[i] = sc.next();
			System.out.print((i+1)+"번째 학생의 전화번호를 입력하세요 : ");
			addr[i] = sc.next();
		}
	} // 1. 학생 등록
	
	public static void printOut(String[] name, int[] id, String[] major, String[] addr) {
		for(int i=0; i<name.length; i++) {
			System.out.println("=======================================");
			System.out.printf("학생 이름 : %s     학번 : %d     학과 : %s     전화번호 : %s\n", name[i], id[i], major[i], addr[i]);
		}
	} // 2. 전체 출력
	
	public static void viewStudent(String[] name, int[] id, String[] major, String[] addr, Scanner sc) {
		System.out.print("조회하실 학생의 학번을 입력하세요 : ");
		int viewSc = sc.nextInt();
		for(int i=0; i<name.length; i++) {
			if(viewSc == id[i]) {
				System.out.printf("학생 이름 : %s     학번 : %d     학과 : %s     전화번호 : %s\n", name[i], id[i], major[i], addr[i]);
				return;
			}
		}
		System.out.println("해당 학생은 존재하지 않습니다.");
	} // 3. 학생 조회
	
	public static void resetStudent(String[] name, int[] id, String[] major, String[] addr, Scanner sc) {
		System.out.print("조회하실 학생의 학번을 입력하세요 : ");
		int viewSc = sc.nextInt();
		for(int i=0; i<name.length; i++) {
			if(viewSc == id[i]) {
				System.out.print(name[i]+" 학생의 학과를 수정하세요 : ");
				major[i] = sc.next();
				System.out.print(name[i]+" 학생의 전화번호를 수정하세요 : ");
				addr[i] = sc.next();
				return;
			}
		}
		System.out.println("해당 학생은 존재하지 않습니다.");
	} // 4. 정보 수정
	public static String programExit(Scanner sc) {
		// 프로그램을 종료하시겠습니까? (Y/N) -> Y입력 종료, N입력 프로그램 반복
		while(true) {
			System.out.print("프로그램을 종료하시겠습니까? : ");
			String exitCode = sc.next();
			if(exitCode.equalsIgnoreCase("Y")) {
				return exitCode;
			} else if(exitCode.equalsIgnoreCase("N")) {
				return exitCode;
			} else {
				System.out.println("Y/N만 입력해주세요!");
			}
		}
	} // 5. 프로그램 종료
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("학생 수를 입력하세요. : ");
		int size = sc.nextInt(); // 처리조건 2
		
		String[] name = new String[size];
		int[] id = new int[size];
		String[] major = new String[size];
		String[] addr = new String[size]; // 처리조건 1
		

		String exitCode = "N";
		while(exitCode.equalsIgnoreCase("N")) {
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 학생 등록");
			System.out.println("2. 전체 출력");
			System.out.println("3. 학생 조회");
			System.out.println("4. 정보 수정");
			System.out.println("5. 프로그램 종료");
			System.out.print("사용하실 서비스를 고르세요 : ");
			String menu = sc.next(); // 메뉴 고르기
			switch (menu) {
			case "1" : saveStudent(name, id, major, addr, sc);
				break;
			case "2" : printOut(name, id, major, addr);
				break;
			case "3" : viewStudent(name, id, major, addr, sc);
		        break;
			case "4" : resetStudent(name, id, major, addr, sc);
		        break;
			case "5" : exitCode = programExit(sc);
		        break;
			default : System.out.println("그런 메뉴는 없습니다.");	 
			}
		}
	}
}
