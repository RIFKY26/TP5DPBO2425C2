import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMenu extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Product Menu");
        ProductMenu productMenu = new ProductMenu();
        frame.setContentPane(productMenu.mainPanel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1;
    private Database database;

    // Komponen GUI
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JSlider ratingSlider;

    public ProductMenu() {
        database = new Database();
        productTable.setModel(setTable());
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        titleLabel.setForeground(new Color(30, 30, 30));
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "Novel", "Self-Improvement", "Fiksi", "Sejarah", "Teknologi", "Komik"
        }));
        deleteButton.setVisible(false);
        ratingSlider.setMinimum(1);
        ratingSlider.setMaximum(5);
        ratingSlider.setValue(3);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setPaintTicks(true);

        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) {
                insertData();
            } else {
                updateData();
            }
        });

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Yakin ingin menghapus data ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                deleteData();
            }
        });

        cancelButton.addActionListener(e -> clearForm());

        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = productTable.getSelectedRow();
                if (selectedIndex != -1) {
                    String id = productTable.getModel().getValueAt(selectedIndex, 0).toString();
                    String nama = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String harga = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String kategori = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String ratingStr = productTable.getModel().getValueAt(selectedIndex, 4).toString().split(" ")[0];
                    int rating = Integer.parseInt(ratingStr);

                    idField.setText(id);
                    namaField.setText(nama);
                    hargaField.setText(harga);
                    kategoriComboBox.setSelectedItem(kategori);
                    ratingSlider.setValue(rating);

                    addUpdateButton.setText("Update");
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    public final DefaultTableModel setTable() {
        String[] kolom = {"ID", "Nama", "Harga", "Kategori", "Rating"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM product");
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getDouble("harga");
                row[3] = resultSet.getString("kategori");
                row[4] = resultSet.getInt("rating") + " / 5";
                model.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    public void insertData() {
        try {
            String id = idField.getText();
            String nama = namaField.getText();
            String hargaText = hargaField.getText();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int rating = ratingSlider.getValue();

            // 1. PENGECEKAN INPUT KOSONG
            if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Hentikan proses jika ada yang kosong
            }

            // 2. PENGECEKAN DUPLIKASI ID
            if (database.isIdExists(id)) {
                JOptionPane.showMessageDialog(null, "ID Produk '" + id + "' sudah ada di database!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Hentikan proses jika ID sudah ada
            }

            double harga = Double.parseDouble(hargaText);
            String sql = "INSERT INTO product VALUES ('" + id + "', '" + nama + "', " + harga + ", '" + kategori + "', " + rating + ")";
            database.insertUpdateDeleteQuery(sql);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            String id = idField.getText();
            String nama = namaField.getText();
            String hargaText = hargaField.getText();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int rating = ratingSlider.getValue();

            // 1. PENGECEKAN INPUT KOSONG
            if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Hentikan proses jika ada yang kosong
            }

            double harga = Double.parseDouble(hargaText);
            String originalId = productTable.getModel().getValueAt(selectedIndex, 0).toString();
            String sql = "UPDATE product SET id='" + id + "', nama='" + nama + "', harga=" + harga + ", kategori='" + kategori + "', rating=" + rating + " WHERE id='" + originalId + "'";
            database.insertUpdateDeleteQuery(sql);

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        String id = productTable.getModel().getValueAt(selectedIndex, 0).toString();
        String sql = "DELETE FROM product WHERE id='" + id + "'";
        database.insertUpdateDeleteQuery(sql);
        productTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        ratingSlider.setValue(3);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}