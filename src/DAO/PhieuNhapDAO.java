/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuNhapDTO;
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
 * @author dhuynh
 */
public class PhieuNhapDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public PhieuNhapDAO() {
    }
    
    public ArrayList<PhieuNhapDTO> docDSPN() throws Exception{
        ArrayList<PhieuNhapDTO> dspn = new ArrayList<PhieuNhapDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from phieunhap";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                PhieuNhapDTO nv = new PhieuNhapDTO();
                nv.setIdPN(rs.getString(1));
                nv.setIdNCC(rs.getString(2));
                nv.setIdNhanVien(rs.getString(3));
                nv.setNgayNhap(rs.getDate(4));
                nv.setTongsotien(rs.getInt(5));
                dspn.add(nv);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin phiếu nhập!");
        }
        return dspn;
    }
    
    public boolean themPN(PhieuNhapDTO pn) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Insert into phieunhap Values (";
            qry = qry + "'" + pn.getIdPN()+ "'";
            qry = qry + "," + "'" + pn.getIdNCC() + "'";
            qry = qry + "," + "'" + pn.getIdNhanVien() + "'";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            qry = qry + "," + "'" + sdf.format(pn.getNgayNhap())+ "'";
            qry = qry + "," + "'" + pn.getTongsotien()+ "'";
            qry = qry + ")";
                        
            st = conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin phiếu nhập!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaPN(String id) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Delete from phieunhap where ID_PN='" + id + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa phiếu nhập!");
            result = false;
        }
        return result;
    }
    
    public boolean suaPN(PhieuNhapDTO pn) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String qry = "Update phieunhap Set";
            qry = qry + " " + "ID_NCC=" + "'" + pn.getIdNCC()+ "'";
            qry = qry + ",ID_NV=" + "'" + pn.getIdNhanVien()+ "'";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            qry = qry + ",NgayNhap=" + "'" + sdf.format(pn.getNgayNhap()) + "'";
            qry = qry + ",TongSoTien=" + "'" + pn.getTongsotien() + "'";
            qry = qry + " where ID_PN=" + "'" + pn.getIdPN()+ "'";
            st.executeUpdate(qry); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật phiếu nhập!");
            result = false;
        }
        return result;
    }
    
    public boolean UpdateTongSoTienPN(PhieuNhapDTO pn){
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Update phieunhap Set ";
            qry=qry +"TongSoTien="+"'"+pn.getTongsotien()+"'";
            qry=qry +" where ID_PN="+"'"+pn.getIdPN()+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật tổng số tiền phiếu nhập");
            result = false;
        }
        return result;
    }

}
