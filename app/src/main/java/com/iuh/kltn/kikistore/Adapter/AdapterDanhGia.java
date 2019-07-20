package com.iuh.kltn.kikistore.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iuh.kltn.kikistore.Model.ObjectClass.DanhGia;
import com.iuh.kltn.kikistore.R;

import java.util.List;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {

    List<DanhGia> danhGiaList;
    int limit;
    Context context;

    public AdapterDanhGia(Context context, List<DanhGia> danhGiaList, int limit){
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txtTieuDeDanhGia,txtNoiDungDanhGia,txtDuocDanhGiaBoi,txtEmail;
        RatingBar rbDanhGia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);
           // txtEmail=itemView.findViewById(R.id.txtDuocDangBoiEmail);
            txtDuocDanhGiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia = itemView.findViewById(R.id.txtTieuDeDanhGia);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);
        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_danhgia_chitiet,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);

        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);

        holder.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.txtDuocDanhGiaBoi.setText("Được đánh giả bởi " + danhGia.getTENTHIETBI()+" với email: "+ danhGia.getMADG() + " ngày " + danhGia.getNGAYDANHGIA() );

        holder.rbDanhGia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }else{
            if(limit !=0){
                dong = limit;
            }else{
                dong = danhGiaList.size();
            }
        }

        return dong;
    }


}
