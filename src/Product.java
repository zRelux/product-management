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
	 * @throws SellingException if the number of items sold is more than the stock level
	 */
	public double sell(int itemsSold) throws SellingException {
		if (this.stockLevel >= itemsSold) {
			this.stockLevel -= itemsSold;
		} else {
			throw new SellingException("Couldn't sell product because number of items is too high");
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

	/***
	 * Returns the name of the product
	 * @return name of the product
	 */
	public String getName() {
		return this.name;
	}

	/***
	 * Returns the stock level of the product
	 * @return stock level of the product
	 */
	public int getStockLevel() {
		return this.stockLevel;
	}

	/***
	 * Returns the price of the product
	 * @return price of the product
	 */
	public double getPrice() {
		return this.price;
	}

	/***
	 * Updates the name of the product
	 * @param name new name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/***
	 * Returns the object in to string format
	 * @return string format of the object
	 */
	public String toString() {
		return "Product: " + getName() + " stock level: " + getStockLevel() + " price at: " + getPrice();
	}
	
	/***
	 * Returns the object in to string format for file saving
	 * @return string format of the object
	 */
	public String toFile() {
		return getName() + ";" + getStockLevel() + ";" + getPrice();
	}
}
