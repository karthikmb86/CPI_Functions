import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.xml.XmlUtil;

def Message processData(Message message) {
    //Body 
       
       def body = message.getBody();
       def root = new XmlSlurper.parse(body);
       def sessionId = root.result.sessionId;;
       message.setHeader("Authorization", "bearer " + sessionId);
       
       def prop = message.getProperties();
       originalPayload = prop.get("originalPayload");
       message.setBody(originalPayload);
       return message;
}
