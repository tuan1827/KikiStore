package com.iuh.kltn.kikistore.Presenter.GioHang;

import android.content.Context;

import com.iuh.kltn.kikistore.Model.GioHang.ModelGioHang;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
