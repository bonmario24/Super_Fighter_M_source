package com.xhuyu.component.widget.ucenter.views;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.language.LanguageUtil;
import com.xhuyu.component.utils.ActivityUtil;
import com.xhuyu.component.widget.TitleBar;
import com.xhuyu.component.widget.ucenter.IViewWrapper;
import com.xhuyu.component.widget.ucenter.adapter.SwitchLanguageAdapter;
import java.util.Arrays;

public class SwitchLanguageView extends IViewWrapper {
    /* access modifiers changed from: private */
    public ListView lvLanguage;

    public SwitchLanguageView(Context context) {
        super(context);
    }

    public void initView() {
        TitleBar titleBar = new TitleBar(findViewByName("rl_title_contains"));
        titleBar.setTitle(getString("huyu_switch_lan"));
        titleBar.setBackClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SwitchLanguageView.this.finish();
            }
        });
        this.lvLanguage = (ListView) findViewByName("lv_language");
        SwitchLanguageAdapter languageAdapter = new SwitchLanguageAdapter(Arrays.asList(getStringArray("languages")), getCurrentLanguage());
        languageAdapter.setOnItemClickListener(new SwitchLanguageAdapter.OnItemClickListener() {
            public void onClick(int position, final String lan) {
                try {
                    String hyAppRestart = SwitchLanguageView.this.getString("huyu_app_restart");
                    if (!CheckUtils.isNullOrEmpty(hyAppRestart)) {
                        Toast.makeText(SwitchLanguageView.this.getActivity(), hyAppRestart, 1).show();
                    }
                    SwitchLanguageView.this.lvLanguage.postDelayed(new Runnable() {
                        public void run() {
                            boolean isSwitch = LanguageUtil.getInstance().switchAppLanguage(lan, SwitchLanguageView.this.getActivity());
                            SwitchLanguageView.this.finish();
                            if (isSwitch) {
                                SwitchLanguageView.this.restartApp();
                            }
                        }
                    }, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.lvLanguage.setAdapter(languageAdapter);
    }

    /* access modifiers changed from: private */
    public void restartApp() {
        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
        if (intent != null && intent.getComponent() != null) {
            Intent makeRestartActivityTask = Intent.makeRestartActivityTask(intent.getComponent());
            intent.addFlags(67108864);
            getActivity().startActivity(intent);
            ActivityUtil.finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public String getLayoutName() {
        return "view_switch_language";
    }
}
