import com.sap.gateway.ip.core.customdev.util.Message;
import groovy.xml.StreamingMarkupBuilder;
import java.util.Iterator;
import java.util.HashMap;

def Message processData(Message message) {

    //Body
    def body = message.getBody(java.io.Reader);
    //message.setBody(body + "Body is modified");

    def list = [];
    String queryString = '';
    def InvoiceRequest = new XmlSlurper().parse(body);
    /*InvoiceRequest.INVOICE.ITEM.EDI_CUSTOMER_INVOICE_ITEM.each { it ->
        if (it.PRODUCT.BUYER_PRODUCT_ID != ''){
            list.add(it.PRODUCT.BUYER_PRODUCT_ID);
        }
    }*/
    InvoiceRequest.Invoice.Item.each { it ->
        if (it.Product.BuyerProductID != ''){
            list.add(it.Product.BuyerProductID);

        }
    }
    Set<String> set = new HashSet<>(list);
    list.clear();
    list.addAll(set);

    Iterator<String> it = set.iterator();
    while(it.hasNext()){
        if(queryString == ''){
            queryString = "MaterialByCustomer eq '${it.next()}'";
        }
        else{
            queryString = queryString + " or MaterialByCustomer eq '${it.next()}'"
        }
    }

    //Properties
    //map = message.getProperties();
    //value = map.get("oldProperty");
    message.setProperty("queryString", queryString);
    return message;
}
