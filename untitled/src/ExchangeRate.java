public class ExchangeRate {

    ifthenelse(

            //Main Condition
            ZCEXCRATE.EXCHANGERATE>0,
            //True
            ZCEXCRATE.EXCHANGERATE,

            //MainFalse
            (ifthenelse(

                    //Nested Condition
                    ZCEXCRATE.TFACT>0 and ZCEXCRATE.FFACT>0,

            //NestedTrue
            (1/abs(ZCEXCRATE.EXCHANGERATE)) *(ZCEXCRATE.TFACT/ZCEXCRATE.FFACT),

            //Nested False
            1/abs(ZCEXCRATE.EXCHANGERATE)
    ) //End of Nested if else
            )  //End of MainFalse

            )



}

