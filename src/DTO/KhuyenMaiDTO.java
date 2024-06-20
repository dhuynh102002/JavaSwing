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
public class KhuyenMaiDTO {
    private String maKM;
    private String tenKM;
    private Date ngaybdKM;
    private Date ngayktKM;
    private int dieukienKM;

    public KhuyenMaiDTO() {
    }

    public KhuyenMaiDTO(String maKM, String tenKM, Date ngaybdKM, Date ngayktKM, int dieukienKM) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngaybdKM = ngaybdKM;
        this.ngayktKM = ngayktKM;
        this.dieukienKM = dieukienKM;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }    

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Date getNgaybdKM() {
        return ngaybdKM;
    }

    public void setNgaybdKM(Date ngaybdKM) {
        this.ngaybdKM = ngaybdKM;
    }

    public Date getNgayktKM() {
        return ngayktKM;
    }

    public void setNgayktKM(Date ngayktKM) {
        this.ngayktKM = ngayktKM;
    }

    public int getDieukienKM() {
        return dieukienKM;
    }

    public void setDieukienKM(int dieukienKM) {
        this.dieukienKM = dieukienKM;
    }
    
    
}
