package com.luke.earbudswidget;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final int REQ_BT = 42;
    private FrameLayout root;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);
        root = new FrameLayout(this);
        root.setBackgroundColor(Color.BLACK);

        ImageView artwork = new ImageView(this);
        artwork.setImageResource(R.drawable.dvorah_screen_background);
        artwork.setScaleType(ImageView.ScaleType.CENTER_CROP);
        artwork.setContentDescription("Tela Dvorah com links e permissão Bluetooth");
        root.addView(artwork, new FrameLayout.LayoutParams(-1, -1));
        setContentView(root);

        root.post(this::addTransparentControls);
    }

    private void addTransparentControls() {
        int rootW = root.getWidth();
        int rootH = root.getHeight();
        float scale = Math.max(rootW / 1440f, rootH / 3219f);
        int imageW = Math.round(1440f * scale);
        int imageH = Math.round(3219f * scale);
        int imageLeft = (rootW - imageW) / 2;
        int imageTop = (rootH - imageH) / 2;
        // As áreas acompanham a arte mesmo quando ela é recortada para preencher a tela.
        addHitArea("Permitir Bluetooth", imageLeft, imageTop, imageW, imageH, 0.4644f, 0.5678f, v -> requestBluetooth());
        addHitArea("Abrir Linktree", imageLeft, imageTop, imageW, imageH, 0.5837f, 0.6632f, v -> openUrl("https://linktr.ee/dvorah.ofc"));
        addHitArea("Abrir Youtube", imageLeft, imageTop, imageW, imageH, 0.6870f, 0.7666f, v -> openUrl("https://youtube.com/@luke.fraktur"));
    }

    private void addHitArea(String description, int imageLeft, int imageTop, int imageW, int imageH, float topRatio, float bottomRatio, View.OnClickListener listener) {
        Button hit = new Button(this);
        hit.setContentDescription(description);
        hit.setText("");
        hit.setBackgroundColor(Color.TRANSPARENT);
        hit.setAlpha(0.02f);
        hit.setOnClickListener(listener);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(imageW, Math.max(1, Math.round(imageH * (bottomRatio - topRatio))));
        lp.gravity = Gravity.TOP;
        lp.leftMargin = imageLeft;
        lp.topMargin = imageTop + Math.round(imageH * topRatio);
        root.addView(hit, lp);
    }

    private void requestBluetooth() {
        if (Build.VERSION.SDK_INT >= 31 && checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN}, REQ_BT);
        }
    }

    private void openUrl(String url) {
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch (Exception ignored) { startActivity(new Intent(Settings.ACTION_SETTINGS)); }
    }
}
