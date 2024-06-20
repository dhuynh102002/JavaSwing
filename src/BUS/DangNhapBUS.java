/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DangNhapDAO;
import DAO.NhanVienDAO;
import DTO.DangNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import GUI.dangnhap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author WIN 10
 */
public class DangNhapBUS {
    public static ArrayList<DangNhapDTO> listDN;

    public DangNhapBUS() {
        
    }
    
    public void docDSNV() throws Exception{
        DangNhapDAO data = new DangNhapDAO();
        if(listDN == null){
            listDN = new ArrayList<DangNhapDTO>();
        }
        listDN = data.docDSDN();
    }
    
    public DangNhapDTO getTaiKhoan(String tendn) throws Exception {
        DangNhapDAO dnDAO = new DangNhapDAO();
        for (DangNhapDTO dnDTO : listDN) {
            if (dnDTO.getTenDangnhapDTO().equals(tendn)) {
                return dnDTO;
            }
        }
        return null;
    }
    
    public void themNV(DangNhapDTO dn) throws Exception{
//        if(!Check.checkStringNumber(String.valueOf(nv.getLuongThang()))){
//            JOptionPane.showMessageDialog(null, "Lương phải là số");
//        }
        DangNhapDAO data = new DangNhapDAO();
        data.themDN(dn);
        listDN.add(dn);
    }
    
    public void suaNV(DangNhapDTO dn, int index) throws Exception{
        DangNhapDAO data = new DangNhapDAO();
        data.suaDN(dn);
        listDN.set(index, dn);
        
    }
    
    public void xoaNV(String id, int index) throws Exception{
        DangNhapDAO data = new DangNhapDAO();
        data.xoaDN(id);
        listDN.remove(index);

    }
    
    public NhanVienDTO timAccountNhanVien(String user, String pass) {
        for (int i = 0; i < listDN.size(); i++) {
            DangNhapDTO dn = listDN.get(i);

            if (dn.getTenDangnhapDTO().equals(user) && dn.getMatkhauDangnhapDTO().equals(pass)) {
                NhanVienDTO nv = dn.getIdNhanvienDTO();
                return nv;
            }
        }

        return null;
    }
    
    public ArrayList<DangNhapDTO> timkiemTenDN(String tenDN){
        ArrayList<DangNhapDTO> dsdn = new ArrayList<>();
        for(DangNhapDTO nv : listDN){
            if(nv.getTenDangnhapDTO().toLowerCase().contains(tenDN.toLowerCase())){
                dsdn.add(nv);
            }
        }
        return dsdn;
    }
    
    public ArrayList<DangNhapDTO> timkiemTenNV(String mk){
        ArrayList<DangNhapDTO> dsnv = new ArrayList<>();
        for(DangNhapDTO nv : listDN){
            if(nv.getMatkhauDangnhapDTO().toLowerCase().contains(mk.toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
}
