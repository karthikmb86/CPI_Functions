
package com.sap.kr

import groovy.json.JsonSlurper

class TokenFetch {
    static stringXML = '<person>' +
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>' +
            '</person>'

    static main(args) {
        def list = [];
        String queryString = '';
        def TokenJson = new JsonSlurper().parse(new File("C:/Users/Kbangera/OneDrive - Deloitte (O365D)/Projects/CBNZ/Wineworks/token/token.json"));

def tokenVal = TokenJson.access_token;
        def a = 1;
    }
}
/*
        InvoiceRequest.INVOICE.ITEM.EDI_CUSTOMER_INVOICE_ITEM.each { it ->
            if (it.PRODUCT.BUYER_PRODUCT_ID != ''){
                list.add(it.PRODUCT.BUYER_PRODUCT_ID);

            }
        }

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

}
 */