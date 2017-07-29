import org.zoolu.sip.message.Message;
import org.zoolu.sip.provider.SipProvider;
import org.zoolu.sip.provider.Transport;
import org.zoolu.sip.provider.TransportConnId;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author chzjy
 * Date 2017/5/18.
 */
public class Sip extends SipProvider {
    private ExecutorService pool = Executors.newFixedThreadPool(3);

    public Sip(String via_addr, int host_port) {
        super(via_addr, host_port);
    }

    public TransportConnId sendMessage(Message msg) {
        return sendMessage(msg, "112.124.122.216", 6061);
    }

    public TransportConnId sendMessage(final Message msg, final String destAddr, final int destPort) {
        TransportConnId id = null;
        try {
            id = pool.submit(new Callable<TransportConnId>() {
                public TransportConnId call() {
                    return sendMessage(msg, "udp", destAddr, destPort, 0);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("<----------send sip message---------->");
        System.out.println(msg.toString());
        return id;
    }

    public synchronized void onReceivedMessage(Transport transport, Message msg) {
        System.out.println("<----------received sip message---------->");
        System.out.println(msg.toString());
    }
}
