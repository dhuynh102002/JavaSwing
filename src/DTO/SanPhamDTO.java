/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class SanPhamDTO {
    private String masp;
    private String tensp;
    private String maloai;
    private int dongia;
    private int soluong;
    private String DVT;
    private boolean trangthai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String masp, String tensp, String maloai, int dongia, int soluong, String DVT, boolean trangthai) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.dongia = dongia;
        this.soluong = soluong;
        this.DVT = DVT;
        this.trangthai = trangthai;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
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

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

}
