-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th5 19, 2022 lúc 05:35 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `maketmini`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `DonGia`, `SoLuong`, `TongTien`) VALUES
('HD001', 'SP004', 6000, 10, 60000),
('HD001', 'SP010', 27500, 5, 137500),
('HD002', 'SP017', 30000, 2, 60000),
('HD002', 'SP015', 21000, 3, 63000),
('HD002', 'SP010', 8000, 4, 32000),
('HD002', 'SP032', 8000, 2, 16000),
('HD003', 'SP009', 27500, 5, 137500),
('HD003', 'SP002', 22000, 10, 220000),
('HD003', 'SP015', 21000, 2, 42000),
('HD004', 'SP032', 8000, 1, 8000),
('HD004', 'SP037', 80000, 1, 80000),
('HD004', 'SP006', 53000, 2, 106000),
('HD005', 'SP001', 21000, 2, 42000),
('HD005', 'SP043', 79000, 2, 158000),
('HD005', 'SP019', 12000, 3, 36000),
('HD006', 'SP030', 39000, 2, 78000),
('HD006', 'SP019', 12000, 3, 36000),
('HD006', 'SP040', 39000, 3, 117000),
('HD007', 'SP006', 53000, 3, 159000),
('HD007', 'SP008', 55000, 1, 55000),
('HD008', 'SP011', 6000, 3, 18000),
('HD008', 'SP003', 24000, 2, 48000),
('HD008', 'SP001', 21000, 1, 21000),
('HD009', 'SP014', 25000, 2, 50000),
('HD009', 'SP049', 339000, 1, 339000),
('HD009', 'SP045', 36000, 1, 36000),
('HD010', 'SP002', 22000, 2, 44000),
('HD010', 'SP004', 6000, 3, 18000),
('HD010', 'SP009', 27500, 1, 27500),
('HD011', 'SP016', 59000, 2, 118000),
('HD011', 'SP017', 30000, 1, 30000),
('HD011', 'SP041', 166000, 1, 166000),
('HD012', 'SP044', 216000, 1, 216000),
('HD012', 'SP038', 1260000, 1, 1260000),
('HD012', 'SP032', 8000, 1, 8000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `MaKM` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `PhanTramKM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`MaKM`, `MaSP`, `PhanTramKM`) VALUES
('KM001', 'SP009', 10),
('KM001', 'SP016', 10),
('KM001', 'SP041', 10),
('KM002', 'SP043', 30),
('KM002', 'SP030', 30),
('KM002', 'SP001', 30),
('KM003', 'SP045', 20),
('KM003', 'SP004', 20),
('KM003', 'SP049', 20),
('KM004', 'SP003', 20),
('KM004', 'SP040', 20),
('KM005', 'SP044', 50),
('KM005', 'SP006', 50),
('KM005', 'SP008', 50),
('KM006', 'SP032', 10),
('KM006', 'SP038', 10),
('KM006', 'SP039', 10),
('KM007', 'SP007', 30);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `ID_PN` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `ID_SP` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(11) NOT NULL,
  `TongTienNhap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`ID_PN`, `ID_SP`, `SoLuong`, `DonGia`, `TongTienNhap`) VALUES
('PN001', 'SP004', 90, 6000, 540000),
('PN001', 'SP009', 120, 27500, 3300000),
('PN002', 'SP001', 30, 21000, 630000),
('PN002', 'SP003', 35, 22000, 770000),
('PN002', 'SP013', 20, 5000, 100000),
('PN003', 'SP004', 50, 6000, 300000),
('PN003', 'SP015', 15, 21000, 315000),
('PN004', 'SP017', 10, 30000, 300000),
('PN004', 'SP034', 10, 35000, 350000),
('PN005', 'SP016', 12, 59000, 708000),
('PN006', 'SP030', 5, 39000, 195000),
('PN006', 'SP011', 10, 6000, 60000),
('PN006', 'SP003', 30, 24000, 720000),
('PN007', 'SP009', 100, 27500, 2750000),
('PN007', 'SP006', 15, 53000, 795000),
('PN008', 'SP004', 80, 6000, 480000),
('PN008', 'SP019', 120, 12000, 1440000),
('PN009', 'SP007', 5, 10000, 50000),
('PN009', 'SP007', 5, 10000, 50000),
('PN011', 'SP003', 3, 24000, 72000),
('PN011', 'SP005', 2, 7000, 14000),
('PN010', 'SP010', 10, 8000, 80000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangnhap`
--

CREATE TABLE `dangnhap` (
  `ID_NV` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenDangNhap` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`ID_NV`, `TenDangNhap`, `MatKhau`) VALUES
('NV001', 'admin', 'admin'),
('NV002', 'nhanvien', 'nhanvien'),
('NV009', 'a', 'a'),
('NV006', 'ha', '123'),
('NV003', 'dung', '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `MaKM` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayLap` date NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `TongTien`) VALUES
('HD001', 'NV005', 'KH003', '', '2022-01-03', 197500),
('HD002', 'NV004', 'KH002', '', '2022-01-12', 171000),
('HD003', 'NV001', 'KH002', 'KM001', '2022-01-30', 399500),
('HD004', 'NV001', 'KH011', '', '2022-02-03', 194000),
('HD005', 'NV001', 'KH003', 'KM002', '2022-02-10', 236000),
('HD006', 'NV001', 'KH017', 'KM002', '2022-02-14', 231000),
('HD007', 'NV001', 'KH006', 'KM002', '2022-02-19', 214000),
('HD008', 'NV001', 'KH001', 'KM002', '2022-03-11', 87000),
('HD009', 'NV001', 'KH002', 'KM003', '2022-03-28', 425000),
('HD010', 'NV001', 'KH006', '', '2022-04-01', 89500),
('HD011', 'NV001', 'KH010', '', '2022-04-05', 314000),
('HD012', 'NV001', 'KH002', 'KM005', '2022-04-11', 1484000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `HoKH` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `TenKH` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` char(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoKH`, `TenKH`, `GioiTinh`, `DiaChi`, `SDT`) VALUES
('KH001', 'Nguyễn Thị', 'Hoa', 'Nữ', 'TP.HCM', '0927827281'),
('KH002', 'Nguyễn Văn', 'Nam', 'Nam', 'Hải Phòng', '0983772777'),
('KH003', 'Nguyễn Thanh', 'Phúc', 'Nam', 'TP.HCM', '0973873873'),
('KH004', 'Hồ Thanh', 'Nhựt', 'Nam', 'Hà Nội', '0393883777'),
('KH005', 'Trần Thùy', 'Dương', 'Nữ', 'TP.HCM', '0399938833'),
('KH006', 'Nguyễn Quang', 'Huy', 'Nam', 'Nam Định', '0973734831'),
('KH007', 'Trần Minh', 'Phú', 'Nam', 'TP.HCM', '0983783722'),
('KH008', 'Lê Thị', 'Phương', 'Nữ', 'Hải Phòng', '0374665462'),
('KH009', 'Lê Tố', 'Hân', 'Nữ', 'Cà Mau', '0928786333'),
('KH010', 'Nguyễn Đại', 'Quang', 'Nam', 'Hà Nội', '0938733834'),
('KH011', 'Nguyễn Như', 'Ý', 'Nữ', 'TP.HCM', '0367267267'),
('KH012', 'Nguyễn Minh', 'Thành', 'Nam', 'Nam Định', '0938737244'),
('KH013', 'Võ Minh', 'Huấn', 'Nam', 'Hà Nội', '0978376522'),
('KH014', 'Trần Mỹ', 'Lệ', 'Nữ', 'Lào Cai', '0382638638'),
('KH015', 'Võ Mỹ', 'Linh', 'Nữ', 'TP.HCM', '0972636733'),
('KH016', 'Võ Minh', 'Toàn', 'Nam', 'Hà Nội', '0927637637'),
('KH017', 'Nguyễn Bảo', 'Hân', 'Nữ', 'Nam Định', '0365675266');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayBD` date NOT NULL,
  `NgayKT` date NOT NULL,
  `DieuKienKM` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `NgayBD`, `NgayKT`, `DieuKienKM`) VALUES
('KM001', 'Sale cuối tuần', '2022-01-30', '2022-02-01', 300000),
('KM002', 'Tri ân khách hàng', '2022-02-10', '2022-02-20', 200000),
('KM003', 'Sale cuối tháng', '2022-03-27', '2022-03-31', 0),
('KM004', 'Sale 20 % cho tất cả các sản phẩm', '2022-04-02', '2022-04-10', 0),
('KM005', 'Sale 50% cho các sản phẩm bánh, kẹo và sữa', '2022-04-03', '2022-04-14', 1000000),
('KM006', 'Giảm giá các loại nước uống và thực phẩm đông lạnh', '2022-05-11', '2022-05-20', 0),
('KM007', 'Sale 50% cho các sản phẩm bánh, kẹo và sữa', '2022-04-30', '2022-05-01', 500000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLoai` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoai` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLoai`, `TenLoai`) VALUES
('ML001', 'Đông Lạnh'),
('ML002', 'Bánh Kẹo'),
('ML003', 'Đồ uống'),
('ML004', 'Đồ Hộp'),
('ML005', 'Gia Vị'),
('ML006', 'Thức ăn nhanh'),
('ML007', 'Thực phẩm khô'),
('ML008', 'Văn phòng phẩm '),
('ML009', 'Mẹ và bé'),
('ML010', 'Gia Dụng'),
('ML011', 'Mỹ Phẩm'),
('ML012', 'Đồ dùng cá nhân'),
('ML013', 'Sữa'),
('ML015', 'a'),
('ML016', 'a');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ID_NCC` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `Ten_NCC` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi_NCC` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `SDT_NCC` char(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`ID_NCC`, `Ten_NCC`, `DiaChi_NCC`, `SDT_NCC`) VALUES
('NCC001', 'Thiên Long', 'Số 7 Đường T4B, Tây Thạnh, Tân Phú, Thành phố Hồ Chí Minh', '0374839281'),
('NCC002', 'Vinamilk', 'Số 10 Tân Trào - Phường Tân Phú - Quận 7 - TP. Hồ Chí Minh', '0378937192'),
('NCC003', 'b', 'b', '0374839284'),
('NCC004', 'a', 'a', 'a'),
('NCC005', 'q', 'q', 'q');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ID` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `Ho` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Ten` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `SDT` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ChucVu` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `LuongThang` int(11) NOT NULL,
  `TrangThai` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`ID`, `Ho`, `Ten`, `GioiTinh`, `NgaySinh`, `SDT`, `DiaChi`, `ChucVu`, `LuongThang`, `TrangThai`) VALUES
('NV001', 'Nguyễn Văn', 'Nam', 'Nam', '2000-10-08', '0354673842', 'TPHCM', 'Quản lý', 10000, 'Hiện hành'),
('NV002', 'Lê Thị Thúy', 'Ngân', 'Nữ', '2000-07-04', '0372847382', 'Hà Nội', 'Nhân viên', 6000, 'Hiện hành'),
('NV003', 'Nguyễn Văn', 'Dũng', 'Nam', '2001-07-11', '0356482942', 'TPHCM', 'Nhân viên', 7000, 'Hiện hành'),
('NV004', 'Trần Minh ', 'Tâm', 'Nam', '2002-02-05', '0384758372', 'TPHCM', 'Quản lý', 12000, 'Hiện hành'),
('NV005', 'Nguyễn Thị', 'Hiền', 'Nữ', '2001-03-11', '0374839273', 'Tây Ninh', 'Nhân viên', 6000, 'Hiện hành'),
('NV006', 'Lê Thanh', 'Hà', 'Nữ', '2002-09-12', '0397362812', 'Hà Nội', 'Quản lý', 10000, 'Hiện hành'),
('NV007', 'Phan Hồng', 'Ngọc', 'Nữ', '2000-01-02', '0366748392', 'THPCM', 'Nhân viên', 7000, 'Hiện hành'),
('NV008', 'Nguyễn Quang', 'Nhựt', 'Nam', '2001-03-24', '0374739292', 'Hà Nội', 'Nhân viên', 6000, 'Hiện hành'),
('NV009', 'Trần Thiên', 'Vũ', 'Nam', '2002-08-17', '0346849283', 'Tây Ninh', 'Nhân viên', 6000, 'Đã nghỉ'),
('NV010', 'Lê Tuyết', 'Văn', 'Nữ', '2001-08-14', '0385930284', 'TPHCM', 'Quản lý', 10000, 'Đã nghỉ'),
('NV011', 'Trần Văn', 'Nghĩa', 'Nam', '2000-07-18', '0354738273', 'Hà Nội', 'Nhân viên', 6000, 'Hiện hành'),
('NV018', 'b', 'b', 'Nữ', '2001-02-08', 'b', 'b', 'Nhân viên', 132000, 'Đã nghỉ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `ID_PN` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `ID_NCC` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `ID_NV` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` date NOT NULL,
  `TongSoTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`ID_PN`, `ID_NCC`, `ID_NV`, `NgayNhap`, `TongSoTien`) VALUES
('PN001', 'NCC001', 'NV003', '2022-03-04', 3840000),
('PN002', 'NCC003', 'NV002', '2022-04-07', 1500000),
('PN003', 'NCC001', 'NV008', '2022-04-12', 615000),
('PN004', 'NCC003', 'NV005', '2022-04-17', 650000),
('PN005', 'NCC002', 'NV009', '2022-04-23', 708000),
('PN006', 'NCC003', 'NV011', '2022-04-28', 975000),
('PN007', 'NCC001', 'NV002', '2022-04-29', 3545000),
('PN008', 'NCC002', 'NV003', '2022-04-30', 1920000),
('PN009', 'NCC002', 'NV003', '2022-04-30', 100000),
('PN010', 'NCC003', 'NV001', '2022-05-12', 80000),
('PN011', 'NCC001', 'NV008', '2022-05-11', 86000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `MaLoai` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `DonGia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DVT` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `MaLoai`, `DonGia`, `SoLuong`, `DVT`, `TrangThai`) VALUES
('SP001', 'Sữa TH true MILK', 'ML013', 21000, 41, 'Lốc', 'Đang bán'),
('SP002', 'Sữa Fami', 'ML013', 22000, 23, 'Lốc', 'Đang bán'),
('SP003', 'Sữa Milo', 'ML013', 24000, 34, 'Lốc', 'Đang bán'),
('SP004', 'Sữa chua Vinamilk', 'ML013', 6000, 279, 'Hộp', 'Đang bán'),
('SP005', 'Sữa Yomost', 'ML013', 7000, 0, 'Hộp', 'Hết hàng'),
('SP006', 'Sữa Yakult(400ml)', 'ML013', 53000, 17, 'Chai', 'Đang bán'),
('SP007', 'Socola Kitkat', 'ML002', 10000, 3, 'Gói', 'Đang bán'),
('SP008', 'Bánh Chocopie', 'ML002', 55000, 8, 'Hộp', 'Đang bán'),
('SP009', 'Bánh AFC', 'ML002', 27500, 233, 'Hộp', 'Đang bán'),
('SP010', 'Bánh OREO', 'ML002', 8000, 13, 'Gói', 'Đang bán'),
('SP011', 'Snack OiShi', 'ML002', 6000, 13, 'Gói', 'Đang bán'),
('SP012', 'Snack Lay\'s', 'ML002', 10000, 0, 'Gói', 'Hết hàng'),
('SP013', 'Kẹo kem dừa', 'ML002', 5, 25, 'Hộp', 'Đang bán'),
('SP014', 'Kẹo bạc hà Socola', 'ML002', 25000, 3, 'Hộp', 'Đang bán'),
('SP015', 'Đường trắng (1kg)', 'ML005', 21000, 9, 'Gói', 'Đang bán'),
('SP016', 'Tiêu đen Phú Quốc (40g)', 'ML005', 59000, 12, 'Hộp', 'Đang bán'),
('SP017', 'Dầu ăn Tường An (1L)', 'ML005', 30000, 11, 'Chai', 'Đang bán'),
('SP018', 'Tương ớt Shinsu', 'ML005', 12000, 0, 'Chai', 'Hết hàng'),
('SP019', 'Bột ngọt (150g)', 'ML005', 12000, 113, 'Gói', 'Đang bán'),
('SP020', 'Thịt hộp Span', 'ML004', 32000, 10, 'Hộp', 'Đang bán'),
('SP021', 'c', 'c', 1, 1, '1', 'Hết hàng'),
('SP030', 'Bò nấu đậu trắng Vissan', 'ML004', 39000, 6, 'Hộp', 'Đang bán'),
('SP031', 'Pepsi', 'ML003', 8000, 0, 'Chai', 'Hết hàng'),
('SP032', 'Cocacola', 'ML003', 8000, 4, 'Chai', 'Đang bán'),
('SP033', 'Redbull', 'ML003', 12000, 0, 'Lon', 'Hết hàng'),
('SP034', 'Tã cho bé', 'ML009', 35000, 11, 'Gói', 'Đang bán'),
('SP035', 'Sữa tắm gội cho bé Cetaphil Ba', 'ML009', 205000, 4, 'Chai', 'Đang bán'),
('SP036', 'Nước giặt quần áo cho bé D-Nee', 'ML009', 17000, 0, 'Can', 'Hết hàng'),
('SP037', 'Cá viên (500g)', 'ML001', 80000, 8, 'Gói', 'Đang bán'),
('SP038', 'Ba rọi bò Mỹ đông lạnh', 'ML001', 1260000, 5, 'Khay', 'Đang bán'),
('SP039', 'Cá hồi phi lê đông lạnh', 'ML001', 86000, 0, 'Khay', 'Hết hàng'),
('SP040', 'Khăn ướt', 'ML012', 39000, 6, 'Bịch', 'Đang bán'),
('SP041', 'Tắm gội X-Men', 'ML012', 166000, 11, 'Chai', 'Đang bán'),
('SP042', 'Kem chống nắng Bioré SPF 50+/P', 'ML011', 62000, 0, 'Chai', 'Hết hàng'),
('SP043', 'Sữa rửa mặt Senka', 'ML011', 79000, 5, 'Chai', 'Đang bán'),
('SP044', 'Nồi lẩu nhôm 26cm', 'ML010', 216000, 3, 'Nồi', 'Đang bán'),
('SP045', 'Cơm gà Cajun Cari', 'ML006', 36000, 9, 'Khay', 'Đang bán'),
('SP046', 'Mỳ ý thịt viên', 'ML006', 39000, 3, 'Khay', 'Đang bán'),
('SP047', 'Bánh tráng Safoco 16cm', 'ML007', 16000, 1, 'Gói', 'Đang bán'),
('SP048', 'Đậu đen Việt San', 'ML007', 27000, 2, 'Gói', 'Đang bán'),
('SP049', 'Bút chì màu Deli', 'ML008', 339000, 1, 'Hộp', 'Đang bán'),
('SP050', 'Thước dẻo', 'ML008', 6000, 15, 'Cây', 'Đang bán'),
('SP051', 'Bộ bình ly thủy tinh', 'ML010', 180000, 3, 'Hộp', 'Đang bán'),
('SP052', 'Hộp nhựa chữ nhật', 'ML010', 28000, 0, 'Hộp', 'Hết hàng');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`ID_NCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`ID_PN`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
