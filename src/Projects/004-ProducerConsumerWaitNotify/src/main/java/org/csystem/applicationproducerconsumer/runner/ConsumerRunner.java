package org.csystem.applicationproducerconsumer.runner;

import org.csystem.applicationproducerconsumer.component.SharedObject;
import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Component
public class ConsumerRunner implements ApplicationRunner {
    private final ExecutorService m_threadPool;
    private final SharedObject m_sharedObject;
    private final Random m_random;

    @Value("${produce.count:99}")
    private int m_count;

    private void consumerCallback()
    {
        int val;

        for (;;) {
            val = m_sharedObject.getVal();
            Console.write("%d ", val);
            if (val == m_count)
                break;
        }

        m_threadPool.shutdown();
    }

    public ConsumerRunner(ExecutorService threadPool, SharedObject sharedObject, Random random)
    {
        m_threadPool = threadPool;
        m_sharedObject = sharedObject;
        m_random = random;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_threadPool.execute(this::consumerCallback);
    }
}
