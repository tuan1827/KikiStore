package com.iuh.kltn.kikistore.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuh.kltn.kikistore.Adapter.AdapterDienTu;
import com.iuh.kltn.kikistore.Adapter.AdapterThuongHieuLonDienTu;
import com.iuh.kltn.kikistore.Adapter.AdapterTopDienThoaiDienTu;
import com.iuh.kltn.kikistore.Model.ObjectClass.DienTu;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Model.ObjectClass.ThuongHieu;
import com.iuh.kltn.kikistore.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView,recylerTopCacThuongHieuLon,recylerHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imSanPham1,imSanPham2,imSanPham3;
    TextView txtSanPham1,txtSanPham2,txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu,container,false);

        recyclerView = view.findViewById(R.id.recyclerDienTu);
        recylerTopCacThuongHieuLon = view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recylerHangMoiVe = view.findViewById(R.id.recylerHangMoiVe);
        imSanPham1 = view.findViewById(R.id.imSanPham1);
        imSanPham2 = view.findViewById(R.id.imSanPham2);
        imSanPham3 = view.findViewById(R.id.imSanPham3);
        txtSanPham1 = view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = view.findViewById(R.id.txtSanPham3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(),dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();


    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(),thuongHieus);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);

        recylerTopCacThuongHieuLon.setLayoutManager(layoutManager);
        recylerTopCacThuongHieuLon.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaivamaytinhbang,sanPhams);

        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recylerHangMoiVe.setLayoutManager(layoutManagerTop);
        recylerHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
        Random random = new Random();

        int vitri = random.nextInt(sanPhams.size());

        Picasso.with(getContext()).load(sanPhams.get(vitri).getANHLON()).fit().centerInside().into(imSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri).getTENSP());

        int vitri1 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri1).getANHLON()).fit().centerInside().into(imSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri2).getANHLON()).fit().centerInside().into(imSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri2).getTENSP());
    }
}
