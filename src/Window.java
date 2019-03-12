import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSetPrice;
	private JTextField txtSetStock;
	private JList list;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JLabel lblSetPrice;
	private JButton btnSetPrice;
	private JLabel lblSetStock;
	private JButton btnSetStock;
	private JLabel lblCurrentStock;
	private JLabel lblAddNewProduct;
	private JSeparator separator;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblStockLevel;
	private JTextField txtStock;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JButton btnAdd;
	private JLabel lblUpdateProduct;
	private JSeparator separa;
	private JScrollPane scrollPane;
	private JLabel lblRemoveSelectedProduct;
	private JSeparator removeSeparator;
	private JButton btnRemoveSelectedItem;

	private ArrayList<Product> products;
	private JSeparator newPriceSeparator;
	private JSeparator newNameSeparator;
	private JLabel lblSetName;
	private JTextField txtSetName;
	private JButton btnSetName;

	public Window() {
		products = new ArrayList<Product>();
		init();
	}

	private void init() {
		setTitle("Product Managment Leonardo Drici u1905444");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// LIST SECTION
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 320, 550);
		contentPane.add(scrollPane);
		list = new JList();
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = list.getSelectedIndex();
				lblSetPrice.setText("Set new price of " + products.get(index).getName());
				lblSetStock.setText("Update stock level of " + products.get(index).getName());
				lblCurrentStock.setText("Current Stock: " + products.get(index).getName());
				lblSetName.setText("Set new name of " + products.get(index).getName());
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);

		// ADD NEW PRODUCT SECTION
		lblAddNewProduct = new JLabel("Add new product");
		lblAddNewProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddNewProduct.setBounds(340, 30, 100, 25);
		contentPane.add(lblAddNewProduct);

		separator = new JSeparator();
		separator.setBounds(340, 53, 434, 2);
		contentPane.add(separator);

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
		lblStockLevel.setBounds(340, 101, 100, 25);
		contentPane.add(lblStockLevel);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(450, 101, 100, 25);
		contentPane.add(txtStock);

		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(340, 137, 100, 25);
		contentPane.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(450, 137, 100, 25);
		contentPane.add(txtPrice);

		btnAdd = new JButton("Add new product");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Integer.parseInt(txtStock.getText());
				} catch (NumberFormatException e) {
					System.out.println("Error cannot parse " + txtStock.getText());
				}
				try {
					Double.parseDouble(txtPrice.getText());
				} catch (NumberFormatException e) {
					System.out.println("Error cannot parse " + txtPrice.getText());
				}

				Product temp = new Product(txtName.getText(), Integer.parseInt(txtStock.getText()),
						Double.parseDouble(txtPrice.getText()));
				products.add(temp);
				model.addElement(temp.getName());
				txtName.setText("");
				txtStock.setText("");
				txtPrice.setText("");
			}
		});
		btnAdd.setBounds(644, 137, 130, 25);
		contentPane.add(btnAdd);

		// UPDATE PRODUCT SECTION
		lblUpdateProduct = new JLabel("Update product");
		lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUpdateProduct.setBounds(340, 186, 100, 25);
		contentPane.add(lblUpdateProduct);

		separa = new JSeparator();
		separa.setBounds(340, 209, 434, 2);
		contentPane.add(separa);

		lblSetPrice = new JLabel("Set new price of ");
		lblSetPrice.setBounds(340, 222, 139, 24);
		contentPane.add(lblSetPrice);

		txtSetPrice = new JTextField();
		txtSetPrice.setBounds(614, 222, 75, 24);
		contentPane.add(txtSetPrice);
		txtSetPrice.setColumns(10);

		btnSetPrice = new JButton("SET");
		btnSetPrice.setBounds(699, 222, 75, 24);
		contentPane.add(btnSetPrice);

		newPriceSeparator = new JSeparator();
		newPriceSeparator.setBounds(390, 260, 320, 2);
		contentPane.add(newPriceSeparator);

		lblSetName = new JLabel("Set new name of ");
		lblSetName.setBounds(340, 273, 139, 24);
		contentPane.add(lblSetName);

		txtSetName = new JTextField();
		txtSetName.setColumns(10);
		txtSetName.setBounds(614, 273, 75, 24);
		contentPane.add(txtSetName);

		btnSetName = new JButton("SET");
		btnSetName.setBounds(699, 273, 75, 24);
		contentPane.add(btnSetName);

		newNameSeparator = new JSeparator();
		newNameSeparator.setBounds(390, 311, 320, 2);
		contentPane.add(newNameSeparator);

		lblSetStock = new JLabel("Update stock level of ");
		lblSetStock.setBounds(340, 330, 264, 24);
		contentPane.add(lblSetStock);

		txtSetStock = new JTextField();
		txtSetStock.setColumns(10);
		txtSetStock.setBounds(614, 365, 75, 24);
		contentPane.add(txtSetStock);

		btnSetStock = new JButton("ADD");
		btnSetStock.setBounds(699, 365, 75, 24);
		contentPane.add(btnSetStock);

		lblCurrentStock = new JLabel("Current Stock: ");
		lblCurrentStock.setBounds(340, 365, 264, 24);
		contentPane.add(lblCurrentStock);

		// REMOVE PRODUCT SECTION
		lblRemoveSelectedProduct = new JLabel("Remove selected product");
		lblRemoveSelectedProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRemoveSelectedProduct.setBounds(340, 430, 160, 25);
		contentPane.add(lblRemoveSelectedProduct);

		removeSeparator = new JSeparator();
		removeSeparator.setBounds(340, 453, 434, 2);
		contentPane.add(removeSeparator);

		btnRemoveSelectedItem = new JButton("Remove selected item");
		btnRemoveSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				products.remove(index);
				model.remove(index);
			}
		});
		btnRemoveSelectedItem.setBounds(615, 465, 160, 25);
		contentPane.add(btnRemoveSelectedItem);

		// SET CONTENT PANE VISIBLE
		setVisible(true);
	}
}
