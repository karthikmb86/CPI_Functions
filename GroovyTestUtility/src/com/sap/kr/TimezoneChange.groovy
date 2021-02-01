
package com.sap.kr

import com.sun.jmx.snmp.internal.SnmpSubSystem;
import java.util.*;
import java.text.*;
import groovy.xml.StreamingMarkupBuilder;

class TimezoneChange {
    static stringXML = '<person>' +
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>' +
            '</person>'

    static main(args) {
//Converts a given DateTime (based on IANA definition of timezones) from a given timezone to DateTime in target timezone.
//Input parameter: local datetime (yyyyMMddhhmmss), source timezone code, target timezone code .
//Output DateTime format is yyyyMMddHHmmss.

            def sdatetime = '20210225190000';
            def stzone = 'UTC';
            def ttzone = 'NZ';
        /*
            SimpleDateFormat sourceDateFormatLocal = new SimpleDateFormat("yyyyMMddHHmmss");
            sourceDateFormatLocal.setTimeZone(TimeZone.getTimeZone(stzone));

            Date date = sourceDateFormatLocal.parse(sdatetime);

            Calendar targetCalendar = Calendar.getInstance(TimeZone.getTimeZone(ttzone));
            targetCalendar.setTimeInMillis(date.getTime());

            SimpleDateFormat targetDateFormatLocal = new SimpleDateFormat("yyyyMMddHHmmss");
            targetDateFormatLocal.setTimeZone(targetCalendar.getTimeZone());

            def val = targetDateFormatLocal.format(date.getTime());
*/
String[] ids = TimeZone.getAvailableIDs();
        //for(String id:ids){
       //     System.out.print(id + '\n');
       // }
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        calendar.setTime(sdf.parse(sdatetime));
        //calendar.setTimeZone('UTC')
        System.out.print("Date:"+ sdf.format(calendar.getTime()));
        System.out.print("Timezone:"+ calendar.getTimeZone().getID());
        //System.out.print("Timezone Name:"+ calendar.getDisplayName());

//Here you say to java the initial timezone. This is the secret
        sdf.setTimeZone(TimeZone.getTimeZone(stzone));
//Will print in UTC
        System.out.println(sdf.format(calendar.getTime()));

//Here you set to your timezone
        sdf.setTimeZone(TimeZone.getTimeZone(ttzone));
//Will print on your default Timezone
        System.out.println(sdf.format(calendar.getTime()));

        sdf.getTimeZone();
        def var4 = '1';

    }
}
