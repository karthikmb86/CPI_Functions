REPORT z_test_abap_kr.

        FORM test_custom_abap_kr.

        DATA : lv_packagesize TYPE i VALUE 5000,
        lt_t006a TYPE SORTED TABLE OF t006a WITH UNIQUE KEY msehi.

        "Mention names of the output fields specifies in CPI DS
        TYPES : BEGIN OF ls_otab1,

        end of ls_otab1.

        FIELD-SYMBOLS : <ls_t006a> TYPE t006a,
<ls_outtab> LIKE LINE OF <<<OTAB1>>>.

        IF download = 'S'.
        lv_packagesize = p_pkgsz.
        ENDIF.

        SELECT msehi
        msehl
        FROM t006a
        INTO CORRESPONDING FIELDS OF TABLE lt_t006a
        WHERE spras = $param1.

        SELECT mara~matnr AS mat_matnr
        meins      AS uomid
        mtart      AS mattypeid
        maktx      AS prddescr
        FROM mara LEFT OUTER JOIN makt
        ON mara~matnr = makt~matnr
        AND makt~spras = $param1
        INTO CORRESPONDING FIELDS OF TABLE <<<otab1>>>
        PACKAGE SIZE lv_packagesize
        WHERE mtart = 'FERT'
        OR mtart = 'HALB'
        OR mtart = 'ROH'
        .
        LOOP AT <<<otab1>>> ASSIGNING <ls_outtab>.
        CASE <ls_outtab>-mattypeid.

        WHEN 'FERT'.
<ls_outtab>-mattypeid = 'FINI'.
        WHEN 'HALB'.
<ls_outtab>-mattypeid = 'SEMI'.
        WHEN 'ROH'.
<ls_outtab>-mattypeid = 'RAW'.
        ENDCASE.
        READ TABLE lt_t006a ASSIGNING <ls_t006a>
    WITH KEY msehi = <ls_outtab>-uomid.
        IF sy-subrc = 0.
<ls_outtab>-uomdescr = <ls_t006a>-msehl.
        ENDIF.
        ENDLOOP.
        PERFORM form3.
        REFRESH <<<otab1>>>.
        append_flag = 'A'.
        ENDSELECT.
        ENDFORM.