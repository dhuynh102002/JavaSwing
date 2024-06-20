/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author 
 */
public class HoaDonDTO {
    private String idHoadon;
    private String idNhanvien;
    private String idKhachhang;
    private String idKhuyenmai;
    private Date ngaylapHoadon;
    private int tongtienHoadon;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String idHoadon, String idNhanvien, String idKhachhang, String idKhuyenmai, Date ngaylapHoadon, int tongtienHoadon) {
        this.idHoadon = idHoadon;
        this.idNhanvien = idNhanvien;
        this.idKhachhang = idKhachhang;
        this.idKhuyenmai = idKhuyenmai;
        this.ngaylapHoadon = ngaylapHoadon;
        this.tongtienHoadon = tongtienHoadon;
    }

    public String getIdHoadon() {
        return idHoadon;
    }

    public void setIdHoadon(String idHoadon) {
        this.idHoadon = idHoadon;
    }

    public String getIdNhanvien() {
        return idNhanvien;
    }

    public void setIdNhanvien(String idNhanvien) {
        this.idNhanvien = idNhanvien;
    }

    public String getIdKhachhang() {
        return idKhachhang;
    }

    public void setIdKhachhang(String idKhachhang) {
        this.idKhachhang = idKhachhang;
    }

    public String getIdKhuyenmai() {
        return idKhuyenmai;
    }

    public void setIdKhuyenmai(String idKhuyenmai) {
        this.idKhuyenmai = idKhuyenmai;
    }

    public Date getNgaylapHoadon() {
        return ngaylapHoadon;
    }

    public void setNgaylapHoadon(Date ngaylapHoadon) {
        this.ngaylapHoadon = ngaylapHoadon;
    }

    public int getTongtienHoadon() {
        return tongtienHoadon;
    }

    public void setTongtienHoadon(int tongtienHoadon) {
        this.tongtienHoadon = tongtienHoadon;
    }
    
    
}
