/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DangNhapDTO;
import DTO.NhanVienDTO;
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
 * @author WIN 10
 */
public class DangNhapDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public DangNhapDAO() {
    }
    
    public ArrayList<DangNhapDTO> docDSDN() throws Exception {
        ArrayList<DangNhapDTO> dsdn = new ArrayList<DangNhapDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from dangnhap";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                DangNhapDTO dn = new DangNhapDTO();
                NhanVienDTO nv = new NhanVienDTO();
                nv.setIdNV(rs.getString(1));
                dn.setIdNhanvienDTO(nv);
                dn.setTenDangnhapDTO(rs.getString(2));
                dn.setMatkhauDangnhapDTO(rs.getString(3));
                
                dsdn.add(dn);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin đăng nhập!");
        }
        return dsdn;
    }
    
    public void themDN(DangNhapDTO dn) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Insert into dangnhap Values (";
            qry = qry + "'" + dn.getIdNhanvienDTO()+ "'";
            qry = qry + "," + "'" + dn.getIdNhanvienDTO() + "'";
            qry = qry + "," + "'" + dn.getTenDangnhapDTO() + "'";
            qry = qry + "," + "'" + dn.getMatkhauDangnhapDTO()+ "'";
            qry = qry + ")";
                        
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin đăng nhập!");
        }
    }
    
    public void xoaDN(String id) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Delete from dangnhap where ID_NV='" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa đăng nhập!");
        }
    }
    
    public void suaDN(DangNhapDTO dn) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String qry = "Update dangnhap Set";
            qry = qry + " " + "TenDangNhap=" + "'" + dn.getTenDangnhapDTO()+ "'";
            qry = qry + ",MatKhau=" + "'" + dn.getMatkhauDangnhapDTO()+ "'";
            qry = qry + " where ID_NV=" + "'" + dn.getIdNhanvienDTO()+ "'";
            
            st.executeUpdate(qry); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật đăng nhập!");
        }
    }
}
