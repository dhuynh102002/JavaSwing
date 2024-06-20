/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import DTO.ThongKeDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class ThongKeDAO {
    Connection conn=null;
    Statement st = null;
    ResultSet rs = null;
    
    public ThongKeDTO getThongKe(int nam) throws Exception {
        ThongKeDTO thongKe = new ThongKeDTO();
        int[] tongThuQuy = new int[4];
        thongKe.setSoLuongSP(getTongSoLuongSP());
        thongKe.setSoLuongKH(getSoLuongKhachHang());
        thongKe.setSoLuongNV(getSoLuongNhanVien());
        thongKe.setSoLuongDonHang(getTongDonHang(nam));
        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        int[] tongChiQuy = new int[4];
        tongChiQuy[0] = getTongChiQuy(nam, 1);
        tongChiQuy[1] = getTongChiQuy(nam, 2);
        tongChiQuy[2] = getTongChiQuy(nam, 3);
        tongChiQuy[3] = getTongChiQuy(nam, 4);
        thongKe.setTongChiQuy(tongChiQuy);
        
        thongKe.setTopSanPhamBanChay(getTopBanChay());
        
        //thongKe.setKhQuy(getTongKHTheoQuy(nam, nam));
        return thongKe;
    }
    
    public ThongKeDTO getThongKeTheoThang(int thang, int nam) throws Exception {
        ThongKeDTO thongKe = new ThongKeDTO();
        thongKe.setSoLuongDonHang(getTongDonHangTheoThang(thang, nam));
        thongKe.setThuThang(getThuThang(thang, nam));
        thongKe.setChiThang(getChiThang(thang, nam));
        //thongKe.setTopSanPhamBanChay(getMatHangBanChayTheoThang(thang, nam));
        return thongKe;
    }
    
    private ArrayList<SanPhamDTO> getTopBanChay() {
        try {
            String sql = "SELECT MaSP, DaBan FROM (" +
                    "SELECT MaSP, SUM(SoLuong) AS DaBan FROM " +
                    "chitiethoadon GROUP BY MaSP" +
                    ") temp " +
                    "ORDER BY DaBan " +
                    "DESC LIMIT 5";
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ArrayList<SanPhamDTO> dssp = new ArrayList<>();
            SanPhamBUS spBUS = new SanPhamBUS();
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(rs.getString(1));
                sp.setSoluong(rs.getInt(2));
                sp.setTensp(spBUS.getTenSP(sp.getMasp()));
                dssp.add(sp);
            }
//            for(int i=0;i<5;i++){
//            System.out.println(dshd.get(i).getMasp() + dshd.get(i).getTensp()+ dshd.get(i).getSoluong());}
            return dssp;
        } catch (Exception e) {
        }
        return null;
    }
    
//    public ArrayList<SanPhamDTO> getMatHangBanChayTheoThang(int thang, int nam) {
//        String tu = nam + "-" + thang + "-01";
//        String den = nam + "-" + (thang+1) + "-01";
//        try {
//            MySQLConnect mysql = new MySQLConnect();
//            conn = mysql.getConnect();
//            PreparedStatement prep = conn.prepareStatement("SELECT MaSP, DaBan FROM (" +
//                    "SELECT MaSP, SUM(SoLuong) AS DaBan FROM " +
//                    "chitiethoadon WHERE chitiethoadon.MaHD = (SELECT hoadon.MaHD FROM hoadon WHERE NgayLap >= ? AND NgayLap < ? ) GROUP BY MaSP" +
//                    ") temp " +
//                    "ORDER BY DaBan " +
//                    "DESC LIMIT 5");
//            prep.setString(1, tu);
//            prep.setString(2, den);
//            rs = prep.executeQuery();
//            ArrayList<SanPhamDTO> dssp = new ArrayList<>();
//            SanPhamBUS spBUS = new SanPhamBUS();
//            while (rs.next()) {
//                SanPhamDTO sp = new SanPhamDTO();
//                sp.setMasp(rs.getString(1));
//                sp.setSoluong(rs.getInt(2));
//                sp.setTensp(spBUS.getTenSP(sp.getMasp()));
//                dssp.add(sp);
//            }
//            for(int i=0;i<dssp.size();i++){
//            System.out.println(dssp.get(i).getMasp() + dssp.get(i).getTensp()+ dssp.get(i).getSoluong());}
//            return dssp;
//        } catch (Exception e) {
//        }
//        return null;
//    }

    private int getTongSoLuongSP() throws Exception {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM sanpham");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private int getTongDonHang(int nam) throws Exception {
        String tu = nam + "-01-01";
        nam+=1;
        String den = nam + "-01-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT COUNT(*) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private int getTongDonHangTheoThang(int thang, int nam) throws Exception {
        String tu = nam + "-" + thang + "-01";
        String den = nam + "-" + (thang+1) + "-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT COUNT(*) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private int getThuThang(int thang, int nam) throws Exception {
        String tu = nam + "-" + thang + "-01";
        String den = nam + "-" + (thang+1) + "-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private int getChiThang(int thang, int nam) throws Exception {
        String tu = nam + "-" + thang + "-01";
        String den = nam + "-" + (thang+1) + "-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongSoTien) FROM phieunhap "
                    + "WHERE NgayNhap >= ? AND NgayNhap < ?");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; //kiểu String do có số 0 ở phía trước
        String thangKetThuc = "04"; //kiểu String do có số 0 ở phía trước
        String[] kq = new String[2];
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "04";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "07";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "09";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
        String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    private int getTongThuQuy(int nam, int quy) throws Exception {
        String[] dateString = getDateString(nam, quy);
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongTien) FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ?");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            rs = prep.executeQuery();
            while (rs.next()) {
                
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    private int getTongChiQuy(int nam, int quy) throws Exception {
        String[] dateString = getDateString(nam, quy);
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT SUM(TongSoTien) FROM phieunhap "
                    + "WHERE NgayNhap >= ? AND NgayNhap < ?");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }
    
    public ArrayList<HoaDonDTO> getHDTheoNam(int nam) throws Exception {
        String tu = nam + "-01-01";
        nam+=1;
        String den = nam + "-01-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT MaHD FROM hoadon WHERE NgayLap >= ? AND NgayLap < ?)");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            ArrayList<HoaDonDTO> dshd = new ArrayList<>();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setIdHoadon(rs.getString(1));
                dshd.add(hd);
            }
            return dshd;
        } catch (SQLException ex) {
        }
        return null;
    }

    
    public ArrayList<HoaDonDTO> getDSHDTheoNam(int nam) throws Exception {
        String tu = nam + "-01-01";
        nam+=1;
        String den = nam + "-01-01";
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT DISTINCT MaKH FROM hoadon "
                    + "WHERE NgayLap >= ? AND NgayLap < ? ");
            prep.setString(1, tu);
            prep.setString(2, den);
            rs = prep.executeQuery();
            ArrayList<HoaDonDTO> dshd = new ArrayList<>();
            HoaDonBUS hdBUS = new HoaDonBUS();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setIdKhachhang(rs.getString(1));
                dshd.add(hd);
            }
            return dshd;
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public ArrayList<HoaDonDTO> getTongKHTheoQuy(int nam, int quy) throws Exception {
          String[] dateString = getDateString(nam, quy);
          try {

            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement prep = conn.prepareStatement("SELECT MaKH, TongTienMua FROM (" +
                    "SELECT MaKH, SUM(TongTien) AS TongTienMua FROM " +
                    "hoadon WHERE (NgayLap >= ? AND NgayLap < ?) GROUP BY MaKH ) temp ORDER BY MaKH, TongTienMua " );
            
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            rs = prep.executeQuery();
            ArrayList<HoaDonDTO> dshd = new ArrayList<>();
            HoaDonBUS hdBUS = new HoaDonBUS();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setIdKhachhang(rs.getString(1));
                hd.setTongtienHoadon(rs.getInt(2));
                dshd.add(hd);
            }
            return dshd;
            
        } catch (SQLException ex) {
        }
        return null;
    }
    
    private int getSoLuongNhanVien() throws Exception {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM nhanvien");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    private int getSoLuongKhachHang() throws Exception {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM khachhang");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public double getDoanhThuThang(int thang, int nam) {
        try {
            String thangBD = nam + "-" + thang + "-01";
            String thangKT = nam + "-" + (thang + 1) + "-01";
            String sql = "SELECT SUM(TongTien) FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, thangBD);
            pre.setString(2, thangKT);
            rs = pre.executeQuery();
            while (rs.next()) {
                return Double.parseDouble(rs.getInt(1) + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nam;
    }

    
}
