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


class CommonFunctions {

    //This method splits a string based on character length, expects two input parameters: Source field which needs to be split(textLines)
//and number of characters after which it has to be split(chunkSize)

    public void splitText(String[] textLines, int[] chunkSize, Output output){

        for(String textLine: textLines){

            int arraySize = (int) Math.ceil(textLine.length() / chunkSize[0]);

            String[] returnArray = new String[arraySize];

            int index = 0;
            for(int i = 0; i < textLine.length(); i = i+chunkSize[0])
            {
                if(textLine.length() - i < chunkSize[0])
                {
                    returnArray[index++] = textLine.substring(i);
                }
                else
                {
                    returnArray[index++] = textLine.substring(i, i+chunkSize[0]);
                }
            }


            for(String value: returnArray)
            {
                output.addValue(value);
            }
        }
    }

//This method checks if a value exists in an array, expects two input parameters: Source field (inputValues)
//and value which needs to be checked in array(checkString)

    public void existsInArray(String[] inputValues, String[] checkString, Output output){
        for(int i=0;i<inputValues.length;i++){
            if(inputValues[i].equals(checkString[0]))
            {
                output.addValue(true);
                break;
            }
        }
        output.addValue(false);
    }

    public void checkQualf(String[] qualf, Output output){
        for(int i=0;i<qualf.length;i++)
        {
            if(qualf[i].equals("001") || qualf[i].equals("002") || qualf[i].equals("003"))
                output.addValue("true");
            break;
        }
    }


//This method checks if a value exists in an array and if true, returns a corresponding value of any arbitrary element of the array:
//expects three input parameters: Search element of the array(inputValues), the value which needs to be checked in array(checkString)
//and the element from which the output value should be retrieved(outputValue)

    public void getArrayValue(String[] inputValues, String[] checkString, String[] outputValue, Output output){
        for(int i=0;i<inputValues.length;i++){
            if(inputValues[i].equals(checkString[0])){
                output.addValue(outputValue[i]);
                return;
            }
        }
    }
//This method checks whether a string contains a character pattern, expects two input parameters: string (textLine)
//and character pattern(checkPattern) which needs to be checked in string. Returns true or false

    public void stringContains(String[] textLine, String[] checkPattern, Output output){

        for(int i=0;i<textLine.length;i++){
            if(textLine[i].contains(checkPattern[0])){
                output.addValue(true);
            } else {
                output.addValue(false);
            }
        }
    }

//This method returns value of a property, expects one input prop_name i.e. name of the property to be retrieved
    def String get_property(String prop_name,MappingContext context) {

        def prop = context.getProperty(prop_name);

        if(prop !=null ){

            return prop;

        }

        return prop_name;

    }

//This method returns value of a header, expects one input header_name i.e. name of the header to be retrieved
    def String get_header(String header_name,MappingContext context) {

        def header = context.getHeader(header_name);

        if(header !=null ){

            return header;

        }

        return header_name;

    }

//This method returns a random 32 char ID in capital letters
    def String generateUUID(String input) {

        String uuid = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();

        return uuid;

    }

// This method returns part of the string after provided offset value(offset) or blank if the input string is blank
//Takes two input values: source string and index with which string needs to be split
    def String getOffset(String input, int offset) {
        if(input.equals(""))
            return input;
        else
            def string_offset = input.substring(offset,input.length());
        return string_offset;

    }

//This method splits a string based on delimiter. Takes two input values: input string and delimiter
    public void splitStringOnDelimiter(String[] textLines, String[] delimiter, Output output){
        def delimiter_char = delimiter[0];
        for(String textLine: textLines){
            String[] values = textLine.split(delimiter_char);
            for(String value: values){
                output.addValue(value);
            }
        }
    }

//This method checks format for string and if '*', adds a line break. Takes two input values: tdline and tdformat
    public void addFormat(String[] tdline, String[] tdformat, Output output){
        for(int i=0;i<tdline.length;i++)
        {
            String format = " ";
            if(tdformat.length>i)
            {
                if(tdformat[i].equals("*"))
                    format="\r\n";
            }

            if(tdline.length>i)
                output.addValue(tdline[i]+format);
        }
    }


    public void concatLines(String[] line, Output output){
        StringBuffer buf=new StringBuffer();
        for(int i=0; i<line.length; i++){
            buf.append(line[i]);
        }
        output.addValue(buf.toString());
    }

    public void removeSUPPRESS(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            if(!output.isSuppress(queue[j]))
                output.addValue(queue[j]);
        }
    }


    public void formatByExample(String[] a, String[] b, Output output){
        int bcount=0;
        for(int i=0;i<a.length; i++){
            if (output.isContextChange[a[i]]){
                output.addContextChange();
                continue;
            }
            while((output.isContextChange(b[bcount]))&&(bcount<b.length)){
                bcount++;
            }
            if (bcount==b.length){
                output.addValue("no value");
            }
            else {
                output.addValue(b[bcount]);
                bcount++;
            }
        }
    }


    def void ConvertUoMs_UStoNZ(String[] unit, String[] value, Output newVal) {

                switch(unit[0]) {
            case "FC":
                newVal.addValue(value[0]*0.0283)
            case "LB":
                newVal.addValue(value[0]*0.4536)
            default:
                newVal.addValue(value[0])
        }
    }

    public void replaceSUPPRESS(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            if(!output.isSuppress(queue[j]))
                output.addValue(queue[j]);
        }
    }

    public void removeNulls(String[] inputVal, Output output){

        for(int i=0;i<inputVal.length; i++){
            try{
                if (output.isContextChange[inputVal[i]]){
                    //output.addContextChange();
                    output.addValue("InIf");
                }
            }
            catch(Exception e){
                output.addValue("InException");
                output.addContextChange();
            }
            //	else{
            //  output.addValue(inputVal[i]);
            // }
            //}
            //catch(Exception e){
            //    output.addValue(inputVal[i])
            //}

        }
    }


    public void addContextChg(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            output.addValue(queue[j]);
            if(j!=(queue.length-1)){
                output.addContextChange();
            }
        }
    }

    public void getFirstValue(String[] queue, Output output){
        try{
            output.addValue(queue[0]);
        }
        catch(Exception e){
            output.addValue("");
        }
    }

    static stringXML = '<person>'+
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>'+
            '</person>'

    static main(args) {

        def input = new XmlSlurper().parse(new File("C:/IntellijFiles/Input1.xml"))
    }
}