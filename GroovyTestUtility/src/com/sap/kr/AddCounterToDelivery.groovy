import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.it.api.mapping.MappingContext;
import groovy.json.JsonSlurper;
import groovy.util.*;
import groovy.xml.XmlUtil;


public static void main(String[] args) {

    //def Root = new XmlSlurper().parse(new File("C:/Karthik/IntellijFiles/DeliveryCounterTest_1.xml"))
    //def tokenVal = body.root.access_token;
   // Message message = new Message();
   // message.setBody(body);
   // processData(message);
    /*def Root = new XmlParser(false,true).parseText(new File("C:/Karthik/IntellijFiles/DeliveryCounterTest_1.xml"));
    def counterBuilder = new NodeBuilder();8
    def counterNode = counterBuilder.counter {
        val(1)
    }
    def newNode = new XmlParser().parseText("<counter>1</counter>")
    Root.appendNode(newNode)
    assert Root.Delivery.append(counterNode);
    message.setBody(XmlUtil.serialize(Root));
*/
    def Root = new XmlSlurper(false,true).parse(new File("C:/Karthik/IntellijFiles/DeliveryCounterTest_1.xml"));
    def newNode = new XmlSlurper(false,true).parseText("<counter>1</counter>")
    Root.Delivery.appendNode(newNode);
    String outputXML = groovy.xml.XmlUtil.serialize(Root);
    println(outputXML);
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
