package com.tianma.api.service.impl.log;

import com.tianma.api.service.log.LogService;
import com.tianma.api.support.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengpeiwei on 16/5/24.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao dao;

    @Override
    public int log(String method,String who) {
        Map map=new HashMap<>();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        map.put("time",ts);
        map.put("method",method);
        String insertwho="."+who;
        return dao.insert("egoo.insertlog",insertwho,map);
    }
}
