package com.tianma.api.service.log;

import java.security.Timestamp;

/**
 * Created by zhengpeiwei on 16/5/24.
 */
public interface StatisticsService {

    public int TimeStatistic(String name, Timestamp start, Timestamp Over);

}
