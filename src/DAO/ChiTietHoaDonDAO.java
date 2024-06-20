/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class ChiTietHoaDonDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ChiTietHoaDonDAO() {
    }
    
    public ArrayList<ChiTietHoaDonDTO> docDSCTHD() throws Exception {
        ArrayList<ChiTietHoaDonDTO> dscthd = new ArrayList<>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from chitiethoadon";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                ChiTietHoaDonDTO km = new ChiTietHoaDonDTO();
                km.setMaHD(rs.getString(1));
                km.setMaSP(rs.getString(2));
                km.setDongia(rs.getInt(3));
                km.setSoluong(rs.getInt(4));
                km.setTongtien(rs.getInt(5));
                dscthd.add(km);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin chi tiết hóa đơn!");
        }
        return dscthd;
    }
    
    public boolean themCTHD(ChiTietHoaDonDTO cthd) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "INSERT INTO `chitiethoadon` (`MaHD`, `MaSP` , `DonGia`, `SoLuong`, `TongTien`) "
                + "VALUES ('" + cthd.getMaHD()+ "','" + cthd.getMaSP()
                + "','" + cthd.getDongia()
                + "','" + cthd.getSoluong()
                + "','" + cthd.getTongtien()+ "')";
                        
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin chi tiết hóa đơn!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaCTHD(String idHD, String idSP) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "DELETE FROM `chitiethoadon` WHERE chitiethoadon.MaHD = '" + idHD + "'" + " AND chitiethoadon.MaSP='" + idSP  + "'";
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết hóa đơn!");
            result = false;
        }
        return result;
    }
    
    public boolean suaCTHD(ChiTietHoaDonDTO cthd) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String sSQL = "UPDATE `chitiethoadon` SET "
                + "`DonGia`='" + cthd.getDongia()+ "',"
                + "`SoLuong`='" + cthd.getSoluong() + "',"
                + "`TongTien`='" + cthd.getTongtien() + "'"
                + " WHERE `MaHD`='" + cthd.getMaHD() + "' AND `MaSP`='" + cthd.getMaSP()+"'";

            st.executeUpdate(sSQL); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết hóa đơn!");
            result = false;
        }
        return result;
    }
}
