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
public class PhieuNhapDTO {
    private String idPN;
    private Date ngayNhap;
    private String idNCC;
    private String idNhanVien;
    private int tongsotien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String idPN, Date ngayNhap, String idNCC, String idNhanVien, int tongsotien) {
        this.idPN = idPN;
        this.ngayNhap = ngayNhap;
        this.idNCC = idNCC;
        this.idNhanVien = idNhanVien;
        this.tongsotien = tongsotien;
    }

    public String getIdPN() {
        return idPN;
    }

    public void setIdPN(String idPN) {
        this.idPN = idPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getTongsotien() {
        return tongsotien;
    }

    public void setTongsotien(int tongsotien) {
        this.tongsotien = tongsotien;
    }

    
}
