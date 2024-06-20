/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author dhuynh
 */
public class ChiTietPhieuNhapDTO {
    private String idPhieuNhap;
    private String idSanPham;
    private int soluong;
    private int dongia;
    private int tongtiennhap;

    public ChiTietPhieuNhapDTO() {
    }    

    public ChiTietPhieuNhapDTO(String idPhieuNhap, String idSanPham, int soluong, int dongia, int tongtiennhap) {
        this.idPhieuNhap = idPhieuNhap;
        this.idSanPham = idSanPham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtiennhap = tongtiennhap;
    }

    public String getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(String idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getTongtiennhap() {
        return tongtiennhap;
    }

    public void setTongtiennhap(int tongtiennhap) {
        this.tongtiennhap = tongtiennhap;
    }

    
}
