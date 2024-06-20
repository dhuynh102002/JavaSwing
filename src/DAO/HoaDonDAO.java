/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import DTO.KhuyenMaiDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class HoaDonDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public HoaDonDAO() {
    }
    
    public ArrayList<HoaDonDTO> docDSHD() throws Exception {
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from hoadon";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                HoaDonDTO hd = new HoaDonDTO();
                hd.setIdHoadon(rs.getString(1));
                hd.setIdNhanvien(rs.getString(2));
                hd.setIdKhachhang(rs.getString(3));
                hd.setIdKhuyenmai(rs.getString(4));
                hd.setNgaylapHoadon(rs.getDate(5));
                hd.setTongtienHoadon(rs.getInt(6));
                dshd.add(hd);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin hóa đơn!");
        }
        return dshd;
    }
    
    public boolean themHD(HoaDonDTO hd) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sSQL = "INSERT INTO `hoadon` (`MaHD`, `MaNV` , `MaKH`, `MaKM`,`NgayLap`, `TongTien`) "
                + "VALUES ('" + hd.getIdHoadon()+ "','" + hd.getIdNhanvien()
                + "','" + hd.getIdKhachhang()
                + "','" + hd.getIdKhuyenmai()
                + "','" + dateFormat.format(hd.getNgaylapHoadon())  
                + "','" + hd.getTongtienHoadon() + "')";
                        
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin hóa đơn!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaHD(String id) throws Exception{        
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "DELETE FROM `hoadon` WHERE hoadon.MaHD = '" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa hóa đơn!");
            result = false;
        }
        return result;
    }
    
    public boolean suaHD(HoaDonDTO hd) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sSQL = "UPDATE `hoadon` SET "
                + "`MaNV`='" + hd.getIdNhanvien()+ "',"
                + "`MaKH`='" + hd.getIdKhachhang() + "',"
                + "`MaKM`='" + hd.getIdKhuyenmai() + "',"
                + "`NgayLap`='" + dateFormat.format(hd.getNgaylapHoadon()) + "',"
                + "`TongTien`='" + hd.getTongtienHoadon() + "'"
                + " WHERE `MaHD`='" + hd.getIdHoadon() + "'";

            st.executeUpdate(sSQL); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật hóa đơn!");
            result = false;
        }
        return result;
    }
    
    public boolean UpdateTongTienHD(HoaDonDTO hd){
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "UPDATE `hoadon` SET "                
                + "`TongTien`='" + hd.getTongtienHoadon() + "'"
                + " WHERE `MaHD`='" + hd.getIdHoadon() + "'";
            st=conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật tổng số tiền hóa đơn");
            result = false;
        }
        return result;
    }
}
