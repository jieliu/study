package com.tianma.client.common.retry.impl;

import com.tianma.client.common.retry.RetryStrategy;
import com.tianma.client.common.retry.UnreliableInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fiboliu on 16-8-26.
 */
public class RetryStrategyRoundBin implements RetryStrategy {

    private static final Logger logger = LoggerFactory.getLogger(RetryStrategyRoundBin.class);

    /**
     * @param unreliableImpl
     * @param retryTimes
     * @param sleepSeconds
     */
    public <T> T retry(UnreliableInterface unreliableImpl, int retryTimes, int sleepSeconds) throws Exception {

        int cur_time = 0;
        for (; cur_time < retryTimes; ++cur_time) {

            try {

                return unreliableImpl.call();

            } catch (Exception e) {

                logger.warn("cannot reach, will retry " + cur_time + " .... " + e.toString());

                try {
                    Thread.sleep(sleepSeconds * 1000);
                } catch (InterruptedException e1) {

                }
            }
        }

        logger.warn("finally failed....");

        throw new Exception();
    }
}
