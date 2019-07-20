package com.iuh.kltn.kikistore.View.DanhGia;

import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList);
}
