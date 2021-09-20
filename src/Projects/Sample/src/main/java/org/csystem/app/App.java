package org.csystem.app;

import org.csystem.util.console.Console;

public class App{
    public static void main(String[] args)
    {

        var thread = new Thread(() -> {
            var self = Thread.currentThread();
            self.setPriority(Thread.MAX_PRIORITY);

            Console.writeLine(self.isDaemon() ? "Deamon" : "Non-daemon");

            for (int i = 0; i < 10; ++i) {
                Console.write("%d ", i);
            }

            Console.writeLine();
        });

        thread.start();
    }
}
