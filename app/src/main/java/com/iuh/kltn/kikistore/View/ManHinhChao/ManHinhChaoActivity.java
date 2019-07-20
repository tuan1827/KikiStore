package com.iuh.kltn.kikistore.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

         Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (Exception e){

                }finally {
                    Intent iTrangChu = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(iTrangChu);
                }
            }
        });

        thread.start();
    }
}
