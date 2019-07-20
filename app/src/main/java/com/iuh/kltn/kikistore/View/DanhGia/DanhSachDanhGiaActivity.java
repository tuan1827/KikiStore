package com.iuh.kltn.kikistore.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.iuh.kltn.kikistore.Adapter.AdapterDanhGia;
import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;
import com.iuh.kltn.kikistore.Model.ObjectClass.ILoadMore;
import com.iuh.kltn.kikistore.Model.ObjectClass.LoadMoreScroll;
import com.iuh.kltn.kikistore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.iuh.kltn.kikistore.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore{

    RecyclerView recyclerDanhSachDanhGia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> tatcaDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);

        recyclerDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp",0);
        tatcaDanhGia = new ArrayList<>();

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);


    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList /*List<DanhGia> danhGiaList*/) {
        tatcaDanhGia.addAll(danhGiaList);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,tatcaDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
