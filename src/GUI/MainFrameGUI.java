/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietKhuyenMaiBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.LoaiSPBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.ThongKeBUS;
import DAO.KhuyenMaiDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.LoaiSPDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.ThongKeDTO;
import Report.ReportPDF;
import Report.ReportPDFNH;
import Tool.Check;
import Tool.ID;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author WIN 10
 */
public class MainFrameGUI extends javax.swing.JFrame {
//    final Color clLeftItem = new Color(63, 74, 89);
//    final Color clLeftItemHover = new Color(72, 88, 107);
//    final Color clLeftItemSelected = new Color(51, 202, 187);
    
    DefaultTableModel modelNV = new DefaultTableModel();
    DefaultTableModel modelNCC = new DefaultTableModel();
    DefaultTableModel modelPN = new DefaultTableModel();
    DefaultTableModel modelCTPN = new DefaultTableModel();
    DefaultTableModel modelSP = new DefaultTableModel();
    DefaultTableModel modelLSP = new DefaultTableModel();
    DefaultTableModel modelKH = new DefaultTableModel();
    DefaultTableModel modelKM = new DefaultTableModel();
    DefaultTableModel modelCTKM = new DefaultTableModel();
    DefaultTableModel modelGioHang = new DefaultTableModel();
    DefaultTableModel modelBH = new DefaultTableModel();
    DefaultTableModel modelDSSPBH = new DefaultTableModel();
    DefaultTableModel modelHD = new DefaultTableModel();
    DefaultTableModel modelCTHD = new DefaultTableModel();
    DefaultTableModel modelThongKeSP = new DefaultTableModel();
    DefaultTableModel modelThongKeKH = new DefaultTableModel();
    DefaultTableModel modelThongKeTopBanChay = new DefaultTableModel();
    DefaultTableModel modelThongKeSPSapHet = new DefaultTableModel();
    DefaultTableModel modelThongKeTongChi = new DefaultTableModel();
    DefaultTableModel modelThongKeTongThu = new DefaultTableModel();
    
    final Color clLeftItem = new Color(204,204,255);
    final Color clLeftItemHover = new Color(204, 255, 255);
    final Color clLeftItemSelected = new Color(102, 255, 255);
    ArrayList<JLabel> listMenuLeftADMIN = new ArrayList<>();
    ArrayList<JLabel> listMenuLeftNV = new ArrayList<>();

    static String idPN;
    static String idKM;
    static String idHD;
    int tongSoTien=0;
    int tongtienBanHang=0;
    
    static String maKM=null;
    //private LoaiSPDTO loaiSPChon = null;
    /**
     * Creates new form MainFrameGUI
     */
    public MainFrameGUI() throws Exception {
        initComponents();
        listMenuLeftADMIN.add(lbBanHangAdmin);
        listMenuLeftADMIN.add(lbNhapHangAdmin);
        listMenuLeftADMIN.add(lbSanPhamAdmin);
        listMenuLeftADMIN.add(lbHoaDonAdmin);
        listMenuLeftADMIN.add(lbNhaCungCapAdmin);
        listMenuLeftADMIN.add(lbKhuyenMaiAdmin);
        listMenuLeftADMIN.add(lbNhanVienAdmin);
        listMenuLeftADMIN.add(lbKhachHangAdmin);
        listMenuLeftADMIN.add(lbThongKeAdmin);
        listMenuLeftADMIN.add(lbDangXuatAdmin);
        
        listMenuLeftNV.add(lbBanHangNV);
        listMenuLeftNV.add(lbNhapHangNV);
        listMenuLeftNV.add(lbSanPhamNV);
        listMenuLeftNV.add(lbHoaDonNV);
        listMenuLeftNV.add(lbKhachHangNV);
        listMenuLeftNV.add(lbDangXuatNV);
        
        docDSNV();
        txMaNV.setText(ID.createNewStaff());
        docDSNCC();
        txMaNCC.setText(ID.createNewNCC());
        
        docDSLoaiSP();
        docDSSP();
        txMaSP.setText(ID.createNewSP());
//        for (int i=0;i<LoaiSPBUS.dsLoaiSP.size();i++){
//            cbbMaLoaiSP.addItem(LoaiSPBUS.dsLoaiSP.get(i).getMaloai());
//        }
        txMaLoai.setText(ID.createNewLoaiSP());
        
        docDSKH();
        txMakh.setText(ID.createNewKH());
        


        docDSPN();
        docDSCTPN();
        for(int i=0;i<PhieuNhapBUS.listPN.size();i++){
            tongSoTien+=PhieuNhapBUS.listPN.get(i).getTongsotien();
        }
//        DecimalFormat dcf = new DecimalFormat("###,###");
//        lbTongSoTien.setText("Tổng số tiền: " + dcf.format(tongSoTien));

        txMaNVPN.setText(dangnhap.idHienHanh);
        txMaPhieuNhap.setText(ID.createNewPhieuNhap());
        ngayNhap.setDate(new Date());
        idPN = ID.createNewPhieuNhap();

        docDSKM();
        docDSCTKM();
        idKM = ID.createNewKhuyenMai();
        txMaKM.setText(ID.createNewKhuyenMai());
        NgayKetThucKM.setDate(new Date());
        NgayBatDauKM.setDate(new Date());

        docDSHD();
        docDSCTHD();
        txMaNVHD.setText(dangnhap.idHienHanh);
        txMaHD.setText(ID.createNewHoaDon());
        ngayLapHD.setDate(new Date());
        idHD = ID.createNewHoaDon();
        
        ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
        listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
        showBH(listSPDangBan);
        txMaHDBH.setText(ID.createNewHoaDon());
        txMaNVBH.setText(dangnhap.idHienHanh);
        ngayLapBH.setDate(new Date());
        modelGioHang.setRowCount(0);
        tblDSGioHang.setModel(modelGioHang);
        hienThiThongKe(2022);
        showDSSPHetHang(ThongKeDSSanPhamHetHang());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dlgChonLoaiSP = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        cbbTimLoaiSP = new javax.swing.JComboBox<>();
        txTimLoaiSP = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblKQTKLoaiSP = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        btnDlgChonLoaiSP = new javax.swing.JButton();
        btnDlgThemLoaiSP = new javax.swing.JButton();
        btnDlgSuaLoaiSP = new javax.swing.JButton();
        PanelMain = new javax.swing.JPanel();
        pnMenu = new javax.swing.JPanel();
        pnAdmin = new javax.swing.JPanel();
        anhAdmin = new javax.swing.JLabel();
        lbTenAdmin = new javax.swing.JLabel();
        lbAdmin = new javax.swing.JLabel();
        lbBanHangAdmin = new javax.swing.JLabel();
        lbNhapHangAdmin = new javax.swing.JLabel();
        lbSanPhamAdmin = new javax.swing.JLabel();
        lbHoaDonAdmin = new javax.swing.JLabel();
        lbNhaCungCapAdmin = new javax.swing.JLabel();
        lbKhuyenMaiAdmin = new javax.swing.JLabel();
        lbNhanVienAdmin = new javax.swing.JLabel();
        lbKhachHangAdmin = new javax.swing.JLabel();
        lbThongKeAdmin = new javax.swing.JLabel();
        lbDangXuatAdmin = new javax.swing.JLabel();
        pnNhanVien = new javax.swing.JPanel();
        anhNV = new javax.swing.JLabel();
        lbTenNhanVien = new javax.swing.JLabel();
        lbNhanVien = new javax.swing.JLabel();
        lbBanHangNV = new javax.swing.JLabel();
        lbNhapHangNV = new javax.swing.JLabel();
        lbSanPhamNV = new javax.swing.JLabel();
        lbHoaDonNV = new javax.swing.JLabel();
        lbKhachHangNV = new javax.swing.JLabel();
        lbDangXuatNV = new javax.swing.JLabel();
        panelChinh = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lbHeader = new javax.swing.JLabel();
        PanelHienthi = new javax.swing.JPanel();
        panelSanPham = new javax.swing.JPanel();
        panelChuyenSP = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JButton();
        btnLoaiSP = new javax.swing.JButton();
        panelChonSP = new javax.swing.JPanel();
        panelSP = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txMaSP = new javax.swing.JTextField();
        txTenSP = new javax.swing.JTextField();
        txDonGiaSP = new javax.swing.JTextField();
        txSoLuongSP = new javax.swing.JTextField();
        txDonViTinhSP = new javax.swing.JTextField();
        cbbTrangThaiSP = new javax.swing.JComboBox<>();
        btThemSP = new javax.swing.JButton();
        btSuaSP = new javax.swing.JButton();
        btXoaSP = new javax.swing.JButton();
        btLamMoiSP = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        btnChonLoaiSP = new javax.swing.JButton();
        txLoaiSP = new javax.swing.JTextField();
        panelTimKiem2 = new javax.swing.JPanel();
        cbbTimKiemSP = new javax.swing.JComboBox<>();
        txTimKiemSP = new javax.swing.JTextField();
        btTimKiemSP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        panelLoaiSP = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txMaLoai = new javax.swing.JTextField();
        txTenLoai = new javax.swing.JTextField();
        btThemLoaiSP = new javax.swing.JButton();
        btSuaLoaiSP = new javax.swing.JButton();
        btXoaLoaiSP = new javax.swing.JButton();
        btLamMoiLoaiSP = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        cbbTimKiemLoaiSP = new javax.swing.JComboBox<>();
        txTimKiemLoaiSP = new javax.swing.JTextField();
        btTimKiemLoaiSP = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbLoaisp = new javax.swing.JTable();
        panelNhaCungCap = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txMaNCC = new javax.swing.JTextField();
        txTenNCC = new javax.swing.JTextField();
        txDiaChiNCC = new javax.swing.JTextField();
        txSDTNCC = new javax.swing.JTextField();
        btnThemNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        btnXoaNCC = new javax.swing.JButton();
        btnLamMoiNCC = new javax.swing.JButton();
        btnNhapExcelNCC = new javax.swing.JButton();
        btnXuatExcelNCC = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbbTimKiemNCC = new javax.swing.JComboBox<>();
        txTimKiemNCC = new javax.swing.JTextField();
        btnTimKiemNCC = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSNCC = new javax.swing.JTable();
        panelTrangChu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        panelNhanVien = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txMaNV = new javax.swing.JTextField();
        txHoNV = new javax.swing.JTextField();
        txTenNV = new javax.swing.JTextField();
        txSDTNV = new javax.swing.JTextField();
        rdBtnNamNV = new javax.swing.JRadioButton();
        rdBtnNuNV = new javax.swing.JRadioButton();
        ngaySinhNV = new com.toedter.calendar.JDateChooser();
        txDiaChiNV = new javax.swing.JTextField();
        cbbChucVuNV = new javax.swing.JComboBox<>();
        txLuongThangNV = new javax.swing.JTextField();
        btnThemNV = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnLamMoiNV = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        cbbTrangThaiNV = new javax.swing.JComboBox<>();
        btnXuatExcelNV = new javax.swing.JButton();
        btnNhapExcelNV = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cbbTimKiemNV = new javax.swing.JComboBox<>();
        txTimKiemNV = new javax.swing.JTextField();
        btnTimKiemNV = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDSNV = new javax.swing.JTable();
        panelKhachHang = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lbTTkhachhang = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lbMakh = new javax.swing.JLabel();
        lbTenkh = new javax.swing.JLabel();
        lbHokh = new javax.swing.JLabel();
        lbGT = new javax.swing.JLabel();
        lbDC = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        txMakh = new javax.swing.JTextField();
        txHokh = new javax.swing.JTextField();
        txTenkh = new javax.swing.JTextField();
        rdBtnNamKH = new javax.swing.JRadioButton();
        rdBtnNuKH = new javax.swing.JRadioButton();
        txDiaChiKH = new javax.swing.JTextField();
        txSDTKH = new javax.swing.JTextField();
        btThemKH = new javax.swing.JButton();
        btSuaKH = new javax.swing.JButton();
        btXoaKH = new javax.swing.JButton();
        btLammoiKH = new javax.swing.JButton();
        btnNhapExcel = new javax.swing.JButton();
        btnXuatExcelKH = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        cbbTimKiemKH = new javax.swing.JComboBox<>();
        txTimKiemKH = new javax.swing.JTextField();
        btTimKiemKH = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSKH = new javax.swing.JTable();
        panelPhieuNhap = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txMaNCCPN = new javax.swing.JTextField();
        btnChonNCC = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txMaNVPN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ngayNhap = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txMaPhieuNhap = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTaoMoiPN = new javax.swing.JButton();
        btnSuaPN = new javax.swing.JButton();
        btnInPN = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblDSPN = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        cbbTimKiemPN = new javax.swing.JComboBox<>();
        txTimKiemPN = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ngayBatDauPN = new com.toedter.calendar.JDateChooser();
        ngayKetThucPN = new com.toedter.calendar.JDateChooser();
        btnLocPN = new javax.swing.JButton();
        btnBoLocPN = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txMaSPCTPN = new javax.swing.JTextField();
        txDonGiaCTPN = new javax.swing.JTextField();
        txSoLuongCTPN = new javax.swing.JTextField();
        txTongTienNhapCTPN = new javax.swing.JTextField();
        btnChonSP = new javax.swing.JButton();
        btnThemCTPN = new javax.swing.JButton();
        btnSuaCTPN = new javax.swing.JButton();
        btnXoaCTPN = new javax.swing.JButton();
        btnCapNhatPN = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDSCTPN = new javax.swing.JTable();
        panelKhuyenMai = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txMaKM = new javax.swing.JTextField();
        txTenKM = new javax.swing.JTextField();
        txDieuKienKM = new javax.swing.JTextField();
        NgayKetThucKM = new com.toedter.calendar.JDateChooser();
        NgayBatDauKM = new com.toedter.calendar.JDateChooser();
        btnTaoMoiKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        btnXoaKM = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        txTimKiemKM = new javax.swing.JTextField();
        cbbTimKiemKM = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        tuNgayKM = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        denNgayKM = new com.toedter.calendar.JDateChooser();
        btnLocKM = new javax.swing.JButton();
        btnBoLocKM = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblDSKM = new javax.swing.JTable();
        jPanel40 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txMaSPCTKM = new javax.swing.JTextField();
        txPhanTramCTKM = new javax.swing.JTextField();
        btnThemCTKM = new javax.swing.JButton();
        btnSuaCTKM = new javax.swing.JButton();
        btnXoaCTKM = new javax.swing.JButton();
        btnCapNhatKM = new javax.swing.JButton();
        btnChonSanPhamCTKM = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblDSCTKM = new javax.swing.JTable();
        panelBanHang = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        cbbTimKiemSPBH = new javax.swing.JComboBox<>();
        txTimKiemSPBH = new javax.swing.JTextField();
        cbbChonLoaiSP = new javax.swing.JComboBox<>();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblBH = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        txMaSPBH = new javax.swing.JTextField();
        txDonGiaBH = new javax.swing.JTextField();
        txTenSPBH = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txSoLuongBH = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        btnThemGioHang = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txMaHDBH = new javax.swing.JTextField();
        txMaNVBH = new javax.swing.JTextField();
        txMaKHBH = new javax.swing.JTextField();
        txMaKMBH = new javax.swing.JTextField();
        txTongTienBH = new javax.swing.JTextField();
        btnChonMaKH = new javax.swing.JButton();
        btnChonMaKM = new javax.swing.JButton();
        ngayLapBH = new com.toedter.calendar.JDateChooser();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDSGioHang = new javax.swing.JTable();
        jPanel39 = new javax.swing.JPanel();
        btnHuyBH = new javax.swing.JButton();
        btnXoaSPBH = new javax.swing.JButton();
        btnThanhToanBH = new javax.swing.JButton();
        panelHoaDon = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txMaHD = new javax.swing.JTextField();
        txMaNVHD = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txMaKHHD = new javax.swing.JTextField();
        txMaKMHD = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        ngayLapHD = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        txTongTienHD = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        cbbTimKiemHD = new javax.swing.JComboBox<>();
        txTimKiemHD = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        ngayTuHD = new com.toedter.calendar.JDateChooser();
        ngayDenHD = new com.toedter.calendar.JDateChooser();
        btnLocHD = new javax.swing.JButton();
        btnBoLocHD = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblDSHD = new javax.swing.JTable();
        jPanel52 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txMaSPCTHD = new javax.swing.JTextField();
        txDonGiaCTHD = new javax.swing.JTextField();
        txSoLuongCTHD = new javax.swing.JTextField();
        txTongTienCTHD = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblDSCTHD = new javax.swing.JTable();
        panelThongKe = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        lbThongKeSP = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        lbThongKeNV = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        lbThongKeDoanhThu = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        lbThongKeTongDonHang = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        cbthangThongke = new javax.swing.JComboBox<>();
        cbbNam = new javax.swing.JComboBox<>();
        pnchart = new javax.swing.JPanel();
        pnchart1 = new javax.swing.JPanel();
        pnchart3 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblThongKeKH = new javax.swing.JTable();
        jPanel64 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblThongKeSPSapHet = new javax.swing.JTable();
        jLabel79 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        lbThongKeTongChi = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblThongKeTongThu = new javax.swing.JTable();
        jPanel66 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblThongKeTongChi = new javax.swing.JTable();
        jPanel62 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblThongKeTopBanChay = new javax.swing.JTable();
        jPanel63 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        lbThongKeKH = new javax.swing.JLabel();

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N

        cbbTimLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã loại", "Tên loại" }));
        cbbTimLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimLoaiSPActionPerformed(evt);
            }
        });

        txTimLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(cbbTimLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txTimLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTimLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTimLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblKQTKLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblKQTKLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ));
        tblKQTKLoaiSP.setRowHeight(25);
        jScrollPane8.setViewportView(tblKQTKLoaiSP);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        btnDlgChonLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnDlgChonLoaiSP.setText("Chọn");
        btnDlgChonLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnDlgChonLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDlgChonLoaiSPActionPerformed(evt);
            }
        });

        btnDlgThemLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnDlgThemLoaiSP.setText("Thêm");
        btnDlgThemLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnDlgSuaLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnDlgSuaLoaiSP.setText("Sửa");
        btnDlgSuaLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnDlgSuaLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDlgSuaLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnDlgChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDlgThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnDlgSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDlgChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDlgThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDlgSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgChonLoaiSPLayout = new javax.swing.GroupLayout(dlgChonLoaiSP.getContentPane());
        dlgChonLoaiSP.getContentPane().setLayout(dlgChonLoaiSPLayout);
        dlgChonLoaiSPLayout.setHorizontalGroup(
            dlgChonLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgChonLoaiSPLayout.setVerticalGroup(
            dlgChonLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgChonLoaiSPLayout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelMain.setBackground(new java.awt.Color(9, 12, 43));

        pnAdmin.setBackground(new java.awt.Color(9, 12, 43));

        anhAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTenAdmin.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbTenAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lbTenAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbAdmin.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lbAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAdmin.setText("ADMIN");

        lbBanHangAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbBanHangAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbBanHangAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBanHangAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-sales-64.png")); // NOI18N
        lbBanHangAdmin.setText("Bán hàng");
        lbBanHangAdmin.setOpaque(true);
        lbBanHangAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBanHangAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbBanHangAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbBanHangAdminMouseExited(evt);
            }
        });

        lbNhapHangAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbNhapHangAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbNhapHangAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhapHangAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-product1-64.png")); // NOI18N
        lbNhapHangAdmin.setText("Nhập hàng");
        lbNhapHangAdmin.setOpaque(true);
        lbNhapHangAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNhapHangAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNhapHangAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNhapHangAdminMouseExited(evt);
            }
        });

        lbSanPhamAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbSanPhamAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbSanPhamAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSanPhamAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-market-64.png")); // NOI18N
        lbSanPhamAdmin.setText("Sản phẩm");
        lbSanPhamAdmin.setOpaque(true);
        lbSanPhamAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSanPhamAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSanPhamAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSanPhamAdminMouseExited(evt);
            }
        });

        lbHoaDonAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbHoaDonAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbHoaDonAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoaDonAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-paid-bill-64.png")); // NOI18N
        lbHoaDonAdmin.setText("Hóa đơn");
        lbHoaDonAdmin.setOpaque(true);
        lbHoaDonAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHoaDonAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHoaDonAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHoaDonAdminMouseExited(evt);
            }
        });

        lbNhaCungCapAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbNhaCungCapAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbNhaCungCapAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhaCungCapAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-supplier-64.png")); // NOI18N
        lbNhaCungCapAdmin.setText("Nhà cung cấp");
        lbNhaCungCapAdmin.setOpaque(true);
        lbNhaCungCapAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNhaCungCapAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNhaCungCapAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNhaCungCapAdminMouseExited(evt);
            }
        });

        lbKhuyenMaiAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbKhuyenMaiAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbKhuyenMaiAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhuyenMaiAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-sales-64.png")); // NOI18N
        lbKhuyenMaiAdmin.setText("Khuyến mãi");
        lbKhuyenMaiAdmin.setOpaque(true);
        lbKhuyenMaiAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbKhuyenMaiAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbKhuyenMaiAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbKhuyenMaiAdminMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbKhuyenMaiAdminMousePressed(evt);
            }
        });

        lbNhanVienAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbNhanVienAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbNhanVienAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhanVienAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-employee-64.png")); // NOI18N
        lbNhanVienAdmin.setText("Nhân viên");
        lbNhanVienAdmin.setOpaque(true);
        lbNhanVienAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNhanVienAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNhanVienAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNhanVienAdminMouseExited(evt);
            }
        });

        lbKhachHangAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbKhachHangAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbKhachHangAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhachHangAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-customer-64.png")); // NOI18N
        lbKhachHangAdmin.setText("Khách hàng");
        lbKhachHangAdmin.setOpaque(true);
        lbKhachHangAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbKhachHangAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbKhachHangAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbKhachHangAdminMouseExited(evt);
            }
        });

        lbThongKeAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbThongKeAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongKeAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-statistics-32.png")); // NOI18N
        lbThongKeAdmin.setText("Thống kê");
        lbThongKeAdmin.setOpaque(true);
        lbThongKeAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThongKeAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbThongKeAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbThongKeAdminMouseExited(evt);
            }
        });

        lbDangXuatAdmin.setBackground(new java.awt.Color(204, 204, 255));
        lbDangXuatAdmin.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbDangXuatAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDangXuatAdmin.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-logout-50.png")); // NOI18N
        lbDangXuatAdmin.setText("Đăng xuất");
        lbDangXuatAdmin.setOpaque(true);
        lbDangXuatAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDangXuatAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbDangXuatAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbDangXuatAdminMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnAdminLayout = new javax.swing.GroupLayout(pnAdmin);
        pnAdmin.setLayout(pnAdminLayout);
        pnAdminLayout.setHorizontalGroup(
            pnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBanHangAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSanPhamAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNhaCungCapAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbKhuyenMaiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNhanVienAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbKhachHangAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbThongKeAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbDangXuatAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbHoaDonAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNhapHangAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnAdminLayout.createSequentialGroup()
                .addGroup(pnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnAdminLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnAdminLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTenAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anhAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        pnAdminLayout.setVerticalGroup(
            pnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(anhAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lbBanHangAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNhapHangAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSanPhamAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbHoaDonAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNhaCungCapAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbKhuyenMaiAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNhanVienAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbKhachHangAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDangXuatAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnNhanVien.setBackground(new java.awt.Color(9, 12, 43));

        anhNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTenNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbTenNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbTenNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhanVien.setText("NHÂN VIÊN");

        lbBanHangNV.setBackground(new java.awt.Color(204, 204, 255));
        lbBanHangNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbBanHangNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBanHangNV.setText("Bán hàng");
        lbBanHangNV.setOpaque(true);
        lbBanHangNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbBanHangNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbBanHangNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbBanHangNVMouseExited(evt);
            }
        });

        lbNhapHangNV.setBackground(new java.awt.Color(204, 204, 255));
        lbNhapHangNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbNhapHangNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNhapHangNV.setText("Nhập hàng");
        lbNhapHangNV.setOpaque(true);
        lbNhapHangNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNhapHangNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbNhapHangNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbNhapHangNVMouseExited(evt);
            }
        });

        lbSanPhamNV.setBackground(new java.awt.Color(204, 204, 255));
        lbSanPhamNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbSanPhamNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSanPhamNV.setText("Sản phẩm");
        lbSanPhamNV.setOpaque(true);
        lbSanPhamNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSanPhamNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSanPhamNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSanPhamNVMouseExited(evt);
            }
        });

        lbHoaDonNV.setBackground(new java.awt.Color(204, 204, 255));
        lbHoaDonNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbHoaDonNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHoaDonNV.setText("Hóa đơn");
        lbHoaDonNV.setOpaque(true);
        lbHoaDonNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHoaDonNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbHoaDonNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbHoaDonNVMouseExited(evt);
            }
        });

        lbKhachHangNV.setBackground(new java.awt.Color(204, 204, 255));
        lbKhachHangNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbKhachHangNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKhachHangNV.setText("Khách hàng");
        lbKhachHangNV.setOpaque(true);
        lbKhachHangNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbKhachHangNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbKhachHangNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbKhachHangNVMouseExited(evt);
            }
        });

        lbDangXuatNV.setBackground(new java.awt.Color(204, 204, 255));
        lbDangXuatNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbDangXuatNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDangXuatNV.setText("Đăng xuất");
        lbDangXuatNV.setOpaque(true);
        lbDangXuatNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDangXuatNVMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbDangXuatNVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbDangXuatNVMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbBanHangNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSanPhamNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbHoaDonNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNhapHangNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbKhachHangNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbDangXuatNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(anhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lbBanHangNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lbNhapHangNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbSanPhamNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lbHoaDonNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbKhachHangNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbDangXuatNV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnMenuLayout.createSequentialGroup()
                    .addComponent(pnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );

        panelChinh.setBackground(new java.awt.Color(9, 12, 43));

        lbHeader.setBackground(new java.awt.Color(9, 12, 43));
        lbHeader.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        lbHeader.setForeground(new java.awt.Color(255, 255, 255));
        lbHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHeader.setText("__________________________________QUẢN LÝ SIÊU THỊ MINI__________________________________");
        lbHeader.setOpaque(true);

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        panelSanPham.setEnabled(false);
        panelSanPham.setPreferredSize(new java.awt.Dimension(1277, 982));

        btnSanPham.setBackground(new java.awt.Color(153, 255, 255));
        btnSanPham.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnLoaiSP.setBackground(new java.awt.Color(153, 153, 153));
        btnLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnLoaiSP.setText("Loại sản phẩm");
        btnLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChuyenSPLayout = new javax.swing.GroupLayout(panelChuyenSP);
        panelChuyenSP.setLayout(panelChuyenSPLayout);
        panelChuyenSPLayout.setHorizontalGroup(
            panelChuyenSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChuyenSPLayout.createSequentialGroup()
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 907, Short.MAX_VALUE))
        );
        panelChuyenSPLayout.setVerticalGroup(
            panelChuyenSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(btnLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelChonSP.setPreferredSize(new java.awt.Dimension(1277, 777));

        jPanel24.setBackground(new java.awt.Color(110, 89, 222));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-product-64.png")); // NOI18N
        jLabel37.setText("Thông tin sản phẩm");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(110, 89, 222));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Mã sản phẩm");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Tên sản phẩm");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Loại sản phẩm");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Đơn giá");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Số lượng");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Đơn vị tính");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Trạng thái");

        txMaSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txMaSP.setEnabled(false);

        txTenSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txDonGiaSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txSoLuongSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txDonViTinhSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        cbbTrangThaiSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbTrangThaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Hết hàng" }));

        btThemSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btThemSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btThemSP.setText("Thêm");
        btThemSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btThemSP.setPreferredSize(new java.awt.Dimension(90, 40));
        btThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemSPActionPerformed(evt);
            }
        });

        btSuaSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btSuaSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btSuaSP.setText("Sửa");
        btSuaSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btSuaSP.setPreferredSize(new java.awt.Dimension(90, 40));
        btSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaSPActionPerformed(evt);
            }
        });

        btXoaSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btXoaSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btXoaSP.setText("Xóa");
        btXoaSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btXoaSP.setPreferredSize(new java.awt.Dimension(90, 40));
        btXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaSPActionPerformed(evt);
            }
        });

        btLamMoiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btLamMoiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btLamMoiSP.setText("Làm mới");
        btLamMoiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btLamMoiSP.setPreferredSize(new java.awt.Dimension(90, 40));
        btLamMoiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoiSPActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        jButton16.setText("Nhập");
        jButton16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        jButton17.setText("Xuất");
        jButton17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        btnChonLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnChonLoaiSP.setText("...");
        btnChonLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonLoaiSPActionPerformed(evt);
            }
        });

        txLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel39)
                                .addComponent(jLabel38))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(txMaSP)
                    .addComponent(btSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txLoaiSP))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43))
                        .addGap(17, 17, 17)))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(btLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDonGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDonViTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addComponent(jLabel44)
                        .addGap(38, 38, 38)))
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(cbbTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txDonGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(cbbTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(txSoLuongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel43)
                    .addComponent(txDonViTinhSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        panelTimKiem2.setBackground(new java.awt.Color(110, 89, 222));
        panelTimKiem2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Mã loại", "Trạng thái" }));

        txTimKiemSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTimKiemSPActionPerformed(evt);
            }
        });

        btTimKiemSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btTimKiemSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-search-24.png")); // NOI18N
        btTimKiemSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTimKiem2Layout = new javax.swing.GroupLayout(panelTimKiem2);
        panelTimKiem2.setLayout(panelTimKiem2Layout);
        panelTimKiem2Layout.setHorizontalGroup(
            panelTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiem2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(txTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        panelTimKiem2Layout.setVerticalGroup(
            panelTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimKiem2Layout.createSequentialGroup()
                .addGroup(panelTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(110, 89, 222));

        tbSanPham.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Mã loại", "Đơn giá", "Số lượng", "Đơn vị tính", "Trạng thái"
            }
        ));
        tbSanPham.setRowHeight(25);
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSPLayout = new javax.swing.GroupLayout(panelSP);
        panelSP.setLayout(panelSPLayout);
        panelSPLayout.setHorizontalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSPLayout.createSequentialGroup()
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTimKiem2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSPLayout.setVerticalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSPLayout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelLoaiSP.setBackground(new java.awt.Color(110, 89, 222));
        panelLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        panelLoaiSP.setPreferredSize(new java.awt.Dimension(1265, 787));

        jPanel21.setBackground(new java.awt.Color(110, 89, 222));
        jPanel21.setForeground(new java.awt.Color(255, 255, 255));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-ibeacon-48.png")); // NOI18N
        jLabel36.setText("Thông tin loại sản phẩm");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(110, 89, 222));
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Mã loại");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Tên loại");

        txMaLoai.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txMaLoai.setEnabled(false);

        txTenLoai.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTenLoai.setBorder(null);
        txTenLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTenLoaiActionPerformed(evt);
            }
        });

        btThemLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btThemLoaiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btThemLoaiSP.setText("Thêm");
        btThemLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btThemLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemLoaiSPActionPerformed(evt);
            }
        });

        btSuaLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btSuaLoaiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btSuaLoaiSP.setText("Sửa");
        btSuaLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btSuaLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaLoaiSPActionPerformed(evt);
            }
        });

        btXoaLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btXoaLoaiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btXoaLoaiSP.setText("Xóa");
        btXoaLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btXoaLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaLoaiSPActionPerformed(evt);
            }
        });

        btLamMoiLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btLamMoiLoaiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btLamMoiLoaiSP.setText("Làm mới");
        btLamMoiLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btLamMoiLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoiLoaiSPActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        jButton4.setText("Nhập");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        jButton5.setText("Xuất");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addGap(125, 125, 125)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(497, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(btXoaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btLamMoiLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btXoaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btLamMoiLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jPanel23.setBackground(new java.awt.Color(110, 89, 222));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel23.setForeground(new java.awt.Color(255, 255, 255));

        cbbTimKiemLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã loại", "Tên loại" }));
        cbbTimKiemLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemLoaiSPActionPerformed(evt);
            }
        });

        txTimKiemLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btTimKiemLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btTimKiemLoaiSP.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-search-24.png")); // NOI18N
        btTimKiemLoaiSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btTimKiemLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(458, 458, 458)
                .addComponent(cbbTimKiemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(txTimKiemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btTimKiemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbTimKiemLoaiSP)
                    .addComponent(btTimKiemLoaiSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                        .addComponent(txTimKiemLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(110, 89, 222));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));

        tbLoaisp.setAutoCreateRowSorter(true);
        tbLoaisp.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tbLoaisp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại", "Tên loại"
            }
        ));
        tbLoaisp.setRowHeight(25);
        tbLoaisp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLoaispMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbLoaisp);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelLoaiSPLayout = new javax.swing.GroupLayout(panelLoaiSP);
        panelLoaiSP.setLayout(panelLoaiSPLayout);
        panelLoaiSPLayout.setHorizontalGroup(
            panelLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoaiSPLayout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLoaiSPLayout.setVerticalGroup(
            panelLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoaiSPLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelChonSPLayout = new javax.swing.GroupLayout(panelChonSP);
        panelChonSP.setLayout(panelChonSPLayout);
        panelChonSPLayout.setHorizontalGroup(
            panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelChonSPLayout.createSequentialGroup()
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelChonSPLayout.createSequentialGroup()
                    .addComponent(panelLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelChonSPLayout.setVerticalGroup(
            panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
            .addGroup(panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelChonSPLayout.createSequentialGroup()
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 124, Short.MAX_VALUE)))
            .addGroup(panelChonSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelChonSPLayout.createSequentialGroup()
                    .addComponent(panelLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addComponent(panelChuyenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelSanPhamLayout.createSequentialGroup()
                    .addComponent(panelChonSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addComponent(panelChuyenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(936, Short.MAX_VALUE))
            .addGroup(panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSanPhamLayout.createSequentialGroup()
                    .addContainerGap(48, Short.MAX_VALUE)
                    .addComponent(panelChonSP, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        panelNhaCungCap.setPreferredSize(new java.awt.Dimension(1529, 791));

        jPanel3.setBackground(new java.awt.Color(110, 89, 222));
        jPanel3.setPreferredSize(new java.awt.Dimension(304, 75));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-supplier1-48.png")); // NOI18N
        jLabel2.setText("Thông tin nhà cung cấp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(110, 89, 222));
        jPanel5.setPreferredSize(new java.awt.Dimension(1529, 479));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mã nhà cung cấp");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tên nhà cung cấp");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Đia chỉ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Số điện thoại");

        txMaNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaNCC.setEnabled(false);

        txTenNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTenNCCActionPerformed(evt);
            }
        });

        txDiaChiNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txSDTNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btnThemNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnThemNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btnThemNCC.setText("Thêm");
        btnThemNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThemNCC.setPreferredSize(new java.awt.Dimension(110, 40));
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        btnSuaNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSuaNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaNCC.setText("Sửa");
        btnSuaNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(110, 40));
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });

        btnXoaNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXoaNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaNCC.setText("Xóa");
        btnXoaNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaNCC.setPreferredSize(new java.awt.Dimension(110, 40));
        btnXoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNCCActionPerformed(evt);
            }
        });

        btnLamMoiNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnLamMoiNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btnLamMoiNCC.setText("Làm mới");
        btnLamMoiNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnLamMoiNCC.setPreferredSize(new java.awt.Dimension(110, 40));
        btnLamMoiNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNCCActionPerformed(evt);
            }
        });

        btnNhapExcelNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnNhapExcelNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnNhapExcelNCC.setText("Nhập");
        btnNhapExcelNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnNhapExcelNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelNCCActionPerformed(evt);
            }
        });

        btnXuatExcelNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXuatExcelNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnXuatExcelNCC.setText("Xuất");
        btnXuatExcelNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXuatExcelNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(228, 228, 228)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnXoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(txDiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(112, 112, 112))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(txSDTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNhapExcelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btnXuatExcelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(txMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txSDTNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapExcelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel6.setBackground(new java.awt.Color(110, 89, 222));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(761, 85));

        cbbTimKiemNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhà cung cấp", "Tên nhà cung cấp" }));

        txTimKiemNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btnTimKiemNCC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnTimKiemNCC.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-search-24.png")); // NOI18N
        btnTimKiemNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnTimKiemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(txTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(110, 89, 222));
        jPanel7.setPreferredSize(new java.awt.Dimension(452, 270));

        tblDSNCC.setAutoCreateRowSorter(true);
        tblDSNCC.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblDSNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"
            }
        ));
        tblDSNCC.setRowHeight(25);
        tblDSNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNCCMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSNCC);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNhaCungCapLayout = new javax.swing.GroupLayout(panelNhaCungCap);
        panelNhaCungCap.setLayout(panelNhaCungCapLayout);
        panelNhaCungCapLayout.setHorizontalGroup(
            panelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
        );
        panelNhaCungCapLayout.setVerticalGroup(
            panelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhaCungCapLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTrangChu.setPreferredSize(new java.awt.Dimension(1529, 930));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\trangchu2.jpg")); // NOI18N

        javax.swing.GroupLayout panelTrangChuLayout = new javax.swing.GroupLayout(panelTrangChu);
        panelTrangChu.setLayout(panelTrangChuLayout);
        panelTrangChuLayout.setHorizontalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrangChuLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTrangChuLayout.setVerticalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelNhanVien.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel8.setBackground(new java.awt.Color(110, 89, 222));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-staff-100.png")); // NOI18N
        jLabel15.setText("Thông tin nhân viên");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(110, 89, 222));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Mã");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Họ");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tên");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Giới tính");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Ngày sinh");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Số điện thoại");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Địa chỉ");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Chức vụ");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Lương tháng");

        txMaNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txMaNV.setEnabled(false);

        txHoNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txTenNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txSDTNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        rdBtnNamNV.setBackground(new java.awt.Color(110, 89, 222));
        buttonGroup1.add(rdBtnNamNV);
        rdBtnNamNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        rdBtnNamNV.setForeground(new java.awt.Color(255, 255, 255));
        rdBtnNamNV.setText("Nam");

        rdBtnNuNV.setBackground(new java.awt.Color(110, 89, 222));
        buttonGroup1.add(rdBtnNuNV);
        rdBtnNuNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        rdBtnNuNV.setForeground(new java.awt.Color(255, 255, 255));
        rdBtnNuNV.setText("Nữ");

        ngaySinhNV.setDateFormatString("dd/MM/yyyy");
        ngaySinhNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txDiaChiNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txDiaChiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDiaChiNVActionPerformed(evt);
            }
        });

        cbbChucVuNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbChucVuNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));

        txLuongThangNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btnThemNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnThemNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThemNV.setPreferredSize(new java.awt.Dimension(100, 40));
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnSuaNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSuaNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaNV.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        btnXoaNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXoaNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaNV.setPreferredSize(new java.awt.Dimension(100, 40));
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btnLamMoiNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnLamMoiNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btnLamMoiNV.setText("Làm mới");
        btnLamMoiNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnLamMoiNV.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLamMoiNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNVActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Trạng thái");

        cbbTrangThaiNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbbTrangThaiNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiện hành", "Đã nghỉ" }));
        cbbTrangThaiNV.setBorder(null);
        cbbTrangThaiNV.setPreferredSize(new java.awt.Dimension(100, 40));

        btnXuatExcelNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXuatExcelNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnXuatExcelNV.setText("Xuất");
        btnXuatExcelNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXuatExcelNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelNVActionPerformed(evt);
            }
        });

        btnNhapExcelNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnNhapExcelNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnNhapExcelNV.setText("Nhập");
        btnNhapExcelNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnNhapExcelNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel19)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(8, 8, 8)))
                                .addGap(49, 49, 49)))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(rdBtnNamNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdBtnNuNV))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnLamMoiNV, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addGap(87, 87, 87)
                        .addComponent(btnNhapExcelNV, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbChucVuNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txLuongThangNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTrangThaiNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatExcelNV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel16)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20))
                            .addComponent(ngaySinhNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbChucVuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addComponent(txSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel22))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(rdBtnNamNV)
                                    .addComponent(rdBtnNuNV)))
                            .addComponent(txDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNhapExcelNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatExcelNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txLuongThangNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(cbbTrangThaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(110, 89, 222));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Họ", "Tên", "Giới tính", "Chức vụ", "Trạng thái" }));

        txTimKiemNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btnTimKiemNV.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnTimKiemNV.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-search-24.png")); // NOI18N
        btnTimKiemNV.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(110, 89, 222));

        tblDSNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblDSNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Chức vụ", "Lương tháng", "Trạng thái"
            }
        ));
        tblDSNV.setRowHeight(25);
        tblDSNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNVMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDSNV);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNhanVienLayout = new javax.swing.GroupLayout(panelNhanVien);
        panelNhanVien.setLayout(panelNhanVienLayout);
        panelNhanVienLayout.setHorizontalGroup(
            panelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNhanVienLayout.setVerticalGroup(
            panelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelKhachHang.setPreferredSize(new java.awt.Dimension(1238, 956));

        jPanel12.setBackground(new java.awt.Color(110, 89, 222));

        lbTTkhachhang.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        lbTTkhachhang.setForeground(new java.awt.Color(255, 255, 255));
        lbTTkhachhang.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-customer1-64.png")); // NOI18N
        lbTTkhachhang.setText("Thông tin khách hàng");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(lbTTkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTTkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(110, 89, 222));

        lbMakh.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbMakh.setForeground(new java.awt.Color(255, 255, 255));
        lbMakh.setText("Mã khách hàng");

        lbTenkh.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbTenkh.setForeground(new java.awt.Color(255, 255, 255));
        lbTenkh.setText("Tên khách hàng");

        lbHokh.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbHokh.setForeground(new java.awt.Color(255, 255, 255));
        lbHokh.setText("Họ khách hàng");

        lbGT.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbGT.setForeground(new java.awt.Color(255, 255, 255));
        lbGT.setText("Giới tính");

        lbDC.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbDC.setForeground(new java.awt.Color(255, 255, 255));
        lbDC.setText("Địa chỉ");

        lbSDT.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbSDT.setForeground(new java.awt.Color(255, 255, 255));
        lbSDT.setText("Số điện thoại");

        txMakh.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txMakh.setEnabled(false);

        txHokh.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txHokh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHokhActionPerformed(evt);
            }
        });

        txTenkh.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        rdBtnNamKH.setBackground(new java.awt.Color(110, 89, 222));
        buttonGroup2.add(rdBtnNamKH);
        rdBtnNamKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        rdBtnNamKH.setForeground(new java.awt.Color(255, 255, 255));
        rdBtnNamKH.setText("Nam");

        rdBtnNuKH.setBackground(new java.awt.Color(110, 89, 222));
        buttonGroup2.add(rdBtnNuKH);
        rdBtnNuKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        rdBtnNuKH.setForeground(new java.awt.Color(255, 255, 255));
        rdBtnNuKH.setText("Nữ");

        txDiaChiKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        txSDTKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btThemKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btThemKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btThemKH.setText("Thêm");
        btThemKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btThemKH.setPreferredSize(new java.awt.Dimension(100, 40));
        btThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemKHActionPerformed(evt);
            }
        });

        btSuaKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btSuaKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btSuaKH.setText("Sửa");
        btSuaKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btSuaKH.setPreferredSize(new java.awt.Dimension(100, 40));
        btSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaKHActionPerformed(evt);
            }
        });

        btXoaKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btXoaKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btXoaKH.setText("Xóa");
        btXoaKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btXoaKH.setPreferredSize(new java.awt.Dimension(100, 40));
        btXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaKHActionPerformed(evt);
            }
        });

        btLammoiKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btLammoiKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btLammoiKH.setText("Làm mới");
        btLammoiKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btLammoiKH.setPreferredSize(new java.awt.Dimension(100, 40));
        btLammoiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLammoiKHActionPerformed(evt);
            }
        });

        btnNhapExcel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnNhapExcel.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnNhapExcel.setText("Nhập");
        btnNhapExcel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });

        btnXuatExcelKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXuatExcelKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_ms_excel_30px.png")); // NOI18N
        btnXuatExcelKH.setText("Xuất");
        btnXuatExcelKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXuatExcelKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(lbMakh)
                            .addGap(114, 114, 114)
                            .addComponent(txMakh, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addComponent(lbHokh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txTenkh)
                                .addComponent(txHokh, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                    .addComponent(lbTenkh)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(btXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSDT)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(lbGT)
                                    .addGap(218, 218, 218)
                                    .addComponent(rdBtnNuKH))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(lbDC)
                                    .addGap(140, 140, 140)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rdBtnNamKH)
                                        .addComponent(txSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btLammoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnXuatExcelKH, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMakh)
                            .addComponent(lbGT)
                            .addComponent(txMakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdBtnNamKH)
                            .addComponent(rdBtnNuKH))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txHokh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbDC)
                                .addComponent(txDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbHokh))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTenkh)
                            .addComponent(txTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSDT)
                            .addComponent(txSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btLammoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXuatExcelKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(110, 89, 222));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã KH", "Họ KH", "Tên KH", "Giới tính" }));
        cbbTimKiemKH.setPreferredSize(new java.awt.Dimension(64, 25));
        cbbTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemKHActionPerformed(evt);
            }
        });

        txTimKiemKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        btTimKiemKH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btTimKiemKH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-search-24.png")); // NOI18N
        btTimKiemKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(654, 654, 654)
                .addComponent(cbbTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(txTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTimKiemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txTimKiemKH))
                    .addComponent(btTimKiemKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(110, 89, 222));

        tblDSKH.setAutoCreateRowSorter(true);
        tblDSKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblDSKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Giới tính", "Địa chỉ", "Số điện thoại"
            }
        ));
        tblDSKH.setRowHeight(25);
        tblDSKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSKH);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelKhachHangLayout = new javax.swing.GroupLayout(panelKhachHang);
        panelKhachHang.setLayout(panelKhachHangLayout);
        panelKhachHangLayout.setHorizontalGroup(
            panelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelKhachHangLayout.setVerticalGroup(
            panelKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelPhieuNhap.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel17.setBackground(new java.awt.Color(110, 89, 222));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nhà cung cấp");

        txMaNCCPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaNCCPN.setEnabled(false);
        txMaNCCPN.setPreferredSize(new java.awt.Dimension(6, 30));

        btnChonNCC.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnChonNCC.setText("...");
        btnChonNCC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonNCC.setPreferredSize(new java.awt.Dimension(80, 30));
        btnChonNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNCCActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nhân viên");

        txMaNVPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaNVPN.setEnabled(false);
        txMaNVPN.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày nhập");

        ngayNhap.setDateFormatString("dd/MM/yyyy");
        ngayNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã phiếu nhập");

        txMaPhieuNhap.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaPhieuNhap.setForeground(new java.awt.Color(255, 255, 255));
        txMaPhieuNhap.setEnabled(false);
        txMaPhieuNhap.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PHIẾU NHẬP");

        btnTaoMoiPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTaoMoiPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btnTaoMoiPN.setText("Tạo mới");
        btnTaoMoiPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnTaoMoiPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnTaoMoiPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiPNActionPerformed(evt);
            }
        });

        btnSuaPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaPN.setText("Sửa");
        btnSuaPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnSuaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPNActionPerformed(evt);
            }
        });

        btnInPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnInPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-pdf-48.png")); // NOI18N
        btnInPN.setText("In phiếu");
        btnInPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnInPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnInPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txMaNCCPN, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txMaNVPN, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(321, 321, 321)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSuaPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInPN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTaoMoiPN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngayNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txMaNCCPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txMaNVPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnTaoMoiPN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnInPN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(110, 89, 222));

        tblDSPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Tổng tiền"
            }
        ));
        tblDSPN.setRowHeight(22);
        tblDSPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSPNMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblDSPN);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(110, 89, 222));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbbTimKiemPN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên" }));
        cbbTimKiemPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemPNActionPerformed(evt);
            }
        });

        txTimKiemPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Từ");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Đến");

        ngayBatDauPN.setDateFormatString("dd/MM/yyyy");
        ngayBatDauPN.setFocusable(false);
        ngayBatDauPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        ngayKetThucPN.setDateFormatString("dd/MM/yyyy");
        ngayKetThucPN.setFocusable(false);
        ngayKetThucPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnLocPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLocPN.setText("Lọc");
        btnLocPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnLocPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocPNActionPerformed(evt);
            }
        });

        btnBoLocPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnBoLocPN.setText("Bỏ lọc");
        btnBoLocPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnBoLocPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(cbbTimKiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txTimKiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ngayBatDauPN, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ngayKetThucPN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btnLocPN, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBoLocPN, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngayBatDauPN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbTimKiemPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txTimKiemPN)
                                .addComponent(jLabel28)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ngayKetThucPN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLocPN, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(btnBoLocPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel18.setBackground(new java.awt.Color(110, 89, 222));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CHI TIẾT PHIẾU NHẬP");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sản phẩm");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Đơn giá");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Số lượng");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Tổng tiền nhập");

        txMaSPCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaSPCTPN.setEnabled(false);
        txMaSPCTPN.setPreferredSize(new java.awt.Dimension(6, 30));

        txDonGiaCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txDonGiaCTPN.setEnabled(false);
        txDonGiaCTPN.setPreferredSize(new java.awt.Dimension(6, 30));

        txSoLuongCTPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txSoLuongCTPN.setPreferredSize(new java.awt.Dimension(6, 30));
        txSoLuongCTPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txSoLuongCTPNKeyPressed(evt);
            }
        });

        txTongTienNhapCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txTongTienNhapCTPN.setEnabled(false);
        txTongTienNhapCTPN.setPreferredSize(new java.awt.Dimension(6, 30));

        btnChonSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnChonSP.setText("...");
        btnChonSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonSP.setPreferredSize(new java.awt.Dimension(80, 30));
        btnChonSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonSPActionPerformed(evt);
            }
        });

        btnThemCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemCTPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btnThemCTPN.setText("Thêm");
        btnThemCTPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThemCTPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnThemCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPNActionPerformed(evt);
            }
        });

        btnSuaCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaCTPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaCTPN.setText("Sửa");
        btnSuaCTPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaCTPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnSuaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTPNActionPerformed(evt);
            }
        });

        btnXoaCTPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaCTPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaCTPN.setText("Xóa");
        btnXoaCTPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaCTPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnXoaCTPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTPNActionPerformed(evt);
            }
        });

        btnCapNhatPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCapNhatPN.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_downloads_30px.png")); // NOI18N
        btnCapNhatPN.setText("Nhập hàng");
        btnCapNhatPN.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnCapNhatPN.setPreferredSize(new java.awt.Dimension(80, 30));
        btnCapNhatPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatPNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnThemCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txMaSPCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChonSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnSuaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(btnCapNhatPN, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txDonGiaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel26)
                        .addGap(37, 37, 37)
                        .addComponent(txSoLuongCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txTongTienNhapCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(508, 508, 508)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(32, 32, 32)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(txMaSPCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDonGiaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSoLuongCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTongTienNhapCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 86, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatPN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemCTPN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(110, 89, 222));

        jScrollPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane6MouseClicked(evt);
            }
        });

        tblDSCTPN.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Mã sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền nhập"
            }
        ));
        tblDSCTPN.setRowHeight(22);
        tblDSCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSCTPNMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDSCTPN);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPhieuNhapLayout = new javax.swing.GroupLayout(panelPhieuNhap);
        panelPhieuNhap.setLayout(panelPhieuNhapLayout);
        panelPhieuNhapLayout.setHorizontalGroup(
            panelPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelPhieuNhapLayout.setVerticalGroup(
            panelPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPhieuNhapLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelKhuyenMai.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel41.setBackground(new java.awt.Color(110, 89, 222));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Mã khuyến mãi");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Tên khuyến mãi");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Ngày bắt đầu");

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Ngày kết thúc");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Điều kiện");

        txMaKM.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        txMaKM.setEnabled(false);
        txMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMaKMActionPerformed(evt);
            }
        });

        txTenKM.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTenKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTenKMActionPerformed(evt);
            }
        });

        txDieuKienKM.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        NgayKetThucKM.setDateFormatString("dd/MM/yyyy");
        NgayKetThucKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        NgayBatDauKM.setDateFormatString("dd/MM/yyyy");
        NgayBatDauKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnTaoMoiKM.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnTaoMoiKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        btnTaoMoiKM.setText("Tạo mới");
        btnTaoMoiKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnTaoMoiKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiKMActionPerformed(evt);
            }
        });

        btnSuaKM.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnSuaKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaKM.setText("Sửa");
        btnSuaKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        btnXoaKM.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXoaKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaKM.setText("Xóa");
        btnXoaKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKMActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-promotion-64.png")); // NOI18N
        jLabel32.setText("KHUYẾN MÃI");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txMaKM, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(txTenKM))
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NgayKetThucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(NgayBatDauKM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel48))))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addComponent(txDieuKienKM, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTaoMoiKM, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txMaKM)
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txTenKM)
                                .addContainerGap(34, Short.MAX_VALUE))))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txDieuKienKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnSuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NgayBatDauKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoMoiKM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NgayKetThucKM, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)))))
        );

        jPanel42.setBackground(new java.awt.Color(110, 89, 222));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        txTimKiemKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        cbbTimKiemKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbbTimKiemKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã khuyến mãi", "Tên khuyến mãi", "Điều kiện" }));
        cbbTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemKMActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Từ ngày");

        tuNgayKM.setDateFormatString("dd/MM/yyyy");
        tuNgayKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Đến ngày");

        denNgayKM.setDateFormatString("dd/MM/yyyy");

        btnLocKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLocKM.setText("Lọc");
        btnLocKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnLocKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocKMActionPerformed(evt);
            }
        });

        btnBoLocKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnBoLocKM.setText("Bỏ lọc");
        btnBoLocKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnBoLocKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cbbTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tuNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(denNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnLocKM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBoLocKM, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tuNgayKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49))
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(denNgayKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBoLocKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLocKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        tblDSKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Điều kiện"
            }
        ));
        tblDSKM.setRowHeight(22);
        tblDSKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSKMMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblDSKM);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );

        jPanel45.setBackground(new java.awt.Color(110, 89, 222));

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("CHI TIẾT KHUYẾN MÃI");

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Mã sản phẩm");

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Phần trăm khuyến mãi");

        txMaSPCTKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaSPCTKM.setEnabled(false);

        txPhanTramCTKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnThemCTKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemCTKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btnThemCTKM.setText("Thêm");
        btnThemCTKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThemCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTKMActionPerformed(evt);
            }
        });

        btnSuaCTKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSuaCTKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        btnSuaCTKM.setText("Sửa");
        btnSuaCTKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnSuaCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTKMActionPerformed(evt);
            }
        });

        btnXoaCTKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaCTKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaCTKM.setText("Xóa");
        btnXoaCTKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTKMActionPerformed(evt);
            }
        });

        btnCapNhatKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCapNhatKM.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_downloads_30px.png")); // NOI18N
        btnCapNhatKM.setText("Cập nhật khuyến mãi");
        btnCapNhatKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnCapNhatKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKMActionPerformed(evt);
            }
        });

        btnChonSanPhamCTKM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnChonSanPhamCTKM.setText("...");
        btnChonSanPhamCTKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonSanPhamCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonSanPhamCTKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel53))
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(btnThemCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txMaSPCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnChonSanPhamCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(382, 382, 382)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txPhanTramCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(btnSuaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(171, 171, 171)
                                .addComponent(btnXoaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                                .addComponent(btnCapNhatKM, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(jLabel51)))
                .addGap(53, 53, 53))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addGap(10, 10, 10)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(txMaSPCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnChonSanPhamCTKM))
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel54)
                        .addComponent(txPhanTramCTKM)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhatKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblDSCTKM.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSCTKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã khuyến mãi", "Mã sản phẩm", "% khuyến mãi"
            }
        ));
        tblDSCTKM.setRowHeight(22);
        tblDSCTKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSCTKMMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblDSCTKM);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelKhuyenMaiLayout = new javax.swing.GroupLayout(panelKhuyenMai);
        panelKhuyenMai.setLayout(panelKhuyenMaiLayout);
        panelKhuyenMaiLayout.setHorizontalGroup(
            panelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelKhuyenMaiLayout.setVerticalGroup(
            panelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelBanHang.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel33.setBackground(new java.awt.Color(110, 89, 222));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemSPBH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbTimKiemSPBH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sản phẩm", "Tên sản phẩm" }));
        cbbTimKiemSPBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemSPBHActionPerformed(evt);
            }
        });

        txTimKiemSPBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        cbbChonLoaiSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbChonLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn loại sp", "Đông lạnh", "Bánh kẹo", "Đồ uống", "Đồ hộp", "Gia vị", "Sữa", "Thức ăn nhanh", "Thực phẩm khô", "Mỹ phẩm", "Văn phòng phẩm", "Mẹ và bé", "Gia dụng", "Đồ dùng cá nhân" }));
        cbbChonLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChonLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbTimKiemSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txTimKiemSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTimKiemSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTimKiemSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbChonLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));

        tblBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Còn lại", "Đơn vị tính"
            }
        ));
        tblBH.setRowHeight(25);
        tblBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBHMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblBH);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        jPanel35.setBackground(new java.awt.Color(110, 89, 222));

        txMaSPBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaSPBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txMaSPBH.setEnabled(false);

        txDonGiaBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txDonGiaBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txDonGiaBH.setEnabled(false);

        txTenSPBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTenSPBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txTenSPBH.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Chi tiết sản phẩm");

        txSoLuongBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txSoLuongBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Số lượng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txMaSPBH, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(txDonGiaBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txTenSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSoLuongBH, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(32, 32, 32)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txMaSPBH, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(txTenSPBH))
                .addGap(18, 18, 18)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txSoLuongBH, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(txDonGiaBH))
                .addContainerGap())
        );

        jPanel36.setBackground(new java.awt.Color(110, 89, 222));

        btnThemGioHang.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnThemGioHang.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        btnThemGioHang.setText("Thêm");
        btnThemGioHang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThemGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jPanel37.setBackground(new java.awt.Color(110, 89, 222));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Chi tiết đơn hàng");

        txMaHDBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaHDBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        txMaHDBH.setEnabled(false);

        txMaNVBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaNVBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txMaNVBH.setEnabled(false);

        txMaKHBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaKHBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
        txMaKHBH.setEnabled(false);

        txMaKMBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txMaKMBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txMaKMBH.setEnabled(false);

        txTongTienBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txTongTienBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tổng tiền", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        txTongTienBH.setEnabled(false);
        txTongTienBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTongTienBHActionPerformed(evt);
            }
        });

        btnChonMaKH.setText("...");
        btnChonMaKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonMaKHActionPerformed(evt);
            }
        });

        btnChonMaKM.setText("...");
        btnChonMaKM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChonMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonMaKMActionPerformed(evt);
            }
        });

        ngayLapBH.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ngày lập HD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20))); // NOI18N
        ngayLapBH.setDateFormatString("dd/MM/yyyy");
        ngayLapBH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(219, 219, 219))
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txMaHDBH, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(txMaKHBH))
                    .addComponent(txMaKMBH, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChonMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txTongTienBH)
                    .addComponent(ngayLapBH, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(txMaNVBH))
                .addGap(28, 28, 28))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGap(37, 37, 37)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txMaHDBH)
                    .addComponent(txMaNVBH, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txMaKHBH, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnChonMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnChonMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txMaKMBH))))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(ngayLapBH, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txTongTienBH, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(158, 158, 158))
        );

        tblDSGioHang.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblDSGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        tblDSGioHang.setRowHeight(25);
        jScrollPane10.setViewportView(tblDSGioHang);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel39.setBackground(new java.awt.Color(110, 89, 222));

        btnHuyBH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnHuyBH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_delete_forever_30px_1.png")); // NOI18N
        btnHuyBH.setText("Hủy");
        btnHuyBH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnHuyBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBHActionPerformed(evt);
            }
        });

        btnXoaSPBH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnXoaSPBH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        btnXoaSPBH.setText("Xóa");
        btnXoaSPBH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnXoaSPBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPBHActionPerformed(evt);
            }
        });

        btnThanhToanBH.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnThanhToanBH.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_us_dollar_30px.png")); // NOI18N
        btnThanhToanBH.setText("Thanh toán");
        btnThanhToanBH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnThanhToanBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanBHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnHuyBH, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(btnXoaSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btnThanhToanBH, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyBH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToanBH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout panelBanHangLayout = new javax.swing.GroupLayout(panelBanHang);
        panelBanHang.setLayout(panelBanHangLayout);
        panelBanHangLayout.setHorizontalGroup(
            panelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBanHangLayout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBanHangLayout.setVerticalGroup(
            panelBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelHoaDon.setBackground(new java.awt.Color(110, 89, 222));
        panelHoaDon.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel49.setBackground(new java.awt.Color(110, 89, 222));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("HÓA ĐƠN");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Mã hóa đơn");

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Mã nhân viên");

        txMaHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaHD.setEnabled(false);

        txMaNVHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaNVHD.setEnabled(false);

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Mã khách hàng ");

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Mã khuyến mãi");

        txMaKHHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaKHHD.setEnabled(false);

        txMaKMHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaKMHD.setEnabled(false);

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Ngày lập");

        ngayLapHD.setDateFormatString("dd/MM/yyyy");
        ngayLapHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Tổng tiền");

        txTongTienHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txTongTienHD.setEnabled(false);

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-refresh-24.png")); // NOI18N
        jButton7.setText("Tạo mới");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        jButton8.setText("Sửa");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-pdf-48.png")); // NOI18N
        jButton9.setText("In phiếu");
        jButton9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton10.setText("...");
        jButton10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton11.setText("...");
        jButton11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(jLabel52))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(txMaNVHD))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txMaKHHD, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(txMaKMHD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ngayLapHD, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(txTongTienHD))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel49Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(9, 9, 9))
                                .addGroup(jPanel49Layout.createSequentialGroup()
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txMaKHHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ngayLapHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel58)
                                    .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txMaKMHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txMaNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel50.setBackground(new java.awt.Color(110, 89, 222));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cbbTimKiemHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Mã khuyến mãi" }));
        cbbTimKiemHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemHDActionPerformed(evt);
            }
        });

        txTimKiemHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Từ ngày");

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Đến ngày");

        ngayTuHD.setDateFormatString("dd/MM/yyyy");
        ngayTuHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        ngayDenHD.setDateFormatString("dd/MM/yyyy");
        ngayDenHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btnLocHD.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnLocHD.setText("Lọc");
        btnLocHD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnLocHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocHDActionPerformed(evt);
            }
        });

        btnBoLocHD.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnBoLocHD.setText("Bỏ lọc");
        btnBoLocHD.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBoLocHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoLocHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cbbTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(18, 18, 18)
                .addComponent(ngayTuHD, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ngayDenHD, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnLocHD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnBoLocHD, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayDenHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLocHD)
                                .addComponent(btnBoLocHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel61)
                                .addComponent(txTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ngayTuHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel51.setBackground(new java.awt.Color(110, 89, 222));

        tblDSHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Mã khuyến mãi", "Ngày lập", "Tổng tiền"
            }
        ));
        tblDSHD.setRowHeight(22);
        tblDSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHDMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblDSHD);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );

        jPanel52.setBackground(new java.awt.Color(110, 89, 222));

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("CHI TIẾT HÓA ĐƠN");

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Mã sản phẩm");

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Đơn giá");

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Số lượng");

        jLabel67.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Tổng tiền");

        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-edit-24.png")); // NOI18N
        jButton12.setText("Sửa");
        jButton12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-add-24.png")); // NOI18N
        jButton13.setText("Thêm");
        jButton13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        txMaSPCTHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txMaSPCTHD.setEnabled(false);

        txDonGiaCTHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txDonGiaCTHD.setEnabled(false);

        txSoLuongCTHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txTongTienCTHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txTongTienCTHD.setEnabled(false);

        jButton14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-delete-24.png")); // NOI18N
        jButton14.setText("Xóa");
        jButton14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8_downloads_30px.png")); // NOI18N
        jButton15.setText("Cập nhât hóa đơn");
        jButton15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton3.setText("...");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txMaSPCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(jButton15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txDonGiaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txSoLuongCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txTongTienCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(485, 485, 485))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txMaSPCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDonGiaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSoLuongCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTongTienCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 303, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        jPanel53.setBackground(new java.awt.Color(110, 89, 222));

        jScrollPane14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane14MouseClicked(evt);
            }
        });

        tblDSCTHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Mã sản phẩm", "Đơn giá", "Số lượng", "Tổng tiền"
            }
        ));
        tblDSCTHD.setRowHeight(22);
        tblDSCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSCTHDMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblDSCTHD);

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelThongKe.setPreferredSize(new java.awt.Dimension(1529, 930));

        jPanel54.setBackground(new java.awt.Color(110, 89, 222));

        jPanel56.setBackground(new java.awt.Color(190, 220, 250));

        jLabel69.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Sản phẩm");

        lbThongKeSP.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeSP.setText("jLabel70");

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(lbThongKeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeSP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel57.setBackground(new java.awt.Color(152, 172, 248));

        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Nhân viên");

        lbThongKeNV.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeNV.setText("jLabel72");

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbThongKeNV, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel71)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeNV, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel58.setBackground(new java.awt.Color(176, 169, 249));

        jLabel73.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Doanh thu ");

        lbThongKeDoanhThu.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeDoanhThu.setText("jLabel74");

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73)
                    .addComponent(lbThongKeDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel59.setBackground(new java.awt.Color(119, 172, 241));

        jLabel75.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Tổng đơn hàng");

        lbThongKeTongDonHang.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeTongDonHang.setText("jLabel76");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbThongKeTongDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(25, 25, 25))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeTongDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel60.setBackground(new java.awt.Color(110, 89, 222));
        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N

        cbthangThongke.setEditable(true);
        cbthangThongke.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        cbthangThongke.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn tháng", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbthangThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbthangThongkeActionPerformed(evt);
            }
        });

        cbbNam.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cbbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015" }));
        cbbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbthangThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbthangThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout pnchartLayout = new javax.swing.GroupLayout(pnchart);
        pnchart.setLayout(pnchartLayout);
        pnchartLayout.setHorizontalGroup(
            pnchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnchartLayout.setVerticalGroup(
            pnchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnchart1Layout = new javax.swing.GroupLayout(pnchart1);
        pnchart1.setLayout(pnchart1Layout);
        pnchart1Layout.setHorizontalGroup(
            pnchart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );
        pnchart1Layout.setVerticalGroup(
            pnchart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnchart3.setBackground(new java.awt.Color(110, 89, 222));

        tblThongKeKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblThongKeKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {"Tổng cộng", null, null, null, null, null}
            },
            new String [] {
                "", "Qúy 1", "Qúy 2", "Qúy 3", "Qúy 4", "Tổng cộng"
            }
        ));
        tblThongKeKH.setRowHeight(25);
        jScrollPane18.setViewportView(tblThongKeKH);

        javax.swing.GroupLayout pnchart3Layout = new javax.swing.GroupLayout(pnchart3);
        pnchart3.setLayout(pnchart3Layout);
        pnchart3Layout.setHorizontalGroup(
            pnchart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnchart3Layout.setVerticalGroup(
            pnchart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnchart3Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel64.setBackground(new java.awt.Color(110, 89, 222));

        tblThongKeSPSapHet.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblThongKeSPSapHet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ));
        tblThongKeSPSapHet.setRowHeight(25);
        jScrollPane15.setViewportView(tblThongKeSPSapHet);
        if (tblThongKeSPSapHet.getColumnModel().getColumnCount() > 0) {
            tblThongKeSPSapHet.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblThongKeSPSapHet.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblThongKeSPSapHet.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Sản phẩm sắp hết");

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel55.setBackground(new java.awt.Color(102, 153, 255));

        jLabel68.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Tổng chi");

        lbThongKeTongChi.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeTongChi.setText("jLabel70");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbThongKeTongChi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addGap(34, 34, 34)
                .addComponent(lbThongKeTongChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel72.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setIcon(new javax.swing.ImageIcon("D:\\java\\MaketMini\\src\\framedesign\\icons8-statistics-49.png")); // NOI18N
        jLabel72.setText("THỐNG KÊ");

        tblThongKeTongThu.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblThongKeTongThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", "", null, null},
                {"Tổng thu", null, null, null, null}
            },
            new String [] {
                "", "Qúy 1", "Qúy 2", "Qúy 3", "Qúy 4"
            }
        ));
        tblThongKeTongThu.setRowHeight(25);
        jScrollPane19.setViewportView(tblThongKeTongThu);

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );

        tblThongKeTongChi.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblThongKeTongChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {"Tổng chi", null, null, null, null}
            },
            new String [] {
                "", "Qúy 1", "Qúy 2", "Qúy 3", "Qúy 4"
            }
        ));
        tblThongKeTongChi.setRowHeight(25);
        jScrollPane16.setViewportView(tblThongKeTongChi);

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblThongKeTopBanChay.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tblThongKeTopBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null},
                {"2", null, null},
                {"3", null, null},
                {"4", null, null},
                {"5", null, null}
            },
            new String [] {
                "Top", "Sản phẩm", "Đã bán"
            }
        ));
        tblThongKeTopBanChay.setRowHeight(25);
        jScrollPane17.setViewportView(tblThongKeTopBanChay);
        if (tblThongKeTopBanChay.getColumnModel().getColumnCount() > 0) {
            tblThongKeTopBanChay.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblThongKeTopBanChay.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblThongKeTopBanChay.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel63.setBackground(new java.awt.Color(204, 204, 255));

        jLabel70.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Khách hàng");

        lbThongKeKH.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        lbThongKeKH.setText("jLabel78");

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbThongKeKH, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbThongKeKH, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(486, 486, 486)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(pnchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnchart3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addComponent(pnchart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel72))
                    .addComponent(jLabel74))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnchart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnchart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnchart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );

        javax.swing.GroupLayout panelThongKeLayout = new javax.swing.GroupLayout(panelThongKe);
        panelThongKe.setLayout(panelThongKeLayout);
        panelThongKeLayout.setHorizontalGroup(
            panelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelThongKeLayout.setVerticalGroup(
            panelThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout PanelHienthiLayout = new javax.swing.GroupLayout(PanelHienthi);
        PanelHienthi.setLayout(PanelHienthiLayout);
        PanelHienthiLayout.setHorizontalGroup(
            PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 55, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 18, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 1221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 56, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 60, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 1541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 1387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 166, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 166, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 1541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 335, Short.MAX_VALUE)))
        );
        PanelHienthiLayout.setVerticalGroup(
            PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 985, Short.MAX_VALUE)
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 96, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 153, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelChinhLayout = new javax.swing.GroupLayout(panelChinh);
        panelChinh.setLayout(panelChinhLayout);
        panelChinhLayout.setHorizontalGroup(
            panelChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelHienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelChinhLayout.setVerticalGroup(
            panelChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChinhLayout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelHienthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDangXuatNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatNVMouseClicked
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbDangXuatNV.setBackground(clLeftItemSelected);
        try {
            // TODO add your handling code here:
            logOut();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbDangXuatNVMouseClicked

    private void lbDangXuatAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatAdminMouseClicked
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbDangXuatAdmin.setBackground(clLeftItemSelected);
        try {
            // TODO add your handling code here:
            logOut();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbDangXuatAdminMouseClicked

    private void lbBanHangAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbBanHangAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ BÁN HÀNG__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelBanHang);
        PanelHienthi.repaint();
        
    }//GEN-LAST:event_lbBanHangAdminMouseClicked

    private void lbNhapHangAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbNhapHangAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ PHIẾU NHẬP HÀNG__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelPhieuNhap);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbNhapHangAdminMouseClicked

    private void lbSanPhamAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbSanPhamAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ SẢN PHẨM__________________________________");
        
        
        panelChonSP.removeAll();
        panelChonSP.add(panelSP);
        panelChonSP.repaint();
        
        PanelHienthi.removeAll();
        PanelHienthi.add(panelSanPham);
        PanelHienthi.repaint();

    }//GEN-LAST:event_lbSanPhamAdminMouseClicked

    private void lbHoaDonAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbHoaDonAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ HÓA ĐƠN__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelHoaDon);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbHoaDonAdminMouseClicked

    private void lbNhaCungCapAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhaCungCapAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbNhaCungCapAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ NHÀ CUNG CẤP__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelNhaCungCap);
        PanelHienthi.repaint();

    }//GEN-LAST:event_lbNhaCungCapAdminMouseClicked

    private void lbKhuyenMaiAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhuyenMaiAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbKhuyenMaiAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ KHUYẾN MÃI__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelKhuyenMai);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbKhuyenMaiAdminMouseClicked

    private void lbNhanVienAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhanVienAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbNhanVienAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ NHÂN VIÊN__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelNhanVien);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbNhanVienAdminMouseClicked

    private void lbKhachHangAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbKhachHangAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ KHÁCH HÀNG__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelKhachHang);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbKhachHangAdminMouseClicked

    private void lbThongKeAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThongKeAdminMouseEntered
        // TODO add your handling code here:
        if (lbThongKeAdmin.getBackground().equals(clLeftItem)) {
            lbThongKeAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbThongKeAdminMouseEntered

    private void lbThongKeAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThongKeAdminMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftADMIN) {
            lblDisable.setBackground(clLeftItem);
        }
        lbThongKeAdmin.setBackground(clLeftItemSelected);
        
        lbHeader.setText("__________________________________QUẢN LÍ THỐNG KÊ__________________________________");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelThongKe);
        PanelHienthi.repaint();
    }//GEN-LAST:event_lbThongKeAdminMouseClicked

    private void lbBanHangNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangNVMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbBanHangNV.setBackground(clLeftItemSelected);
    }//GEN-LAST:event_lbBanHangNVMouseClicked

    private void lbNhapHangNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangNVMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbNhapHangNV.setBackground(clLeftItemSelected);
    }//GEN-LAST:event_lbNhapHangNVMouseClicked

    private void lbSanPhamNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamNVMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbSanPhamNV.setBackground(clLeftItemSelected);
    }//GEN-LAST:event_lbSanPhamNVMouseClicked

    private void lbHoaDonNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonNVMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbHoaDonNV.setBackground(clLeftItemSelected);
    }//GEN-LAST:event_lbHoaDonNVMouseClicked

    private void lbKhachHangNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangNVMouseClicked
        // TODO add your handling code here:
        for (JLabel lblDisable : listMenuLeftNV) {
            lblDisable.setBackground(clLeftItem);
        }
        lbKhachHangNV.setBackground(clLeftItemSelected);
    }//GEN-LAST:event_lbKhachHangNVMouseClicked

    private void lbBanHangNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangNVMouseEntered
        // TODO add your handling code here:
        if (lbBanHangNV.getBackground().equals(clLeftItem)) {
            lbBanHangNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbBanHangNVMouseEntered

    private void lbNhapHangNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangNVMouseEntered
        // TODO add your handling code here:
        if (lbNhapHangNV.getBackground().equals(clLeftItem)) {
            lbNhapHangNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbNhapHangNVMouseEntered

    private void lbSanPhamNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamNVMouseEntered
        // TODO add your handling code here:
        if (lbSanPhamNV.getBackground().equals(clLeftItem)) {
            lbSanPhamNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbSanPhamNVMouseEntered

    private void lbHoaDonNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonNVMouseEntered
        // TODO add your handling code here:
        if (lbHoaDonNV.getBackground().equals(clLeftItem)) {
            lbHoaDonNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbHoaDonNVMouseEntered

    private void lbKhachHangNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangNVMouseEntered
        // TODO add your handling code here:
        if (lbKhachHangNV.getBackground().equals(clLeftItem)) {
            lbKhachHangNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbKhachHangNVMouseEntered

    private void lbDangXuatNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatNVMouseEntered
        // TODO add your handling code here:
        if (lbDangXuatNV.getBackground().equals(clLeftItem)) {
            lbDangXuatNV.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbDangXuatNVMouseEntered

    private void lbBanHangNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangNVMouseExited
        // TODO add your handling code here:
        if (lbBanHangNV.getBackground().equals(clLeftItemHover)) {
            lbBanHangNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbBanHangNVMouseExited

    private void lbNhapHangNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangNVMouseExited
        // TODO add your handling code here:
        if (lbNhapHangNV.getBackground().equals(clLeftItemHover)) {
            lbNhapHangNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbNhapHangNVMouseExited

    private void lbSanPhamNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamNVMouseExited
        // TODO add your handling code here:
        if (lbSanPhamNV.getBackground().equals(clLeftItemHover)) {
            lbSanPhamNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbSanPhamNVMouseExited

    private void lbHoaDonNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonNVMouseExited
        // TODO add your handling code here:
        if (lbHoaDonNV.getBackground().equals(clLeftItemHover)) {
            lbHoaDonNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbHoaDonNVMouseExited

    private void lbKhachHangNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangNVMouseExited
        // TODO add your handling code here:
        if (lbKhachHangNV.getBackground().equals(clLeftItemHover)) {
            lbKhachHangNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbKhachHangNVMouseExited

    private void lbDangXuatNVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatNVMouseExited
        // TODO add your handling code here:
        if (lbDangXuatNV.getBackground().equals(clLeftItemHover)) {
            lbDangXuatNV.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbDangXuatNVMouseExited

    private void lbBanHangAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangAdminMouseExited
        // TODO add your handling code here:
        if (lbBanHangAdmin.getBackground().equals(clLeftItemHover)) {
            lbBanHangAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbBanHangAdminMouseExited

    private void lbNhapHangAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangAdminMouseExited
        // TODO add your handling code here:
        if (lbNhapHangAdmin.getBackground().equals(clLeftItemHover)) {
            lbNhapHangAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbNhapHangAdminMouseExited

    private void lbSanPhamAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamAdminMouseExited
        // TODO add your handling code here:
        if (lbSanPhamAdmin.getBackground().equals(clLeftItemHover)) {
            lbSanPhamAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbSanPhamAdminMouseExited

    private void lbHoaDonAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonAdminMouseExited
        // TODO add your handling code here:
        if (lbHoaDonAdmin.getBackground().equals(clLeftItemHover)) {
            lbHoaDonAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbHoaDonAdminMouseExited

    private void lbNhaCungCapAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhaCungCapAdminMouseExited
        // TODO add your handling code here:
        if (lbNhaCungCapAdmin.getBackground().equals(clLeftItemHover)) {
            lbNhaCungCapAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbNhaCungCapAdminMouseExited

    private void lbKhuyenMaiAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhuyenMaiAdminMouseExited
        // TODO add your handling code here:
        if (lbKhuyenMaiAdmin.getBackground().equals(clLeftItemHover)) {
            lbKhuyenMaiAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbKhuyenMaiAdminMouseExited

    private void lbNhanVienAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhanVienAdminMouseExited
        // TODO add your handling code here:
        if (lbNhanVienAdmin.getBackground().equals(clLeftItemHover)) {
            lbNhanVienAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbNhanVienAdminMouseExited

    private void lbKhachHangAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangAdminMouseExited
        // TODO add your handling code here:
        if (lbKhachHangAdmin.getBackground().equals(clLeftItemHover)) {
            lbKhachHangAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbKhachHangAdminMouseExited

    private void lbThongKeAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThongKeAdminMouseExited
        // TODO add your handling code here:
        if (lbThongKeAdmin.getBackground().equals(clLeftItemHover)) {
            lbThongKeAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbThongKeAdminMouseExited

    private void lbDangXuatAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatAdminMouseExited
        // TODO add your handling code here:
        if (lbDangXuatAdmin.getBackground().equals(clLeftItemHover)) {
            lbDangXuatAdmin.setBackground(clLeftItem);
        }
    }//GEN-LAST:event_lbDangXuatAdminMouseExited

    private void lbBanHangAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbBanHangAdminMouseEntered
        // TODO add your handling code here:
        if (lbBanHangAdmin.getBackground().equals(clLeftItem)) {
            lbBanHangAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbBanHangAdminMouseEntered

    private void lbNhapHangAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhapHangAdminMouseEntered
        // TODO add your handling code here:
        if (lbNhapHangAdmin.getBackground().equals(clLeftItem)) {
            lbNhapHangAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbNhapHangAdminMouseEntered

    private void lbSanPhamAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSanPhamAdminMouseEntered
        // TODO add your handling code here:
        if (lbSanPhamAdmin.getBackground().equals(clLeftItem)) {
            lbSanPhamAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbSanPhamAdminMouseEntered

    private void lbHoaDonAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHoaDonAdminMouseEntered
        // TODO add your handling code here:
        if (lbHoaDonAdmin.getBackground().equals(clLeftItem)) {
            lbHoaDonAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbHoaDonAdminMouseEntered

    private void lbNhaCungCapAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhaCungCapAdminMouseEntered
        // TODO add your handling code here:
        if (lbNhaCungCapAdmin.getBackground().equals(clLeftItem)) {
            lbNhaCungCapAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbNhaCungCapAdminMouseEntered

    private void lbKhuyenMaiAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhuyenMaiAdminMouseEntered
        // TODO add your handling code here:
        if (lbKhuyenMaiAdmin.getBackground().equals(clLeftItem)) {
            lbKhuyenMaiAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbKhuyenMaiAdminMouseEntered

    private void lbNhanVienAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNhanVienAdminMouseEntered
        // TODO add your handling code here:
        if (lbNhanVienAdmin.getBackground().equals(clLeftItem)) {
            lbNhanVienAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbNhanVienAdminMouseEntered

    private void lbKhachHangAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhachHangAdminMouseEntered
        // TODO add your handling code here:
        if (lbKhachHangAdmin.getBackground().equals(clLeftItem)) {
            lbKhachHangAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbKhachHangAdminMouseEntered

    private void lbDangXuatAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangXuatAdminMouseEntered
        // TODO add your handling code here:
        if (lbDangXuatAdmin.getBackground().equals(clLeftItem)) {
            lbDangXuatAdmin.setBackground(clLeftItemHover);
        }
    }//GEN-LAST:event_lbDangXuatAdminMouseEntered

    private void lbKhuyenMaiAdminMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKhuyenMaiAdminMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbKhuyenMaiAdminMousePressed

    private void txDiaChiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDiaChiNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDiaChiNVActionPerformed

    private void txTenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTenNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTenNCCActionPerformed

    private void txHokhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHokhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txHokhActionPerformed

    private void cbbTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTimKiemKHActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        try {
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setIdNV(ID.createNewStaff());
                    nv.setHo(txHoNV.getText());
                    nv.setTen(txTenNV.getText());
                    
                    nv.setNgaySinh(ngaySinhNV.getDate());
//                    sp.setNgaysinh(String.format("%02d",Integer.parseInt(txNgay.getText())) + "/"
//                        +String.format("%02d",Integer.parseInt(txThang.getText())) + "/"
//                        +String.format("%04d",Integer.parseInt(txNam.getText())));

                    
                    nv.setSdt(txSDTNV.getText());
                    nv.setDiachi(txDiaChiNV.getText());
                    
                    nv.setChucvu(cbbChucVuNV.getItemAt(cbbChucVuNV.getSelectedIndex()));
                    if(cbbTrangThaiNV.getItemAt(cbbTrangThaiNV.getSelectedIndex()).equals("Hiện hành")){
                        nv.setTrangthai(true);
                    }
                    else{
                        nv.setTrangthai(false);
                    }
                    if(!Check.checkStringNumber(String.valueOf(txLuongThangNV.getText()))){
                        JOptionPane.showMessageDialog(null, "Lương phải là số");
                    }
                    else if(!rdBtnNamNV.isSelected() && !rdBtnNuNV.isSelected()){
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn giới tính");
                    }
                    else{
                        if(rdBtnNamNV.isSelected()){
                        nv.setGioitinh("Nam");
                        }
                        else if(rdBtnNuNV.isSelected()){
                            nv.setGioitinh("Nữ");
                        }
                        nv.setLuongThang(Integer.parseInt(txLuongThangNV.getText()));
                        NhanVienBUS bus = new NhanVienBUS();
                        bus.themNV(nv);
                        //showDSNV(NhanVienBUS.listNV);
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");

                        txMaNV.setText(ID.createNewStaff());
                    }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công");
        }
        showDSNV(NhanVienBUS.listNV);
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        int i= tblDSNV.getSelectedRow();
                if(i>=0){
                    try {
                        NhanVienDTO nv = new NhanVienDTO();
                        nv.setIdNV(txMaNV.getText());
                        nv.setHo(txHoNV.getText());
                        nv.setTen(txTenNV.getText());
                        
                        
                        nv.setNgaySinh(ngaySinhNV.getDate());

                        if(rdBtnNamNV.isSelected()){
                            nv.setGioitinh("Nam");
                        }
                        else if(rdBtnNuNV.isSelected()){
                            nv.setGioitinh("Nữ");
                        }
                        nv.setSdt(txSDTNV.getText());
                        nv.setDiachi(txDiaChiNV.getText());
                        nv.setChucvu(cbbChucVuNV.getItemAt(cbbChucVuNV.getSelectedIndex()));
                        //nv.setLuongThang(Integer.parseInt(txLuong.getText()));
                        if(cbbTrangThaiNV.getItemAt(cbbTrangThaiNV.getSelectedIndex()).equals("Hiện hành")){
                            nv.setTrangthai(true);
                        }
                        else{
                            nv.setTrangthai(false);
                        }
                        if(!Check.checkStringNumber(String.valueOf(txLuongThangNV.getText()))){
                            JOptionPane.showMessageDialog(null, "Lương phải là số");
                        }
                        else{
                            nv.setLuongThang(Integer.parseInt(txLuongThangNV.getText()));
                            NhanVienBUS bus = new NhanVienBUS();
                            bus.suaNV(nv);
                            bus.docDSNV();
                            showDSNV(NhanVienBUS.listNV);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa nhân viên không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        // TODO add your handling code here:
        String ma = txMaNV.getText();
        int i= tblDSNV.getSelectedRow();
            try {
                if(i>=0){
                        NhanVienBUS bus = new NhanVienBUS();
                        bus.xoaNV(ma);
                        bus.docDSNV();
                        showDSNV(NhanVienBUS.listNV);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnLamMoiNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNVActionPerformed
        // TODO add your handling code here:
        txMaNV.setText(ID.createNewStaff());
        txHoNV.setText("");
        txTenNV.setText("");
        txSDTNV.setText("");
        rdBtnNamNV.setSelected(false);
        rdBtnNuNV.setSelected(false);
        txDiaChiNV.setText("");
        txLuongThangNV.setText("");
        ngaySinhNV.setDate(new Date());
    }//GEN-LAST:event_btnLamMoiNVActionPerformed

    private void btnTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNVActionPerformed
        // TODO add your handling code here:
        NhanVienBUS bus = new NhanVienBUS();
        ArrayList<NhanVienDTO> kq = new ArrayList<>();
        if(cbbTimKiemNV.getSelectedItem().equals("Mã")){
            NhanVienDTO nv = bus.timkiemMaNV(txTimKiemNV.getText());
            if(nv != null){
                kq.add(nv);
            }
        }
        else if(cbbTimKiemNV.getSelectedItem().equals("Họ")){
            kq = bus.timkiemHoNV(txTimKiemNV.getText());
        }
        else if(cbbTimKiemNV.getSelectedItem().equals("Tên")){
            kq = bus.timkiemTenNV(txTimKiemNV.getText());
        }
        else if(cbbTimKiemNV.getSelectedItem().equals("Giới tính")){
            kq = bus.timkiemGioiTinhNV(txTimKiemNV.getText());
        }
        else if(cbbTimKiemNV.getSelectedItem().equals("Chức vụ")){
            kq = bus.timkiemChucVuNV(txTimKiemNV.getText());
        }
        else if(cbbTimKiemNV.getSelectedItem().equals("Trạng thái")){
            if(bus.timkiemTrangThaiNV(txTimKiemNV.getText()) != null){
                kq = bus.timkiemTrangThaiNV(txTimKiemNV.getText());
            }       
        }
        if(txTimKiemNV.getText().equals("")){
            showDSNV(NhanVienBUS.listNV);
            JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + NhanVienBUS.listNV.size());
        }
        else{
            showDSNV(kq);
            JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
        }
    }//GEN-LAST:event_btnTimKiemNVActionPerformed

    private void tblDSNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNVMouseClicked
        // TODO add your handling code here:
        int i = tblDSNV.getSelectedRow();
        if(i>=0){
            txMaNV.setText(tblDSNV.getValueAt(i, 0) + "");
            txHoNV.setText(tblDSNV.getValueAt(i, 1) + "");
            txTenNV.setText(tblDSNV.getValueAt(i, 2) + "");
            if(tblDSNV.getValueAt(i, 3).equals("Nam")){
                rdBtnNamNV.setSelected(true);
            }
            else if(tblDSNV.getValueAt(i, 3).equals("Nữ")){
                rdBtnNuNV.setSelected(true);
            }
            
            String ngaySinh = tblDSNV.getValueAt(i, 4) + "";
            Date ngayS = new Date();
            try {
                ngayS = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
            } catch (ParseException ex) {
            }
            ngaySinhNV.setDate(ngayS);
            
            txSDTNV.setText(tblDSNV.getValueAt(i, 5) + "");
            txDiaChiNV.setText(tblDSNV.getValueAt(i, 6) + "");
            cbbChucVuNV.setSelectedItem(tblDSNV.getValueAt(i, 7) + "");
            String luong = tblDSNV.getValueAt(i, 8) + "";
            String luongThang = luong.replaceAll(",", "");
            txLuongThangNV.setText(luongThang);
            cbbTrangThaiNV.setSelectedItem(tblDSNV.getValueAt(i, 9)+"");
        }
    }//GEN-LAST:event_tblDSNVMouseClicked

    private void btnXoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNCCActionPerformed
        // TODO add your handling code here:
        String ma = txMaNCC.getText();
        int i= tblDSNCC.getSelectedRow();
                
            try {
                if(i>=0){
                        NhaCungCapBUS bus = new NhaCungCapBUS();
                        bus.xoaNCC(ma);
                        bus.docDSNCC();
                        showDSNCC(NhaCungCapBUS.listNCC);
                }
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnXoaNCCActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        // TODO add your handling code here:
        try {
                    NhaCungCapDTO ncc = new NhaCungCapDTO();
                    ncc.setIdNCC(ID.createNewNCC());
                    ncc.setTenNCC(txTenNCC.getText());
                    ncc.setDiachiNCC(txDiaChiNCC.getText());
                    ncc.setSdtNCC(txSDTNCC.getText());
                    
                    NhaCungCapBUS bus = new NhaCungCapBUS();
                    bus.themNCC(ncc);
                    JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công");

                    txMaNCC.setText(ID.createNewNCC());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp không thành công");
        }
        showDSNCC(NhaCungCapBUS.listNCC);
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        // TODO add your handling code here:
        int i= tblDSNCC.getSelectedRow();
                if(i>=0){
                    try {
                        NhaCungCapDTO ncc = new NhaCungCapDTO();
                        ncc.setIdNCC(txMaNCC.getText());
                        ncc.setTenNCC(txTenNCC.getText());
                        ncc.setDiachiNCC(txDiaChiNCC.getText());
                        ncc.setSdtNCC(txSDTNCC.getText());
                        
                        NhaCungCapBUS bus = new NhaCungCapBUS();
                        bus.suaNCC(ncc);
                        bus.docDSNCC();
                        showDSNCC(NhaCungCapBUS.listNCC);
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void btnLamMoiNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNCCActionPerformed
        // TODO add your handling code here:
        txMaNCC.setText(ID.createNewNCC());
        txTenNCC.setText("");
        txDiaChiNCC.setText("");
        txSDTNCC.setText("");
    }//GEN-LAST:event_btnLamMoiNCCActionPerformed

    private void btnTimKiemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNCCActionPerformed
        // TODO add your handling code here:
        NhaCungCapBUS bus = new NhaCungCapBUS();
        ArrayList<NhaCungCapDTO> kq = new ArrayList<>();
        if(cbbTimKiemNCC.getSelectedItem().equals("Mã nhà cung cấp")){
            NhaCungCapDTO ncc = bus.timkiemMaNCC(txTimKiemNCC.getText());
            if(ncc != null){
                kq.add(ncc);
            }
        }
        else if(cbbTimKiemNCC.getSelectedItem().equals("Tên nhà cung cấp")){
            kq = bus.timkiemTenNCC(txTimKiemNCC.getText());
        }
        if(txTimKiemNCC.getText().equals("")){
            showDSNCC(NhaCungCapBUS.listNCC);
            JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + NhaCungCapBUS.listNCC.size());
        }
        else{
            showDSNCC(kq);
            JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
        }
    }//GEN-LAST:event_btnTimKiemNCCActionPerformed

    private void tblDSNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNCCMouseClicked
        // TODO add your handling code here:
        
        int i = tblDSNCC.getSelectedRow();
                if(i>=0){
                    txMaNCC.setText(tblDSNCC.getValueAt(i, 0) + "");
                    txTenNCC.setText(tblDSNCC.getValueAt(i, 1) + "");
                    txDiaChiNCC.setText(tblDSNCC.getValueAt(i, 2) + "");
                    txSDTNCC.setText(tblDSNCC.getValueAt(i, 3) + "");
                }
    }//GEN-LAST:event_tblDSNCCMouseClicked

    private void txTenLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTenLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTenLoaiActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        
        lbHeader.setText("__________________________________QUẢN LÍ SẢN PHẨM__________________________________");
        panelChonSP.removeAll();
        panelChonSP.add(panelSP);
        panelChonSP.repaint();
        
        PanelHienthi.removeAll();
        PanelHienthi.add(panelSanPham);
        PanelHienthi.repaint();
        
        btnSanPham.setBackground(new Color(153,255,255));
        btnLoaiSP.setBackground(new Color(153,153,153));
        
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiSPActionPerformed
        // TODO add your handling code here:
        
        lbHeader.setText("__________________________________QUẢN LÍ LOẠI SẢN PHẨM__________________________________");
        panelChonSP.removeAll();
        panelChonSP.add(panelLoaiSP);
        panelChonSP.repaint();
        
        PanelHienthi.removeAll();
        PanelHienthi.add(panelSanPham);
        PanelHienthi.repaint();

        btnLoaiSP.setBackground(new Color(153,255,255));
        btnSanPham.setBackground(new Color(153,153,153));
        
    }//GEN-LAST:event_btnLoaiSPActionPerformed

    private void btThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemSPActionPerformed
        // TODO add your handling code here:
        try {
            SanPhamDTO sp = new SanPhamDTO(); 
            sp.setMasp(ID.createNewSP());
            if(cbbTrangThaiSP.getItemAt(cbbTrangThaiSP.getSelectedIndex()).equals("Đang bán")){
                sp.setTrangthai(true);
            }
            else{
                sp.setTrangthai(false);
            }
            sp.setMaloai(txLoaiSP.getText());
            sp.setDVT(txDonViTinhSP.getText());
            if(txTenSP.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
            }
            else if(!Check.checkStringNumber(String.valueOf(txDonGiaSP.getText()))){
                        JOptionPane.showMessageDialog(null, "Đơn giá phải là số");
            }
            else if(!Check.checkStringNumber(String.valueOf(txSoLuongSP.getText()))){
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số");
            }
            else{
                sp.setDongia(Integer.parseInt(txDonGiaSP.getText()));
                sp.setSoluong(Integer.parseInt(txSoLuongSP.getText())); 
                sp.setTensp(txTenSP.getText());
                SanPhamBUS bus = new  SanPhamBUS();
                bus.themSP(sp);

                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");

                txMaSP.setText(ID.createNewSP());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm không thành công");
        }
        showDSSP(SanPhamBUS.dssp);  //để đây thì jtable tự sắp xếp lại 
    }//GEN-LAST:event_btThemSPActionPerformed

    private void btSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaSPActionPerformed
        // TODO add your handling code here:
        int i= tbSanPham.getSelectedRow();
                if(i>=0){
                    try {
                        SanPhamDTO sp = new SanPhamDTO();
                        sp.setMasp(txMaSP.getText());
                        sp.setMaloai(txLoaiSP.getText());
                        sp.setDVT(txDonViTinhSP.getText()); 
                        if(cbbTrangThaiSP.getItemAt(cbbTrangThaiSP.getSelectedIndex()).equals("Đang bán")){
                            sp.setTrangthai(true);
                        }
                        else{
                            sp.setTrangthai(false);
                        }
                        if(txTenSP.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
                        }
                        else if(!Check.checkStringNumber(String.valueOf(txDonGiaSP.getText()))){
                            JOptionPane.showMessageDialog(null, "Đơn giá phải là số");
                        }
                        else if(!Check.checkStringNumber(String.valueOf(txSoLuongSP.getText()))){
                                    JOptionPane.showMessageDialog(null, "Số lượng phải là số");
                        }
                        else{
                            sp.setTensp(txTenSP.getText());
                            sp.setDongia(Integer.parseInt(txDonGiaSP.getText()));
                            sp.setSoluong(Integer.parseInt(txSoLuongSP.getText())); 
                            
                            SanPhamBUS bus = new SanPhamBUS();
                            bus.suaSP(sp);
                            bus.docDSSP();
                            showDSSP(SanPhamBUS.dssp);
                        }
                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Sửa sản phẩm không thành công");
                    } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btSuaSPActionPerformed

    private void btXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaSPActionPerformed
        // TODO add your handling code here:
        String ma = txMaSP.getText();
        int i= tbSanPham.getSelectedRow();
            try {
                if(i>=0){
                        SanPhamBUS bus = new SanPhamBUS();
                        bus.xoaSP(ma);
                        bus.docDSSP();
                        showDSSP(SanPhamBUS.dssp);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi xóa sản phẩm");
            }
    }//GEN-LAST:event_btXoaSPActionPerformed

    private void btLamMoiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoiSPActionPerformed
        // TODO add your handling code here:
        txMaSP.setText(ID.createNewSP());
        txTenSP.setText("");
        txDonGiaSP.setText("");
        txSoLuongSP.setText("");
        txDonViTinhSP.setText("");
    }//GEN-LAST:event_btLamMoiSPActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        int i = tbSanPham.getSelectedRow();
        if(i>=0){
            txMaSP.setText(tbSanPham.getValueAt(i, 0) + "");
            txTenSP.setText(tbSanPham.getValueAt(i, 1) + "");
            txLoaiSP.setText(tbSanPham.getValueAt(i, 2) + "");
            txDonGiaSP.setText(tbSanPham.getValueAt(i, 3) + "");
            txSoLuongSP.setText(tbSanPham.getValueAt(i, 4) + "");
            txDonViTinhSP.setText(tbSanPham.getValueAt(i, 5) + "");
            cbbTrangThaiSP.setSelectedItem(tbSanPham.getValueAt(i, 6));
        }
        
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemSPActionPerformed
        // TODO add your handling code here:
        SanPhamBUS bus=new SanPhamBUS();
            ArrayList<SanPhamDTO> kq=new ArrayList<>();
                if(cbbTimKiemSP.getSelectedItem().equals("Mã sản phẩm")){
                   SanPhamDTO sp=bus.timKiemMaSP(SanPhamBUS.dssp, txTimKiemSP.getText());
                    if(sp!=null){
                        kq.add(sp);
                    }
                }
                else if(cbbTimKiemSP.getSelectedItem().equals("Tên sản phẩm")){
                    kq=bus.timKiemTenSP(SanPhamBUS.dssp, txTimKiemSP.getText());
                }
                else if(cbbTimKiemSP.getSelectedItem().equals("Mã loại")){
                    kq=bus.timKiemMaLoaiSP(SanPhamBUS.dssp, txTimKiemSP.getText());
                }
                else if(cbbTimKiemSP.getSelectedItem().equals("Trạng thái")){
                    kq=bus.timKiemTrangthaiSP(txTimKiemSP.getText());
                }
                if(txTimKiemSP.getText().equals("")){
                    showDSSP(SanPhamBUS.dssp);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + SanPhamBUS.dssp.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "không có sản phẩm mà bạn cần tìm");
                }
                else{
                    showDSSP(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_btTimKiemSPActionPerformed

    private void btThemLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemLoaiSPActionPerformed
        // TODO add your handling code here:
        try {
            LoaiSPDTO loaisp = new LoaiSPDTO();
            loaisp.setMaloai(ID.createNewLoaiSP());
            if(txTenLoai.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Tên loại không được để trống");
            }
            else{
                loaisp.setTenloai(txTenLoai.getText());

                LoaiSPBUS bus = new LoaiSPBUS();
                bus.themLoaiSP(loaisp);

                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công");

                txMaLoai.setText(ID.createNewLoaiSP());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm không thành công");
        }
        showDSLoaiSP(LoaiSPBUS.dsLoaiSP);  //để đây thì jtable tự sắp xếp lại
    }//GEN-LAST:event_btThemLoaiSPActionPerformed

    private void btLamMoiLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoiLoaiSPActionPerformed
        // TODO add your handling code here:
        txMaLoai.setText(ID.createNewLoaiSP());
        txTenLoai.setText("");
    }//GEN-LAST:event_btLamMoiLoaiSPActionPerformed

    private void btSuaLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaLoaiSPActionPerformed
        // TODO add your handling code here:
        int i= tbLoaisp.getSelectedRow();
                if(i>=0){
                    try {
                        LoaiSPDTO loaisp = new LoaiSPDTO();
                        loaisp.setMaloai(txMaLoai.getText());
                        loaisp.setTenloai(txTenLoai.getText());
                        
                        LoaiSPBUS bus = new LoaiSPBUS();
                        bus.suaLoaiSP(loaisp);
                        bus.docDSLoaiSP();
                        showDSLoaiSP(LoaiSPBUS.dsLoaiSP);
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btSuaLoaiSPActionPerformed

    private void btXoaLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaLoaiSPActionPerformed
        // TODO add your handling code here:
        String ma = txMaLoai.getText();
        int i= tbLoaisp.getSelectedRow();
            try {
                if(i>=0){
                        LoaiSPBUS bus = new LoaiSPBUS();
                        bus.xoaLoaiSP(ma);
                        bus.docDSLoaiSP();
                        showDSLoaiSP(LoaiSPBUS.dsLoaiSP);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm không thành công");
        }
    }//GEN-LAST:event_btXoaLoaiSPActionPerformed

    private void btTimKiemLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemLoaiSPActionPerformed
        // TODO add your handling code here:
        LoaiSPBUS bus=new LoaiSPBUS();
            ArrayList<LoaiSPDTO> kq=new ArrayList<>();
                if(cbbTimKiemLoaiSP.getSelectedItem().equals("Mã loại")){
                   LoaiSPDTO loaisp=bus.timKiemMaLoai(txTimKiemLoaiSP.getText());
                    if(loaisp!=null){
                        kq.add(loaisp);
                    }
                }
                else if(cbbTimKiemLoaiSP.getSelectedItem().equals("Tên loại")){
                    kq=bus.timKiemTenLoai(txTimKiemLoaiSP.getText());
                }
                if(txTimKiemLoaiSP.getText().equals("")){
                    showDSLoaiSP(LoaiSPBUS.dsLoaiSP);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + LoaiSPBUS.dsLoaiSP.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "không có sản phẩm mà bạn cần tìm");
                }
                else{
                    showDSLoaiSP(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_btTimKiemLoaiSPActionPerformed

    private void tbLoaispMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLoaispMouseClicked
        // TODO add your handling code here:
        int i = tbLoaisp.getSelectedRow();
        if(i>=0){
            txMaLoai.setText(tbLoaisp.getValueAt(i, 0) + "");
            txTenLoai.setText(tbLoaisp.getValueAt(i, 1) + "");
        }
        
    }//GEN-LAST:event_tbLoaispMouseClicked

    private void btThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemKHActionPerformed
        // TODO add your handling code here:
        try {
            KhachHangDTO kh = new KhachHangDTO();
            kh.setMaKH(ID.createNewKH());
            kh.setDiachi(txDiaChiKH.getText());
            
            
            if(rdBtnNamKH.isSelected()){
                kh.setGioitinh("Nam");
            }
            else if(rdBtnNuKH.isSelected()){
                kh.setGioitinh("Nữ");
            }
            if(!rdBtnNamKH.isSelected() && !rdBtnNuKH.isSelected()){
                JOptionPane.showMessageDialog(null, "Hãy chọn giới tính");
            }
            else if(txHokh.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Họ không được để trống");
            }
            else if(txTenkh.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Tên không được để trống");
            }
            else if(!checkPhone(txSDTKH.getText())){
                JOptionPane.showMessageDialog(null, "Số điện thoại phải đủ 10 số");
            }
            else{
                kh.setHoKH(txHokh.getText());
                kh.setTenKH(txTenkh.getText());
                kh.setSdt(txSDTKH.getText()); 

                KhachHangBUS bus = new KhachHangBUS();
                bus.themKH(kh);

                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");

                txMakh.setText(ID.createNewKH());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công");
        }
        showDSKH(KhachHangBUS.dskh);
    }//GEN-LAST:event_btThemKHActionPerformed

    private void btSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaKHActionPerformed
        // TODO add your handling code here:
        int i= tblDSKH.getSelectedRow();
                if(i>=0){
                    try {
                        KhachHangDTO kh = new KhachHangDTO();
                        kh.setMaKH(txMakh.getText());
                        kh.setHoKH(txHokh.getText());
                        kh.setTenKH(txTenkh.getText());

                        if(rdBtnNamKH.isSelected()){
                            kh.setGioitinh("Nam");
                        }
                        else if(rdBtnNuKH.isSelected()){
                            kh.setGioitinh("Nữ");
                        }
                        kh.setSdt(txSDTKH.getText());
                        kh.setDiachi(txDiaChiKH.getText());
                        KhachHangBUS bus = new KhachHangBUS();
                        bus.suaKH(kh);
                        bus.docDSKH();
                        showDSKH(KhachHangBUS.dskh);
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa khách hàng không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btSuaKHActionPerformed

    private void btXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaKHActionPerformed
        // TODO add your handling code here:
        String ma = txMakh.getText();
        int i= tblDSKH.getSelectedRow();
            try {
                if(i>=0){
                        KhachHangBUS bus = new KhachHangBUS();
                        bus.xoaKH(ma);
                        bus.docDSKH();
                        showDSKH(KhachHangBUS.dskh);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa khách hàng không thành công");
            }
    }//GEN-LAST:event_btXoaKHActionPerformed

    private void btLammoiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLammoiKHActionPerformed
        // TODO add your handling code here:
        txMakh.setText(ID.createNewKH());
        txHokh.setText("");
        txTenkh.setText("");
        txDiaChiKH.setText("");
        txSDTKH.setText("");
        rdBtnNamKH.setSelected(false);
        rdBtnNuKH.setSelected(false);
    }//GEN-LAST:event_btLammoiKHActionPerformed

    private void tblDSKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSKHMouseClicked
        // TODO add your handling code here:
        int i = tblDSKH.getSelectedRow();
                if(i>=0){
                    txMakh.setText(tblDSKH.getValueAt(i, 0) + "");
                    txHokh.setText(tblDSKH.getValueAt(i, 1) + "");
                    txTenkh.setText(tblDSKH.getValueAt(i, 2) + "");
                    if(tblDSKH.getValueAt(i, 3).equals("Nam")){
                        rdBtnNamKH.setSelected(true);
                    }
                    else if(tblDSKH.getValueAt(i, 3).equals("Nữ")) {
                        rdBtnNuKH.setSelected(true);
                    }
                    txDiaChiKH.setText(tblDSKH.getValueAt(i, 4) + "");
                    txSDTKH.setText(tblDSKH.getValueAt(i, 5) + "");
        }
            
    }//GEN-LAST:event_tblDSKHMouseClicked

    private void btTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemKHActionPerformed
        // TODO add your handling code here:
        KhachHangBUS bus=new KhachHangBUS();
            ArrayList<KhachHangDTO> kq=new ArrayList<>();
                if(cbbTimKiemKH.getSelectedItem().equals("Mã KH")){
                    KhachHangDTO kh=bus.timKiemMaKH(txTimKiemKH.getText());
                    if(kh!=null){
                        kq.add(kh);
                    }
                }
                else if(cbbTimKiemKH.getSelectedItem().equals("Họ KH")){
                    kq=bus.timKiemHoKH(txTimKiemKH.getText());
                }
                else if(cbbTimKiemKH.getSelectedItem().equals("Tên KH")){
                    kq=bus.timKiemTenKH(txTimKiemKH.getText());
                }
                else if(cbbTimKiemKH.getSelectedItem().equals("Giới tính")){
                    kq=bus.timKiemGioiTinh(txTimKiemKH.getText());
                }
                if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "không có khách hàng mà bạn cần tìm");
                }
                if(txTimKiemKH.getText().equals("")){
                    showDSKH(KhachHangBUS.dskh);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + KhachHangBUS.dskh.size());
                }
                else{
                    showDSKH(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_btTimKiemKHActionPerformed

    private void btnXuatExcelNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelNVActionPerformed
        // TODO add your handling code here:
        exportExcel(tblDSNV);
    }//GEN-LAST:event_btnXuatExcelNVActionPerformed

    private void btnNhapExcelNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelNVActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        importExcel(tblDSNV);
        NhanVienBUS nvBus = new NhanVienBUS();
        int row = tblDSNV.getRowCount();
        System.out.println(row);
        for (int i = 0; i < row; i++) {
            NhanVienDTO nvDTO = new NhanVienDTO();
            nvDTO.setIdNV(tblDSNV.getValueAt(i, 0) + "");
            nvDTO.setHo(tblDSNV.getValueAt(i, 1) + "");
            nvDTO.setTen(tblDSNV.getValueAt(i, 2) + "");
            nvDTO.setGioitinh(tblDSNV.getValueAt(i, 3) + "");
            
            String ngaySinh = tblDSNV.getValueAt(i, 4) + "";
            Date ngayS = new Date();
            try {
                ngayS = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
            } catch (ParseException ex) {
            }
            nvDTO.setNgaySinh(ngayS);
            
            nvDTO.setSdt(tblDSNV.getValueAt(i, 5) + "");
            nvDTO.setDiachi(tblDSNV.getValueAt(i, 6) + "");
            nvDTO.setChucvu(tblDSNV.getValueAt(i, 7) + "");
            
            String luong = tblDSNV.getValueAt(i, 8) + "";
            String luongThang = luong.replaceAll(",", "");
            nvDTO.setLuongThang(Integer.parseInt(luongThang));
            if(tblDSNV.getValueAt(i, 9).toString().equals("Hiện hành"))
                nvDTO.setTrangthai(true);
            else{
                nvDTO.setTrangthai(false);
            }
            try {
                nvBus.importExcelNV(nvDTO);
//            } catch (HeadlessException ex) {
//                //JOptionPane.showMessageDialog(null,"Nhập Excel thất bại");
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //TH trong bảng có nhiều nhân viên hơn file excel
        for(NhanVienDTO nv : NhanVienBUS.listNV){
            int t=0;
            for (int i = 0; i < row; i++) {
                if(nv.getIdNV().equals(tblDSNV.getValueAt(i, 0) + "")){
                    t=1;
                }
            }
            if(t==0){
                try {
                    new NhanVienBUS().xoaNV(nv.getIdNV());
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            NhanVienBUS.listNV = new NhanVienBUS().getList();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNhapExcelNVActionPerformed

    private void btnXuatExcelNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelNCCActionPerformed
        // TODO add your handling code here:
        exportExcel(tblDSNCC);
    }//GEN-LAST:event_btnXuatExcelNCCActionPerformed

    private void btnNhapExcelNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelNCCActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return;
        }

        importExcel(tblDSNCC);
        NhaCungCapBUS nccBus = new NhaCungCapBUS();
        int row = tblDSNCC.getRowCount();
        System.out.println(row);
        for (int i = 0; i < row; i++) {
            NhaCungCapDTO nccDTO = new NhaCungCapDTO();
            nccDTO.setIdNCC(tblDSNCC.getValueAt(i, 0) + "");
            nccDTO.setTenNCC(tblDSNCC.getValueAt(i, 1) + "");
            nccDTO.setDiachiNCC(tblDSNCC.getValueAt(i, 2) + "");
            nccDTO.setSdtNCC(tblDSNCC.getValueAt(i, 3) + "");
            
            try {
                nccBus.importExcelNCC(nccDTO);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Nhập Excel thất bại");
            }

        }
        
        //TH trong bảng có nhiều ncc hơn file excel
        for(NhaCungCapDTO ncc : NhaCungCapBUS.listNCC){
            int t=0;
            for (int i = 0; i < row; i++) {
                if(ncc.getIdNCC().equals(tblDSNCC.getValueAt(i, 0) + "")){
                    t=1;
                }
            }
            if(t==0){
                try {
                    new NhaCungCapBUS().xoaNCC(ncc.getIdNCC());
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            NhaCungCapBUS.listNCC = new NhaCungCapBUS().getList();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNhapExcelNCCActionPerformed

    private void btnBoLocPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocPNActionPerformed
        // TODO add your handling code here:
        ngayBatDauPN.setDate(new Date());
        ngayKetThucPN.setDate(new Date());
        showDSPN(PhieuNhapBUS.listPN);
    }//GEN-LAST:event_btnBoLocPNActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        exportExcel(tbSanPham );
    }//GEN-LAST:event_jButton17ActionPerformed

    private void btnDlgChonLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDlgChonLoaiSPActionPerformed
        // TODO add your handling code here:
//        int row = tblKQTKLoaiSP.getSelectedRow();
//        if (row < 0) {
//            JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần chọn");
//            return;
//        }
//        String maLoai = tblKQTKLoaiSP.getValueAt(row, 0) + "";
//        String tenLoai = tblKQTKLoaiSP.getValueAt(row, 1) + "";
//        
//        loaiSPChon = new LoaiSPDTO(maLoai, tenLoai);
//        
//        dlgChonLoaiSP.dispose();
    }//GEN-LAST:event_btnDlgChonLoaiSPActionPerformed

    private void btnDlgSuaLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDlgSuaLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDlgSuaLoaiSPActionPerformed

    private void btnChonLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonLoaiSPActionPerformed
        // TODO add your handling code here:
        DlgChonLoaiSP dlg = new DlgChonLoaiSP();
        dlg.setVisible(true);

        if (DlgChonLoaiSP.maLoaiSP != null) {
            txLoaiSP.setText(DlgChonLoaiSP.maLoaiSP);
        } else {
            txLoaiSP.setText("");
        }
    }//GEN-LAST:event_btnChonLoaiSPActionPerformed

    private void cbbTimLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimLoaiSPActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbbTimLoaiSPActionPerformed

    private void btnChonNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNCCActionPerformed
        // TODO add your handling code here:
        DlgChonNhaCungCap dlg = new DlgChonNhaCungCap();
        dlg.setVisible(true);

//        if (dlg.getLoaiSP() != null) {
//            txLoaiSP.setText(dlg.getLoaiSP().getMaloai());
//        } else {
//            txLoaiSP.setText("");
//        }
        if (DlgChonNhaCungCap.maNCC != null) {
            txMaNCCPN.setText(DlgChonNhaCungCap.maNCC);
        } else {
            txLoaiSP.setText("");
        }
    }//GEN-LAST:event_btnChonNCCActionPerformed

    private void btnChonSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonSPActionPerformed
        // TODO add your handling code here:
        DlgChonSanPham dlg = new DlgChonSanPham();
        dlg.setVisible(true);

        if (DlgChonSanPham.maSP != null) {
            txMaSPCTPN.setText(DlgChonSanPham.maSP);
        } else {
            txMaSPCTPN.setText("");
        }
        
        for(SanPhamDTO sp : SanPhamBUS.dssp){
            if(sp.getMasp().equals(DlgChonSanPham.maSP)){
                txDonGiaCTPN.setText(sp.getDongia() + "");
            }
        }
       
    }//GEN-LAST:event_btnChonSPActionPerformed

    private void btnTaoMoiPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiPNActionPerformed
        // TODO add your handling code here:
        txMaPhieuNhap.setText(ID.createNewPhieuNhap());
        idPN = ID.createNewPhieuNhap();
        txTenNCC.setText("");
        ngayNhap.setDate(new Date());
        txMaNVPN.setText(dangnhap.idHienHanh);
    }//GEN-LAST:event_btnTaoMoiPNActionPerformed

    private void btnSuaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPNActionPerformed
        // TODO add your handling code here:
        int i= tblDSPN.getSelectedRow();
                if(i>=0){
                    try {
                        PhieuNhapDTO pn = new PhieuNhapDTO();
                        pn.setIdPN(idPN);
                        pn.setNgayNhap(ngayNhap.getDate());

                        pn.setIdNCC(txMaNCCPN.getText());
                        pn.setIdNhanVien(txMaNCCPN.getText());
                        
                        PhieuNhapBUS bus = new PhieuNhapBUS();
                        bus.suaPN(pn); //sửa sql
                        bus.docDSPN(); //cập nhật lại arraylist từ sql
                        showDSPN(PhieuNhapBUS.listPN); //show lại arraylist sau khi cập nhật
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa phiếu nhập không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaPNActionPerformed

    private void btnThemCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPNActionPerformed
        // TODO add your handling code here:
        try {
                    
                    ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
                    ctpn.setIdPhieuNhap(idPN);
                    ctpn.setIdSanPham(txMaSPCTPN.getText());
                    ctpn.setSoluong(Integer.parseInt(txSoLuongCTPN.getText()));
                    ctpn.setDongia(Integer.parseInt(txDonGiaCTPN.getText()));
                    
                    ctpn.setTongtiennhap(ctpn.getSoluong()*ctpn.getDongia());
                    
                    //phiếu nhập thiếu tổng số tiền
                        ChiTietPhieuNhapBUS bus = new ChiTietPhieuNhapBUS();
                        bus.themCTPN(ctpn);
                        JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập thành công");

                        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết phiếu nhập không thành công");
        }
        showDSCTPN(ChiTietPhieuNhapBUS.listCTPN);
    }//GEN-LAST:event_btnThemCTPNActionPerformed

    private void btnSuaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTPNActionPerformed
        // TODO add your handling code here:
        DecimalFormat dcf = new DecimalFormat("###,###");
        int i= tblDSCTPN.getSelectedRow();
                if(i>=0){
                    try {
                        if(txMaSPCTPN.getText().equals(tblDSCTPN.getValueAt(i, 1))){  //KTra không sửa mã sản phẩm
                            ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO();
                            ctpn.setIdPhieuNhap(txMaPhieuNhap.getText());
                            ctpn.setIdSanPham(txMaSPCTPN.getText());
                            ctpn.setSoluong(Integer.parseInt(txSoLuongCTPN.getText()));
                            ctpn.setDongia(Integer.parseInt(txDonGiaCTPN.getText()));
                            ctpn.setTongtiennhap(ctpn.getSoluong()*ctpn.getDongia());
                            
                            ChiTietPhieuNhapBUS bus = new ChiTietPhieuNhapBUS();
                            if(bus.suaCTPN(ctpn)){
                                boolean findPN = false;
                                //TH: Sửa chi tiết sp mà đã có trên phiếu nhập (tính lại tổng tiền, số lượng)
                                for(PhieuNhapDTO pn : PhieuNhapBUS.listPN){
                                    if(pn.getIdPN().equals(txMaPhieuNhap.getText())){ 
                                        findPN = true;
                                        
                                        //set lại số lượng trong bảng sản phẩm
                                        int soluongspo = 0;
                                        SanPhamDTO sp = new SanPhamDTO();
                                        int Soluongspnhap = Integer.valueOf(tblDSCTPN.getValueAt(i, 2).toString()); //lấy số lượng bảng
                                        for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                            if (tbSanPham.getValueAt(z, 0).equals(tblDSCTPN.getValueAt(i, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                            }
                                        }
                                        int soluongchenhlech = ctpn.getSoluong() - Soluongspnhap; //ra âm nếu số lượng chưa sửa lớn hơn số lượng đã sửa
                                        int soluongmoi = soluongspo + soluongchenhlech;
                                        sp.setSoluong(soluongmoi);
                                        SanPhamBUS spbus = new SanPhamBUS();
                                        if (spbus.UpdateSoluongSP(sp)) {
                                            spbus.docDSSP();
                                            showDSSP(SanPhamBUS.dssp);
                                            
                                            ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                            listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                            showBH(listSPDangBan);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                        }
                                                
                                        bus.docDSCTPN();
                                        showDSCTPN(ChiTietPhieuNhapBUS.listCTPN);
                                        //tính lại tổng tiền cho sp
                                        int tongtien=0;
                                        for (ChiTietPhieuNhapDTO ct : ChiTietPhieuNhapBUS.listCTPN){
                                            if(ct.getIdPhieuNhap().equals(txMaPhieuNhap.getText())){
                                                tongtien+=ct.getTongtiennhap();
                                            }
                                        }
                                        pn.setTongsotien(tongtien);
                                        PhieuNhapBUS pnbus = new PhieuNhapBUS();
                                        if(pnbus.UpdateTongSoTienPN(pn)){
                                            pnbus.docDSPN();
                                            showDSPN(PhieuNhapBUS.listPN);
                                        }
                                        else{
                                           JOptionPane.showMessageDialog(null, "Cập nhật tổng số tiền phiếu nhập thất bại");
                                        }
                                    }
                                }
                                //TH: Nếu sửa ct chưa nhập hàng (chưa có mã phiếu nhập của ct trên phiếu nhập)
                                if(!findPN){
                                    bus.docDSCTPN();
                                    showDSCTPN(ChiTietPhieuNhapBUS.listCTPN);
                                }
                                
                            }else{
                                JOptionPane.showMessageDialog(null, "Sửa chi tiết phiếu nhập thất bại");
                            }   
                        }else{
                            JOptionPane.showMessageDialog(null, "Không thể sửa mã sản phẩm");
                        }
                        
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa chi tiết phiếu nhập không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaCTPNActionPerformed

    private void btnXoaCTPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTPNActionPerformed
        // TODO add your handling code here:
        String idPN = txMaPhieuNhap.getText();
        String idSP = txMaSPCTPN.getText();
        int i= tblDSCTPN.getSelectedRow();
                
            try {
                if(i>=0){
                        ChiTietPhieuNhapBUS bus = new ChiTietPhieuNhapBUS();
                        if(bus.xoaCTPN(idPN, idSP)){  //xóa sql
                            //TH: Xóa chi tiết sp mà đã có trên phiếu nhập (trừ tổng tiền, trừ số lượng)
                            for(PhieuNhapDTO pn : PhieuNhapBUS.listPN){
                                if(pn.getIdPN().equals(idPN)){ 
                                    //set lại tổng số tiền phiếu nhập
                                    int tongsotienPN = Integer.parseInt(String.valueOf(pn.getTongsotien()).replaceAll(",", ""));
                                    int tongsotienmoi = tongsotienPN - Integer.parseInt(txTongTienNhapCTPN.getText());
                                    pn.setTongsotien(tongsotienmoi);
                                    PhieuNhapBUS pnbus = new PhieuNhapBUS();
                                    if(pnbus.UpdateTongSoTienPN(pn)){
                                        pnbus.docDSPN();
                                        showDSPN(PhieuNhapBUS.listPN);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Cập nhật tổng số tiền phiếu nhập thất bại");
                                    }
                                    
                                    //set lại số lượng trong bảng sản phẩm
                                    int soluongspo = 0;
                                    
                                            SanPhamDTO sp = new SanPhamDTO();
                                            int Soluongspnhap = Integer.valueOf(tblDSCTPN.getValueAt(i, 2).toString()); //lấy số lượng nhập
                                            for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                                if (tbSanPham.getValueAt(z, 0).equals(tblDSCTPN.getValueAt(i, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                    sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                    soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                                }
                                            }
                                            int soluongmoi = soluongspo - Soluongspnhap;
                                            sp.setSoluong(soluongmoi);
                                            SanPhamBUS spbus = new SanPhamBUS();
                                            if (spbus.UpdateSoluongSP(sp)) {
                                                spbus.docDSSP();
                                                showDSSP(SanPhamBUS.dssp);
                                                
                                                ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                                listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                                showBH(listSPDangBan);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                            }
                                }
                            }
                            
                            bus.docDSCTPN();
                            showDSCTPN(ChiTietPhieuNhapBUS.listCTPN);
                            
                            
                            
                            
                            //TH khi xóa hết all chi tiết thì xóa luôn phiếu nhập 
                            boolean findCTPN = false;
                            for(ChiTietPhieuNhapDTO ctpn : ChiTietPhieuNhapBUS.listCTPN){
                                if(ctpn.getIdPhieuNhap().equals(idPN)){
                                    findCTPN = true;
                                }
                            }
                            if(!findCTPN){
                                PhieuNhapBUS pnbus = new PhieuNhapBUS();
                                if(pnbus.xoaPN(idPN)){
                                    pnbus.docDSPN();
                                    showDSPN(PhieuNhapBUS.listPN);
                                    
                                    int soluongspo = 0;
                                    for(int u = 0; u < tblDSCTPN.getRowCount(); u++){
                                        if(tblDSCTPN.getValueAt(u, 0).equals(idPN)){
                                            SanPhamDTO sp = new SanPhamDTO();
                                            int Soluongspnhap = Integer.valueOf(tblDSCTPN.getValueAt(u, 2).toString()); //lấy số lượng nhập
                                            for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                                if (tbSanPham.getValueAt(z, 0).equals(tblDSCTPN.getValueAt(u, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                    sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                    soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                                }
                                            }
                                            int soluongmoi = soluongspo - Soluongspnhap;
                                            sp.setSoluong(soluongmoi);
                                            SanPhamBUS spbus = new SanPhamBUS();
                                            if (spbus.UpdateSoluongSP(sp)) {
                                                spbus.docDSSP();
                                                showDSSP(SanPhamBUS.dssp);
                                                
                                                ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                                listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                                showBH(listSPDangBan);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                            }
                                        }
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thất bại");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Xóa chi tiết phiếu nhập thất bại");
                        }
                        
                        
                        
                        
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết phiếu nhập không thành công");
            }
    }//GEN-LAST:event_btnXoaCTPNActionPerformed

    private void btnXuatExcelKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelKHActionPerformed
        // TODO add your handling code here:
        exportExcel(tblDSKH);
    }//GEN-LAST:event_btnXuatExcelKHActionPerformed

    private void btnCapNhatPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatPNActionPerformed
        // TODO add your handling code here:
        DecimalFormat dcf = new DecimalFormat("###,###");
        try {
                    int tongTienNhap=0;
                    PhieuNhapDTO pn = new PhieuNhapDTO();
                    pn.setIdPN(idPN);
                    pn.setNgayNhap(ngayNhap.getDate());
                    
                    pn.setIdNCC(txMaNCCPN.getText());
                    pn.setIdNhanVien(txMaNVPN.getText());
                    
                    for(ChiTietPhieuNhapDTO ctpn : ChiTietPhieuNhapBUS.listCTPN){
                        if(ctpn.getIdPhieuNhap().equals(idPN)){
                            tongTienNhap+=ctpn.getTongtiennhap();
                        }
                    }
                    
                    pn.setTongsotien(tongTienNhap);
                    
                    tongSoTien+=tongTienNhap;
                    //lbTongSoTien.setText("Tổng số tiền: " + dcf.format(tongSoTien));
                    
                    PhieuNhapBUS bus = new PhieuNhapBUS();
                    if(bus.themPN(pn)){
                        JOptionPane.showMessageDialog(null, "Cập nhật phiếu nhập thành công");
                        int soluongspo = 0;
                        for(int u = 0; u < tblDSCTPN.getRowCount(); u++){
                            if(tblDSCTPN.getValueAt(u, 0).equals(idPN)){
                                SanPhamDTO sp = new SanPhamDTO();
                                int Soluongspnhap = Integer.valueOf(tblDSCTPN.getValueAt(u, 2).toString()); //lấy số lượng nhập
                                for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                    if (tbSanPham.getValueAt(z, 0).equals(tblDSCTPN.getValueAt(u, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                        sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                        soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu
                                    }
                                }
                                int soluongmoi = soluongspo + Soluongspnhap;
                                sp.setSoluong(soluongmoi);
                                SanPhamBUS spbus = new SanPhamBUS();
                                if (spbus.UpdateSoluongSP(sp)) {
                                    spbus.docDSSP();
                                    showDSSP(SanPhamBUS.dssp);
                                    
                                    ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                    listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                    showBH(listSPDangBan);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                }
                            } 
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thất bại");
                    }
                        
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cật nhật phiếu nhập không thành công");
        }
        showDSPN(PhieuNhapBUS.listPN);
    }//GEN-LAST:event_btnCapNhatPNActionPerformed

    private void tblDSPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSPNMouseClicked
        // TODO add your handling code here:
        int i = tblDSPN.getSelectedRow();
                if(i>=0){
                    txMaPhieuNhap.setText(tblDSPN.getValueAt(i, 0) + "");
                    txMaNCCPN.setText(tblDSPN.getValueAt(i, 1) + "");
                    txMaNVPN.setText(tblDSPN.getValueAt(i, 2) + "");
                    
                    String ngay = tblDSNV.getValueAt(i, 3) + "";
                    Date ngayN = new Date();
                    try {
                        ngayN = new SimpleDateFormat("dd/MM/yyyy").parse(ngay);
                    } catch (ParseException ex) {
                    }
                    ngayNhap.setDate(ngayN);
                    
                    ArrayList<ChiTietPhieuNhapDTO> kqCTPN = new ArrayList<>();
                    for (ChiTietPhieuNhapDTO ctpn : ChiTietPhieuNhapBUS.listCTPN){
                        if(ctpn.getIdPhieuNhap().equals(tblDSPN.getValueAt(i, 0))){
                            kqCTPN.add(ctpn);
                        }
                    }
                    showDSCTPN(kqCTPN);
                }
    }//GEN-LAST:event_tblDSPNMouseClicked

    private void tblDSCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSCTPNMouseClicked
        // TODO add your handling code here:
        int i = tblDSCTPN.getSelectedRow();
                if(i>=0){
                    txMaPhieuNhap.setText(tblDSCTPN.getValueAt(i, 0) + "");
                    for (PhieuNhapDTO pn : PhieuNhapBUS.listPN){
                        if(pn.getIdPN().equals(txMaPhieuNhap.getText())){
                            txMaNCCPN.setText(pn.getIdNCC());
                            txMaNVPN.setText(pn.getIdNhanVien());
                            ngayNhap.setDate(pn.getNgayNhap());
                        }
                    }
                    
                    txMaSPCTPN.setText(tblDSCTPN.getValueAt(i, 1) + "");
                    txSoLuongCTPN.setText(tblDSCTPN.getValueAt(i, 2) + "");
                    
                    String gia = tblDSCTPN.getValueAt(i, 3) + "";
                    String dongia = gia.replaceAll(",", "");
                    txDonGiaCTPN.setText(dongia);
                    
                    String tien = tblDSCTPN.getValueAt(i, 4) + "";
                    String tongtien = tien.replaceAll(",", "");
                    txTongTienNhapCTPN.setText(tongtien);
                }
    }//GEN-LAST:event_tblDSCTPNMouseClicked

    private void btnLocPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocPNActionPerformed
        // TODO add your handling code here:
        if (ngayBatDauPN.getDate() == null || ngayKetThucPN.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn khoảng ngày");
        } else {
            Date dayK = ngayKetThucPN.getDate(); //ketthuc
            Date dayB = ngayBatDauPN.getDate(); //batdau

            ArrayList<PhieuNhapDTO> kq = new ArrayList<>();
            for (PhieuNhapDTO pn : PhieuNhapBUS.listPN) {
                if (pn.getNgayNhap().compareTo(dayB) >= 0 && dayK.compareTo(pn.getNgayNhap()) >= 0) {
                    kq.add(pn);
                }
            }
            showDSPN(kq);
        }
    }//GEN-LAST:event_btnLocPNActionPerformed

    private void cbbTimKiemPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemPNActionPerformed
        // TODO add your handling code here:
        PhieuNhapBUS bus=new PhieuNhapBUS();
            ArrayList<PhieuNhapDTO> kq=new ArrayList<>();
                if(cbbTimKiemPN.getSelectedItem().equals("Mã phiếu nhập")){
                   PhieuNhapDTO pn=bus.timkiemMaPN(txTimKiemPN.getText());
                    if(pn!=null){
                        kq.add(pn);
                    }
                }
                else if(cbbTimKiemPN.getSelectedItem().equals("Mã nhà cung cấp")){
                    kq=bus.timkiemMaNCCPN(txTimKiemPN.getText());
                }
                else if(cbbTimKiemPN.getSelectedItem().equals("Mã nhân viên")){
                    kq=bus.timkiemMaNVPN(txTimKiemPN.getText());
                }
                if(cbbTimKiemPN.getSelectedItem().equals("Tất cả")){
                    showDSPN(PhieuNhapBUS.listPN);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + PhieuNhapBUS.listPN.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "Không có phiếu nhập mà bạn cần tìm");
                }
                else{
                    showDSPN(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_cbbTimKiemPNActionPerformed

    private void btnInPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPNActionPerformed
        // TODO add your handling code here:
        int i = tblDSPN.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn phiếu nhập muốn in!!");
        } else {
            String maPNH = tblDSPN.getValueAt(i, 0).toString();
            try {
                ReportPDFNH pdf = new ReportPDFNH(maPNH);
                JOptionPane.showMessageDialog(null, "Đã in thành công!");
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
            
        }
    }//GEN-LAST:event_btnInPNActionPerformed

    private void txSoLuongCTPNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txSoLuongCTPNKeyPressed
        // TODO add your handling code here:
        int dongia = Integer.parseInt(txDonGiaCTPN.getText());
        int soluong = Integer.parseInt(txSoLuongCTPN.getText());
        txTongTienNhapCTPN.setText(String.valueOf(dongia*soluong));
    }//GEN-LAST:event_txSoLuongCTPNKeyPressed

    private void txTongTienBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTongTienBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTongTienBHActionPerformed

    private void txMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMaKMActionPerformed

    private void txTenKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTenKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTenKMActionPerformed

    private void btnBoLocKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocKMActionPerformed
        // TODO add your handling code here:
        NgayKetThucKM.setDate(new Date());
        NgayBatDauKM.setDate(new Date());
        showDSKM(KhuyenMaiBUS.listKM);
    }//GEN-LAST:event_btnBoLocKMActionPerformed

    private void btnTaoMoiKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiKMActionPerformed
        // TODO add your handling code here:
        txMaKM.setText(ID.createNewKhuyenMai());
        idKM = ID.createNewKhuyenMai();
        txTenKM.setText("");
        txDieuKienKM.setText("");
        NgayKetThucKM.setDate(new Date());
        NgayBatDauKM.setDate(new Date());
    }//GEN-LAST:event_btnTaoMoiKMActionPerformed

    private void btnThemCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTKMActionPerformed
        // TODO add your handling code here:
        try {
                if(!Check.checkStringNumber(String.valueOf(txPhanTramCTKM.getText()))){
                        JOptionPane.showMessageDialog(null, "Phần trăm khuyến mãi phải là số");
                }
                else{
                    ChiTietKhuyenMaiDTO ctkm = new ChiTietKhuyenMaiDTO();
                    ctkm.setMaKM(idKM);
                    ctkm.setMaSP(txMaSPCTKM.getText());
                    ctkm.setPhantramKM(Integer.parseInt(txPhanTramCTKM.getText()));
                    
                    ChiTietKhuyenMaiBUS bus = new ChiTietKhuyenMaiBUS();
                    if(bus.themCTKM(ctkm)){
                        JOptionPane.showMessageDialog(null, "Thêm chi tiết khuyến mãi thành công");
                    }
                }
      
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm chi tiết khuyến mãi không thành công");
        }
        showDSCTKM(ChiTietKhuyenMaiBUS.listCTKM);
    }//GEN-LAST:event_btnThemCTKMActionPerformed

    private void btnSuaCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTKMActionPerformed
        // TODO add your handling code here:
        int i= tblDSCTKM.getSelectedRow();
                if(i>=0){
                    try {
                        if(txMaSPCTKM.getText().equals(tblDSCTKM.getValueAt(i, 1))){  //KTra không sửa mã sản phẩm
                            ChiTietKhuyenMaiDTO ctkm = new ChiTietKhuyenMaiDTO();
                            ctkm.setMaKM(txMaKM.getText());
                            ctkm.setMaSP(txMaSPCTKM.getText());
                            ctkm.setPhantramKM(Integer.parseInt(txPhanTramCTKM.getText()));
                            ChiTietKhuyenMaiBUS bus = new ChiTietKhuyenMaiBUS();
                            if(bus.suaCTKM(ctkm)){
                                bus.docDSCTKM();
                                showDSCTKM(ChiTietKhuyenMaiBUS.listCTKM);
                            }else{
                                JOptionPane.showMessageDialog(null, "Sửa chi tiết khuyến mãi thất bại");
                            }   
                        }else{
                            JOptionPane.showMessageDialog(null, "Không thể sửa mã sản phẩm");
                        }
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa chi tiết khuyến mãi không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaCTKMActionPerformed

    private void btnXoaCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTKMActionPerformed
        // TODO add your handling code here:
        String idKM = txMaKM.getText();
        String idSP = txMaSPCTKM.getText();
        int i= tblDSCTKM.getSelectedRow();
            try {
                if(i>=0){
                        ChiTietKhuyenMaiBUS bus = new ChiTietKhuyenMaiBUS();
                        if(bus.xoaCTKM(idKM, idSP)){  //xóa sql
                            bus.docDSCTKM();
                            showDSCTKM(ChiTietKhuyenMaiBUS.listCTKM);
                            
                            //TH khi xóa hết all chi tiết ct thì xóa luôn khuyến mãi
                            boolean findCTKM = false;
                            for(ChiTietKhuyenMaiDTO ctkm : ChiTietKhuyenMaiBUS.listCTKM){
                                if(ctkm.getMaKM().equals(idKM)){
                                    findCTKM = true;
                                }
                            }
                            if(!findCTKM){
                                KhuyenMaiBUS kmbus = new KhuyenMaiBUS();
                                if(kmbus.xoaKM(idKM)){
                                    kmbus.docDSKM();
                                    showDSKM(KhuyenMaiBUS.listKM);
                                }else{
                                    JOptionPane.showMessageDialog(null, "Xóa khuyến mãi thất bại");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi thất bại");
                        }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi không thành công");
            }
    }//GEN-LAST:event_btnXoaCTKMActionPerformed

    private void btnCapNhatKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKMActionPerformed
        // TODO add your handling code here:
        try {
                    KhuyenMaiDTO km = new KhuyenMaiDTO();
                    km.setMaKM(idKM);
                    km.setTenKM(txTenKM.getText());
                    km.setNgaybdKM(NgayBatDauKM.getDate());
                    km.setNgayktKM(NgayKetThucKM.getDate());
                    if(!Check.checkStringNumber(String.valueOf(txDieuKienKM.getText()))){
                        JOptionPane.showMessageDialog(null, "Điều kiện khuyến mãi phải là số");
                    }
                    else if(Check.isNull(txDieuKienKM.getText())){
                        JOptionPane.showMessageDialog(null, "Điều kiện khuyến mãi không thể rỗng");
                    }
                    else{
                        km.setDieukienKM(Integer.parseInt(txDieuKienKM.getText()));
                    
                        KhuyenMaiBUS bus = new KhuyenMaiBUS();
                        if(bus.themKM(km)){
                            JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thành công");
                        }else {
                            JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thất bại");
                        }
                    }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cật nhật khuyến mãi không thành công");
        }
        showDSKM(KhuyenMaiBUS.listKM);
    }//GEN-LAST:event_btnCapNhatKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed
        // TODO add your handling code here:
        int i= tblDSKM.getSelectedRow();
                if(i>=0){
                    try {
                        KhuyenMaiDTO km = new KhuyenMaiDTO();
                        km.setMaKM(txMaKM.getText());
                        km.setTenKM(txTenKM.getText());
                        km.setNgaybdKM(NgayBatDauKM.getDate());
                        km.setNgayktKM(NgayKetThucKM.getDate());
                        km.setDieukienKM(Integer.parseInt(txDieuKienKM.getText()));
                        
                        KhuyenMaiBUS bus = new KhuyenMaiBUS();
                        bus.suaKM(km); 
                        bus.docDSKM();
                        showDSKM(KhuyenMaiBUS.listKM); 
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa khuyến mãi không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void btnXoaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKMActionPerformed
        // TODO add your handling code here:
        String idKM = txMaKM.getText();
        int i= tblDSKM.getSelectedRow();
                
            try {
                if(i>=0){
                    KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
                    if(kmBUS.xoaKM(idKM)){
                        //xóa all chi tiết ct
                        boolean result = false;
                        ChiTietKhuyenMaiBUS ctkmBUS = new ChiTietKhuyenMaiBUS();
                        for(ChiTietKhuyenMaiDTO ctkm : ChiTietKhuyenMaiBUS.listCTKM){
                            if(ctkm.getMaKM().equals(idKM)){
                                result = ctkmBUS.xoaCTKM(idKM, ctkm.getMaSP());  
                            }
                        }
                        if(result){
                            ctkmBUS.docDSCTKM();
                            showDSCTKM(ChiTietKhuyenMaiBUS.listCTKM);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi thất bại");
                        }
                        
                        kmBUS.docDSKM();
                        showDSKM(KhuyenMaiBUS.listKM);
                    
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Xóa khuyến mãi thất bại");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết khuyến mãi không thành công");
            }
    }//GEN-LAST:event_btnXoaKMActionPerformed

    private void tblDSCTKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSCTKMMouseClicked
        // TODO add your handling code here:
        int i = tblDSCTKM.getSelectedRow();
                if(i>=0){
                    txMaKM.setText(tblDSCTKM.getValueAt(i, 0) + "");
                    for (KhuyenMaiDTO km : KhuyenMaiBUS.listKM){
                        if(km.getMaKM().equals(txMaKM.getText())){
                            txTenKM.setText(km.getTenKM());
                            NgayBatDauKM.setDate(km.getNgaybdKM());
                            NgayKetThucKM.setDate(km.getNgayktKM());
                            txDieuKienKM.setText(String.valueOf(km.getDieukienKM()));
                        }
                    }
                    
                    txMaSPCTKM.setText(tblDSCTKM.getValueAt(i, 1) + "");
                    txPhanTramCTKM.setText(tblDSCTKM.getValueAt(i, 2) + "");
                }
    }//GEN-LAST:event_tblDSCTKMMouseClicked

    private void jScrollPane6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane6MouseClicked

    private void tblDSKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSKMMouseClicked
        // TODO add your handling code here:
        int i = tblDSKM.getSelectedRow();
                if(i>=0){
                    txMaKM.setText(tblDSKM.getValueAt(i, 0) + "");
                    txTenKM.setText(tblDSKM.getValueAt(i, 1) + "");
                    
                    String ngayB = tblDSKM.getValueAt(i, 2) + "";
                    Date ngayBD = new Date();
                    try {
                        ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(ngayB);
                    } catch (ParseException ex) {
                    }
                    NgayBatDauKM.setDate(ngayBD);
                    
                    String ngayK = tblDSKM.getValueAt(i, 3) + "";
                    Date ngayKT = new Date();
                    try {
                        ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(ngayK);
                    } catch (ParseException ex) {
                    }
                    NgayKetThucKM.setDate(ngayKT);
                    
                    txDieuKienKM.setText(tblDSKM.getValueAt(i, 4) + "");
                    
                    //show chi tiết ct
                    ArrayList<ChiTietKhuyenMaiDTO> kqCTKM = new ArrayList<>();
                    for (ChiTietKhuyenMaiDTO ctkm : ChiTietKhuyenMaiBUS.listCTKM){
                        if(ctkm.getMaKM().equals(tblDSKM.getValueAt(i, 0))){
                            kqCTKM.add(ctkm);
                        }
                    }
                    showDSCTKM(kqCTKM);
                } 
    }//GEN-LAST:event_tblDSKMMouseClicked

    private void cbbTimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemKMActionPerformed
        // TODO add your handling code here:
        KhuyenMaiBUS bus=new KhuyenMaiBUS();
            ArrayList<KhuyenMaiDTO> kq=new ArrayList<>();
                if(cbbTimKiemKM.getSelectedItem().equals("Mã khuyến mãi")){
                   KhuyenMaiDTO km=bus.timkiemMaKM(txTimKiemKM.getText());
                    if(km!=null){
                        kq.add(km);
                    }
                }
                else if(cbbTimKiemKM.getSelectedItem().equals("Tên khuyến mãi")){
                    kq=bus.timkiemTenKM(txTimKiemKM.getText());
                }
                else if(cbbTimKiemKM.getSelectedItem().equals("Điều kiện")){
                    kq=bus.timkiemDieuKienKM(Integer.parseInt(txTimKiemKM.getText()));
                }
                if(cbbTimKiemKM.getSelectedItem().equals("Tất cả")){
                    showDSKM(KhuyenMaiBUS.listKM);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + KhuyenMaiBUS.listKM.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "Không có khuyến mãi mà bạn cần tìm");
                }
                else{
                    showDSKM(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
   
    }//GEN-LAST:event_cbbTimKiemKMActionPerformed

    private void btnLocKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocKMActionPerformed
        // TODO add your handling code here:
        if (tuNgayKM.getDate() == null || denNgayKM.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn khoảng ngày");
        } else {
            Date dayK = denNgayKM.getDate(); //ket thuc
            Date dayB = tuNgayKM.getDate(); //bat dau

            ArrayList<KhuyenMaiDTO> kq = new ArrayList<>();
            for (KhuyenMaiDTO km : KhuyenMaiBUS.listKM) {
                if (km.getNgaybdKM().compareTo(dayB) >= 0 && dayK.compareTo(km.getNgaybdKM()) >= 0) {
                    if(km.getNgayktKM().compareTo(dayB) >= 0 && dayK.compareTo(km.getNgayktKM()) >= 0){
                        kq.add(km);
                    }
                }
            }
            showDSKM(kq);
        }
    }//GEN-LAST:event_btnLocKMActionPerformed

    private void btnChonSanPhamCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonSanPhamCTKMActionPerformed
        // TODO add your handling code here:
        DlgChonSanPham dlg = new DlgChonSanPham();
        dlg.setVisible(true);

        if (DlgChonSanPham.maSP != null) {
            txMaSPCTKM.setText(DlgChonSanPham.maSP);
        } else {
            txMaSPCTKM.setText("");
        }
        
    }//GEN-LAST:event_btnChonSanPhamCTKMActionPerformed

    private void cbbTimKiemSPBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemSPBHActionPerformed
        // TODO add your handling code here:
        SanPhamBUS bus=new SanPhamBUS();
        ArrayList<SanPhamDTO> listSPConHang=new ArrayList<>();
        listSPConHang = bus.timKiemTrangthaiSP("Đang bán");
        
            ArrayList<SanPhamDTO> kq=new ArrayList<>();
                if(cbbTimKiemSPBH.getSelectedItem().equals("Mã sản phẩm")){
                   SanPhamDTO sp=bus.timKiemMaSP(listSPConHang, txTimKiemSPBH.getText());
                    if(sp!=null){
                        kq.add(sp);
                    }
                }
                else if(cbbTimKiemSPBH.getSelectedItem().equals("Tên sản phẩm")){
                    kq=bus.timKiemTenSP(listSPConHang, txTimKiemSPBH.getText());
                }
                if(cbbTimKiemSPBH.getSelectedItem().equals("Tất cả")){
                    showBH(listSPConHang);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + listSPConHang.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "Không có sản phẩm mà bạn cần tìm");
                }
                else{
                    showBH(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_cbbTimKiemSPBHActionPerformed

    private void cbbChonLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChonLoaiSPActionPerformed
        // TODO add your handling code here:
        LoaiSPBUS loaispBUS=new LoaiSPBUS();
        SanPhamBUS spBUS=new SanPhamBUS();
            ArrayList<SanPhamDTO> kqSanPham=new ArrayList<>();
            ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
            listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                if(cbbChonLoaiSP.getSelectedIndex()>0){
                    String tenLoai = cbbChonLoaiSP.getSelectedItem()+"";
                    for(LoaiSPDTO loaisp : LoaiSPBUS.dsLoaiSP){
                        if(loaisp.getTenloai().toLowerCase().equals(tenLoai.toLowerCase())){
                            kqSanPham = spBUS.timKiemMaLoaiSP(listSPDangBan, loaisp.getMaloai());
                        }
                    }
                    //kqLoaiSP=loaispBUS.timKiemTenLoai(tenLoai);
                }
                if(kqSanPham.size()==0){
                    JOptionPane.showMessageDialog(null, "không có sản phẩm mà bạn cần tìm");
                }
                else{
                    showBH(kqSanPham);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kqSanPham.size());
                }
    }//GEN-LAST:event_cbbChonLoaiSPActionPerformed

    private void cbbTimKiemLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemLoaiSPActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbbTimKiemLoaiSPActionPerformed

    private void btnThanhToanBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanBHActionPerformed
        // TODO add your handling code here:
        try {
            HoaDonBUS bus = new HoaDonBUS();
            HoaDonDTO hd = new HoaDonDTO();
            hd.setIdHoadon(txMaHDBH.getText());
            hd.setIdNhanvien(txMaNVBH.getText());
            hd.setIdKhachhang(txMaKHBH.getText());
            hd.setIdKhuyenmai(txMaKMBH.getText());
            hd.setNgaylapHoadon(ngayLapBH.getDate());
            int tongtien=0;
            for(ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD){
                if(ct.getMaHD().equals(txMaHDBH.getText())){
                    tongtien += ct.getTongtien();
                }
            }
            hd.setTongtienHoadon(tongtien);

            if(bus.themHD(hd)){
                showDSHD(HoaDonBUS.listHD);
                //set lại số lượng sản phẩm sau khi thanh toán
                int soluongspo = 0;
                        for(int u = 0; u < tblDSCTHD.getRowCount(); u++){
                            if(tblDSCTHD.getValueAt(u, 0).equals(txMaHDBH.getText())){
                                SanPhamDTO sp = new SanPhamDTO();
                                int SoluongspBH = Integer.valueOf(tblDSCTHD.getValueAt(u, 3).toString()); //lấy số lượng bán hàng
                                for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                    if (tbSanPham.getValueAt(z, 0).equals(tblDSCTHD.getValueAt(u, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                        sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                        soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu
                                    }
                                }
                                int soluongmoi = soluongspo - SoluongspBH;
                                sp.setSoluong(soluongmoi);
                                SanPhamBUS spbus = new SanPhamBUS();
                                if (spbus.UpdateSoluongSP(sp)) {
                                    spbus.docDSSP();
                                    showDSSP(SanPhamBUS.dssp);
                                    
                                    ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                    listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                    showBH(listSPDangBan);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                }
                            } 
                        }
                        
                int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Thanh toán thành công, bạn có muốn IN HÓA ĐƠN?", "Thành công",
                JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    new ReportPDF(txMaHDBH.getText());
                    JOptionPane.showMessageDialog(this, "In thành công");
                }
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                clear();
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại");
            }
        } catch (Exception e) {
        }
            
        
        
    }//GEN-LAST:event_btnThanhToanBHActionPerformed

    public void clear() {
        modelGioHang.setRowCount(0);
        tblDSGioHang.setModel(modelGioHang);
        txMaHDBH.setText(ID.createNewHoaDon());
        txMaNVBH.setText(dangnhap.idHienHanh);
        txMaKHBH.setText("");
        txMaKMBH.setText("");
        ngayLapBH.setDate(new Date());
        txTongTienBH.setText("0VND");
        tongtienBanHang=0;
        
        txMaSPBH.setText("");
        txTenSPBH.setText("");
        txDonGiaBH.setText("");
        txSoLuongBH.setText("");
    }
    
    private void btnThemGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGioHangActionPerformed
        // TODO add your handling code here:
        int i = tblBH.getSelectedRow();
        if(i>=0){
            ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
            ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
            cthd.setMaHD(txMaHDBH.getText());
            cthd.setMaSP(txMaSPBH.getText());
            cthd.setDongia(Integer.parseInt(txDonGiaBH.getText()));
            
            if(Check.isNull(txSoLuongBH.getText())){
                JOptionPane.showMessageDialog(null, "Số lượng không thể rỗng");
            }
            else if(Integer.parseInt(txSoLuongBH.getText())>0){
                cthd.setSoluong(Integer.parseInt(txSoLuongBH.getText()));
                cthd.setTongtien(cthd.getDongia()*cthd.getSoluong());
                
                Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa (TH khách mua cùng loại sản phẩm 2 lần)
                for (ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD) {
                    if (ct.getMaHD().equals(txMaHDBH.getText()) && ct.getMaSP().equals(txMaSPBH.getText())) {
                        daCo = true;
                        int tongSoLuong = cthd.getSoluong() + ct.getSoluong();
                        for(SanPhamDTO sp : SanPhamBUS.dssp){
                            if(sp.getMasp().equals(ct.getMaSP())){ //tìm sản phẩm để kiểm tra số lượng hiện có
                                if (tongSoLuong > sp.getSoluong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluong() + ")");
                                }else{
                                    try {
                                        ct.setSoluong(tongSoLuong); // có rồi thì thay đổi số lượng
                                        ct.setTongtien(ct.getDongia()*ct.getSoluong()); //set lại tổng tiền
                                        bus.suaCTHD(ct);
                                        bus.docDSCTHD();
                                        showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                        
                                        tongtienBanHang+=cthd.getTongtien();
                                        txTongTienBH.setText(String.valueOf(tongtienBanHang));
                                    } catch (Exception ex) {
                                        
                                    }
                                }
                            }
                        }
                                                                                                                                                        
                    }
                }

                if (!daCo) { // nếu chưa có thì thêm mới
                    for(SanPhamDTO sp : SanPhamBUS.dssp){
                            if(sp.getMasp().equals(cthd.getMaSP())){ //tìm sản phẩm để kiểm tra số lượng hiện có
                                if (cthd.getSoluong() > sp.getSoluong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluong() + ")");
                                }else{
                                    try {
                                        bus.themCTHD(cthd);
                                        showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                        
                                        tongtienBanHang+=cthd.getTongtien();
                                        txTongTienBH.setText(String.valueOf(tongtienBanHang));
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thất bại");
                                    }
                                }
                            }
                    }                    
                }
            }else {
                JOptionPane.showMessageDialog(null, "Số lượng không được âm");
            }
        }
        showGioHang(getListGioHang(txMaHDBH.getText()));
    }//GEN-LAST:event_btnThemGioHangActionPerformed

    private void btnHuyBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBHActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn?", "Cảnh báo",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            try {
                ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
                for (ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
                    if(cthd.getMaHD().equals(txMaHDBH.getText())){
                        if(bus.xoaCTHD(cthd.getMaHD(), cthd.getMaSP())){
                            bus.docDSCTHD();
                            showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thất bại");
                        }
                    }
                }
            } catch (Exception e) {
            }
            tongtienBanHang=0;
            modelGioHang.setRowCount(0);
            tblDSGioHang.setModel(modelGioHang);
            txTongTienBH.setText("0VND");
            txMaHDBH.setText(ID.createNewHoaDon());
            txMaKHBH.setText("");
            txMaKMBH.setText("");
            
            txMaSPBH.setText("");
            txTenSPBH.setText("");
            txDonGiaBH.setText("");
            txSoLuongBH.setText("");
        } else {
            // no option
        }
    }//GEN-LAST:event_btnHuyBHActionPerformed

    private void btnXoaSPBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPBHActionPerformed
        // TODO add your handling code here:
        int i = tblDSGioHang.getSelectedRow();
        if(i>=0){
            if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn?", "Cảnh báo",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                try {
                    ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
                    for (ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
                        if(cthd.getMaHD().equals(txMaHDBH.getText()) && cthd.getMaSP().equals(tblDSGioHang.getValueAt(i, 0))){
                            if(bus.xoaCTHD(cthd.getMaHD(), cthd.getMaSP())){
                                bus.docDSCTHD();
                                showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                
                                tongtienBanHang-=cthd.getTongtien();
                                txTongTienBH.setText(String.valueOf(tongtienBanHang));
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thất bại");
                            }
                        }
                    }
                } catch (Exception e) {
                }
                showGioHang(getListGioHang(txMaHDBH.getText()));
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa");
        }
    }//GEN-LAST:event_btnXoaSPBHActionPerformed

    private void btnChonMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonMaKHActionPerformed
        // TODO add your handling code here:
        DlgChonKhachHang dlg = new DlgChonKhachHang();
        dlg.setVisible(true);

        if (DlgChonKhachHang.maKH != null) {
            txMaKHBH.setText(DlgChonKhachHang.maKH);
        } else {
            txMaKHBH.setText("");
        }
    }//GEN-LAST:event_btnChonMaKHActionPerformed

    private void btnChonMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonMaKMActionPerformed
        // TODO add your handling code here:
        ArrayList<SanPhamDTO> ds = new ArrayList<>();  //lấy các mã sản phẩm trong giỏ hàng
        for(int i = 0 ; i<tblDSGioHang.getRowCount();i++){
            SanPhamDTO sp = new SanPhamDTO(); 
            sp.setMasp(tblDSGioHang.getValueAt(i, 0) + ""); 
            ds.add(sp);
        }
        System.out.println(tongtienBanHang);
        ArrayList<ChiTietKhuyenMaiDTO> dsctkm = xulyKhuyenMai(ngayLapBH.getDate(), tongtienBanHang, ds); //lấy dsctkm thỏa 
        if(dsctkm.size()!=0){
            maKM = dsctkm.get(0).getMaKM();//chỉ lấy mã để hiện mã khuyến mãi thỏa lên dlg
        }
        else{
            maKM="";
        }
        
        DlgChonKhuyenMai dlg = new DlgChonKhuyenMai();
        dlg.setVisible(true);

        if (DlgChonKhuyenMai.maKM != null) {
            txMaKMBH.setText(DlgChonKhuyenMai.maKM);
        } else {
            txMaKMBH.setText("");
        }
        
        if(!txMaKMBH.getText().equals("")){
            for(ChiTietKhuyenMaiDTO ctkm : dsctkm){
                int tienkm=0;
                for (int i = 0 ; i<tblDSGioHang.getRowCount();i++){
                    if(tblDSGioHang.getValueAt(i, 0).toString().equals(ctkm.getMaSP())){
                        int tongtienSP = Integer.parseInt(tblDSGioHang.getValueAt(i, 4).toString().replaceAll(",", ""));
                        tongtienBanHang-=tongtienSP;
                        tienkm = tongtienSP - (tongtienSP * ctkm.getPhantramKM()) / 100;
                        tongtienBanHang += tienkm;
                    }
                }
            }
            

            txTongTienBH.setText(String.valueOf(tongtienBanHang));
        }
        
//                int tienkm = tongtienBanHang - (tongtienBanHang * DlgChonKhuyenMai.phantramKM) / 100;
//                tongtienBanHang = tienkm;
//                txTongTienBH.setText(String.valueOf(tongtienBanHang));
    }//GEN-LAST:event_btnChonMaKMActionPerformed

    private void cbbTimKiemHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemHDActionPerformed
        // TODO add your handling code here:
        HoaDonBUS bus=new HoaDonBUS();
            ArrayList<HoaDonDTO> kq=new ArrayList<>();
                if(cbbTimKiemHD.getSelectedItem().equals("Mã hóa đơn")){
                   HoaDonDTO hd=bus.timkiemMaHD(txTimKiemHD.getText());
                    if(hd!=null){
                        kq.add(hd);
                    }
                }
                else if(cbbTimKiemHD.getSelectedItem().equals("Mã nhân viên")){
                    kq=bus.timkiemMaNVHD(txTimKiemHD.getText());
                }
                else if(cbbTimKiemHD.getSelectedItem().equals("Mã khách hàng")){
                    kq=bus.timkiemMaKHHD(txTimKiemHD.getText());
                }
                else if(cbbTimKiemHD.getSelectedItem().equals("Mã khuyến mãi")){
                    kq=bus.timkiemMaKMHD(txTimKiemHD.getText());
                }
                if(cbbTimKiemHD.getSelectedItem().equals("Tất cả")){
                    showDSHD(HoaDonBUS.listHD);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + HoaDonBUS.listHD.size());
                }
                else if(kq.size()==0){
                    JOptionPane.showMessageDialog(null, "Không có hóa đơn mà bạn cần tìm");
                }
                else{
                    showDSHD(kq);
                    JOptionPane.showMessageDialog(null, "Kết quả tìm được: " + kq.size());
                }
    }//GEN-LAST:event_cbbTimKiemHDActionPerformed

    private void btnLocHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocHDActionPerformed
        // TODO add your handling code here:
        if (ngayTuHD.getDate() == null || ngayDenHD.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn khoảng ngày");
        } else {
            Date dayK = ngayTuHD.getDate(); //ketthuc
            Date dayB = ngayDenHD.getDate(); //batdau

            ArrayList<HoaDonDTO> kq = new ArrayList<>();
            for (HoaDonDTO pn : HoaDonBUS.listHD) {
                if (pn.getNgaylapHoadon().compareTo(dayB) >= 0 && dayK.compareTo(pn.getNgaylapHoadon()) >= 0) {
                    kq.add(pn);
                }
            }
            showDSHD(kq);
        }
    }//GEN-LAST:event_btnLocHDActionPerformed

    private void btnBoLocHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoLocHDActionPerformed
        // TODO add your handling code here:
        ngayTuHD.setDate(new Date());
        ngayDenHD.setDate(new Date());
        showDSHD(HoaDonBUS.listHD);
    }//GEN-LAST:event_btnBoLocHDActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        txMaHD.setText(ID.createNewHoaDon());
        txMaNVHD.setText(dangnhap.idHienHanh);
        txMaKHHD.setText("");
        txMaKMHD.setText("");
        ngayLapHD.setDate(new Date());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int i = tblDSCTHD.getSelectedRow();
        if(i>=0){
            ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
            ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
            cthd.setMaHD(txMaHD.getText());
            cthd.setMaSP(txMaSPCTHD.getText());
            cthd.setDongia(Integer.parseInt(txDonGiaCTHD.getText()));
            cthd.setSoluong(Integer.parseInt(txSoLuongCTHD.getText()));
            cthd.setTongtien(cthd.getDongia()*cthd.getSoluong());
            if(cthd.getSoluong()>0){
                Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa (TH thêm cùng loại sản phẩm 2 lần)
                for (ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD) {
                    if (ct.getMaHD().equals(txMaHD.getText()) && ct.getMaSP().equals(txMaSPCTHD.getText())) {
                        daCo = true;
                        int tongSoLuong = cthd.getSoluong() + ct.getSoluong();
                        for(SanPhamDTO sp : SanPhamBUS.dssp){
                            if(sp.getMasp().equals(ct.getMaSP())){ //tìm sản phẩm để kiểm tra số lượng hiện có
                                if (tongSoLuong > sp.getSoluong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluong() + ")");
                                }else{
                                    try {
                                        ct.setSoluong(tongSoLuong); // có rồi thì thay đổi số lượng
                                        bus.suaCTHD(ct);
                                        bus.docDSCTHD();
                                        showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                    } catch (Exception ex) {
                                        
                                    }
                                }
                            }
                        }
                                                                                                                                                        
                    }
                }

                if (!daCo) { // nếu chưa có thì thêm mới
                    for(SanPhamDTO sp : SanPhamBUS.dssp){
                            if(sp.getMasp().equals(cthd.getMaSP())){ //tìm sản phẩm để kiểm tra số lượng hiện có
                                if (cthd.getSoluong() > sp.getSoluong()) {
                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluong() + ")");
                                }else{
                                    try {
                                        bus.themCTHD(cthd);
                                        showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thất bại");
                                    }
                                }
                            }
                    }                    
                }
            }else {
                JOptionPane.showMessageDialog(null, "Số lượng không được âm");
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DecimalFormat dcf = new DecimalFormat("###,###");
        int i= tblDSCTHD.getSelectedRow();
                if(i>=0){
                    try {
                        if(txMaSPCTHD.getText().equals(tblDSCTHD.getValueAt(i, 1))){  //KTra không sửa mã sản phẩm
                            ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
                            cthd.setMaHD(txMaHD.getText());
                            cthd.setMaSP(txMaSPCTHD.getText());
                            cthd.setSoluong(Integer.parseInt(txSoLuongCTHD.getText()));
                            cthd.setDongia(Integer.parseInt(txDonGiaCTHD.getText()));
                            cthd.setTongtien(cthd.getSoluong()*cthd.getDongia());
                            
                            ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
                            if(bus.suaCTHD(cthd)){
                                boolean findHD = false;
                                //TH: Sửa chi tiết sp mà đã có trên hóa đơn (tính lại tổng tiền, số lượng)
                                for(HoaDonDTO hd : HoaDonBUS.listHD){
                                    if(hd.getIdHoadon().equals(txMaHD.getText())){ 
                                        findHD = true;
                                        
                                        //set lại số lượng trong bảng sản phẩm
                                        int soluongspo = 0;
                                        SanPhamDTO sp = new SanPhamDTO();
                                        int Soluongspnhap = Integer.valueOf(tblDSCTHD.getValueAt(i, 3).toString()); //lấy số lượng bảng
                                        for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                            if (tbSanPham.getValueAt(z, 0).equals(tblDSCTHD.getValueAt(i, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                            }
                                        }
                                        int soluongchenhlech = cthd.getSoluong() - Soluongspnhap; //ra âm nếu số lượng chưa sửa lớn hơn số lượng đã sửa
                                        int soluongmoi = soluongspo - soluongchenhlech;
                                        sp.setSoluong(soluongmoi);
                                        SanPhamBUS spbus = new SanPhamBUS();
                                        if (spbus.UpdateSoluongSP(sp)) {
                                            spbus.docDSSP();
                                            showDSSP(SanPhamBUS.dssp);
                                            
                                            ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                            listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                            showBH(listSPDangBan);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                        }
                                                
                                        bus.docDSCTHD();
                                        showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                        //tính lại tổng tiền cho sp
                                        int tongtienSP=0;
                                        for (ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD){
                                            if(ct.getMaHD().equals(txMaHD.getText())){
                                                tongtienSP+=ct.getTongtien();
                                            }
                                        }
                                        ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
                                        ArrayList<ChiTietHoaDonDTO> listct=cthdBUS.getListCthd(ChiTietHoaDonBUS.listCTHD, txMaHD.getText());
                                        int tongtien = tongtien(hd, listct, tongtienSP);
                                        hd.setTongtienHoadon(tongtien);
                                        HoaDonBUS hdbus = new HoaDonBUS();
                                        if(hdbus.UpdateTongTienHD(hd)){
                                            hdbus.docDSHD();
                                            showDSHD(HoaDonBUS.listHD);
                                        }
                                        else{
                                           JOptionPane.showMessageDialog(null, "Cập nhật tổng số tiền hóa đơn thất bại");
                                        }
                                    }
                                }
                                //TH: Nếu sửa ct chưa nhập hàng (chưa có mã phiếu nhập của ct trên phiếu nhập)
                                if(!findHD){
                                    bus.docDSCTHD();
                                    showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                                }
                                
                            }else{
                                JOptionPane.showMessageDialog(null, "Sửa chi tiết hóa đơn thất bại");
                            }   
                        }else{
                            JOptionPane.showMessageDialog(null, "Không thể sửa mã sản phẩm");
                        }
                        
                    
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa chi tiết phiếu nhập không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_jButton12ActionPerformed

    public ArrayList<SanPhamDTO> dsSP (HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> list){
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for(ChiTietHoaDonDTO ct : list){
            if(ct.getMaHD().equals(hd.getIdHoadon())){
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMasp(ct.getMaSP());
                ds.add(sp);
            }
        }
        return ds;
    }
    
    public int tongtien(HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> list, int tongtienHD){
        ArrayList<ChiTietKhuyenMaiDTO> dsctkm = xulyKhuyenMai(hd.getNgaylapHoadon(), tongtienHD, dsSP(hd, list)); //lấy dsctkm thỏa 
        String maKM = dsctkm.get(0).getMaKM();
        for(ChiTietKhuyenMaiDTO ctkm : dsctkm){
            int tienkm=0;
            int tongtienSP=0;
            for (ChiTietHoaDonDTO cthd : list){
                if(cthd.getMaSP().equals(ctkm.getMaSP())){
                    tongtienSP = cthd.getTongtien();
                    tongtienHD-=tongtienSP;
                    tienkm = tongtienSP - (tongtienSP * ctkm.getPhantramKM()) / 100;
                    tongtienHD += tienkm;

                }
            }
        }
        return tongtienHD;
    }
    
    
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
//        int i = tblDSCTHD.getSelectedRow();
//        if(i>=0){
//            if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn?", "Cảnh báo",
//                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                // yes option
//                try {
//                    ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
//                    for (ChiTietHoaDonDTO sp : ChiTietHoaDonBUS.listCTHD){
//                        if(sp.getMaHD().equals(txMaHD.getText()) && sp.getMaSP().equals(tblDSCTHD.getValueAt(i, 0))){
//                            if(bus.xoaCTHD(sp.getMaHD(), sp.getMaSP())){
//                                bus.docDSCTHD();
//                                showDSCTHD(ChiTietHoaDonBUS.listCTHD);
//                            }
//                            else{
//                                JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thất bại");
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//        else{
//            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa");
//        }
        
        
        String idHD = txMaHD.getText();
        String idSP = txMaSPCTHD.getText();
        int i= tblDSCTHD.getSelectedRow();
                
            try {
                if(i>=0){
                        ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
                        if(bus.xoaCTHD(idHD, idSP)){  //xóa sql
                            //TH: Xóa chi tiết sp mà đã có trên hóa đơn (trừ tổng tiền, trừ số lượng)
                            for(HoaDonDTO hd : HoaDonBUS.listHD){
                                if(hd.getIdHoadon().equals(txMaHD.getText())){ 
                                    //set lại tổng số tiền hóa đơn
//                                    int tongsotienHD = Integer.parseInt(String.valueOf(sp.getTongtienHoadon()).replaceAll(",", ""));
//                                    int tongsotienmoi = tongsotienHD - Integer.parseInt(txTongTienCTHD.getText());
//                                    sp.setTongtienHoadon(tongsotienmoi);
                                    int tongtienSP=0;
                                        for (ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD){
                                            if(ct.getMaHD().equals(txMaHD.getText())){
                                                tongtienSP+=ct.getTongtien();
                                            }
                                    }
                                    ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
                                    ArrayList<ChiTietHoaDonDTO> listct=cthdBUS.getListCthd(ChiTietHoaDonBUS.listCTHD, txMaHD.getText());
                                    int tongtien = tongtien(hd, listct, tongtienSP);
                                    hd.setTongtienHoadon(tongtien);
                                    
                                    HoaDonBUS hdbus = new HoaDonBUS();
                                    if(hdbus.UpdateTongTienHD(hd)){
                                        hdbus.docDSHD();
                                        showDSHD(HoaDonBUS.listHD);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Cập nhật tổng tiền hóa đơn thất bại");
                                    }
                                    
                                    //set lại số lượng trong bảng sản phẩm
                                    int soluongspo = 0;
                                    
                                            SanPhamDTO sp = new SanPhamDTO();
                                            int Soluongspnhap = Integer.valueOf(tblDSCTHD.getValueAt(i, 3).toString()); //lấy số lượng nhập
                                            for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                                if (tbSanPham.getValueAt(z, 0).equals(tblDSCTHD.getValueAt(i, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                    sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                    soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                                }
                                            }
                                            int soluongmoi = soluongspo + Soluongspnhap;
                                            sp.setSoluong(soluongmoi);
                                            SanPhamBUS spbus = new SanPhamBUS();
                                            if (spbus.UpdateSoluongSP(sp)) {
                                                spbus.docDSSP();
                                                showDSSP(SanPhamBUS.dssp);
                                                
                                                ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                                listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                                showBH(listSPDangBan);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                            }
                                }
                            }
                            
                            bus.docDSCTHD();
                            showDSCTHD(ChiTietHoaDonBUS.listCTHD);
                            
                            
                            
                            
                            //TH khi xóa hết all chi tiết thì xóa luôn hóa đơn
                            boolean findCTHD = false;
                            for(ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
                                if(cthd.getMaHD().equals(txMaHD.getText())){
                                    findCTHD = true;
                                }
                            }
                            if(!findCTHD){
                                HoaDonBUS hdbus = new HoaDonBUS();
                                if(hdbus.xoaHD(txMaHD.getText())){
                                    hdbus.docDSHD();
                                    showDSHD(HoaDonBUS.listHD);
                                    
                                    int soluongspo = 0;
                                    for(int u = 0; u < tblDSCTHD.getRowCount(); u++){
                                        if(tblDSCTHD.getValueAt(u, 0).equals(idHD)){
                                            SanPhamDTO sp = new SanPhamDTO();
                                            int Soluongspnhap = Integer.valueOf(tblDSCTHD.getValueAt(u, 3).toString()); //lấy số lượng nhập
                                            for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                                if (tbSanPham.getValueAt(z, 0).equals(tblDSCTHD.getValueAt(u, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                                    sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                                    soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu (trong bảng sản phẩm)
                                                }
                                            }
                                            int soluongmoi = soluongspo + Soluongspnhap;
                                            sp.setSoluong(soluongmoi);
                                            SanPhamBUS spbus = new SanPhamBUS();
                                            if (spbus.UpdateSoluongSP(sp)) {
                                                spbus.docDSSP();
                                                showDSSP(SanPhamBUS.dssp);
                                                
                                                ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                                listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                                showBH(listSPDangBan);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                            }
                                        }
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Xóa hóa đơn thất bại");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thất bại");
                        }
                        
                        
                        
                        
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn không thành công");
            }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        try {
            HoaDonBUS bus = new HoaDonBUS();
            HoaDonDTO hd = new HoaDonDTO();
            hd.setIdHoadon(txMaHD.getText());
            hd.setIdNhanvien(txMaNVHD.getText());
            hd.setIdKhachhang(txMaKHHD.getText());
            hd.setIdKhuyenmai(txMaKMHD.getText());
            hd.setNgaylapHoadon(ngayLapHD.getDate());
            int tongtien=0;
            for(ChiTietHoaDonDTO ct : ChiTietHoaDonBUS.listCTHD){
                if(ct.getMaHD().equals(txMaHD.getText())){
                    tongtien += ct.getTongtien();
                }
            }
            hd.setTongtienHoadon(tongtien);

            if(bus.themHD(hd)){
                showDSHD(HoaDonBUS.listHD);
                //set lại số lượng sản phẩm sau khi thanh toán
                int soluongspo = 0;
                        for(int u = 0; u < tblDSCTHD.getRowCount(); u++){
                            if(tblDSCTHD.getValueAt(u, 0).equals(txMaHD.getText())){
                                SanPhamDTO sp = new SanPhamDTO();
                                int SoluongspBH = Integer.valueOf(tblDSCTHD.getValueAt(u, 3).toString()); //lấy số lượng bán hàng
                                for (int z = 0; z < tbSanPham.getRowCount(); z++) {
                                    if (tbSanPham.getValueAt(z, 0).equals(tblDSCTPN.getValueAt(u, 1))) {  //tìm mã sản phẩm trong bảng sản phẩm
                                        sp.setMasp(tbSanPham.getValueAt(z, 0).toString());
                                        soluongspo = Integer.parseInt(tbSanPham.getValueAt(z, 4).toString());  //lấy số lượng sản phẩm ban đầu
                                    }
                                }
                                int soluongmoi = soluongspo - SoluongspBH;
                                sp.setSoluong(soluongmoi);
                                SanPhamBUS spbus = new SanPhamBUS();
                                if (spbus.UpdateSoluongSP(sp)) {
                                    spbus.docDSSP();
                                    showDSSP(SanPhamBUS.dssp);
                                    
                                    ArrayList<SanPhamDTO> listSPDangBan = new ArrayList<>();
                                    listSPDangBan = new SanPhamBUS().timKiemTrangthaiSP("Đang bán");
                                    showBH(listSPDangBan);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Cập nhật số lượng sản phẩm thất bại");
                                }
                            } 
                        }
                clear();
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void tblDSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHDMouseClicked
        // TODO add your handling code here:
        int i = tblDSHD.getSelectedRow();
                if(i>=0){
                    txMaHD.setText(tblDSHD.getValueAt(i, 0) + "");
                    txMaNVHD.setText(tblDSHD.getValueAt(i, 1) + "");
                    txMaKHHD.setText(tblDSHD.getValueAt(i, 2) + "");
                    txMaKMHD.setText(tblDSHD.getValueAt(i, 3) + "");
                    String ngay = tblDSHD.getValueAt(i, 4) + "";
                    Date ngayL = new Date();
                    try {
                        ngayL = new SimpleDateFormat("dd/MM/yyyy").parse(ngay);
                    } catch (ParseException ex) {
                    }
                    ngayLapHD.setDate(ngayL);
                    
                    ArrayList<ChiTietHoaDonDTO> kqCTHD = new ArrayList<>();
                    for (ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
                        if(cthd.getMaHD().equals(tblDSHD.getValueAt(i, 0))){
                            kqCTHD.add(cthd);
                        }
                    }
                    showDSCTHD(kqCTHD);
                }
    }//GEN-LAST:event_tblDSHDMouseClicked

    private void tblDSCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSCTHDMouseClicked
        // TODO add your handling code here:
        int i = tblDSCTHD.getSelectedRow();
                if(i>=0){
                    txMaHD.setText(tblDSCTHD.getValueAt(i, 0) + "");
                    for (HoaDonDTO hd : HoaDonBUS.listHD){
                        if(hd.getIdHoadon().equals(txMaHD.getText())){
                            txMaNVHD.setText(hd.getIdNhanvien());
                            txMaKHHD.setText(hd.getIdKhachhang());
                            txMaKMHD.setText(hd.getIdKhuyenmai());
                            ngayLapHD.setDate(hd.getNgaylapHoadon());
                        }
                    }
                    
                    txMaSPCTHD.setText(tblDSCTHD.getValueAt(i, 1) + "");
                    
                    String gia = tblDSCTHD.getValueAt(i, 2) + "";
                    String dongia = gia.replaceAll(",", "");
                    txDonGiaCTHD.setText(dongia);
                    
                    txSoLuongCTHD.setText(tblDSCTHD.getValueAt(i, 3) + "");
                    
                    String tien = tblDSCTHD.getValueAt(i, 4) + "";
                    String tongtien = tien.replaceAll(",", "");
                    txTongTienCTHD.setText(tongtien);
                }
    }//GEN-LAST:event_tblDSCTHDMouseClicked

    private void jScrollPane14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane14MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int i= tblDSHD.getSelectedRow();
                if(i>=0){
                    try {
                        if(txMaKMHD.getText().equals(tblDSHD.getValueAt(i, 3))){  //KTra không sửa mã khuyến mãi
                            HoaDonDTO hd = new HoaDonDTO();
                            hd.setIdHoadon(txMaHD.getText());
                            hd.setIdNhanvien(txMaNVHD.getText());
                            hd.setIdKhachhang(txMaKHHD.getText());
                            hd.setIdKhuyenmai(txMaKMHD.getText());
                            hd.setNgaylapHoadon(ngayLapHD.getDate());


                            HoaDonBUS bus = new HoaDonBUS();
                            bus.suaHD(hd); //sửa sql
                            bus.docDSHD();//cập nhật lại arraylist từ sql
                            showDSHD(HoaDonBUS.listHD); //show lại arraylist sau khi cập nhật
                        }else{
                            JOptionPane.showMessageDialog(null, "Không thể sửa mã khuyến mãi");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sửa hóa đơn không thành công");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tblBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBHMouseClicked
        // TODO add your handling code here:
        int i= tblBH.getSelectedRow();
                if(i>=0){
                        txMaSPBH.setText(tblBH.getValueAt(i, 0) + "");
                        txTenSPBH.setText(tblBH.getValueAt(i, 1)+ "");
                        
                        String gia = tblBH.getValueAt(i, 2)+ "";
                        String dongia = gia.replaceAll(",", "");
                        txDonGiaBH.setText(dongia);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
                }
    }//GEN-LAST:event_tblBHMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DlgChonSanPham dlg = new DlgChonSanPham();
        dlg.setVisible(true);

        if (DlgChonSanPham.maSP != null) {
            txMaSPCTHD.setText(DlgChonSanPham.maSP);
        } else {
            txMaSPCTHD.setText("");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamActionPerformed
        // TODO add your handling code here:
        try {
            
                hienThiThongKe(Integer.parseInt(cbbNam.getSelectedItem()+""));
            
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbbNamActionPerformed

    private void cbthangThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbthangThongkeActionPerformed
        // TODO add your handling code here:
        try {
            
                hienThiThongKeTheoThang(Integer.parseInt(cbthangThongke.getSelectedItem()+""), Integer.parseInt(cbbNam.getSelectedItem()+""));
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbthangThongkeActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int i = tblDSHD.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần in");
        } else {
            try {
                new ReportPDF(String.valueOf(tblDSHD.getValueAt(i, 0)));
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        importExcel(tbSanPham);
        SanPhamBUS spBus = new SanPhamBUS();
        int row = tbSanPham.getRowCount();
        System.out.println(row);
        for (int i = 0; i < row; i++) {
            SanPhamDTO spDTO = new SanPhamDTO();
            spDTO.setMasp(tbSanPham.getValueAt(i, 0) + "");
            spDTO.setTensp(tbSanPham.getValueAt(i, 1) + "");
            spDTO.setMaloai(tbSanPham.getValueAt(i, 2) + "");
            spDTO.setDongia(Integer.parseInt(tbSanPham.getValueAt(i, 3) + ""));
            spDTO.setSoluong(Integer.parseInt(tbSanPham.getValueAt(i, 4) + ""));
            spDTO.setDVT(tbSanPham.getValueAt(i, 5) + "");
            if(tbSanPham.getValueAt(i, 6).toString().equals("Đang bán"))
                spDTO.setTrangthai(true);
            else{
                spDTO.setTrangthai(false);
            }
            try {
                spBus.importExcelSP(spDTO);
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        for(SanPhamDTO sp : SanPhamBUS.dssp){
            int t=0;
            for (int i = 0; i < row; i++) {
                if(sp.getMasp().equals(tbSanPham.getValueAt(i, 0) + "")){
                    t=1;
                }
            }
            if(t==0){
                try {
                    new SanPhamBUS().xoaSP(sp.getMasp());
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            SanPhamBUS.dssp = new SanPhamBUS().getList();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        exportExcel(tbLoaisp);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        importExcel(tbLoaisp);
        LoaiSPBUS loaispBus = new LoaiSPBUS();
        int row = tbLoaisp.getRowCount();
        System.out.println(row);
        for (int i = 0; i < row; i++) {
            LoaiSPDTO loaispDTO = new LoaiSPDTO();
            loaispDTO.setMaloai(tbLoaisp.getValueAt(i, 0) + "");
            loaispDTO.setTenloai(tbLoaisp.getValueAt(i, 1) + "");
            
            try {
                loaispBus.importExcelLoaiSP(loaispDTO);
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //TH trong bảng có nhiều loại sp hơn file excel
        for(LoaiSPDTO loaiSP : LoaiSPBUS.dsLoaiSP){
            int t=0;
            for (int i = 0; i < row; i++) {
                if(loaiSP.getMaloai().equals(tbLoaisp.getValueAt(i, 0) + "")){
                    t=1;
                }
            }
            if(t==0){
                try {
                    new LoaiSPBUS().xoaLoaiSP(loaiSP.getMaloai());
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            LoaiSPBUS.dsLoaiSP = new LoaiSPBUS().getList();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Dữ liệu cũ sẽ bị xoá, tiếp tục?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return;
        }
        
        importExcel(tblDSKH);
        KhachHangBUS khBus = new KhachHangBUS();
        int row = tblDSKH.getRowCount();
        System.out.println(row);
        for (int i = 0; i < row; i++) {
            KhachHangDTO khDTO = new KhachHangDTO();
            khDTO.setMaKH(tblDSKH.getValueAt(i, 0) + "");
            khDTO.setHoKH(tblDSKH.getValueAt(i, 1) + "");
            khDTO.setTenKH(tblDSKH.getValueAt(i, 2) + "");
            khDTO.setGioitinh(tblDSKH.getValueAt(i, 3) + "");
            khDTO.setDiachi(tblDSKH.getValueAt(i, 4) + "");
            khDTO.setSdt(tblDSKH.getValueAt(i, 5) + "");
            try {
                khBus.importExcelKH(khDTO);
//            } catch (HeadlessException ex) {
//                //JOptionPane.showMessageDialog(null,"Nhập Excel thất bại");
            } catch (Exception ex) {
                Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //TH trong bảng có nhiều sp hơn file excel
        for(KhachHangDTO kh : KhachHangBUS.dskh){
            int t=0;
            for (int i = 0; i < row; i++) {
                if(kh.getMaKH().equals(tblDSKH.getValueAt(i, 0) + "")){
                    t=1;
                }
            }
            if(t==0){
                try {
                    new KhachHangBUS().xoaKH(kh.getMaKH());
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            KhachHangBUS.dskh = new KhachHangBUS().getList();
        } catch (Exception ex) {
            Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void txTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTimKiemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTimKiemSPActionPerformed

    public void nhanVien() {
        PanelHienthi.removeAll();
        PanelHienthi.add(panelTrangChu);
        PanelHienthi.repaint();
        pnAdmin.setVisible(false);

        NhanVienBUS nhanVienBus = new NhanVienBUS();
        NhanVienDTO nhanVien = nhanVienBus.thongTinNhanVien();

        String tenDayDu = nhanVien.getHo() + " " + nhanVien.getTen();

        lbTenNhanVien.setText(tenDayDu);
        if(nhanVien.getGioitinh().equals("Nam")){
            anhNV.setIcon(new ImageIcon(getClass().getResource("/framedesign/staff.png")));
        }
        else if(nhanVien.getGioitinh().equals("Nữ")){
            anhNV.setIcon(new ImageIcon(getClass().getResource("/framedesign/icons8-woman-profile-96.png")));
        }
        pnNhanVien.setVisible(true);
    }

    public void admin() {
        PanelHienthi.removeAll();
        PanelHienthi.add(panelTrangChu);
        PanelHienthi.repaint();
        pnNhanVien.setVisible(false);

        NhanVienBUS adminBus = new NhanVienBUS();
        NhanVienDTO admin = adminBus.thongTinNhanVien();

        String tenDayDu = admin.getHo() + " " + admin.getTen();

        lbTenAdmin.setText(tenDayDu);
        if(admin.getGioitinh().equals("Nam")){
            anhAdmin.setIcon(new ImageIcon(getClass().getResource("/framedesign/icons8-admin-settings-male-96.png")));
        }
        else if(admin.getGioitinh().equals("Nữ")){
            anhAdmin.setIcon(new ImageIcon(getClass().getResource("/framedesign/admin-nu.jpg")));
        }
        
        pnAdmin.setVisible(true);
        
    }
    
    private void logOut() throws Exception {
        dispose();
        dangnhap login = new dangnhap();

        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
    public void docDSNV() throws Exception{
        NhanVienBUS bus = new NhanVienBUS();
        if(NhanVienBUS.listNV == null){
            bus.docDSNV();
        }
        Vector header = new Vector();
        header.add("Mã sinh viên");
        header.add("Họ");
        header.add("Tên");
        header.add("Giới tính");
        header.add("Ngày sinh");
        header.add("Số điện thoại");
        header.add("Địa chỉ");
        header.add("Chức vụ");
        header.add("Lương tháng");
        header.add("Trạng thái");
        
        modelNV = new DefaultTableModel(header,0);
        
        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for(NhanVienDTO nv : NhanVienBUS.listNV){
            Vector row = new Vector();
            row.add(nv.getIdNV());
            row.add(nv.getHo());
            row.add(nv.getTen());
            row.add(nv.getGioitinh());
            row.add(sdf.format(nv.getNgaySinh()));
            row.add(nv.getSdt());
            row.add(nv.getDiachi());
            row.add(nv.getChucvu());
            row.add(dcf.format(nv.getLuongThang()));
            if(nv.isTrangthai()){
                row.add("Hiện hành");
            }
            else{
                row.add("Đã nghỉ");
            }
            modelNV.addRow(row);
        }
        tblDSNV.setModel(modelNV);
    }
    
    
    public void showDSNV(ArrayList<NhanVienDTO> ds){
        Vector header = new Vector();
        header.add("Mã sinh viên");
        header.add("Họ");
        header.add("Tên");
        header.add("Giới tính");
        header.add("Ngày sinh");
        header.add("Số điện thoại");
        header.add("Địa chỉ");
        header.add("Chức vụ");
        header.add("Lương tháng");
        header.add("Trạng thái");
                
        modelNV = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(NhanVienDTO nv : ds){
            Vector row = new Vector();
            row.add(nv.getIdNV());
            row.add(nv.getHo());
            row.add(nv.getTen());
            row.add(nv.getGioitinh());
            row.add(sdf.format(nv.getNgaySinh()));
            row.add(nv.getSdt());
            row.add(nv.getDiachi());
            row.add(nv.getChucvu());
            row.add(dcf.format(nv.getLuongThang()));
            if(nv.isTrangthai()){
                row.add("Hiện hành");
            }
            else{
                row.add("Đã nghỉ");
            }
            modelNV.addRow(row);
        }
        tblDSNV.setModel(modelNV);
        //tblDSNV.getRowSorter().toggleSortOrder(0); //sắp xếp lại cho thứ tự trong jtable giống vs thứ tự trong arraylist
    }
    
    public void docDSNCC() throws Exception{
        NhaCungCapBUS bus = new NhaCungCapBUS();
        if(NhaCungCapBUS.listNCC == null){
            bus.docDSNCC();
        }
        Vector header = new Vector();
        header.add("ID_NCC");
        header.add("Tên nhà cung cấp");
        header.add("Địa chỉ");
        header.add("Số điện thoại");
        
        modelNCC = new DefaultTableModel(header,0);
                        
        for(NhaCungCapDTO nv : NhaCungCapBUS.listNCC){
            Vector row = new Vector();
            row.add(nv.getIdNCC());
            row.add(nv.getTenNCC());
            row.add(nv.getDiachiNCC());
            row.add(nv.getSdtNCC());
            modelNCC.addRow(row);
        }
        tblDSNCC.setModel(modelNCC);
    }
    
    public void showDSNCC(ArrayList<NhaCungCapDTO> ds){
        Vector header = new Vector();
        header.add("ID_NCC");
        header.add("Tên nhà cung cấp");
        header.add("Địa chỉ");
        header.add("Số điện thoại");
                
        modelNCC = new DefaultTableModel(header,0);
        
        for(NhaCungCapDTO nv : ds){
            Vector row = new Vector();
            row.add(nv.getIdNCC());
            row.add(nv.getTenNCC());
            row.add(nv.getDiachiNCC());
            row.add(nv.getSdtNCC());
            modelNCC.addRow(row);
        }
        tblDSNCC.setModel(modelNCC);
       //tblDSNCC.getRowSorter().toggleSortOrder(0); //sắp xếp lại cho thứ tự trong jtable giống vs thứ tự trong arraylist
    }
    
    public void docDSSP(){
    SanPhamBUS bus = new SanPhamBUS();
        if(SanPhamBUS.dssp == null){
            bus.docDSSP();
        }
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Mã loại");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Đơn vị tính");
        header.add("Trạng thái");
      
        modelSP = new DefaultTableModel(header,0);
                        
        for(SanPhamDTO sp : SanPhamBUS.dssp){
            Vector row = new Vector();
            row.add(sp.getMasp());
            row.add(sp.getTensp());
            row.add(sp.getMaloai());
            row.add(sp.getDongia());
            row.add(sp.getSoluong());
            row.add(sp.getDVT());
            if(sp.getTrangthai()){
                row.add("Đang bán");
            }
            else{
                row.add("Hết hàng");
            }
            modelSP.addRow(row);
        }
        tbSanPham.setModel(modelSP);
    }
    
    public void showDSSP(ArrayList<SanPhamDTO> ds){
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Mã loại");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Đơn vị tính");
        header.add("Trạng thái");
       
        modelSP = new DefaultTableModel(header,0);
        
        for(SanPhamDTO sp : ds){
            Vector row = new Vector();
            row.add(sp.getMasp());
            row.add(sp.getTensp());
            row.add(sp.getMaloai());
            row.add(sp.getDongia());
            row.add(sp.getSoluong());
            row.add(sp.getDVT());
            if(sp.getTrangthai()){
                row.add("Đang bán");
            }
            else{
                row.add("Hết hàng");
            }
            
            modelSP.addRow(row);
        }
        tbSanPham.setModel(modelSP);
    }
    
    public void docDSLoaiSP(){
        LoaiSPBUS bus = new LoaiSPBUS();
        if(LoaiSPBUS.dsLoaiSP == null){
            bus.docDSLoaiSP();
        }
        Vector header = new Vector();
        header.add("Mã loại");
        header.add("Tên loại");
      
        modelLSP = new DefaultTableModel(header,0);
                        
        for(LoaiSPDTO sp : LoaiSPBUS.dsLoaiSP){
            Vector row = new Vector();
            row.add(sp.getMaloai());
            row.add(sp.getTenloai());
            
            modelLSP.addRow(row);
        }
        tbLoaisp.setModel(modelLSP);
    }
    
    public void showDSLoaiSP(ArrayList<LoaiSPDTO> ds){
        Vector header = new Vector();
        header.add("Mã loại");
        header.add("Tên loại");
        
        modelLSP = new DefaultTableModel(header,0);
        
        for(LoaiSPDTO sp : ds){
            Vector row = new Vector();
            row.add(sp.getMaloai());
            row.add(sp.getTenloai());
            
            modelLSP.addRow(row);
        }
        tbLoaisp.setModel(modelLSP);
    }
    
    
    
    public void docDSKH(){
    KhachHangBUS bus = new KhachHangBUS();
        if(KhachHangBUS.dskh == null){
            bus.docDSKH();
        }
        Vector header = new Vector();
        header.add("Mã khách hàng");
        header.add("Họ khách hàng");
        header.add("Tên khách hàng");
        header.add("Giới tính");
        header.add("Địa chỉ");
        header.add("SDT");
      
        modelKH = new DefaultTableModel(header,0);
                        
        for(KhachHangDTO kh : KhachHangBUS.dskh){
            Vector row = new Vector();
            row.add(kh.getMaKH());
            row.add(kh.getHoKH());
            row.add(kh.getTenKH());
            row.add(kh.getGioitinh());
            row.add(kh.getDiachi());
            row.add(kh.getSdt());

            modelKH.addRow(row);
        }
        tblDSKH.setModel(modelKH);
    }
    
    public void showDSKH(ArrayList<KhachHangDTO> ds){
        Vector header = new Vector();
        header.add("Mã khách hàng");
        header.add("Họ khách hàng");
        header.add("Tên khách hàng");
        header.add("Giới tính");
        header.add("Địa chỉ");
        header.add("SDT");
       
        modelKH = new DefaultTableModel(header,0);
        
        for(KhachHangDTO kh : ds){
            Vector row = new Vector();
            row.add(kh.getMaKH());
            row.add(kh.getHoKH());
            row.add(kh.getTenKH());
            row.add(kh.getGioitinh());
            row.add(kh.getDiachi());
            row.add(kh.getSdt());
            
            modelKH.addRow(row);
        }
        tblDSKH.setModel(modelKH);
        //tbKhachhang.getRowSorter().toggleSortOrder(0);//sắp xếp lại cho thứ tự trong jtable giống vs thứ tự trong arraylist
    }
    
    public void docDSCTPN() throws Exception{
        DecimalFormat dcf = new DecimalFormat("###,###");
        ChiTietPhieuNhapBUS bus = new ChiTietPhieuNhapBUS();
        if(ChiTietPhieuNhapBUS.listCTPN == null){
            bus.docDSCTPN();
        }
        Vector header = new Vector();
        header.add("Mã phiếu nhập");
        header.add("Mã sản phẩm");
        header.add("Số lượng");
        header.add("Đơn giá");
        header.add("Tổng tiền nhập");
        
        
        modelCTPN = new DefaultTableModel(header,0);
                        
        for(ChiTietPhieuNhapDTO ctpn : ChiTietPhieuNhapBUS.listCTPN){
            Vector row = new Vector();
            row.add(ctpn.getIdPhieuNhap());
            row.add(ctpn.getIdSanPham());
            row.add(ctpn.getSoluong());
            row.add(dcf.format(ctpn.getDongia()));
            row.add(dcf.format(ctpn.getTongtiennhap()));
            modelCTPN.addRow(row);
        }
        tblDSCTPN.setModel(modelCTPN);
    }
    
    public void showDSCTPN(ArrayList<ChiTietPhieuNhapDTO> ds){
        Vector header = new Vector();
        header.add("Mã phiếu nhập");
        header.add("Mã sản phẩm");
        header.add("Số lượng");
        header.add("Đơn giá");
        header.add("Tổng tiền nhập");
                
        modelCTPN = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(ChiTietPhieuNhapDTO ctpn : ds){
            Vector row = new Vector();
            row.add(ctpn.getIdPhieuNhap());
            row.add(ctpn.getIdSanPham());
            row.add(ctpn.getSoluong());
            row.add(dcf.format(ctpn.getDongia()));
            row.add(dcf.format(ctpn.getTongtiennhap()));
            modelCTPN.addRow(row);
        }
        tblDSCTPN.setModel(modelCTPN);
        //tblDSNV.getRowSorter().toggleSortOrder(0); //sắp xếp lại cho thứ tự trong jtable giống vs thứ tự trong arraylist
    }
    
    public void docDSPN() throws Exception{
        PhieuNhapBUS bus = new PhieuNhapBUS();
        if(PhieuNhapBUS.listPN == null){
            bus.docDSPN();
        }
        Vector header = new Vector();
        header.add("Mã phiếu nhập");
        header.add("Mã nhà cung cấp");
        header.add("Mã nhân viên");
        header.add("Ngày nhập");
        header.add("Tổng tiền");
        
        
        modelPN = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");   
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuNhapDTO pn : PhieuNhapBUS.listPN){
            Vector row = new Vector();
            row.add(pn.getIdPN());
            row.add(pn.getIdNCC());
            row.add(pn.getIdNhanVien());
            row.add(sdf.format(pn.getNgayNhap()));
            row.add(dcf.format(pn.getTongsotien()));
            modelPN.addRow(row);
        }
        tblDSPN.setModel(modelPN);
    }
    
    public void showDSPN(ArrayList<PhieuNhapDTO> ds){
        Vector header = new Vector();
        header.add("Mã phiếu nhập");
        header.add("Mã nhà cung cấp");
        header.add("Mã nhân viên");
        header.add("Ngày nhập");
        header.add("Tổng tiền");
                
        modelPN = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuNhapDTO pn : ds){
            Vector row = new Vector();
            row.add(pn.getIdPN());
            row.add(pn.getIdNCC());
            row.add(pn.getIdNhanVien());
            row.add(sdf.format(pn.getNgayNhap()));
            row.add(dcf.format(pn.getTongsotien()));
            modelPN.addRow(row);
        }
        tblDSPN.setModel(modelPN);
    }
    
    public void docDSCTKM() throws Exception{
        ChiTietKhuyenMaiBUS bus = new ChiTietKhuyenMaiBUS();
        if(ChiTietKhuyenMaiBUS.listCTKM == null){
            bus.docDSCTKM();
        }
        Vector header = new Vector();
        header.add("Mã khuyến mãi");
        header.add("Mã sản phẩm");
        header.add("% khuyến mãi");
        
        modelCTKM = new DefaultTableModel(header,0);
                        
        for(ChiTietKhuyenMaiDTO ctkm : ChiTietKhuyenMaiBUS.listCTKM){
            Vector row = new Vector();
            row.add(ctkm.getMaKM());
            row.add(ctkm.getMaSP());
            row.add(ctkm.getPhantramKM());
            modelCTKM.addRow(row);
        }
        tblDSCTKM.setModel(modelCTKM);
    }
    
    public void showDSCTKM(ArrayList<ChiTietKhuyenMaiDTO> ds){
        Vector header = new Vector();
        header.add("Mã khuyến mãi");
        header.add("Mã sản phẩm");
        header.add("% khuyến mãi");
                
        modelCTKM = new DefaultTableModel(header,0);
        for(ChiTietKhuyenMaiDTO ctkm : ds){
            Vector row = new Vector();
            row.add(ctkm.getMaKM());
            row.add(ctkm.getMaSP());
            row.add(ctkm.getPhantramKM());
            modelCTKM.addRow(row);
        }
        tblDSCTKM.setModel(modelCTKM);
    }
    
    public void docDSKM() throws Exception{
        KhuyenMaiBUS bus = new KhuyenMaiBUS();
        if(KhuyenMaiBUS.listKM == null){
            bus.docDSKM();
        }
        Vector header = new Vector();
        header.add("Mã khuyến mãi");
        header.add("Tên khuyến mãi");
        header.add("Ngày bắt đầu");
        header.add("Ngày kết thúc");
        header.add("Điều kiện");
        
        
        modelKM = new DefaultTableModel(header,0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(KhuyenMaiDTO km : KhuyenMaiBUS.listKM){
            Vector row = new Vector();
            row.add(km.getMaKM());
            row.add(km.getTenKM());
            row.add(sdf.format(km.getNgaybdKM()));
            row.add(sdf.format(km.getNgayktKM()));
            row.add(km.getDieukienKM());
            modelKM.addRow(row);
        }
        tblDSKM.setModel(modelKM);
    }
    
    public void showDSKM(ArrayList<KhuyenMaiDTO> ds){
        Vector header = new Vector();
        header.add("Mã khuyến mãi");
        header.add("Tên khuyến mãi");
        header.add("Ngày bắt đầu");
        header.add("Ngày kết thúc");
        header.add("Điều kiện");
                
        modelKM = new DefaultTableModel(header,0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(KhuyenMaiDTO km : ds){
            Vector row = new Vector();
            row.add(km.getMaKM());
            row.add(km.getTenKM());
            row.add(sdf.format(km.getNgaybdKM()));
            row.add(sdf.format(km.getNgayktKM()));
            row.add(km.getDieukienKM());
            modelKM.addRow(row);
        }
        tblDSKM.setModel(modelKM);
    }
    
    public void docDSCTHD() throws Exception{
        ChiTietHoaDonBUS bus = new ChiTietHoaDonBUS();
        if(ChiTietHoaDonBUS.listCTHD == null){
            bus.docDSCTHD();
        }
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sản phẩm");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Tổng tiền");
        
        
        modelCTHD = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
            Vector row = new Vector();
            row.add(cthd.getMaHD());
            row.add(cthd.getMaSP());
            row.add(dcf.format(cthd.getDongia()));
            row.add(cthd.getSoluong());
            row.add(dcf.format(cthd.getTongtien()));
            
            modelCTHD.addRow(row);
        }
        tblDSCTHD.setModel(modelCTHD);
    }
    
    public void showDSCTHD(ArrayList<ChiTietHoaDonDTO> ds){
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã sản phẩm");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Tổng tiền");
                
        modelCTHD = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(ChiTietHoaDonDTO cthd : ds){
            Vector row = new Vector();
            row.add(cthd.getMaHD());
            row.add(cthd.getMaSP());
            row.add(dcf.format(cthd.getDongia()));
            row.add(cthd.getSoluong());
            row.add(dcf.format(cthd.getTongtien()));
            modelCTHD.addRow(row);
        }
        tblDSCTHD.setModel(modelCTHD);
    }
    
    public void docDSHD() throws Exception{
        HoaDonBUS bus = new HoaDonBUS();
        if(HoaDonBUS.listHD == null){
            bus.docDSHD();
        }
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã nhân viên");
        header.add("Mã khách hàng");
        header.add("Mã khuyến mãi");
        header.add("Ngày lập");
        header.add("Tổng tiền");
        
        
        modelHD = new DefaultTableModel(header,0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(HoaDonDTO hd : HoaDonBUS.listHD){
            Vector row = new Vector();
            row.add(hd.getIdHoadon());
            row.add(hd.getIdNhanvien());
            row.add(hd.getIdKhachhang());
            row.add(hd.getIdKhuyenmai());
            row.add(sdf.format(hd.getNgaylapHoadon()));
            row.add(dcf.format(hd.getTongtienHoadon()));
            modelHD.addRow(row);
        }
        tblDSHD.setModel(modelHD);
    }
    
    public void showDSHD(ArrayList<HoaDonDTO> ds){
        Vector header = new Vector();
        header.add("Mã hóa đơn");
        header.add("Mã nhân viên");
        header.add("Mã khách hàng");
        header.add("Mã khuyến mãi");
        header.add("Ngày lập");
        header.add("Tổng tiền");
                
        modelHD = new DefaultTableModel(header,0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(HoaDonDTO hd : ds){
            Vector row = new Vector();
            row.add(hd.getIdHoadon());
            row.add(hd.getIdNhanvien());
            row.add(hd.getIdKhachhang());
            row.add(hd.getIdKhuyenmai());
            row.add(sdf.format(hd.getNgaylapHoadon()));
            row.add(dcf.format(hd.getTongtienHoadon()));
            modelHD.addRow(row);
        }
        tblDSHD.setModel(modelHD);
    }
    
    public void showBH(ArrayList<SanPhamDTO> ds){
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Đơn giá");
        header.add("Còn lại");
        header.add("Đơn vị tính");
       
        modelBH = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(SanPhamDTO sp : ds){
            Vector row = new Vector();
            row.add(sp.getMasp());
            row.add(sp.getTensp());
            row.add(dcf.format(sp.getDongia()));
            row.add(sp.getSoluong());
            row.add(sp.getDVT());
            modelBH.addRow(row);
        }
        tblBH.setModel(modelBH);
    }
    
    public ArrayList<ChiTietHoaDonDTO> getListGioHang(String mahd){
        ArrayList<ChiTietHoaDonDTO> ds = new ArrayList<>();
        for (ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
            if(cthd.getMaHD().equals(mahd)){
                ds.add(cthd);
            }
        }
        return ds;
    }
    
    public void showGioHang(ArrayList<ChiTietHoaDonDTO> ds){
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Đơn giá");
        header.add("Số lượng");
        header.add("Thành tiền");
       
        modelGioHang = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(ChiTietHoaDonDTO cthd : ds){
            Vector row = new Vector();
            row.add(cthd.getMaSP());
            SanPhamDTO sp = new SanPhamBUS().timKiemMaSP(SanPhamBUS.dssp, cthd.getMaSP());
            row.add(sp.getTensp());
            row.add(dcf.format(cthd.getDongia()));
            row.add(cthd.getSoluong());
            row.add(dcf.format(cthd.getTongtien()));
            modelGioHang.addRow(row);
        }
        tblDSGioHang.setModel(modelGioHang);
    }
    
    
    public boolean checkPhone(String str){
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Kiem tra dinh dang
        boolean kt = str.matches(reg);

        if (kt == false) {
            return false;
        } else {
            //System.out.println("Số điện thoại hợp lệ");
            return true;
        }
    }
    
    public void requestFocusSP(){
        txTenSP.requestFocus();
        txTenSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txDonGiaSP.requestFocus();
            }
        });
        txDonGiaSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txSoLuongSP.requestFocus();
            }
        });
        txSoLuongSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txDonViTinhSP.requestFocus();
            }
        });
        txDonViTinhSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txTimKiemSP.requestFocus();
            }
        });
        txTimKiemSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txTenSP.requestFocus();
            }
        });
        
    }
    public void requestFocusLoaiSP(){
        txTenLoai.requestFocus();
        txTenLoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txTimKiemLoaiSP.requestFocus();
            }
        });
        txTimKiemLoaiSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                    txTenLoai.requestFocus();
            }
        });
    }
    
    public void exportExcel(JTable tbl) {
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new JFileChooser("Export/");
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn vừa chọn + tên tệp
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Sheet 1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerCellStyle.setBorderTop(BorderStyle.THIN);
                headerCellStyle.setBorderBottom(BorderStyle.THIN);
                headerCellStyle.setBorderLeft(BorderStyle.THIN);
                headerCellStyle.setBorderRight(BorderStyle.THIN);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row headerRow = sheet.createRow(0);

                // Tạo header
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                    cell.setCellStyle(headerCellStyle);
                }

                Font contentFont = workbook.createFont();
                contentFont.setBold(false);
                contentFont.setFontHeightInPoints((short) 13);
                contentFont.setColor(IndexedColors.BLACK.getIndex());
                CellStyle contentCellStyle = workbook.createCellStyle();
                contentCellStyle.setFont(contentFont);
                contentCellStyle.setBorderTop(BorderStyle.THIN);
                contentCellStyle.setBorderBottom(BorderStyle.THIN);
                contentCellStyle.setBorderLeft(BorderStyle.THIN);
                contentCellStyle.setBorderRight(BorderStyle.THIN);

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j) + "");
                        cell.setCellStyle(contentCellStyle);
                        sheet.autoSizeColumn(j);
                    }
                }

                // Ghi file
                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                
                JOptionPane.showMessageDialog(null, "Xuất file thành công!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xuất file thất bại!");
        }
    }
    
    public void importExcel(JTable tbl) {
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new JFileChooser("export/");
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(fileSelected);
                BufferedInputStream bis = new BufferedInputStream(fis);

                XSSFWorkbook wb = new XSSFWorkbook(bis);
                XSSFSheet sheet = wb.getSheetAt(0);

                DefaultTableModel dtmtbl = (DefaultTableModel) dtm;
                dtmtbl.setRowCount(0);
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Vector vec = new Vector();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (dtmtbl.getColumnCount() != row.getLastCellNum()) {
                            JOptionPane.showMessageDialog(null, "Nhập file thất bại!");
                            return;
                        }
                        Cell cell = row.getCell(j);
                        vec.add(cell.getStringCellValue());
                    }
                    dtmtbl.addRow(vec);
                }
                JOptionPane.showMessageDialog(null, "Nhập file thành công!");
            }
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Nhập file thất bại!");
        }
    }
    
    private ArrayList<ChiTietKhuyenMaiDTO> xulyKhuyenMai(Date ngayLapHD, int tongtienHD, ArrayList<SanPhamDTO> ds){ //ds để lấy các mã khuyến mãi trong giỏ hàng
        ChiTietKhuyenMaiDTO kqCTKM = new ChiTietKhuyenMaiDTO();
        ArrayList<ChiTietKhuyenMaiDTO> dsCTKM = new ArrayList<>(); //ds dsctkm thỏa
        //KT Khuyến mãi
        for(KhuyenMaiDTO km : KhuyenMaiBUS.listKM){
            Date dayK = km.getNgayktKM(); //ketthuc
            Date dayB = km.getNgaybdKM(); //batdau
            if (ngayLapHD.compareTo(dayB) >= 0 && dayK.compareTo(ngayLapHD) >= 0) {
                if(tongtienHD >= km.getDieukienKM()){
                    kqCTKM.setMaKM(km.getMaKM()); //lấy mã khuyến mãi thỏa các điều kiện trong bảng khuyến mãi
                }
            }
        }
        
        //KT Chi tiết khuyến mãi
        for(ChiTietKhuyenMaiDTO ctkm : ChiTietKhuyenMaiBUS.listCTKM){
            if(ctkm.getMaKM().equals(kqCTKM.getMaKM())){
                for(SanPhamDTO sp : ds){
                    if(ctkm.getMaSP().equals(sp.getMasp())){
                        ChiTietKhuyenMaiDTO ct = new ChiTietKhuyenMaiDTO();
                        ct.setMaKM(kqCTKM.getMaKM());
                        ct.setMaSP(ctkm.getMaSP());
                        ct.setPhantramKM(ctkm.getPhantramKM());
                        dsCTKM.add(ct);
//                        kqHD.setPhantramKM(dsctkm.getPhantramKM());
//                        kqHD.setMaSP(dsctkm.getMaSP());
                    }
                }
                
            }
        }
        return dsCTKM;   
    }
    
    private ArrayList<SanPhamDTO> ThongKeDSSanPhamHetHang(){
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for (SanPhamDTO sp : SanPhamBUS.dssp){
            if(sp.getSoluong() < 10){
                ds.add(sp);
            }
        }
        return ds;
    }
    
    public void showDSSPHetHang(ArrayList<SanPhamDTO> ds){
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Số lượng");
       
        modelThongKeSPSapHet = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for(SanPhamDTO sp : ds){
            Vector row = new Vector();
            row.add(sp.getMasp());
            row.add(sp.getTensp());
            row.add(sp.getSoluong());
            modelThongKeSPSapHet.addRow(row);
        }
        tblThongKeSPSapHet.setModel(modelThongKeSPSapHet);
    }
    
    private void hienThiThongKeTheoThang(int thang, int nam) throws Exception {
        DecimalFormat dcf = new DecimalFormat("###,###");
        //ThongKeDTO thongKe = new ThongKeBUS().thongKe(Integer.parseInt(cbbNam.getSelectedItem()+""));
        ThongKeDTO thongKe = new ThongKeBUS().thongKeTheoThang(thang, nam);
        lbThongKeTongChi.setText(dcf.format(thongKe.getChiThang()));
        lbThongKeDoanhThu.setText(dcf.format(thongKe.getThuThang()));
        lbThongKeTongDonHang.setText(dcf.format(thongKe.getSoLuongDonHang()));
        
        //showThongKeTopBanChay(thang, nam);
    }
    
    private void hienThiThongKe(int nam) throws Exception {
        DecimalFormat dcf = new DecimalFormat("###,###");
        //ThongKeDTO thongKe = new ThongKeBUS().thongKe(Integer.parseInt(cbbNam.getSelectedItem()+""));
        ThongKeDTO thongKe = new ThongKeBUS().thongKe(nam);
        lbThongKeSP.setText(dcf.format(thongKe.getSoLuongSP()));
        lbThongKeNV.setText(dcf.format(thongKe.getSoLuongNV()));
        lbThongKeKH.setText(dcf.format(thongKe.getSoLuongKH()));
        lbThongKeTongChi.setText(dcf.format(thongKe.getTongChi()));
        lbThongKeDoanhThu.setText(dcf.format(thongKe.getTongDoanhThu()));
        lbThongKeTongDonHang.setText(dcf.format(thongKe.getSoLuongDonHang()));
        
//        lblDoanhThuQuy1.setText(dcf.format(thongKe.getTongThuQuy(1)));
//        lblDoanhThuQuy2.setText(dcf.format(thongKe.getTongThuQuy(2)));
//        lblDoanhThuQuy3.setText(dcf.format(thongKe.getTongThuQuy(3)));
//        lblDoanhThuQuy4.setText(dcf.format(thongKe.getTongThuQuy(4)));
//        lblTongDoanhThu.setText(dcf.format(thongKe.getTongDoanhThu()));
//        showThongKeSP(Integer.parseInt(cbbNam.getSelectedItem()+""));
//        showThongKeKH(Integer.parseInt(cbbNam.getSelectedItem()+""));
        //showThongKeSP(nam);
        showThongKeKH(nam);
        showThongKeTopBanChay(nam);
        showThongKeTongThu(nam);
        showThongKeTongChi(nam);
    }
//    
//    public void showThongKeSP(int nam) throws Exception{
//        
//        Vector header = new Vector();
//        header.add("");
//        header.add("Qúy 1");
//        header.add("Qúy 2");
//        header.add("Qúy 3");
//        header.add("Qúy 4");
//        header.add("Tổng cộng");
//        
//        modelThongKeSP = new DefaultTableModel(header,0);
//        ThongKeBUS thongKe = new ThongKeBUS();
////        ArrayList<SanPhamDTO> dssp = new ArrayList<>();
////                dssp= thongKe.thongKeDSSPTheoNam(nam);
////        
////        ArrayList<SanPhamDTO> spQ1= new ArrayList<>();
////              spQ1  = thongKe.thongKeSPTheoQuy(nam, 1);
////        ArrayList<SanPhamDTO> spQ2= new ArrayList<>();
////               spQ2 = thongKe.thongKeSPTheoQuy(nam, 2);
////        ArrayList<SanPhamDTO> spQ3= new ArrayList<>();
////               spQ3 = thongKe.thongKeSPTheoQuy(nam, 3);
////        ArrayList<SanPhamDTO> spQ4= new ArrayList<>();
////               spQ4 = thongKe.thongKeSPTheoQuy(nam, 4);
//
//        ArrayList<SanPhamDTO> dssp= thongKeSPTheoQuy(nam, 0);
//        
//        ArrayList<SanPhamDTO> spQ1= thongKeSPTheoQuy(nam, 1);
//        ArrayList<SanPhamDTO> spQ2= thongKeSPTheoQuy(nam, 2);
//        ArrayList<SanPhamDTO> spQ3= thongKeSPTheoQuy(nam, 3);
//        ArrayList<SanPhamDTO> spQ4= thongKeSPTheoQuy(nam, 4);
//        
//        for(SanPhamDTO a : dssp){
//            System.out.println("ds " + a.getMasp());
//        }
//        
//        for(SanPhamDTO a : spQ1){
//            System.out.println(1 + " " + a.getMasp() + " " + a.getDongia());
//        }
//        
//        for(SanPhamDTO a : spQ2){
//            System.out.println(2 + " " + a.getMasp() + " " + a.getDongia());
//        }
//        
//        DecimalFormat dcf = new DecimalFormat("###,###");
//        int tongcong=0;
//        if(dssp!=null){
//        for(SanPhamDTO sp : dssp){
//            int sum=0; //tổng tiền sản phẩm của 4 quý
//            Vector row = new Vector();
//            row.add(sp.getMasp());  //tên khách hàng
//            int t=0;
//            int p=0;
//            if(spQ1!=null){
//                for(SanPhamDTO sp1 : spQ1){
//                    if(sp1.getMasp().equals(sp.getMasp())){
//                        row.add(sp1.getDongia()); //giá sản phẩm dòng 1
//                        sum+=sp1.getDongia();
//                        p=1;
//                    }
//                }
//                if(p==0){
//                    row.add(0);
//                    t=1;
//                }
//            }else if(t==0){
//                row.add(0);
//            }
//            t=0;
//            p=0;
//            if(spQ2!=null){
//                for(SanPhamDTO sp2 : spQ2){
//                    if(sp2.getMasp().equals(sp.getMasp())){
//                        row.add(sp2.getDongia()); //giá sản phẩm dòng 1
//                        sum+=sp2.getDongia();
//                        p=1;
//                    }
//                }
//                if(p==0){
//                    row.add(0);
//                    t=1;
//                }
//            }else if(t==0){
//                row.add(0);
//            }
//            t=0;
//            p=0;
//            if(spQ3!=null){
//                for(SanPhamDTO sp3 : spQ3){
//                    if(sp3.getMasp().equals(sp.getMasp())){
//                        row.add(sp3.getDongia()); //giá sản phẩm dòng 1
//                        sum+=sp3.getDongia();
//                        p=1;
//                    }
//                }
//                if(p==0){
//                    row.add(0);
//                    t=1;
//                }
//            }else if(t==0){
//                row.add(0);
//            }
//            t=0;
//            p=0;
//            if(spQ4!=null){
//                for(SanPhamDTO sp4 : spQ4){
//                    if(sp4.getMasp().equals(sp.getMasp())){
//                        row.add(sp4.getDongia()); //giá sản phẩm dòng 1
//                        sum+=sp4.getDongia();
//                        p=1;
//                    }
//                }
//                if(p==0){
//                    row.add(0);
//                    t=1;
//                }
//            }else if(t==0){
//                row.add(0);
//            }
//            
//            row.add(sum);
//            tongcong+=sum;
//            modelThongKeSP.addRow(row);
//        }
//        
//            int tong1=0;
//            if(spQ1!=null){
//                for(SanPhamDTO hd1 : spQ1){
//                        tong1+=hd1.getDongia();
//                }           
//            }
//            int tong2=0;
//            if(spQ2!=null){
//                for(SanPhamDTO hd2 : spQ2){
//                        tong2+=hd2.getDongia();
//                }
//            }
//            int tong3=0;
//            if(spQ3!=null){
//                for(SanPhamDTO hd3 : spQ3){
//                        tong3+=hd3.getDongia();
//                }
//            }
//            int tong4=0;
//            if(spQ4!=null){
//                for(SanPhamDTO hd4 : spQ4){
//                        tong4+=hd4.getDongia();
//                }
//            }
//            Vector row1 = new Vector();
//            row1.add("Tổng cộng");
//            row1.add(tong1);
//            row1.add(tong2);
//            row1.add(tong3);
//            row1.add(tong4);
//            row1.add(tongcong);
//            modelThongKeSP.addRow(row1);
//       
//            
//        tblThongKeSP.setModel(modelThongKeSP);}
//    }
    
    public void showThongKeKH(int nam) throws Exception{
        
        Vector header = new Vector();
        header.add("");
        header.add("Qúy 1");
        header.add("Qúy 2");
        header.add("Qúy 3");
        header.add("Qúy 4");
        header.add("Tổng cộng");
        
        modelThongKeKH = new DefaultTableModel(header,0);
        ThongKeBUS thongKe = new ThongKeBUS();
        ArrayList<HoaDonDTO> dshd = thongKe.thongKeDSHDTheoNam(nam);
        
        ArrayList<HoaDonDTO> hdQ1 = thongKe.thongKeKHTheoQuy(nam, 1);
        ArrayList<HoaDonDTO> hdQ2 = thongKe.thongKeKHTheoQuy(nam, 2);
        ArrayList<HoaDonDTO> hdQ3 = thongKe.thongKeKHTheoQuy(nam, 3);
        ArrayList<HoaDonDTO> hdQ4 = thongKe.thongKeKHTheoQuy(nam, 4);
       

        DecimalFormat dcf = new DecimalFormat("###,###");
        int tongcong=0;
        KhachHangBUS khBUS = new KhachHangBUS();
        if(dshd!=null){
        for(HoaDonDTO hd : dshd){
            int sum=0; //tổng tiền khách hàng mua của 4 quý
            Vector row = new Vector();
            KhachHangDTO khDT0 = new KhachHangDTO();
            khDT0 = khBUS.timKiemMaKH(hd.getIdKhachhang());
            row.add(khDT0.getHoKH() + " " + khDT0.getTenKH());  //tên khách hàng
            int t=0;
            int p=0;
            if(hdQ1!=null){
                for(HoaDonDTO hd1 : hdQ1){
                    if(hd1.getIdKhachhang().equals(hd.getIdKhachhang())){
                        row.add(hd1.getTongtienHoadon()); //giá sản phẩm dòng 1
                        sum+=hd1.getTongtienHoadon();
                        p=1;
                    }
                }
                if(p==0){
                    row.add(0);
                    t=1;
                }
            }else if(t==0){
                row.add(0);
            }
            t=0;
            p=0;
            if(hdQ2!=null){
                for(HoaDonDTO hd2 : hdQ2){
                    if(hd2.getIdKhachhang().equals(hd.getIdKhachhang())){
                        row.add(hd2.getTongtienHoadon()); //giá sản phẩm dòng 1
                        sum+=hd2.getTongtienHoadon();
                        p=1;
                    }
                }
                if(p==0){
                    row.add(0);
                    t=1;
                }
            }else if(t==0){
                row.add(0);
            }
            t=0;
            p=0;
            if(hdQ3!=null){
                for(HoaDonDTO hd3 : hdQ3){
                    if(hd3.getIdKhachhang().equals(hd.getIdKhachhang())){
                        row.add(hd3.getTongtienHoadon()); //giá sản phẩm dòng 1
                        sum+=hd3.getTongtienHoadon();
                        p=1;
                    }
                }
                if(p==0){
                    row.add(0);
                    t=1;
                }
            }else if(t==0){
                row.add(0);
            }
            t=0;
            p=0;
            if(hdQ4!=null){
                for(HoaDonDTO hd4 : hdQ4){
                    if(hd4.getIdKhachhang().equals(hd.getIdKhachhang())){
                        row.add(hd4.getTongtienHoadon()); //giá sản phẩm dòng 1
                        sum+=hd4.getTongtienHoadon();
                        p=1;
                    }
                }
                if(p==0){
                    row.add(0);
                    t=1;
                }
            }else if(t==0){
                row.add(0);
            }
            
            row.add(sum);
            tongcong+=sum;
            modelThongKeKH.addRow(row);
        }
        
            int tong1=0;
            if(hdQ4!=null){
                for(HoaDonDTO hd1 : hdQ1){
                        tong1+=hd1.getTongtienHoadon();
                }           
            }
            int tong2=0;
            if(hdQ4!=null){
                for(HoaDonDTO hd2 : hdQ2){
                        tong2+=hd2.getTongtienHoadon();
                }
            }
            int tong3=0;
            if(hdQ4!=null){
                for(HoaDonDTO hd3 : hdQ3){
                        tong3+=hd3.getTongtienHoadon();
                }
            }
            int tong4=0;
            if(hdQ4!=null){
                for(HoaDonDTO hd4 : hdQ4){
                        tong4+=hd4.getTongtienHoadon();
                }
            }
            Vector row1 = new Vector();
            row1.add("Tổng cộng");
            row1.add(tong1);
            row1.add(tong2);
            row1.add(tong3);
            row1.add(tong4);
            row1.add(tongcong);
            modelThongKeKH.addRow(row1);
       
            
        tblThongKeKH.setModel(modelThongKeKH);}
    }
    
    public void showThongKeTopBanChay(int nam) throws Exception{
        ThongKeDTO thongKe = new ThongKeBUS().thongKe(nam);
        Vector header = new Vector();
        header.add("Top");
        header.add("Tên sản phẩm");
        header.add("Đã bán");
        
        modelThongKeTopBanChay = new DefaultTableModel(header,0);
        for(int i=0;i<5;i++){
            Vector row = new Vector();
            row.add(i+1);            
            row.add(thongKe.getTopSanPhamBanChay().get(i).getTensp()); //tên sản phẩm dòng 1
            row.add(thongKe.getTopSanPhamBanChay().get(i).getSoluong());
            modelThongKeTopBanChay.addRow(row);
        }
        tblThongKeTopBanChay.setModel(modelThongKeTopBanChay);   
        
    }
    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; //kiểu String do có số 0 ở phía trước
        String thangKetThuc = "04"; //kiểu String do có số 0 ở phía trước
        String[] kq = new String[2];
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "04";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "07";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "09";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = "01-" + thangBatDau + "-" +  Integer.toString(namBatDau);
        String strKetThuc = "01-" + thangKetThuc + "-" + Integer.toString(namKetThuc);
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }
    
    private ArrayList<SanPhamDTO> thongKeSPTheoQuy(int nam, int quy){ //ds để lấy các mã khuyến mãi trong giỏ hàng
        String dateBD = "";
        String dateKT = "";
        
        if(quy==0){
            dateBD = "01-01-" + nam;
            dateKT = "01-01-" + (nam+1);
        }
        else{
            String[] dateString = getDateString(nam, quy);
            dateBD = dateString[0];
            dateKT = dateString[1];
        }
        
            Date ngayKT = new Date();
            try {
                ngayKT = new SimpleDateFormat("dd/MM/yyyy").parse(dateKT);
            } catch (ParseException ex) {
            }
        
            Date ngayBD = new Date();
            try {
                ngayBD = new SimpleDateFormat("dd/MM/yyyy").parse(dateBD);
            } catch (ParseException ex) {
            }
        Date dayK = ngayKT;//ketthuc
        Date dayB = ngayBD; //batdau
        
        
        ArrayList<HoaDonDTO> dsHD = new ArrayList<>(); 
        ArrayList<ChiTietHoaDonDTO> dsCTHD = new ArrayList<>(); 
        //KT Hoá đơn
        for(HoaDonDTO hd : HoaDonBUS.listHD){
            if (hd.getNgaylapHoadon().compareTo(dayB) >= 0 && dayK.compareTo(hd.getNgaylapHoadon()) >= 0) {
                HoaDonDTO kqHD = new HoaDonDTO();
                kqHD.setIdHoadon(hd.getIdHoadon()); //lấy mã sp thỏa quý
                dsHD.add(kqHD);
            }
        }
        ArrayList<SanPhamDTO> dsSP = new ArrayList<>(); 
        //KT Chi tiết khuyến mãi
        for(HoaDonDTO hd : dsHD){
            for(ChiTietHoaDonDTO cthd : ChiTietHoaDonBUS.listCTHD){
                if(cthd.getMaHD().equals(hd.getIdHoadon())){
                    ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO();
                    ct.setMaHD(cthd.getMaHD());
                    ct.setMaSP(cthd.getMaSP());
                    ct.setTongtien(cthd.getTongtien());
                    dsCTHD.add(ct);
                    
                    if(dsSP.size()==0){
                        SanPhamDTO sp = new SanPhamDTO();
                        sp.setMasp(ct.getMaSP());
                        dsSP.add(sp);
                    }
                    else{
                        int t=0;
                        for(SanPhamDTO sp : dsSP){
                            if(sp.getMasp().equals(ct.getMaSP())){
                                t=1;
                            }
                        }
                        if(t==0){
                            SanPhamDTO sp1 = new SanPhamDTO();
                            sp1.setMasp(ct.getMaSP());
                            dsSP.add(sp1);
                        }
                    }
                }

            }
        }
        int tong=0;
        for (SanPhamDTO sp : dsSP){
            tong=0;
            for(ChiTietHoaDonDTO cthd : dsCTHD){
                if(cthd.getMaSP().equals(sp.getMasp())){
                   tong+= cthd.getTongtien();
                }
            }
            sp.setDongia(tong);
        }
        for(SanPhamDTO a : dsSP){
            System.out.println(a.getMasp() + " " + a.getDongia());
        }
        return dsSP;
    }
    
    
    public void showThongKeTongThu(int nam) throws Exception{
        ThongKeDTO thongKe = new ThongKeBUS().thongKe(nam);

        Vector header = new Vector();
        header.add("");
        header.add("Qúy 1");
        header.add("Qúy 2");
        header.add("Qúy 3");
        header.add("Qúy 4");
       
        modelThongKeTongThu = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
            Vector row = new Vector();
            row.add("Doanh thu");
            row.add(dcf.format(thongKe.getTongThuQuy(1)));
            row.add(dcf.format(thongKe.getTongThuQuy(2)));
            row.add(dcf.format(thongKe.getTongThuQuy(3)));
            row.add(dcf.format(thongKe.getTongThuQuy(4)));
            modelThongKeTongThu.addRow(row);
            
            Vector row1 = new Vector();
            row1.add("Tổng cộng");
            row1.add(dcf.format(thongKe.getTongDoanhThu()));
            
            modelThongKeTongThu.addRow(row1);
        tblThongKeTongThu.setModel(modelThongKeTongThu);
    }
    
    public void showThongKeTongChi(int nam) throws Exception{
        ThongKeDTO thongKe = new ThongKeBUS().thongKe(nam);

        Vector header = new Vector();
        header.add("");
        header.add("Qúy 1");
        header.add("Qúy 2");
        header.add("Qúy 3");
        header.add("Qúy 4");
       
        modelThongKeTongChi = new DefaultTableModel(header,0);
        DecimalFormat dcf = new DecimalFormat("###,###");
            Vector row = new Vector();
            row.add("Tổng chi");
            row.add(dcf.format(thongKe.getTongChiQuy(1)));
            row.add(dcf.format(thongKe.getTongChiQuy(2)));
            row.add(dcf.format(thongKe.getTongChiQuy(3)));
            row.add(dcf.format(thongKe.getTongChiQuy(4)));
            modelThongKeTongChi.addRow(row);
            
            Vector row1 = new Vector();
            row1.add("Tổng cộng");
            row1.add(dcf.format(thongKe.getTongChi()));
            
            modelThongKeTongChi.addRow(row1);
        tblThongKeTongChi.setModel(modelThongKeTongChi);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrameGUI().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MainFrameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser NgayBatDauKM;
    private com.toedter.calendar.JDateChooser NgayKetThucKM;
    private javax.swing.JPanel PanelHienthi;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JLabel anhAdmin;
    private javax.swing.JLabel anhNV;
    private javax.swing.JButton btLamMoiLoaiSP;
    private javax.swing.JButton btLamMoiSP;
    private javax.swing.JButton btLammoiKH;
    private javax.swing.JButton btSuaKH;
    private javax.swing.JButton btSuaLoaiSP;
    private javax.swing.JButton btSuaSP;
    private javax.swing.JButton btThemKH;
    private javax.swing.JButton btThemLoaiSP;
    private javax.swing.JButton btThemSP;
    private javax.swing.JButton btTimKiemKH;
    private javax.swing.JButton btTimKiemLoaiSP;
    private javax.swing.JButton btTimKiemSP;
    private javax.swing.JButton btXoaKH;
    private javax.swing.JButton btXoaLoaiSP;
    private javax.swing.JButton btXoaSP;
    private javax.swing.JButton btnBoLocHD;
    private javax.swing.JButton btnBoLocKM;
    private javax.swing.JButton btnBoLocPN;
    private javax.swing.JButton btnCapNhatKM;
    private javax.swing.JButton btnCapNhatPN;
    private javax.swing.JButton btnChonLoaiSP;
    private javax.swing.JButton btnChonMaKH;
    private javax.swing.JButton btnChonMaKM;
    private javax.swing.JButton btnChonNCC;
    private javax.swing.JButton btnChonSP;
    private javax.swing.JButton btnChonSanPhamCTKM;
    private javax.swing.JButton btnDlgChonLoaiSP;
    private javax.swing.JButton btnDlgSuaLoaiSP;
    private javax.swing.JButton btnDlgThemLoaiSP;
    private javax.swing.JButton btnHuyBH;
    private javax.swing.JButton btnInPN;
    private javax.swing.JButton btnLamMoiNCC;
    private javax.swing.JButton btnLamMoiNV;
    private javax.swing.JButton btnLoaiSP;
    private javax.swing.JButton btnLocHD;
    private javax.swing.JButton btnLocKM;
    private javax.swing.JButton btnLocPN;
    private javax.swing.JButton btnNhapExcel;
    private javax.swing.JButton btnNhapExcelNCC;
    private javax.swing.JButton btnNhapExcelNV;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaCTKM;
    private javax.swing.JButton btnSuaCTPN;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaPN;
    private javax.swing.JButton btnTaoMoiKM;
    private javax.swing.JButton btnTaoMoiPN;
    private javax.swing.JButton btnThanhToanBH;
    private javax.swing.JButton btnThemCTKM;
    private javax.swing.JButton btnThemCTPN;
    private javax.swing.JButton btnThemGioHang;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnTimKiemNCC;
    private javax.swing.JButton btnTimKiemNV;
    private javax.swing.JButton btnXoaCTKM;
    private javax.swing.JButton btnXoaCTPN;
    private javax.swing.JButton btnXoaKM;
    private javax.swing.JButton btnXoaNCC;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btnXoaSPBH;
    private javax.swing.JButton btnXuatExcelKH;
    private javax.swing.JButton btnXuatExcelNCC;
    private javax.swing.JButton btnXuatExcelNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChonLoaiSP;
    private javax.swing.JComboBox<String> cbbChucVuNV;
    private javax.swing.JComboBox<String> cbbNam;
    private javax.swing.JComboBox<String> cbbTimKiemHD;
    private javax.swing.JComboBox<String> cbbTimKiemKH;
    private javax.swing.JComboBox<String> cbbTimKiemKM;
    private javax.swing.JComboBox<String> cbbTimKiemLoaiSP;
    private javax.swing.JComboBox<String> cbbTimKiemNCC;
    private javax.swing.JComboBox<String> cbbTimKiemNV;
    private javax.swing.JComboBox<String> cbbTimKiemPN;
    private javax.swing.JComboBox<String> cbbTimKiemSP;
    private javax.swing.JComboBox<String> cbbTimKiemSPBH;
    private javax.swing.JComboBox<String> cbbTimLoaiSP;
    private javax.swing.JComboBox<String> cbbTrangThaiNV;
    private javax.swing.JComboBox<String> cbbTrangThaiSP;
    private javax.swing.JComboBox<String> cbthangThongke;
    private com.toedter.calendar.JDateChooser denNgayKM;
    private javax.swing.JDialog dlgChonLoaiSP;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbAdmin;
    private javax.swing.JLabel lbBanHangAdmin;
    private javax.swing.JLabel lbBanHangNV;
    private javax.swing.JLabel lbDC;
    private javax.swing.JLabel lbDangXuatAdmin;
    private javax.swing.JLabel lbDangXuatNV;
    private javax.swing.JLabel lbGT;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbHoaDonAdmin;
    private javax.swing.JLabel lbHoaDonNV;
    private javax.swing.JLabel lbHokh;
    private javax.swing.JLabel lbKhachHangAdmin;
    private javax.swing.JLabel lbKhachHangNV;
    private javax.swing.JLabel lbKhuyenMaiAdmin;
    private javax.swing.JLabel lbMakh;
    private javax.swing.JLabel lbNhaCungCapAdmin;
    private javax.swing.JLabel lbNhanVien;
    private javax.swing.JLabel lbNhanVienAdmin;
    private javax.swing.JLabel lbNhapHangAdmin;
    private javax.swing.JLabel lbNhapHangNV;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbSanPhamAdmin;
    private javax.swing.JLabel lbSanPhamNV;
    private javax.swing.JLabel lbTTkhachhang;
    private javax.swing.JLabel lbTenAdmin;
    private javax.swing.JLabel lbTenNhanVien;
    private javax.swing.JLabel lbTenkh;
    private javax.swing.JLabel lbThongKeAdmin;
    private javax.swing.JLabel lbThongKeDoanhThu;
    private javax.swing.JLabel lbThongKeKH;
    private javax.swing.JLabel lbThongKeNV;
    private javax.swing.JLabel lbThongKeSP;
    private javax.swing.JLabel lbThongKeTongChi;
    private javax.swing.JLabel lbThongKeTongDonHang;
    private com.toedter.calendar.JDateChooser ngayBatDauPN;
    private com.toedter.calendar.JDateChooser ngayDenHD;
    private com.toedter.calendar.JDateChooser ngayKetThucPN;
    private com.toedter.calendar.JDateChooser ngayLapBH;
    private com.toedter.calendar.JDateChooser ngayLapHD;
    private com.toedter.calendar.JDateChooser ngayNhap;
    private com.toedter.calendar.JDateChooser ngaySinhNV;
    private com.toedter.calendar.JDateChooser ngayTuHD;
    private javax.swing.JPanel panelBanHang;
    private javax.swing.JPanel panelChinh;
    private javax.swing.JPanel panelChonSP;
    private javax.swing.JPanel panelChuyenSP;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelKhachHang;
    private javax.swing.JPanel panelKhuyenMai;
    private javax.swing.JPanel panelLoaiSP;
    private javax.swing.JPanel panelNhaCungCap;
    private javax.swing.JPanel panelNhanVien;
    private javax.swing.JPanel panelPhieuNhap;
    private javax.swing.JPanel panelSP;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JPanel panelThongKe;
    private javax.swing.JPanel panelTimKiem2;
    private javax.swing.JPanel panelTrangChu;
    private javax.swing.JPanel pnAdmin;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JPanel pnchart;
    private javax.swing.JPanel pnchart1;
    private javax.swing.JPanel pnchart3;
    private javax.swing.JRadioButton rdBtnNamKH;
    private javax.swing.JRadioButton rdBtnNamNV;
    private javax.swing.JRadioButton rdBtnNuKH;
    private javax.swing.JRadioButton rdBtnNuNV;
    private javax.swing.JTable tbLoaisp;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tblBH;
    private javax.swing.JTable tblDSCTHD;
    private javax.swing.JTable tblDSCTKM;
    private javax.swing.JTable tblDSCTPN;
    private javax.swing.JTable tblDSGioHang;
    private javax.swing.JTable tblDSHD;
    private javax.swing.JTable tblDSKH;
    private javax.swing.JTable tblDSKM;
    private javax.swing.JTable tblDSNCC;
    private javax.swing.JTable tblDSNV;
    private javax.swing.JTable tblDSPN;
    private javax.swing.JTable tblKQTKLoaiSP;
    private javax.swing.JTable tblThongKeKH;
    private javax.swing.JTable tblThongKeSPSapHet;
    private javax.swing.JTable tblThongKeTongChi;
    private javax.swing.JTable tblThongKeTongThu;
    private javax.swing.JTable tblThongKeTopBanChay;
    private com.toedter.calendar.JDateChooser tuNgayKM;
    private javax.swing.JTextField txDiaChiKH;
    private javax.swing.JTextField txDiaChiNCC;
    private javax.swing.JTextField txDiaChiNV;
    private javax.swing.JTextField txDieuKienKM;
    private javax.swing.JTextField txDonGiaBH;
    private javax.swing.JTextField txDonGiaCTHD;
    private javax.swing.JTextField txDonGiaCTPN;
    private javax.swing.JTextField txDonGiaSP;
    private javax.swing.JTextField txDonViTinhSP;
    private javax.swing.JTextField txHoNV;
    private javax.swing.JTextField txHokh;
    private javax.swing.JTextField txLoaiSP;
    private javax.swing.JTextField txLuongThangNV;
    private javax.swing.JTextField txMaHD;
    private javax.swing.JTextField txMaHDBH;
    private javax.swing.JTextField txMaKHBH;
    private javax.swing.JTextField txMaKHHD;
    private javax.swing.JTextField txMaKM;
    private javax.swing.JTextField txMaKMBH;
    private javax.swing.JTextField txMaKMHD;
    private javax.swing.JTextField txMaLoai;
    private javax.swing.JTextField txMaNCC;
    private javax.swing.JTextField txMaNCCPN;
    private javax.swing.JTextField txMaNV;
    private javax.swing.JTextField txMaNVBH;
    private javax.swing.JTextField txMaNVHD;
    private javax.swing.JTextField txMaNVPN;
    private javax.swing.JTextField txMaPhieuNhap;
    private javax.swing.JTextField txMaSP;
    private javax.swing.JTextField txMaSPBH;
    private javax.swing.JTextField txMaSPCTHD;
    private javax.swing.JTextField txMaSPCTKM;
    private javax.swing.JTextField txMaSPCTPN;
    private javax.swing.JTextField txMakh;
    private javax.swing.JTextField txPhanTramCTKM;
    private javax.swing.JTextField txSDTKH;
    private javax.swing.JTextField txSDTNCC;
    private javax.swing.JTextField txSDTNV;
    private javax.swing.JTextField txSoLuongBH;
    private javax.swing.JTextField txSoLuongCTHD;
    private javax.swing.JTextField txSoLuongCTPN;
    private javax.swing.JTextField txSoLuongSP;
    private javax.swing.JTextField txTenKM;
    private javax.swing.JTextField txTenLoai;
    private javax.swing.JTextField txTenNCC;
    private javax.swing.JTextField txTenNV;
    private javax.swing.JTextField txTenSP;
    private javax.swing.JTextField txTenSPBH;
    private javax.swing.JTextField txTenkh;
    private javax.swing.JTextField txTimKiemHD;
    private javax.swing.JTextField txTimKiemKH;
    private javax.swing.JTextField txTimKiemKM;
    private javax.swing.JTextField txTimKiemLoaiSP;
    private javax.swing.JTextField txTimKiemNCC;
    private javax.swing.JTextField txTimKiemNV;
    private javax.swing.JTextField txTimKiemPN;
    private javax.swing.JTextField txTimKiemSP;
    private javax.swing.JTextField txTimKiemSPBH;
    private javax.swing.JTextField txTimLoaiSP;
    private javax.swing.JTextField txTongTienBH;
    private javax.swing.JTextField txTongTienCTHD;
    private javax.swing.JTextField txTongTienHD;
    private javax.swing.JTextField txTongTienNhapCTPN;
    // End of variables declaration//GEN-END:variables
}
