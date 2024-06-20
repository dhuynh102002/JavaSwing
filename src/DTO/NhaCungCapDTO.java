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
public class NhaCungCapDTO {
    private String idNCC;
    private String tenNCC;
    private String diachiNCC;
    private String sdtNCC;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(String idNCC, String tenNCC, String diachiNCC, String sdtNCC) {
        this.idNCC = idNCC;
        this.tenNCC = tenNCC;
        this.diachiNCC = diachiNCC;
        this.sdtNCC = sdtNCC;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiachiNCC() {
        return diachiNCC;
    }

    public void setDiachiNCC(String diachiNCC) {
        this.diachiNCC = diachiNCC;
    }

    public String getSdtNCC() {
        return sdtNCC;
    }

    public void setSdtNCC(String sdtNCC) {
        this.sdtNCC = sdtNCC;
    }
    
    
    
}
