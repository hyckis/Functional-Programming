// Author: YICHIN HO

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[]args) {

		boolean continueLoop = true;

		do {
			String num = JOptionPane.showInputDialog(null, "Please enter a positive integer: ");

			try {
				int number = Integer.parseInt(num);
				if (number<1)	// 輸入負數
					JOptionPane.showMessageDialog(null, "Please enter positive interger", "Message", JOptionPane.ERROR_MESSAGE);
				HashSet<Integer> threeNums = new HashSet<Integer>();	// hashset

				for (int i=1; i<number+1; i++) {
					if (i % 3 == 0 || Integer.toString(i).indexOf('3') != -1)	// 3的倍數或是含有3
						threeNums.add(i);	// 加到hashset
				}

				if (!threeNums.isEmpty()) {	// hashset裡有東西可以印
					// 排序
					List sortList = threeNums.stream()
							 				 .sorted()
							 				 .collect(Collectors.toList());
					// iterator
					Iterator sortedNums = sortList.iterator();

					// 把iterator跑出來的東西加進string
					String result = "";
					while(sortedNums.hasNext()) {
						result += sortedNums.next().toString();
						result += "  ";
					}
					JOptionPane.showMessageDialog(null, result);
				} else if (threeNums.isEmpty() && number>0)	// 沒東西可以印
					JOptionPane.showMessageDialog(null, "Nothing here QQ");

			} catch (RuntimeException e) {	// 抓不能跑的情況

				if (num.equals(String.format("%d", JOptionPane.CANCEL_OPTION)) || num.equals(String.format("%d", JOptionPane.CLOSED_OPTION))) // 按X或取消
					continueLoop = false;
				else	// 輸入錯誤
					JOptionPane.showMessageDialog(null, "Please enter positive interger", "Message", JOptionPane.ERROR_MESSAGE);

			}

		} while (continueLoop);

	}

}
