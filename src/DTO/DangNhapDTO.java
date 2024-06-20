/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author WIN 10
 */
public class DangNhapDTO {
    private NhanVienDTO idNhanvienDTO;
    private String tenDangnhapDTO;
    private String matkhauDangnhapDTO;

    public DangNhapDTO() {
    }

    
    public DangNhapDTO(NhanVienDTO idNhanvienDTO, String tenDangnhapDTO, String matkhauDangnhapDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
        this.tenDangnhapDTO = tenDangnhapDTO;
        this.matkhauDangnhapDTO = matkhauDangnhapDTO;
    }

    public NhanVienDTO getIdNhanvienDTO() {
        return idNhanvienDTO;
    }

    public void setIdNhanvienDTO(NhanVienDTO idNhanvienDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
    }

    public String getTenDangnhapDTO() {
        return tenDangnhapDTO;
    }

    public void setTenDangnhapDTO(String tenDangnhapDTO) {
        this.tenDangnhapDTO = tenDangnhapDTO;
    }

    public String getMatkhauDangnhapDTO() {
        return matkhauDangnhapDTO;
    }

    public void setMatkhauDangnhapDTO(String matkhauDangnhapDTO) {
        this.matkhauDangnhapDTO = matkhauDangnhapDTO;
    }
    
    
}
