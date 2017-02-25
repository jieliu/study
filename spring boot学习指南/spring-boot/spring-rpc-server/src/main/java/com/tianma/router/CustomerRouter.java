package com.tianma.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

/**
 * Created by fiboliu on 16-8-23.
 * 自定义路由规则
 */
public class CustomerRouter implements RouterFactory {

    @Override
    public Router getRouter(URL url) {

        return null;
    }

}
