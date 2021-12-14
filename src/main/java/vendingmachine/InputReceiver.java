package vendingmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import camp.nextstep.edu.missionutils.Console;

public class InputReceiver {
	public static int getNumber(){
		String number;

		//VendingMachinePrinter.printGetAmountMessage(messageType);

		while (true) {
			try {
				number = Console.readLine();
				InputValidator.validateNumber(number);
				return Integer.parseInt(number);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				//VendingMachinePrinter.printGetAmountMessage(messageType);
			}
		}
	}

	public static HashMap<String, Product> getProductInfo(){
		String productInfo;

		//VendingMachinePrinter.printGetProductInfoMessage();

		while (true) {
			try {
				productInfo = Console.readLine();
				//InputValidator.validateProduct(productInfo);
				return parseProductInfo(parseEachProduct(productInfo));
			} catch (NumberFormatException e) {
				System.out.println("[ERROR] 가격과 수량은 숫자여야 합니다.");
				//VendingMachinePrinter.printGetVendingMachineInfoMessage();
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				//VendingMachinePrinter.printGetVendingMachineInfoMessage();
			}
		}
	}

	public static ArrayList<String> parseEachProduct(String productInfo){
		String[] tempProducts = productInfo.split(";");
		ArrayList<String> products = new ArrayList<String>(Arrays.asList(tempProducts));

		return products;
	}

	public static HashMap<String, Product> parseProductInfo(ArrayList<String> product){
		HashMap<String, Product> products = null;

		for(String temp : product){
			String[] tempArray = temp.split(",");
			tempArray[0] = tempArray[0].replace("[","");
			tempArray[2] = tempArray[2].replace("]","");

			int price = Integer.parseInt(tempArray[1]);
			InputValidator.validatePrice(price);
			int quantity = Integer.parseInt(tempArray[2]);
			Product tempProduct = new Product(tempArray[0], price, quantity);

			products.put(tempArray[0], tempProduct);
		}

		return products;
	}

}
