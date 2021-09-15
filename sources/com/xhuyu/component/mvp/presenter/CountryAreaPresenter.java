package com.xhuyu.component.mvp.presenter;

import com.xhuyu.component.mvp.model.CountryModel;
import java.io.InputStream;
import java.util.List;

public interface CountryAreaPresenter {
    void doInitCountryList(String str, InputStream inputStream);

    List<CountryModel> searchContent(String str, List<CountryModel> list);
}
