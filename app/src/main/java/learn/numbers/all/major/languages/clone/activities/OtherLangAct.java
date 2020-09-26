package learn.numbers.all.major.languages.clone.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.adapters.OtherLanguagesAdapter;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.models.OtherNumbersModel;


public class OtherLangAct extends AppCompatActivity {

    String[] arabicEngPronounce;
    String[] persianEngPronounce;
    String[] pakhtuEngPronounce;
    String[] urduEngPronounce;
    String[] arabicWords;
    String[] persianWords;
    String[] pakhtuWords;
    String[] urduWords;
    OtherLanguagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_languages);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initArrays();

        RecyclerView other_language_rv = findViewById(R.id.extra_language_rv);
        TextView textView6 = findViewById(R.id.textView6);
        ArrayList<OtherNumbersModel> list = new ArrayList<>();
        String s_extra_lang = getIntent().getStringExtra(MyAnno.Extra_LANGUAGE_KEY);
        textView6.setText(s_extra_lang);
        if (s_extra_lang.matches(MyAnno.Arabic)) {
            list.add(new OtherNumbersModel(getOneToHundArabic(), arabicWords, arabicEngPronounce));
            adapter = new OtherLanguagesAdapter(this, getOneToHundArabic(), arabicWords,
                    arabicEngPronounce);
        } else if (s_extra_lang.matches(MyAnno.Persian)) {
            list.add(new OtherNumbersModel(getOneToHundArabic(), persianWords, persianEngPronounce));
            adapter = new OtherLanguagesAdapter(this, getOneToHundArabic(), persianWords,
                    persianEngPronounce);

        } else if (s_extra_lang.matches(MyAnno.Pakhtu)) {
            list.add(new OtherNumbersModel(getOneToHundArabic(), pakhtuWords, pakhtuEngPronounce));
            adapter = new OtherLanguagesAdapter(this, getOneToHundArabic(), pakhtuWords,
                    pakhtuEngPronounce);

        } else {
            list.add(new OtherNumbersModel(getOneToHundArabic(), urduWords, urduEngPronounce));
            adapter = new OtherLanguagesAdapter(this, getOneToHundArabic(), urduWords,
                    urduEngPronounce);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        other_language_rv.setLayoutManager(layoutManager);
        other_language_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void initArrays() {

        urduWords = new String[]{"صفر", "ایک", "دو", "تین", "چار", "پانچ", "چھ", "سات", "آٹھ", "نو",
                "دس", "گیارہ", "بارہ", "تیرہ", "چودہ", "پندرہ", "سولہ", "سترہ", "اٹھارہ", "انیس", "بیس",
                "اکیس", "بائیس", "تئیس", "چوبیس", "پچیس", "چھببیس", "ستائیس", "اٹھائیس", "انتیس",
                "تیس", "اکتیس", "بتیس", "تینتیس", "چونتیس", "پینتیس", "چھتیس", "سینتیس", "اڑتیس",
                "انتالیس", "چالیس", "اکتالیس", "بیالیس", "تینتالیس", "چوالیس", "پینتالیس", "چھیالیس",
                "سینتالیس", "اڑتالیس", "انچاس", "پچاس", "اکیاون", "باون", "ترپن", "چون", "پچپن", "چھپن",
                "ستاون", "اٹھاون", "انسٹھ", "ساٹھ", "اکسٹھ", "باسٹھ", "ترسٹھ", "چوسٹھ", "پینسٹھ",
                "چھیاسٹھ", "سڑسٹھ", "اٹھسٹھ", "انہتر", "ستر", "اکہتر", "بہتر", "تہتر", "چوہتر", "پچہتر",
                "چھہتر", "ستتر", "اٹھہتر", "اناسي", "اسی", "اکیاسی", "بیاسي", "تراسي", "چوراسي",
                "پچاسي", "چھیاسي", "ستاسي", "اٹھاسي", "نواسی", "نوے", "اکانوے", "بانوے", "ترانوے",
                "چورانوے", "پچانوے", "چھیانوے", "ستانوے", "اٹھانوے", "ننانوے", "سو"};

        arabicWords = new String[]{
                "صفر", "وَاحِد", "اِثْنَان", "ثَلَاثَة", "أَرْبَعَة", "خَمْسَة", "سِتَّة", "سَبْعَة", "ثَمَانِيَة", "تِسْعَة",
                "عَشَرَة", "أَحَدَ َ عَشَر", "اِثْنَا عَشَرَ", "ثَلَاثَةَ عَشَرَ", "أَرْبَعَة عَشَرََ", "خَمْسَةَ عَشَرَ", "سِتَّةَ عَشَرَ",
                "سَبْعَةَ عَشَرَ", "ثَمَانِيَة عَشَر ََ", "عَشَرَ تِسْعَةَ", "عِشْرُونَ", " وَاحِد و عِشْرُونَ", " اِثْنَان و عِشْرُونَ",
                " ثَلَاثَة و عِشْرُونَ", "أَرْبَعَة و عِشْرُونَ", "خَمْسَة و عِشْرُونَ", "سِتَّة و عِشْرُونَ", "سَبْعَة و عِشْرُونَ",
                "ثَمَانِيَة و عِشْرُونَ", "تِسْعَة و عِشْرُونَ", "ثَلَاثُونَ", "وَاحِد و ثَلَاثُونَ", "اِثْنَان و ثَلَاثُونَ",
                "ثَلَاثَة و ثَلَاثُونَ", "أَرْبَعَة و ثَلَاثُونَ", "خَمْسَة و ثَلَاثُونَ", "سِتَّة و ثَلَاثُونَ", "سَبْعَة و ثَلَاثُونَ",
                "ثَمَانِيَة و ثَلَاثُونَ", "تِسْعَة و ثَلَاثُونَ", "أَرْبَعُونَ", "وَاحِد و أَرْبَعُونَ", "اِثْنَان و أَرْبَعُونَ",
                "ثَلَاثَة و أَرْبَعُونَ", "أَرْبَعَة و أَرْبَعُونَ", "خَمْسَة و أَرْبَعُونَ", "سِتَّة و أَرْبَعُونَ", "سَبْعَة و أَرْبَعُونَ",
                "ثَمَانِيَة و أَرْبَعُونَ", "تِسْعَة و أَرْبَعُونَ", "خَمْسُونَ", "وَاحِد و خَمْسُونَ", "اِثْنَان و خَمْسُونَ",
                "ثَلَاثَة و خَمْسُونَ", "أَرْبَعَة و خَمْسُونَ", "خَمْسَة و خَمْسُونَ", "سِتَّة و خَمْسُونَ", "سَبْعَة و خَمْسُونَ",
                "ثَمَانِيَة و خَمْسُونَ", "تِسْعَة و خَمْسُونَ", "سِتُّونَ", "وَاحِد و سِتُّونَ", "اِثْنَان و سِتُّونَ",
                "ثَلَاثَة و سِتُّونَ", "أَرْبَعَة و سِتُّونَ", "خَمْسَة و سِتُّونَ", "سِتَّة و سِتُّونَ", "سَبْعَة و سِتُّونَ",
                "ثَمَانِيَة و سِتُّونَ", "تِسْعَة و سِتُّونَ", "سَبْعُونَ", "وَاحِد و سَبْعُونَ", "اِثْنَان و سَبْعُونَ",
                "ثَلَاثَة و سَبْعُونَ", "أَرْبَعَة و سَبْعُونَ", "خَمْسَة و سَبْعُونَ", "سِتَّة و سَبْعُونَ", "سَبْعَة و سَبْعُونَ",
                "ثَمَانِيَة و سَبْعُونَ", "تِسْعَة و سَبْعُونَ", "ثَمَانُونَ", "وَاحِد و ثَمَانُونَ", "اِثْنَان و ثَمَانُونَ",
                "ثَلَاثَة و ثَمَانُونَ", "أَرْبَعَة و ثَمَانُونَ", "خَمْسَة و ثَمَانُونَ", "سِتَّة و ثَمَانُونَ", "سَبْعَة و ثَمَانُونَ",
                "ثَمَانِيَة و ثَمَانُونَ", "تِسْعَة و ثَمَانُونَ", "تِسْعُونَ ", "وَاحِد و تِسْعُونَ", "اِثْنَان و تِسْعُونَ",
                "ثَلَاثَة و تِسْعُونَ", "أَرْبَعَة و تِسْعُونَ", "خَمْسَة و تِسْعُونَ", "سِتَّة و تِسْعُونَ", "سَبْعَة و تِسْعُونَ",
                "ثَمَانِيَة و تِسْعُونَ", "تِسْعَة و تِسْعُون", "مِئَة"};

        persianWords = new String[]{"صفر", "يک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت",
                "نه", "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده",
                "'وزده", "بیست", " بیست و یک", "بیست و دو", "بیست و سه", "بیست و چهار",
                "بیست و پنج", "بیست و شش", "بیست و هفت", "بیست و هشت", "بیست و نه", "سی",
                "سی و یک", "سی و دو", "سی و سه", "سی و چهار", "سی و پنج", "سی و شش",
                " سی و هفت", "سی و هشت", "سی و نه", "چهل", "چهل و یک", "چهل و دو",
                "چهل و سه", "چهل و چهار", "چهل و پنج", "چهل و شش", "چهل و هفت", "چهل و هشت",
                "چهل و نه", "پنجاه", "پنجاه و یک", "پنجاه و دو", "پنجاه و سه", "پنجاه و چهار",
                "پنجاه و پنج", "پنجاه و شش", "پنجاه و هفت", "پنجاه و هشت", "پنجاه و نه", "شصت",
                "شصت و یک ", "شصت و دو", "شصت و سه", "شصت و چهار", "شصت و پنج",
                "شصت و شش", "شصت و هفت", "شصت و هشت", "شصت و نه", "هفتاد", "هفتاد و یک",
                "هفتاد و دو", "هفتاد و سه", "هفتاد و چهار", "هفتاد و پنج", "هفتاد و شش",
                "هفتاد و هفت", "هفتاد و هشت", "هفتاد و نه", "هشتاد", "هشتاد و یک", "هشتاد و دو",
                "هشتاد و سه", "هشتاد و چهار", "هشتاد و پنج", "هشتاد و شش", "هشتاد و هفت",
                "هشتاد و هشت", "هشتاد و نه", "نود", "نود و یک", "نود و دو", "نود و سه",
                "نود و چهار", "نود و پنج", "نود و شش", "نود و هفت", "نود و هشت", "نود و نه", "صد"};

        pakhtuWords = new String[]{"صفر", "یو", "دوه", "درې", "څلور", "پنځه", "شپږ", "اووه", "اته",
                "نهه", "لس", "یولس", "دولس", "دیارلس", "څوارلس", "پنځلس", "شپاړس", "اولس",
                "اتلس", "نولس", "شل", "یوویشت", "دو ویشت", "درې ويشت", "څلوروېشت", "پنځه ویشت",
                "شپږویشت", "اوويشت", "اته ویشت", "نهه ويشت", "دیرش", "یو دیرش", "دوه دیرش",
                "درې دېرش", "څلور دېرش", "پنځه دیرش", " شپږ دیرش", "اووه دیرش", "اته اتیا",
                "نهه دیرش", "څلوېښت", "یوڅلويښت", "دوه څلویښت", "درې دېرش", "څلور څلوېښت",
                "پنځه پنځوس", " شپږ څلویښت", "اووه څلوېښت", "اته څلوېښت", "نهه څلوېښت", "پنځوس",
                "یو پنځوس", "دوه پنځوس", "درې پنځوس", "څلور پنځوس", "پنځه پنځوس", "شپږ پنځوس",
                "اوه پنځوس", "اته پنځوس", "نهه پنځوس", "شپیته", "يوو شپیته", "دوه شپیته", "درې شپېته",
                "څلور شپېته", "پنځه شپېته", "شپږ شپیته", "اووه شپېته", "اته شپېته", "نهه شپیته", "اویا",
                "یو اویا", "دوه اویا", "درې اویا", "څلور اویا", "پنځه اویا", "شپږ اتیا", "اوه اتیا", "اته اویا",
                "نهه اویا", "اتیا", "یو اتیا", "دوه اتیا", "درې اتیا", "څلور اتیا", "پنځه اتیا", "شپږ اتیا",
                "اوه اتیا", "اته اتیا", "اته اتیا", "نوي", "یو نوي", "دوه نوي", "دری نوي", "څلور نوي",
                "پنځه نوي", "شپږ نوي", "اوه نوي", "اته اتیا", "نهه نوي", "سل"};

        urduEngPronounce = new String[]{
                "Sifr", "Aik", "Do", "Teen", "Char", "Paanch", "chay", "Saat", "aath", "No", "Das",
                "Giyarah", "Baarah", "Terah", "Chaudah", "pandra", "Solah", "Sathrah", "Ataarah",
                "Unnees", "Bees", "Ikkees", "Baais", "Taees", "Chaubees", "Pachees", "Chabbees",
                "Staees", "atayees", "Untees", "Tees", "Ikathees", "Bathees", "Thenthees",
                "Chauntees", "Pantees", "Chatees", "Santees", "Adthees", "Untaalees",
                "Chalees", "Iktalees", "Biyalees", "Tentalees", "Chavalees", "Pantalees",
                "Chiyalees", "Santalees", "Adtaalees", "Unchaas", "Pachaas", "Ikyavan", "Baavan",
                "Tarippan", "Chawwan", "Pachpan", "Chappan", "Staavan", "Athaavan", "Unsat", "Saat",
                "Iksat", "Baasat", "Traisat", "Chaunsat", "Painsat", "Chiyasat", "Sadsat", "Adsat",
                "Unhattar", "Sattar", "Ikhattar", "Behattar", "Tihattar", "Chauhattar", "Pachattar",
                "Chihattar", "Satattar", "Athhattar", "Unaasi", "Assi", "Ikyaasi", "Bayaasi",
                "Tiraasi", "Chaurassi", "Pachaasi", "Chiyaasi", "Sataasi", "Athaasi", "Navassi",
                "Navvay", "Ikyaanvey", "Baanvay", "Tiraanvay", "Chauraanvay", "Pachaanvay",
                "Chiyanvay", "Satanvay", "Athanvay", "Ninanvay", "Sow"};

        arabicEngPronounce = new String[]{
                "sifr", "wahid", "ithnan", "salasah", "arbaa", "khamsa", "sitha", "suba", "samanya",
                "thisa", "ashar", "ahada ashar", "ithnan ashar", "salasah ashar", "arbaa ashar",
                "khamsa ashar", "sitta ashar", "suba ashar", "samanya ashar", "thisa ashar ",
                "ishrun", "wahid wa ishrun", "ithnan wa ishrun", "salasah wa ishrun",
                "arbaa wa ishrun", "khamsa wa ishrun", "sitta wa ishrun", "suba wa ishrun",
                "samanya wa ishrun", "thisa wa ishrun", "salasun", "wahid wa salasun",
                "ithnan wa salasun", "salasah wa salasun", "arbaa wa salasun",
                "khamsa wa salasun", "sitta wa salasun", "suba wa salasun",
                "samanya wa salasun", "thisa wa salasun", "arba-on", "wahid wa arba-on",
                "ithnan wa arba-on", "salasah wa arba-on", "arbaa wa arba-on", "khamsa wa arba-on",
                "sitta wa arba-on", "suba wa arba-on", "samanya wa arba-on", "thisa wa arba-on",
                "khamson", "wahid wa khamson", "ithnan wa khamson", "salasah wa khamson",
                "arbaa wa khamson", "khamsa wa khamson", "sitta wa khamson", "suba wa khamson",
                "samanya wa khamson", "thisa wa khamson", "sitton", "wahid wa sitton",
                "ithnan wa sitton", "salasah wa sitton", "arbaa wa sitton", "khamsa wa sitton",
                "sitta wa sitton", "suba wa sitton", "samanya wa sitton", "thisa wa sitton",
                "sub-on", "wahid wa sub-o", "ithnan wa sub-o", "salasah wa sub-o",
                "arbaa wa sub-o", "khamsa wa sub-o", "ّitta wa sub-o", "suba wa sub-o ",
                "samanya wa sub-o", "thisa wa sub-o  ", "sama-nun", "ِwahid wa sama-nun ",
                " ithnan wa sama-nun", "salasah wa sama-nun", "arbaa wa sama-nun", "khamsa wa sama-nun",
                "sitta wa sama-nun", "suba wa sama-nun", "samanya wa sama-nun",
                "thisa wa sama-nun", "this-on", "wahid wa this-on", "ithnan wa this-on ",
                "salasah wa this-on ", "arbaa wa this-on", "khamsa wa this-on", "sitta wa this-on",
                "suba wa this-on", "samanya wa this-on", "thisa wa this-on", "miyathan"};

        persianEngPronounce = new String[]{
                "sifr", "yek", "dō", "sé", "chāhār", "panj", "sheesh", "haft",
                "hasht", "noh", "dah", "yâzdah", "davâzdah", "sizdah", "chahârdah", "ponzdah",
                "shonzdah", "hifdah", "hijdah", "nozdah", "bisth", "bist o yek",
                "bist o dō", "bist o seh", "bist o chahâr", "bist o panj", "bist o shish",
                "bist o haft", "bist o hasht", "bist o noh", "si", "si o yek", "si o dō", "si o seh",
                "si o chahâr", "si o panj", "si o shesh", "si o haft", "si o hasht", "si o noh",
                "chehel", "chehel o yek", "chehel o dō", "chehel o seh", "chehel o chahâr",
                "chehel o panj", "chehel o shesh", "chehel o haft", "chehel o hasht",
                "chehel o noh", "panjâh", "panjâh o yek", "panjâh o dō", "panjâh o seh",
                "panjâh o chahâr", "panjâh o panj", "panjâh o shesh", "panjâh o haft",
                "panjâh o hasht", "panjâh o noh", "shast", "shast o yek", "shast o dō",
                "shast o seh", "shast o chahâr", "shast o panj", "shast o shesh", "shast o haft",
                "shast o hasht", "shast o noh", "haftâd", "haftâd o yek", "haftâd o dō",
                "haftâd o seh", "haftâd o chahâr", "haftâd o panj", "haftâd o shesh", "haftâd o haft",
                "haftâd o hasht", "haftâd o noh", "hashtâd", "hashtâd o yek", "hashtâd o dō",
                "hashtâd o seh", "hashtâd o chahâr", "hashtâd o panj", "hashtâd o shesh",
                "hashtâd o haft", "hashtâd o hasht", "hashtâd o noh", "navad", "navad o yek",
                "navad o dō", "navad o seh", "navad o chahâr", "navad o panj", "navad o shesh",
                "navad o haft", "navad o hasht", "navad o noh", "sad"};

        pakhtuEngPronounce = new String[]
                {"sifr", "yo", "dua", "dray", "saloor", "pindza", "špag", "owa", "atha", "naha", "lus",
                        "yow-lus", "do-lus", "dyar-lus", "swar-lus", "pinza-lus", "špaa-rass",
                        "wa-lus", "atha-lus", "no-nas", "shal", "yu-wisht", "du-wisht", "dray-wisht",
                        "saloor-wisht", "pindza-wisht", "špag-wisht", "wa-wisht", "atha-wisht",
                        "naha-wisht", "dersh", "yu-dersh", "dua-dersh", "dray-dersh",
                        "saloor-dersh", "pindza-dersh", "špag-dersh", "wa-dersh", "atha-dersh",
                        "naha-dersh", "sulwēkht", "you-sulwēkht", "dua-sulwēkht", "dray-sulwēkht",
                        "saloor-sulwēkht", "pindza-sulwēkht", "špag-sulwēkht", "wa-sulwēkht",
                        "atha-sulwēkht", "naha-sulwēkht", "pendzos", "yu-pendzos", "dua-pendzos",
                        "dray-pendzos", "saloor-pendzos", "pindza-pendzos", "špag-pendzos",
                        "wa-pendzos", "atha-pendzos", "naha-pendzos", "špetha", "yu-špetha",
                        "dua-špetha", "dray-špetha", "saloor-špetha", "pindza-špetha", "špag-špetha",
                        "wa-špetha", "atha-špetha", "naha-špetha", "auyiâ", "yu-auyiâ", "dua-auyiâ",
                        "dray-auyiâ", "saloor-auyiâ", "pindza-auyiâ", "špag-auyiâ",
                        "wa-auyiâ", "atha-auyiâ", "naha-auyiâ", "atya", "yu-atya", "dua-atya",
                        "dray-atya", "saloor-atya", "pindza-atya", "špag-atya",
                        "wa-atya", "atha-atya", "naha-atya", "nowi", "yu-nowi", "dua-nowi",
                        "dray-nowi", "saloor-nowi", "pindza-nowi", "špag-nowi", "wa-nowi",
                        "atha-nowi", "naha-nowi", "sal"
                };
    }

    public ArrayList<String> getOneToHundArabic() {
        ArrayList<String> numbersAL = new ArrayList<>();
        String[] numbersArray = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};

        for (int i = 0; i <= numbersArray.length; i++) {
            String firstNum = "";
            if (i <= 9) {
                firstNum = numbersArray[i];
                for (int j = 0; j < numbersArray.length; j++) {
                    if (i == 0) {
                        numbersAL.add(numbersArray[j]);
                        Log.i("num", numbersArray[j]);
                    } else {
                        numbersAL.add(firstNum + numbersArray[j]);
                        Log.i("num", firstNum + numbersArray[j]);
                    }
                }
            } else {
                firstNum = numbersArray[1];
                Log.i("num", firstNum + numbersArray[0] + numbersArray[0]);
                numbersAL.add(firstNum + numbersArray[0] + numbersArray[0]);
            }
        }
        return numbersAL;
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.shutDown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.shutDown();
    }

}