package com.xhuyu.component.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.xhuyu.component.callback.BaseTextWatcher;
import com.xhuyu.component.callback.OnMultiClickListener;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class ViewUtil {
    public static boolean saveViewBitmap(View view, String path) {
        SDKLoggerUtil.getLogger().mo19501d("修改保存图片" + path, new Object[0]);
        if (view == null || TextUtils.isEmpty(path)) {
            return false;
        }
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        if (path.endsWith(".png")) {
            format = Bitmap.CompressFormat.PNG;
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), format == Bitmap.CompressFormat.JPEG ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return FileUtil.writeBitmap(bitmap, format, 100, path);
    }

    public static void bindButtonEnable(final Button btn, final EditText[] edts) {
        BaseTextWatcher watcher = new BaseTextWatcher() {
            private Button vbtn = btn;
            private EditText[] views = edts;

            public void afterTextChanged(Editable s) {
                boolean enable = true;
                int i = 0;
                while (true) {
                    if (i >= this.views.length) {
                        break;
                    } else if (TextUtils.isEmpty(this.views[i].getText())) {
                        enable = false;
                        break;
                    } else {
                        i++;
                    }
                }
                this.vbtn.setEnabled(enable);
            }
        };
        for (EditText addTextChangedListener : edts) {
            addTextChangedListener.addTextChangedListener(watcher);
        }
    }

    public static void bindViewVisiable(final View view, final EditText[] edts) {
        BaseTextWatcher watcher = new BaseTextWatcher() {
            private View target = view;
            private EditText[] views = edts;

            public void afterTextChanged(Editable s) {
                int visible = 0;
                int i = 0;
                while (true) {
                    if (i >= this.views.length) {
                        break;
                    } else if (TextUtils.isEmpty(this.views[i].getText())) {
                        visible = 8;
                        break;
                    } else {
                        i++;
                    }
                }
                this.target.setVisibility(visible);
            }
        };
        for (EditText addTextChangedListener : edts) {
            addTextChangedListener.addTextChangedListener(watcher);
        }
    }

    public static void bindPassowrdVisiable(final ImageView ivEye, final EditText edtPwd) {
        ivEye.setOnClickListener(new OnMultiClickListener() {
            public void onMultiClick(View view) {
                ivEye.setSelected(!ivEye.isSelected());
                edtPwd.setTransformationMethod(ivEye.isSelected() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                edtPwd.setSelection(edtPwd.getEditableText().length());
            }
        });
    }

    public static void bindFocusVisiable(final EditText editText, final View view) {
        if (editText != null && view != null) {
            editText.addTextChangedListener(new BaseTextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s) || !editText.isFocused()) {
                        view.setVisibility(8);
                    } else {
                        view.setVisibility(0);
                    }
                }
            });
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (TextUtils.isEmpty(editText.getText()) || !hasFocus) {
                        view.setVisibility(8);
                    } else {
                        view.setVisibility(0);
                    }
                    Log.d("shen", editText + "---onFocusChange------" + hasFocus);
                }
            });
            view.setOnClickListener(new OnMultiClickListener() {
                public void onMultiClick(View view) {
                    editText.setText("");
                }
            });
        }
    }
}
