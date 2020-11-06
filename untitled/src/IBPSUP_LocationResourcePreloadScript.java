print('INFO - Current And Last Successful Load Dates Start ===========================================================================');

        # $G_LOAD_DATE - load date of the job.
        if ( $G_LOAD_DATE is not NULL and abs(date_diff($G_LOAD_DATE, sysutcdate(), 'S')) > 10 ) print('WARNING - Value ' || $G_LOAD_DATE || ' of load date variable $G_LOAD_DATE set by input is ignored, because value is calculated');
        $G_LOAD_DATE = sysutcdate();
        print('INFO - Current load date $G_LOAD_DATE calculated value: '|| $G_LOAD_DATE);

        # Set Global Variables used by IBP stored procedure call
        # ----------------------------------------------------------

        # Planning Area
        if ($G_PLAN_AREA is null or $G_PLAN_AREA = 'MRODEV1INT')
        print('INFO - IBP Planning area variable $G_PLAN_AREA with default value: '|| $G_PLAN_AREA);
        else
        begin
        print('INFO -  IBP Planning area variable $G_PLAN_AREA set by input to: '|| $G_PLAN_AREA);
        end

        # IBP Scenario - acceptable values are:
        $G_SCENARIO = nvl($G_SCENARIO, '');
        if ($G_SCENARIO = '') print('INFO - IBP planning scenario $G_SCENARIO with default value: '|| $G_SCENARIO);
        else
        begin
        print('INFO -  IBP planning scenario $G_SCENARIO set by input to: '|| $G_SCENARIO);
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
        end

        # Time Profile - Time profile. You must get this numeric ID from your time profile table in IBP.
        $G_TIME_PROFILE = nvl($G_TIME_PROFILE, -1);
        if ($G_TIME_PROFILE = -1) print('INFO - IBP time profile $G_TIME_PROFILE with default value: '|| $G_TIME_PROFILE);
        else
        begin
        print('INFO -  IBP time profile $G_TIME_PROFILE set by input to: '|| $G_TIME_PROFILE);
        end
        #####################################################################


        print('INFO - Variable Declaration End ===============================================================================================');
        print('INFO - Object Processing Section Start ========================================================================================');
