/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dhuynh
 */
public class NhaCungCapBUS {
    public static ArrayList<NhaCungCapDTO> listNCC;

    public NhaCungCapBUS() {
        
    }
    
    public ArrayList<NhaCungCapDTO> getList() throws Exception{
        ArrayList<NhaCungCapDTO> listCTPNH= new NhaCungCapDAO().docDSNCC();
        return listCTPNH;
    }
    
    public void docDSNCC() throws Exception{
        NhaCungCapDAO data = new NhaCungCapDAO();
        if(listNCC == null){
            listNCC = new ArrayList<NhaCungCapDTO>();
        }
        listNCC = data.docDSNCC();
    }
    
    public void themNCC(NhaCungCapDTO ncc) throws Exception{
        NhaCungCapDAO data = new NhaCungCapDAO();
        data.themNCC(ncc);
        listNCC.add(ncc);
    }
    
    public void suaNCC(NhaCungCapDTO ncc) throws Exception{
        NhaCungCapDAO data = new NhaCungCapDAO();
        data.suaNCC(ncc);
    }
    
    public void xoaNCC(String id) throws Exception{
        NhaCungCapDAO data = new NhaCungCapDAO();
        data.xoaNCC(id);
    }
    
     public NhaCungCapDTO timkiemMaNCC(String id){
        for(NhaCungCapDTO ncc : listNCC){
            if(ncc.getIdNCC().toLowerCase().equals(id.toLowerCase())){
                return ncc;
            }
        }
        return null;
    }
    
    public ArrayList<NhaCungCapDTO> timkiemTenNCC(String ten){
        ArrayList<NhaCungCapDTO> dsnv = new ArrayList<>();
        for(NhaCungCapDTO ncc : listNCC){
            if(ncc.getTenNCC().toLowerCase().contains(ten.toLowerCase())){
                dsnv.add(ncc);
            }
        }
        return dsnv;
    }
    
    public void sortID(){
        Collections.sort(listNCC, new Comparator<NhaCungCapDTO>() {
            @Override
            public int compare(NhaCungCapDTO nv1, NhaCungCapDTO nv2) {
                return nv1.getIdNCC().compareToIgnoreCase(nv2.getIdNCC());
            }
        });
        
    }
    
    public void importExcelNCC(NhaCungCapDTO ncc) throws Exception {
        NhaCungCapDAO data = new NhaCungCapDAO();
        data.importExcelNCC(ncc);
    }
}
