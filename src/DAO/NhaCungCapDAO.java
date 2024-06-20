/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhaCungCapDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dhuynh
 */
public class NhaCungCapDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public NhaCungCapDAO() {
    }
    
    public ArrayList<NhaCungCapDTO> docDSNCC() throws Exception{
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<NhaCungCapDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from nhacungcap";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                NhaCungCapDTO ncc = new NhaCungCapDTO();
                ncc.setIdNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiachiNCC(rs.getString(3));
                ncc.setSdtNCC(rs.getString(4));
                dsncc.add(ncc);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin nhà cung cấp!");
        }
        return dsncc;
    }
    
    public void themNCC(NhaCungCapDTO ncc) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Insert into nhacungcap Values (";
            qry = qry + "'" + ncc.getIdNCC()+ "'";
            qry = qry + "," + "'" + ncc.getTenNCC() + "'";
            qry = qry + "," + "'" + ncc.getDiachiNCC() + "'";
            qry = qry + "," + "'" + ncc.getSdtNCC()+ "'";
            qry = qry + ")";
                        
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin nhà cung cấp!");
        }
    }
    
    public void xoaNCC(String id) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Delete from nhacungcap where ID_NCC='" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa nhà cung cấp!");
        }
    }
    
    public void suaNCC(NhaCungCapDTO ncc) throws Exception{
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String qry = "Update nhacungcap Set";
            qry = qry + " " + "Ten_NCC=" + "'" + ncc.getTenNCC()+ "'";
            qry = qry + ",DiaChi_NCC=" + "'" + ncc.getDiachiNCC()+ "'";
            qry = qry + ",SDT_NCC=" + "'" + ncc.getSdtNCC() + "'";
            qry = qry + " where ID_NCC=" + "'" + ncc.getIdNCC()+ "'";
            
            st.executeUpdate(qry); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật nhà cung cấp!");
        }
    }
    
    public void importExcelNCC(NhaCungCapDTO ncc) throws Exception {
        String sql_check = "SELECT * FROM nhacungcap WHERE ID_NCC='" + ncc.getIdNCC() + "'";
        MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql_check);
            if (!rs.next()) {
                themNCC(ncc);

            } else {
                suaNCC(ncc);
            }
    }
}
