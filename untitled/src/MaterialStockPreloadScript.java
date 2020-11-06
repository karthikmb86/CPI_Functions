print('INFO - Current And Last Successful Load Dates Start ===========================================================================');

        # $G_LOAD_DATE - load date of the job.
        if ( $G_LOAD_DATE is not NULL and abs(date_diff($G_LOAD_DATE, sysutcdate(), 'S')) > 10 ) print('WARNING - Value ' || $G_LOAD_DATE || ' of load date variable $G_LOAD_DATE set by input is ignored, because value is calculated');
        $G_LOAD_DATE = sysutcdate();
        print('INFO - Current load date $G_LOAD_DATE calculated value: '|| $G_LOAD_DATE);

        # $G_LAST_LOAD_DATE - last successful load date of the job.
        if ( $G_LAST_LOAD_DATE is not NULL and $G_LAST_LOAD_DATE <> sysutcdate() ) print('WARNING - Value ' || $G_LAST_LOAD_DATE || ' of last successful load date variable $G_LAST_LOAD_DATE set by input is ignored, because variable is calculated');
        $G_LAST_LOAD_DATE = ifthenelse(get_data(job_name() || '-LOAD_DATE') = '', NULL, to_date(get_data(job_name() || '-LOAD_DATE'), 'yyyy-mm-dd hh24:mi:ss'));
        print('INFO - Last successful load date $G_LAST_LOAD_DATE calculated value: '|| $G_LAST_LOAD_DATE);

        # $G_LAST_STANDARD_LOAD_DATE - date and time of last successful load with standard settings.
        if ( $G_LAST_STANDARD_LOAD_DATE is not NULL and $G_LAST_STANDARD_LOAD_DATE <> sysutcdate() ) print('WARNING - Value ' || $G_LAST_STANDARD_LOAD_DATE || ' of last successful load date with standard settings $G_LAST_STANDARD_LOAD_DATE is ignored');
        $G_LAST_STANDARD_LOAD_DATE = ifthenelse(get_data(job_name() || '-STANDARD_LOAD_DATE') = '', NULL, to_date(get_data(job_name() || '-STANDARD_LOAD_DATE'), 'yyyy-mm-dd hh24:mi:ss'));
        print('INFO - Last successful load date with standard settings $G_LAST_STANDARD_LOAD_DATE calculated value: '|| $G_LAST_STANDARD_LOAD_DATE);

        # $G_STANDARD_LOAD - flag indicating if the data selection is done with standard settings.
        if  ($G_STANDARD_LOAD is NULL) $G_STANDARD_LOAD = 'yes';
        else if ($G_STANDARD_LOAD <> 'yes')  print('INFO - variable $G_STANDARD_LOAD set by input to: '|| $G_STANDARD_LOAD);

        print('INFO - Current And Last Successful Load Dates End =============================================================================');

        print('INFO - Variable Declaration Start =============================================================================================');

        # Set Global Variables used by IBP stored procedure call
        # ----------------------------------------------------------

        # Planning Area
        if ($G_PLAN_AREA is null or $G_PLAN_AREA = 'MRODEV1INT')
        print('INFO - IBP Planning area variable $G_PLAN_AREA with default value: '|| $G_PLAN_AREA);
        else
        begin
        print('INFO -  IBP Planning area variable $G_PLAN_AREA set by input to: '|| $G_PLAN_AREA);
        $G_STANDARD_LOAD = 'no';
        end

        # IBP Scenario - acceptable values are:
        $G_SCENARIO = nvl($G_SCENARIO, '');
        if ($G_SCENARIO = '') print('INFO - IBP planning scenario $G_SCENARIO with default value: '|| $G_SCENARIO);
        else
        begin
        print('INFO -  IBP planning scenario $G_SCENARIO set by input to: '|| $G_SCENARIO);
        $G_STANDARD_LOAD = 'no';
        end

        # Batch command processing. Indicates if IBP should process batch as
        # 'INSERT_UPDATE' or 'DELETE'
        if ($G_BATCH_COMMAND = 'REPLACE')
        begin
        #     change 'REPLACE' mode to 'INSERT_UPDATE' mode, because 'REPLACE' has a severe risk of data loss if it is not handled correctly
        #     if you plan to enable batch mode 'REPLACE' by modifying the behavior here please read the documentation carefully to avoid data loss when running integration jobs the wrong way
        print('WARNING - Value ' || $G_BATCH_COMMAND || ' of variable $G_BATCH_COMMAND set by input is ignored, because this mode is forbidden by preload script');
        $G_BATCH_COMMAND = 'INSERT_UPDATE';
        end
        if ($G_BATCH_COMMAND = 'INSERT_UPDATE')
        print('INFO - batch command for IBP post processing variable $G_BATCH_COMMAND with default value: '|| $G_BATCH_COMMAND);
        else
        begin
        print('INFO -  batch command for IBP post processing variable $G_BATCH_COMMAND set by input to: '|| $G_BATCH_COMMAND);
        $G_STANDARD_LOAD = 'no';
        end

        # Time Profile - Time profile. You must get this numeric ID from your time profile table in IBP.
        $G_TIME_PROFILE = nvl($G_TIME_PROFILE, -1);
        if ($G_TIME_PROFILE = -1) print('INFO - IBP time profile $G_TIME_PROFILE with default value: '|| $G_TIME_PROFILE);
        else
        begin
        print('INFO -  IBP time profile $G_TIME_PROFILE set by input to: '|| $G_TIME_PROFILE);
        $G_STANDARD_LOAD = 'no';
        end

        # Time Profile Level for Disaggregation.
        if ($G_TIME_PROFILE_LEVEL is NULL) print('INFO - IBP time profile level for disaggregation $G_TIME_PROFILE_LEVEL with default value: NULL ');
        else
        begin
        print('INFO -  IBP time profile level for disaggregation $G_TIME_PROFILE_LEVEL set by input to: '|| $G_TIME_PROFILE_LEVEL);
        $G_STANDARD_LOAD = 'no';
        end

        print('INFO - Non-IBP specific variables =================================================================================================');

        # $G_MATERIAL_TYPE_n - User selected material types. Allows users to extract inventory
        #  for only the material types defined here.
        # If not populated, default material types selected are MARA.MTART in ('ROH', 'HALB', 'FERT', 'HIBE', 'FHMI')
        if ($G_MATERIAL_TYPE_1 is not null and $G_MATERIAL_TYPE_1 <> 'ROH')
        begin
        print('INFO - variable $G_MATERIAL_TYPE_1 set by input to: '|| $G_MATERIAL_TYPE_1);
        $G_STANDARD_LOAD = 'no';
        end
        else
        print('INFO - variable $G_MATERIAL_TYPE_1 with default value: '|| $G_MATERIAL_TYPE_1);

        if ($G_MATERIAL_TYPE_2 is not null and $G_MATERIAL_TYPE_2 <> 'HALB')
        begin
        print('INFO - variable $G_MATERIAL_TYPE_2 set by input to: '|| $G_MATERIAL_TYPE_2);
        $G_STANDARD_LOAD = 'no';
        end
        else
        print('INFO - variable $G_MATERIAL_TYPE_2 with default value: '|| $G_MATERIAL_TYPE_2);

        if ($G_MATERIAL_TYPE_3 is not null and $G_MATERIAL_TYPE_3 <> 'FERT')
        begin
        print('INFO - variable $G_MATERIAL_TYPE_3 set by input to: '|| $G_MATERIAL_TYPE_3);
        $G_STANDARD_LOAD = 'no';
        end
        else
        print('INFO - variable $G_MATERIAL_TYPE_3 with default value: '|| $G_MATERIAL_TYPE_3);

        if ($G_MATERIAL_TYPE_4 is not null and $G_MATERIAL_TYPE_4 <> 'HIBE')
        begin
        print('INFO - variable $G_MATERIAL_TYPE_4 set by input to: '|| $G_MATERIAL_TYPE_4);
        $G_STANDARD_LOAD = 'no';
        end
        else
        print('INFO - variable $G_MATERIAL_TYPE_4 with default value: '|| $G_MATERIAL_TYPE_4);

        if ($G_MATERIAL_TYPE_5 is not null and $G_MATERIAL_TYPE_5 <> 'FHMI')
        begin
        print('INFO - variable $G_MATERIAL_TYPE_5 set by input to: '|| $G_MATERIAL_TYPE_5);
        $G_STANDARD_LOAD = 'no';
        end
        else
        print('INFO - variable $G_MATERIAL_TYPE_5 with default value: '|| $G_MATERIAL_TYPE_5);

        print('INFO - Data load with standard settings variable $G_STANDARD_LOAD calculated value: '|| $G_STANDARD_LOAD);

        # Storage Location For Damaged Products
        if ($G_STOLOC_DAMAGED is null or $G_STOLOC_DAMAGED = '0099')
        print('INFO - IBP Storage Location For Damaged Products variable $G_STOLOC_DAMAGED with default value: '|| $G_STOLOC_DAMAGED);
        else
        begin
        print('INFO -  IBP Storage Location For Damaged Products variable $G_STOLOC_DAMAGED set by input to: '|| $G_STOLOC_DAMAGED);
        $G_STANDARD_LOAD = 'no';
        end

        # MRP Controller 1
        if ($G_MRP_CONTROLLER_1 is null or $G_MRP_CONTROLLER_1 = '001')
        print('INFO - IBP MRP Controller variable $G_MRP_CONTROLLER_1 with default value: '|| $G_MRP_CONTROLLER_1);
        else
        begin
        print('INFO -  IBP Planning MRP Controller area variable $G_MRP_CONTROLLER_1 set by input to: '|| $G_MRP_CONTROLLER_1);
        $G_STANDARD_LOAD = 'no';
        end

        # MRP Controller 2
        if ($G_MRP_CONTROLLER_2 is null or $G_MRP_CONTROLLER_2 = '002')
        print('INFO - IBP MRP Controller variable $G_MRP_CONTROLLER_2 with default value: '|| $G_MRP_CONTROLLER_2);
        else
        begin
        print('INFO -  IBP Planning MRP Controller area variable $G_MRP_CONTROLLER_2 set by input to: '|| $G_MRP_CONTROLLER_2);
        $G_STANDARD_LOAD = 'no';
        end

        # MRP Controller 3
        if ($G_MRP_CONTROLLER_3 is null or $G_MRP_CONTROLLER_3 = '003')
        print('INFO - IBP MRP Controller variable $G_MRP_CONTROLLER_3 with default value: '|| $G_MRP_CONTROLLER_3);
        else
        begin
        print('INFO -  IBP Planning MRP Controller area variable $G_MRP_CONTROLLER_3 set by input to: '|| $G_MRP_CONTROLLER_3);
        $G_STANDARD_LOAD = 'no';
        end

        #####################################################################


        print('INFO - Variable Declaration End ===============================================================================================');
        print('INFO - Object Processing Section Start ========================================================================================');
