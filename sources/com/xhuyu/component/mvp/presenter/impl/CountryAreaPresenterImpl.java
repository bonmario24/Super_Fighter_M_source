package com.xhuyu.component.mvp.presenter.impl;

import com.alibaba.fastjson.JSONObject;
import com.doraemon.util.language.Language;
import com.xhuyu.component.mvp.model.CountryBean;
import com.xhuyu.component.mvp.model.CountryComparator;
import com.xhuyu.component.mvp.model.CountryModel;
import com.xhuyu.component.mvp.presenter.CountryAreaPresenter;
import com.xhuyu.component.mvp.view.CountryAreaView;
import com.xhuyu.component.utils.TextUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountryAreaPresenterImpl implements CountryAreaPresenter {
    private final CountryComparator countryComparator = new CountryComparator();
    private CountryAreaView mView;

    public CountryAreaPresenterImpl(CountryAreaView view) {
        this.mView = view;
    }

    public void doInitCountryList(String currentLan, InputStream areaCodeStream) {
        if (areaCodeStream != null) {
            try {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(areaCodeStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                while (true) {
                    String inputStr = streamReader.readLine();
                    if (inputStr == null) {
                        break;
                    }
                    responseStrBuilder.append(inputStr);
                }
                List<CountryBean> countryList = JSONObject.parseArray(responseStrBuilder.toString(), CountryBean.class);
                if (countryList != null && countryList.size() > 0) {
                    List<CountryModel> countryModelList = new ArrayList<>();
                    for (CountryBean cb : countryList) {
                        countryModelList.add(getLanCountry(currentLan, cb));
                    }
                    Collections.sort(countryModelList, this.countryComparator);
                    this.mView.showCountryList(countryModelList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<CountryModel> searchContent(String searchContent, List<CountryModel> list) {
        List<CountryModel> filterList = new ArrayList<>();
        for (CountryModel countryModel : list) {
            if ((countryModel.getCountryName().equals(searchContent) || countryModel.getCountryName().contains(searchContent) || countryModel.getCountryName().toLowerCase().contains(searchContent.toLowerCase()) || countryModel.getAreaCode().equals(searchContent) || countryModel.getAreaCode().contains(searchContent)) && !filterList.contains(countryModel)) {
                filterList.add(countryModel);
            }
        }
        return filterList;
    }

    private CountryModel getLanCountry(String currentLan, CountryBean cb) {
        String countryName;
        String sortLetter;
        CountryModel countryModel = new CountryModel();
        countryModel.setAreaCode(cb.getCode());
        char c = 65535;
        switch (currentLan.hashCode()) {
            case -372468771:
                if (currentLan.equals("zh-Hans")) {
                    c = 1;
                    break;
                }
                break;
            case 3241:
                if (currentLan.equals(Language.f807en)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                countryName = cb.getEn();
                sortLetter = TextUtil.getSortLetterBySortKey(countryName);
                break;
            case 1:
                countryName = cb.getZhHans();
                sortLetter = TextUtil.getSortLetterBySortKey(cb.getHansPinYin());
                break;
            default:
                countryName = cb.getZhHant();
                sortLetter = cb.getIndexStrokeNum() + "";
                countryModel.setHasStroke(true);
                break;
        }
        countryModel.setCountryName(countryName);
        countryModel.setSortLetters(sortLetter);
        countryModel.setAbbreviation(cb.getAbbreviation());
        return countryModel;
    }

    public boolean isHantLan(String currentLan) {
        return "zh-Hant".equals(currentLan);
    }
}
