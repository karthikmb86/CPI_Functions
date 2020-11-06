package com.sap

import com.sap.it.api.mapping.*
import groovy.xml.StreamingMarkupBuilder;

/*Add MappingContext parameter to read or set headers and properties
def String customFunc1(String P1,String P2,MappingContext context) {
         String value1 = context.getHeader(P1);
         String value2 = context.getProperty(P2);
         return value1+value2;
}

Add Output parameter to assign the output value.
def void custFunc2(String[] is,String[] ps, Output output, MappingContext context) {
        String value1 = context.getHeader(is[0]);
        String value2 = context.getProperty(ps[0]);
        output.addValue(value1);
        output.addValue(value2);
}*/

import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.*;
import com.sap.it.api.mapping.*;
import com.sap.it.api.mapping.MappingContext;


class CB_Funcs {

    def void ConvertUoMs_UStoNZ(String[] unit, String[] value, Output newValue, Output UoM) {

        try{

            if(unit[0].equalsIgnoreCase("FTQ")) {

                newValue.addValue((Double.parseDouble(value[0])*0.0283).toString())
                UoM.addValue("M3")  //MTQ /M3
            }
            else if (unit[0].equalsIgnoreCase("LBR")){
                newValue.addValue((Double.parseDouble(value[0])*0.4536).toString())
                UoM.addValue("KG")
            }
            else if (unit[0].equalsIgnoreCase("INH")){
                newValue.addValue((Double.parseDouble(value[0])*25.4).toString())
                UoM.addValue("MM")
            }
            else if (unit[0].equalsIgnoreCase("LTR")){
                newValue.addValue(value[0])
                UoM.addValue("L")
            }
            else{
                newValue.addValue(value[0])
                UoM.addValue(unit[0])
            }

            //System.out.println("Value:"+newValue.getValue());
            //System.out.println("UoM:"+UoM.getValue());

        }
        catch(Exception e){
            newValue.addValue(value[0])
            UoM.addValue(unit[0])
        }
    }

    static main(args) {

        Output valueOp = new Output();
        Output unitOp = new Output();
       // def input = new XmlSlurper().parse(new File("C:/IntellijFiles/Input1.xml"))
        String[] unit = new String("FC")
        String[] value = new String("10")
        ConvertUoMs_UStoNZ(unit,value,valueOp,unitOp)
    }

}


