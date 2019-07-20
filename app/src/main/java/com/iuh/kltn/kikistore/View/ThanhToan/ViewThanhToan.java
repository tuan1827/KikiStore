package com.iuh.kltn.kikistore.View.ThanhToan;

import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
