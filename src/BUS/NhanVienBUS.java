/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.dangnhap;
import Tool.Check;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author dhuynh
 */
public class NhanVienBUS {
    public static ArrayList<NhanVienDTO> listNV;

    public NhanVienBUS() {
        
    }
    
    public ArrayList<NhanVienDTO> getList() throws Exception{
        ArrayList<NhanVienDTO> ds= new NhanVienDAO().docDSNV();
        
        return ds;
    }
    
    public void docDSNV() throws Exception{
        NhanVienDAO data = new NhanVienDAO();
        if(listNV == null){
            listNV = new ArrayList<NhanVienDTO>();
        }
        listNV = data.docDSNV();
    }
    
    public void themNV(NhanVienDTO nv) throws Exception{
        NhanVienDAO data = new NhanVienDAO();
        data.themNV(nv);
        listNV.add(nv);
    }
    
    public void suaNV(NhanVienDTO nv) throws Exception{
        NhanVienDAO data = new NhanVienDAO();
        data.suaNV(nv);
    }
    
    public void xoaNV(String id) throws Exception{
        NhanVienDAO data = new NhanVienDAO();
        data.xoaNV(id);
    }
    
    public NhanVienDTO thongTinNhanVien() {
        NhanVienDTO nhanVien = new NhanVienDTO();

        for (int i = 0; i < listNV.size(); i++) {
            nhanVien = listNV.get(i);

            if (dangnhap.idHienHanh.equals(nhanVien.getIdNV())) {
                return nhanVien;
            }
        }
        return nhanVien;
    }
    
    public NhanVienDTO timkiemMaNV(String id){
        for(NhanVienDTO nv : listNV){
            if(nv.getIdNV().toLowerCase().equals(id.toLowerCase())){
                return nv;
            }
        }
        return null;
    }
    
    public ArrayList<NhanVienDTO> timkiemHoNV(String ho){
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        for(NhanVienDTO nv : listNV){
            if(nv.getHo().toLowerCase().contains(ho.toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
    
    public ArrayList<NhanVienDTO> timkiemTenNV(String ten){
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        for(NhanVienDTO nv : listNV){
            if(nv.getTen().toLowerCase().contains(ten.toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
    
    public ArrayList<NhanVienDTO> timkiemGioiTinhNV(String gt){
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        for(NhanVienDTO nv : listNV){
            if(nv.getGioitinh().toLowerCase().contains(gt.toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
    
    public ArrayList<NhanVienDTO> timkiemChucVuNV(String chucvu){
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        for(NhanVienDTO nv : listNV){
            if(nv.getChucvu().toLowerCase().contains(chucvu.toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
    
    public ArrayList<NhanVienDTO> timkiemTrangThaiNV(String trangthai){
        ArrayList<NhanVienDTO> dsnvhienhanh = new ArrayList<>();
        ArrayList<NhanVienDTO> dsnvdanghi = new ArrayList<>();
        int t=0;
        for(NhanVienDTO nv : listNV){
            if(nv.isTrangthai() && "Hiện hành".toLowerCase().contains(trangthai.toLowerCase())){
                dsnvhienhanh.add(nv);
                t=1;
            }
            else if(!nv.isTrangthai() && "Đã nghỉ".toLowerCase().contains(trangthai.toLowerCase())){
                dsnvdanghi.add(nv);
                t=2;
            }
        }
        if(t==1){
            return dsnvhienhanh;
        }
        else if(t==2){
            return dsnvdanghi;
        }
        return null;
    }
    
    public void sortID(){
        Collections.sort(listNV, new Comparator<NhanVienDTO>() {
            @Override
            public int compare(NhanVienDTO nv1, NhanVienDTO nv2) {
                return nv1.getIdNV().compareToIgnoreCase(nv2.getIdNV());
            }
        });
        
    }
    
    public void importExcelNV(NhanVienDTO nv) throws Exception {
        NhanVienDAO data = new NhanVienDAO();
        data.importExcelNV(nv);
    }
    
}
