package com.iuh.kltn.kikistore.View.DangNhap_DangKy.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.iuh.kltn.kikistore.Model.ObjectClass.NhanVien;
import com.iuh.kltn.kikistore.Presenter.DangKy.PresenterLogicDangKy;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.DangNhap_DangKy.ViewDangKy;

import java.util.Calendar;
import java.util.TimeZone;

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    CheckBox cbGioiTinh;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edDiaChiEmail,edSDT,edDiaChi,edNgaySinh;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen;
    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edDiaChiEmail;
    TextInputLayout input_edDiaChi,input_edSDT,input_edNgaySinh;
    Boolean kiemtrathongtin = false;
    //---------
    Calendar myCalendar = Calendar.getInstance(); //global

    public void showDatePickerDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            edNgaySinh.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                    + "/" + String.valueOf(year));
            //edNgaySinh.setText("1/1/1997"); // or whatever you want
        }

    };
    //-----------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edMatKhau = view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = view.findViewById(R.id.edDiaChiEmailDK);
        sEmailDocQuyen = view.findViewById(R.id.sEmailDocQuyen);
        //--------------------
        cbGioiTinh=view.findViewById(R.id.cbGioiTinhDK);
        edDiaChi=view.findViewById(R.id.edDiaChiDK);
        edSDT=view.findViewById(R.id.edSDTDK);
        edNgaySinh=view.findViewById(R.id.edNgaySinhDK);
        input_edDiaChi=view.findViewById(R.id.input_edDiaChiDK);
        input_edSDT=view.findViewById(R.id.input_edSDTDK);
        input_edNgaySinh=view.findViewById(R.id.input_edNgaySinhDK);
        //-------------------
        input_edHoTen = view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        //-----------------
        edSDT.setOnFocusChangeListener(this);
        edDiaChi.setOnFocusChangeListener(this);


        //edNgaySinh.setOnClickListener(this);
        edNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //-----
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        return view;
    }


    @Override
    public void DangKyThangCong() {
        Toast.makeText(getActivity(),"Đăng ký thành công !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(),"Đăng ký thất bại !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDangKy:
                btnDangKy();
                break;
        }
    }

    String emaildocquyen = "";
    private void btnDangKy(){
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();

        //---------
        String diachi=edDiaChi.getText().toString();
        String sdt=edSDT.getText().toString();
        String ngaysinh=edNgaySinh.getText().toString();
        int gioitinh;
        if (cbGioiTinh.isChecked()){
            gioitinh=1;
        } else gioitinh=0;
        //----------

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if(kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);
            //--------------
            nhanVien.setNgaySinh(ngaysinh);
            nhanVien.setSoDT(sdt);
            nhanVien.setGioiTinh(gioitinh);
            nhanVien.setDiaChi(diachi);
            //----------------

            presenterLogicDangKy.ThucHienDangKy(nhanVien);
        }else{
            Log.d("kiemtra","Dang ky that bai ");
        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edDiaChiEmailDK:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;

            case R.id.edMatKhauDK:
                break;

            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                break;
                //----------------------------
            case R.id.edSDTDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edSDT.setErrorEnabled(true);
                        input_edSDT.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    }else{
                        if(chuoi.length()==10){
                        input_edSDT.setErrorEnabled(false);
                        input_edSDT.setError("");
                        kiemtrathongtin = true;}
                        else
                        {
                            input_edSDT.setErrorEnabled(true);
                            input_edSDT.setError("Chưa đúng đinh dạng!");
                            kiemtrathongtin = false;
                        }
                    }
                }
                break;
            case R.id.edNgaySinhDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edNgaySinh.setErrorEnabled(true);
                        input_edNgaySinh.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edNgaySinh.setErrorEnabled(false);
                        input_edNgaySinh.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edDiaChiDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChi.setErrorEnabled(true);
                        input_edDiaChi.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    }else{
                        input_edDiaChi.setErrorEnabled(false);
                        input_edDiaChi.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
                //-----------------------------
        }
    }

}
