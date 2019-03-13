import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSetPrice;
	private JTextField txtSetStock;
	private JList<String> list;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JLabel lblSetPrice;
	private JButton btnSetPrice;
	private JLabel lblSetStock;
	private JButton btnSetStock;
	private JLabel lblCurrentStock;
	private JLabel lblAddNewProduct;
	private JSeparator addSeparator;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblStockLevel;
	private JTextField txtStock;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JButton btnAdd;
	private JLabel lblUpdateProduct;
	private JSeparator updateSeparator;
	private JScrollPane scrollPane;
	private JLabel lblRemoveSelectedProduct;
	private JSeparator removeSeparator;
	private JButton btnRemoveSelectedItem;
	private JSeparator newNameSeparator;
	private JSeparator newPriceSeparator;
	private JLabel lblSetName;
	private JTextField txtSetName;
	private JButton btnSetName;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnAbout;
	private JSeparator sellSeparator;
	private JLabel lblSell;
	private JTextField txtSellItem;
	private JLabel lblSoldItems;
	private JLabel lblSellItem;
	private JButton btnSell;

	private ArrayList<Product> products;
	private int itemsSold = 0;
	private double totalPrice = 0;
	private JLabel lblProductInfo;
	private JSeparator nameSeparator;

	public Window() {
		products = new ArrayList<Product>();
		init();
	}

	private void init() {
		setTitle("Product Managment Leonardo Drici u1905444");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
		setBounds(100, 100, 800, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// JMENU
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 795, 20);
		contentPane.add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFile = new JMenuItem("Open file");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		mnFile.add(mntmOpenFile);

		JMenuItem mntmSaveToFile = new JMenuItem("Save to file");
		mntmSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnFile.add(mntmSaveToFile);

		JMenuItem mntmClearList = new JMenuItem("Clear list");
		mntmClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnFile.add(mntmClearList);

		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmBoh = new JMenuItem("Boh");
		mntmBoh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnEdit.add(mntmBoh);

		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmInformation = new JMenuItem("Information");
		mntmInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnAbout.add(mntmInformation);

		// LIST SECTION
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 320, 580);
		contentPane.add(scrollPane);
		list = new JList<String>();
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedProduct = list.getSelectedIndex();
				if (selectedProduct > -1) {
					lblSetPrice.setText("Set new price of " + products.get(selectedProduct).getName() + " current is "
							+ value(products.get(selectedProduct).getPrice()));
					lblSetStock.setText("Update stock level of " + products.get(selectedProduct).getName());
					lblCurrentStock.setText("Current Stock: " + products.get(selectedProduct).getStockLevel());
					lblSetName.setText("Set new name of " + products.get(selectedProduct).getName());
					lblSellItem.setText("Sell " + products.get(selectedProduct).getName());
					lblProductInfo.setText(products.get(selectedProduct).getName() + " the price is "
							+ value(products.get(selectedProduct).getPrice()) + " the stock level is "
							+ products.get(selectedProduct).getStockLevel());
					btnSetName.setEnabled(true);
					btnSetPrice.setEnabled(true);
					btnSetStock.setEnabled(true);
					btnSell.setEnabled(true);
				}

			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);

		// ADD NEW PRODUCT SECTION
		lblAddNewProduct = new JLabel("Add new product");
		lblAddNewProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddNewProduct.setBounds(340, 30, 100, 25);
		contentPane.add(lblAddNewProduct);

		addSeparator = new JSeparator();
		addSeparator.setBounds(340, 55, 435, 2);
		contentPane.add(addSeparator);

		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(340, 65, 100, 25);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(450, 65, 100, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);

		lblStockLevel = new JLabel("Stock level:");
		lblStockLevel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStockLevel.setBounds(340, 100, 100, 25);
		contentPane.add(lblStockLevel);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(450, 100, 100, 25);
		contentPane.add(txtStock);

		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(340, 135, 100, 25);
		contentPane.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(450, 135, 100, 25);
		contentPane.add(txtPrice);

		btnAdd = new JButton("Add new product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtName.getText().equals("") && !txtStock.getText().equals("") && !txtPrice.getText().equals("")) {
					try {
						Integer.parseInt(txtStock.getText());
						try {
							Double.parseDouble(txtPrice.getText());
							Product temp = new Product(txtName.getText(), Integer.parseInt(txtStock.getText()),
									Double.parseDouble(txtPrice.getText()));
							products.add(temp);
							model.addElement(temp.getName());
							txtName.setText("");
							txtStock.setText("");
							txtPrice.setText("");
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Couldn't add product because price is not a number",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Couldn't add product because stock number is not a number",
								"Error", JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "One of the fields is empty", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(645, 135, 130, 25);
		contentPane.add(btnAdd);

		// SELL PRODUCT SECTION
		lblSell = new JLabel("Sell product");
		lblSell.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSell.setBounds(340, 175, 100, 25);
		contentPane.add(lblSell);

		sellSeparator = new JSeparator();
		sellSeparator.setBounds(340, 200, 435, 2);
		contentPane.add(sellSeparator);

		lblProductInfo = new JLabel("");
		lblProductInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProductInfo.setBounds(340, 210, 435, 30);
		contentPane.add(lblProductInfo);

		nameSeparator = new JSeparator();
		nameSeparator.setBounds(390, 250, 320, 2);
		contentPane.add(nameSeparator);

		lblSoldItems = new JLabel("Sold items " + itemsSold + " for a total of " + value(totalPrice));
		lblSoldItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoldItems.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoldItems.setBounds(340, 265, 435, 25);
		contentPane.add(lblSoldItems);

		lblSellItem = new JLabel("Sell ");
		lblSellItem.setBounds(340, 300, 265, 25);
		contentPane.add(lblSellItem);

		txtSellItem = new JTextField();
		txtSellItem.setColumns(10);
		txtSellItem.setBounds(615, 300, 75, 25);
		contentPane.add(txtSellItem);

		btnSell = new JButton("SELL");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					int itemsSelling = Integer.parseInt(txtSellItem.getText());
					itemsSold += itemsSelling;
					try {
						totalPrice += products.get(selectedProduct).sell(itemsSelling);
						lblSoldItems.setText("Sold items " + itemsSold + " for a total of " + value(totalPrice));
						lblCurrentStock.setText("Current Stock: " + products.get(selectedProduct).getStockLevel());
						lblProductInfo.setText(products.get(selectedProduct).getName() + " the price is "
								+ value(products.get(selectedProduct).getPrice()) + " the stock level is "
								+ products.get(selectedProduct).getStockLevel());
					} catch (SellingException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Couldn't sell product because number is not a correct",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSell.setEnabled(false);
		btnSell.setBounds(700, 300, 75, 25);
		contentPane.add(btnSell);

		// UPDATE PRODUCT SECTION
		lblUpdateProduct = new JLabel("Update product");
		lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUpdateProduct.setBounds(340, 335, 100, 25);
		contentPane.add(lblUpdateProduct);

		updateSeparator = new JSeparator();
		updateSeparator.setBounds(340, 355, 435, 2);
		contentPane.add(updateSeparator);

		lblSetName = new JLabel("Set new name of ");
		lblSetName.setBounds(340, 370, 265, 25);
		contentPane.add(lblSetName);

		txtSetName = new JTextField();
		txtSetName.setColumns(10);
		txtSetName.setBounds(615, 370, 75, 25);
		contentPane.add(txtSetName);

		btnSetName = new JButton("SET");
		btnSetName.setEnabled(false);
		btnSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				products.get(selectedIndex).setName(txtSetName.getText());
				model.removeElementAt(selectedIndex);
				model.add(selectedIndex, products.get(selectedIndex).getName());
				txtSetName.setText("");
				lblProductInfo.setText("");
				btnSetName.setEnabled(false);
				btnSetPrice.setEnabled(false);
				btnSetStock.setEnabled(false);
				btnSell.setEnabled(false);
			}
		});
		btnSetName.setBounds(700, 370, 75, 25);
		contentPane.add(btnSetName);

		newPriceSeparator = new JSeparator();
		newPriceSeparator.setBounds(390, 460, 320, 2);
		contentPane.add(newPriceSeparator);

		lblSetPrice = new JLabel("Set new price of ");
		lblSetPrice.setBounds(340, 420, 265, 25);
		contentPane.add(lblSetPrice);

		txtSetPrice = new JTextField();
		txtSetPrice.setBounds(615, 420, 75, 25);
		contentPane.add(txtSetPrice);
		txtSetPrice.setColumns(10);

		btnSetPrice = new JButton("SET");
		btnSetPrice.setEnabled(false);
		btnSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					Double.parseDouble(txtSetPrice.getText());
					products.get(selectedProduct).setPrice(Double.parseDouble(txtSetPrice.getText()));
					txtSetPrice.setText("");
					lblSetPrice.setText("Set new price of " + products.get(selectedProduct).getName() + " current is "
							+ value(products.get(selectedProduct).getPrice()));
					lblProductInfo.setText(products.get(selectedProduct).getName() + " the price is "
							+ value(products.get(selectedProduct).getPrice()) + " the stock level is "
							+ products.get(selectedProduct).getStockLevel());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Couldn't update price because it is not a number", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSetPrice.setBounds(700, 420, 75, 25);
		contentPane.add(btnSetPrice);

		newNameSeparator = new JSeparator();
		newNameSeparator.setBounds(390, 410, 320, 2);
		contentPane.add(newNameSeparator);

		lblSetStock = new JLabel("Update stock level of ");
		lblSetStock.setBounds(340, 480, 265, 25);
		contentPane.add(lblSetStock);

		lblCurrentStock = new JLabel("Current Stock: ");
		lblCurrentStock.setBounds(340, 515, 265, 25);
		contentPane.add(lblCurrentStock);

		txtSetStock = new JTextField();
		txtSetStock.setColumns(10);
		txtSetStock.setBounds(615, 515, 75, 25);
		contentPane.add(txtSetStock);

		btnSetStock = new JButton("ADD");
		btnSetStock.setEnabled(false);
		btnSetStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedProduct = list.getSelectedIndex();
				try {
					Integer.parseInt(txtSetStock.getText());
					products.get(selectedProduct).reStock(Integer.parseInt(txtSetStock.getText()));
					txtSetStock.setText("");
					lblCurrentStock.setText("Current Stock: " + products.get(selectedProduct).getStockLevel());
					lblProductInfo.setText(products.get(selectedProduct).getName() + " the price is "
							+ value(products.get(selectedProduct).getPrice()) + " the stock level is "
							+ products.get(selectedProduct).getStockLevel());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Couldn't update stock because it is not a number", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSetStock.setBounds(700, 515, 75, 25);
		contentPane.add(btnSetStock);

		// REMOVE PRODUCT SECTION
		lblRemoveSelectedProduct = new JLabel("Remove selected product");
		lblRemoveSelectedProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRemoveSelectedProduct.setBounds(340, 550, 160, 25);
		contentPane.add(lblRemoveSelectedProduct);

		removeSeparator = new JSeparator();
		removeSeparator.setBounds(340, 575, 435, 2);
		contentPane.add(removeSeparator);

		btnRemoveSelectedItem = new JButton("Remove selected item");
		btnRemoveSelectedItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemoveSelectedItem.setForeground(Color.RED);
		btnRemoveSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if(index > -1) {
					products.remove(index);
					model.remove(index);
				}else {
					JOptionPane.showMessageDialog(null, "Please select a Product to delete", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRemoveSelectedItem.setBounds(575, 585, 200, 25);
		contentPane.add(btnRemoveSelectedItem);

		// SET CONTENT PANE VISIBLE
		setVisible(true);
	}

	private String value(double price) {
		return ((price < 1) ? price + "p" : "£ " + price);
	}
}
