import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
class ProductComboBoxModel extends DefaultComboBoxModel<Product> {
    public ProductComboBoxModel(Product[] items) {
        super(items);
    }
    public Object getSelectedItem() {
        return super.getSelectedItem();
    }
}