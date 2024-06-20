/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
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
public class ChiTietPhieuNhapDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ChiTietPhieuNhapDAO() {
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> docDSCTPN() throws Exception {
        ArrayList<ChiTietPhieuNhapDTO> dsctpn = new ArrayList<ChiTietPhieuNhapDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "select * from chitietphieunhap";
            st = conn.createStatement();
            rs = st.executeQuery(qry);
            while(rs.next()){
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();

                ctpn.setIdPhieuNhap(rs.getString(1));
                ctpn.setIdSanPham(rs.getString(2));
                ctpn.setSoluong(rs.getInt(3));
                ctpn.setDongia(rs.getInt(4));
                ctpn.setTongtiennhap(rs.getInt(5));
                
                dsctpn.add(ctpn);
            }
            //conn.close();
            mysql.Close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin chi tiết phiếu nhập!");
        }
        return dsctpn;
    }
    
    public boolean themCTPN(ChiTietPhieuNhapDTO ctpn) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Insert into chitietphieunhap Values (";
            qry = qry + "'" + ctpn.getIdPhieuNhap()+ "'";
            qry = qry + "," + "'" + ctpn.getIdSanPham() + "'";
            qry = qry + "," + "'" + ctpn.getSoluong() + "'";
            qry = qry + "," + "'" + ctpn.getDongia()+ "'";
            qry = qry + "," + "'" + ctpn.getTongtiennhap()+ "'";
            qry = qry + ")";
                        
            st = conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin chi tiết phiếu nhập!");
            result = false;
        }
        return result;
    }
    
    public boolean xoaCTPN(String idPN, String idSP) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry = "Delete from chitietphieunhap where ID_PN='" + idPN + "'" + " AND ID_SP='" + idSP  + "'";
            st = conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết phiếu nhập!");
            result = false;
        }
        return result;
    }
    
    public boolean suaCTPN(ChiTietPhieuNhapDTO ctpn) throws Exception{ 
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            String qry = "Update chitietphieunhap Set";
            qry = qry + " " + "SoLuong=" + "'" + ctpn.getSoluong()+ "'";
            qry = qry + ",DonGia=" + "'" + ctpn.getDongia() + "'";
            qry = qry + ",TongTienNhap=" + "'" + ctpn.getTongtiennhap() + "'";
            qry = qry + " where ID_PN=" + "'" + ctpn.getIdPhieuNhap()+ "'" + " AND ID_SP='" + ctpn.getIdSanPham()  + "'";
            
            st.executeUpdate(qry); 
            result = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật chi tiết phiếu nhập!");
            result = false;
        }
        return result;
    }
}
