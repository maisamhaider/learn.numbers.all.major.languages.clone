package learn.numbers.all.major.languages.clone.languagesutils;


import java.text.DecimalFormat;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.Language;

public class PORTUGUESENumConverter implements Language {

    public String[] sNum = new String[]{"zero","cem","mil","milhão","bilhão","trilhão"};
    public final String[] tensNames = {
            "", " dez", " vinte", " trinta", " quarenta", " cinquenta", " sessenta", " setenta", " oitenta",
            " noventa"
    };

    public final String[] numNames = {
            "",
            " um", " dois", " três", " quatro", " cinco", " seis", " sete", " oito", " nove", " dez",
            " onze", " doze", " treze", " quatorze", " quinze", " dezesseis", " dezessete",
            " dezoito", " dezenove"
    };
    public PORTUGUESENumConverter() {}

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
        return numNames[number] + " cem" + soFar;
    }


    public  String convertNumber(long number) {
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
                tradBillions = convertLessThanThousand(billions) + " bilhão ";
                break;
            default :
                tradBillions = convertLessThanThousand(billions) + " bilhão ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanThousand(millions) + " milhão ";
                break;
            default :
                tradMillions = convertLessThanThousand(millions) + " milhão ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "um mil ";
                break;
            default :
                tradHundredThousands = convertLessThanThousand(hundredThousands) + " mil ";
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
