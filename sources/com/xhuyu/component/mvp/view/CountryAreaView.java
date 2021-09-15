package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;
import com.xhuyu.component.mvp.model.CountryModel;
import java.util.List;

public interface CountryAreaView extends BaseView {
    void showCountryList(List<CountryModel> list);

    void showToastMessage(boolean z, String str);
}
