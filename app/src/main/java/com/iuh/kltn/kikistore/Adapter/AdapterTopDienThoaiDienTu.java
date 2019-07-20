package com.iuh.kltn.kikistore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iuh.kltn.kikistore.Model.ObjectClass.ChiTietKhuyenMai;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopDienThoaiDienTu(Context context,int layout, List<SanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;

        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);

            imHinhSanPham = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);

        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Log.d("hinhsanpham",sanPham.getANHLON());
        Picasso.with(context).load(sanPham.getANHLON()).resize(140,140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();

        if(chiTietKhuyenMai !=null){
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if(phamtramkm!=0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                holder.txtGiamGia.setVisibility(View.VISIBLE);
                holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtGiamGia.setText(gia + " VNĐ");

                giatien = giatien - (giatien * phamtramkm / 100);
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VNĐ ");
        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietsanpham.putExtra("masp",(int)v.getTag());
                context.startActivity(iChitietsanpham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }



}
