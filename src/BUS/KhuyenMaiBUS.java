/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author 
 */
public class KhuyenMaiBUS {
    public static ArrayList<KhuyenMaiDTO> listKM;

    public void docDSKM() throws Exception{
        KhuyenMaiDAO data = new KhuyenMaiDAO();
        if(listKM == null){
            listKM = new ArrayList<KhuyenMaiDTO>();
        }
        listKM = data.docDSKM();
    }
    
    public boolean themKM(KhuyenMaiDTO km) throws Exception{
        boolean result = new KhuyenMaiDAO().themKM(km);
        if(result){
            listKM.add(km);
        }
        return result;
    }
    public boolean xoaKM(String id) throws Exception{
        boolean result = new KhuyenMaiDAO().xoaKM(id);
        return result;
    }
    public boolean suaKM(KhuyenMaiDTO km) throws Exception{
        boolean result = new KhuyenMaiDAO().suaKM(km);
        return result;
    }
    
    
    public ArrayList<KhuyenMaiDTO> getList() throws Exception{
        ArrayList<KhuyenMaiDTO> listKM= new KhuyenMaiDAO().docDSKM();
        
        return listKM;
    }
    
    public KhuyenMaiDTO timkiemMaKM(String id){
        for(KhuyenMaiDTO km : listKM){
            if(km.getMaKM().toLowerCase().equals(id.toLowerCase())){
                return km;
            }
        }
        return null;
    }
    
    public ArrayList<KhuyenMaiDTO> timkiemTenKM(String tenKM){
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        for(KhuyenMaiDTO km : listKM){
            if(km.getTenKM().toLowerCase().contains(tenKM.toLowerCase())){
                dskm.add(km);
            }
        }
        return dskm;
    }
    
    public ArrayList<KhuyenMaiDTO> timkiemDieuKienKM(int dieukien){
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        for(KhuyenMaiDTO km : listKM){
            if(km.getDieukienKM()==dieukien){
                dskm.add(km);
            }
        }
        return dskm;
    }
    
    public void sortID(){
        Collections.sort(listKM, new Comparator<KhuyenMaiDTO>() {
            @Override
            public int compare(KhuyenMaiDTO km1, KhuyenMaiDTO km2) {
                return km1.getMaKM().compareToIgnoreCase(km2.getMaKM());
            }
        });
        
    }
}
