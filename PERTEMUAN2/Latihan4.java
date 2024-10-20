/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERTEMUAN2;

/**
 *
 * @author adirap
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Latihan4 extends JFrame implements ActionListener {
    private JTextField kotakNama;
    private JTextField kotakNoHp;
    private JButton tombol;
    private JTextArea areaBiodata;

    public Latihan4() {
        this.setTitle("PERTEMUAN 2 - LATIHAN 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(null);

        JLabel LabelNama = new JLabel("Nama :");
        LabelNama.setBounds(50, 20, 100, 30);
        kotakNama = new JTextField();
        kotakNama.setBounds(120, 20, 200, 30);

        JLabel LabelNoHp = new JLabel("No Hp :");
        LabelNoHp.setBounds(50, 50, 100, 30);
        kotakNoHp = new JTextField();
        kotakNoHp.setBounds(120, 50, 200, 30);
        tombol = new JButton("Simpan");
        tombol.setBounds(120, 80, 100, 30);
        tombol.addActionListener(this);

        areaBiodata = new JTextArea();
        areaBiodata.setBounds(50, 130, 300, 250);
        areaBiodata.setEditable(false);

        add(LabelNama);
        add(kotakNama);
        add(LabelNoHp);
        add(kotakNoHp);
        add(tombol);
        add(areaBiodata);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = kotakNama.getText();
        String noHp = kotakNoHp.getText();

        String biodata = "Nama: " + nama + "\nNomor Telepon: " + noHp + "\n" + "=".repeat(30) + "\n";

        areaBiodata.append(biodata);

        kotakNama.setText("");
        kotakNoHp.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Latihan4();
        });
    }
}
