package learn.numbers.all.major.languages.clone.models;

import java.util.ArrayList;

public class OtherNumbersModel {
    private ArrayList<String> numbArray;
     private String[] numWordArray;
    private String[] numPronArray;

    public OtherNumbersModel(ArrayList<String> numbArray, String[] numWordArray, String[] numPronArray) {
        this.numbArray = numbArray;
        this.numWordArray = numWordArray;
        this.numPronArray = numPronArray;
    }

    public ArrayList<String> getNumbArray() {
        return numbArray;
    }

    public String[] getNumWordArray() {
        return numWordArray;
    }

    public String[] getNumPronArray() {
        return numPronArray;
    }
}
