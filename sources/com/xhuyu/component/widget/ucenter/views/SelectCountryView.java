package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.doraemon.p027eg.CheckUtils;
import com.xhuyu.component.callback.BaseTextWatcher;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.mvp.presenter.impl.CountryAreaPresenterImpl;
import com.xhuyu.component.mvp.view.CountryAreaView;
import com.xhuyu.component.widget.LoadingDialog;
import com.xhuyu.component.widget.TitleBar;
import com.xhuyu.component.widget.ucenter.IViewWrapper;
import com.xhuyu.component.widget.ucenter.adapter.CountryAreaAdapter;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.CheckUtil;
import com.xsdk.doraemon.widget.sidebar.BaseSideBar;
import com.xsdk.doraemon.widget.sidebar.LetterSideBar;
import com.xsdk.doraemon.widget.sidebar.StrokeSideBar;
import java.util.ArrayList;
import java.util.List;

public class SelectCountryView extends IViewWrapper implements CountryAreaView {
    /* access modifiers changed from: private */
    public EditText autoTvSearch;
    /* access modifiers changed from: private */
    public CountryAreaAdapter countryAreaAdapter;
    /* access modifiers changed from: private */
    public ImageView ivDelete;
    /* access modifiers changed from: private */
    public ListView lvSearch;
    /* access modifiers changed from: private */
    public List<CountryModel> mAllCountryList;
    /* access modifiers changed from: private */
    public CountryAreaPresenterImpl mAreaPresenter;
    private LoadingDialog showLoading;
    BaseSideBar sideBar;
    private TitleBar titleBar;

    public SelectCountryView(Context context) {
        super(context);
    }

    public void initView() {
        this.mAreaPresenter = new CountryAreaPresenterImpl(this);
        this.titleBar = new TitleBar(findViewByName("rl_title_contains"));
        this.titleBar.setTitle(getString("huyu_select_country_or_area"));
        this.titleBar.setBackClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelectCountryView.this.finish();
            }
        });
        this.autoTvSearch = (EditText) findViewByName("auto_tv_search");
        this.lvSearch = (ListView) findViewByName("lv_search");
        this.ivDelete = (ImageView) findViewByName("iv_delete");
        TextView countryDialog = (TextView) findViewByName("country_dialog");
        LinearLayout containsSide = (LinearLayout) findViewByName("contains_side");
        String language = getString("xf_lan");
        if (this.mAreaPresenter.isHantLan(language)) {
            this.sideBar = new StrokeSideBar(getActivity());
        } else {
            this.sideBar = new LetterSideBar(getActivity());
        }
        this.sideBar.setTextView(countryDialog);
        this.sideBar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        containsSide.addView(this.sideBar);
        this.mAreaPresenter.doInitCountryList(language, ReflectResource.getInstance(getContext()).getRawFile("area_code"));
        setListener();
    }

    private void setListener() {
        this.ivDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SelectCountryView.this.autoTvSearch.setText("");
                SelectCountryView.this.countryAreaAdapter.updateListView(SelectCountryView.this.mAllCountryList);
            }
        });
        this.autoTvSearch.addTextChangedListener(new BaseTextWatcher() {
            public void afterTextChanged(Editable s) {
                String searchContent = SelectCountryView.this.autoTvSearch.getText().toString();
                if (searchContent.equals("")) {
                    SelectCountryView.this.ivDelete.setVisibility(4);
                } else {
                    SelectCountryView.this.ivDelete.setVisibility(0);
                }
                if (searchContent.length() > 0) {
                    SelectCountryView.this.countryAreaAdapter.updateListView((ArrayList) SelectCountryView.this.mAreaPresenter.searchContent(searchContent, SelectCountryView.this.mAllCountryList));
                } else {
                    SelectCountryView.this.countryAreaAdapter.updateListView(SelectCountryView.this.mAllCountryList);
                }
                SelectCountryView.this.lvSearch.setSelection(0);
            }
        });
        this.sideBar.setOnTouchingLetterChangedListener(new BaseSideBar.OnTouchingLetterChangedListener() {
            public void onTouchingLetterChanged(String s) {
                int position;
                if (CheckUtil.isInteger(s)) {
                    position = SelectCountryView.this.countryAreaAdapter.getPositionForSection(Integer.parseInt(s));
                } else {
                    position = SelectCountryView.this.countryAreaAdapter.getPositionForSection(s.charAt(0));
                }
                if (position != -1) {
                    SelectCountryView.this.lvSearch.setSelection(position);
                }
            }
        });
        this.lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CountryModel countryModel;
                String searchContent = SelectCountryView.this.autoTvSearch.getText().toString();
                if (searchContent.length() > 0) {
                    countryModel = ((ArrayList) SelectCountryView.this.mAreaPresenter.searchContent(searchContent, SelectCountryView.this.mAllCountryList)).get(position);
                } else {
                    countryModel = (CountryModel) SelectCountryView.this.mAllCountryList.get(position);
                }
                SDKEventPost.post(16, countryModel);
                SelectCountryView.this.finish();
            }
        });
    }

    public String getLayoutName() {
        return "view_search_area";
    }

    public void showToastMessage(boolean fromRes, String message) {
        String msg = message;
        if (fromRes && !CheckUtils.isNullOrEmpty(msg)) {
            msg = getString(message);
        }
        showToast(msg);
    }

    public void showCountryList(List<CountryModel> countryList) {
        this.mAllCountryList = countryList;
        this.countryAreaAdapter = new CountryAreaAdapter(this.mAllCountryList);
        this.lvSearch.setAdapter(this.countryAreaAdapter);
    }

    public void showDialog() {
        this.showLoading = showLoading((String) null);
    }

    public void closeLoadingDialog() {
        if (this.showLoading != null) {
            this.showLoading.dismiss();
        }
    }
}
