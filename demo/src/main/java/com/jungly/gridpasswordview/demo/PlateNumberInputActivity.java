package com.jungly.gridpasswordview.demo;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.jungly.gridpasswordview.GridPasswordView;
import com.kw.lib.ui.keyboardview.XKeyboardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlateNumberInputActivity extends AppCompatActivity {
    @BindView(R.id.gpvPlateNumber)
    GridPasswordView gpvPlateNumber;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_keyboard)
    XKeyboardView viewKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_number_input);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        testPlateNumberInput();
    }

    private void testPlateNumberInput() {
        viewKeyboard = (XKeyboardView) findViewById(R.id.view_keyboard);
        viewKeyboard.setIOnKeyboardListener(new XKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                gpvPlateNumber.appendPassword(text);
            }

            @Override
            public void onDeleteKeyEvent() {
                gpvPlateNumber.deletePassword();
            }
        });
        gpvPlateNumber.togglePasswordVisibility();
        gpvPlateNumber.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public boolean beforeInput(int position) {
                if (position == 0) {
                    viewKeyboard.setKeyboard(new Keyboard(PlateNumberInputActivity.this, R.xml.provice));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 1 && position < 2) {
                    viewKeyboard.setKeyboard(new Keyboard(PlateNumberInputActivity.this, R.xml.english));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 2 && position < 6) {
                    viewKeyboard.setKeyboard(new Keyboard(PlateNumberInputActivity.this, R.xml.qwerty_without_chinese));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 6 && position < 7) {
                    if (gpvPlateNumber.getPassWord().startsWith("粤Z")) {
                        viewKeyboard.setKeyboard(new Keyboard(PlateNumberInputActivity.this, R.xml.qwerty));
                    } else {
                        viewKeyboard.setKeyboard(new Keyboard(PlateNumberInputActivity.this, R.xml.qwerty_without_chinese));
                    }
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                }
                viewKeyboard.setVisibility(View.GONE);
                return false;
            }

            @Override
            public void onTextChanged(String psw) {
                Log.e("PlateNumberInputActivity", "onTextChanged：" + psw);
            }

            @Override
            public void onInputFinish(String psw) {
                Log.e("PlateNumberInputActivity", "onInputFinish：" + psw);
            }
        });
    }

    @OnClick(value = {R.id.btnPasswordInput})
    void gotoPassword(View v) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (viewKeyboard.isShown()) {
                viewKeyboard.setVisibility(View.GONE);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
