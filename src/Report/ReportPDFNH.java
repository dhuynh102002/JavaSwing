/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

//import com.itextpdf.io.font.FontConstants;
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

import BUS.ChiTietPhieuNhapBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;



/**
 *
 * @author dhuynh
 */
public class ReportPDFNH {
    //    public ReportPDF(String mahd) throws FileNotFoundException, IOException {
//        init(mahd);
//        File myFile = new File("BillReport/" + mahd + ".pdf");
//
//        Desktop.getDesktop().open(myFile);
//    }
    
    public ReportPDFNH(String mahd) throws Exception{
        try {
            init(mahd);
            File myFile = new File("BillReport/" + mahd + ".pdf");
            
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            //Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Đang tạo hóa đơn");
        }
    }
    public ArrayList<ChiTietPhieuNhapDTO> getListCthd(ArrayList<ChiTietPhieuNhapDTO> list, String id) {
        ArrayList<ChiTietPhieuNhapDTO> lt = new ArrayList<>();
        for (ChiTietPhieuNhapDTO cthd : list) {
            if (cthd.getIdPhieuNhap().equals(id)) {
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
        for (NhanVienDTO sp : list) {
            if (sp.getIdNV().equals(id)) {
                return sp;
            }
        }
        return null;
    }
    public PhieuNhapDTO findHD(String id,ArrayList<PhieuNhapDTO> list){
        for (PhieuNhapDTO pn : list) {
            if (pn.getIdPN().equals(id)) {
                return pn;
            }
        }
        return null;
    }
    public NhaCungCapDTO findNCC(String id,ArrayList<NhaCungCapDTO> list){
        for (NhaCungCapDTO ncc : list) {
            if (ncc.getIdNCC().equals(id)) {
                return ncc;
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
//    public KhuyenmaiDTO findKM(int id,ArrayList<KhuyenmaiDTO> list){
//        for (KhuyenmaiDTO sp : list) {
//            if (sp.getIdKhuyenmaiDTO()== id) {
//                return sp;
//            }
//        }
//        return null;
//    }

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public void init(String mahd) throws FileNotFoundException, IOException, Exception {
        
        ArrayList<PhieuNhapDTO> listPNH = new PhieuNhapBUS().getList();
        ArrayList<ChiTietPhieuNhapDTO> listCPNH = new ChiTietPhieuNhapBUS().getList();
        ArrayList<NhanVienDTO> listNV = new NhanVienBUS().getList();
        ArrayList<SanPhamDTO> listSP = new SanPhamBUS().getList();
        ArrayList<NhaCungCapDTO> listNCC = new NhaCungCapBUS().getList();
        
        
        PhieuNhapDTO hd = findHD(mahd, listPNH);
        
        //khuyenmaibus.docDSKMT(); 
        PdfWriter writer = new PdfWriter("BillReport/"+ mahd + ".pdf");
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
        
        NhaCungCapDTO ncc = findNCC(hd.getIdNCC(),listNCC);
        NhanVienDTO nv = findNV(hd.getIdNhanVien(),listNV);
        Paragraph phieuthanhtoan = new Paragraph("Hóa Đơn Phiếu Nhập Hàng");
        phieuthanhtoan.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph hoadonso = new Paragraph("Phiếu nhập Số: " + hd.getIdPN());
        hoadonso.setFont(font);
        Paragraph khachhang = new Paragraph("Nhà cung cấp: " + hd.getIdNCC()+ " - " + ncc.getTenNCC());
        khachhang.setFont(font);
        Paragraph nhanvien = new Paragraph("Nhân viên: " + hd.getIdNhanVien()+ " - " + nv.getHo()+" "+ nv.getTen());
        nhanvien.setFont(font);
        Paragraph thoigian = new Paragraph("Ngày lập: " + hd.getNgayNhap());
        thoigian.setFont(font);

        document.add(phieuthanhtoan);
        document.add(hoadonso);
        document.add(khachhang);
        document.add(nhanvien);
        document.add(thoigian);
        document.add(row);

        Paragraph phieuthanhtoanct = new Paragraph("Chi Tiết Phiếu Nhập");
        phieuthanhtoanct.setTextAlignment(TextAlignment.CENTER).setFont(font);

        float[] pointColumnWidths = {120F, 85F, 85F, 85F};
        Table table = new Table(pointColumnWidths);
        table.addHeaderCell(new Cell().add("Sản phẩm").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Số lượng").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Đơn giá").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Thành tiền").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        ArrayList<ChiTietPhieuNhapDTO> listct=getListCthd(listCPNH,mahd);
        for (ChiTietPhieuNhapDTO cthd : listct) {
            //table.addCell(new Cell().add(cthd.getTen()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            SanPhamDTO sp = findSP(cthd.getIdSanPham(), listSP);
            
            
            
            table.addCell(new Cell().add(sp.getTensp()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(String.valueOf(cthd.getSoluong())).setTextAlignment(TextAlignment.CENTER).setFont(font));
            //table.addCell(new Cell().add(Chuyentien(String.valueOf(cthd.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(Chuyentien(String.valueOf(sp.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            int thanhtien = sp.getDongia()*cthd.getSoluong();
            table.addCell(new Cell().add(Chuyentien(String.valueOf(thanhtien))).setTextAlignment(TextAlignment.CENTER).setFont(font));
        }

        document.add(phieuthanhtoanct);
        document.add(table);
        Paragraph row2 = new Paragraph("----------------------------------------------");
        row2.setTextAlignment(TextAlignment.CENTER).setFont(font);
        document.add(row2);
        //Paragraph tongsoluong = new Paragraph("Tổng số lượng: " + hd.getSoluongtong());
        //tongsoluong.setFont(font);
        Paragraph tongtien = new Paragraph("Tổng tiền: " + Chuyentien(String.valueOf(hd.getTongsotien())));
        tongtien.setFont(font);
        //Paragraph giamgia = new Paragraph("Mã KM : " + hd.getMakm() + " - Tỉ lệ giảm : " + khuyenmaibus.duyetTile(hd.getMakm()) + "% - Mô tả KM : " + khuyenmaibus.duyetMota(hd.getMakm()));
        //giamgia.setFont(font);
        //Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getThanhtien())));
//        int percent = findKM(hd.getIdKhuyenmaiDTO(), listKM).getPhantramKhuyenmaiDTO();
//        Paragraph tiengiamgia = new Paragraph("Tiền khuyến mãi:"+Chuyentien(String.valueOf(hd.getTongHoadonDTO()/100*percent)));
        
//        tiengiamgia.setFont(font);
        Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getTongsotien())));
        tienphaitra.setFont(font);

        //document.add(tongsoluong);
        document.add(tongtien);
        //document.add(giamgia);
//        document.add(tiengiamgia);
        document.add(tienphaitra);

        document.close();

    }

    public static void main(String[] args) throws IOException, Exception {
        //ReportPDF test = new ReportPDF("HD01");
        ReportPDFNH test = new ReportPDFNH("236");
    }
}
