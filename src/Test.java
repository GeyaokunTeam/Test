import org.zoolu.sip.address.NameAddress;
import org.zoolu.sip.address.SipURL;
import org.zoolu.sip.message.Message;

import java.util.Random;

/**
 * Author chzjy
 * Date 2017/5/18.
 */
public class Test {
    public static String REGISTER_ID = "330100000010000190";
    public static String serverIp = "112.124.122.216";
    public static String SERVER_ID = "330100000010000090";
    public static NameAddress user_from;
    //
    public static NameAddress user_to;

    public static void resgister() {
        int hostPort = new Random().nextInt(5000) + 2000;
        Sip sip = new Sip(null, hostPort);
        SipURL local = new SipURL(REGISTER_ID, serverIp, 6061);
        SipURL remote = new SipURL(SERVER_ID, serverIp, 6061);
        user_from = new NameAddress("3245074", local);
        user_to = new NameAddress("rvsup", remote);
        Message register = SipMessageFactoryForHaiKang.getInstance().createRegisterRequest(
                sip, user_to, user_from);
        sip.sendMessage(register);
    }

    public static void main(String[] args) {
        resgister();
    }
}
