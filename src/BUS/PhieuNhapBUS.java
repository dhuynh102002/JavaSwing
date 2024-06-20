/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dhuynh
 */
public class PhieuNhapBUS {
    public static ArrayList<PhieuNhapDTO> listPN;

    public PhieuNhapBUS() {
        
    }
    
    public ArrayList<PhieuNhapDTO> getList() throws Exception{
        ArrayList<PhieuNhapDTO> ds= new PhieuNhapDAO().docDSPN();
        
        return ds;
    }
    
    
    public void docDSPN() throws Exception{
        PhieuNhapDAO data = new PhieuNhapDAO();
        if(listPN == null){
            listPN = new ArrayList<PhieuNhapDTO>();
        }
        listPN = data.docDSPN();
    }
    
    public boolean themPN(PhieuNhapDTO pn) throws Exception{
        PhieuNhapDAO data = new PhieuNhapDAO();
        boolean result = data.themPN(pn);
        if(result){
            listPN.add(pn);
        }
        return result;
    }
    
    public boolean suaPN(PhieuNhapDTO pn) throws Exception{
        PhieuNhapDAO data = new PhieuNhapDAO();
        boolean result = data.suaPN(pn);
        return result;
    }
    
    public boolean xoaPN(String id) throws Exception{
        PhieuNhapDAO data = new PhieuNhapDAO();
        boolean result = data.xoaPN(id);
        return result;
    }
    
    public boolean UpdateTongSoTienPN(PhieuNhapDTO pn){
        boolean result = new PhieuNhapDAO().UpdateTongSoTienPN(pn);
        return result;
    }
    
    public PhieuNhapDTO timkiemMaPN(String id){
        for(PhieuNhapDTO pn : listPN){
            if(pn.getIdPN().toLowerCase().equals(id.toLowerCase())){
                return pn;
            }
        }
        return null;
    }
    
    public ArrayList<PhieuNhapDTO> timkiemMaNVPN(String idNV){
        ArrayList<PhieuNhapDTO> dspn = new ArrayList<>();
        for(PhieuNhapDTO pn : listPN){
            if(pn.getIdNhanVien().toLowerCase().contains(idNV.toLowerCase())){
                dspn.add(pn);
            }
        }
        return dspn;
    }
    
    public ArrayList<PhieuNhapDTO> timkiemMaNCCPN(String idNCC){
        ArrayList<PhieuNhapDTO> dspn = new ArrayList<>();
        for(PhieuNhapDTO pn : listPN){
            if(pn.getIdNCC().toLowerCase().contains(idNCC.toLowerCase())){
                dspn.add(pn);
            }
        }
        return dspn;
    }
    
    public void sortID(){
        Collections.sort(listPN, new Comparator<PhieuNhapDTO>() {
            @Override
            public int compare(PhieuNhapDTO nv1, PhieuNhapDTO nv2) {
                return nv1.getIdPN().compareToIgnoreCase(nv2.getIdPN());
            }
        });
        
    }
}
