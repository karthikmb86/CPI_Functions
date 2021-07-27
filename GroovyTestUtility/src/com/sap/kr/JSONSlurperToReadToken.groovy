import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper;

def Message processData(Message message) {
    //Body 
       
       def body = message.getBody();
       def root = new JsonSlurper().parse(body);
       def token = root.access_token;
       message.setHeader("Authorization", "bearer " + token);
       
       def prop = message.getProperties();
       originalPayload = prop.get("originalPayload");
       message.setBody(originalPayload);
       return message;
}
