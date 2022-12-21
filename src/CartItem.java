
public class CartItem {
    private Product product;
    private String options;
	
	
	
	public CartItem(Product product, String options)
	{
		this.product=product;
		this.options=options;
		
	}
	public Product getProduct()
	{
		return this.product;
	}
	public String getOptions()
	{
		return this.options;
	}
	
}
