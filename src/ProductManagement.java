import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProductManagement {

	private ArrayList<Product> products;
	private int itemsSold = 0;
	private double totalPrice = 0;

	/***
	 * Constructor itializes the arrayList
	 */
	public ProductManagement() {
		super();
		products = new ArrayList<Product>();
		loadFromFile();
	}
	
	/***
	 * 
	 * @return arraylist of products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	/***
	 * Removes all products in arraylist updates file
	 */
	public void clearAll() {
		products.clear();
		saveToFile();
	}
	
	/***
	 * Returns a product based on indec
	 * @param productNumber index of the product
	 * @return Product selected
	 */
	public Product getProduct(int productNumber) {
		return products.get(productNumber);
	}

	/***
	 * Adds a new Product to list
	 * @param newProduct object
	 */
	public void addProduct(Product newProduct) {
		products.add(newProduct);
	}

	/***
	 * Removes product from list based on index
	 * @param removeProduct index of the product
	 */
	public void removeProduct(int removeProduct) {
		products.remove(removeProduct);
	}

	/***
	 * Adds to total of items sold the items sold
	 * @param items of products sold
	 */
	public void addToItemsSold(int items) {
		this.itemsSold += items;
	}

	/***
	 * Adds to total of price the price of the items sold
	 * @param price
	 */
	public void addToTotalPrice(double price) {
		this.totalPrice += price;
	}

	/***
	 * Load from a file the information about Products
	 */
	public void loadFromFile() {
		BufferedReader br = null;
		try {
			try {
				File productsFile = new File("products.csv");
				productsFile.createNewFile();
				br = new BufferedReader(new FileReader(productsFile));
				String st = "";
				if ((st = br.readLine()) != null) {
					String[] data = st.split(";");
					this.itemsSold = Integer.parseInt(data[0]);
					this.totalPrice = Double.parseDouble(data[1]);
					while ((st = br.readLine()) != null) {
						String[] parts = st.split(";");
						products.add(new Product(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2])));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * Saves to file all the information about the products
	 */
	public void saveToFile() {
		FileWriter fw = null;
		try {
			fw = new FileWriter("products.csv", false);
			fw.write(this.itemsSold + ";" + this.totalPrice);
			fw.write(System.getProperty("line.separator"));
			for (int i = 0; i < products.size(); i++) {
				fw.write(products.get(i).toFile() + "");
				fw.write(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * Saves the current products to csv file
	 * @param fileName to save to
	 */
	public void saveAsUserCSV(String fileName) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName, false);
			fw.write("Name;Stock Level;Price");
			fw.write(System.getProperty("line.separator"));
			for (int i = 0; i < products.size(); i++) {
				fw.write(products.get(i).toFile() + "");
				fw.write(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	/***
	 * Returns item sold
	 * @return item sold
	 */
	public int getItemsSold() {
		return itemsSold;
	}

	/***
	 * Sets items sold value
	 * @param itemsSold value
	 */
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}

	/***
	 * Returns the total price
	 * @return total price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/***
	 * Sets the new total price
	 * @param totalPrice
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
