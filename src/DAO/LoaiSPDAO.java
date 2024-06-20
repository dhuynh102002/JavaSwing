/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiSPDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class LoaiSPDAO {
    Connection conn=null;
    Statement st = null;
    ResultSet rs = null;

    public LoaiSPDAO() {
    }
   
    public ArrayList<LoaiSPDTO> docDSLoaiSP(){
        ArrayList<LoaiSPDTO> dsLoaiSP = new ArrayList<LoaiSPDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry ="select * from loaisanpham";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                LoaiSPDTO sp=new LoaiSPDTO();
                sp.setMaloai(rs.getString(1));
                sp.setTenloai(rs.getString(2));
                dsLoaiSP.add(sp);
            }
            mysql.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin loại sản phẩm");
        }
        return dsLoaiSP;
    }
    public void them(LoaiSPDTO sp){
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Insert into loaisanpham Values (";
            qry = qry + "'" + sp.getMaloai() + "'";
            qry = qry + "," + "'" + sp.getTenloai() + "'";
            qry = qry + ")";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin loại sản phẩm");
        }
    }
    public void xoa(String ma) {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Delete from loaisanpham where MaLoai='" +ma+ "'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa loại sản phẩm");
        }
        
    }
    public void sua(LoaiSPDTO sp) {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Update loaisanpham Set ";
            qry=qry +"TenLoai="+"'"+sp.getTenloai()+"'";
            qry=qry +" where MaLoai="+"'"+sp.getMaloai()+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật loại sản phẩm");
        }
    }

    public void importExcelLoaiSP(LoaiSPDTO loaisp) throws Exception {
        String sql_check = "SELECT * FROM loaisanpham WHERE MaLoai='" + loaisp.getMaloai() + "'";
        MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql_check);
            if (!rs.next()) {
                them(loaisp);

            } else {
                sua(loaisp);
            }
        
    }
}
