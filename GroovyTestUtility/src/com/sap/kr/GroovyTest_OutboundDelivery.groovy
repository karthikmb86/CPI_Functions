
package com.sap.kr

import com.sap.gateway.ip.core.customdev.util.Message
//import org.apache.commons.lang.StringUtils.*

class GroovyTest_OutboundDelivery {
    static stringXML = '<person>' +
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>' +
            '</person>'

    def process(Message message) {

    }

    static main(args) {

        Message message;
        String input = '8008541320'
        String unpadded = "12345";
        String padded = "000000000000000000".substring(input.length()) + input;
        try{
            //int newVal = (int) Double.parseDouble(input);
            //return String.format("%018d", Integer.parseInt(input));
            String newStr = String.format("%018d", (int) Double.parseDouble(input));
        }
        catch(Exception e){
            //return input;
            e.getMessage();
        }


        def DeliveryMsg = new XmlSlurper().parse(new File("C:/Karthik/IntellijFiles/POChange_NewInsert.xml"))
/*
        Boolean isNew = true;

        OrderConfRequest.OrderConfirmation.Item.each { it ->
            //if (it.RequestedQuantity.text() != it.YY1_Originalqty.text() || it.Product.BuyerProductID != it.Product.SupplierProductID) {
            try{
                if (it.YY1_BUYER_PRODUCT_ID.text() != '') {    // YY1_BUYER_PRODUCT_ID will be blank for new inserts
                    isNew = false;
                }
            }
            catch(Exception e){
                //isNew = true;
            }
        }
        if (!isNew) {
            message.setProperty("IsChangeConfirmation", "True");
        } else {
            message.setProperty("IsChangeConfirmation", "False");
        }
        */
        //return message;

    }
}
