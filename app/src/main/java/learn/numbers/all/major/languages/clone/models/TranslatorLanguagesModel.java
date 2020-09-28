package learn.numbers.all.major.languages.clone.models;

public class TranslatorLanguagesModel {
    String languageCode ;
    String languageName ;

    public TranslatorLanguagesModel(String languageCode, String languageName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
