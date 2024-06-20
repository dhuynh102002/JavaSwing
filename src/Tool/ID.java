/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.LoaiSPBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.LoaiSPDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;

/**
 *
 * @author ASUS
 */
public class ID {
    public static String createNewStaff(){
        String id="NV";
        new NhanVienBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<NhanVienBUS.listNV.size()&&flag){
            NhanVienDTO tmp = NhanVienBUS.listNV.get(i);
            if(tmp.getIdNV().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewNCC(){
        String id="NCC";
        new NhaCungCapBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<NhaCungCapBUS.listNCC.size()&&flag){
            NhaCungCapDTO tmp = NhaCungCapBUS.listNCC.get(i);
            if(tmp.getIdNCC().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewPhieuNhap(){
        String id="PN";
        new PhieuNhapBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<PhieuNhapBUS.listPN.size()&&flag){
            PhieuNhapDTO tmp = PhieuNhapBUS.listPN.get(i);
            if(tmp.getIdPN().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewSP(){
        String id="SP";
        new SanPhamBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<SanPhamBUS.dssp.size()&&flag){
            SanPhamDTO tmp = SanPhamBUS.dssp.get(i);
            if(tmp.getMasp().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewLoaiSP(){
        String id="ML";
        new LoaiSPBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<LoaiSPBUS.dsLoaiSP.size()&&flag){
            LoaiSPDTO tmp = LoaiSPBUS.dsLoaiSP.get(i);
            if(tmp.getMaloai().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    public static String createNewKH(){
        String id="KH";
        new KhachHangBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<KhachHangBUS.dskh.size()&&flag){
            KhachHangDTO tmp = KhachHangBUS.dskh.get(i);
            if(tmp.getMaKH().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewKhuyenMai(){
        String id="KM";
        new KhuyenMaiBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<KhuyenMaiBUS.listKM.size()&&flag){
            KhuyenMaiDTO tmp = KhuyenMaiBUS.listKM.get(i);
            if(tmp.getMaKM().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
    
    public static String createNewHoaDon(){
        String id="HD";
        new HoaDonBUS().sortID();
        int ID=1;
        String newID=id+String.format("%03d",ID); 
        boolean flag=true;
        int i=0;
        while(i<HoaDonBUS.listHD.size()&&flag){
            HoaDonDTO tmp = HoaDonBUS.listHD.get(i);
            if(tmp.getIdHoadon().equals(newID)){
                ID++;i++;  
                newID=id+String.format("%03d",ID);
            }
            else{
                flag=false;
            }
        }        
        return newID;
    }
}
