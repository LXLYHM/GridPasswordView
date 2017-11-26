package com.jungly.gridpasswordview.demo;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Spinner;

import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.kw.lib.ui.keyboardview.XKeyboardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnItemSelected;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gpv_length)
    GridPasswordView gpvLength;
    @BindView(R.id.gpv_transformation)
    GridPasswordView gpvTransformation;
    @BindView(R.id.gpv_passwordType)
    GridPasswordView gpvPasswordType;
    @BindView(R.id.gpv_customUi)
    GridPasswordView gpvCustomUi;
    @BindView(R.id.gpv_normail_twice)
    GridPasswordView gpvNormalTwice;
    @BindView(R.id.gpvPlateNumber)
    GridPasswordView gpvPlateNumber;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pswtype_sp)
    Spinner pswtypeSp;
    @BindView(R.id.view_keyboard)
    XKeyboardView viewKeyboard;

    boolean isFirst = true;
    String firstPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        testPlateNumberInput();
        onPwdChangedTest();
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
                    viewKeyboard.setKeyboard(new Keyboard(MainActivity.this, R.xml.provice));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 1 && position < 2) {
                    viewKeyboard.setKeyboard(new Keyboard(MainActivity.this, R.xml.english));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 2 && position < 6) {
                    viewKeyboard.setKeyboard(new Keyboard(MainActivity.this, R.xml.qwerty_without_chinese));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                } else if (position >= 6 && position < 7) {
                    viewKeyboard.setKeyboard(new Keyboard(MainActivity.this, R.xml.qwerty));
                    viewKeyboard.setVisibility(View.VISIBLE);
                    return true;
                }
                viewKeyboard.setVisibility(View.GONE);
                return false;
            }

            @Override
            public void onTextChanged(String psw) {
                Log.e("MainActivity","onTextChanged：" + psw);
            }

            @Override
            public void onInputFinish(String psw) {
                Log.e("MainActivity","onInputFinish：" + psw);
            }
        });
    }

    @OnCheckedChanged(R.id.psw_visibility_switcher)
    void onCheckedChanged(boolean isChecked) {
        gpvPasswordType.togglePasswordVisibility();
    }

    @OnItemSelected(R.id.pswtype_sp)
    void onTypeSelected(int position) {
        switch (position) {
            case 0:
                gpvPasswordType.setPasswordType(PasswordType.NUMBER);
                break;

            case 1:
                gpvPasswordType.setPasswordType(PasswordType.TEXT);
                break;

            case 2:
                gpvPasswordType.setPasswordType(PasswordType.TEXTVISIBLE);
                break;

            case 3:
                gpvPasswordType.setPasswordType(PasswordType.TEXTWEB);
                break;
        }

    }

    // Test GridPasswordView.clearPassword() in OnPasswordChangedListener.
    // Need enter the password twice and then check the password , like Alipay
    void onPwdChangedTest() {
        gpvNormalTwice.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public boolean beforeInput(int position) {
                return false;
            }

            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6 && isFirst) {
                    gpvNormalTwice.clearPassword();
                    isFirst = false;
                    firstPwd = psw;
                } else if (psw.length() == 6 && !isFirst) {
                    if (psw.equals(firstPwd)) {
                        Log.d("MainActivity", "The password is: " + psw);
                    } else {
                        Log.d("MainActivity", "password doesn't match the previous one, try again!");
                        gpvNormalTwice.clearPassword();
                        isFirst = true;
                    }
                }
            }

            @Override
            public void onInputFinish(String psw) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
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
