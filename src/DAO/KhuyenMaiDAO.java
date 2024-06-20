/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class KhuyenMaiDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public KhuyenMaiDAO() {
    }
    
    public ArrayList<KhuyenMaiDTO> docDSKM() throws Exception {
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<KhuyenMaiDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from khuyenmai";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                KhuyenMaiDTO km = new KhuyenMaiDTO();
                km.setMaKM(rs.getString(1));
                km.setTenKM(rs.getString(2));
                km.setNgaybdKM(rs.getDate(3));
                km.setNgayktKM(rs.getDate(4));
                km.setDieukienKM(rs.getInt(5));
                dskm.add(km);
            }
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin khuyến mãi!");
        }
        return dskm;
    }
    
    public boolean themKM(KhuyenMaiDTO km) throws Exception{
//        boolean result;
//        try {
//            MySQLConnect mysql = new MySQLConnect();
//            conn = mysql.getConnect();
//            String qry = "Insert into khuyenmai Values (";
//            qry = qry + "'" + km.getMaKM()+ "'";
//            qry = qry + "," + "'" + km.getTenKM() + "'";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            qry = qry + "," + "'" + sdf.format(km.getNgaybdKM()) + "'";
//            qry = qry + "," + "'" + sdf.format(km.getNgayktKM())+ "'";
//            qry = qry + "," + "'" + km.getDieukienKM()+ "'";
//            qry = qry + ")";
//                        
//            st = conn.createStatement();
//            st.executeUpdate(qry);
//            result = true;
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin khuyến mãi!");
//            result = false;
//        }
//        return result;
        
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sSQL = "INSERT INTO `khuyenmai` (`MaKM`, `TenKM` , `NgayBD`, `NgayKT`,`DieuKienKM`) "
                + "VALUES ('" + km.getMaKM()+ "','" + km.getTenKM()
                + "','" + dateFormat.format(km.getNgaybdKM())
                + "','" + dateFormat.format(km.getNgayktKM())  
                + "','" + km.getDieukienKM() + "')";
                        
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin khuyến mãi!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaKM(String id) throws Exception{
//        boolean result;
//        try {
//            MySQLConnect mysql = new MySQLConnect();
//            conn = mysql.getConnect();
//            String qry = "Delete from khuyenmai where MaKM='" + id + "'";
//            st = conn.createStatement();
//            st.executeUpdate(qry);
//            result = true;
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi xóa khuyến mãi!");
//            result = false;
//        }
//        return result;
        
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String sSQL = "DELETE FROM `khuyenmai` WHERE khuyenmai.MaKM = '" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(sSQL);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa khuyến mãi!");
            result = false;
        }
        return result;
    }
    
    public boolean suaKM(KhuyenMaiDTO km) throws Exception{
//        boolean result;
//        try {
//            MySQLConnect mysql = new MySQLConnect();
//            conn = mysql.getConnect();
//            st = conn.createStatement();
//            String qry = "Update khuyenmai Set";
//            qry = qry + " " + "TenKM=" + "'" + km.getTenKM()+ "'";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            qry = qry + ",NgayBD=" + "'" + sdf.format(km.getNgaybdKM()) + "'";
//            qry = qry + ",NgayKT=" + "'" + sdf.format(km.getNgayktKM()) + "'";
//            qry = qry + ",DieuKienKM=" + "'" + km.getDieukienKM() + "'";
//            qry = qry + " where MaKM=" + "'" + km.getMaKM()+ "'";
//            st.executeUpdate(qry); 
//            result = true;
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi cập nhật khuyến mãi!");
//            result = false;
//        }
//        return result;
        
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sSQL = "UPDATE `khuyenmai` SET "
                + "`TenKM`='" + km.getTenKM()+ "',"
                + "`NgayBD`='" + dateFormat.format(km.getNgaybdKM()) + "',"
                + "`NgayKT`='" + dateFormat.format(km.getNgayktKM()) + "',"
                + "`DieuKienKM`='" + km.getDieukienKM() + "'"
                + " WHERE `MaKM`='" + km.getMaKM()+"'";

            st.executeUpdate(sSQL); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật khuyến mãi!");
            result = false;
        }
        return result;
    }
}
