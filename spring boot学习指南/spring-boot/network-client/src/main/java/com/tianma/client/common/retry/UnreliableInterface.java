package com.tianma.client.common.retry;

/**
 * Created by fiboliu on 16-8-26.
 * 可重试的可执行方法
 */

public interface UnreliableInterface {

    <T> T call() throws Exception;

}
