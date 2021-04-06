import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.it.api.mapping.MappingContext;
import groovy.json.JsonSlurper;

public static void main(String[] args) {

    //def body = new JsonSlurper().parse(new File("C:/Karthik/IntellijFiles/SampleJSON3.json"));
    def rootMain = new XmlSlurper().parse(new File("C:/Users/Kbangera/Downloads/AfterGatherGroovyInput.xml"));

    String MatnBatch = "";
    rootMain.root.shipmentLines.each { it ->
        if ((it.stockCode == '') && (it.lot == '')) {

            if (MatnBatch.equalsIgnoreCase("")) {
                MatnBatch = (it.SupplierProductID).toString() + "-" + (it.Batch).toString();
            } else {
                MatnBatch = MatnBatch + "," + (it.SupplierProductID).toString() + "-" + (it.Batch).toString();
            }
        }
    }

  //   processData(message);
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
