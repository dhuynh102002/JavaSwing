/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import DTO.ThongKeDTO;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ThongKeBUS {
    public ThongKeDAO thongKeDAO = new ThongKeDAO();
    private ArrayList<Double> doanhThuThang;

    public ThongKeDTO thongKe(int nam) throws Exception {
        return thongKeDAO.getThongKe(nam);
    }
    
    public ThongKeDTO thongKeTheoThang(int thang, int nam) throws Exception {
        return thongKeDAO.getThongKeTheoThang(thang, nam);
    }

    public double getDoanhThuThang(int thang, int nam) {
        return thongKeDAO.getDoanhThuThang(thang, nam);
    }
    
    public ArrayList<HoaDonDTO> thongKeKHTheoQuy(int nam, int quy) throws Exception {
        ArrayList<HoaDonDTO> dshd = thongKeDAO.getTongKHTheoQuy(nam, quy);
        return dshd;
    }
    
    public ArrayList<HoaDonDTO> thongKeDSHDTheoNam(int nam) throws Exception {
        ArrayList<HoaDonDTO> dshd = thongKeDAO.getDSHDTheoNam(nam);
        return dshd;
    }
}
