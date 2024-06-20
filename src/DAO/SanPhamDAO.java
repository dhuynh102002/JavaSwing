/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author ASUS
 */
public class SanPhamDAO {
    Connection conn=null;
    Statement st = null;
    ResultSet rs = null;

    public SanPhamDAO() {
    }
   
    public ArrayList<SanPhamDTO> docDSSP(){
        ArrayList<SanPhamDTO> dssp = new ArrayList<SanPhamDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry ="select * from sanpham";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                SanPhamDTO sp=new SanPhamDTO();
                sp.setMasp(rs.getString(1));
                sp.setTensp(rs.getString(2));
                sp.setMaloai(rs.getString(3));
                sp.setDongia(rs.getInt(4));
                sp.setSoluong(rs.getInt(5));
                sp.setDVT(rs.getString(6));
                if(rs.getString(7).equals("Đang bán")){
                    sp.setTrangthai(true);
                }
                else{
                    sp.setTrangthai(false);
                }
                dssp.add(sp);
            }
            mysql.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin khách hàng");
        }
        return dssp;
    }
    public void them(SanPhamDTO sp){
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Insert into sanpham Values (";
            qry = qry + "'" + sp.getMasp() + "'";
            qry = qry + "," + "'" + sp.getTensp() + "'";
            qry = qry + "," + "'" + sp.getMaloai() + "'";
            qry = qry + "," + "'" + sp.getDongia()+ "'";
            qry = qry + "," + "'" + sp.getSoluong() + "'";
            qry = qry + "," + "'" + sp.getDVT() + "'";
            if(sp.getTrangthai()){
                qry = qry + "," + "'" + "Đang bán" + "'";
            }
            else{
                qry = qry + "," + "'" + "Hết hàng" + "'";
            }
            qry = qry + ")";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin sản phẩm");
        }
    }
    public void xoa(String ma) {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Delete from sanpham where MaSP='" +ma+ "'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa sản phẩm");
        }
        
    }
    public void sua(SanPhamDTO sp) throws Exception {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            
            String qry="Update sanpham Set ";
            qry=qry +"TenSP="+"'"+sp.getTensp()+"'";
            qry=qry +",MaLoai="+"'"+sp.getMaloai()+"'";
            qry=qry +",DonGia="+"'"+sp.getDongia()+"'";
            qry=qry +",SoLuong="+"'"+sp.getSoluong()+"'";
            qry=qry +",DVT="+"'"+sp.getDVT()+"'";
            if(sp.getTrangthai()){
                qry=qry +",TrangThai="+"'"+ "Đang bán" +"'";
            }
            else{
                qry=qry +",TrangThai="+"'"+ "Hết hàng" +"'";
            }
            qry=qry +" where MaSP="+"'"+sp.getMasp()+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Lỗi cập nhật sản phẩm");
        }
    }
    
    public boolean UpdateSoLuongSP(SanPhamDTO sp) throws Exception{
        boolean result;
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Update sanpham Set ";
            qry=qry +"SoLuong="+"'"+sp.getSoluong()+"'";
            if(sp.getSoluong()>0){
                qry=qry +",TrangThai="+"'"+ "Đang bán" +"'";
            }
            else{
                qry=qry +",TrangThai="+"'"+ "Hết hàng" +"'";
            }
            
            qry=qry +" where MaSP="+"'"+sp.getMasp()+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
            result = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật số lượng sản phẩm");
            result = false;
        }
        return result;
    }
    public void importExcelSP(SanPhamDTO sp) throws Exception {  
        String sql_check = "SELECT * FROM sanpham WHERE MaSP='" + sp.getMasp() + "'";
        MySQLConnect mysql = new MySQLConnect();
        conn = mysql.getConnect();
        st = conn.createStatement();
        rs = st.executeQuery(sql_check);
        if (!rs.next()) {
            them(sp);
        } else {
            sua(sp);
        }
    }
}
