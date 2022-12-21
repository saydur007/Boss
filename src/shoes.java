// Name: Saydur Rahman
// Student number: 501046782
public class shoes extends Product 
{
	private String brand;
	private String year;
	//creating variable for each stock type
	int six_blackstock;
	int seven_blackstock;
	int eight_blackstock;
	int nine_blackstock;
	int ten_blackstock;
	int six_brownstock;
	int seven_brownstock;
	int eight_brownstock;
	int nine_brownstock;
	int ten_brownstock;
	//default constructor
	public shoes(String name,String id,double price, int six_blackstock,int seven_blackstock,int eight_blackstock,int nine_blackstock,int ten_blackstock,int six_brownstock,
			int seven_brownstock,int eight_brownstock,int nine_brownstock,int ten_brownstock,String brand,String year)
	{
	super(name,id,price,six_blackstock+seven_blackstock+eight_blackstock+nine_blackstock+ten_blackstock+six_brownstock+seven_brownstock+eight_brownstock+ nine_brownstock
				+ten_brownstock,Category.GENERAL);
		this.brand=brand;
		this.year=year;
		this.six_blackstock=six_blackstock;
		 this.seven_blackstock=seven_blackstock;
		 this.eight_blackstock=eight_blackstock;
		 this.nine_blackstock=nine_blackstock;
		 this.ten_blackstock=ten_blackstock;
		this.six_brownstock=six_brownstock;
		 this.seven_brownstock=seven_brownstock;
		 this.eight_brownstock=eight_brownstock;
		 this.nine_brownstock=nine_brownstock;
		 this.ten_brownstock=ten_brownstock;
	}
	 public boolean validOptions(String productOptions)
	  {
	  	
	  	// if it is one of these, return true, else return false
		  if(productOptions.equals("6 Black") || productOptions.equals("7 Black") || productOptions.equals("8 Black") || productOptions.equals("9 Black")
				  || productOptions.equals("10 Black")|| productOptions.equals("6 Brown")|| productOptions.equals("7 Brown")|| productOptions.equals("8 Brown")
				  || productOptions.equals("9 Brown")|| productOptions.equals("10 Brown"))
		  {
	  	return true;
		  }
		  return false;
	  }
	 public int getStockCount(String productOptions)
		{// check the product options and return the required stock count
	  	if(productOptions.equals("6 Black"))
		  {
			return six_blackstock;  
		  }
		  else if(productOptions.equals("7 Black"))
		  {
			  return seven_blackstock;
		  }
		  else if(productOptions.equals("8 Black"))
		  {
			  return eight_blackstock;
		  }
		  else if(productOptions.equals("9 Black"))
		  {
			  return nine_blackstock;
		  }
		  else if(productOptions.equals("10 Black"))
		  {
			  return ten_blackstock;
		  }
		  else if(productOptions.equals("6 Brown"))
		  {
			  return six_brownstock;
		  }
		  else if(productOptions.equals("7 Brown"))
		  {
			  return seven_brownstock;
		  }
		  else if(productOptions.equals("8 Brown"))
		  {
			  return eight_brownstock;
		  }
		  else if(productOptions.equals("9 Brown"))
		  {
			  return nine_brownstock;
		  }
		  else if(productOptions.equals("10 Brown"))
		  {
			  return ten_brownstock;
		  }
	  	return 1;
		}
	 public void setStockCount(int stockCount, String productOptions)
	 {//check valid product options and set stock count
		 if(productOptions.equals("6 Black"))
		  {
			 six_blackstock=stockCount;  
		  }
		  else if(productOptions.equals("7 Black"))
		  {
			   seven_blackstock=stockCount;
		  }
		  else if(productOptions.equals("8 Black"))
		  {
			   eight_blackstock=stockCount;
		  }
		  else if(productOptions.equals("9 Black"))
		  {
			   nine_blackstock=stockCount;
		  }
		  else if(productOptions.equals("10 Black"))
		  {
			   ten_blackstock=stockCount;
		  }
		  else if(productOptions.equals("6 Brown"))
		  {
			  six_brownstock=stockCount;
		  }
		  else if(productOptions.equals("7 Brown"))
		  {
			 seven_brownstock=stockCount;
		  }
		  else if(productOptions.equals("8 Brown"))
		  {
			  eight_brownstock=stockCount;
		  }
		  else if(productOptions.equals("9 Brown"))
		  {
			  nine_brownstock=stockCount;
		  }
		  else if(productOptions.equals("10 Brown"))
		  {
			  ten_brownstock=stockCount;
		  }
	 }
	 public void reduceStockCount(String productOptions)
	 {//check valid product options and reduce stock count
		 if(productOptions.equals("6 Black"))
		  {
			 six_blackstock--; 
		  }
		  else if(productOptions.equals("7 Black"))
		  {
			   seven_blackstock--;
		  }
		  else if(productOptions.equals("8 Black"))
		  {
			   eight_blackstock--;
		  }
		  else if(productOptions.equals("9 Black"))
		  {
			   nine_blackstock--;
		  }
		  else if(productOptions.equals("10 Black"))
		  {
			   ten_blackstock--;
		  }
		  else if(productOptions.equals("6 Brown"))
		  {
			  six_brownstock--;
		  }
		  else if(productOptions.equals("7 Brown"))
		  {
			 seven_brownstock--;
		  }
		  else if(productOptions.equals("8 Brown"))
		  {
			  eight_brownstock--;
		  }
		  else if(productOptions.equals("9 Brown"))
		  {
			  nine_brownstock--;
		  }
		  else if(productOptions.equals("10 Brown"))
		  {
			  ten_brownstock--;
		  } 
	 }
	 public void print()
	  {
	  	// Replace the line below.
	  	// Make use of the super class print() method and append the title and author info. See the video
		  super.print();
		  
		  System.out.printf(" Brand:%-20s",this.brand);
		  System.out.printf(" Year:%-20s",this.year);
	  	
	  }
	 
}
