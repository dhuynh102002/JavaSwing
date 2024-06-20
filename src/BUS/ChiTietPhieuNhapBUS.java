/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dhuynh
 */
public class ChiTietPhieuNhapBUS {
    public static ArrayList<ChiTietPhieuNhapDTO> listCTPN;

    public ChiTietPhieuNhapBUS() {
        
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getList() throws Exception{
        ArrayList<ChiTietPhieuNhapDTO> ds= new ChiTietPhieuNhapDAO().docDSCTPN();
        
        return ds;
    }
    
    public void docDSCTPN() throws Exception{
        ChiTietPhieuNhapDAO data = new ChiTietPhieuNhapDAO();
        if(listCTPN == null){
            listCTPN = new ArrayList<ChiTietPhieuNhapDTO>();
        }
        listCTPN = data.docDSCTPN();
    }
    
    public boolean themCTPN(ChiTietPhieuNhapDTO ctpn) throws Exception{
        ChiTietPhieuNhapDAO data = new ChiTietPhieuNhapDAO();
        boolean result = data.themCTPN(ctpn);
        if(result){
            listCTPN.add(ctpn);
        }
        return result;
    }
    
    public boolean suaCTPN(ChiTietPhieuNhapDTO ctpn) throws Exception{
        ChiTietPhieuNhapDAO data = new ChiTietPhieuNhapDAO();
        boolean result = data.suaCTPN(ctpn);
        return result;
    }
    
    public boolean xoaCTPN(String idPN, String idSP) throws Exception{
        ChiTietPhieuNhapDAO data = new ChiTietPhieuNhapDAO();
        boolean result = data.xoaCTPN(idPN, idSP);
        return result;
    }
    
    public ChiTietPhieuNhapDTO timkiemCTPN(String id, ArrayList<ChiTietPhieuNhapDTO> list){
        for(ChiTietPhieuNhapDTO ctpn : list){
            if(ctpn.getIdPhieuNhap().equals(id)){
                return ctpn;
            }
        }
        return null;
    }
    
}
