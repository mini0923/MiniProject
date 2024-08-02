package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Quiz1 {

	static int count = 1;
	
	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter("order.txt");
		PrintWriter pw = new PrintWriter(fw);
		Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.println("전화 상담 할당 방식을 선택하세요");		
				System.out.println("1: 상품 주문하기");
				System.out.println("2 : 전체 주문 이력 보기");
				System.out.println("3 : 고객별 주문 이력 보기");
				System.out.println("4 : 특정날짜에 들어온 주문이력 보기");
				System.out.println("5 : 끝내기");
				System.out.print("옵션을 선택하세요 : ");
				
				int num = scanner.nextInt();
				
				if (num == 1) {
					getNextOrder(pw, scanner);
					count++;
					continue;
				} 
				else if (num == 2) {
					getOrderHistory();
					continue;
				}
				else if (num == 3) {
					getOrderByCustomer(scanner);
					continue;
				} 
				else if (num == 4) {
					getSpecialHistory();
					continue;
				}
				else if (num == 5) {
					System.out.println("프로그램을 종료합니다");
					break;
				} 
				else {
					System.out.println("잘못된 숫자입니다. 1~5 까지 입력해주세요");
					continue;
				}
			}
			scanner.close();
			
	}
	
	public static void getNextOrder(PrintWriter pw, Scanner scanner) throws IOException {
	
		String name;
		String productName ;
		int amount ; 
		int price;

		System.out.print("고객명 : ");
		scanner.nextLine();
		name = scanner.nextLine();

		System.out.print("제품명 : ");
		productName = scanner.nextLine();
		
		System.out.print("제품의 수량: ");
		amount = scanner.nextInt();
		
		System.out.print("제품의 가격 : ");
		price = scanner.nextInt();
		
		LocalDateTime curDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = curDateTime.format(formatter);
		
		String str = String.format("주문번호 : %d, 고객명 : %s, 제품명 : %s, 제품의 수량 : %d, 제품의 가격 : %d, 주문일시 %s", count, name, productName, amount, price, formatDate);
		
		pw.println(str);
		
		pw.flush();
	}
	
	public static void getOrderHistory() throws IOException {
		FileReader fr = new FileReader("order.txt");
		BufferedReader br = new BufferedReader(fr);
		
		System.out.println("==================================주문이력==================================");
		
		while (true) {
			String str = br.readLine();	//한줄씩 읽기(readLine())
			if (str == null) {
				break;
			}
			System.out.println(str);
		}
		System.out.println("=========================================================================");

	}
	
	public static void getOrderByCustomer(Scanner scanner) throws IOException {
		FileReader fr = new FileReader("order.txt");
		BufferedReader br = new BufferedReader(fr);
		
		scanner.nextLine();
		System.out.println("고객명 : ");
		String name = scanner.nextLine();
		
		System.out.println("==================================고객별 주문이력==================================");

		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}
			
			String nameArr[] = str.split(",");
			for (int i = 0 ; i < nameArr.length; i++) {
				System.out.print(nameArr[i]);		
				System.out.println();
			}
			
			String nameArr2[] = nameArr[1].split(":");
			
			String totalAmount[] = nameArr[4].split(":");
			
			int totalPrice = 0;
			
			
			
			if (nameArr[1].equals(name)) {
				System.out.print("전체 주문 건수 : ");
				totalPrice = totalPrice +Integer.parseInt(totalAmount[1]); 
				System.out.println();
			}
			
			
			
			
			System.out.println(Arrays.toString(nameArr)); 
			
			System.out.println("=========================================================================");

			
		
			
			}

	}
	
	public static void getSpecialHistory() {
		
	}
}
