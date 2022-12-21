// Name: Saydur Rahman
// Student number: 501046782
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.io.IOException;
import java.util.TreeMap;
import java.util.HashMap;
/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
   
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    Map<String,Product> products= new TreeMap<String,Product>();
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	
    	// Create some products. Notice how generateProductId() method is used
    	//products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    	//products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    	//products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney",2011));
    	//products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    	//products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    	//products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    	//products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast",2014));
    	//products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive",2019));
    	//products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney",2020));
    	//products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
    	//products.add(new shoes("Air Jordan 1", generateProductId(), 230.0, 0,10,9,8,7,6,5,4,3,2,"Nike","2020"));
    	//products.add(new shoes("Adidas Hyper Boast", generateProductId(), 150.0, 22,10,9,8,7,6,5,4,3,2,"Adidas","2022");
    	try
    	{//calling the file reader method to get product variables from file products.txt
    	products=filereader();
    	String id=generateProductId();
    	products.put(id, new shoes("Air Jordan 1", id, 230.0, 0,10,9,8,7,6,5,4,3,2,"Nike","2020"));
    	id=generateProductId();
    	products.put(id, new shoes("Adidas Hyper Boast", id, 150.0, 22,10,9,8,7,6,5,4,3,2,"Adidas","2022"));
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    	
    	}
    	catch(IOException e)//catching any io exception
    	{
    		System.out.println(e.getMessage());
    	}
    }
    private Map<String,Product> filereader() throws FileNotFoundException,IOException
    { Product pd= null;
    //so we are gonna scan products.txt file and use : and white as delimiter
    	Scanner in = new Scanner(new File("products.txt"));
        in.useDelimiter(":| ");
       ArrayList<String> x= new ArrayList<>();
       // we are going to scan each line and put on the array list x 
       int c=0;//c is line count variable
        while(in.hasNextLine())
        { 
       	String s=in.nextLine();
       	//so if s is any of the following we scan till line 4and add to x and break
       			 if(s.equalsIgnoreCase("COMPUTERS") || s.equalsIgnoreCase("clothing" )|| s.equalsIgnoreCase("furniture") || s.equalsIgnoreCase("general"))
       	    	 {
       	    		 x.add(s);
       	    		 c++;
       	    		 while(in.hasNextLine())
       	    		 {
       	    			 x.add(in.nextLine());
       	    			 c++;
       	    			 if(c==4)
       	    			 {
       	    				 c=0;
       	    				 break;
       	    			 }
       	    			 
       	    		 }
       	    	 }
       			 //if s is books after line 3 we split the line in terms of space and add them singly and after line 4 we split the line in terms of : and add them one by one to the list
       			 //
       			 else if(s.equalsIgnoreCase("books"))
       			 {
       				 x.add(s);
       				 c++;
       				 while(in.hasNextLine())
       				 {
       					 x.add(in.nextLine());
       					 c++;
       					 if (c==3)
       					 {
       						 String q= in.nextLine();
       						 String[] p=q.split(" ");
       						 for(String n: p)
       						 {
       							 x.add(n);
       						 }
       						 c++;
       					 }
       					 if(c==4)
       					 {
       						String  q=in.nextLine();
       						String[] p=q.split(":");
       						for(String w :p)
       						{
       							x.add(w);
       						}
       						c++;
       					 }
       					 if(c==5)
       					 {
       						 c=0;
       						 break;
       					 }
       				 }
       				 
       			 }
       			
        }
        // now we create a map and put each product by generating id
       Map<String,Product> prods= new TreeMap<String,Product>();
       int i=0;
       String id="";
       while(i<x.size())
       {// so for any product category other than book we take 4 index elements form the array list and for books we take 8 as books as 8 attributes
    	   if(x.get(i).equalsIgnoreCase("computers"))
    	   { id=generateProductId();
    	   pd=new Product(x.get(i+1),id,Double.parseDouble(x.get(i+2)),Integer.parseInt(x.get(i+3)),Product.Category.COMPUTERS);
    	   prods.put(id, pd);
    		  i=i+4; 
    	   }
    	   else if(x.get(i).equalsIgnoreCase("furniture"))
    	   { id=generateProductId();
    	   pd=new Product(x.get(i+1),id,Double.parseDouble(x.get(i+2)),Integer.parseInt(x.get(i+3)),Product.Category.FURNITURE);
    	   prods.put(id, pd);
    		  i=i+4; 
    	   }
    	   else if(x.get(i).equalsIgnoreCase("General"))
    	   { id=generateProductId();
    	   pd=new Product(x.get(i+1),id,Double.parseDouble(x.get(i+2)),Integer.parseInt(x.get(i+3)),Product.Category.GENERAL);
    	   prods.put(id, pd);
    		  i=i+4; 
    	   }
    	   else if(x.get(i).equalsIgnoreCase("clothing"))
    	   { id=generateProductId();
    	   pd=new Product(x.get(i+1),id,Double.parseDouble(x.get(i+2)),Integer.parseInt(x.get(i+3)),Product.Category.CLOTHING);
    	   prods.put(id, pd);
    		  i=i+4; 
    	   }
    	   else if(x.get(i).equalsIgnoreCase("books"))
    	   { id=generateProductId();
    	   pd=new Book(x.get(i+1),id,Double.parseDouble(x.get(i+2)),Integer.parseInt(x.get(i+3)),Integer.parseInt(x.get(i+4)),x.get(i+5),x.get(i+6),Integer.parseInt(x.get(i+7)));
    	   prods.put(id, pd);
    		  i=i+8; 
    	   }
       }
       
       
       return prods;
    }
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
    	for(String key: products.keySet())
    	{
    		products.get(key).print();
    	}
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
    	for(String key: products.keySet())
    	{
    		if(products.get(key).getCategory()==Product.Category.BOOKS)
    		{
    			products.get(key).print();
    		}
    	}
    	
    }
    
    // Print all current orders
    public void printAllOrders()
    {
    	for(ProductOrder po:orders)
    	{
    		po.print();
    	}
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for(ProductOrder pd:shippedOrders)
    	{
    		pd.print();
    	}
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
    		c.print();
    }
    public void printCart(String Customerid)
    {
    	Customer ci= null;
    	String Cust_id="";
    	for(Customer c:customers)
    	{
    		if(Customerid.equals(c.getId()))
    		{
    			
    			ci=c;
    			Cust_id=Customerid;
    			break;
    		}
    	}
    	if(Cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+Customerid+" Not Found");
    		
    	}
    	Cart ca=ci.getCart();
    	ca.print();
    	
    	
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId
    	// If customer does not exist, set errMsg String and return false
    	// see video for an appropriate error message string
    	// ... code here
    	String cust_id="";
    	Customer ci= null;
    	for(Customer c:customers)
    	{
    		if(customerId.equals(c.getId()))
    		{
    			cust_id=customerId;
    			ci=c;
    		}
    	}
    	if(cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
    		
    	
    	}
    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	for(ProductOrder po:orders)
    	{
    		if(po.getCustomer()==ci)// if customer matches print the order
    		{
    			po.print();
    		}
    	}
    	
    
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
    	for(ProductOrder po:shippedOrders)
    	{
    		if(po.getCustomer()==ci)// if customer matches print the shipped order
    		{
    			po.print();
    		}
    	}
    	
    	return true;
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
    	
    	
    	String cust_id="";
    	String prod_id="";
    	String prod_opt="";
    	Product pi = null;
    	Customer ci= null;
    	for(Customer c:customers)
    	{
    		if(customerId.equals(c.getId()))
    		{
    			cust_id=customerId;//setting the instance variables
    			ci=c;
    		}
    	}
    	if(cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
    		
    	}
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object 
    	for(String key:products.keySet())
    	{
    		if(key.equals(productId))
    		{
    			prod_id=key;
    			pi=products.get(key);
    		}
    	}
    	
    	if(prod_id.equals(""))
    	{
    		throw new UnknownProductException("Product "+productId+" Not Found");
    		
    	}
    
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
    	if(pi.validOptions(productOptions)==false)
    	{
    		if(pi.getCategory()==Product.Category.BOOKS)
    		{
    		throw new UnknownOptionsException("Product Book ProductId "+productId+" Invalid Options: "+productOptions);
    		
    		}
    		else
    		{
    			throw new UnknownOptionsException("Product shoes ProductId "+productId+" Invalid Options: "+productOptions);
        		
    		}
    	}
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if(pi.getStockCount(productOptions)==0)
    	{
    		throw new OutofStockException("Product "+productId+" "+productOptions+" Out of Stock");
    		
    	}
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	ProductOrder pd= new ProductOrder(generateOrderNumber(),pi,ci,productOptions);
    	// reduce stock count of product by 1 (see class Product and class Book)
    	pi.setStockCount(pi.getStockCount(productOptions)-1, productOptions);
    	// Add to orders list and return order number string
    	    	orders.add(pd);
    	return pd.getOrderNumber(); // replace this line
   
    	}
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public boolean createCustomer(String name, String address)
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	if(name==null || name.equals(""))
    	{
    		throw new CustomerNameException("Invalid Customer Name");
    		
    	}
    	if(address==null || address.equals(""))
    	{
    		throw new CustomerAddressException("Invalid Customer Address");
    		
    	}
    	// Create a Customer object and add to array list
    	Customer cp= new Customer(generateCustomerId(),name,address);
    	customers.add(cp);
    	
    	return true;
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	String ship_ord="";
    	ProductOrder pd=null;
    	int j=0;
    	for(int i=0;i<orders.size();i++)
    	{
    		if(orders.get(i).getOrderNumber().equals(orderNumber))
    		{
    			ship_ord=orderNumber;
    			pd=orders.get(i);
    			
    			j=i;//setting the index
    		}
    	}
    	
    	if(ship_ord.equals(""))
    	{
    		throw new OrderNumException("Order "+orderNumber+" Not Found");
    		
    	}
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	shippedOrders.add(pd);
    	orders.remove(j);
    	// return a reference to the order
    	return pd;
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	String can_ord="";
    	ProductOrder pd=null;
    	int j=0;
    	for(int i=0;i<orders.size();i++)
    	{
    		if(orders.get(i).getOrderNumber().equals(orderNumber))
    		{
    			can_ord=orderNumber;
    			pd=orders.get(i);
    			
    			j=i;
    		}
    	}
    	
    	if(can_ord.equals(""))
    	{
    		throw new OrderNumException("Order "+orderNumber+" Not Found");
    		
    	}
    	orders.remove(j);
    	return true;
    }
    public void addtocart(String productId, String customerId,String Options)
    {
    	Product po=null;
    	Customer co=null;
    	String prod_id="";
    	String Cust_id="";
    	
    		for(String key:products.keySet())
        	{
        		if(key.equals(productId))
        		{
        			prod_id=key;
        			po=products.get(key);
        		}
        	}
    	if(prod_id.equals(""))
    	{
    		throw new UnknownProductException("Product "+productId+" Not Found");
    	}
    	
    	for(Customer c:customers)
    	{
    		if(customerId.equals(c.getId()))
    		{
    			//setting the instance variables
    			co=c;
    			Cust_id=customerId;
    		}
    	}
    	if(Cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
    		
    	}
    	if(!po.validOptions(Options))
    	{
    		throw new UnknownOptionsException("Invalid option");
    	}
    	Cart ca;
    	ca=co.getCart();
    	
    	ca.addToCart(po, Options);
    	
    }
    public void removeCart(String productId,String customerId)
    {
    	Customer co=null;
    	String Cust_id="";
    	String prod_id="";
    	for(Customer c:customers)
    	{
    		if(customerId.equals(c.getId()))
    		{
    			//setting the instance variables
    			co=c;
    			Cust_id=customerId;
    		}
    	}
    	if(Cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
    		
    	}
    	ArrayList<CartItem> items= co.getCart().getList();
    	for(int i=0;i<items.size();i++)
    	{
    		if(items.get(i).getProduct().getId().equals(productId))
    		{
    			prod_id=productId;
    			items.remove(i);
    		}
    	}
    	if(prod_id.equals(""))
    	{
    		throw new UnknownProductException("Product "+productId+" Not Found");
    	}
    }
    public void ordercartitems(String customerId)
    {
    	Customer co=null;
    	String Cust_id="";
    	for(Customer c:customers)
    	{
    		if(customerId.equals(c.getId()))
    		{
    			//setting the instance variables
    			co=c;
    			Cust_id=customerId;
    		}
    	}
    	if(Cust_id.equals(""))
    	{
    		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
    		
    	}
    	ArrayList<CartItem> items= co.getCart().getList();
    	for(CartItem ci :items)
    	{
    		orderProduct(ci.getProduct().getId(),customerId,ci.getOptions());
    	}
    }
    public boolean BooksByAuthor(String Author)
    {
    	//create an array list books to collect all the book type from products
    	String authorr="";
    	 ArrayList<Book>  books = new ArrayList<Book>();
    	for(int i=0;i<products.size();i++)
    	{
    		if(products.get(i).getCategory().equals(Product.Category.BOOKS))
    		{
    			if(Author.equals(((Book)products.get(i)).GetAuthor()))
    			{
    			 authorr=((Book)products.get(i)).GetAuthor();
    			 books.add(((Book)products.get(i)));
    			}
    		}
    	}
    	if (authorr.equals(""))
    	{
    		throw new RuntimeException("Invalid Author Name");
    		
    	}
    	// sort the books by year 
    	 for(int i=0;i<books.size();i++)
     	  {
     		  for(int j=0;j<books.size()-i-1;j++)
     		  {
     			  if(books.get(j).GetYear()>books.get(j+1).GetYear())
     			  {
     				 Book temp=books.get(j);
     				 books.set(j, books.get(j+1));
     				 books.set(j+1, temp);
     			  }
     		  }
     		  
     	  }
    	 // print the sorted books 
    	for(Book bk:books)
    	{
    		bk.print();
    	}
    	
    	
    	
    	
    	
    	return true;
    }
    public void OrderStats()
    {//prints stats of how many times a product was ordered
    	Map<Product,Integer> stat=new HashMap<Product,Integer>();
    	int count=0;
    	// by default every product has a count 0. we go through orders to check if any product was ordered then we increase count.
    	for(String key: products.keySet())
    	{
    		count=0;
    		
    		for(ProductOrder po:orders)
    		{
    			
    			if(po.getProduct().getId().equals(key))
    			{
    				count++;
    				
    			}
    		}
    		
    		stat.put( products.get(key), count);
    	}
    	
    	for(Product key: stat.keySet())
    	{
    		System.out.printf("\nName: %-20s ID: %3s Number of times ordered:%3d",key.getName(),key.getId(),stat.get(key));
    	}
    	
    }
    public void addRating(String customerId,String productId,Double Rating)
    //to add rating to a product by a customer
    //if a customer already rated for the product we just modify the current rating
    {String cust_id="";
	String prod_id="";
	
	Product pi = null;
	Customer ci= null;
	for(Customer c:customers)
	{
		if(customerId.equals(c.getId()))
		{
			cust_id=customerId;//setting the instance variables
			ci=c;
		}
	}
	if(cust_id.equals(""))
	{
		throw new UnknownCustomerException("Customer "+customerId+" Not Found");
		
	}
	// Check to see if product object with productId exists in array list of products
	// if it does not, set errMsg and return null (see video for appropriate error message string)
	// else get the Product object 
	for(String key:products.keySet())
	{
		if(key.equals(productId))
		{
			prod_id=key;
			pi=products.get(key);
		}
	}
	
	if(prod_id.equals(""))
	{
		throw new UnknownProductException("Product "+productId+" Not Found");
		
	}
	if(pi.getRating(customerId)==-1)
{
	pi.addtorating(Rating, cust_id);
}
	else {
		pi.modifyRating(Rating, cust_id);
	}



    	
    }
    public void PrintRating(String productId)
    {//for printing average rating for a certain product type
    	String prod_id="";
    	Product pi = null;
    	for(String key:products.keySet())
    	{
    		if(key.equals(productId))
    		{
    			prod_id=key;
    			pi=products.get(key);
    		}
    	}
    	
    	if(prod_id.equals(""))
    	{
    		throw new UnknownProductException("Product "+productId+" Not Found");
    		
    	}
    	pi.print();
    	System.out.printf(" Rating:%7.1f",pi.getAvgRating());
    }
    public void printratingbyprods(String producttype,double rating)
    {//print rating of a product of a certain type with a minimum threshold rating
    	if(producttype.equalsIgnoreCase("any"))
    	{
    		for(String key:products.keySet())
    		{
    			if(products.get(key).getAvgRating()>=rating)
    			{
    				products.get(key).print();
    				System.out.printf(" Rating:%7.1f",products.get(key).getAvgRating());
    			}
    		}
    	}
    	else if(producttype.equalsIgnoreCase("book")) {
    		for(String key:products.keySet())
    		{
    			if(products.get(key).getCategory()==Product.Category.BOOKS)
    			{
    			if(products.get(key).getAvgRating()>=rating)
    			{
    				products.get(key).print();
    				System.out.printf(" Rating:%7.1f",products.get(key).getAvgRating());
    			}
    			}
    		}
    	}
    	else if(producttype.equalsIgnoreCase("Shoe")) {
    		for(String key:products.keySet())
    		{
    			if(products.get(key).getCategory()==Product.Category.GENERAL)
    			{
    			if(products.get(key).getAvgRating()>=rating)
    			{
    				products.get(key).print();
    				System.out.printf(" Rating:%7.1f",products.get(key).getAvgRating());
    			}
    			}
    		}
    	}
    }
    // Sort products by increasing price
    public void printByPrice()
    {//Using bubble sort sorting the products by price
    	
    	ArrayList<Product> prods=new ArrayList<Product>();
    	for(String key:products.keySet())
    	{
    		prods.add(products.get(key));
    	}
    
    	for(int i=0;i<prods.size();i++)
    	  {
    		  for(int j=0;j<prods.size()-i-1;j++)
    		  {
    			  if(prods.get(j).getPrice()>prods.get(j+1).getPrice())
    			  {
    				 Product temp=prods.get(j);
    				 prods.set(j, prods.get(j+1));
    				 prods.set(j+1, temp);
    			  }
    		  }
    		  
    	  }
    	for(Product p:prods)
    	{
    		p.print();
    	}
    	
    }
    
    
    // Sort products alphabetically by product name
    public void printByName()
    {//Using bubble sort sorting the products by name
    	ArrayList<Product> prods=new ArrayList<Product>();
    	for(String key:products.keySet())
    	{
    		prods.add(products.get(key));
    	}
    	for(int i=0;i<prods.size();i++)
    	  {
    		  for(int j=0;j<prods.size()-i-1;j++)
    		  {
    			  if(prods.get(j).getName().compareTo(prods.get(j+1).getName())>0)
    			  {
    				 Product temp=prods.get(j);
    				 prods.set(j, prods.get(j+1));
    				 prods.set(j+1, temp);
    			  }
    		  }
    		  
    	  }
    	for(Product p:prods)
    	{
    		p.print();
    	}
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {//Using bubble sort sorting the customers by name
    	for(int i=0;i<customers.size();i++)
  	  {
  		  for(int j=0;j<customers.size()-i-1;j++)
  		  {
  			  if(customers.get(j).getName().compareTo(customers.get(j+1).getName())>0)
  			  {
  				 Customer temp=customers.get(j);
  				customers.set(j, customers.get(j+1));
  				customers.set(j+1, temp);
  			  }
  		  }
  		  
  	  }
    }
    public class UnknownCustomerException extends RuntimeException
    {
    	public  UnknownCustomerException(String message)
    	{
    		super(message);
    	}
    }
    public class UnknownProductException extends RuntimeException
    {
    	public UnknownProductException(String message)
    	{
    		super(message);
    	}
    }
    public class UnknownOptionsException extends RuntimeException
    {
    	public UnknownOptionsException(String message)
    	{
    		super(message);
    	}
    }
    public class OutofStockException extends RuntimeException
    {
    	public OutofStockException(String message)
    	{
    		super(message);
    	}
    }
    public class CustomerNameException extends RuntimeException
    {
    	public CustomerNameException(String message)
    	{
    		super(message);
    	}
    }
    public class CustomerAddressException extends RuntimeException
    {
    	public CustomerAddressException(String message)
    	{
    		super(message);
    	}
    }
    public class OrderNumException extends RuntimeException
    {
    	public OrderNumException(String message)
    	{
    		super(message);
    	}
    }
}

