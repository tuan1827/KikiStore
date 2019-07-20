package com.iuh.kltn.kikistore.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
