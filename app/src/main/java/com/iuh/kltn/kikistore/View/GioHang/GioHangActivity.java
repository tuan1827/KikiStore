package com.iuh.kltn.kikistore.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iuh.kltn.kikistore.Adapter.AdapterGioHang;
import com.iuh.kltn.kikistore.Model.DangNhap_DangKy.ModelDangNhap;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.iuh.kltn.kikistore.Presenter.GioHang.PresenterLogicGioHang;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.DangNhap_DangKy.DangNhapActivity;
import com.iuh.kltn.kikistore.View.ThanhToan.ThanhToanActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {

    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;
    TextView txtTongTien;
    //---
    ModelDangNhap modelDangNhap;
    //-----
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = findViewById(R.id.recyclerGioHang);
        toolbar = findViewById(R.id.toolbar);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        txtTongTien=findViewById(R.id.txtTongTien);

        setSupportActionBar(toolbar);

        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);


        int total=0;
        for (SanPham sp : sanPhamList) {
            total+=sp.getGIA()*sp.getSOLUONG();
        }
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(total);
        txtTongTien.setText(gia + " vnđ ");

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnMuaNgay:
                //-----
                modelDangNhap = new ModelDangNhap();
                PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
                String tennv = modelDangNhap.LayCachedDangNhap_DatHang(this).getTenNV();

                    //-----------------
                    if (String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)).equalsIgnoreCase("0")) {
                        Toast.makeText(this, "Giỏ hàng không có sản phẩm!", Toast.LENGTH_SHORT).show();
                    } else {
                        if(!tennv.equals("")) {
                        Intent iThanhToan = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                        startActivity(iThanhToan);
                        }
                        else
                        {
                            Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                            startActivity(iDangNhap);
                        }
                    }

                break;
        }

    }
}
