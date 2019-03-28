import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	//JMenu elements
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmInformation;
	private JMenuItem mntmClearList;
	private JMenu mnAbout;
	private JMenuItem mntmSaveToFile;
	
	//JList
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> model = new DefaultListModel<>();
	
	//Add Section
	private JPanel addPanel;
	private JLabel lblAddNewProduct;
	private JSeparator addSeparator;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblStockLevel;
	private JTextField txtStock;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JButton btnAdd;
	
	//Sell Section
	private JPanel sellPanel;
	private JLabel lblSell;
	private JSeparator sellSeparator;
	private JLabel lblProductInfo;
	private JLabel lblSoldItems;
	private JLabel lblSellItem;
	private JTextField txtSellItem;
	private JButton btnSell;
	
	//Set Section
	private JPanel updatePanel;
	private JLabel lblUpdateProduct;
	private JSeparator updateSeparator;
	private JTextField txtSetPrice;
	private JTextField txtSetStock;
	private JLabel lblSetPrice;
	private JButton btnSetPrice;
	private JLabel lblSetStock;
	private JButton btnSetStock;
	private JLabel lblCurrentStock;
	private JSeparator newNameSeparator;
	private JSeparator newPriceSeparator;
	private JLabel lblSetName;
	private JTextField txtSetName;
	private JButton btnSetName;
	private JSeparator nameSeparator;

	//Remove Section
	private JPanel removePanel;
	private JLabel lblRemoveSelectedProduct;
	private JSeparator removeSeparator;
	private JButton btnRemoveSelectedItem;
	
	//Productmanagment object
	private ProductManagement managment;
	
	public Window() {
		managment = new ProductManagement();
		init();
		addToList();
	}

	/***
	 * Window initialization method renders elements
	 * 
	 */
	private void init() {
		setTitle("Product Managment Leonardo Drici u1905444");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				managment.saveToFile();
				dispose();
				System.exit(0);
			}
		});
		setSize(815, 665);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		// JMENU 
		menuBar = new JMenuBar();
		
		mntmSaveToFile = new JMenuItem("Save to file");
		mntmSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");
				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV file", "csv"));
				int userSelection = fileChooser.showSaveDialog(getContentPane());

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();

					if (!fileToSave.getAbsolutePath().endsWith(".csv"))
						fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");

					managment.saveAsUserCSV(fileToSave.getAbsolutePath());
				}
			}
		});
		
		mntmClearList = new JMenuItem("Clear list");
		mntmClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to delete everything",
						"Information", JOptionPane.YES_NO_OPTION);
				if (res == 0) {
					managment.clearAll();
					model.clear();
				}
			}
		});
		
		mntmInformation = new JMenuItem("Information");
		mntmInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(getContentPane(), "Basic Product Management software made by Leonardo Drici 1905444",
						"Information", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		mnFile = new JMenu("File");
		mnFile.add(mntmSaveToFile);
		mnFile.add(mntmClearList);
		
		mnAbout = new JMenu("About");
		mnAbout.add(mntmInformation);
		
		menuBar.add(mnFile);
		menuBar.add(mnAbout);
		
		setJMenuBar(menuBar);
		
		// LIST SECTION
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 320, 580);
		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedProduct = list.getSelectedIndex();
				if (selectedProduct > -1) {
					lblSetPrice.setText("Set new price of " + managment.getProduct(selectedProduct).getName()
							+ " current is " + value(managment.getProduct(selectedProduct).getPrice()));
					lblSetStock.setText("Update stock level of " + managment.getProduct(selectedProduct).getName());
					lblCurrentStock.setText("Current Stock: " + managment.getProduct(selectedProduct).getStockLevel());
					lblSetName.setText("Set new name of " + managment.getProduct(selectedProduct).getName());
					lblSellItem.setText("Sell " + managment.getProduct(selectedProduct).getName());
					lblProductInfo.setText(managment.getProduct(selectedProduct).getName() + " the price is "
							+ value(managment.getProduct(selectedProduct).getPrice()) + " the stock level is "
							+ managment.getProduct(selectedProduct).getStockLevel());
					btnSetName.setEnabled(true);
					btnSetPrice.setEnabled(true);
					btnSetStock.setEnabled(true);
					btnSell.setEnabled(true);
				}

			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);
		
		getContentPane().add(scrollPane);

		// ADD NEW PRODUCT SECTION
		addPanel = new JPanel();
		addPanel.setBounds(340, 11, 435, 133);
		addPanel.setLayout(null);

		lblAddNewProduct = new JLabel("Add new product");
		lblAddNewProduct.setBounds(170, 5, 95, 14);
		addPanel.add(lblAddNewProduct);
		lblAddNewProduct.setFont(new Font("Tahoma", Font.BOLD, 11));

		addSeparator = new JSeparator();
		addSeparator.setBounds(0, 25, 435, 2);
		addPanel.add(addSeparator);

		lblName = new JLabel("Name: ");
		lblName.setBounds(10, 30, 100, 25);
		addPanel.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtName = new JTextField();
		txtName.setBounds(110, 30, 100, 25);
		addPanel.add(txtName);
		txtName.setColumns(10);

		lblStockLevel = new JLabel("Stock level:");
		lblStockLevel.setBounds(10, 65, 100, 25);
		addPanel.add(lblStockLevel);
		lblStockLevel.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtStock = new JTextField();
		txtStock.setBounds(110, 65, 100, 25);
		addPanel.add(txtStock);
		txtStock.setColumns(10);

		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(10, 97, 100, 25);
		addPanel.add(lblPrice);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtPrice = new JTextField();
		txtPrice.setBounds(110, 97, 100, 25);
		addPanel.add(txtPrice);
		txtPrice.setColumns(10);

		btnAdd = new JButton("Add new product");
		btnAdd.setBounds(295, 97, 130, 25);
		addPanel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtName.getText().equals("") && !txtStock.getText().equals("") && !txtPrice.getText().equals("")) {
					try {
						Integer.parseInt(txtStock.getText());
						try {
							Double.parseDouble(txtPrice.getText());
							Product temp = new Product(txtName.getText(), Integer.parseInt(txtStock.getText()),
									Double.parseDouble(txtPrice.getText()));
							managment.addProduct(temp);
							model.addElement(temp.getName());
							txtName.setText("");
							txtStock.setText("");
							txtPrice.setText("");
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(getContentPane(), "Couldn't add product because price is not a number",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(getContentPane(), "Couldn't add product because stock number is not a number",
								"Error", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					if(txtName.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane(), "Name field cannot be empty", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else if(txtStock.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane(), "Stock field cannot be empty", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else if(txtPrice.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane(), "Price field cannot be empty", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});

		getContentPane().add(addPanel);

		// SELL PRODUCT SECTION
		sellPanel = new JPanel();
		sellPanel.setBounds(340, 155, 435, 150);
		sellPanel.setLayout(null);

		lblSell = new JLabel("Sell product");
		lblSell.setBounds(184, 15, 67, 14);
		sellPanel.add(lblSell);
		lblSell.setFont(new Font("Tahoma", Font.BOLD, 11));

		sellSeparator = new JSeparator();
		sellSeparator.setBounds(0, 33, 425, 2);
		sellPanel.add(sellSeparator);

		lblProductInfo = new JLabel("");
		lblProductInfo.setBounds(67, 44, 300, 15);
		sellPanel.add(lblProductInfo);
		lblProductInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductInfo.setFont(new Font("Tahoma", Font.BOLD, 14));

		nameSeparator = new JSeparator();
		nameSeparator.setBounds(93, 14, 0, 2);
		sellPanel.add(nameSeparator);

		lblSoldItems = new JLabel(
				"Sold items " + managment.getItemsSold() + " for a total of " + value(managment.getTotalPrice()));
		lblSoldItems.setBounds(67, 85, 300, 15);
		sellPanel.add(lblSoldItems);
		lblSoldItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoldItems.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblSellItem = new JLabel("Sell ");
		lblSellItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSellItem.setBounds(10, 115, 55, 20);
		sellPanel.add(lblSellItem);

		txtSellItem = new JTextField();
		txtSellItem.setBounds(80, 115, 85, 20);
		sellPanel.add(txtSellItem);
		txtSellItem.setColumns(10);

		btnSell = new JButton("SELL");
		btnSell.setBounds(370, 115, 55, 25);
		sellPanel.add(btnSell);
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					int itemsSelling = Integer.parseInt(txtSellItem.getText());
					if (itemsSelling > 0) {
						try {
							managment.addToTotalPrice(managment.getProduct(selectedProduct).sell(itemsSelling));
							managment.addToItemsSold(itemsSelling);
							lblSoldItems.setText("Sold items " + managment.getItemsSold() + " for a total of "
									+ value(managment.getTotalPrice()));
							lblCurrentStock
									.setText("Current Stock: " + managment.getProduct(selectedProduct).getStockLevel());
							lblProductInfo.setText(managment.getProduct(selectedProduct).getName() + " the price is "
									+ value(managment.getProduct(selectedProduct).getPrice()) + " the stock level is "
									+ managment.getProduct(selectedProduct).getStockLevel());
						} catch (SellingException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Item cannot be less than 0", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(getContentPane(), "Couldn't sell product because number is not correct.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSell.setEnabled(false);

		getContentPane().add(sellPanel);

		// UPDATE PRODUCT SECTION
		updatePanel = new JPanel();
		updatePanel.setBounds(340, 316, 435, 210);
		updatePanel.setLayout(null);

		lblUpdateProduct = new JLabel("Update product");
		lblUpdateProduct.setBounds(172, 0, 100, 25);
		updatePanel.add(lblUpdateProduct);
		lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 11));

		updateSeparator = new JSeparator();
		updateSeparator.setBounds(0, 25, 435, 2);
		updatePanel.add(updateSeparator);

		lblSetName = new JLabel("Set new name of ");
		lblSetName.setBounds(10, 40, 245, 25);
		updatePanel.add(lblSetName);

		txtSetName = new JTextField();
		txtSetName.setBounds(265, 40, 75, 25);
		updatePanel.add(txtSetName);
		txtSetName.setColumns(10);

		btnSetName = new JButton("SET");
		btnSetName.setBounds(350, 40, 75, 25);
		updatePanel.add(btnSetName);
		btnSetName.setEnabled(false);

		newPriceSeparator = new JSeparator();
		newPriceSeparator.setBounds(0, 126, 435, 2);
		updatePanel.add(newPriceSeparator);

		lblSetPrice = new JLabel("Set new price of ");
		lblSetPrice.setBounds(10, 90, 245, 25);
		updatePanel.add(lblSetPrice);

		txtSetPrice = new JTextField();
		txtSetPrice.setBounds(265, 90, 75, 25);
		updatePanel.add(txtSetPrice);
		txtSetPrice.setColumns(10);

		btnSetPrice = new JButton("SET");
		btnSetPrice.setBounds(350, 90, 75, 25);
		updatePanel.add(btnSetPrice);
		btnSetPrice.setEnabled(false);

		newNameSeparator = new JSeparator();
		newNameSeparator.setBounds(0, 75, 435, 2);
		updatePanel.add(newNameSeparator);

		lblSetStock = new JLabel("Update stock level of ");
		lblSetStock.setBounds(10, 140, 245, 25);
		updatePanel.add(lblSetStock);

		lblCurrentStock = new JLabel("Current Stock: ");
		lblCurrentStock.setBounds(10, 175, 245, 25);
		updatePanel.add(lblCurrentStock);

		txtSetStock = new JTextField();
		txtSetStock.setBounds(265, 174, 75, 25);
		updatePanel.add(txtSetStock);
		txtSetStock.setColumns(10);

		btnSetStock = new JButton("ADD");
		btnSetStock.setBounds(350, 175, 75, 25);
		updatePanel.add(btnSetStock);
		btnSetStock.setEnabled(false);
		btnSetStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					Integer.parseInt(txtSetStock.getText());
					if (Integer.parseInt(txtSetStock.getText()) > 0) {
						managment.getProduct(selectedProduct).reStock(Integer.parseInt(txtSetStock.getText()));
						txtSetStock.setText("");
						lblCurrentStock
								.setText("Current Stock: " + managment.getProduct(selectedProduct).getStockLevel());
						lblProductInfo.setText(managment.getProduct(selectedProduct).getName() + " the price is "
								+ value(managment.getProduct(selectedProduct).getPrice()) + " the stock level is "
								+ managment.getProduct(selectedProduct).getStockLevel());
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Couldn't update stock because the value is less than 0",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(getContentPane(), "Couldn't update stock because it is not a number", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					Double.parseDouble(txtSetPrice.getText());
					if (Double.parseDouble(txtSetPrice.getText()) > 0) {
						managment.getProduct(selectedProduct).setPrice(Double.parseDouble(txtSetPrice.getText()));
						txtSetPrice.setText("");
						lblSetPrice.setText("Set new price of " + managment.getProduct(selectedProduct).getName()
								+ " current is " + value(managment.getProduct(selectedProduct).getPrice()));
						lblProductInfo.setText(managment.getProduct(selectedProduct).getName() + " the price is "
								+ value(managment.getProduct(selectedProduct).getPrice()) + " the stock level is "
								+ managment.getProduct(selectedProduct).getStockLevel());
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Couldn't update price because the value is less than 0",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(getContentPane(), "Couldn't update price because it is not a number", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (!txtSetName.getText().equals("")) {
					managment.getProduct(selectedIndex).setName(txtSetName.getText());
					model.removeElementAt(selectedIndex);
					model.add(selectedIndex, managment.getProduct(selectedIndex).getName());
					txtSetName.setText("");
					lblProductInfo.setText("");
					btnSetName.setEnabled(false);
					btnSetPrice.setEnabled(false);
					btnSetStock.setEnabled(false);
					btnSell.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Couldn't change product name because name is empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		getContentPane().add(updatePanel);

		// REMOVE PRODUCT SECTION
		removePanel = new JPanel();
		removePanel.setBounds(340, 537, 435, 56);
		removePanel.setLayout(null);

		removeSeparator = new JSeparator();
		removeSeparator.setBounds(0, 20, 435, 2);
		removePanel.add(removeSeparator);

		btnRemoveSelectedItem = new JButton("Remove selected item");
		btnRemoveSelectedItem.setBounds(140, 28, 160, 25);
		removePanel.add(btnRemoveSelectedItem);
		btnRemoveSelectedItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveSelectedItem.setForeground(Color.RED);

		lblRemoveSelectedProduct = new JLabel("Remove selected product");
		lblRemoveSelectedProduct.setBounds(145, 0, 145, 15);
		removePanel.add(lblRemoveSelectedProduct);
		lblRemoveSelectedProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index > -1) {
					int res = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to delete " + managment.getProduct(index).getName(),
							"Information", JOptionPane.YES_NO_OPTION);
					if (res == 0) {
						managment.removeProduct(index);
						model.remove(index);
						managment.saveToFile();
						lblSetPrice.setText("Set new price of " + "current is ");
						lblSetStock.setText("Update stock level of ");
						lblCurrentStock.setText("Current Stock: ");
						lblSetName.setText("Set new name of ");
						lblSellItem.setText("Sell ");
						btnSetName.setEnabled(false);
						btnSetPrice.setEnabled(false);
						btnSetStock.setEnabled(false);
						btnSell.setEnabled(false);
					}
					
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Please select a Product to delete", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		getContentPane().add(removePanel);

		// SET CONTENT PANE VISIBLE
		setVisible(true);
	}

	/***
	 * Returns a string with the £ or p symbol based on the price
	 * @param price price to convert
	 * @return String with � or p symbol
	 */
	private String value(double price) {
		return ((price < 1) ? price + "p" : "£ " + price);
	}
	
	/***
	 * Method to add all elements in product management into the list 
	 */
	private void addToList() {
		for (Product item : managment.getProducts()) {
			model.addElement(item.getName());
		}
	}
}
