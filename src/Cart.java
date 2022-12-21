import java.util.ArrayList;
public class Cart {
	
	private ArrayList<CartItem>items;
	public Cart()
	{
		this.items= new ArrayList<CartItem>();
	}
	
	
	public void addToCart(Product product,String Options)
	{
		items.add(new CartItem(product,Options));
	}
	
	public void print()
	{
		for(CartItem c: items)
		{
			c.getProduct().print();
			System.out.print("     Options: "+c.getOptions());
		}
	}
	public ArrayList<CartItem> getList()
	{
		return this.items;
	}
	
	

}
