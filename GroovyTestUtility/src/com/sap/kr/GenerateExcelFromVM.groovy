import org.apache.poi.ss.usermodel.Workbook

import static org.apache.poi.ss.usermodel.CellStyle.*
import static org.apache.poi.ss.usermodel.IndexedColors.*
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sap.gateway.ip.core.customdev.util.Message;
import com.sap.it.api.mapping.MappingContext;
import groovy.json.JsonSlurper;

public static void main(String[] args) {

        // create a new file
        FileOutputStream out = new FileOutputStream("C:/Users/Kbangera/Downloads/workbook.xls");
        // create a new workbook
        Workbook wb = new HSSFWorkbook();
        // create a new sheet
        Sheet s = wb.createSheet();

        def rownum = 0
            // create a row
            Row r = s.createRow(0)
            Cell c = r.createCell(0);
            c.setCellValue( "CBNZ Ship to" );
            c = r.createCell(1);
            c.setCellValue( "Foodstuffs Storecode" );
            rownum++;
            def rootMain = new XmlSlurper().parse(new File("C:/Users/Kbangera/Downloads/value_mapping.xml"));

            //String root = "";
            rootMain.group.each { it ->
                r = s.createRow(rownum++)
                for (int cellnum=0; cellnum <2; cellnum++) {
                    c = r.createCell(cellnum);
                    c.setCellValue( it.entry[cellnum].value.text());
                }
            }
        wb.write(out);
        out.close();
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
