
package com.sap.kr

import groovy.xml.StreamingMarkupBuilder;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;

class GroovyTestCB_INVC {
    static stringXML = '<person>'+
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>'+
            '</person>'
    static main(args) {
        def list = [];
        String queryString = '';
        def InvoiceRequest = new XmlSlurper().parse(new File("C:/Users/Kbangera/OneDrive - Deloitte (O365D)/Projects/CBNZ/Foodstuffs/Invoice/InputToGroovy.xml"))
        def inputVal = '787F87878'
        def resp = '';
        def customerNo = 11123;
        def ATBEZ = 'Expiration date, shelf life';
        def dateVal = '2020-12-09T23:00:17Z'
        dateVal.substring(0, 10);
        def dateVal2 = '01.12.2020'
        String strDate = '01.12.2020';
        strDate = strDate.replace(".","-");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStr = formatter.parse(strDate);
        String formattedDate = formatter.format(dateStr);
        if(ATBEZ.toLowerCase().contains('date')){

            def abcd2 = 2;
        }

        def abcd = 1;


/*
        if(inputVal.matches(".*[A-Za-z].*")){
            //return inputVal;
            resp = inputVal;
        }
        else {
            resp = inputVal.padLeft(18,'0');
        }
        */

        //resp = padZeros(inputVal, 18, '0');
        /*
        InvoiceRequest.INVOICE.ITEM.EDI_CUSTOMER_INVOICE_ITEM.each { it ->
            if (it.PRODUCT.BUYER_PRODUCT_ID != ''){
                list.add(it.PRODUCT.BUYER_PRODUCT_ID);

            }
        }
*/
        if((Integer.parseInt(customerNo)  >= 5000000 && Integer.parseInt(customerNo) <= 5999999) || (Integer.parseInt(customerNo)  >= 7000000 && Integer.parseInt(customerNo) <= 7999999)){
            // '502171';
        }
        else
            // ''



        //Use this part in actual mapping in CPI

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
        def abc = '1';
        //def newPeople = new StreamingMarkupBuilder().bind {mkp.yield people}.toString()

        //File file = new File("C:/IntellijFiles/out.xml")
        //file.write(newPeople)
    }

    def String padZeros(String inputVal, Integer digits, String padWith) {
        //if (inputVal.matches("[A-Za-z0-9]+")) {
        if(inputVal.matches(".*[A-Za-z].*")){
            return inputVal;
        }
        else {
            return inputVal.padLeft(digits,padWith);
        }
    }

    def Boolean iSMultiOutput(String inputVal) {
        if(inputVal.contains('_')){
            return true;
        }
        else {
            return false;
        }
    }

}
