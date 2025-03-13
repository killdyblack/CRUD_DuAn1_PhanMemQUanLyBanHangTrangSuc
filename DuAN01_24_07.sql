create database DUAN01_02
go 
use DUAN01_02
go
CREATE TABLE KhachHang (
    IDKhachHang VARCHAR(6) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(10) NOT NULL,
    DiaChi NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL, 
    TichDiem INT DEFAULT 0,
    NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
    TrangThai BIT DEFAULT 1
);
GO
--trigger KhachHang
-- Trigger trg_UpdateTimestampKH: Cập nhật NgàySua khi có sự thay đổi trong bảng KhachHang
CREATE TRIGGER trg_UpdateTimestampKH
ON KhachHang
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE KhachHang
    SET NgaySua = GETDATE() -- Cập nhật NgàySua thành thời điểm hiện tại
    FROM KhachHang k
    INNER JOIN Inserted i ON k.IDKhachHang = i.IDKhachHang; -- Chi tiết bản ghi thay đổi
END;
GO
-- Sequence KhachHangSeq: Định nghĩa Sequence để sinh số thứ tự cho KhachHang
CREATE SEQUENCE KhachHangSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDKhachHang: Sinh ID tự động cho KhachHang khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDKhachHang
ON KhachHang
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'KH';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR KhachHangSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO KhachHang (IDKhachHang, HoTen, SoDienThoai, DiaChi,  NgayTao, NgaySua, Email,TichDiem, TrangThai)
    SELECT @NewID, HoTen, SoDienThoai, DiaChi,  GETDATE(), GETDATE(),Email, TichDiem, TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO
-- Proc Thêm 
CREATE OR ALTER PROCEDURE dbo.ThemKhachHang
    @HoTen NVARCHAR(100),
    @SoDienThoai VARCHAR(10),
    @DiaChi NVARCHAR(100),
    @Email NVARCHAR(100),
    @TichDiem INT = 0,
    @TrangThai BIT = 1
AS
BEGIN
    IF @Email IS NULL
    BEGIN
        SET @Email = 'default@example.com'; 
    END

    INSERT INTO KhachHang (HoTen, SoDienThoai, DiaChi, Email, TichDiem, NgayTao, NgaySua, TrangThai)
    VALUES (@HoTen, @SoDienThoai, @DiaChi, @Email, @TichDiem, GETDATE(), GETDATE(), @TrangThai);
END;

go
create table TaiKhoan(
	IDTaiKhoan varchar(6) primary key,
	TaiKhoan nvarchar(100) not null,
	MatKhau nvarchar(100) not null,
	HoTen nvarchar(100) not null,
	DiaChi nvarchar(100) not null,
	SoDienThoai varchar(10) not null,
	Email nvarchar(100) not null,
	NgaySinh Date,
	HinhAnh nvarchar(100),
	ChucVu bit not null,
	GioiTinh bit not null,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai bit
)
alter table TaiKhoan
alter column HinhAnh varchar(5000)
GO
--trigger TaiKhoan
CREATE TRIGGER trg_UpdateTimestampTK
ON TaiKhoan
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE TaiKhoan
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE TaiKhoan.IDTaiKhoan= i.IDTaiKhoan;
END;
GO
-- Sequence TaiKhoanSeq: Định nghĩa Sequence để sinh số thứ tự cho TaiKhoan
CREATE SEQUENCE TaiKhoanSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDTaiKhoan: Sinh ID tự động cho TaiKhoan khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDTaiKhoan
ON TaiKhoan
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'TK';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR TaiKhoanSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO TaiKhoan (IDTaiKhoan, TaiKhoan, MatKhau, HoTen, DiaChi, Email, NgaySinh, ChucVu, NgaySua, NgayTao, TrangThai, HinhAnh, SoDienThoai, GioiTinh)
    SELECT @NewID, TaiKhoan, MatKhau, HoTen, DiaChi, Email, NgaySinh, ChucVu, GETDATE(), GETDATE(), TrangThai, HinhAnh, SoDienThoai, GioiTinh
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
go
-- Proc Thêm
CREATE or alter PROCEDURE ThemTaiKhoan
    @TaiKhoan nvarchar(100),
    @MatKhau nvarchar(100),
    @HoTen nvarchar(100),
    @DiaChi nvarchar(100),
    @SoDienThoai varchar(10),
    @Email nvarchar(100),
    @NgaySinh Date = NULL,
    @HinhAnh nvarchar(100) = NULL,
    @ChucVu bit,
    @GioiTinh bit,
    @TrangThai bit = 1
AS
BEGIN
    INSERT INTO TaiKhoan ( TaiKhoan, MatKhau, HoTen, DiaChi, SoDienThoai, Email, NgaySinh, HinhAnh, ChucVu, GioiTinh, NgayTao, NgaySua, TrangThai)
    VALUES ( @TaiKhoan, @MatKhau, @HoTen, @DiaChi, @SoDienThoai, @Email, @NgaySinh, @HinhAnh, @ChucVu, @GioiTinh, GETDATE(), GETDATE(), @TrangThai);
END;
GO
create table KiemDinh(
	IDKiemDinh VARCHAR(6) PRIMARY KEY,
	DonViKiemDinh nvarchar(100) not null,
	NgayKiemDinh Date not null,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai bit not null
)
GO
--trigger KiemDinh
CREATE TRIGGER trg_UpdateTimestampKD
ON KiemDinh
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE KiemDinh
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE KiemDinh.IDKiemDinh= i.IDKiemDinh;
END;
GO
-- Sequence KiemDinhSeq: Định nghĩa Sequence để sinh số thứ tự cho KiemDinh
CREATE SEQUENCE KiemDinhSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDKiemDinh: Sinh ID tự động cho KiemDinh khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDKiemDinh
ON KiemDinh
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'KD';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR KiemDinhSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO KiemDinh (IDKiemDinh, DonViKiemDinh, NgayKiemDinh, NgaySua, NgayTao, TrangThai)
    SELECT @NewID,  DonViKiemDinh, NgayKiemDinh, GETDATE(), GETDATE(), TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO
-- Proc Thêm 
CREATE or alter PROCEDURE ThemKiemDinh
    @DonViKiemDinh NVARCHAR(100),
    @NgayKiemDinh DATE,
    @TrangThai BIT
AS
BEGIN
    INSERT INTO KiemDinh ( DonViKiemDinh, NgayKiemDinh, TrangThai)
    VALUES ( @DonViKiemDinh, @NgayKiemDinh, @TrangThai);
END;
 go
create table Voucher(
	IDVoucher varchar(6) primary key,
	TyLe float,
	TenVoucher nvarchar(100),
	NgayBatDau Date,
	NgayKetThuc Date,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai bit
)

go
CREATE TRIGGER trg_UpdateTimestampVoucher
ON Voucher
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Voucher
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE Voucher.IDVoucher= i.IDVoucher;
END;
GO
-- Sequence VoucherSeq: Định nghĩa Sequence để sinh số thứ tự cho Voucher
CREATE SEQUENCE VoucherSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDVoucher: Sinh ID tự động cho Voucher khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDVoucher
ON Voucher
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'VC';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR VoucherSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO Voucher (IDVoucher,TyLe,TenVoucher,NgayBatDau,NgayKetThuc,NgaySua,TrangThai)
    SELECT @NewID,TyLe,TenVoucher,NgayBatDau,NgayKetThuc,GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
go 
-- Proc Thêm 
CREATE or alter PROCEDURE ThemVoucher
    @TyLe FLOAT,
    @TenVoucher NVARCHAR(100),
    @NgayBatDau DATE,
    @NgayKetThuc DATE,
    @TrangThai BIT
AS
BEGIN
    INSERT INTO Voucher ( TyLe, TenVoucher, NgayBatDau, NgayKetThuc, TrangThai)
    VALUES ( @TyLe, @TenVoucher, @NgayBatDau, @NgayKetThuc, @TrangThai);
END;
GO


CREATE TABLE Size(
	IDSize VARCHAR(6) PRIMARY KEY,
	SoSize INT NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
go 
-- THêm PRoc 
CREATE or alter PROCEDURE ThemSize
    @SoSize INT,
    @TrangThai BIT
AS
BEGIN
    INSERT INTO Size (SoSize, TrangThai)
    VALUES (@SoSize, @TrangThai);
END;

GO
--trigger Size
CREATE TRIGGER trg_UpdateTimestampSize
ON Size
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Size
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE Size.IDSize= i.IDSize;
END;
GO
-- Sequence SizeSeq: Định nghĩa Sequence để sinh số thứ tự cho Size
CREATE SEQUENCE SizeSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDSize: Sinh ID tự động cho Size khi chưa có ID
CREATE or alter TRIGGER trg_GenerateEmployeeIDSize
ON Size
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'sz';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR SizeSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO Size (IDSize,SoSize,NgaySua,NgayTao,TrangThai)
    SELECT @NewID, SoSize,GETDATE(),GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO

CREATE TABLE MauSac(
	IDMauSac VARCHAR(6) PRIMARY KEY,
	ChiTietMauSac NVARCHAR(20),
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
GO
-- Proc Thêm 
CREATE OR ALTER PROCEDURE sp_ThemMauSac
    @ChiTietMauSac NVARCHAR(20),
    @TrangThai BIT
AS
BEGIN
    BEGIN TRY
        -- Thử chèn dữ liệu vào bảng MauSac
        INSERT INTO MauSac (ChiTietMauSac, TrangThai)
        VALUES (@ChiTietMauSac, @TrangThai);

        -- Nếu chèn dữ liệu thành công, thông báo thành công
        PRINT 'Dữ liệu đã được chèn thành công.';
    END TRY
    BEGIN CATCH
        -- Nếu có lỗi xảy ra, thông báo lỗi và không chèn dữ liệu
        PRINT 'Lỗi xảy ra: ' + ERROR_MESSAGE();
        -- Bạn có thể thêm các bước khác để xử lý lỗi nếu cần
    END CATCH
END;
go
--trigger Size
CREATE TRIGGER trg_UpdateTimestampMauSac
ON MauSac
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE MauSac
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE MauSac.IDMauSac= i.IDMauSac;
END;
GO
-- Sequence MauSacSeq: Định nghĩa Sequence để sinh số thứ tự cho MauSac
CREATE SEQUENCE MauSacSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDMauSac: Sinh ID tự động cho MauSac khi chưa có ID
CREATE or alter TRIGGER trg_GenerateEmployeeIDMauSac
ON MauSac
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'MS';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR MauSacSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO MauSac (IDMauSac, ChiTietMauSac, TrangThai)
    SELECT @NewID, ChiTietMauSac, TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
Go


CREATE TABLE ChatLieu(
	IDChatLieu VARCHAR(6) PRIMARY KEY,
	TenChatLieu NVARCHAR(50) NOT NULL,
	TyLe FLOAT NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
go
-- PRoc Thêm 
CREATE or alter PROCEDURE ThemChatLieu
    @TenChatLieu NVARCHAR(50),
    @TyLe FLOAT,
    @TrangThai BIT
AS
BEGIN
    INSERT INTO ChatLieu (TenChatLieu, TyLe, TrangThai)
    VALUES (@TenChatLieu, @TyLe, @TrangThai);
END;

GO
--trigger ChatLieu
CREATE TRIGGER trg_UpdateTimestampCL
ON ChatLieu
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE ChatLieu
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE ChatLieu.IDChatLieu= i.IDChatLieu;
END;
GO
-- Sequence ChatLieuSeq: Định nghĩa Sequence để sinh số thứ tự cho ChatLieu
CREATE SEQUENCE ChatLieuSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDChatLieuc: Sinh ID tự động cho ChatLieu khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDChatLieu
ON ChatLieu
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'CL';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR ChatLieuSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO ChatLieu (IDChatLieu,TenChatLieu,TyLe,NgaySua,NgayTao,TrangThai)
    SELECT @NewID,TenChatLieu,TyLe,GETDATE(),GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO


CREATE TABLE GiamGia(
	IDGiamGia VARCHAR(6) PRIMARY KEY,
	TenMaGiamGia NVARCHAR(50) NOT NULL,
	TyLeGiamGia FLOAT NOT NULL,
	NgayBatDau DATE NOT NULL,
	NgayKetThuc DATE NOT NULL,
	NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
GO
-- Proc Thêm 
CREATE or alter PROCEDURE ThemGiamGia

    @TenMaGiamGia NVARCHAR(50),
    @TyLeGiamGia FLOAT,
    @NgayBatDau DATE,
    @NgayKetThuc DATE,
    @TrangThai BIT
AS
BEGIN
    INSERT INTO GiamGia (TenMaGiamGia, TyLeGiamGia, NgayBatDau, NgayKetThuc, TrangThai)
    VALUES ( @TenMaGiamGia, @TyLeGiamGia, @NgayBatDau, @NgayKetThuc, @TrangThai);
END;

go
--trigger ChatLieu
CREATE TRIGGER trg_UpdateTimestampGG
ON GiamGia
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE GiamGia
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE GiamGia.IDGiamGia= i.IDGiamGia;
END;
GO
-- Sequence GiamGiaSeq: Định nghĩa Sequence để sinh số thứ tự cho GiamGia
CREATE SEQUENCE GiamGiaSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDGiamGia: Sinh ID tự động cho GiamGia khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDGiamGia
ON GiamGia
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'GG';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR GiamGiaSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO GiamGia (IDGiamGia,TenMaGiamGia,TyLeGiamGia,NgayBatDau,NgayKetThuc,NgaySua,TrangThai)
    SELECT @NewID,TenMaGiamGia,TyLeGiamGia,NgayBatDau,NgayKetThuc,GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO


CREATE TABLE NhaCungCap(
	IDNhaCungCap VARCHAR(6) PRIMARY KEY,
	TenNhaCungCap nvarchar(50) not null,
	SoDienThoai varchar(10) not null,
	DiaChi NVARCHAR(255) NOT NULL,
	Email nvarchar(50) not null,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL,
)
go
--Proc Thêm 
CREATE or alter PROCEDURE sp_ThemNhaCungCap 
    @TenNhaCungCap NVARCHAR(50), 
    @SoDienThoai VARCHAR(10), 
    @DiaChi NVARCHAR(255), 
    @Email NVARCHAR(50), 
    @TrangThai BIT 
AS 
BEGIN 
    INSERT INTO NhaCungCap 
    (TenNhaCungCap, SoDienThoai, DiaChi, Email, NgayTao, NgaySua, TrangThai) 
    VALUES 
    ( @TenNhaCungCap, @SoDienThoai, @DiaChi, @Email, GETDATE(), GETDATE(), @TrangThai); 
END


GO

--trigger NguonGoc
CREATE TRIGGER trg_UpdateTimestampNCC
ON NhaCungCap
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE NhaCungCap
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE NhaCungCap.IDNhaCungCap= i.IDNhaCungCap;
END;
GO
-- Sequence NguonGocSeq: Định nghĩa Sequence để sinh số thứ tự cho NguonGoc
CREATE SEQUENCE NhanCungCapSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDGiamGia: Sinh ID tự động cho NguonGoc khi chưa có ID
CREATE or alter TRIGGER trg_GenerateEmployeeIDNhaCungCap
ON NhaCungCap
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'NC';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR NhanCungCapSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO NhaCungCap(IDNhaCungCap,TenNhaCungCap,SoDienThoai,DiaChi,Email,NgayTao,NgaySua,TrangThai)
    SELECT @NewID,TenNhaCungCap,SoDienThoai,DiaChi,Email,GETDATE(),GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO

CREATE TABLE DaQuy(
	IDDaQuy VARCHAR(6) PRIMARY KEY,
	TenDaQuy NVARCHAR(50) NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
go
-- Proc thêm
CREATE or alter PROCEDURE sp_ThemDaQuy 

    @TenDaQuy NVARCHAR(50), 
    @TrangThai BIT 
AS 
BEGIN 
    INSERT INTO DaQuy 
    ( TenDaQuy, NgayTao, NgaySua, TrangThai) 
    VALUES 
    (@TenDaQuy, GETDATE(), GETDATE(), @TrangThai); 
END
GO
--trigger DaQuy
CREATE TRIGGER trg_UpdateTimestampDQ
ON DaQuy
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE DaQuy
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE DaQuy.IDDaQuy= i.IDDaQuy;
END;
GO
-- Sequence DaQuySeq: Định nghĩa Sequence để sinh số thứ tự cho DaQuy
CREATE SEQUENCE DaQuySeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDDaQuy: Sinh ID tự động cho DaQuy khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDDaQuy
ON DaQuy
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'DQ';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR DaQuySeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO DaQuy (IDDaQuy,TenDaQuy,NgaySua,NgayTao,TrangThai)
    SELECT @NewID,TenDaQuy,GETDATE(),GETDATE(),TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
go
create table PhanLoai(
	IDPhanLoai varchar(6) primary key,
	TenPhanLoai nvarchar(50) not null,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
go
--Thêm Proc 
CREATE or alter PROCEDURE sp_ThemPhanLoai 
    @TenPhanLoai NVARCHAR(50), 
    @TrangThai BIT 
AS 
BEGIN 
    INSERT INTO PhanLoai 
    (TenPhanLoai, NgayTao, NgaySua, TrangThai) 
    VALUES 
    ( @TenPhanLoai, GETDATE(), GETDATE(), @TrangThai); 
END

go
CREATE TRIGGER trg_UpdateTimestampPL
ON PhanLoai
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE PhanLoai
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE PhanLoai.IDPhanLoai= i.IDPhanLoai;
END;
GO
-- Sequence PhanLoaiSeQ: Định nghĩa Sequence để sinh số thứ tự cho DaQuy
CREATE SEQUENCE PhanLoaiSeq
START WITH 1
INCREMENT BY 1;
GO
-- Trigger trg_GenerateEmployeeIDDaQuy: Sinh ID tự động cho DaQuy khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDPhanLoai
ON PhanLoai
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'PL';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR PhanLoaiSeq) AS NVARCHAR(4)), 4);

    SET @NewID = @Prefix + @Suffix;

    INSERT INTO PhanLoai (IDPhanLoai, TenPhanLoai, NgayTao, NgaySua, TrangThai)
    SELECT @NewID, TenPhanLoai, GETDATE(), GETDATE(), TrangThai
    FROM inserted;
END;
GO
create table SanPham(
	IDSanPham VARCHAR(6) PRIMARY KEY,
	IDPhanLoai varchar(6) not null,
	IDKiemDinh varchar(6) not null,
	IDMauSac varchar(6) not null,
	IDSize varchar(6) not null,
	TenSanPham nvarchar(100) NOT NULL,
	GioiTinh bit,
	IDChatLieu varchar(6) not null,
	SoLuongTonKho int,
	GiaChiTiet money,
	IDGiamGia varchar(6),
	IDNhaCungCap varchar(6) not null,
	SoLuongDaQuy int,
	KichThuocDa float,
	TrongLuong float,
	HinhAnhSanPham varchar(100),
	IDDaQuy varchar(6),
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai bit NOT NULL
)
go
--Proc Thêm 
CREATE OR ALTER PROCEDURE sp_ThemSanPham
    @IDPhanLoai VARCHAR(6),
    @IDKiemDinh VARCHAR(6),
    @IDMauSac VARCHAR(6),
    @IDSize VARCHAR(8),
    @TenSanPham NVARCHAR(100),
    @GioiTinh BIT,
    @IDChatLieu VARCHAR(6),
    @SoLuongTonKho INT,
    @GiaChiTiet MONEY,
    @IDGiamGia VARCHAR(6),
    @IDNhaCungCap VARCHAR(6),
    @SoLuongDaQuy INT,
    @KichThuocDa FLOAT,
    @TrongLuong FLOAT,
    @HinhAnhSanPham VARCHAR(100),
    @IDDaQuy VARCHAR(6),
    @TrangThai BIT
AS
BEGIN
    INSERT INTO SanPham 
    (
        IDPhanLoai, 
        IDKiemDinh, 
        IDMauSac, 
        IDSize, 
        TenSanPham, 
        GioiTinh, 
        IDChatLieu, 
        SoLuongTonKho, 
        GiaChiTiet, 
        IDGiamGia, 
        IDNhaCungCap, 
        SoLuongDaQuy, 
        KichThuocDa, 
        TrongLuong, 
        HinhAnhSanPham, 
        IDDaQuy, 
        TrangThai
    )
    VALUES 
    (
        @IDPhanLoai, 
        @IDKiemDinh, 
        @IDMauSac, 
        @IDSize, 
        @TenSanPham, 
        @GioiTinh, 
        @IDChatLieu, 
        @SoLuongTonKho, 
        @GiaChiTiet, 
        @IDGiamGia, 
        @IDNhaCungCap, 
        @SoLuongDaQuy, 
        @KichThuocDa, 
        @TrongLuong, 
        @HinhAnhSanPham, 
        @IDDaQuy, 
        @TrangThai
    );
END;
-- Liệt kê tất cả các triggers trên bảng
SELECT * FROM sys.triggers WHERE parent_id = OBJECT_ID('SanPham');

-- Liệt kê tất cả các thủ tục lưu trữ
SELECT * FROM sys.procedures;

go

--trigger SanPham
CREATE TRIGGER trg_UpdateTimestampSP
ON SanPham
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE SanPham
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE SanPham.IDSanPham= i.IDSanPham;
END;
GO
-- Sequence SanPhamSeq: Định nghĩa Sequence để sinh số thứ tự cho SanPham
CREATE SEQUENCE SanPhamSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDSanPham: Sinh ID tự động cho SanPham khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDSanPham
ON SanPham
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'SP';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR SanPhamSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO SanPham (IDSanPham,IDPhanLoai,IDKiemDinh,IDMauSac,IDSize,TenSanPham,GioiTinh,IDChatLieu,SoLuongTonKho,GiaChiTiet,
	IDGiamGia,IDNhaCungCap,TrangThai,SoLuongDaQuy,KichThuocDa,TrongLuong,HinhAnhSanPham,IDDaQuy,NgaySua,NgayTao)
    SELECT @NewID, IDPhanLoai,IDKiemDinh,IDMauSac,IDSize,TenSanPham,GioiTinh,IDChatLieu,SoLuongTonKho,GiaChiTiet,
	IDGiamGia,IDNhaCungCap,TrangThai,SoLuongDaQuy,KichThuocDa,TrongLuong,HinhAnhSanPham,IDDaQuy,GETDATE(),GETDATE()
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO

CREATE TABLE HoaDon(
	IDHoaDon VARCHAR(6) PRIMARY KEY,
	IDKhachHang VARCHAR(6) NOT NULL,
	IDTaiKhoan VARCHAR(6) NOT NULL,
	IDVoucher VARCHAR(6) NOT NULL,
	TongTienTruoc MONEY NOT NULL,
	TongTienSau MONEY NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL,
	TrangThaiTichDiem BIT
)
go 
CREATE OR ALTER PROCEDURE sp_ThemHoaDon
    @IDKhachHang VARCHAR(6),
    @IDTaiKhoan VARCHAR(6),
    @IDVoucher VARCHAR(6),
    @TongTienTruoc MONEY,
    @TongTienSau MONEY,
    @TrangThai BIT,
    @TrangThaiTichDiem BIT
AS
BEGIN
    -- Bắt đầu giao dịch
    BEGIN TRANSACTION;

    BEGIN TRY
        -- Kiểm tra xem IDKhachHang có tồn tại trong bảng KhachHang không
        IF NOT EXISTS (SELECT 1 FROM KhachHang WHERE IDKhachHang = @IDKhachHang)
        BEGIN
            -- Nếu không tồn tại, báo lỗi và rollback giao dịch
            RAISERROR('IDKhachHang không tồn tại trong bảng KhachHang.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Kiểm tra xem IDTaiKhoan có tồn tại trong bảng TaiKhoan không (nếu cần)
        IF @IDTaiKhoan IS NOT NULL AND NOT EXISTS (SELECT 1 FROM TaiKhoan WHERE IDTaiKhoan = @IDTaiKhoan)
        BEGIN
            -- Nếu không tồn tại, báo lỗi và rollback giao dịch
            RAISERROR('IDTaiKhoan không tồn tại trong bảng TaiKhoan.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Kiểm tra xem IDVoucher có tồn tại trong bảng Voucher không (nếu cần)
        IF @IDVoucher IS NOT NULL AND NOT EXISTS (SELECT 1 FROM Voucher WHERE IDVoucher = @IDVoucher)
        BEGIN
            -- Nếu không tồn tại, báo lỗi và rollback giao dịch
            RAISERROR('IDVoucher không tồn tại trong bảng Voucher.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Chèn dữ liệu vào bảng HoaDon
        INSERT INTO HoaDon
        (IDKhachHang, IDTaiKhoan, IDVoucher, TongTienTruoc, TongTienSau, NgayTao, NgaySua, TrangThai, TrangThaiTichDiem)
        VALUES
        (@IDKhachHang, @IDTaiKhoan, @IDVoucher, @TongTienTruoc, @TongTienSau, GETDATE(), GETDATE(), @TrangThai, @TrangThaiTichDiem);
        
        -- Commit giao dịch nếu không có lỗi
        COMMIT TRANSACTION;
        PRINT 'Hóa đơn đã được thêm thành công.';
    END TRY
    BEGIN CATCH
        -- Nếu có lỗi xảy ra, rollback giao dịch
        ROLLBACK TRANSACTION;

        -- Báo lỗi
        PRINT 'Lỗi xảy ra khi thêm hóa đơn: ' + ERROR_MESSAGE();
    END CATCH
END;
go
CREATE or alter TRIGGER chkTichDiem2
ON HoaDon
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    ;WITH TichDiemUpdate AS (
        SELECT 
            HD.IDKhachHang,
            SUM(HD.TongTienTruoc) AS TongTienTruoc
        FROM HoaDon HD
        WHERE HD.TrangThai = 1
        GROUP BY HD.IDKhachHang
    )
    UPDATE K
    SET TichDiem = CASE 
                        WHEN TDU.TongTienTruoc IS NOT NULL 
                        THEN FLOOR(TDU.TongTienTruoc / 100000.0)
                        ELSE 0 
                    END
    FROM KhachHang K
    LEFT JOIN TichDiemUpdate TDU ON K.IDKhachHang = TDU.IDKhachHang;
END;
GO


Go
ALTER TABLE HoaDon ADD CONSTRAINT FK_KhachHang_HoaDon FOREIGN KEY(IDKhachHang) REFERENCES KhachHang(IDKhachHang)
Go
ALTER TABLE HoaDon ADD CONSTRAINT FK_TaiKhoan_HoaDon FOREIGN KEY(IDTaiKhoan) REFERENCES TaiKhoan(IDTaiKhoan)
GO
ALTER TABLE HoaDon ADD CONSTRAINT FK_Voucher FOREIGN KEY(IDVoucher) REFERENCES Voucher(IDVoucher)
GO
--trigger HoaDon
CREATE TRIGGER trg_UpdateTimestampHD
ON HoaDon
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE HoaDon
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE HoaDon.IDHoaDon= i.IDHoaDon;
END;
GO
-- Sequence HoaDonSeq: Định nghĩa Sequence để sinh số thứ tự cho HoaDon
CREATE SEQUENCE HoaDonSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDHoaDon: Sinh ID tự động cho HoaDon khi chưa có ID
CREATE TRIGGER trg_GenerateEmployeeIDHoaDon
ON HoaDon
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'HD';
    DECLARE @Suffix NVARCHAR(4);

    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR HoaDonSeq) AS NVARCHAR(4)), 4); -- Sinh số thứ tự duy nhất

    SET @NewID = @Prefix + @Suffix; -- Ghép tiền tố và hậu tố để tạo ID mới

    INSERT INTO HoaDon (IDHoaDon,IDKhachHang,IDTaiKhoan,IDVoucher,TongTienTruoc,TongTienSau,NgaySua,NgayTao,TrangThaiTichDiem,TrangThai)
    SELECT @NewID,IDKhachHang,IDTaiKhoan,IDVoucher,TongTienTruoc,TongTienSau,GETDATE(),GETDATE(),TrangThaiTichDiem,TrangThai
    FROM inserted; -- Chèn bản ghi mới với ID đã sinh
END;
GO
CREATE TABLE HoaDonChiTiet(
	IDHoaDonChiTiet VARCHAR(8) PRIMARY KEY,
	IDSanPham VARCHAR(6) NOT NULL,
	IDHoaDon VARCHAR(6) NOT NULL,
	SoLuongSanPham TINYINT NOT NULL,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL,
	InsertCount int
)
select * from HoaDonChiTiet
GO
-- Thêm Proc 
CREATE OR ALTER PROCEDURE sp_ThemHoaDonChiTiet
    @IDSanPham VARCHAR(6),
    @IDHoaDon VARCHAR(6),
    @SoLuongSanPham TINYINT,
    @TrangThai BIT
AS
BEGIN
    -- Thêm bản ghi mới vào bảng HoaDonChiTiet
    INSERT INTO HoaDonChiTiet
    (IDSanPham, IDHoaDon, SoLuongSanPham, NgayTao, NgaySua, TrangThai)
    VALUES
    (@IDSanPham, @IDHoaDon, @SoLuongSanPham, GETDATE(), GETDATE(), @TrangThai);
END;
GO
ALTER TABLE HoaDonChiTiet ADD CONSTRAINT FK_SanPham_HoaDonChiTiet FOREIGN KEY(IDSanPham) REFERENCES SanPham(IDSanPham)
GO
ALTER TABLE HoaDonChiTiet ADD CONSTRAINT FK_HoaDon_HoaDonChiTiet FOREIGN KEY(IDHoaDon) REFERENCES HoaDon(IDHoaDon)
GO
--trigger HoaDonChiTiet
CREATE or alter TRIGGER trg_UpdateTimestampHDCT
ON HoaDonChiTiet
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật trường NgaySua với ngày giờ hiện tại khi bản ghi được cập nhật
    UPDATE HoaDonChiTiet
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE HoaDonChiTiet.IDHoaDonChiTiet = i.IDHoaDonChiTiet;
END;
GO
-- Sequence HoaDonChiTietSeq: Định nghĩa Sequence để sinh số thứ tự cho HoaDonChiTiet
CREATE SEQUENCE HoaDonChiTietSeq
START WITH 1
INCREMENT BY 1;
GO

-- Tạo Trigger trg_GenerateIDHoaDonChiTiet để tự động sinh ID mới khi chưa có ID
CREATE OR ALTER TRIGGER trg_GenerateIDHoaDonChiTiet
ON HoaDonChiTiet
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(8);
    DECLARE @Prefix NVARCHAR(4) = 'HDCT';
    DECLARE @Suffix NVARCHAR(4);

    -- Sinh số thứ tự duy nhất từ sequence
    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR HoaDonChiTietSeq) AS NVARCHAR(4)), 4);

    -- Ghép tiền tố và hậu tố để tạo ID mới
    SET @NewID = @Prefix + @Suffix;

    -- Chèn bản ghi mới với ID đã sinh
    INSERT INTO HoaDonChiTiet (IDHoaDonChiTiet, IDSanPham, IDHoaDon, SoLuongSanPham, NgayTao, NgaySua, TrangThai)
    SELECT @NewID, IDSanPham, IDHoaDon, SoLuongSanPham, GETDATE(), GETDATE(), TrangThai
    FROM Inserted;
END;
GO
create table BaoHanh(
	IDBaoHanh varchar(6) primary key,
	IDKhachHang varchar(6)not null,
	SerialNumber varchar(6) not null,
	IDSanPham varchar(6) not null,
	NgayHenTra Date,
	IDHoaDonChiTiet varchar(8) not null,
	NgayYeuCau date not null,
	GhiChu nvarchar(100) not null,
	NgayTao DATETIME DEFAULT GETDATE(),
    NgaySua DATETIME DEFAULT GETDATE(),
	TrangThai BIT NOT NULL
)
go
-- Thêm Proc 
CREATE OR ALTER PROCEDURE sp_ThemBaoHanh
    @IDKhachHang VARCHAR(6),
    @SerialNumber VARCHAR(6),
    @IDSanPham VARCHAR(6),
    @IDHoaDonChiTiet VARCHAR(8),
    @NgayYeuCau DATE,
    @GhiChu NVARCHAR(100),
    @TrangThai BIT
AS
BEGIN
    INSERT INTO BaoHanh (IDKhachHang, SerialNumber, IDSanPham, IDHoaDonChiTiet, NgayYeuCau, GhiChu, NgayTao, NgaySua, TrangThai)
    VALUES (@IDKhachHang, @SerialNumber, @IDSanPham, @IDHoaDonChiTiet, @NgayYeuCau, @GhiChu, GETDATE(), GETDATE(), @TrangThai);
END;

go
--trigger BaoHanh
CREATE OR ALTER TRIGGER trg_UpdateTimestampBH
ON BaoHanh
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE BaoHanh
    SET NgaySua = GETDATE()
    FROM Inserted i
    WHERE BaoHanh.IDBaoHanh = i.IDBaoHanh;
END;
GO


-- Sequence BaoHanhtSeq: Định nghĩa Sequence để sinh số thứ tự cho BaoHanh
CREATE SEQUENCE BaoHanhSeq
START WITH 1
INCREMENT BY 1;
GO

-- Trigger trg_GenerateEmployeeIDBaoHanh: Sinh ID tự động cho BaoHanh khi chưa có ID
CREATE OR ALTER TRIGGER trg_GenerateEmployeeIDBaoHanh
ON BaoHanh
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(6);
    DECLARE @Prefix NVARCHAR(2) = 'BH';
    DECLARE @Suffix NVARCHAR(4);

    -- Sinh số thứ tự duy nhất
    SELECT @Suffix = RIGHT('0000' + CAST((NEXT VALUE FOR BaoHanhSeq) AS NVARCHAR(4)), 4);

    -- Ghép tiền tố và hậu tố để tạo ID mới
    SET @NewID = @Prefix + @Suffix;

    INSERT INTO BaoHanh (IDBaoHanh, IDKhachHang, SerialNumber, IDSanPham, IDHoaDonChiTiet, NgayYeuCau, GhiChu, NgaySua, NgayTao, TrangThai)
    SELECT @NewID, IDKhachHang, SerialNumber, IDSanPham, IDHoaDonChiTiet, NgayYeuCau, GhiChu, GETDATE(), GETDATE(), TrangThai
    FROM Inserted;
END;
GO

-- viết trigger tự insert serinumber
CREATE TABLE SerialNumber (
    IDHoaDonChiTiet VARCHAR(8),
    IDSanPham VARCHAR(6),
    SerialNumber VARCHAR(6),
	thoihanbaohanh date,
	trangthai bit default 1,

    PRIMARY KEY (IDHoaDonChiTiet, IDSanPham, SerialNumber),
    FOREIGN KEY (IDHoaDonChiTiet) REFERENCES HoaDonChiTiet (IDHoaDonChiTiet),
    FOREIGN KEY (IDSanPham) REFERENCES SanPham (IDSanPham)
);

go
--Tự Cập Nhật Trạng Thái Bảo Hành
CREATE OR ALTER TRIGGER trg_UpdateStatusOnExpiry
ON SerialNumber
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật trạng thái về 0 nếu thoihanbaohanh nhỏ hơn ngày hôm nay
    UPDATE SerialNumber
    SET trangthai = 0
    FROM SerialNumber s
    INNER JOIN Inserted i
    ON s.IDHoaDonChiTiet = i.IDHoaDonChiTiet
    AND s.IDSanPham = i.IDSanPham
    AND s.SerialNumber = i.SerialNumber
    WHERE i.thoihanbaohanh < GETDATE()
    AND s.trangthai = 1; -- Chỉ cập nhật các bản ghi có trạng thái 1 (hoạt động)
END;
GO

go
CREATE OR ALTER TRIGGER trg_InsertSerialNumber
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN
    -- Biến để lưu tiền tố số seri
    DECLARE @serialPrefix VARCHAR(6) = 'SN';

    -- Tạo bảng tạm để lưu các giá trị từ bảng inserted
    CREATE TABLE #InsertedTemp (
        IDHoaDonChiTiet VARCHAR(8),
        IDSanPham VARCHAR(6),
        SoLuongSanPham INT
    );

    -- Chèn các giá trị từ bảng inserted vào bảng tạm
    INSERT INTO #InsertedTemp (IDHoaDonChiTiet, IDSanPham, SoLuongSanPham)
    SELECT IDHoaDonChiTiet, IDSanPham, SoLuongSanPham
    FROM inserted;

    -- Chèn số seri vào bảng SerialNumber cho mỗi bản ghi mới trong bảng HoaDonChiTiet
    INSERT INTO SerialNumber (IDHoaDonChiTiet, IDSanPham, SerialNumber, ThoiHanBaoHanh, TrangThai)
    SELECT 
        it.IDHoaDonChiTiet,
        it.IDSanPham,
        -- Tạo số seri duy nhất cho từng sản phẩm
        @serialPrefix + RIGHT('0000' + CAST(ROW_NUMBER() OVER (PARTITION BY it.IDHoaDonChiTiet ORDER BY it.IDHoaDonChiTiet, numbers.rn) AS VARCHAR(4)), 4),
        DATEADD(YEAR, 1, GETDATE()),  -- Giả định thời hạn bảo hành là 1 năm từ ngày hiện tại
        1  -- Mặc định trạng thái là 1
    FROM 
        #InsertedTemp it
    CROSS JOIN 
        (SELECT TOP (SELECT MAX(SoLuongSanPham) FROM #InsertedTemp) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS rn FROM master..spt_values) AS numbers
    WHERE 
        it.SoLuongSanPham > 0
    ORDER BY 
        it.IDHoaDonChiTiet, it.IDSanPham, numbers.rn;

    -- Xóa bảng tạm
    DROP TABLE #InsertedTemp;
END;

ALTER TABLE SanPham ADD CONSTRAINT FK_PhanLoai FOREIGN KEY(IDPhanLoai) REFERENCES PhanLoai(IDPhanLoai)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_KiemDinh FOREIGN KEY(IDKiemDinh) REFERENCES KiemDinh(IDKiemDinh)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_MauSac FOREIGN KEY(IDMauSac) REFERENCES MauSac(IDMauSac)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_Size FOREIGN KEY(IDSize) REFERENCES Size(IDSize)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_ChatLieu FOREIGN KEY(IDChatLieu) REFERENCES ChatLieu(IDChatLieu)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_GiamGia FOREIGN KEY(IDGiamGia) REFERENCES GiamGia(IDGiamGia)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_NhaCungCap FOREIGN KEY(IDNhaCungCap) REFERENCES NhaCungCap(IDNhaCungCap)
go
ALTER TABLE SanPham ADD CONSTRAINT FK_DaQuy FOREIGN KEY(IDDaQuy) REFERENCES DaQuy(IDDaQuy)
GO
alter table BaoHanh
add CONSTRAINT FK_KhachHang_BaoHanh FOREIGN KEY (IDKhachHang) REFERENCES KhachHang(IDKhachHang)
GO
alter table BaoHanh
add CONSTRAINT FK_HoaDonChiTiet_BaoHanh FOREIGN KEY (IDHoaDonChiTiet) REFERENCES HoaDonChiTiet(IDHoaDonChiTiet)
GO
alter table BaoHanh
add CONSTRAINT FK_SanPham_BaoHanh FOREIGN KEY (IDSanPham) REFERENCES SanPham(IDSanPham)
GO
alter table BaoHanh
add CONSTRAINT FK_SerialNumber_BaoHanh FOREIGN KEY (IDHoaDonChiTiet, IDSanPham, SerialNumber)
REFERENCES SerialNumber(IDHoaDonChiTiet, IDSanPham, SerialNumber)
go
-- Thêm tài khoản thứ nhất
EXEC ThemTaiKhoan 
    @TaiKhoan = N'taikhoan1',
    @MatKhau = N'password1',
    @HoTen = N'Nguyen Van A',
    @DiaChi = N'123 Le Loi, Q.1, TP. HCM',
    @SoDienThoai = '0123456789',
    @Email = N'nguyenvana@example.com',
    @NgaySinh = '1990-01-01',
    @HinhAnh = N'image1.jpg',
    @ChucVu = 1,
    @GioiTinh = 1;
GO

-- Thêm tài khoản thứ hai
EXEC ThemTaiKhoan 
    @TaiKhoan = N'taikhoan2',
    @MatKhau = N'password2',
    @HoTen = N'Tran Thi B',
    @DiaChi = N'456 Tran Hung Dao, Q.5, TP. HCM',
    @SoDienThoai = '0987654321',
    @Email = N'tranthib@example.com',
    @NgaySinh = '1985-05-05',
    @HinhAnh = N'image2.jpg',
    @ChucVu = 0,
    @GioiTinh = 0;
GO

-- Thêm tài khoản thứ ba
EXEC ThemTaiKhoan 
    @TaiKhoan = N'taikhoan3',
    @MatKhau = N'password3',
    @HoTen = N'Le Minh C',
    @DiaChi = N'789 Nguyen Trai, Q.10, TP. HCM',
    @SoDienThoai = '0912345678',
    @Email = N'leminhc@example.com',
    @NgaySinh = '1995-07-07',
    @HinhAnh = N'image3.jpg',
    @ChucVu = 1,
    @GioiTinh = 1;
GO
-- Thêm khách hàng thứ nhất
EXEC dbo.ThemKhachHang 
    @HoTen = N'Nguyen Van A',
    @SoDienThoai = '0123456789',
    @DiaChi = N'123 Le Loi, Q.1, TP. HCM',
    @Email = Null;
GO

-- Thêm khách hàng thứ hai
EXEC dbo.ThemKhachHang 
    @HoTen = N'Tran Thi B',
    @SoDienThoai = '0987654321',
    @DiaChi = N'456 Tran Hung Dao, Q.5, TP. HCM',
    @Email = N'tranthib@example.com';
GO
-- Thêm khách hàng thứ ba
EXEC dbo.ThemKhachHang 
    @HoTen = N'Le Minh C',
    @SoDienThoai = '0912345678',
    @DiaChi = N'789 Nguyen Trai, Q.10, TP. HCM',
    @Email = N'leminhc@example.com';
GO
-- Thêm kiểm định thứ nhất
EXEC ThemKiemDinh
    @DonViKiemDinh = N'Trung tâm PNJ Lab',
    @NgayKiemDinh = '2024-07-22',
    @TrangThai = 1;
GO
-- Thêm kiểm định thứ hai
EXEC ThemKiemDinh
    @DonViKiemDinh = N'Trung tâm kiểm định SJC',
    @NgayKiemDinh = '2024-07-23',
    @TrangThai = 1;
GO

-- Thêm kiểm định thứ ba
EXEC ThemKiemDinh
    @DonViKiemDinh = N'DOJILab',
    @NgayKiemDinh = '2024-07-24',
    @TrangThai = 1;
GO
-- Thêm kích thước thứ nhất
EXEC ThemSize
    @SoSize = 38,
    @TrangThai = 1;
GO

-- Thêm kích thước thứ hai
EXEC ThemSize
    @SoSize = 39,
    @TrangThai = 1;
GO

-- Thêm kích thước thứ ba
EXEC ThemSize
    @SoSize = 40,
    @TrangThai = 1;
GO
-- Thêm màu sắc thứ nhất
EXEC sp_ThemMauSac
    @ChiTietMauSac = N'Đỏ',
    @TrangThai = 1;
GO

-- Thêm màu sắc thứ hai
EXEC sp_ThemMauSac
    @ChiTietMauSac = N'Xanh',
    @TrangThai = 1;
GO

-- Thêm màu sắc thứ ba
EXEC sp_ThemMauSac
    @ChiTietMauSac = N'Vàng',
    @TrangThai = 1;
GO
-- Thêm đá quý thứ nhất
EXEC sp_ThemDaQuy
    @TenDaQuy = N'Đá Ruby',
    @TrangThai = 1;
GO

-- Thêm đá quý thứ hai
EXEC sp_ThemDaQuy
    @TenDaQuy = N'Đá Sapphire',
    @TrangThai = 1;
GO

-- Thêm đá quý thứ ba
EXEC sp_ThemDaQuy
    @TenDaQuy = N'Đá Emerald',
    @TrangThai = 1;
GO
-- Thêm nhà cung cấp thứ nhất
EXEC sp_ThemNhaCungCap
    @TenNhaCungCap = N'NCC A',
    @SoDienThoai = '0123456789',
    @DiaChi = N'Số 789, Đường DEF, Quận 3, TP.HCM',
    @Email = N'ncca@example.com',
    @TrangThai = 1;
GO

-- Thêm nhà cung cấp thứ hai
EXEC sp_ThemNhaCungCap
    @TenNhaCungCap = N'NCC B',
    @SoDienThoai = '0987654321',
    @DiaChi = N'Số 456, Đường GHI, Quận 5, TP.HCM',
    @Email = N'nccb@example.com',
    @TrangThai = 1;
GO

-- Thêm nhà cung cấp thứ ba
EXEC sp_ThemNhaCungCap
    @TenNhaCungCap = N'NCC C',
    @SoDienThoai = '0912345678',
    @DiaChi = N'Số 123, Đường JKL, Quận 10, TP.HCM',
    @Email = N'nccc@example.com',
    @TrangThai = 1;
GO
-- Thêm phân loại thứ nhất
EXEC sp_ThemPhanLoai
    @TenPhanLoai = N'Nhẫn',
    @TrangThai = 1;
GO

-- Thêm phân loại thứ hai
EXEC sp_ThemPhanLoai
    @TenPhanLoai = N'Dây chuyền',
    @TrangThai = 1;
GO

-- Thêm phân loại thứ ba
EXEC sp_ThemPhanLoai
    @TenPhanLoai = N'Lắc tay',
    @TrangThai = 1;
GO
-- Thêm giảm giá thứ nhất
EXEC ThemGiamGia
    @TenMaGiamGia = N'GG20',
    @TyLeGiamGia = 20.0,
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO

-- Thêm giảm giá thứ hai
EXEC ThemGiamGia
    @TenMaGiamGia = N'GG30',
    @TyLeGiamGia = 30.0,
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO

-- Thêm giảm giá thứ ba
EXEC ThemGiamGia
    @TenMaGiamGia = N'GG40',
    @TyLeGiamGia = 40.0,
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO
-- Thêm chất liệu thứ nhất
EXEC ThemChatLieu
    @TenChatLieu = N'Vàng',
    @TyLe = 75.0,
    @TrangThai = 1;
GO

-- Thêm chất liệu thứ hai
EXEC ThemChatLieu
    @TenChatLieu = N'Bạc',
    @TyLe = 92.5,
    @TrangThai = 1;
GO

-- Thêm chất liệu thứ ba
EXEC ThemChatLieu
    @TenChatLieu = N'Bạch kim',
    @TyLe = 95.0,
    @TrangThai = 1;
GO
-- Thêm voucher thứ nhất
EXEC ThemVoucher
    @TyLe = 10.0,
    @TenVoucher = N'VOUCHER10',
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO

-- Thêm voucher thứ hai
EXEC ThemVoucher
    @TyLe = 15.0,
    @TenVoucher = N'VOUCHER15',
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO

-- Thêm voucher thứ ba
EXEC ThemVoucher
    @TyLe = 20.0,
    @TenVoucher = N'VOUCHER20',
    @NgayBatDau = '2024-07-01',
    @NgayKetThuc = '2024-12-31',
    @TrangThai = 1;
GO

-- Thêm hóa đơn thứ nhất
EXEC sp_ThemHoaDon
    @IDKhachHang = 'KH0001',
    @IDTaiKhoan = 'TK0001',
    @IDVoucher = 'VC0001',
    @TongTienTruoc = 2000000,
    @TongTienSau = 1800000,
    @TrangThai = 1,
    @TrangThaiTichDiem = 1;
GO

-- Thêm hóa đơn thứ hai
EXEC sp_ThemHoaDon
    @IDKhachHang = 'KH0002',
    @IDTaiKhoan = 'TK0002',
    @IDVoucher = 'VC0002',
    @TongTienTruoc = 3000000,
    @TongTienSau = 2700000,
    @TrangThai = 1,
    @TrangThaiTichDiem = 1;
GO

-- Thêm hóa đơn thứ ba
EXEC sp_ThemHoaDon
    @IDKhachHang = 'KH0003',
    @IDTaiKhoan = 'TK0003',
    @IDVoucher = 'VC0003',
    @TongTienTruoc = 4000000,
    @TongTienSau = 3600000,
    @TrangThai = 1,
    @TrangThaiTichDiem = 1;
GO
-- Thêm sản phẩm thứ nhất
EXEC ThemSanPham
    @IDPhanLoai = 'PL0001',
    @IDKiemDinh = 'KD0001',
    @IDMauSac = 'MS0001',
    @IDSize = 'sz0001',
    @TenSanPham = N'Sản phẩm A',
    @GioiTinh = 1,
    @IDChatLieu = 'CL0001',
    @SoLuongTonKho = 50,
    @GiaChiTiet = 1000000,
    @IDGiamGia = 'GG0001',
    @IDNhaCungCap = 'NC0001',
    @SoLuongDaQuy = 10,
    @KichThuocDa = 5.0,
    @TrongLuong = 10.0,
	@HinhAnhSanPham = 'http://example.com/image2.jpg',
    @IDDaQuy = 'DQ0001',
    @TrangThai = 1;
GO
EXEC ThemSanPham
    @IDPhanLoai = 'PL0001',
    @IDKiemDinh = 'KD0001',
    @IDMauSac = 'MS0001',
    @IDSize = 'sz0001',
    @TenSanPham = N'Sản phẩm A',
    @GioiTinh = 1,
    @IDChatLieu = 'CL0001',
    @SoLuongTonKho = 50,
    @GiaChiTiet = 1000000,
    @IDGiamGia = NULL,
    @IDNhaCungCap = 'NC0001',
    @SoLuongDaQuy = 10,
    @KichThuocDa = 5.0,
    @TrongLuong = 10.0,
	@HinhAnhSanPham = 'http://example.com/image2.jpg',
    @IDDaQuy = 'DQ0001',
    @TrangThai = 1;
GO


-- Thêm sản phẩm thứ hai
EXEC ThemSanPham
    @IDPhanLoai = 'PL0002',
    @IDKiemDinh = 'KD0002',
    @IDMauSac = 'MS0002',
    @IDSize = 'sz0002',
    @TenSanPham = N'Sản phẩm B',
    @GioiTinh = 1,
    @IDChatLieu = 'CL0002',
    @SoLuongTonKho = 30,
    @GiaChiTiet = 2000000,
    @IDGiamGia = 'GG0002',
    @IDNhaCungCap = 'NC0002',
    @SoLuongDaQuy = 20,
    @KichThuocDa = 6.0,
    @TrongLuong = 15.0,
	@HinhAnhSanPham = 'http://example.com/image2.jpg',
    @IDDaQuy = 'DQ0002',
    @TrangThai = 1;
GO

-- Thêm sản phẩm thứ ba
EXEC ThemSanPham
    @IDPhanLoai = 'PL0003',
    @IDKiemDinh = 'KD0003',
    @IDMauSac = 'MS0003',
    @IDSize = 'sz0003',
    @TenSanPham = N'Sản phẩm C',
    @GioiTinh = 1,
    @IDChatLieu = 'CL0003',
    @SoLuongTonKho = 20,
    @GiaChiTiet = 3000000,
    @IDGiamGia = 'GG0003',
    @IDNhaCungCap = 'NC0003',
    @SoLuongDaQuy = 15,
    @KichThuocDa = 7.0,
    @TrongLuong = 20.0,
	@HinhAnhSanPham = 'http://example.com/image2.jpg',
    @IDDaQuy = 'DQ0003',
    @TrangThai = 1;
GO
-- Thêm hóa đơn chi tiết thứ nhất
EXEC sp_ThemHoaDonChiTiet
    @IDSanPham = 'SP0001',
    @IDHoaDon = 'HD0001',
    @SoLuongSanPham = 2,
    @TrangThai = 1;
GO

-- Thêm hóa đơn chi tiết thứ hai
EXEC sp_ThemHoaDonChiTiet
    @IDSanPham = 'SP0002',
    @IDHoaDon = 'HD0002',
    @SoLuongSanPham = 3,
    @TrangThai = 1;
GO

-- Thêm hóa đơn chi tiết thứ ba
EXEC sp_ThemHoaDonChiTiet
    @IDSanPham = 'SP0003',
    @IDHoaDon = 'HD0003',
    @SoLuongSanPham = 1,
    @TrangThai = 1;
GO
-- Thêm dữ liệu vào bảng BaoHanh bằng thủ tục
select * from SerialNumber
EXEC sp_ThemBaoHanh 
    @IDKhachHang = 'KH0001',
    @SerialNumber = 'SN0001',
    @IDSanPham = 'SP0001',
    @IDHoaDonChiTiet = 'HDCT0059',
    @NgayYeuCau = '2024-08-01',
    @GhiChu = 'Yeu cau bao hanh cho san pham 1',
    @TrangThai = 1;
go
EXEC sp_ThemBaoHanh 
    @IDKhachHang = 'KH0002',
    @SerialNumber = 'SN0002',
    @IDSanPham = 'SP0001',
    @IDHoaDonChiTiet = 'HDCT0059',
    @NgayYeuCau = '2024-08-02',
    @GhiChu = 'Yeu cau bao hanh cho san pham 2',
    @TrangThai = 1;
go
EXEC sp_ThemBaoHanh 
    @IDKhachHang = 'KH0003',
    @SerialNumber = 'SN0003',
    @IDSanPham = 'SP0002',
    @IDHoaDonChiTiet = 'HDCT0060',
    @NgayYeuCau = '2024-08-03',
    @GhiChu = 'Yeu cau bao hanh cho san pham 3',
    @TrangThai = 1;
go

-- Tạo một bảng tạm thời để lưu trữ dữ liệu từ bảng Inserted
CREATE TABLE TempInsertedData (
    IDHoaDonChiTiet VARCHAR(8),
    IDSanPham VARCHAR(6),
    SoLuongSanPham TINYINT
);
go
-- Tạo trigger để chèn dữ liệu vào bảng tạm thời
CREATE OR ALTER TRIGGER trg_TestInserted
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN
    -- Đảm bảo không có lỗi trong quá trình thực thi trigger
    SET NOCOUNT ON;

    -- Chèn dữ liệu từ bảng Inserted vào bảng tạm thời
    INSERT INTO TempInsertedData (IDHoaDonChiTiet, IDSanPham, SoLuongSanPham)
    SELECT IDHoaDonChiTiet, IDSanPham, SoLuongSanPham
    FROM Inserted;
END;
go
select *from SerialNumber 
select * from ChatLieu
select * from SanPham
select * from PhanLoai
select * from DaQuy
select * from GiamGia
select * from KhachHang
select * from HoaDon
SELECT * FROM TaiKhoan
select * from Size
select * from NhaCungCap
select * from MauSac
select * from Voucher
select * from HoaDonChiTiet
select * from SanPham
select * from BaoHanh
select * from KiemDinh
go
CREATE OR ALTER VIEW v_SanPham_PhanLoai AS
SELECT 
    SP.IDSanPham,
    SP.IDPhanLoai,
    SP.IDKiemDinh,
    SP.IDMauSac,
    SP.IDSize,
    SP.TenSanPham,
    SP.GioiTinh,
    PL.TenPhanLoai,
    SP.IDChatLieu,
    SP.SoLuongTonKho,
    SP.GiaChiTiet,
    SP.IDGiamGia,
	gg.TenMaGiamGia,
    gg.TyLeGiamGia,
    SP.IDNhaCungCap,
    SP.SoLuongDaQuy,
    SP.KichThuocDa,
    SP.TrongLuong,
    SP.HinhAnhSanPham,
    SP.IDDaQuy,
    SP.NgayTao,
    SP.NgaySua,
    SP.TrangThai
FROM 
    SanPham SP
INNER JOIN 
    PhanLoai PL ON SP.IDPhanLoai = PL.IDPhanLoai
LEFT JOIN 
    GiamGia gg ON SP.IDGiamGia = gg.IDGiamGia;
	select * from TaiKhoan
	select * from v_SanPham_PhanLoai
	select * from TaiKhoan
	select * from KhachHang
go
CREATE OR ALTER VIEW v_SanPham_all AS
SELECT 
    sp.IDSanPham,
    sp.IDPhanLoai,
    sp.IDKiemDinh,
    sp.IDMauSac,
    sp.IDSize,
    sp.TenSanPham,
    sp.GioiTinh,
    pl.TenPhanLoai,
    sp.IDChatLieu,
    sp.SoLuongTonKho,
    sp.GiaChiTiet,
    sp.IDGiamGia,
    sp.IDNhaCungCap,
    sp.SoLuongDaQuy,
    sp.KichThuocDa,
    sp.TrongLuong,
    sp.HinhAnhSanPham,
    sp.IDDaQuy,
    sp.NgayTao,
    sp.NgaySua,
    sp.TrangThai,
    cl.TyLe,
    sz.SoSize,
    cl.TenChatLieu,
    dq.TenDaQuy,
    ncc.TenNhaCungCap,
    kd.DonViKiemDinh,
    kd.NgayKiemDinh,
	ms.ChiTietMauSac
FROM 
    SanPham sp
JOIN 
    Size sz ON sp.IDSize = sz.IDSize
JOIN 
    PhanLoai pl ON sp.IDPhanLoai = pl.IDPhanLoai
JOIN 
    ChatLieu cl ON sp.IDChatLieu = cl.IDChatLieu
JOIN 
    MauSac ms ON sp.IDMauSac = ms.IDMauSac
JOIN 
    DaQuy dq ON sp.IDDaQuy = dq.IDDaQuy
JOIN 
    NhaCungCap ncc ON sp.IDNhaCungCap = ncc.IDNhaCungCap
JOIN 
    KiemDinh kd ON sp.IDKiemDinh = kd.IDKiemDinh;

select * from v_SanPham_all where IDSanPham like '%01%'

go
CREATE OR ALTER VIEW v_Sp_GG AS
SELECT
    sp.IDSanPham,
    sp.IDPhanLoai,
    sp.IDKiemDinh,
    sp.IDMauSac,
    sp.IDSize,
    sp.TenSanPham,
    sp.GioiTinh,
    pl.TenPhanLoai,
    sp.IDChatLieu,
    sp.SoLuongTonKho,
    sp.GiaChiTiet,
    sp.IDGiamGia,
    sp.IDNhaCungCap,
    sp.SoLuongDaQuy,
    sp.KichThuocDa,
    sp.TrongLuong,
    sp.HinhAnhSanPham,
    sp.IDDaQuy,
    sp.NgayTao,
    sp.NgaySua,
    sp.TrangThai,
    cl.TyLe,
    sz.SoSize,
    cl.TenChatLieu,
    dq.TenDaQuy,
    ncc.TenNhaCungCap,
    kd.DonViKiemDinh,
    kd.NgayKiemDinh,
    ms.ChiTietMauSac,
    gg.TenMaGiamGia,
    gg.NgayBatDau,
    gg.NgayKetThuc,
    gg.TyLeGiamGia
FROM 
    SanPham sp
JOIN 
    Size sz ON sp.IDSize = sz.IDSize
JOIN 
    PhanLoai pl ON sp.IDPhanLoai = pl.IDPhanLoai
JOIN 
    ChatLieu cl ON sp.IDChatLieu = cl.IDChatLieu
JOIN 
    MauSac ms ON sp.IDMauSac = ms.IDMauSac
JOIN 
    DaQuy dq ON sp.IDDaQuy = dq.IDDaQuy
JOIN 
    NhaCungCap ncc ON sp.IDNhaCungCap = ncc.IDNhaCungCap
JOIN 
    KiemDinh kd ON sp.IDKiemDinh = kd.IDKiemDinh
JOIN 
    GiamGia gg ON sp.IDGiamGia = gg.IDGiamGia;

go
select * from v_Sp_GG 
select * from GiamGia
go
-- Thêm View Hoá Đơn

CREATE VIEW View_HoaDon_ChiTiet AS
SELECT 
    hd.IDHoaDon,
    hd.NgayTao,
    hd.TongTienSau,
    hd.TrangThai AS HoaDon_TrangThai, 
    tk.IDTaiKhoan AS TaiKhoan_IDTaiKhoan,
    tk.HoTen AS TaiKhoan_HoTen,
    tk.Email,
    tk.SoDienThoai AS TaiKhoan_SoDienThoai,
    ctsp.TenSanPham, 
    kh.IDKhachHang,
    kh.HoTen AS KhachHang_HoTen,
    kh.DiaChi,
    kh.SoDienThoai AS KhachHang_SoDienThoai
FROM 
    HoaDon AS hd
JOIN 
    TaiKhoan AS tk ON hd.IDTaiKhoan = tk.IDTaiKhoan
JOIN 
    (
        SELECT 
            ct.IDHoaDon, 
            sp.TenSanPham 
        FROM 
            HoaDonChiTiet AS ct 
        JOIN 
            SanPham AS sp ON ct.IDSanPham = sp.IDSanPham
    ) AS ctsp ON hd.IDHoaDon = ctsp.IDHoaDon
JOIN 
    KhachHang AS kh ON hd.IDKhachHang = kh.IDKhachHang

select * from View_HoaDon_ChiTiet 
go

CREATE or alter VIEW View_HoaDonChiTiet AS
SELECT 
    CT.IDHoaDonChiTiet,
    CT.IDSanPham,
    CT.IDHoaDon,
    CT.SoLuongSanPham,
    SP.TenSanPham,
    SP.GiaChiTiet,
	sp.IDGiamGia,
    GG.TyLeGiamGia
FROM 
    HoaDonChiTiet AS CT
    JOIN SanPham AS SP ON CT.IDSanPham = SP.IDSanPham
    LEFT JOIN GiamGia AS GG ON SP.IDGiamGia = GG.IDGiamGia
GO
select * from View_HoaDonChiTiet
select * from HoaDonChiTiet
select * from GiamGia
select * from SanPham

GO


-- Sửa giá trị về null
-- Thay đổi thuộc tính của cột IDKhachHang từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN IDKhachHang VARCHAR(6) NULL;

-- Thay đổi thuộc tính của cột IDTaiKhoan từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN IDTaiKhoan VARCHAR(6) NULL;

-- Thay đổi thuộc tính của cột IDVoucher từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN IDVoucher VARCHAR(6) NULL;

-- Thay đổi thuộc tính của cột TongTienTruoc từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN TongTienTruoc MONEY NULL;

-- Thay đổi thuộc tính của cột TongTienSau từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN TongTienSau MONEY NULL;

-- Thay đổi thuộc tính của cột TrangThai từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN TrangThai BIT NULL;

-- Thay đổi thuộc tính của cột TrangThaiTichDiem từ NOT NULL thành NULL
ALTER TABLE HoaDon
ALTER COLUMN TrangThaiTichDiem BIT NULL;


--tạo view hóa đơn để fill lên dialog hoa đơn chi tiet
go
CREATE OR ALTER VIEW v_showhoadon_hoadonchitiet AS
SELECT 
    hd.IDHoaDon,
    hd.IDKhachHang,
    hd.NgayTao,
    hd.TrangThai,
	hd.TongTienTruoc,
    hd.TongTienSau,
    kh.HoTen AS KhachHangHoTen,
    tk.IDTaiKhoan,
    tk.HoTen AS TaiKhoanHoTen,
	hd.IDVoucher,
	v.TenVoucher,
	v.TyLe,
	ct.SoLuongSanPham,
    sp.IDSanPham,
    sp.TenSanPham,
    sp.IDGiamGia,
	sp.GiaChiTiet,
    gg.TyLeGiamGia
FROM 
    HoaDon AS hd
JOIN 
    KhachHang AS kh ON hd.IDKhachHang = kh.IDKhachHang
JOIN 
    TaiKhoan AS tk ON hd.IDTaiKhoan = tk.IDTaiKhoan
JOIN 
    HoaDonChiTiet AS ct ON hd.IDHoaDon = ct.IDHoaDon
	join Voucher as v on v.IDVoucher = hd.IDVoucher
JOIN 
    SanPham AS sp ON ct.IDSanPham = sp.IDSanPham
LEFT JOIN 
    GiamGia AS gg ON sp.IDGiamGia = gg.IDGiamGia;

	select * from v_showhoadon_hoadonchitiet
	select * from HoaDonChiTiet
	select * from HoaDon
	go

-- View GetAll Table
CREATE OR ALTER VIEW v_DoanhThu_Khoang_Thoi_Gian AS
SELECT 
    tk.IDTaiKhoan,
    tk.HoTen,
    COUNT(hd.IDHoaDon) AS TongSoDon,
    SUM(hd.TongTienTruoc) AS TongDoanhThu,
    SUM(hd.TongTienSau) AS DoanhThuThuc,
    SUM(hd.TongTienTruoc - hd.TongTienSau) AS GiamGiaSanPham

FROM 
    TaiKhoan tk 
JOIN 
    HoaDon hd ON tk.IDTaiKhoan = hd.IDTaiKhoan
GROUP BY
    tk.IDTaiKhoan,
    tk.HoTen
	
	go
	select * from v_DoanhThu_Khoang_Thoi_Gian
	go

go
--Cập nhật lên bảng sóng theo ngày của từng nhân viên
CREATE OR ALTER VIEW v_DoanhThu_Khoang_Thoi_Gian_NhanVien AS
SELECT 
    tk.IDTaiKhoan,
    tk.HoTen,
	Day(hd.NgayTao) as Ngay,
	YEAR(hd.NgayTao) AS Nam,
    MONTH(hd.NgayTao) AS Thang,
    COUNT(hd.IDHoaDon) AS TongSoDon,
    SUM(hd.TongTienTruoc) AS TongTienTruoc,
    SUM(hd.TongTienSau) AS TongTienSau,
    SUM(hd.TongTienTruoc - hd.TongTienSau) AS GiamGiaSanPham,
	hd.TrangThai
FROM 
    TaiKhoan tk 
JOIN 
    HoaDon hd ON tk.IDTaiKhoan = hd.IDTaiKhoan
where hd.TrangThai = 1
GROUP BY
    tk.IDTaiKhoan,
    tk.HoTen,
	Day(hd.NgayTao),
	YEAR(hd.NgayTao),
    MONTH(hd.NgayTao),
	hd.TrangThai;

go
select * from HoaDon
-- Cập nhật bảng sóng all theo tháng và tk
CREATE OR ALTER VIEW v_DoanhThu_Thang_Nv AS
SELECT 
    YEAR(hd.NgayTao) AS Nam,
    MONTH(hd.NgayTao) AS Thang,
    SUM(hd.TongTienTruoc) AS TongDoanhThu,
    SUM(hd.TongTienSau) AS DoanhThuThuc,
    SUM(hd.TongTienTruoc - hd.TongTienSau) AS GiamGiaSanPham
FROM 
    HoaDon hd
GROUP BY 
    YEAR(hd.NgayTao),
    MONTH(hd.NgayTao)
go

-- Cập nhật table NhânVien theo thời gian 
CREATE OR ALTER VIEW v_DoanhThu_NhanVien_ThoiGian AS
SELECT 
    tk.IDTaiKhoan,
    tk.HoTen,
    YEAR(hd.NgayTao) AS Nam,
    MONTH(hd.NgayTao) AS Thang,
    DAY(hd.NgayTao) AS Ngay,
    COUNT(hd.IDHoaDon) AS TongSoDon,
    SUM(hd.TongTienTruoc) AS TongTienTruoc,
    SUM(hd.TongTienSau) AS TongTienSau,
    SUM(hd.TongTienTruoc - hd.TongTienSau) AS GiamGiaSanPham
FROM 
    TaiKhoan tk 
JOIN 
    HoaDon hd ON tk.IDTaiKhoan = hd.IDTaiKhoan
WHERE 
    hd.TrangThai = 1
GROUP BY
    tk.IDTaiKhoan,
    tk.HoTen,
    YEAR(hd.NgayTao),
    MONTH(hd.NgayTao),
    DAY(hd.NgayTao);
select * from v_DoanhThu_NhanVien_ThoiGian
select * from KhachHang
go
CREATE OR ALTER VIEW View_DanhSachSanPham_BaoHanh AS
SELECT 
    bh.IDBaoHanh,
	bh.NgayTao,
	bh.NgayYeuCau,
	bh.TrangThai,
	bh.GhiChu,
	sp.TenSanPham,
	kh.HoTen,
	kh.SoDienThoai,
	kh.DiaChi
from BaoHanh bh
join SanPham sp on bh.IDSanPham  = sp.IDSanPham
join KhachHang kh on kh.IDKhachHang = bh.IDKhachHang
SELECT * FROM View_DanhSachSanPham_BaoHanh WHERE NgayYeuCau BETWEEN ? AND ? and SoDienThoai like ?;
GO
select * from View_DanhSachSanPham_BaoHanh
select * from TaiKhoan
go
CREATE OR ALTER VIEW View_BaoHanh_ChiTiet AS
SELECT 
    kh.SoDienThoai,
    hdct.IDHoaDonChiTiet,
    sn.SerialNumber,
    sp.IDSanPham,
    sp.TenSanPham,
    sn.thoihanbaohanh,
    sn.trangthai,
    hdct.NgayTao,
	tk.HoTen
FROM 
    SerialNumber AS sn
JOIN 
    HoaDonChiTiet AS hdct ON sn.IDHoaDonChiTiet = hdct.IDHoaDonChiTiet
JOIN 
    SanPham AS sp ON hdct.IDSanPham = sp.IDSanPham
JOIN 
    HoaDon AS hd ON hdct.IDHoaDon = hd.IDHoaDon
JOIN 
    KhachHang AS kh ON hd.IDKhachHang = kh.IDKhachHang
join 
	TaiKhoan as tk on hd.IDTaiKhoan = tk.IDTaiKhoan

go
select * from SerialNumber
select * from HoaDonChiTiet
select * from HoaDon
go
CREATE OR ALTER TRIGGER trg_UpdateSerialNumberStatus
ON BaoHanh
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật trạng thái của SerialNumber
    UPDATE SerialNumber
    SET trangthai = 0
    FROM SerialNumber sn
    JOIN Inserted i ON sn.SerialNumber = i.SerialNumber
                      AND sn.IDHoaDonChiTiet = i.IDHoaDonChiTiet
                      AND sn.IDSanPham = i.IDSanPham
    WHERE sn.trangthai = 1; -- Chỉ cập nhật trạng thái nếu hiện tại là 1
END;


-- Hùng cập nhật ngày 5/8
--tạo hóa đơn chi tiết để show dữ liệu lên 
go
create or alter view v_HoaDonChiTiet
as
select 
 ct.IDHoaDonChiTiet,
 ct.SoLuongSanPham,
 ct.GiaSanPham,
 ct.GiaSauGiamGia,
 hd.IDHoaDon,
 hd.NgayTao,
 hd.TongTienTruoc, 
 hd.TongTienSau,
 hd.TrangThai,
hd.IDVoucher,
vc.TenVoucher,
 sp.IDSanPham,
 sp.TenSanPham,
 sp.SoLuongTonKho,
 kh.IDKhachHang,
 kh.HoTen as HoTenKhachHang,
 tk.IDTaiKhoan,
 tk.HoTen as HoTenTaiKhoan
from HoaDonChiTiet as ct
 left join HoaDon as hd on ct.IDHoaDon = hd.IDHoaDon
left join SanPham as sp on ct.IDSanPham = sp.IDSanPham
left join KhachHang as kh on hd.IDKhachHang = kh.IDKhachHang
left join TaiKhoan as tk on hd.IDTaiKhoan = tk.IDTaiKhoan
left join Voucher as vc on hd.IDVoucher = vc.IDVoucher
select * from v_HoaDonChiTiet where NgayTao between ? and ? and IDTaiKhoan = ?
-- Tạo view hóa đơn chờ
go
CREATE OR ALTER VIEW v_HoaDonCho AS
SELECT 
    hd.IDHoaDon,
    hd.NgayTao,
    hd.TrangThai,
    -- Tổng số lượng và tổng tiền cho hóa đơn chính
    SUM(ct.SoLuongSanPham * 
        CASE 
            WHEN ct.GiaSauGiamGia IS NOT NULL THEN ct.GiaSauGiamGia
            ELSE ct.GiaSanPham
        END
    ) AS TongTien
FROM 
    HoaDon AS hd
left JOIN 
    HoaDonChiTiet AS ct ON hd.IDHoaDon = ct.IDHoaDon
WHERE 
    hd.TrangThai = 0
GROUP BY 
    hd.IDHoaDon,
    hd.NgayTao,
    hd.TrangThai;

	-- Hùng cập nhật ngày 5/8
--tạo hóa đơn chi tiết để show dữ liệu lên 
go
create or alter view v_HoaDonChiTiet
as
select 
 ct.IDHoaDonChiTiet,
 ct.SoLuongSanPham,
 ct.GiaSanPham,
 ct.GiaSauGiamGia,
 hd.IDHoaDon,
 hd.NgayTao,
 hd.TongTienTruoc, 
 hd.TongTienSau,
 hd.TrangThai,
hd.IDVoucher,
vc.TenVoucher,
 sp.IDSanPham,
 sp.TenSanPham,
 sp.SoLuongTonKho,
 kh.IDKhachHang,
 kh.HoTen as HoTenKhachHang,
 tk.IDTaiKhoan,
 tk.HoTen as HoTenTaiKhoan
from HoaDonChiTiet as ct
 left join HoaDon as hd on ct.IDHoaDon = hd.IDHoaDon
left join SanPham as sp on ct.IDSanPham = sp.IDSanPham
left join KhachHang as kh on hd.IDKhachHang = kh.IDKhachHang
left join TaiKhoan as tk on hd.IDTaiKhoan = tk.IDTaiKhoan
left join Voucher as vc on hd.IDVoucher = vc.IDVoucher

-- Tạo view hóa đơn chờ

go
CREATE OR ALTER VIEW v_HoaDonCho AS
SELECT 
    hd.IDHoaDon,
    hd.NgayTao,
    hd.TrangThai,
    -- Tổng số lượng và tổng tiền cho hóa đơn chính
    SUM(ct.SoLuongSanPham * 
        CASE 
            WHEN ct.GiaSauGiamGia IS NOT NULL THEN ct.GiaSauGiamGia
            ELSE ct.GiaSanPham
        END
    ) AS TongTien
FROM 
    HoaDon AS hd
left JOIN 
    HoaDonChiTiet AS ct ON hd.IDHoaDon = ct.IDHoaDon
WHERE 
    hd.TrangThai = 0
GROUP BY 
    hd.IDHoaDon,
    hd.NgayTao,
    hd.TrangThai;
go
-- Trigger để cập nhật trạng thái của HoaDonChiTiet khi HoaDon chuyển sang trạng thái 1
CREATE TRIGGER trg_UpdateHoaDonChiTietStatus
ON HoaDon
AFTER UPDATE
AS
BEGIN
    -- Kiểm tra nếu cột trạng thái được cập nhật và giá trị mới là 1
    IF UPDATE(trangThai)
    BEGIN
        -- Cập nhật trạng thái của HoaDonChiTiet
        UPDATE hdct
        SET hdct.trangThai = 1
        FROM HoaDonChiTiet hdct
        INNER JOIN inserted i ON hdct.IDHoaDon = i.IDHoaDon
        WHERE i.trangThai = 1;
    END
END
GO
go
CREATE or alter PROCEDURE DeleteHoaDon
    @idhoadon nvarchar(50)
AS
BEGIN
    -- Xóa hóa đơn chi tiết liên quan
    DELETE FROM HoaDonChiTiet
    WHERE idhoadon = @idhoadon;

    -- Xóa hóa đơn chính
    DELETE FROM HoaDon
    WHERE idhoadon = @idhoadon;
END
select * from v_HoaDonChiTiet

CREATE OR Alter TRIGGER trg_InsertGiaSanPhamVaGiamGia
ON HoaDonChiTiet
AFTER INSERT
AS
BEGIN

    -- Cập nhật giá sản phẩm và giá sau giảm giá sau khi chèn bản ghi mới
    UPDATE hdc
    SET GiaSanPham = sp.GiaChiTiet,
        GiaSauGiamGia = 
            CASE 
                WHEN sp.IDGiamGia IS NOT NULL THEN 
                    sp.GiaChiTiet * (1 - gg.TyLeGiamGia / 100.0) -- Tính giá sau giảm giá
                ELSE 
                    sp.GiaChiTiet
            END
    FROM HoaDonChiTiet hdc
    INNER JOIN inserted i ON hdc.IDHoaDonChiTiet = i.IDHoaDonChiTiet
    INNER JOIN SanPham sp ON hdc.IDSanPham = sp.IDSanPham
    LEFT JOIN GiamGia gg ON sp.IDGiamGia = gg.IDGiamGia;
END;
select * from BaoHanh
select * from HoaDonChiTiet
select * from SerialNumber
ALTER TABLE SerialNumber
ADD CONSTRAINT FK__SerialNum__IDHoa__756D6ECB
FOREIGN KEY (IDHoaDonChiTiet)
REFERENCES HoaDonChiTiet(IDHoaDonChiTiet)
ON DELETE CASCADE;
