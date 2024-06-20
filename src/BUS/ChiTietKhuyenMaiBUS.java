/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietKhuyenMaiDAO;
import DTO.ChiTietKhuyenMaiDTO;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ChiTietKhuyenMaiBUS {
    public static ArrayList<ChiTietKhuyenMaiDTO> listCTKM;

    public void docDSCTKM() throws Exception{
        ChiTietKhuyenMaiDAO data = new ChiTietKhuyenMaiDAO();
        if(listCTKM == null){
            listCTKM = new ArrayList<ChiTietKhuyenMaiDTO>();
        }
        listCTKM = data.docDSCTKM();
    }
    
    public boolean themCTKM(ChiTietKhuyenMaiDTO ctkm) throws Exception{
        boolean result = new ChiTietKhuyenMaiDAO().themCTKM(ctkm);
        if(result){
            listCTKM.add(ctkm);
        }
        return result;
    }
    public boolean xoaCTKM(String idKM, String idSP) throws Exception{
        boolean result = new ChiTietKhuyenMaiDAO().xoaCTKM(idKM, idSP);
        return result;
    }
    public boolean suaCTKM(ChiTietKhuyenMaiDTO km) throws Exception{
        boolean result = new ChiTietKhuyenMaiDAO().suaCTKM(km);
        return result;
    }

    public ArrayList<ChiTietKhuyenMaiDTO> getList() throws Exception{
        ArrayList<ChiTietKhuyenMaiDTO> listCTKM= new ChiTietKhuyenMaiDAO().docDSCTKM();
        
        return listCTKM;
    }
}
