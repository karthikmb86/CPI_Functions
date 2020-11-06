# Post Load script
        print('INFO - Remember successful load date: '|| $G_LOAD_DATE);
        save_data(job_name() || '-LOAD_DATE',to_char($G_LOAD_DATE,'yyyy-mm-dd hh24:mi:ss'));
        if ($G_STANDARD_LOAD = 'yes')
        begin
        print('INFO - Remember successful load date with standard settings: '|| $G_LOAD_DATE);
        save_data(job_name() || '-STANDARD_LOAD_DATE',to_char($G_LOAD_DATE,'yyyy-mm-dd hh24:mi:ss'));
        end