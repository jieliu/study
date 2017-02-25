package com.tianma.api.service.impl.log;

import com.tianma.api.service.log.StatisticsService;
import com.tianma.api.support.LogDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Timestamp;

/**
 * Created by zhengpeiwei on 16/5/24.
 */
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private LogDao dao;

    @Override
    public int TimeStatistic(String name,Timestamp start,Timestamp Over) {
        //从时间参数和api名字参数来选择数据，统计各种方法的调用次数
        return 0;
    }
}
