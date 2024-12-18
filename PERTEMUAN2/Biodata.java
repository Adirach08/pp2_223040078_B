/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERTEMUAN2;

/**
 *
 * @author adirap
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Biodata extends JFrame {
    private JTextField textFieldNama, textFieldHP;
    private JRadioButton radioLakiLaki, radioPerempuan;
    private JTextArea txtOutput;
    private JList<String> listTabungan;
    private JSpinner spinnerTransaksi, spinnerTanggalLahir;
    private JPasswordField passwordField, confirmPasswordField;
    private JScrollPane scrollPane;

    public Biodata() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 850);
        this.setTitle("Biodata");
        this.setLayout(null);


        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");

        menuItemReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(menuItemReset);
        menu.add(menuItemExit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

 
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 350, 20);
        this.add(labelNama);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 50, 350, 30);
        this.add(textFieldNama);

        JLabel labelHP = new JLabel("Nomor HP:");
        labelHP.setBounds(15, 90, 350, 20);
        this.add(labelHP);

        textFieldHP = new JTextField();
        textFieldHP.setBounds(15, 120, 350, 30);
        this.add(textFieldHP);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 150, 100, 20);
        this.add(labelJenisKelamin);

        radioLakiLaki = new JRadioButton("Laki-Laki");
        radioLakiLaki.setBounds(15, 180, 350, 30);
        this.add(radioLakiLaki);

        radioPerempuan = new JRadioButton("Perempuan");
        radioPerempuan.setBounds(15, 210, 350, 30);
        this.add(radioPerempuan);

        ButtonGroup group = new ButtonGroup();
        group.add(radioLakiLaki);
        group.add(radioPerempuan);

        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(15, 270, 350, 20);
        this.add(labelTabungan);

        String[] jenisTabungan = {"Tabungan Reguler", "Tabungan Pendidikan", "Tabungan Bisnis"};
        listTabungan = new JList<>(jenisTabungan);
        listTabungan.setBounds(15, 300, 350, 50);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(listTabungan);

        JLabel labelTransaksi = new JLabel("Frekuensi Transaksi per Bulan:");
        labelTransaksi.setBounds(15, 390, 350, 20);
        this.add(labelTransaksi);

        SpinnerModel modelSpinner = new SpinnerNumberModel(1, 1, 100, 1);
        spinnerTransaksi = new JSpinner(modelSpinner);
        spinnerTransaksi.setBounds(280, 390, 50, 30);
        this.add(spinnerTransaksi);

        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 430, 350, 20);
        this.add(labelTanggalLahir);

        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        spinnerTanggalLahir.setBounds(15, 460, 200, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        this.add(spinnerTanggalLahir);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(15, 500, 350, 20);
        this.add(labelPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(15, 530, 350, 30);
        this.add(passwordField);

        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(15, 570, 350, 20);
        this.add(labelConfirmPassword);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(15, 600, 350, 30);
        this.add(confirmPasswordField);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 640, 100, 30);
        this.add(buttonSimpan);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);

        scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBounds(15, 680, 350, 150);
        this.add(scrollPane);

        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanBiodata();
            }
        });

        this.setVisible(true);
    }

    private void simpanBiodata() {
        String nama = textFieldNama.getText();
        String nomorHP = textFieldHP.getText();
        String jenisKelamin = radioLakiLaki.isSelected() ? "Laki-Laki" : radioPerempuan.isSelected() ? "Perempuan" : "Tidak Dipilih";
        String tabungan = listTabungan.getSelectedValue() != null ? listTabungan.getSelectedValue() : "Tidak Dipilih";
        int frekuensiTransaksi = (int) spinnerTransaksi.getValue();


        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        String passwordStatus = password.equals(confirmPassword) ? "Password cocok" : "Password tidak cocok";


        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tanggalLahirStr = sdf.format(tanggalLahir);


        txtOutput.append("Nama       : " + nama + "\n");
        txtOutput.append("Nomor HP   : " + nomorHP + "\n");
        txtOutput.append("Jenis Kelamin : " + jenisKelamin + "\n");
        txtOutput.append("Jenis Tabungan : " + tabungan + "\n");
        txtOutput.append("Frekuensi Transaksi : " + frekuensiTransaksi + "\n");
        txtOutput.append("Tanggal Lahir : " + tanggalLahirStr + "\n");
        txtOutput.append("Status Password: " + passwordStatus + "\n");
        txtOutput.append("============================================\n");
        resetFields();
    }

    private void resetFields() {
        textFieldNama.setText("");
        textFieldHP.setText("");
        radioLakiLaki.setSelected(false);
        radioPerempuan.setSelected(false);
        listTabungan.clearSelection();
        spinnerTransaksi.setValue(1);
        spinnerTanggalLahir.setValue(new Date());
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Biodata());
    }
}
