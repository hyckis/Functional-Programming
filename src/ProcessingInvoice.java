// Author: YICHIN HO
// 加分題sort: 可依照id/description/quantity/price來排序

import java.util.*;
import java.util.function.*;


public class ProcessingInvoice {
	
	public static void main(String[] args) {
		
		Invoice[] invoices = {
				new Invoice(83, "Electric sander", 7, 57.98),
				new Invoice(24, "Power saw", 18, 99.99),
				new Invoice(7, "Sledge hammer", 11, 21.50),
				new Invoice(77, "Hammer", 76, 11.99),
				new Invoice(39, "Lawn mower", 3, 79.50),
				new Invoice(68, "Screwdriver", 106, 6.99),
				new Invoice(56, "Jig saw", 21, 11.00),
				new Invoice(3, "Wrench", 34, 7.50),
				new Invoice(45, "Wrench", 13, 7.50),
				new Invoice(22, "Hammer", 47, 11.99)
		};	
		
		List<Invoice> list = Arrays.asList(invoices);	// 轉成list
		
		System.out.print("Welcome to invoices management system. \nFunctions: Report/Select/Sort \nChoice (-1 to exit): ");
		Scanner choice = new Scanner(System.in);
		String s = choice.nextLine();
		
		do {
			
			if (s.toLowerCase().equals("report")) {	// 單純列報表
				
				System.out.println("\nInvoices group by description: ");
				
				list.stream()
					.sorted(Comparator.comparing(i -> i.getPrice() * i.getQuantity()))
					.forEach(i -> System.out.printf("Description: %-15s  Invoice amount: %,10.2f%n", i.getPartDescription(), i.getPrice() * i.getQuantity()));
				
			} else if (s.toLowerCase().equals("select")) {	// 選擇顯示區間
				
				System.out.print("\nInput the range to show (min, max): ");
				
				try {
					
					String[] range = choice.nextLine().split(",");	// 用逗號分開2個數字
					int min = Integer.parseInt(range[0]);	// 第1個數字是min值
					int max = Integer.parseInt(range[1]);	// 第2個數字是max值
					
					// 判斷介於min與max之間的amount
					Predicate<Invoice> minToMax = i -> (i.getPrice() * i.getQuantity() >= min && i.getPrice() * i.getQuantity() <= max);
					
					System.out.printf("%nInvoices mapped to description and invoice amount for invoices in the range %d-%d%n", min, max);
					list.stream()
						.filter(minToMax)
						.sorted(Comparator.comparing(i -> i.getPrice() * i.getQuantity()))
						.forEach(i -> System.out.printf("Description: %-15s  Invoice amount: %,10.2f%n", i.getPartDescription(), i.getPrice() * i.getQuantity()));
				
				} catch (ArrayIndexOutOfBoundsException e) {	// 輸入錯誤: 只有1個數
					System.out.print("Wrong input. Enter again: ");					
				} catch (NumberFormatException e) {	// 輸入錯誤: 輸入的不是數字
					System.out.print("Wrong input. Enter again: ");
				}	
				
			} else if (s.toLowerCase().equals("sort")) {	//排列
				
				System.out.println();
				System.out.print("Sort by ID/Description/Quantity/Price: ");
				s = choice.nextLine();
				
				if (s.toLowerCase().equals("id")) {	// id排序
					
					System.out.println();
					System.out.println("Sorted by ID: ");
					
					list.stream()
						.sorted(Comparator.comparing(i -> i.getPartNumber()))
						.forEach(System.out::println);
					
				} else if (s.toLowerCase().equals("description")) {	// description(字母由前到後)排序
					
					System.out.println();
					System.out.println("Sorted by Description: ");
					
					Function<Invoice, String> byDescription = Invoice::getPartDescription;
					Comparator<Invoice> sort = Comparator.comparing(byDescription);
					
					list.stream()
						.sorted(sort)
						.forEach(System.out::println);
					
				} else if (s.toLowerCase().equals("quantity")) {	// quantity排序
					
					System.out.println();
					System.out.println("Sorted by Quantity: ");
					
					list.stream()
						.sorted(Comparator.comparing(i -> i.getQuantity()))
						.forEach(System.out::println);
					
				} else if (s.toLowerCase().equals("price")) {	// price排序
					
					System.out.println();
					System.out.println("Sorted by Price: ");
					
					list.stream()
						.sorted(Comparator.comparing(i -> i.getPrice()))
						.forEach(System.out::println);
					
				} else {	// 輸入錯誤
					System.out.print("Wrong input. Enter again: ");					
				}
				
			} else {	// 輸入錯誤
				System.out.print("Wrong input. Enter again: ");			
			}
			
			System.out.println();
			System.out.print("\nWelcome to invoices management system. \nFunctions: Report/Select/Sort \nChoice (-1 to exit): ");
			s = choice.nextLine();
			
		} while (!s.equals("-1"));	// 輸入-1時結束
		
	}
}
