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
				if (number<1)	// ��J�t��
					JOptionPane.showMessageDialog(null, "Please enter positive interger", "Message", JOptionPane.ERROR_MESSAGE);
				HashSet<Integer> threeNums = new HashSet<Integer>();	// hashset

				for (int i=1; i<number+1; i++) {
					if (i % 3 == 0 || Integer.toString(i).indexOf('3') != -1)	// 3�����ƩάO�t��3
						threeNums.add(i);	// �[��hashset
				}

				if (!threeNums.isEmpty()) {	// hashset�̦��F��i�H�L
					// �Ƨ�
					List sortList = threeNums.stream()
							 				 .sorted()
							 				 .collect(Collectors.toList());
					// iterator
					Iterator sortedNums = sortList.iterator();

					// ��iterator�]�X�Ӫ��F��[�istring
					String result = "";
					while(sortedNums.hasNext()) {
						result += sortedNums.next().toString();
						result += "  ";
					}
					JOptionPane.showMessageDialog(null, result);
				} else if (threeNums.isEmpty() && number>0)	// �S�F��i�H�L
					JOptionPane.showMessageDialog(null, "Nothing here QQ");

			} catch (RuntimeException e) {	// �줣��]�����p

				if (num.equals(String.format("%d", JOptionPane.CANCEL_OPTION)) || num.equals(String.format("%d", JOptionPane.CLOSED_OPTION))) // ��X�Ψ���
					continueLoop = false;
				else	// ��J���~
					JOptionPane.showMessageDialog(null, "Please enter positive interger", "Message", JOptionPane.ERROR_MESSAGE);

			}

		} while (continueLoop);

	}

}
