package com.iuh.kltn.kikistore.View.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.iuh.kltn.kikistore.Adapter.AdapterTopDienThoaiDienTu;
import com.iuh.kltn.kikistore.Model.ObjectClass.ILoadMore;
import com.iuh.kltn.kikistore.Model.ObjectClass.LoadMoreScroll;
import com.iuh.kltn.kikistore.Model.ObjectClass.SanPham;
import com.iuh.kltn.kikistore.Presenter.TimKiem.PresenterLogicTimKiem;
import com.iuh.kltn.kikistore.R;

import java.util.List;


public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore, SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerTimKiem);

        setSupportActionBar(toolbar);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(this,R.layout.custom_layout_list_topdienthoaivamaytinhbang,sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSP(query,0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
