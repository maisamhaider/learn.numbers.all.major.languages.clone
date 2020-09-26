package learn.numbers.all.major.languages.clone.languagesutils;


import java.text.DecimalFormat;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.Language;

public class GEORGIANNumConverter implements Language {

    public String[] sNum = new String[]{"nuli","asi","atasi","milioni","miliardi","trillion"};
    public final String[] tensNames = {
            "", " ten", " otsi", " otsdaati", " ormotsi", " ormotsdaati", " samotsi", " samotsdaati", " otkhmotsi",
            " otkhmotsdaati"
    };



    public final String[] numNames = {
            "",
            " erti", " ori", " sami", " otkhi", " khuti", " ekvsi", " švidi", " rva", " tskhra", " ati",
            " tertmetee", " tormetee", " tsametee", " totkhmetee", " tkhutmetee", " tekvsmetee", " čvidmetee",
            " tvrametee", " tskhrametee"
    };
    public GEORGIANNumConverter() {}

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
        return numNames[number] + " asi" + soFar;
    }


    public  String convertNumber(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "nuli"; }

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
            tradBillions = convertLessThanThousand(billions) + " miliardi ";
        }
        String result =  tradBillions;

        String tradMillions;
        if (millions == 0) {
            tradMillions = "";
        } else {
            tradMillions = convertLessThanThousand(millions) + " milioni ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "erti atasi) ";
                break;
            default :
                tradHundredThousands = convertLessThanThousand(hundredThousands) + " atasi) ";
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
