package ro.atelieruldigital.news.utils;

public class DataSingleton {
    private static final DataSingleton ourInstance = new DataSingleton();

    public static DataSingleton getInstance() {
        return ourInstance;
    }

    private DataSingleton() {
    }

    public String[] getCountryData() {

        String[] countries = new String[55];

        countries[0] = "None";
        countries[1] = "ae";    countries[2] = "ar";
        countries[3] = "at";    countries[4] = "au";
        countries[5] = "be";    countries[6] = "bg";
        countries[7] = "br";    countries[8] = "ca";
        countries[9] = "ch";    countries[10] = "cn";
        countries[11] = "co";   countries[12] = "cu";
        countries[13] = "cz";   countries[14] = "de";
        countries[15] = "eg";   countries[16] = "fr";
        countries[17] = "gb";   countries[18] = "gr";
        countries[19] = "hk";   countries[20] = "hu";
        countries[21] = "id";   countries[22] = "ie";
        countries[23] = "il";   countries[24] = "in";
        countries[25] = "it";   countries[26] = "jp";
        countries[27] = "kr";   countries[28] = "lt";
        countries[29] = "lv";   countries[30] = "ma";
        countries[31] = "mx";   countries[32] = "my";
        countries[33] = "ng";   countries[34] = "nl";
        countries[35] = "no";   countries[36] = "nz";
        countries[37] = "ph";   countries[38] = "pl";
        countries[39] = "pt";   countries[40] = "ro";
        countries[41] = "rs";   countries[42] = "ru";
        countries[43] = "sa";   countries[44] = "se";
        countries[45] = "sg";   countries[46] = "si";
        countries[47] = "sk";   countries[48] = "th";
        countries[49] = "tr";   countries[50] = "tw";
        countries[51] = "ua";   countries[52] = "us";
        countries[53] = "ve";   countries[54] = "za";

        return countries;
    }

    public String[] getCategoryData() {

        String[] categories = new String[8];

        categories[0] = "None";
        categories[1] = "business"; categories[2] = "entertainment";
        categories[3] = "general";  categories[4] = "health";
        categories[5] = "science";  categories[6] = "sports";
        categories[7] = "technology";

        return categories;
    }

    public String[] getLanguageData() {

        String[] languages = new String[15];

        languages[0] = "None";
        languages[1] = "ar";    languages[2] = "de";
        languages[3] = "en";    languages[4] = "es";
        languages[5] = "fr";    languages[6] = "he";
        languages[7] = "it";    languages[8] = "nl";
        languages[9] = "no";    languages[10] = "pt";
        languages[11] = "ru";   languages[12] = "se";
        languages[13] = "ud";   languages[14] = "zh";

        return languages;
    }

    public String[] getSortByData() {

        String[] sortBy = new String[4];

        sortBy[0] = "None";
        sortBy[1] = "relevancy";
        sortBy[2] = "popularity";
        sortBy[3] = "publishedAt";

        return sortBy;
    }
}
