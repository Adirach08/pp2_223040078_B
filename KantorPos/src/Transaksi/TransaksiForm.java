/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaksi;

/**
 *
 * @author adirap
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TransaksiForm extends JFrame {
    private JTextField tfTanggal, tfJenisTransaksi, tfJumlah, tfKeterangan;
    private JTable table;
    private DefaultTableModel tableModel;
    private TransaksiDAO transaksiDAO;

    public TransaksiForm() {
        transaksiDAO = new TransaksiDAO();

        setTitle("Manajemen Transaksi Kantor Pos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel input
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInput.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        tfTanggal = new JTextField();
        panelInput.add(tfTanggal);

        panelInput.add(new JLabel("Jenis Transaksi:"));
        tfJenisTransaksi = new JTextField();
        panelInput.add(tfJenisTransaksi);

        panelInput.add(new JLabel("Jumlah:"));
        tfJumlah = new JTextField();
        panelInput.add(tfJumlah);

        panelInput.add(new JLabel("Keterangan:"));
        tfKeterangan = new JTextField();
        panelInput.add(tfKeterangan);

        // Panel tombol
        JPanel panelButton = new JPanel();
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnRefresh = new JButton("Refresh");
        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnHapus);
        panelButton.add(btnRefresh);

        // Tabel
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tanggal", "Jenis Transaksi", "Jumlah", "Keterangan"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout utama
        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(panelButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event handler
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahTransaksi();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTransaksi();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusTransaksi();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        loadData();
    }

    private void tambahTransaksi() {
        try {
            Transaksi transaksi = new Transaksi(0, tfTanggal.getText(), tfJenisTransaksi.getText(),
                    Double.parseDouble(tfJumlah.getText()), tfKeterangan.getText());
            transaksiDAO.tambahTransaksi(transaksi);
            JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan!");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateTransaksi() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan diperbarui!");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Transaksi transaksi = new Transaksi(id, tfTanggal.getText(), tfJenisTransaksi.getText(),
                    Double.parseDouble(tfJumlah.getText()), tfKeterangan.getText());
            transaksiDAO.updateTransaksi(transaksi);
            JOptionPane.showMessageDialog(this, "Transaksi berhasil diperbarui!");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void hapusTransaksi() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan dihapus!");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            transaksiDAO.hapusTransaksi(id);
            JOptionPane.showMessageDialog(this, "Transaksi berhasil dihapus!");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0); // Clear table
            List<Transaksi> transaksiList = transaksiDAO.getAllTransaksi();
            for (Transaksi transaksi : transaksiList) {
                tableModel.addRow(new Object[]{
                        transaksi.getIdTransaksi(),
                        transaksi.getTanggal(),
                        transaksi.getJenisTransaksi(),
                        transaksi.getJumlah(),
                        transaksi.getKeterangan()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransaksiForm().setVisible(true);
        });
    }
}

