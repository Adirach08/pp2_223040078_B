/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Transaksi;

/**
 *
 * @author adirap
 */
public class Transaksi {
    private int idTransaksi;
    private String tanggal;
    private String jenisTransaksi;
    private double jumlah;
    private String keterangan;

    // Constructor
    public Transaksi(int idTransaksi, String tanggal, String jenisTransaksi, double jumlah, String keterangan) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    // Getter dan Setter
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "idTransaksi=" + idTransaksi +
                ", tanggal='" + tanggal + '\'' +
                ", jenisTransaksi='" + jenisTransaksi + '\'' +
                ", jumlah=" + jumlah +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}
