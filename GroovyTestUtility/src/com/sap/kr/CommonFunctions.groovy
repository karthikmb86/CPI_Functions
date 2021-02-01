import com.sap.gateway.ip.core.customdev.util.*;
import java.util.*;
import java.text.SimpleDateFormat;
import com.sap.it.api.mapping.*;
import  com.sap.it.api.mapping.MappingContext;

public static void main(String[] args) {

    String timeVal = '20210204110000'
    String sourceTZ = 'UTC'
    String targetTZ = 'NZ'
    MappingContext mc = new MappingContext();
    getConvertedTime(timeVal, sourceTZ, targetTZ, mc);
}

/* SAP */
    //This method splits a string based on character length, expects two input parameters: Source field which needs to be split(textLines)
//and number of characters after which it has to be split(chunkSize)

    def void splitText(String[] textLines, int[] chunkSize, Output output){

        for(String textLine: textLines){

            int arraySize = (int) Math.ceil(textLine.length() / chunkSize[0])

            String[] returnArray = new String[arraySize]

            int index = 0;
            for(int i = 0; i < textLine.length(); i = i+chunkSize[0])
            {
                if(textLine.length() - i < chunkSize[0])
                {
                    returnArray[index++] = textLine.substring(i)
                }
                else
                {
                    returnArray[index++] = textLine.substring(i, i+chunkSize[0])
                }
            }


            for(String value: returnArray)
            {
                output.addValue(value)
            }
        }
    }

//This method checks if a value exists in an array, expects two input parameters: Source field (inputValues)
//and value which needs to be checked in array(checkString)

    def void existsInArray(String[] inputValues, String[] checkString, Output output){
        for(int i=0;i<inputValues.length;i++){
            if(inputValues[i].equals(checkString[0]))
            {
                output.addValue(true)
                break;
            }
        }
        output.addValue(false)
    }

    def void checkQualf(String[] qualf, Output output){
        for(int i=0;i<qualf.length;i++)
        {
            if(qualf[i].equals("001") || qualf[i].equals("002") || qualf[i].equals("003"))
                output.addValue("true")
            break;
        }
    }

//This method checks if a value exists in an array and if true, returns a corresponding value of any arbitrary element of the array:
//expects three input parameters: Search element of the array(inputValues), the value which needs to be checked in array(checkString)
//and the element from which the output value should be retrieved(outputValue)

    def void getArrayValue(String[] inputValues, String[] checkString, String[] outputValue, Output output){
        for(int i=0;i<inputValues.length;i++){
            if(inputValues[i].equals(checkString[0])){
                output.addValue(outputValue[i])
                return;
            }
        }
    }
//This method checks whether a string contains a character pattern, expects two input parameters: string (textLine)
//and character pattern(checkPattern) which needs to be checked in string. Returns true or false

    def void stringContains(String[] textLine, String[] checkPattern, Output output){

        for(int i=0;i<textLine.length;i++){
            if(textLine[i].contains(checkPattern[0])){
                output.addValue(true)
            } else {
                output.addValue(false)
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
    def void splitStringOnDelimiter(String[] textLines, String[] delimiter, Output output){
        def delimiter_char = delimiter[0];
        for(String textLine: textLines){
            String[] values = textLine.split(delimiter_char);
            for(String value: values){
                output.addValue(value);
            }
        }
    }

//This method checks format for string and if '*', adds a line break. Takes two input values: tdline and tdformat
    def void addFormat(String[] tdline, String[] tdformat, Output output){
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

/*Karthik Bangera*/

    def void concatLines(String[] line, Output output){
        StringBuffer buf=new StringBuffer();
        for(int i=0; i<line.length; i++){
            buf.append(line[i]);
        }
        output.addValue(buf.toString());
    }

    def void removeSUPPRESS(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            if(!output.isSuppress(queue[j]))
                output.addValue(queue[j]);
        }
    }

    def void formatByExample(String[] a, String[] b, Output output){
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

    def void replaceSUPPRESS(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            if(!output.isSuppress(queue[j]))
                output.addValue(queue[j]);
        }
    }

    def void removeNulls(String[] inputVal, Output output){

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

    def void addContextChg(String[] queue, Output output){
        for(int j=0;j<queue.length;j++)
        {
            output.addValue(queue[j]);
            if(j!=(queue.length-1)){
                output.addContextChange();
            }
        }
    }

    def void getFirstValue(String[] queue, Output output){
        try{
            output.addValue(queue[0]);
        }
        catch(Exception e){
            output.addValue("");
        }
    }

/* Pad zeros to left (Handle long material numbers in the catch block) */
    def String padZerosToLeft(String input){
        try{
            return String.format('%018d',Integer.parseInt(input));
        }
        catch(Exception e){
            return "000000000000000000".substring(input.length()) + input
        }
    }

    def String getConvertedTime(String timeVal, String sourceTimeZone, String targetTimeZone, MappingContext context) {
        SimpleDateFormat FORMATTER = new SimpleDateFormat('yyyyMMddHHmmss');
        //SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        FORMATTER.setTimeZone(TimeZone.getTimeZone(sourceTimeZone));
        //Date date = FORMATTER.parse('2021-02-24 11:00:00');
        Date date = FORMATTER.parse(timeVal);
        FORMATTER.setTimeZone(TimeZone.getTimeZone(targetTimeZone));

        return FORMATTER.format(date);
    }



