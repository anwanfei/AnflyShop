package com.anfly.anflyshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.anfly.anflyshop.ui.Login.LoginActivity;
import com.anfly.anflyshop.ui.test.Base64GetValueActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_special_topic, R.id.navigation_sort,
                R.id.navigation_shopping_cart, R.id.navigation_my)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        openLogin();
//        base64PassValue();
    }

    /**
     * android Base64 使用学习地址 https://www.jianshu.com/p/48fac4e0fe1e
     * 配合base64，可以在网络中实现文件的封装和传递以及数据的隐藏（也可以做加密处理），
     * 要解决网络中跨平台的文件和数据的传递
     */
    private void base64PassValue() {
        //以图片为例转换成base64 传递到 TestDataActivity
        String bmpBase64 = Base64.encodeToString(parseBitmapBytes(), Base64.DEFAULT);
        Intent intent = new Intent(this, Base64GetValueActivity.class);
        intent.putExtra("data", bmpBase64);
        startActivity(intent);
    }

    private byte[] parseBitmapBytes() {
        //读取图片文件到bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        //将bitmap转换成字节数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);//图片压缩
        byte[] datas = baos.toByteArray();//图片转换为二进制流

        //插入文字内容
        byte[] words = "anfly".getBytes();

        //图片数据流长度
        int length = datas.length;

        //把图片和文字内容的byte数组拼接成一个byte
        datas = concat(datas,words);

        return new byte[0];
    }

    private byte[] concat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
