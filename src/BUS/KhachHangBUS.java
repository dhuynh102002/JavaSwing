/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class KhachHangBUS {
    public static ArrayList<KhachHangDTO> dskh;

    public KhachHangBUS() {
       
    }
    
    public void docDSKH(){
        KhachHangDAO data = new KhachHangDAO();
        if(dskh==null){
            dskh = new ArrayList<KhachHangDTO>();
        }
        dskh = data.docDSKH();
    }
    
    public void themKH(KhachHangDTO kh){
        KhachHangDAO data = new KhachHangDAO();
        data.them(kh);
        dskh.add(kh);
    }
    
    public void suaKH(KhachHangDTO kh){
        KhachHangDAO data = new KhachHangDAO();
        data.sua(kh);
    }
    
    public void xoaKH(String ma){
        KhachHangDAO data = new KhachHangDAO();
        data.xoa(ma);
    }
    
    public ArrayList<KhachHangDTO> getList() throws Exception{
        ArrayList<KhachHangDTO> ds = new KhachHangDAO().docDSKH();
        return ds;
    }
    
    public KhachHangDTO timKiemMaKH(String ma){
        for (KhachHangDTO kh : dskh){
            if(kh.getMaKH().toLowerCase().contains(ma.toLowerCase())){
                return kh;
            }
        }
        return null;
    }
    public ArrayList<KhachHangDTO> timKiemHoKH(String ho){
        ArrayList<KhachHangDTO> kq=new ArrayList<>();
        for (KhachHangDTO kh : dskh){
            if(kh.getHoKH().toLowerCase().contains(ho.toLowerCase())){
               kq.add(kh);
            }
        }
        return kq;
    }
    public ArrayList<KhachHangDTO> timKiemTenKH(String ten){
        ArrayList<KhachHangDTO> kq=new ArrayList<>();
        for (KhachHangDTO kh : dskh){
            if(kh.getTenKH().toLowerCase().contains(ten.toLowerCase())){
               kq.add(kh);
            }
        }
        return kq;
    }
    public ArrayList<KhachHangDTO> timKiemGioiTinh(String gt){
        ArrayList<KhachHangDTO> kq=new ArrayList<>();
        for (KhachHangDTO kh : dskh){
            if(kh.getGioitinh().toLowerCase().contains(gt.toLowerCase())){
               kq.add(kh);
            }
        }
        return kq;
    }
    
    
    public void sortID(){
        Collections.sort(dskh, new Comparator<KhachHangDTO>() {
            @Override
            public int compare(KhachHangDTO kh1, KhachHangDTO kh2) {
                return kh1.getMaKH().compareToIgnoreCase(kh2.getMaKH());
            }
        });
    }
    
     public void importExcelKH(KhachHangDTO kh) throws Exception {
        KhachHangDAO data = new KhachHangDAO();
        data.importExcelKH(kh);
    }
}
    



