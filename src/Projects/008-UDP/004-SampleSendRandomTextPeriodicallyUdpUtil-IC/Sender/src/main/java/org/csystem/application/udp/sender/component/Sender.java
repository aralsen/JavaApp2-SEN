package org.csystem.application.udp.sender.component;

import org.csystem.util.console.Console;
import org.csystem.util.converter.BitConverter;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

@Component
@EnableScheduling
public class Sender {
    private final DatagramSocket m_datagramSocket;

    @Value("${sender.host}")
    private String m_host;

    @Value("${sender.port}")
    private int m_port;
    @Value("${sender.random.min}")
    private int m_min;

    @Value("${sender.random.max}")
    private int m_max;

    public Sender(DatagramSocket datagramSocket)
    {
        m_datagramSocket = datagramSocket;
    }

    @Scheduled(fixedRate = 50)
    public void run()
    {
        var random = new Random();

        try {
            var text = StringUtil.getRandomTextTR(random, random.nextInt(m_max - m_min + 1) + m_min);

            var buf = BitConverter.getBytes(text);

            var datagramPacket = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName(m_host), m_port);

            m_datagramSocket.send(datagramPacket);
        }
        catch (IOException ex) {
            Console.Error.writeLine("IOException:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Throwable:%s", ex.getMessage());
        }
    }
}
