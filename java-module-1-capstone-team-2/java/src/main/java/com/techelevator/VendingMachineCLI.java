package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         

public class VendingMachineCLI  {

	/*******************************************************************************************
	* Instance Variables
	******************************************************************************************/
	
    private static BigDecimal balance = BigDecimal.valueOf(0); 
    private static Map<String, Slot> inventorySlots = new LinkedHashMap<String, Slot>();
    private static BigDecimal feedMoney = BigDecimal.valueOf(0);
    private static String name;
    private static String selectProduct;
    private static BigDecimal change = BigDecimal.valueOf(0);
    private static BigDecimal totalSales = BigDecimal.valueOf(0);
    private static LocalDateTime currentTime;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
  
    
    private File auditFile = new File("Log.txt");
    private PrintWriter auditFileWriter = new PrintWriter(auditFile);
    
//    private static Scanner theKeyboard = new Scanner(System.in);
    

	/*******************************************************************************************
	* Menu Display
	******************************************************************************************/
    
    
	private static final String   MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String   MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String   MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String   MAIN_MENU_OPTION_SALES_REPORT  = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT,
													    MAIN_MENU_OPTION_SALES_REPORT
													    };
	
	private static final String   PURCHASE_MENU_OPTION_FEED_MONEY 			= "Feed Money";
	private static final String   PURCHASE_MENU_OPTION_SELECT_PRODUCT      	= "Select Product";
	private static final String   PURCHASE_MENU_OPTION_FINISH_TRANSACTION   = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
															PURCHASE_MENU_OPTION_SELECT_PRODUCT, 
															PURCHASE_MENU_OPTION_FINISH_TRANSACTION
													    };

	/*******************************************************************************************
	* Constructor
	******************************************************************************************/

	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) throws FileNotFoundException {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;		// Make the Menu the user object passed, our Menu
		reStock();
		fileWriter();
	}
	
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	* @throws FileNotFoundException 
	*
	***************************************************************************************************************************/

	public void run() {

		boolean shouldProcess = true;        			  // Loop control variable
		
		while(shouldProcess) {          			      // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                 			  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();       	    		  // invoke method to display items in Vending Machine
					break;                  			  // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();        			  // invoke method to purchase items from Vending Machine
					break;                    		      // Exit switch statement
					
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();   			  // Invoke method to perform end of method processing
					shouldProcess = false;   			  // Set variable to end loop
					break;                  			  // Exit switch statement
					
				case MAIN_MENU_OPTION_SALES_REPORT:
					SalesReportWriter();			   	  // Invoke method to write the Sales Report
					break;
			}	
		}
		return;                             			  // End method and return to caller
	}
	
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	
	/*******************************************************************************************
	 * Re-stock Method
	 * @throws FileNotFoundException 
	 ******************************************************************************************/
	
	public void reStock() throws FileNotFoundException {

		String fileName = "vendingmachine.csv";

		File vendingMachineFile = new File(fileName);

		Scanner vendingMachineFileReader = new Scanner(vendingMachineFile);

		while (vendingMachineFileReader.hasNextLine()) {
			String fileLine = vendingMachineFileReader.nextLine();

			String[] snackData = fileLine.split("\\|");
			
			BigDecimal snackPrice = new BigDecimal(snackData[2]);

			Slot slotObject = new Slot(snackData[1], snackPrice, snackData[3], 5, 0);

			inventorySlots.put(snackData[0], slotObject);
		}
		vendingMachineFileReader.close();
	}
	
	/*******************************************************************************************
	 * Display Items Method
	 ******************************************************************************************/
	
	public void displayItems() {   
		
			System.out.println();
		
		for (String aSlot : inventorySlots.keySet()) {

			
			System.out.print(String.format("%-10s",aSlot));
			System.out.print(String.format("%-25s", inventorySlots.get(aSlot).getName()));
			System.out.print(String.format("%-10s",inventorySlots.get(aSlot).getPrice()));
			System.out.print(String.format("%-5s",inventorySlots.get(aSlot).getQuantity()));
			System.out.println();
		}
	}
	
	/********************************************************************************************************
	 * Purchase Items Method
	 ********************************************************************************************************/
	
	public void purchaseItems() {

		boolean shouldProcess = true;

		while (shouldProcess) {
			
			String choice = (String) vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			switch (choice) {

			case PURCHASE_MENU_OPTION_FEED_MONEY:
				System.out.println();
				System.out.println("Please select one of the following dollar bills to input:   1 , 2 , 5 , 10");
				feedMoney();
				System.out.println();
				System.out.println("Current Money Provided: $" + balance);
				break;

			case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
				displayItems();
				selectProduct();				
				break;

			case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
				returnChange();
				shouldProcess = false;
				break;
			}
		}
		return;
	}
	
	/********************************************************************************************************
	 * End Processing Method
	 ********************************************************************************************************/
	
	public void endMethodProcessing() { 
		
		System.out.println();
		System.out.println("Thank you for using Umbrella Corp's Vendo-O-Matic 6000. Our business is life itself. Have a good day.");
		auditFileWriter.close();
//		theKeyboard.close();
		
	}
	
	/********************************************************************************************************
	 * Return Change Method
	 ********************************************************************************************************/
	
	public void returnChange() {

		BigDecimal quarter = BigDecimal.valueOf(.25);
		BigDecimal dimes = BigDecimal.valueOf(.10);
		BigDecimal nickels = BigDecimal.valueOf(.05);

		BigDecimal qtrDiv[] = balance.divideAndRemainder(quarter);
		BigDecimal dimeDiv[] = qtrDiv[1].divideAndRemainder(dimes);
		BigDecimal nickelDiv[] = dimeDiv[1].divideAndRemainder(nickels);
		change = nickelDiv[1];

		currentTime = LocalDateTime.now();
		auditFileWriter.print(dtf.format(currentTime));
		auditFileWriter.print(String.format("%-28s", " GIVE CHANGE:"));
		auditFileWriter.print(String.format("%-7s", "$" + balance));
		auditFileWriter.print(" $" + change);
		auditFileWriter.println();
		balance = change;

		System.out.println();
		System.out.print("Change returned: " + qtrDiv[0].setScale(0) + " quarter(s) ");
		System.out.print(dimeDiv[0].setScale(0) + " dime(s) ");
		System.out.println(nickelDiv[0].setScale(0) + " nickle(s) ");
		System.out.println("Remaining Balance: $" + balance);
	}

	/********************************************************************************************************
	 * Feed Money Method
	 ********************************************************************************************************/
	
	public void feedMoney() {

		Scanner theKeyboard = new Scanner(System.in);
		feedMoney = BigDecimal.valueOf(theKeyboard.nextInt());

		BigDecimal one = BigDecimal.valueOf(1);
		BigDecimal two = BigDecimal.valueOf(2);
		BigDecimal five = BigDecimal.valueOf(5);
		BigDecimal ten = BigDecimal.valueOf(10);

		if (feedMoney.equals(one) || feedMoney.equals(two) || feedMoney.equals(five) || feedMoney.equals(ten)) {
			balance = balance.add(feedMoney);

			currentTime = LocalDateTime.now();
			auditFileWriter.print(dtf.format(currentTime));
			auditFileWriter.print(String.format("%-28s", " FEED MONEY: "));
			auditFileWriter.print(String.format("%-7s", "$" + feedMoney));
			auditFileWriter.print(" $" + balance);
			auditFileWriter.println();
			
		} else {
			System.out.println("*** " + feedMoney + " is not a valid bill input ***");
		}
	}
	
	/********************************************************************************************************
	 * Select Product Method
	 ********************************************************************************************************/
	
	public void selectProduct() {

		Scanner theKeyboard = new Scanner(System.in);

		System.out.println();
		System.out.println("Please enter the 2-digit alphanumeric code for the product you would like to purchase");
		selectProduct = theKeyboard.nextLine();

		if (inventorySlots.keySet().contains(selectProduct)) {
			name = inventorySlots.get(selectProduct).getName();

			BigDecimal price = inventorySlots.get(selectProduct).getPrice();
			int quantity = inventorySlots.get(selectProduct).getQuantity();
			int sold = inventorySlots.get(selectProduct).getSold();

			if (balance.compareTo(price) == 0 || balance.compareTo(price) == 1) {

				if (quantity > 0) {

					change = balance.subtract(price);

					currentTime = LocalDateTime.now();
					auditFileWriter.print(dtf.format(currentTime));
					auditFileWriter.print(String.format("%-25s", " " + name));
					auditFileWriter.print(selectProduct + " ");
					auditFileWriter.print(String.format("%-7s", "$" + balance));
					auditFileWriter.print(" $" + change);
					auditFileWriter.println();
					balance = change;

					inventorySlots.get(selectProduct).setQuantity(quantity - 1);
					inventorySlots.get(selectProduct).setSold(sold + 1);

					totalSales = totalSales.add(price);

					System.out.println();
					System.out.println("Successfully purchased " + name + " for $" + price + " and "
							+ inventorySlots.get(selectProduct).getQuantity() + " remain");
					System.out.println("Current balance: $" + balance);

					if (inventorySlots.get(selectProduct).getType().equals("Chip")) {
						System.out.println("Crunch Crunch, Yum! ");
					}
					if (inventorySlots.get(selectProduct).getType().equals("Candy")) {
						System.out.println("Munch Munch, Yum! ");
					}
					if (inventorySlots.get(selectProduct).getType().equals("Drink")) {
						System.out.println("Glug Glug, Yum! ");
					}
					if (inventorySlots.get(selectProduct).getType().equals("Gum")) {
						System.out.println("Chew Chew, Yum! ");
					}
				} else {
					System.out.println();
					System.out.println("SOLD OUT");
					System.out.println("Current remaining balance: $" + balance);
				}
			} else {
				System.out.println();
				System.out.println("Insufficient funds.");
				System.out.println("Current balance: $" + balance);
			}

		} else {
			System.out.println("*** " + selectProduct + " is not a valid product code ***");
		}
	}
	
	/********************************************************************************************************
	 * Audit File Writer Method
	 ********************************************************************************************************/
	
	public void fileWriter() {
		
		File auditFile = new File("Log.txt");
		try {
			auditFile.createNewFile();
		} catch (IOException exceptionObject) {
			exceptionObject.getMessage();
		}		
	}
	
	/********************************************************************************************************
	 * Sales Report Writer Method
	 ********************************************************************************************************/
	
	public void SalesReportWriter() {
		
		currentTime = LocalDateTime.now();		
		String fileName = dtf.format(currentTime).replace("/", "-").replace(" ", "_").replace(":", "-");
		fileName += "_Sales_Report.txt";
		
		System.out.println(fileName);
		
		File salesReportFile = new File(fileName);
		try {
			salesReportFile.createNewFile();
		} catch (IOException exceptionObject) {
			exceptionObject.getMessage();
		}	
		
		try {
			PrintWriter salesReportFileWriter = new PrintWriter(salesReportFile);

			for (String aSlot : inventorySlots.keySet()) {

				salesReportFileWriter.println(inventorySlots.get(aSlot).getName() + "|" + inventorySlots.get(aSlot).getSold());
			}
			salesReportFileWriter.println();
			salesReportFileWriter.println("Total Sales: $" + totalSales);
			salesReportFileWriter.close();

		} catch (FileNotFoundException exceptionObject) {
			exceptionObject.getMessage();
		}
	}
}
