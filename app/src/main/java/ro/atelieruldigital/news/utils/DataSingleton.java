package ro.atelieruldigital.news.utils;

public class DataSingleton {
    private static final DataSingleton ourInstance = new DataSingleton();

    public static DataSingleton getInstance() {
        return ourInstance;
    }

    private DataSingleton() {
    }

    public String[] getCountryData() {

        String[] countries = new String[54];

        countries[0] = "ae";    countries[1] = "ar";
        countries[2] = "at";    countries[3] = "au";
        countries[4] = "be";    countries[5] = "bg";
        countries[6] = "br";    countries[7] = "ca";
        countries[8] = "ch";    countries[9] = "cn";
        countries[10] = "co";   countries[11] = "cu";
        countries[12] = "cz";   countries[13] = "de";
        countries[14] = "eg";   countries[15] = "fr";
        countries[16] = "gb";   countries[17] = "gr";
        countries[18] = "hk";   countries[19] = "hu";
        countries[20] = "id";   countries[21] = "ie";
        countries[22] = "il";   countries[23] = "in";
        countries[24] = "it";   countries[25] = "jp";
        countries[26] = "kr";   countries[27] = "lt";
        countries[28] = "lv";   countries[29] = "ma";
        countries[30] = "mx";   countries[31] = "my";
        countries[32] = "ng";   countries[33] = "nl";
        countries[34] = "no";   countries[35] = "nz";
        countries[36] = "ph";   countries[37] = "pl";
        countries[38] = "pt";   countries[39] = "ro";
        countries[40] = "rs";   countries[41] = "ru";
        countries[42] = "sa";   countries[43] = "se";
        countries[44] = "sg";   countries[45] = "si";
        countries[46] = "sk";   countries[47] = "th";
        countries[48] = "tr";   countries[49] = "tw";
        countries[50] = "ua";   countries[51] = "us";
        countries[52] = "ve";   countries[53] = "za";

        return countries;
    }

    public String[] getCategoryData() {

        String[] categories = new String[7];

        categories[0] = "business"; categories[1] = "entertainment";
        categories[2] = "general";  categories[3] = "health";
        categories[4] = "science";  categories[5] = "sports";
        categories[6] = "technology";

        return categories;
    }
}
