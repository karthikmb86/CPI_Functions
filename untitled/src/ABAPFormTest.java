REPORT Z_TEST_CURR4.


        FORM <<<FORMNAME>>>.

        "lt_tcurr        TYPE TABLE OF tcurr,
        DATA :lt_tcurr_sub    TYPE TABLE OF tcurr,
        lt_tcurr_output TYPE TABLE OF tcurr,
        ls_tcurr_output TYPE          tcurr,
        lv_frcurr(10)   TYPE c,
        lv_tocurr(10)   TYPE c,
        lv_flag         TYPE i,
        lv_date         TYPE date.


        TYPES : BEGIN OF ty_curr,
        kurst TYPE KURST_CURR,
        fcurr TYPE FCURR_CURR,
        tcurr TYPE TCURR_CURR,
        gdatu TYPE GDATU_INV,
        ukurs TYPE UKURS_CURR,
        ffact TYPE FFACT_CURR,
        tfact TYPE TFACT_CURR,
        END OF ty_curr.

        DATA : lt_tcurr TYPE TABLE OF ty_curr,
        ls_tcurr TYPE          ty_curr.

        "FIELD-SYMBOLS : <fs_tcurr> TYPE tcurr.

        SELECT kurst
        fcurr
        tcurr
        gdatu
        ukurs
        ffact
        tfact
        FROM tcurr
        INTO TABLE lt_tcurr
        WHERE kurst = 'M'.

        "READ TABLE lt_tcurr ASSIGNING <fs_tcurr> INDEX 1.
        READ TABLE lt_tcurr INTO ls_tcurr INDEX 1.
        move ls_tcurr-kurst to <<<OTAB1>>>-kurst.
        move ls_tcurr-kurst to <<<OTAB1>>>-kurst.
        move ls_tcurr-fcurr to <<<OTAB1>>>-fcurr.
        move ls_tcurr-tcurr to <<<OTAB1>>>-tcurr.
        move ls_tcurr-gdatu to <<<OTAB1>>>-gdatu.
        move ls_tcurr-ukurs to <<<OTAB1>>>-ukurs.
        move ls_tcurr-ffact to <<<OTAB1>>>-ffact.
        move ls_tcurr-tfact to <<<OTAB1>>>-tfact.
        "Append output into internal table
        "append ls_tcurr TO lt_tcurr.
        append <<<OTAB1>>>.


        ENDFORM.