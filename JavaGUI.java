import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
class JavaGUI extends JFrame {
    private JComboBox<Product> productComboBox;
    private JTextField customerNameField;
    private JTextArea transactionDetailsArea;
    private Transaction currentTransaction;
    private List<Product> availableProducts;
    public JavaGUI() {
        JOptionPane.showMessageDialog(null, "SELAMAT DATANG DI TOSERBA MAJU JAYA");
        availableProducts = new ArrayList<>();
        availableProducts.add(new Product("Beras 1 Liter", 12000.0));
        availableProducts.add(new Product("Minyak Goreng 1 Liter", 15000.0));
        availableProducts.add(new Product("Telur 1 Kg", 30000.0));
        availableProducts.add(new Product("Garam 500g", 15000.0));

        setTitle("TOSERBA MAJU JAYA");
        setSize(400, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        setLocationRelativeTo(null);

        currentTransaction = null;
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Nama Pelanggan:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        customerNameField = new JTextField(20);
        customerNameField.setBounds(100, 20, 165, 25);
        panel.add(customerNameField);

        JLabel productLabel = new JLabel("Pilih Produk:");
        productLabel.setBounds(10, 50, 80, 25);
        panel.add(productLabel);

        Product[] productArray = availableProducts.toArray(new Product[0]);
        ProductComboBoxModel productComboBoxModel = new ProductComboBoxModel(productArray);

        productComboBox = new JComboBox<>(productComboBoxModel);
        productComboBox.setBounds(100, 50, 165, 25);
        panel.add(productComboBox);

        JButton addButton = new JButton("Tambah Produk");
        addButton.setBounds(10, 80, 150, 25);
        panel.add(addButton);

        transactionDetailsArea = new JTextArea();
        transactionDetailsArea.setBounds(10, 110, 350, 200);
        panel.add(transactionDetailsArea);
        
        JButton clearButton = new JButton("Hapus Teks");
        clearButton.setBounds(200, 80, 150, 25);
        panel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transactionDetailsArea.setText("");
                customerNameField.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProductToTransaction();
            }
        });
    }

    private void addProductToTransaction() {
        if (currentTransaction == null) {
            String customerName = customerNameField.getText();
            Customer customer = new Customer(customerName);
            currentTransaction = new Transaction(customer);
        }

        Product selectedProduct = (Product) productComboBox.getSelectedItem();
        currentTransaction.addProduct(selectedProduct);

        updateTransactionDetailsArea();
    }

    private void updateTransactionDetailsArea() {
        if (currentTransaction != null) {
            transactionDetailsArea.setText("Pelanggan: " + currentTransaction.getCustomer().getName() + "\n");
            transactionDetailsArea.append("Produk:\n");

            for (Product product : currentTransaction.getProducts()) {
                transactionDetailsArea.append("- " + product.getName() + " (Rp " + product.getPrice() + ")\n");
            }

            transactionDetailsArea.append("\nTotal: Rp " + currentTransaction.calculateTotal());
        }
    }
}