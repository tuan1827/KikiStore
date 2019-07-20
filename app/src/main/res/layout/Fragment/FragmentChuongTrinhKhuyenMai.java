package com.checongbinh.lazada.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.checongbinh.lazada.Adapter.AdapterDanhSachKhuyenMai;
import com.checongbinh.lazada.Model.ObjectClass.KhuyenMai;
import com.checongbinh.lazada.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.checongbinh.lazada.R;
import com.checongbinh.lazada.View.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lenovo S410p on 6/23/2016.
 */
public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {

    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerDanhSachKhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chuongtrinhkhuyenmai,container,false);

        lnHinhKhuyenMai = (LinearLayout) view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerDanhSachKhuyenMai = (RecyclerView) view.findViewById(R.id.recyclerDanhSachKhuyenMai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

        int demhinh = khuyenMaiList.size();
        if(demhinh > 5){
            demhinh = 5;
        }else {
            demhinh = khuyenMaiList.size();
        }

        lnHinhKhuyenMai.removeAllViews();
        for (int i = 0 ;i<demhinh ;i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(720,200).into(imageView);

            lnHinhKhuyenMai.addView(imageView);
        }

        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getContext(),khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerDanhSachKhuyenMai.setLayoutManager(layoutManager);
        recyclerDanhSachKhuyenMai.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
