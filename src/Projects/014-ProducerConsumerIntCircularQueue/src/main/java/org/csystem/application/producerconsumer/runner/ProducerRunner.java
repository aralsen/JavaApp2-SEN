package org.csystem.application.producerconsumer.runner;

import org.csystem.application.producerconsumer.component.SharedObject;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@Component
public class ProducerRunner implements ApplicationRunner {
    private final ExecutorService m_threadPool;
    private final SharedObject m_sharedObject;
    private final Random m_random;

    @Value("${sleep.min:0}")
    private long m_min;

    @Value("${sleep.max:1}")
    private long m_max;

    @Value("${produce.count:99}")
    private int m_count;

    private void producerCallback()
    {
        int i = 0;

        for (; ; ) {
            ThreadUtil.sleep(Math.abs(m_random.nextLong()) % (m_max - m_min + 1) + m_min);
            m_sharedObject.setVal(i);
            if (i == m_count)
                break;
            ++i;
        }

        m_threadPool.shutdown();
    }

    public ProducerRunner(ExecutorService threadPool, SharedObject sharedObject, Random random)
    {
        m_threadPool = threadPool;
        m_sharedObject = sharedObject;
        m_random = random;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.execute(this::producerCallback);
    }
}
