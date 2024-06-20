/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietKhuyenMaiDTO;
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
public class ChiTietKhuyenMaiDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ChiTietKhuyenMaiDAO() {
    }
    
    public ArrayList<ChiTietKhuyenMaiDTO> docDSCTKM() throws Exception {
        ArrayList<ChiTietKhuyenMaiDTO> dsctkm = new ArrayList<>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from chitietkhuyenmai";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                ChiTietKhuyenMaiDTO km = new ChiTietKhuyenMaiDTO();
                km.setMaKM(rs.getString(1));
                km.setMaSP(rs.getString(2));
                km.setPhantramKM(rs.getInt(3));
                dsctkm.add(km);
            }
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin chi tiết khuyến mãi!");
        }
        return dsctkm;
    }
    
    public boolean themCTKM(ChiTietKhuyenMaiDTO ctkm) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "INSERT INTO `chitietkhuyenmai` (`MaKM`, `MaSP` , `PhanTramKM`) "
                + "VALUES ('" + ctkm.getMaKM()+ "','" + ctkm.getMaSP()
                + "','" + ctkm.getPhantramKM() + "')";
                        
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin chi tiết khuyến mãi!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaCTKM(String idKM, String idSP) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "DELETE FROM `chitietkhuyenmai` WHERE chitietkhuyenmai.MaKM = '" + idKM + "'" + " AND chitietkhuyenmai.MaSP='" + idSP  + "'";
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết khuyến mãi!");
            result = false;
        }
        return result;
    }
    
    public boolean suaCTKM(ChiTietKhuyenMaiDTO ctkm) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String sSQL = "UPDATE `chitietkhuyenmai` SET "
                + "`PhanTramKM`='" + ctkm.getPhantramKM()+ "'"
                + " WHERE `MaKM`='" + ctkm.getMaKM() + "' AND `MaSP`='" + ctkm.getMaSP() + "'";

            st.executeUpdate(sSQL); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết khuyến mãi!");
            result = false;
        }
        return result;
    }

}
