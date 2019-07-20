package com.iuh.kltn.kikistore.View.TimKiem;

import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewTimKiem {
    void TimKiemThanhCong(List<SanPham> sanPhamList);
    void TimKiemThatBai();
}
