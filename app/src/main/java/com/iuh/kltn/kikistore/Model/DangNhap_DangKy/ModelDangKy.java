package com.iuh.kltn.kikistore.Model.DangNhap_DangKy;

import android.util.Log;

import com.iuh.kltn.kikistore.ConnectInternet.DownloadJSON;
import com.iuh.kltn.kikistore.Model.ObjectClass.NhanVien;
import com.iuh.kltn.kikistore.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public Boolean DangKyThanhVien(NhanVien nhanVien){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv",nhanVien.getTenNV());

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",nhanVien.getTenDN());

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hsMaLoaiNV = new HashMap<>();
        hsMaLoaiNV.put("maloainv",String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String,String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());
        //-------------

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi",nhanVien.getDiaChi());

        HashMap<String,String> hsSDT = new HashMap<>();
        hsSDT.put("sdt",nhanVien.getSoDT());

        HashMap<String,String> hsNgaySinh = new HashMap<>();
        hsNgaySinh.put("ngaysinh",nhanVien.getNgaySinh());

        HashMap<String,String> hsGioiTinh = new HashMap<>();
        hsGioiTinh.put("gioitinh",String.valueOf(nhanVien.getGioiTinh()));

        attrs.add(hsDiaChi);
        attrs.add(hsNgaySinh);
        attrs.add(hsSDT);
        attrs.add(hsGioiTinh);
        //-------------
        attrs.add(hsHam);
        attrs.add(hsTenNV);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmailDocQuyen);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
