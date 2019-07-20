package com.iuh.kltn.kikistore.View.ChiTietSanPham;

import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGiohangThatBai();
}
