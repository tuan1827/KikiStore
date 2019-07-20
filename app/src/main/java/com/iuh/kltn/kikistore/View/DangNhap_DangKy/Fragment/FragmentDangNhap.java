package com.iuh.kltn.kikistore.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iuh.kltn.kikistore.Model.DangNhap_DangKy.ModelDangNhap;
import com.iuh.kltn.kikistore.R;
import com.iuh.kltn.kikistore.View.TrangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    Button btnDangNhapFacebook,btnDangNhapGoogle,btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 111;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edTenDangNhap,edMatKhau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);
        //--------------
        // Add code to print out the key hash
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.iuh.kltn.kikistore",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("kiemtra", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        //--------------
        modelDangNhap = new ModelDangNhap();
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(),this);


        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        edTenDangNhap = view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = view.findViewById(R.id.edMatKhauDangNhap);

        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;

            case R.id.btnDangNhapGoogle:
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus,SIGN_IN_GOOGLE_PLUS);
                showProcessDialog();
                break;

            case R.id.btnDangNhap:
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                if(kiemtra){
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                }else{
                    Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void showProcessDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SIGN_IN_GOOGLE_PLUS){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        progressDialog.cancel();
    }
}
