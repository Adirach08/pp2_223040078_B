/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERTEMUAN6;

/**
 *
 * @author adirap
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankSampah extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextField namaPengirimField;
    private JTextArea deskripsiArea;
    private JRadioButton organikRadio;
    private JRadioButton anorganikRadio;
    private JCheckBox plastikCheck, kertasCheck, logamCheck;
    private JComboBox<String> kategoriCombo;
    private JSpinner beratSpinner;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JList<String> lokasiList;

    public BankSampah() {
        super("Aplikasi Bank Sampah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Setup Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem inputItem = new JMenuItem("Input Sampah");
        JMenuItem dataItem = new JMenuItem("Data Sampah");

        // Action untuk menampilkan panel input
        inputItem.addActionListener(e -> cardLayout.show(mainPanel, "inputPanel"));
        
        // Action untuk menampilkan panel data
        dataItem.addActionListener(e -> cardLayout.show(mainPanel, "dataPanel"));
        
        menu.add(inputItem);
        menu.add(dataItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel Utama dengan CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nama Pengirim
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama Pengirim:"), gbc);
        gbc.gridx = 1;
        namaPengirimField = new JTextField(20);
        inputPanel.add(namaPengirimField, gbc);

        // Deskripsi
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Deskripsi:"), gbc);
        gbc.gridx = 1;
        deskripsiArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(deskripsiArea), gbc);

        // Jenis Sampah (Organik/Anorganik)
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Jenis Sampah:"), gbc);
        gbc.gridx = 1;
        organikRadio = new JRadioButton("Organik");
        anorganikRadio = new JRadioButton("Anorganik");
        ButtonGroup jenisGroup = new ButtonGroup();
        jenisGroup.add(organikRadio);
        jenisGroup.add(anorganikRadio);
        JPanel jenisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jenisPanel.add(organikRadio);
        jenisPanel.add(anorganikRadio);
        inputPanel.add(jenisPanel, gbc);

        // Material Sampah
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Material Sampah:"), gbc);
        gbc.gridx = 1;
        plastikCheck = new JCheckBox("Plastik");
        kertasCheck = new JCheckBox("Kertas");
        logamCheck = new JCheckBox("Logam");
        JPanel materialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        materialPanel.add(plastikCheck);
        materialPanel.add(kertasCheck);
        materialPanel.add(logamCheck);
        inputPanel.add(materialPanel, gbc);

        // Kategori Sampah
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Kategori Sampah:"), gbc);
        gbc.gridx = 1;
        String[] kategori = {"Botol Plastik", "Kertas Koran", "Kaleng"};
        kategoriCombo = new JComboBox<>(kategori);
        inputPanel.add(kategoriCombo, gbc);

        // Berat Sampah
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Berat Sampah (kg):"), gbc);
        gbc.gridx = 1;
        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        beratSpinner = new JSpinner(model);
        inputPanel.add(beratSpinner, gbc);

        // Lokasi Pengiriman (Menggunakan JList)
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Lokasi Pengiriman:"), gbc);
        gbc.gridx = 1;
        String[] lokasi = {"Jakarta", "Bandung", "Bogor", "Cimahi", "Cianjur", "Garut", "Sukabumi", "Bekasi"};
        lokasiList = new JList<>(lokasi);
        lokasiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inputPanel.add(new JScrollPane(lokasiList), gbc);

        // Tombol Tambah
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton tambahButton = new JButton("Tambah Data");
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahData();
            }
        });
        inputPanel.add(tambahButton, gbc);

        // Panel Data Tabel
        JPanel dataPanel = new JPanel(new BorderLayout());
        String[] kolom = {"Nama", "Deskripsi", "Jenis", "Material", "Kategori", "Berat (kg)", "Lokasi"};
        tableModel = new DefaultTableModel(kolom, 0);
        dataTable = new JTable(tableModel);
        dataPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

        // Tambah panel ke CardLayout
        mainPanel.add(inputPanel, "inputPanel");
        mainPanel.add(dataPanel, "dataPanel");

        // Set layout utama
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Tampilkan form input saat aplikasi pertama kali dibuka
        cardLayout.show(mainPanel, "inputPanel");

        setVisible(true);
    }

    private void tambahData() {
        // Ambil data dari form
        String nama = namaPengirimField.getText();
        String deskripsi = deskripsiArea.getText();
        String jenis = organikRadio.isSelected() ? "Organik" : anorganikRadio.isSelected() ? "Anorganik" : "Tidak Ditentukan";

        StringBuilder material = new StringBuilder();
        if (plastikCheck.isSelected()) material.append("Plastik ");
        if (kertasCheck.isSelected()) material.append("Kertas ");
        if (logamCheck.isSelected()) material.append("Logam ");

        String kategori = (String) kategoriCombo.getSelectedItem();
        int berat = (int) beratSpinner.getValue();
        String lokasi = lokasiList.getSelectedValue();

        // Validasi input lokasi
        if (lokasi == null) {
            JOptionPane.showMessageDialog(this, "Pilih lokasi pengiriman!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tambah data ke tabel
        Object[] rowData = {nama, deskripsi, jenis, material.toString(), kategori, berat, lokasi};
        tableModel.addRow(rowData);

        // Reset form input
        namaPengirimField.setText("");
        deskripsiArea.setText("");
        organikRadio.setSelected(false);
        anorganikRadio.setSelected(false);
        plastikCheck.setSelected(false);
        kertasCheck.setSelected(false);
        logamCheck.setSelected(false);
        kategoriCombo.setSelectedIndex(0);
        beratSpinner.setValue(1);
        lokasiList.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankSampah::new);
    }
}