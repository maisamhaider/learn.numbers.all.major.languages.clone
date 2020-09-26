package learn.numbers.all.major.languages.clone.languagesutils;


import java.text.DecimalFormat;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.Language;

public class IRISHNumConverter implements Language {

    public String[] sNum = new String[]{"nialas","céad","míle","milliún","billiún","trilliún"};
    public final String[] tensNames = {
            "", " a deich", " fiche", " tríocha", " daichead", " caoga", " seasca", " seachtó", " ochtó",
            " nócha"
    };
    public final String[] numNames = {
            "",
            " a haon", " a dó", " a trí", " a ceathrar", " a cúig", " a sé", " a seacht",
            " a hocht", " a naoi", " a deich", " a haon déag", " a dó dhéag", " a trí déag",
            " a ceathair déag", " a cúig déag", " a sé déag", " aseacht déag", " a hocht déag",
            " a naoi déag"
    };
    public IRISHNumConverter() {}

    public String convertLessThanThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " hundred" + soFar;
    }


    public  String convertNumber(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "nialas"; }

        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        if (billions == 0) {
            tradBillions = "";
        } else {
            tradBillions = convertLessThanThousand(billions) + " billiún ";
        }
        String result =  tradBillions;

        String tradMillions;
        if (millions == 0) {
            tradMillions = "";
        } else {
            tradMillions = convertLessThanThousand(millions) + " milliún ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "a haon míle ";
                break;
            default :
                tradHundredThousands = convertLessThanThousand(hundredThousands) + " míle ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
    @Override
    public String selectedNumber(String which) {

        switch (which) {
            case MyAnno.Zero:
                return sNum[0];
            case MyAnno.Hundred:
                return sNum[1];
            case MyAnno.Thousand:
                return sNum[2];
            case MyAnno.Million:
                return sNum[3];
            case MyAnno.Billion:
                return sNum[4];
            case MyAnno.Trillion:
                return sNum[5];
        }
        return "";

    }
}
