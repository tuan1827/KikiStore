package com.iuh.kltn.kikistore.View.ThanhToan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iuh.kltn.kikistore.Model.DangNhap_DangKy.ModelDangNhap;
import com.iuh.kltn.kikistore.Model.ObjectClass.ChiTietHoaDon;
import com.iuh.kltn.kikistore.Model.ObjectClass.HoaDon;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan{
    Toolbar toolbar;
    EditText edTenNguoiNhan,edDiaChi,edSoDT,edEmail;
    ImageButton imNhanTienKhiGiaoHang,imChuyenKhoan;
    TextView txtNhanTienKhiGiaoHang,txtChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoaThuan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
    //--------------
    ModelDangNhap modelDangNhap;
    //--------------
    int chonHinhThuc = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        toolbar = findViewById(R.id.toolbar);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        edEmail = findViewById(R.id.edEmail);
        imNhanTienKhiGiaoHang = findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = findViewById(R.id.imChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        cbThoaThuan = findViewById(R.id.cbThoaThuan);
        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);

        //--------
        modelDangNhap = new ModelDangNhap();
        String tennv = modelDangNhap.LayCachedDangNhap_DatHang(this).getTenNV();
        String diachi = modelDangNhap.LayCachedDangNhap_DatHang(this).getDiaChi();
        String sodt = modelDangNhap.LayCachedDangNhap_DatHang(this).getSoDT();
        String email = modelDangNhap.LayCachedDangNhap_DatHang(this).getTenDN();
        if(!tennv.equals("")){
            edTenNguoiNhan.setText(tennv);
        }
        if(!diachi.equals("")){
            edDiaChi.setText(diachi);
        }
        if(!sodt.equals("")){
            edSoDT.setText(sodt);
        }
        if(!email.equals("")){
            edEmail.setText(email);
            edEmail.setEnabled(false);
        }
        //---------

        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();

        setSupportActionBar(toolbar);

        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiGiaoHang.setOnClickListener(this);
        imChuyenKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnThanhToan:
                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String email = edEmail.getText().toString();
                boolean ktmail = validateEmail(email);
                boolean ktten = validateName(tennguoinhan);
                boolean ktdc = validateAddress(diachi);
                boolean ktsdt = validatePhone(sodt);
                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0&& email.trim().length() > 0 ){
                    if(ktdc){
                        if(ktten) {
                            if (ktsdt) {
                                if (ktmail) {
                                    if (cbThoaThuan.isChecked()) {
                                        HoaDon hoaDon = new HoaDon();
                                        hoaDon.setTenNguoiNhan(tennguoinhan);
                                        hoaDon.setSoDT(sodt);
                                        hoaDon.setDiaChi(diachi);
                                        hoaDon.setEmail(email);
                                        hoaDon.setChuyenKhoan(chonHinhThuc);
                                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);
                                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                                    } else {
                                        edEmail.setFocusable(true);
                                        Toast.makeText(this, "Bạn chưa nhấn chọn vào ô thỏa thuận !", Toast.LENGTH_SHORT).show();
                                    }
                                } else {

                                    Toast.makeText(this, "Nhập email sai định dạng", Toast.LENGTH_SHORT).show();
                                }
                            } else {

                                Toast.makeText(this, "Nhập số điện thoại sai định dạng", Toast.LENGTH_SHORT).show();
                            }
                        }else {

                                Toast.makeText(this, "Nhập tên sai định dạng", Toast.LENGTH_SHORT).show();
                            }
                    }else {

                        Toast.makeText(this, "Nhập địa chỉ sai định dạng", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"Bạn chưa nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.imNhanTienKhiGiaoHang:
                ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
                chonHinhThuc = 0;
                break;

            case R.id.imChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonHinhThuc = 1;
                break;
        }
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[A-Z][\\p{L}][+[\\-'\\s]?[a-zA-Z\\p{L}]]*+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_ADDRESS_REGEX =
            Pattern.compile("[\\d]+[\\-'\\s]+[A-Z][\\p{L}][+[\\-'\\s]?[a-zA-Z\\p{L}]]*+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^(\\\\+84|0)(8|9|1[2|6|8|9])+([0-9]{8})$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    public static boolean validateName(String nameStr) {
        Matcher matcher = VALID_NAME_REGEX .matcher(nameStr);
        return matcher.find();
    }
    public static boolean validateAddress(String addressStr) {
        Matcher matcher = VALID_ADDRESS_REGEX .matcher(addressStr);
        return matcher.find();
    } public static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONE_REGEX .matcher(phoneStr);
        return matcher.find();
    }




    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon){
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorBlack));
    }

    private int getIdColor(int idcolor){

        int color =0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this,"Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this,"thành công !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        for (int i=0;i<sanPhamList.size();i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());
            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}
