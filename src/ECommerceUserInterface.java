// Name: Saydur Rahman
// Student number: 501046782
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;


// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try
			{
			
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				boolean success = amazon.createCustomer(name, address);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
				
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
					ProductOrder ship_ord=null;
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					ship_ord=amazon.shipOrder(orderNumber);
					// Ship order to customer (see ECommerceSystem for the correct method to use
					if (ship_ord==null)
					{
						System.out.print(amazon.getErrorMessage());
					}
					else
					{
						ship_ord.print();
					}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";
				boolean cust_ord=true;
				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
					cust_ord=amazon.printOrderHistory(customerId);
					if (!cust_ord)
					{
						System.out.print(amazon.getErrorMessage());
					}
			}
			else if (action.equalsIgnoreCase("printcart"))
			{String customerId = "";
			System.out.print("Customer Id: ");
			if (scanner.hasNextLine())
				customerId = scanner.nextLine();
			amazon.printCart(customerId);
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";
				String order_id="";
				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
			  // Get customer Id from scanner
				
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				order_id=amazon.orderProduct(productId, customerId, "");
				// Print Order Number string returned from method in ECommerceSystem
				if(order_id==null)
				{
					System.out.print(amazon.getErrorMessage());
				}
				else
				{
					System.out.printf("Order #%3s",order_id);
					
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";
				String order_id="";
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format  and store in options string
				if (scanner.hasNextLine())
					options = scanner.nextLine();
				
				// Order product. Check for error message set in ECommerceSystem
				// Print order number string if order number is not null
				order_id=amazon.orderProduct(productId, customerId, options);
				// Print Order Number string returned from method in ECommerceSystem
				if(order_id==null)
				{
					System.out.print(amazon.getErrorMessage());
				}
				else
				{
					System.out.printf("Order #%3s",order_id);
					
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				String order_id="";
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if (scanner.hasNextLine())
				{
					options=scanner.nextLine();
				}
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
				{
					options=options+" "+scanner.nextLine();
				}
				
				//order shoes
				order_id=amazon.orderProduct(productId, customerId, options);
				// Print Order Number string returned from method in ECommerceSystem
				if(order_id==null)
				{
					System.out.print(amazon.getErrorMessage());
				}
				else
				{
					System.out.printf("Order #%3s",order_id);
					
				}
			}
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";
				boolean success;
				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				// cancel order. Check for error
				success=amazon.cancelOrder(orderNumber);
				if(!success)
				{
					System.out.print(amazon.getErrorMessage());
				}
			}
			else if(action.equalsIgnoreCase("BOOKSBYAUTHOR"))
			{
				String Author="";
				System.out.print("Author Name: ");
				//get Author name from scanner
				if(scanner.hasNextLine()){
					Author=scanner.nextLine();
				}
				//list books by Author and check for error
				boolean success;
				success=amazon.BooksByAuthor(Author);
				if(!success)
				{
					System.out.print(amazon.getErrorMessage());
				}
			}
			else if(action.equalsIgnoreCase("addtocart"))
			{String productId = "";
			String customerId = "";
			String options = "";
				
				
					
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
					}
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();}
					System.out.print("\nOptions: ");
					if (scanner.hasNextLine()) {
						options=scanner.nextLine();}
					
					amazon.addtocart(productId, customerId, options);
					
					
				
			}
			else if(action.equalsIgnoreCase("remcartitem"))
					{String productId = "";
					String customerId = "";
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
					}
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();}
					amazon.removeCart(productId, customerId);
				
					}
			
			else if(action.equalsIgnoreCase("orderitems"))
			{String customerId = "";
			
			System.out.print("\nCustomer Id: ");
			
			if (scanner.hasNextLine())
			{
				customerId = scanner.nextLine();
						}
			amazon.ordercartitems(customerId);
			}
			
			else if(action.equalsIgnoreCase("rate"))
			{String customerId = "";
			String productId = "";
			System.out.print("\nCustomer Id: ");
			
			if (scanner.hasNextLine())
			{
				customerId = scanner.nextLine();
						}
			System.out.print("\nProduct Id: ");
			if (scanner.hasNextLine())
			{
				productId = scanner.nextLine();
			}
			double rating=0;
			System.out.print("\nRate on a scale oof 1 to 5: ");
			if (scanner.hasNextDouble())
			{
				rating = scanner.nextDouble();
			}
			amazon.addRating(customerId, productId, rating);
			}
			else if(action.equalsIgnoreCase("PrintAverageRating"))
			{
				String productId = "";
				System.out.print("\nProduct Id: ");
				if (scanner.hasNextLine())
				{
					productId = scanner.nextLine();
				}
				amazon.PrintRating(productId);
			}
			else if(action.equalsIgnoreCase("PrintRatingbyproducts"))
			{
				String producttype = "";
				System.out.print("\nProduct type(Any,Book or Shoe): ");
				if (scanner.hasNextLine())
				{
					producttype = scanner.nextLine();
				}
				double rating=0;
				System.out.print("\nWhat should be the minimum rating on a scale of 1 to 5: ");
				if (scanner.hasNextDouble())
				{
					rating = scanner.nextDouble();
				}
				amazon.printratingbyprods(producttype, rating);
			}
			
			else if(action.equalsIgnoreCase("STATS"))
			{
				amazon.OrderStats();
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.printByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.printByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			System.out.print("\n>");
			
		}
			catch(ECommerceSystem.UnknownCustomerException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.UnknownProductException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.UnknownOptionsException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.OutofStockException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.CustomerAddressException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.CustomerNameException e)
			{
				System.out.println(e.getMessage());
			}
			catch(ECommerceSystem.OrderNumException e)
			{
				System.out.println(e.getMessage());
			}
			catch(RuntimeException e)
			{
				System.out.println(e.getMessage());
			}
			
	}
}
}