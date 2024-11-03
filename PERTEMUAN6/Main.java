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

public class Main {
    public static void main(String[] args) {
        // Jalankan aplikasi di Event Dispatch Thread (EDT) untuk memastikan thread-safe Swing components
        SwingUtilities.invokeLater(() -> {
            new BankSampah();  // Membuat instance dari BankSampah untuk menampilkan jendela utama
        });
    }
}
