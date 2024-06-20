/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author dhuynh
 */
public class NhanVienDTO {
    private String idNV;
    private String ho;
    private String ten;
    private String gioitinh;
    private Date ngaySinh;
    private String sdt;
    private String diachi;
    private int luongThang;
    private String chucvu;
    private boolean trangthai;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String idNV, String ho, String ten, String gioitinh, Date ngaySinh, String sdt, String diachi, int luongThang, String chucvu, boolean trangthai) {
        this.idNV = idNV;
        this.ho = ho;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.luongThang = luongThang;
        this.chucvu = chucvu;
        this.trangthai = trangthai;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getLuongThang() {
        return luongThang;
    }

    public void setLuongThang(int luongThang) {
        this.luongThang = luongThang;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    
}
