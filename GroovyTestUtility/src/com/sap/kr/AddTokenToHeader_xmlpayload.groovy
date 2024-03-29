import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.it.api.mapping.MappingContext;
import groovy.xml.XmlUtil;

public static void main(String[] args) {

    def body = new XmlSlurper().parse(new File("C:/Karthik/Work/Files/InputFileSessionIDFromSF.xml"));
    def sessionId = body.result.sessionId;
    Message message = new Message();
    message.setBody(body);
    processData(message);
}

def Message processData(Message message) {
    //Body
    //def body = message.getBody(java.io.Reader);
    def body = message.getBody();
    //def TokenJson = new JsonSlurper().parse(body);
    def tokenVal = body.access_token;
    message.setHeader("Authorization", "Bearer " + tokenVal);
    return message;
}
