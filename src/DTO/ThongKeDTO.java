/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ThongKeDTO {
    public int soLuongSP;
    public int soLuongKH;
    public int soLuongNV;
    public int soLuongDonHang;
    public int thuThang;
    public int chiThang;
    public int[] tongThuQuy;
    public int[] tongChiQuy;
    public ArrayList<SanPhamDTO> topSanPhamBanChay;
    public ArrayList<SanPhamDTO> spSapHetHan;
    public ArrayList<SanPhamDTO> spQuy;
    public ArrayList<HoaDonDTO> khQuy;
    
    public ThongKeDTO() {
    }

    public ThongKeDTO(int soLuongSP, int soLuongKH, int soLuongNV, int soLuongDonHang, int thuThang, int chiThang, int[] tongThuQuy, int[] tongChiQuy, ArrayList<SanPhamDTO> topSanPhamBanChay, ArrayList<SanPhamDTO> spSapHetHan, ArrayList<SanPhamDTO> spQuy, ArrayList<HoaDonDTO> khQuy) {
        this.soLuongSP = soLuongSP;
        this.soLuongKH = soLuongKH;
        this.soLuongNV = soLuongNV;
        this.soLuongDonHang = soLuongDonHang;
        this.thuThang = thuThang;
        this.chiThang = chiThang;
        this.tongThuQuy = tongThuQuy;
        this.tongChiQuy = tongChiQuy;
        this.topSanPhamBanChay = topSanPhamBanChay;
        this.spSapHetHan = spSapHetHan;
        this.spQuy = spQuy;
        this.khQuy = khQuy;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int getSoLuongDonHang() {
        return soLuongDonHang;
    }

    public void setSoLuongDonHang(int soLuongDonHang) {
        this.soLuongDonHang = soLuongDonHang;
    }

    public int getThuThang() {
        return thuThang;
    }

    public void setThuThang(int thuThang) {
        this.thuThang = thuThang;
    }

    public int getChiThang() {
        return chiThang;
    }

    public void setChiThang(int chiThang) {
        this.chiThang = chiThang;
    }

    public int getTongThuQuy(int quy) {
        return tongThuQuy[quy - 1];
    }
    
    
    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }

    public int getTongChiQuy(int quy) {
        return tongChiQuy[quy - 1];
    }

    public void setTongChiQuy(int[] tongChiQuy) {
        this.tongChiQuy = tongChiQuy;
    }

    public ArrayList<SanPhamDTO> getTopSanPhamBanChay() {
        return topSanPhamBanChay;
    }

    public void setTopSanPhamBanChay(ArrayList<SanPhamDTO> topSanPhamBanChay) {
        this.topSanPhamBanChay = topSanPhamBanChay;
    }

    public ArrayList<SanPhamDTO> getSpSapHetHan() {
        return spSapHetHan;
    }

    public void setSpSapHetHan(ArrayList<SanPhamDTO> spSapHetHan) {
        this.spSapHetHan = spSapHetHan;
    }

    public ArrayList<SanPhamDTO> getSpQuy() {
        return spQuy;
    }

    public void setSpQuy(ArrayList<SanPhamDTO> spQuy) {
        this.spQuy = spQuy;
    }

    public ArrayList<HoaDonDTO> getKhQuy() {
        return khQuy;
    }

    public void setKhQuy(ArrayList<HoaDonDTO> khQuy) {
        this.khQuy = khQuy;
    }

    
    public int getTongDoanhThu() {
        int tong = 0;
        for (int i = 0; i < 4; i++) {
            tong += tongThuQuy[i];
        }
        return tong;
    }
    
    public int getTongChi() {
        int tong = 0;
        for (int i = 0; i < 4; i++) {
            tong += tongChiQuy[i];
        }
        return tong;
    }
}
