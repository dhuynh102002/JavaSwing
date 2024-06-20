/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author 
 */
public class HoaDonBUS {
    public static ArrayList<HoaDonDTO> listHD;

    public void docDSHD() throws Exception{
        HoaDonDAO data = new HoaDonDAO();
        if(listHD == null){
            listHD = new ArrayList<HoaDonDTO>();
        }
        listHD = data.docDSHD();
    }
    
    public boolean themHD(HoaDonDTO hd) throws Exception{
        boolean result = new HoaDonDAO().themHD(hd);
        if(result){
            listHD.add(hd);
        }
        return result;
    }
    public boolean xoaHD(String id) throws Exception{
        boolean result = new HoaDonDAO().xoaHD(id);
        return result;
    }
    public boolean suaHD(HoaDonDTO hd) throws Exception{
        boolean result = new HoaDonDAO().suaHD(hd);
        return result;
    }
    
    
    public ArrayList<HoaDonDTO> getList() throws Exception{
        ArrayList<HoaDonDTO> listHD= new HoaDonDAO().docDSHD();
        
        return listHD;
    }
    
    public HoaDonDTO timkiemMaHD(String id){
        for(HoaDonDTO hd : listHD){
            if(hd.getIdHoadon().toLowerCase().equals(id.toLowerCase())){
                return hd;
            }
        }
        return null;
    }
    
    public ArrayList<HoaDonDTO> timkiemMaNVHD(String idNV){
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        for(HoaDonDTO hd : listHD){
            if(hd.getIdNhanvien().toLowerCase().contains(idNV.toLowerCase())){
                dshd.add(hd);
            }
        }
        return dshd;
    }
    
    public ArrayList<HoaDonDTO> timkiemMaKHHD(String idKH){
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        for(HoaDonDTO hd : listHD){
            if(hd.getIdKhachhang().toLowerCase().contains(idKH.toLowerCase())){
                dshd.add(hd);
            }
        }
        return dshd;
    }
    
    public ArrayList<HoaDonDTO> timkiemMaKMHD(String idKM){
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        for(HoaDonDTO hd : listHD){
            if(hd.getIdKhuyenmai().toLowerCase().contains(idKM.toLowerCase())){
                dshd.add(hd);
            }
        }
        return dshd;
    }
    
    public boolean UpdateTongTienHD(HoaDonDTO hd){
        boolean result = new HoaDonDAO().UpdateTongTienHD(hd);
        return result;
    }
    
    public void sortID(){
        Collections.sort(listHD, new Comparator<HoaDonDTO>() {
            @Override
            public int compare(HoaDonDTO hd1, HoaDonDTO hd2) {
                return hd1.getIdHoadon().compareToIgnoreCase(hd2.getIdHoadon());
            }
        });
        
    }
}
