package com.thinkfly.cloudlader;

import com.thinkfly.cloudlader.data.DBData;
import java.util.List;

public interface Interceptor {
    List<DBData> intercept(List<DBData> list);

    boolean isIntercept();
}
