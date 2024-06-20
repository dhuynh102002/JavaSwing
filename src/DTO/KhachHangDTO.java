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
public class KhachHangDTO {
    private String maKH;
    private String hoKH;
    private String tenKH;
    private String gioitinh;
    private String diachi;
    private String sdt;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String maKH, String hoKH, String tenKH, String gioitinh, String diachi, String sdt) {
        this.maKH = maKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoKH() {
        return hoKH;
    }

    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
