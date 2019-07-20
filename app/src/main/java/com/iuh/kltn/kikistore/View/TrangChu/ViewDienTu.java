package com.iuh.kltn.kikistore.View.TrangChu;

import com.iuh.kltn.kikistore.Model.ObjectClass.DienTu;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTus);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);
}
