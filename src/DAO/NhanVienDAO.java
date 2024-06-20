/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static BUS.NhanVienBUS.listNV;
import DTO.NhanVienDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dhuynh
 */
public class NhanVienDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public NhanVienDAO() {
    }
    
    public ArrayList<NhanVienDTO> docDSNV() throws Exception {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<NhanVienDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from nhanvien";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                NhanVienDTO nv = new NhanVienDTO();
                nv.setIdNV(rs.getString(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioitinh(rs.getString(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setSdt(rs.getString(6));
                nv.setDiachi(rs.getString(7));
                nv.setChucvu(rs.getString(8));
                nv.setLuongThang(rs.getInt(9));
                if(rs.getString(10).equals("Hiện hành")){
                    nv.setTrangthai(true);
                }
                else{
                    nv.setTrangthai(false);
                }
                dsnv.add(nv);
            }
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin nhân viên!");
        }
        return dsnv;
    }
    
    public void themNV(NhanVienDTO nv) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Insert into nhanvien Values (";
            qry = qry + "'" + nv.getIdNV()+ "'";
            qry = qry + "," + "'" + nv.getHo() + "'";
            qry = qry + "," + "'" + nv.getTen() + "'";
            qry = qry + "," + "'" + nv.getGioitinh()+ "'";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            qry = qry + "," + "'" + sdf.format(nv.getNgaySinh())+ "'";
            qry = qry + "," + "'" + nv.getSdt()+ "'";
            qry = qry + "," + "'" + nv.getDiachi()+ "'";
            qry = qry + "," + "'" + nv.getChucvu()+ "'";
            qry = qry + "," + "'" + nv.getLuongThang()+ "'";
            if(nv.isTrangthai()){
                qry = qry + "," + "'Hiện hành'";
            }
            else{
                qry = qry + "," + "'Đã nghỉ'";
            }
            qry = qry + ")";
                        
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin nhân viên!");
        }
    }
    
    public void xoaNV(String id) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Delete from nhanvien where ID='" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa nhân viên!");
        }
    }
    
    public void suaNV(NhanVienDTO nv) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String qry = "Update nhanvien Set";
            qry = qry + " " + "Ho=" + "'" + nv.getHo()+ "'";
            qry = qry + ",Ten=" + "'" + nv.getTen()+ "'";
            qry = qry + ",GioiTinh=" + "'" + nv.getGioitinh() + "'";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            qry = qry + ",NgaySinh=" + "'" + sdf.format(nv.getNgaySinh()) + "'";
            qry = qry + ",SDT=" + "'" + nv.getSdt() + "'";
            qry = qry + ",DiaChi=" + "'" + nv.getDiachi() + "'";
            qry = qry + ",ChucVu=" + "'" + nv.getChucvu() + "'";
            qry = qry + ",LuongThang=" + "'" + nv.getLuongThang() + "'";
            if(nv.isTrangthai()){
                qry = qry + ",TrangThai=" + "'Hiện hành'";
            }
            else{
                qry = qry + ",TrangThai=" + "'Đã nghỉ'";
            }
            qry = qry + " where ID=" + "'" + nv.getIdNV()+ "'";
            
            st.executeUpdate(qry); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật nhân viên!");
        }
    }
    public void importExcelNV(NhanVienDTO nv) throws Exception {
//            try {    
//            MySQLConnect mysql = new MySQLConnect();
//            conn = mysql.getConnect();
//            String qry = "Delete * from nhanvien; " +
//                                "Insert into nhanvien Values (";
//            qry = qry + "'" + nv.getIdNV()+ "'";
//            qry = qry + "," + "'" + nv.getHo() + "'";
//            qry = qry + "," + "'" + nv.getTen() + "'";
//            qry = qry + "," + "'" + nv.getGioitinh()+ "'";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            qry = qry + "," + "'" + sdf.format(nv.getNgaySinh())+ "'";
//            qry = qry + "," + "'" + nv.getSdt()+ "'";
//            qry = qry + "," + "'" + nv.getDiachi()+ "'";
//            qry = qry + "," + "'" + nv.getChucvu()+ "'";
//            qry = qry + "," + "'" + nv.getLuongThang()+ "'";
//            if(nv.isTrangthai()){
//                qry = qry + "," + "'Hiện hành'";
//            }
//            else{
//                qry = qry + "," + "'Đã nghỉ'";
//            }
//            qry = qry + ")";
//                        
//            st = conn.createStatement();
//            st.executeUpdate(qry);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Lỗi import Excel nhân viên!");
//        }
        
        String sql_check = "SELECT * FROM nhanvien WHERE ID='" + nv.getIdNV() + "'";
        MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql_check);
            if (!rs.next()) {
                themNV(nv);

            } else {
                suaNV(nv);
            }
        
    }
}
