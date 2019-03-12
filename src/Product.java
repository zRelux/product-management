public class Product {
	private String name;
	private int stockLevel;
	private double price;

	/***
	 * Constructor of a product
	 * @param name of the product
	 * @param stockLevel of the product
	 * @param price of the product
	 */
	public Product(String name, int stockLevel, double price) {
		super();
		this.name = name;
		this.stockLevel = stockLevel;
		this.price = price;
	}

	/***
	 * Updates the stock level
	 * @param amount to update level
	 */
	public void reStock(int amount) {
		this.stockLevel += amount;
	}

	/***
	 * Sells items
	 * @param itemsSold number of items sold 
	 * @return the cost of the order
	 * @throws Exception if the number of items sold is more than the stock level
	 */
	public double sell(int itemsSold) throws Exception {
		if (this.stockLevel >= itemsSold) {
			this.stockLevel -= itemsSold;
		} else {
			throw new Exception();
		}

		return itemsSold * this.price;
	}

	/***
	 * Updates the price of the product
	 * @param new price for the product
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public int getStockLevel() {
		return this.stockLevel;
	}

	public double getPrice() {
		return this.price;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Product: " + getName() + " stock level: " + getStockLevel() + " price at: " + getPrice();
	}
}
