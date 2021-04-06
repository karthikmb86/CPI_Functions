import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.it.api.mapping.MappingContext;
import groovy.json.JsonSlurper;

public static void main(String[] args) {

    def delay = (Math.abs(new Random().nextInt() % 60) + 1)*1000;  // in ms
    sleep(delay);
    //return message;
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
