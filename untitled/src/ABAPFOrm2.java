FORM <<<FORMNAME>>>.

        DATA : lt_tcurr        TYPE TABLE OF tcurr,
        lt_tcurr_sub    TYPE TABLE OF tcurr,
        lt_tcurr_output TYPE TABLE OF tcurr,
        ls_tcurr_output TYPE          tcurr,
        lv_frcurr(10)   TYPE c,
        lv_tocurr(10)   TYPE c,
        lv_flag         TYPE i,
        lv_date         TYPE date.

        FIELD-SYMBOLS : <fs_tcurr> TYPE tcurr.

        SELECT mandt
        kurst
        fcurr
        tcurr
        gdatu
        ukurs
        ffact
        tfact
        FROM tcurr
        INTO TABLE lt_tcurr
        WHERE kurst = $PARAM1.

        SORT lt_tcurr BY fcurr tcurr ASCENDING.

        LOOP AT lt_tcurr INTO DATA(ls_tcurr).

        IF lv_flag = 0.
        lv_frcurr = ls_tcurr-fcurr.
        lv_tocurr = ls_tcurr-tcurr.
        ENDIF.

        IF ls_tcurr-fcurr = lv_frcurr AND ls_tcurr-tcurr = lv_tocurr.

        CALL FUNCTION 'CONVERSION_EXIT_INVDT_OUTPUT'
        EXPORTING
        input  = ls_tcurr-gdatu
        IMPORTING
        output = lv_date.
        ls_tcurr-gdatu = lv_date.

        APPEND ls_tcurr TO lt_tcurr_sub.
        lv_flag = 1.

        ELSE.

        "sort sub table by date and pick the latest date
        SORT lt_tcurr_sub BY gdatu DESCENDING.
        READ TABLE lt_tcurr_sub ASSIGNING <fs_tcurr> INDEX 1.
        <<<OTAB1>>>-kurst = <fs_tcurr>-kurst.
        <<<OTAB1>>>-fcurr = <fs_tcurr>-fcurr.
        <<<OTAB1>>>-tcurr = <fs_tcurr>-tcurr.
        <<<OTAB1>>>-gdatu = <fs_tcurr>-gdatu.
        <<<OTAB1>>>-ukurs = <fs_tcurr>-ukurs.
        <<<OTAB1>>>-ffact = <fs_tcurr>-ffact.
        <<<OTAB1>>>-tfact = <fs_tcurr>-tfact.
        CLEAR lt_tcurr_sub.
        "Append output into internal table
        APPEND <<<OTAB1>>>.

        "Assign new combination to condition checker
        lv_frcurr = ls_tcurr-fcurr.
        lv_tocurr = ls_tcurr-tcurr.

        "Start appending new combination
        CALL FUNCTION 'CONVERSION_EXIT_INVDT_OUTPUT'
        EXPORTING
        input  = ls_tcurr-gdatu
        IMPORTING
        output = lv_date.
        ls_tcurr-gdatu = lv_date.

        APPEND ls_tcurr TO lt_tcurr_sub.
        lv_flag = 1.
        ENDIF.

        AT LAST.
        "sort sub table by date and pick the latest date
        SORT lt_tcurr_sub BY gdatu DESCENDING.
        READ TABLE lt_tcurr_sub ASSIGNING <fs_tcurr> INDEX 1.
        <<<OTAB1>>>-kurst = <fs_tcurr>-kurst.
        <<<OTAB1>>>-fcurr = <fs_tcurr>-fcurr.
        <<<OTAB1>>>-tcurr = <fs_tcurr>-tcurr.
        <<<OTAB1>>>-gdatu = <fs_tcurr>-gdatu.
        <<<OTAB1>>>-ukurs = <fs_tcurr>-ukurs.
        <<<OTAB1>>>-ffact = <fs_tcurr>-ffact.
        <<<OTAB1>>>-tfact = <fs_tcurr>-tfact.
        CLEAR lt_tcurr_sub.
        "Append output into internal table
        APPEND <<<OTAB1>>>.
        ENDAT.

        ENDLOOP.

        ENDFORM.