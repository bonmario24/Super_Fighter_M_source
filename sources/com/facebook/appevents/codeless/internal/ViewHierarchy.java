package com.facebook.appevents.codeless.internal;

import android.support.annotation.Nullable;
import android.support.p000v4.view.NestedScrollingChild;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewHierarchy {
    private static final int ADAPTER_VIEW_ITEM_BITMASK = 9;
    private static final int BUTTON_BITMASK = 2;
    private static final int CHECKBOX_BITMASK = 15;
    private static final String CHILDREN_VIEW_KEY = "childviews";
    private static final String CLASS_NAME_KEY = "classname";
    private static final String CLASS_RCTROOTVIEW = "com.facebook.react.ReactRootView";
    private static final String CLASS_RCTTEXTVIEW = "com.facebook.react.views.view.ReactTextView";
    private static final String CLASS_RCTVIEWGROUP = "com.facebook.react.views.view.ReactViewGroup";
    private static final String CLASS_TOUCHTARGETHELPER = "com.facebook.react.uimanager.TouchTargetHelper";
    private static final String CLASS_TYPE_BITMASK_KEY = "classtypebitmask";
    private static final int CLICKABLE_VIEW_BITMASK = 5;
    private static final String DESC_KEY = "description";
    private static final String DIMENSION_HEIGHT_KEY = "height";
    private static final String DIMENSION_KEY = "dimension";
    private static final String DIMENSION_LEFT_KEY = "left";
    private static final String DIMENSION_SCROLL_X_KEY = "scrollx";
    private static final String DIMENSION_SCROLL_Y_KEY = "scrolly";
    private static final String DIMENSION_TOP_KEY = "top";
    private static final String DIMENSION_VISIBILITY_KEY = "visibility";
    private static final String DIMENSION_WIDTH_KEY = "width";
    private static final String GET_ACCESSIBILITY_METHOD = "getAccessibilityDelegate";
    private static final String HINT_KEY = "hint";
    private static final String ICON_BITMAP = "icon_image";
    private static final int ICON_MAX_EDGE_LENGTH = 44;
    private static final String ID_KEY = "id";
    private static final int IMAGEVIEW_BITMASK = 1;
    private static final int INPUT_BITMASK = 11;
    private static final String IS_USER_INPUT_KEY = "is_user_input";
    private static final int LABEL_BITMASK = 10;
    private static final String METHOD_FIND_TOUCHTARGET_VIEW = "findTouchTargetView";
    private static final int PICKER_BITMASK = 12;
    private static final int RADIO_GROUP_BITMASK = 14;
    private static final int RATINGBAR_BITMASK = 16;
    private static WeakReference<View> RCTRootViewReference = new WeakReference<>((Object) null);
    private static final int REACT_NATIVE_BUTTON_BITMASK = 6;
    private static final int SWITCH_BITMASK = 13;
    private static final String TAG = ViewHierarchy.class.getCanonicalName();
    private static final String TAG_KEY = "tag";
    private static final int TEXTVIEW_BITMASK = 0;
    private static final String TEXT_IS_BOLD = "is_bold";
    private static final String TEXT_IS_ITALIC = "is_italic";
    private static final String TEXT_KEY = "text";
    private static final String TEXT_SIZE = "font_size";
    private static final String TEXT_STYLE = "text_style";
    @Nullable
    private static Method methodFindTouchTargetView = null;

    @Nullable
    public static ViewGroup getParentOfView(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            return (ViewGroup) parent;
        }
        return null;
    }

    public static List<View> getChildrenOfView(View view) {
        ArrayList<View> children = new ArrayList<>();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                children.add(viewGroup.getChildAt(i));
            }
        }
        return children;
    }

    public static JSONObject setBasicInfoOfView(View view, JSONObject json) {
        try {
            String text = getTextOfView(view);
            String hint = getHintOfView(view);
            Object tag = view.getTag();
            CharSequence description = view.getContentDescription();
            json.put(CLASS_NAME_KEY, view.getClass().getCanonicalName());
            json.put(CLASS_TYPE_BITMASK_KEY, getClassTypeBitmask(view));
            json.put("id", view.getId());
            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                json.put(TEXT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(text), ""));
            } else {
                json.put(TEXT_KEY, "");
                json.put(IS_USER_INPUT_KEY, true);
            }
            json.put(HINT_KEY, Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(hint), ""));
            if (tag != null) {
                json.put("tag", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(tag.toString()), ""));
            }
            if (description != null) {
                json.put("description", Utility.coerceValueIfNullOrEmpty(Utility.sha256hash(description.toString()), ""));
            }
            json.put(DIMENSION_KEY, getDimensionOfView(view));
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
        return json;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r9 = (android.widget.TextView) r15;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject setAppearanceOfView(android.view.View r15, org.json.JSONObject r16, float r17) {
        /*
            r14 = 1110441984(0x42300000, float:44.0)
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0083 }
            r8.<init>()     // Catch:{ JSONException -> 0x0083 }
            boolean r11 = r15 instanceof android.widget.TextView     // Catch:{ JSONException -> 0x0083 }
            if (r11 == 0) goto L_0x0038
            r0 = r15
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch:{ JSONException -> 0x0083 }
            r9 = r0
            android.graphics.Typeface r10 = r9.getTypeface()     // Catch:{ JSONException -> 0x0083 }
            if (r10 == 0) goto L_0x0038
            java.lang.String r11 = "font_size"
            float r12 = r9.getTextSize()     // Catch:{ JSONException -> 0x0083 }
            double r12 = (double) r12     // Catch:{ JSONException -> 0x0083 }
            r8.put(r11, r12)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = "is_bold"
            boolean r12 = r10.isBold()     // Catch:{ JSONException -> 0x0083 }
            r8.put(r11, r12)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = "is_italic"
            boolean r12 = r10.isItalic()     // Catch:{ JSONException -> 0x0083 }
            r8.put(r11, r12)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = "text_style"
            r0 = r16
            r0.put(r11, r8)     // Catch:{ JSONException -> 0x0083 }
        L_0x0038:
            boolean r11 = r15 instanceof android.widget.ImageView     // Catch:{ JSONException -> 0x0083 }
            if (r11 == 0) goto L_0x0082
            r0 = r15
            android.widget.ImageView r0 = (android.widget.ImageView) r0     // Catch:{ JSONException -> 0x0083 }
            r11 = r0
            android.graphics.drawable.Drawable r5 = r11.getDrawable()     // Catch:{ JSONException -> 0x0083 }
            boolean r11 = r5 instanceof android.graphics.drawable.BitmapDrawable     // Catch:{ JSONException -> 0x0083 }
            if (r11 == 0) goto L_0x0082
            int r11 = r15.getHeight()     // Catch:{ JSONException -> 0x0083 }
            float r11 = (float) r11     // Catch:{ JSONException -> 0x0083 }
            float r11 = r11 / r17
            int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r11 > 0) goto L_0x0082
            int r11 = r15.getWidth()     // Catch:{ JSONException -> 0x0083 }
            float r11 = (float) r11     // Catch:{ JSONException -> 0x0083 }
            float r11 = r11 / r17
            int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r11 > 0) goto L_0x0082
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5     // Catch:{ JSONException -> 0x0083 }
            android.graphics.Bitmap r2 = r5.getBitmap()     // Catch:{ JSONException -> 0x0083 }
            if (r2 == 0) goto L_0x0082
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ JSONException -> 0x0083 }
            r4.<init>()     // Catch:{ JSONException -> 0x0083 }
            android.graphics.Bitmap$CompressFormat r11 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ JSONException -> 0x0083 }
            r12 = 100
            r2.compress(r11, r12, r4)     // Catch:{ JSONException -> 0x0083 }
            byte[] r3 = r4.toByteArray()     // Catch:{ JSONException -> 0x0083 }
            r11 = 0
            java.lang.String r7 = android.util.Base64.encodeToString(r3, r11)     // Catch:{ JSONException -> 0x0083 }
            java.lang.String r11 = "icon_image"
            r0 = r16
            r0.put(r11, r7)     // Catch:{ JSONException -> 0x0083 }
        L_0x0082:
            return r16
        L_0x0083:
            r6 = move-exception
            java.lang.String r11 = TAG
            com.facebook.internal.Utility.logd((java.lang.String) r11, (java.lang.Exception) r6)
            goto L_0x0082
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.setAppearanceOfView(android.view.View, org.json.JSONObject, float):org.json.JSONObject");
    }

    public static JSONObject getDictionaryOfView(View view) {
        if (view.getClass().getName().equals(CLASS_RCTROOTVIEW)) {
            RCTRootViewReference = new WeakReference<>(view);
        }
        JSONObject json = new JSONObject();
        try {
            json = setBasicInfoOfView(view, json);
            JSONArray childViews = new JSONArray();
            List<View> children = getChildrenOfView(view);
            for (int i = 0; i < children.size(); i++) {
                childViews.put(getDictionaryOfView(children.get(i)));
            }
            json.put(CHILDREN_VIEW_KEY, childViews);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for view.", e);
        }
        return json;
    }

    private static int getClassTypeBitmask(View view) {
        int bitmask = 0;
        if (view instanceof ImageView) {
            bitmask = 0 | 2;
        }
        if (isClickableView(view)) {
            bitmask |= 32;
        }
        if (isAdapterViewItem(view)) {
            bitmask |= 512;
        }
        if (view instanceof TextView) {
            int bitmask2 = bitmask | 1024 | 1;
            if (view instanceof Button) {
                bitmask2 |= 4;
                if (view instanceof Switch) {
                    bitmask2 |= 8192;
                } else if (view instanceof CheckBox) {
                    bitmask2 |= 32768;
                }
            }
            if (view instanceof EditText) {
                return bitmask2 | 2048;
            }
            return bitmask2;
        } else if ((view instanceof Spinner) || (view instanceof DatePicker)) {
            return bitmask | 4096;
        } else {
            if (view instanceof RatingBar) {
                return bitmask | 65536;
            }
            if (view instanceof RadioGroup) {
                return bitmask | 16384;
            }
            if (!(view instanceof ViewGroup) || !isRCTButton(view, (View) RCTRootViewReference.get())) {
                return bitmask;
            }
            return bitmask | 64;
        }
    }

    public static boolean isClickableView(View view) {
        Field listenerField;
        boolean z = true;
        try {
            Field listenerInfoField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (listenerInfoField == null) {
                return false;
            }
            listenerInfoField.setAccessible(true);
            Object listenerObj = listenerInfoField.get(view);
            if (listenerObj == null || (listenerField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener")) == null) {
                return false;
            }
            if (((View.OnClickListener) listenerField.get(listenerObj)) == null) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            Log.e(TAG, "Failed to check if the view is clickable.", e);
            return false;
        }
    }

    private static boolean isAdapterViewItem(View view) {
        ViewParent parent = view.getParent();
        return (parent instanceof AdapterView) || (parent instanceof NestedScrollingChild);
    }

    public static String getTextOfView(View view) {
        Object selectedItem;
        CharSequence textObj = null;
        if (view instanceof TextView) {
            textObj = ((TextView) view).getText();
            if (view instanceof Switch) {
                textObj = ((Switch) view).isChecked() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
        } else if (view instanceof Spinner) {
            if (((Spinner) view).getCount() > 0 && (selectedItem = ((Spinner) view).getSelectedItem()) != null) {
                textObj = selectedItem.toString();
            }
        } else if (view instanceof DatePicker) {
            DatePicker picker = (DatePicker) view;
            textObj = String.format("%04d-%02d-%02d", new Object[]{Integer.valueOf(picker.getYear()), Integer.valueOf(picker.getMonth()), Integer.valueOf(picker.getDayOfMonth())});
        } else if (view instanceof TimePicker) {
            TimePicker picker2 = (TimePicker) view;
            textObj = String.format("%02d:%02d", new Object[]{Integer.valueOf(picker2.getCurrentHour().intValue()), Integer.valueOf(picker2.getCurrentMinute().intValue())});
        } else if (view instanceof RadioGroup) {
            RadioGroup radioGroup = (RadioGroup) view;
            int checkedId = radioGroup.getCheckedRadioButtonId();
            int childCount = radioGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View child = radioGroup.getChildAt(i);
                if (child.getId() == checkedId && (child instanceof RadioButton)) {
                    textObj = ((RadioButton) child).getText();
                    break;
                }
                i++;
            }
        } else if (view instanceof RatingBar) {
            textObj = String.valueOf(((RatingBar) view).getRating());
        }
        if (textObj == null) {
            return "";
        }
        return textObj.toString();
    }

    public static String getHintOfView(View view) {
        CharSequence hintObj = null;
        if (view instanceof EditText) {
            hintObj = ((EditText) view).getHint();
        } else if (view instanceof TextView) {
            hintObj = ((TextView) view).getHint();
        }
        return hintObj == null ? "" : hintObj.toString();
    }

    private static JSONObject getDimensionOfView(View view) {
        JSONObject dimension = new JSONObject();
        try {
            dimension.put(DIMENSION_TOP_KEY, view.getTop());
            dimension.put(DIMENSION_LEFT_KEY, view.getLeft());
            dimension.put(DIMENSION_WIDTH_KEY, view.getWidth());
            dimension.put(DIMENSION_HEIGHT_KEY, view.getHeight());
            dimension.put(DIMENSION_SCROLL_X_KEY, view.getScrollX());
            dimension.put(DIMENSION_SCROLL_Y_KEY, view.getScrollY());
            dimension.put(DIMENSION_VISIBILITY_KEY, view.getVisibility());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create JSONObject for dimension.", e);
        }
        return dimension;
    }

    @Nullable
    public static View.AccessibilityDelegate getExistingDelegate(View view) {
        try {
            return (View.AccessibilityDelegate) view.getClass().getMethod(GET_ACCESSIBILITY_METHOD, new Class[0]).invoke(view, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | NullPointerException | SecurityException | InvocationTargetException e) {
            return null;
        }
    }

    @Nullable
    public static View.OnTouchListener getExistingOnTouchListener(View view) {
        try {
            Field listenerInfoField = Class.forName("android.view.View").getDeclaredField("mListenerInfo");
            if (listenerInfoField != null) {
                listenerInfoField.setAccessible(true);
            }
            Object listenerObj = listenerInfoField.get(view);
            if (listenerObj == null) {
                return null;
            }
            Field listenerField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
            if (listenerField == null) {
                return null;
            }
            listenerField.setAccessible(true);
            return (View.OnTouchListener) listenerField.get(listenerObj);
        } catch (NoSuchFieldException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        } catch (ClassNotFoundException e2) {
            Utility.logd(TAG, (Exception) e2);
            return null;
        } catch (IllegalAccessException e3) {
            Utility.logd(TAG, (Exception) e3);
            return null;
        }
    }

    @Nullable
    public static View getTouchReactView(float[] location, @Nullable View RCTRootView) {
        initTouchTargetHelperMethods();
        if (methodFindTouchTargetView == null || RCTRootView == null) {
            return null;
        }
        try {
            View nativeTargetView = (View) methodFindTouchTargetView.invoke((Object) null, new Object[]{location, RCTRootView});
            if (nativeTargetView == null || nativeTargetView.getId() <= 0) {
                return null;
            }
            View reactTargetView = (View) nativeTargetView.getParent();
            if (reactTargetView == null) {
                reactTargetView = null;
            }
            return reactTargetView;
        } catch (IllegalAccessException e) {
            Utility.logd(TAG, (Exception) e);
            return null;
        } catch (InvocationTargetException e2) {
            Utility.logd(TAG, (Exception) e2);
            return null;
        }
    }

    public static boolean isRCTButton(View view, @Nullable View RCTRootView) {
        View touchTargetView;
        if (!view.getClass().getName().equals(CLASS_RCTVIEWGROUP) || (touchTargetView = getTouchReactView(getViewLocationOnScreen(view), RCTRootView)) == null || touchTargetView.getId() != view.getId()) {
            return false;
        }
        return true;
    }

    public static boolean isRCTRootView(View view) {
        return view.getClass().getName().equals(CLASS_RCTROOTVIEW);
    }

    public static boolean isRCTTextView(View view) {
        return view.getClass().getName().equals(CLASS_RCTTEXTVIEW);
    }

    public static boolean isRCTViewGroup(View view) {
        return view.getClass().getName().equals(CLASS_RCTVIEWGROUP);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.view.View findRCTRootView(android.view.View r2) {
        /*
        L_0x0000:
            if (r2 == 0) goto L_0x0015
            boolean r1 = isRCTRootView(r2)
            if (r1 == 0) goto L_0x0009
        L_0x0008:
            return r2
        L_0x0009:
            android.view.ViewParent r0 = r2.getParent()
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L_0x0015
            r2 = r0
            android.view.View r2 = (android.view.View) r2
            goto L_0x0000
        L_0x0015:
            r2 = 0
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.internal.ViewHierarchy.findRCTRootView(android.view.View):android.view.View");
    }

    private static float[] getViewLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new float[]{(float) location[0], (float) location[1]};
    }

    private static void initTouchTargetHelperMethods() {
        if (methodFindTouchTargetView == null) {
            try {
                methodFindTouchTargetView = Class.forName(CLASS_TOUCHTARGETHELPER).getDeclaredMethod(METHOD_FIND_TOUCHTARGET_VIEW, new Class[]{float[].class, ViewGroup.class});
                methodFindTouchTargetView.setAccessible(true);
            } catch (ClassNotFoundException e) {
                Utility.logd(TAG, (Exception) e);
            } catch (NoSuchMethodException e2) {
                Utility.logd(TAG, (Exception) e2);
            }
        }
    }
}
