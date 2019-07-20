package com.iuh.kltn.kikistore.View.DanhGia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.iuh.kltn.kikistore.Model.DangNhap_DangKy.ModelDangNhap;
import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;
import com.iuh.kltn.kikistore.Presenter.DanhGia.PresenterLogicDanhGia;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.TrangChu.TrangChuActivity;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {

    TextInputLayout input_edTieuDe, input_edNoiDung,input_edEmail;
    EditText edTieuDe, edNoiDung,edEmail;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;
    //---
    ModelDangNhap modelDangNhap;
    //-----


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);

        input_edNoiDung = findViewById(R.id.input_edNoiDungDanhGia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDanhGia);
       // input_edEmail=findViewById(R.id.input_edEmailDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        // edEmail=findViewById(R.id.edEmail);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);


        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩn này !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {

    }

    @Override
    public void onClick(View v) {
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
////            // TODO: Consider calling
////            //    ActivityCompat#requestPermissions
////            // here to request the missing permissions, and then overriding
////            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////            //                                          int[] grantResults)
////            // to handle the case where the user grants the permission. See the documentation
////            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        String madg = telephonyManager.getDeviceId();
        modelDangNhap = new ModelDangNhap();
        String madg = modelDangNhap.LayCachedDangNhap_DatHang(this).getTenDN();

        //String madg = edEmail.getText().toString();
       // String madg = "a";
        String tenthietbi = Build.MODEL;
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();

//        if(madg.trim().length() > 0){
//            input_edEmail.setErrorEnabled(false);
//            input_edEmail.setError("");
//        }else{
//            input_edEmail.setErrorEnabled(true);
//            input_edEmail.setError("Bạn chưa nhập email!");
//        }
        if(tieude.trim().length() > 0){
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        }else{
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập tiêu đề !");
        }

        if(noidung.trim().length() > 0){
            input_edNoiDung.setError("");
            input_edNoiDung.setErrorEnabled(false);
        }else{
            input_edNoiDung.setErrorEnabled(true);
            input_edNoiDung.setError("Bạn chưa nhập nội dung");
        }

        if(!input_edNoiDung.isErrorEnabled() && !input_edTieuDe.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMASP(masp);
            danhGia.setMADG(madg);
            danhGia.setTIEUDE(tieude);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);
            danhGia.setTENTHIETBI(tenthietbi);

            presenterLogicDanhGia.ThemDanhGia(danhGia);
            finish();

        }

    }
}
