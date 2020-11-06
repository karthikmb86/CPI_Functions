ifthenelse(

        date_diff(sysdate(),JoinAndFilter.BDTER,'D') < 0,

        ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < (5*7) AND abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) > (4*7) ,0, JoinAndFilter.BDMNG),

        JoinAndFilter.BDMNG

        )


        ifthenelse(

        date_diff(sysdate(),JoinAndFilter.BDTER,'D') < 0,

        ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < (5*7) AND abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) > (4*7) ,0, JoinAndFilter.ENMNG),

        JoinAndFilter.ENMNG

        )


        ifthenelse(

                date_diff(sysdate(),JoinAndFilter.BDTER,'D') < 0,

        ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < (5*7),'Select','Ignore'),

        ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < (52*7),'Select','Ignore')
        )
        != 'Ignore'


        Aggregator.BDTER - least( day_in_week(Aggregator.BDTER), day_in_month(Aggregator.BDTER) ) + 1

        ifthenelse(Aggregator.BDMNG - Aggregator.ENMNG > 0, Aggregator.BDMNG - Aggregator.ENMNG, 0)



        MSTA.STATM = $STATUS AND ((MSTA.ERSDA <= to_char(sysdate(), 'yyyy.mm.dd') AND MSTA.ERSDA >= to_char(sysdate()-30, 'yyyy.mm.dd')) OR (MSTA.LAEDA <= to_char(sysdate(), 'yyyy.mm.dd') AND MSTA.LAEDA >= to_char(sysdate()-30, 'yyyy.mm.dd')))

        MSTA.STATM = $STATUS AND (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-30)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-30))

        MARA.MTART = $MATERIAL_TYPE AND (((MARA.ERSDA <= sysdate() AND MARA.ERSDA >= sysdate()-30)) OR (MARA.LAEDA <= sysdate() AND MARA.LAEDA >= sysdate()-30))

        //With Global Params
        MARA.MTART = $G_MATERIAL_TYPE AND (((MARA.ERSDA <= sysdate() AND MARA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MARA.LAEDA <= sysdate() AND MARA.LAEDA >= sysdate()-$G_PAST_DAYS))

        MARC.DISPO = $G_MRP_CONTROLLER AND (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS))


        Reservation Join Filter:
        ifthenelse(date_diff(sysdate(),FilterRESB.BDTER,'D') > 0,ifthenelse(abs(date_diff(sysdate(),FilterRESB.BDTER,'D')) < ($G_PAST_WEEKS*7),'Select','Ignore'),ifthenelse(abs(date_diff(sysdate(),FilterRESB.BDTER,'D')) < ($G_FUTURE_WEEKS*7),'Select','Ignore')) != 'Ignore'

        ifthenelse(date_diff(sysdate(),JoinAndFilter.BDTER,'D') > 0,ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < ($G_PAST_WEEKS*7),'Select','Ignore'),ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < ($G_FUTURE_WEEKS*7),'Select','Ignore')) != 'Ignore'

Corrected
        ifthenelse(date_diff(sysdate(),JoinAndFilter.BDTER,'D') > 0,ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < ($G_FUTURE_WEEKS*7),'Select','Ignore'),ifthenelse(abs(date_diff(sysdate(),JoinAndFilter.BDTER,'D')) < ($G_PAST_WEEKS*7),'Select','Ignore')) != 'Ignore'


        ifthenelse(GetExchangeRate.ukurs > 0, GetExchangeRate.ukurs,ifthenelse((GetExchangeRate.ffact > 0 AND GetExchangeRate.tfact > 0), (1 / abs(GetExchangeRate.ukurs)) * (GetExchangeRate.tfact/GetExchangeRate.ffact), (1/abs(GetExchangeRate.ukurs))))

Filter Condition for Location Source IO
        "/IBP/MARA_EXT".MTART = 'HIBE' AND ("/IBP/MARC_EXT".SOBSL =  '40' OR "/IBP/MARC_EXT".SOBSL =  '41' OR "/IBP/MARC_EXT".SOBSL =  '42' OR "/IBP/MARC_EXT".SOBSL =  '43' OR "/IBP/MARC_EXT".SOBSL =  '44')

Join
        "/IBP/MARC_EXT".MATNR = "/IBP/MARA_EXT".MATNR
        (T460A.WERKS = "/IBP/MARC_EXT".LOCNO) AND (T460A.SOBSL = "/IBP/MARC_EXT".SOBSL)

Location Source filter
        ((MARC.DISPO = $G_MRP_CONTROLLER) AND (MARC.SOBSL =  '40' OR "/IBP/MARC_EXT".SOBSL =  '41' OR "/IBP/MARC_EXT".SOBSL =  '42' OR "/IBP/MARC_EXT".SOBSL =  '43' OR "/IBP/MARC_EXT".SOBSL =  '44')) AND (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS))

ID - gen_uuid( )

        SendInBatches.LFDAT - least( day_in_week(SendInBatches.LFDAT), day_in_month(SendInBatches.LFDAT) ) + 1

Prod source header - Filter for MARC
        ("MARC".SOBSL!='40') AND ("MARC".SOBSL!='41') AND ("MARC".SOBSL!='42') AND ("MARC".SOBSL!='43') AND ("MARC".SOBSL!='44') AND ("MARC".DISMM!='X0')

System date to technical week- (KEYFIGUREDATE)
        sysdate() - least( day_in_week(sysdate()), day_in_month(sysdate()) ) + 1


        ifthenelse(GetExchangeRate.ukurs > 0, GetExchangeRate.ukurs,ifthenelse((GetExchangeRate.ffact > 0 AND GetExchangeRate.tfact > 0), (1 / abs(GetExchangeRate.ukurs)) * (GetExchangeRate.tfact/GetExchangeRate.ffact), (1/abs(GetExchangeRate.ukurs))))

Location Source Logic in Filter1
        ((MARC.DISPO = $G_MRP_CONTROLLER) AND (MARC.SOBSL =  '40' OR MARC.SOBSL =  '41' OR MARC.SOBSL =  '42' OR MARC.SOBSL =  '43' OR MARC.SOBSL =  '44')) AND (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS)) AND (MARA.MATNR = $G_MATERIAL_TYPE)
        ((MARC.DISPO != $G_MRP_CONTROLLER_1 AND MARC.DISPO != $G_MRP_CONTROLLER_2 AND MARC.DISPO != $G_MRP_CONTROLLER_3) AND (MARC.SOBSL =  $G_SPECIAL_PROC_KEY_1 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_2 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_3 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_4 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_5)) AND (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS)) AND (MARA.MATNR = $G_MATERIAL_TYPE)
        ((MARC.DISPO != $G_MRP_CONTROLLER_1 AND MARC.DISPO != $G_MRP_CONTROLLER_2 AND MARC.DISPO != $G_MRP_CONTROLLER_3) AND (MARC.SOBSL =  $G_SPECIAL_PROC_KEY_1 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_2 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_3 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_4 OR MARC.SOBSL =  $G_SPECIAL_PROC_KEY_5)) AND ((((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS)) AND MSTA.STATM = $G_MAINTENANCE_STATUS) AND (MARA.MATNR = $G_MATERIAL_TYPE)

Customer Product
        (MARC.DISPO != $G_MRP_CONTROLLER_1 AND MARC.DISPO != $G_MRP_CONTROLLER_2 AND MARC.DISPO != $G_MRP_CONTROLLER_3) AND ((((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS)) AND $G_MAINTENANCE_STATUS = 'D')
        (MARC.DISPO != $G_MRP_CONTROLLER_1 AND MARC.DISPO != $G_MRP_CONTROLLER_2 AND MARC.DISPO != $G_MRP_CONTROLLER_3) AND ((((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS)) AND MSTA.STATM = $G_MAINTENANCE_STATUS)

        (((MSTA.ERSDA <= sysdate() AND MSTA.ERSDA >= sysdate()-$G_PAST_DAYS)) OR (MSTA.LAEDA <= sysdate() AND MSTA.LAEDA >= sysdate()-$G_PAST_DAYS))

        -----------------------------------ID field mapping of KF table----------------------------------------------
        -> gen_uuid( )

        ------------------------------Special Character Logic in mapping fields--------------------------------------
        ifthenelse(ltrim_blanks(translate(SendInBatches.MATNR, CHR(10) || CHR(13) || CHR(39) || '"<>', '      ')) = '',
        NULL,
        translate(SendInBatches.MATNR, CHR(10) || CHR(13) || CHR(39) || '"<>', '      '))
        # replace special characters carriage return, line feed, single and double quote, less and greater sign by space, then replace strings consisting of spaces only by NULL

        (("/IBP/MARA_EXT".MTART = $G_MATERIAL_TYPE) AND ("/IBP/MARC_EXT".DISMM =  $G_MRP_TYPE)) AND (("/IBP/MARC_EXT".DISPO !=  $G_MRP_CONTROLLER_1) AND ("/IBP/MARC_EXT".DISPO !=  $G_MRP_CONTROLLER_2) AND ("/IBP/MARC_EXT".DISPO !=  $G_MRP_CONTROLLER_3))
        ((MARA.MTART = $G_MATERIAL_TYPE) AND (MARC.DISMM =  $G_MRP_TYPE)) AND ((MARC.DISPO !=  $G_MRP_CONTROLLER_1) AND (MARC.DISPO !=  $G_MRP_CONTROLLER_2) AND (MARC.DISPO !=  $G_MRP_CONTROLLER_3))
