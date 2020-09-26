package learn.numbers.all.major.languages.clone.interfaces;

public interface Language {

    String[] tensNames = null;
    String[] sNumber = null;
    String[] numberNames = null;

    String convertLessThanThousand(int number);

    String convertNumber(long number);
    String selectedNumber(String which);


}
