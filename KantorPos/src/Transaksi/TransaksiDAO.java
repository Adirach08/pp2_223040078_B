/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaksi;

/**
 *
 * @author adirap
 */
import DatabaseUtil.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    // Create: Menambahkan data ke tabel transaksi
    public void tambahTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (tanggal, jenis_transaksi, jumlah, keterangan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaksi.getTanggal());
            stmt.setString(2, transaksi.getJenisTransaksi());
            stmt.setDouble(3, transaksi.getJumlah());
            stmt.setString(4, transaksi.getKeterangan());
            stmt.executeUpdate();
        }
    }

    // Read: Mengambil semua data dari tabel transaksi
    public List<Transaksi> getAllTransaksi() throws SQLException {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaksi transaksi = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getString("tanggal"),
                        rs.getString("jenis_transaksi"),
                        rs.getDouble("jumlah"),
                        rs.getString("keterangan")
                );
                transaksiList.add(transaksi);
            }
        }
        return transaksiList;
    }

    // Update: Memperbarui data pada tabel transaksi
    public void updateTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "UPDATE transaksi SET tanggal = ?, jenis_transaksi = ?, jumlah = ?, keterangan = ? WHERE id_transaksi = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaksi.getTanggal());
            stmt.setString(2, transaksi.getJenisTransaksi());
            stmt.setDouble(3, transaksi.getJumlah());
            stmt.setString(4, transaksi.getKeterangan());
            stmt.setInt(5, transaksi.getIdTransaksi());
            stmt.executeUpdate();
        }
    }

    // Delete: Menghapus data dari tabel transaksi
    public void hapusTransaksi(int idTransaksi) throws SQLException {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTransaksi);
            stmt.executeUpdate();
        }
    }
}

