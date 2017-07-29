import org.zoolu.sip.address.NameAddress;
import org.zoolu.sip.address.SipURL;
import org.zoolu.sip.message.Message;
import org.zoolu.sip.message.MessageFactory;
import org.zoolu.sip.provider.SipProvider;

/**
 * Author chzjy
 * Date 2017/5/18.
 */
public class SipMessageFactory extends MessageFactory {
    private static SipURL requestUri = new SipURL("101.69.255.132", 6061);

    public static Message createRegisterRequest(SipProvider sip_provider, NameAddress to, NameAddress from) {
        String via_addr = sip_provider.getViaAddress();
        int host_port = sip_provider.getPort();
        SipURL sipURL = new SipURL(via_addr, host_port);
        NameAddress contact = new NameAddress(sipURL);
        return createRegisterRequest(sip_provider, requestUri, to, from, contact);
    }

    public static Message createRegisterRequest(SipProvider sip_provider,  //注册第二步；心跳
                                                NameAddress to, NameAddress from, String body) {
        Message msg = createRegisterRequest(sip_provider, to, from);
        msg.setBody("application/xml", body);
        return msg;
    }
}
