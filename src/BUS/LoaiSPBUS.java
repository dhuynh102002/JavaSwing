/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiSPDAO;
import DTO.LoaiSPDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class LoaiSPBUS {
    public static ArrayList<LoaiSPDTO> dsLoaiSP;

    public LoaiSPBUS() {
       
    }
    
    public void docDSLoaiSP(){
        LoaiSPDAO data = new LoaiSPDAO();
        if(dsLoaiSP==null){
            dsLoaiSP = new ArrayList<LoaiSPDTO>();
        }
        dsLoaiSP = data.docDSLoaiSP();
    }
    
    public void themLoaiSP(LoaiSPDTO sp){
        LoaiSPDAO data = new LoaiSPDAO();
        data.them(sp);
        dsLoaiSP.add(sp);
    }
    
    public void suaLoaiSP(LoaiSPDTO sp){
        LoaiSPDAO data = new LoaiSPDAO();
        data.sua(sp);
    }
    
    public void xoaLoaiSP(String ma){
        LoaiSPDAO data = new LoaiSPDAO();
        data.xoa(ma);
    }
    
    public LoaiSPDTO timKiemMaLoai(String ma){
        for (LoaiSPDTO loaisp : dsLoaiSP){
            if(loaisp.getMaloai().toLowerCase().equals(ma.toLowerCase())){
                return loaisp;
            }
        }
        return null;
    }
    
    public ArrayList<LoaiSPDTO> timKiemTenLoai(String ten){
        ArrayList<LoaiSPDTO> ds = new ArrayList<>();
        for(LoaiSPDTO loaisp : dsLoaiSP){
            if(loaisp.getTenloai().toLowerCase().contains(ten.toLowerCase())){
                ds.add(loaisp);
            }
        }
        return ds;
    }
    public void sortID(){
        Collections.sort(dsLoaiSP, new Comparator<LoaiSPDTO>() {
            @Override
            public int compare(LoaiSPDTO Loaisp1, LoaiSPDTO Loaisp2) {
                return Loaisp1.getMaloai().compareToIgnoreCase(Loaisp2.getMaloai());
            }
        });
    }
    
    public ArrayList<LoaiSPDTO> getList() throws Exception{
        ArrayList<LoaiSPDTO> ds= new LoaiSPDAO().docDSLoaiSP();
        return ds;
    }
    
    public void importExcelLoaiSP(LoaiSPDTO loaisp) throws Exception {
        LoaiSPDAO data = new LoaiSPDAO();
        data.importExcelLoaiSP(loaisp);
    }
}
