/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ChiTietHoaDonBUS {
    public static ArrayList<ChiTietHoaDonDTO> listCTHD;

    public void docDSCTHD() throws Exception{
        ChiTietHoaDonDAO data = new ChiTietHoaDonDAO();
        if(listCTHD == null){
            listCTHD = new ArrayList<ChiTietHoaDonDTO>();
        }
        listCTHD = data.docDSCTHD();
    }
    
    public boolean themCTHD(ChiTietHoaDonDTO cthd) throws Exception{
        boolean result = new ChiTietHoaDonDAO().themCTHD(cthd);
        if(result){
            listCTHD.add(cthd);
        }
        return result;
    }
    public boolean xoaCTHD(String idPN, String idSP) throws Exception{
        boolean result = new ChiTietHoaDonDAO().xoaCTHD(idPN, idSP);
        return result;
    }
    public boolean suaCTHD(ChiTietHoaDonDTO cthd) throws Exception{
        boolean result = new ChiTietHoaDonDAO().suaCTHD(cthd);
        return result;
    }
    
    public ArrayList<ChiTietHoaDonDTO> getListCthd(ArrayList<ChiTietHoaDonDTO> list, String id) {
        ArrayList<ChiTietHoaDonDTO> lt = new ArrayList<>();
        for (ChiTietHoaDonDTO cthd : list) {
            if (cthd.getMaHD().equals(id)) {
                lt.add(cthd);
            }
        }
        return lt;
    }
    
    
    public ArrayList<ChiTietHoaDonDTO> getList() throws Exception{
        ArrayList<ChiTietHoaDonDTO> listCTHD= new ChiTietHoaDonDAO().docDSCTHD();
        return listCTHD;
    }
}
