package learn.numbers.all.major.languages.clone.languagesutils;


import java.text.DecimalFormat;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.Language;

public class JAPANESENumConverter  implements Language {
    public String[] sNum = new String[]{"zero","hyaku","sen","hyaku-man","Oku",
            "Chō"};
    public final String[] tensNames = {
            "",
            " iti-zyuu", " ni-zyuu", " sen-zyuu", " si-zyuu", " go-zyuu", " roku-zyuu",
            " siti-zyuu", " hati-zyuu", " kyuu-zyuu",
    };

    public final String[] numNames = {
            "", " iti", " ni", " sen", " si", " go", " roku", " siti", " hati", " kyuu", " zyuu",
            " zyuu-iti", " zyuu-ni", " zyuu-sen", " zyuu-si", " zyuu-go", " zyuu-roku",
            " zyuu-siti", " zyuu-hati", " zyuu-kyuu"
    };
    public JAPANESENumConverter() {}

    @Override
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
        return numNames[number] + "-hyaku" + soFar;
    }

    @Override
    public String convertNumber(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

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
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanThousand(billions) + "-Oku ";
                break;
            default :
                tradBillions = convertLessThanThousand(billions) + "-Oku ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanThousand(millions) + "-hyaku-man ";
                break;
            default :
                tradMillions = convertLessThanThousand(millions) + "-hyaku-man ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "-iti-sen ";
                break;
            default :
                tradHundredThousands = convertLessThanThousand(hundredThousands) + "-sen ";
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





