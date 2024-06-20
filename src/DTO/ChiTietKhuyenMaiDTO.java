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
public class ChiTietKhuyenMaiDTO {
    private String maKM;
    private String maSP;
    private int phantramKM;

    public ChiTietKhuyenMaiDTO() {
    }

    public ChiTietKhuyenMaiDTO(String maKM, String maSP, int phantramKM) {
        this.maKM = maKM;
        this.maSP = maSP;
        this.phantramKM = phantramKM;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getPhantramKM() {
        return phantramKM;
    }

    public void setPhantramKM(int phantramKM) {
        this.phantramKM = phantramKM;
    }
    
    
}
