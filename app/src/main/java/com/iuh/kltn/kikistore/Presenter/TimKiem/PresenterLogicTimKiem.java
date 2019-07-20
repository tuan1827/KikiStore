package com.iuh.kltn.kikistore.Presenter.TimKiem;

import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Model.TimKiem.ModelTimKiem;
import com.iuh.kltn.kikistore.View.TimKiem.ViewTimKiem;

import java.util.List;


public class PresenterLogicTimKiem implements IPresenterTimKiem {

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSP(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSanPhamTheoTen(tensp,"DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP",limit);

        if(sanPhamList.size() > 0){
            viewTimKiem.TimKiemThanhCong(sanPhamList);

        }else{
            viewTimKiem.TimKiemThatBai();
        }
    }
}
