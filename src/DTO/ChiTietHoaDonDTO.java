/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author 
 */
public class ChiTietHoaDonDTO {
    private String maHD;
    private String maSP;
    private int dongia;
    private int soluong;
    private int tongtien;

    public ChiTietHoaDonDTO() {
    }

    public ChiTietHoaDonDTO(String maHD, String maSP, int dongia, int soluong, int tongtien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.dongia = dongia;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
    
    
    
}
