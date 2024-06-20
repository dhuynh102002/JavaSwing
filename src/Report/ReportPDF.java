/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

//import com.itextpdf.io.font.FontConstants;
import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietKhuyenMaiBUS;
import BUS.HoaDonBUS;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author dhuynh
 */
public class ReportPDF {
    ArrayList<ChiTietHoaDonDTO> dsTienSPgiamgia = new ArrayList<>();
    //    public ReportPDF(String mahd) throws FileNotFoundException, IOException {
//        init(mahd);
//        File myFile = new File("BillReport/" + mahd + ".pdf");
//
//        Desktop.getDesktop().open(myFile);
//    }
    
    public ReportPDF(String mahd) throws Exception{
        try {
            init(mahd);
            File myFile = new File("BillReport/"+ mahd + ".pdf");
            
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            //Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Đang tạo hóa đơn");
        }
    }
    public ArrayList<ChiTietHoaDonDTO> getListCthd(ArrayList<ChiTietHoaDonDTO> list, String id) {
        ArrayList<ChiTietHoaDonDTO> lt = new ArrayList<>();
        for (ChiTietHoaDonDTO cthd : list) {
            if (cthd.getMaHD().equals(id)) {
                lt.add(cthd);
            }
        }
        return lt;
    }
    public SanPhamDTO findSP(String id,ArrayList<SanPhamDTO> list) {
        for (SanPhamDTO sp : list) {
            if (sp.getMasp().equals(id)) {
                return sp;
            }
        }
        return null;
    }
    public NhanVienDTO findNV(String id,ArrayList<NhanVienDTO> list){
        for (NhanVienDTO nv : list) {
            if (nv.getIdNV().equals(id)) {
                return nv;
            }
        }
        return null;
    }
    public HoaDonDTO findHD(String id,ArrayList<HoaDonDTO> list){
        for (HoaDonDTO hd : list) {
            if (hd.getIdHoadon().equals(id)) {
                return hd;
            }
        }
        return null;
    }
    public KhachHangDTO findKH(String id,ArrayList<KhachHangDTO> list){
        for (KhachHangDTO kh : list) {
            if (kh.getMaKH().equals(id)) {
                return kh;
            }
        }
        return null;
    }
    public ChiTietKhuyenMaiDTO findCTKM(String id,ArrayList<ChiTietKhuyenMaiDTO> list){
        for (ChiTietKhuyenMaiDTO ctkm : list) {
            if (ctkm.getMaKM().equals(id)) {
                return ctkm;
            }
        }
        return null;
    }
    
    public int tongTienSP (HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> list){
        int tong=0;
        for(ChiTietHoaDonDTO ct : list){
            if(ct.getMaHD().equals(hd.getIdHoadon())){
                tong+=ct.getTongtien();
            }
        }
        return tong;
    }
    
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
//                        kqCTKM.setPhantramKM(dsctkm.getPhantramKM());
//                        kqCTKM.setMaSP(dsctkm.getMaSP());
                    }
                }
                
            }
        }
        return dsCTKM;   
    }
    
    public int[] tongtien(HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> list){
        int[] tong = new int[4];
        int tongtienHD = tongTienSP(hd, list); //tổng tiền hóa đơn khi chưa trừ khuyến mãi
        tong[0]= tongtienHD;
        
        ArrayList<ChiTietKhuyenMaiDTO> dsctkm = xulyKhuyenMai(hd.getNgaylapHoadon(), tongtienHD, dsSP(hd, list)); //lấy ctkm thỏa 
        String maKM = dsctkm.get(0).getMaKM();
        int tienkm=0;
        int tongtienSP=0;
        int tongtiengiam =0;
        for(ChiTietKhuyenMaiDTO ctkm : dsctkm){
            tienkm=0;
            tongtienSP=0;
            for (ChiTietHoaDonDTO cthd : list){
                if(cthd.getMaSP().equals(ctkm.getMaSP())){
                    tongtienSP = cthd.getTongtien();
                    tongtienHD-=tongtienSP;
                    tienkm = tongtienSP - (tongtienSP * ctkm.getPhantramKM()) / 100;
                    tongtienHD += tienkm;
                    tong[1]=ctkm.getPhantramKM();
                    
                    int tiengiamgia = tongtienSP - tienkm;
                    tongtiengiam+=tiengiamgia;
                    
                    ChiTietHoaDonDTO ct = new ChiTietHoaDonDTO();
                    ct.setMaSP(cthd.getMaSP());
                    ct.setTongtien(tienkm);
                    dsTienSPgiamgia.add(ct);
                }
            }
        }
        
        
        //int tongtiengiam = tongtienSP - tienkm;
        
        tong[2]=tongtiengiam;  //tiền khuyến mãi
        tong[3]=tongtienHD;//tiền phải trả
        //tong[4]=tienkm;
        return tong;
    }
   

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public void init(String mahd) throws FileNotFoundException, IOException, Exception {

        ArrayList<HoaDonDTO> listHD = new HoaDonBUS().getList();
        ArrayList<KhachHangDTO> listKH = new KhachHangBUS().getList();
        ArrayList<ChiTietHoaDonDTO> listCTHD = new ChiTietHoaDonBUS().getList();
        ArrayList<NhanVienDTO> listNV = new NhanVienBUS().getList();
        ArrayList<SanPhamDTO> listSP = new SanPhamBUS().getList();
        ArrayList<KhuyenMaiDTO> listKM = new KhuyenMaiBUS().getList();
        ArrayList<ChiTietKhuyenMaiDTO> listCTKM = new ChiTietKhuyenMaiBUS().getList();
        
        HoaDonDTO hd = findHD(mahd, listHD);
        
        //khuyenmaibus.docDSKMT(); 
         PdfWriter writer = new PdfWriter("BillReport/" + mahd + ".pdf");
        PdfFont font = PdfFontFactory.createFont("font/OpenSans-Regular.ttf", PdfEncodings.IDENTITY_H, true);

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A5);
        PdfPage pdfPage = pdf.addNewPage();

        Paragraph tencuahang = new Paragraph("Siêu thị GS25");
        tencuahang.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph diachi = new Paragraph("273 An Dương Vương, Phường 3, Quận 5, Hồ Chí Minh");
        diachi.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph row = new Paragraph("----------------------------------------------");
        row.setTextAlignment(TextAlignment.CENTER).setFont(font);

        document.add(tencuahang);
        document.add(diachi);
        document.add(row);
        KhachHangDTO kh = findKH(hd.getIdKhachhang(),listKH);
        NhanVienDTO nv = findNV(hd.getIdNhanvien(),listNV);
        Paragraph phieuthanhtoan = new Paragraph("Hóa Đơn Thanh Toán");
        phieuthanhtoan.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph hoadonso = new Paragraph("Hóa Đơn Số: " + hd.getIdHoadon());
        hoadonso.setFont(font);
        Paragraph khachhang = new Paragraph("Khách hàng: " + hd.getIdKhachhang()+ " - " + kh.getHoKH()+" "+kh.getTenKH());
        khachhang.setFont(font);
        Paragraph nhanvien = new Paragraph("Nhân viên: " + hd.getIdNhanvien()+ " - " + nv.getHo()+" "+ nv.getTen());
        nhanvien.setFont(font);
        Paragraph thoigian = new Paragraph("Ngày lập: " + hd.getNgaylapHoadon());
        thoigian.setFont(font);

        document.add(phieuthanhtoan);
        document.add(hoadonso);
        document.add(khachhang);
        document.add(nhanvien);
        document.add(thoigian);
        document.add(row);

        Paragraph phieuthanhtoanct = new Paragraph("Chi Tiết Hóa Đơn");
        phieuthanhtoanct.setTextAlignment(TextAlignment.CENTER).setFont(font);

        
        float[] pointColumnWidths = {120F, 85F, 85F, 85F};
        Table table = new Table(pointColumnWidths);
        table.addHeaderCell(new Cell().add("Sản phẩm").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Số lượng").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Đơn giá").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Thành tiền").setBackgroundColor(Color.GREEN).setTextAlignment(TextAlignment.CENTER).setFont(font));
        ArrayList<ChiTietHoaDonDTO> listct=getListCthd(listCTHD, mahd);
        
        int tongtienHD = tongTienSP(hd, listct); //tổng tiền hóa đơn khi chưa trừ khuyến mãi
        //ArrayList<ChiTietKhuyenMaiDTO> dsctkm = xulyKhuyenMai(hd.getNgaylapHoadon(), tongtienHD, dsSP(hd, listct)); //lấy ctkm thỏa 
        int[] tong = tongtien(hd, listct); //lấy list dsTienSPGiamgia
        
        for (ChiTietHoaDonDTO cthd : listct) {
            //table.addCell(new Cell().add(cthd.getTen()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            SanPhamDTO sp = findSP(cthd.getMaSP(), listSP);
            
            
            
            table.addCell(new Cell().add(sp.getTensp()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(String.valueOf(cthd.getSoluong())).setTextAlignment(TextAlignment.CENTER).setFont(font));
            //table.addCell(new Cell().add(Chuyentien(String.valueOf(cthd.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(Chuyentien(String.valueOf(sp.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            int thanhtien = 0;
            int t=0;
            for(ChiTietHoaDonDTO ct : dsTienSPgiamgia){
                if(cthd.getMaSP().equals(ct.getMaSP())){
                    thanhtien = ct.getTongtien();
                    t=1;
                }
            }
            if(t==0){
                thanhtien = sp.getDongia()*cthd.getSoluong();
            }
            table.addCell(new Cell().add(Chuyentien(String.valueOf(thanhtien))).setTextAlignment(TextAlignment.CENTER).setFont(font));
        }

        document.add(phieuthanhtoanct);
        document.add(table);
        Paragraph row2 = new Paragraph("----------------------------------------------");
        row2.setTextAlignment(TextAlignment.CENTER).setFont(font);
        document.add(row2);
        //Paragraph tongsoluong = new Paragraph("Tổng số lượng: " + hd.getSoluongtong());
        //tongsoluong.setFont(font);
        
//        Paragraph tongtien = new Paragraph("Tổng tiền: " + Chuyentien(String.valueOf(hd.getTongtienHoadon())));
//        tongtien.setFont(font);
        
        
        //Paragraph giamgia = new Paragraph("Mã KM : " + hd.getMakm() + " - Tỉ lệ giảm : " + khuyenmaibus.duyetTile(hd.getMakm()) + "% - Mô tả KM : " + khuyenmaibus.duyetMota(hd.getMakm()));
        //giamgia.setFont(font);
        //Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getThanhtien())));
        
//        int percent = findCTKM(hd.getIdKhuyenmai(), listCTKM).getPhantramKM();
//        Paragraph tongtiengiam = new Paragraph("Tiền khuyến mãi:"+Chuyentien(String.valueOf(hd.getTongtienHoadon()/100*percent)));
//        
//        tongtiengiam.setFont(font);
//        Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getTongtienHoadon()-(hd.getTongtienHoadon()/100*percent))));
//        tienphaitra.setFont(font);
        
        
        Paragraph tongtien = new Paragraph("Tổng tiền: " + Chuyentien(String.valueOf(tong[0])));
        tongtien.setFont(font);
        
        int percent = tong[1];
        Paragraph tiengiamgia = new Paragraph("Tiền khuyến mãi:"+Chuyentien(String.valueOf(tong[2])));
        tiengiamgia.setFont(font);
        
        Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(tong[3])));
        tienphaitra.setFont(font);

        //document.add(tongsoluong);
        document.add(tongtien);
        //document.add(giamgia);
        document.add(tiengiamgia);
        document.add(tienphaitra);

        document.close();

    }

    public static void main(String[] args) throws IOException, Exception {
        //ReportPDF test = new ReportPDF("HD01");
        ReportPDF test = new ReportPDF("4");
    }
}
