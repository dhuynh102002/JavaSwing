/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class SanPhamBUS {
    public static ArrayList<SanPhamDTO> dssp;

    public SanPhamBUS() {
       
    }
    
    public ArrayList<SanPhamDTO> getList(){
        ArrayList<SanPhamDTO> ds= new SanPhamDAO().docDSSP();
        return ds;
    }
    
    public void docDSSP(){
        SanPhamDAO data = new SanPhamDAO();
        if(dssp==null){
            dssp = new ArrayList<SanPhamDTO>();
        }
        dssp = data.docDSSP();
    }
    
    public void themSP(SanPhamDTO sp){
        SanPhamDAO data = new SanPhamDAO();
        data.them(sp);
        dssp.add(sp);
    }
    
    public void suaSP(SanPhamDTO sp) throws Exception{
        SanPhamDAO data = new SanPhamDAO();
        data.sua(sp);
    }
    
    public void xoaSP(String ma){
        SanPhamDAO data = new SanPhamDAO();
        data.xoa(ma);
    }
    
    public boolean UpdateSoluongSP(SanPhamDTO sp) throws Exception{
        boolean result = new SanPhamDAO().UpdateSoLuongSP(sp);
        return result;
    }
    
    public SanPhamDTO timKiemMaSP(ArrayList<SanPhamDTO> list, String ma){
        for (SanPhamDTO sp : list){
            if(sp.getMasp().toLowerCase().equals(ma.toLowerCase())){
                return sp;
            }
        }
        return null;
    }
    
    public ArrayList<SanPhamDTO> timKiemTenSP(ArrayList<SanPhamDTO> list, String ten){
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for(SanPhamDTO sp : list){
            if(sp.getTensp().toLowerCase().contains(ten.toLowerCase())){
                ds.add(sp);
            }
        }
        return ds;
    }
    
    public ArrayList<SanPhamDTO> timKiemMaLoaiSP(ArrayList<SanPhamDTO> list, String maloai){
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for(SanPhamDTO sp : list){
            if(sp.getMaloai().toLowerCase().contains(maloai.toLowerCase())){
                ds.add(sp);
            }
        }
        return ds;
    }
    
    public ArrayList<SanPhamDTO> timKiemTrangthaiSP(String trangthai){
        ArrayList<SanPhamDTO> dsDangBan = new ArrayList<>();
        ArrayList<SanPhamDTO> dsHetHang = new ArrayList<>();
        int t=0;
        for(SanPhamDTO sp : dssp){
            if(sp.getTrangthai() && "Đang bán".toLowerCase().contains(trangthai.toLowerCase())){
                dsDangBan.add(sp);
                t=1;
            }
            else if(!sp.getTrangthai() && "Hết hàng".toLowerCase().contains(trangthai.toLowerCase())){
                dsHetHang.add(sp);
                t=2;
            }
        }
        if(t==1){
            return dsDangBan;
        }
        else if(t==2){
            return dsHetHang;
        }
        return null;
    }
    
    public void sortID(){
        Collections.sort(dssp, new Comparator<SanPhamDTO>() {
            @Override
            public int compare(SanPhamDTO sp1, SanPhamDTO sp2) {
                return sp1.getMasp().compareToIgnoreCase(sp2.getMasp());
            }
        });
    }
    public String getTenSP(String maSP) {
        for (SanPhamDTO sp : dssp) {
            if (sp.getMasp().equals(maSP)) {
                return sp.getTensp();
            }
        }
        return "";
    }
    
    public void importExcelSP(SanPhamDTO sp) throws Exception {
        SanPhamDAO data = new SanPhamDAO();
        data.importExcelSP(sp);
    }
}
