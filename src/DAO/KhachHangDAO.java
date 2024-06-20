/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import MySQL.MySQLConnect;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;

/**
 *
 * @author ASUS
 */
public class KhachHangDAO {
    Connection conn=null;
    Statement st = null;
    ResultSet rs = null;

    public KhachHangDAO() {
    }
   
    public ArrayList<KhachHangDTO> docDSKH(){
        ArrayList<KhachHangDTO> dskh = new ArrayList<KhachHangDTO>();
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry ="select * from khachhang";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                KhachHangDTO kh=new KhachHangDTO();
                kh.setMaKH(rs.getString(1));
                kh.setHoKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setGioitinh(rs.getString(4));
                kh.setDiachi(rs.getString(5));
                kh.setSdt(rs.getString(6));
                dskh.add(kh);
            }
            mysql.Close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin khách hàng");
        }
        return dskh;
    }
    public void them(KhachHangDTO kh){
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Insert into khachhang Values (";
            qry = qry + "'" + kh.getMaKH() + "'";
            qry = qry + "," + "'" + kh.getHoKH() + "'";
            qry = qry + "," + "'" + kh.getTenKH() + "'";
            qry = qry + "," + "'" + kh.getGioitinh()+ "'";
            qry = qry + "," + "'" + kh.getDiachi() + "'";
            qry = qry + "," + "'" + kh.getSdt() + "'";
            qry = qry + ")";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin khách hàng");
        }
    }
    public void xoa(String ma) {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            String qry="Delete from khachhang where MaKH='" +ma+ "'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa khách hàng");
        }
        
    }
    public void sua(KhachHangDTO kh) {
        try {
            MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st=conn.createStatement();
            String qry="Update khachhang Set ";
            qry=qry +"HoKH="+"'"+kh.getHoKH()+"'";
            qry=qry +",TenKH="+"'"+kh.getTenKH()+"'";
            qry=qry +",GioiTinh="+"'"+kh.getGioitinh()+"'";
            qry=qry +",DiaChi="+"'"+kh.getDiachi()+"'";
            qry=qry +",SDT="+"'"+kh.getSdt()+"'";
            qry=qry +" where MaKH="+"'"+kh.getMaKH()+"'";
            st.executeUpdate(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật khách hàng");
        }
    }
    
    public void importExcelKH(KhachHangDTO kh) throws Exception {
        String sql_check = "SELECT * FROM khachhang WHERE MaKH='" + kh.getMaKH() + "'";
        MySQLConnect mysql = new MySQLConnect();
            conn = mysql.getConnect();
            st = conn.createStatement();
            rs = st.executeQuery(sql_check);
            if (!rs.next()) {
                them(kh);

            } else {
                sua(kh);
            }
        
    }
}
