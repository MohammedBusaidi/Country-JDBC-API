import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;

public class APIConsumer {
    static ArrayList<Country> countries = new ArrayList<Country>();

    public void showAllCountries() {
        String apiUrl = "https://restcountries.com/v3.1/all";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder json = new StringBuilder();

            while ((output = br.readLine()) != null) {
                json.append(output);
            }

            conn.disconnect();

            Gson gson = new Gson();
            Country[] country = gson.fromJson(json.toString(), Country[].class);
            System.out.println("=============================================================================");
            for (Country myCountry : country) {
                System.out.println("Common: " + myCountry.name.common);
                System.out.println("Official: " + myCountry.name.official);

                System.out.println("NativeName: ");
                if (myCountry.name.nativeName != null && !myCountry.name.nativeName.isEmpty()) {
                    for (Entry<String, NativeName> entry : myCountry .name.nativeName.entrySet()) {
                        String key = entry.getKey();
                        NativeName value = entry.getValue();
                        System.out.println("  *" + key + "*");
                        System.out.println("    " + "Official: " + value.official);
                        System.out.println("    " + "Common:   " + value.common);
                    }
                    if (myCountry.tld != null) {
                        for (String tld : myCountry.tld) {
                            System.out.println("  Tld:  " + tld);
                        }
                        System.out.println("  Cca2: " + myCountry.cca2);
                        System.out.println("  Ccn3: " + myCountry.ccn3);
                        System.out.println("  Cca3: " + myCountry.cca3);
                        System.out.println("  Cioc: " + myCountry.cioc);
                        System.out.println("  Independent: " + myCountry.independent);
                        System.out.println("  Status: " + myCountry.status);
                        System.out.println("  UnMember: " + myCountry.unMember);
                    }
                    System.out.println("Currencies :");
                    if (myCountry.currencies != null && !myCountry.currencies.isEmpty()) {
                        for (Entry<String, Currencies> entry : myCountry.currencies.entrySet()) {
                            String key = entry.getKey();
                            Currencies value = entry.getValue();
                            System.out.println("  *" + key + "*");
                            System.out.println("    " + "Name: " + value.name);
                            System.out.println("    " + "Symbol:   " + value.symbol);
                        }
                        System.out.println("Idd: " );
                        System.out.println("    " + "Root: " + myCountry.idd.root);
                        if (myCountry.idd.suffixes != null) {
                            for (String idd : myCountry.idd.suffixes) {
                                System.out.println("    " + "Suffixes: " + idd);
                            }
                        }

                        if (myCountry.capital != null) {
                            for (String capital : myCountry.capital) {
                                System.out.println("  Capital: " + capital);
                            }
                        }

                        if (myCountry.altSpellings != null) {
                            System.out.println("AltSpellings: ");
                            for (String altSpellings : myCountry.altSpellings) {
                                System.out.println("   " + altSpellings);
                            }
                        }

                        System.out.println("Region: " + myCountry.region);
                        System.out.println("SubRegion: " + myCountry.subregion);

                        System.out.println("Languages:");
                        if (myCountry.languages != null && !myCountry.languages.isEmpty()) {
                            for (Entry<String, String> entry : myCountry.languages.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                System.out.println("  *" + key + "*");
                                System.out.println("    " + value);
                            }
                        }
                        System.out.println("Translations:");
                        if (myCountry.translations != null && !myCountry.translations.isEmpty()) {
                            for (Entry<String, Translations> entry : myCountry.translations.entrySet()) {
                                String key = entry.getKey();
                                Translations value = entry.getValue();
                                System.out.println("  *" + key + "*");
                                System.out.println("    " + "Official: " + value.official);
                                System.out.println("    " + "Common:   " + value.common);
                            }
                        }
                        if (myCountry.latlng != null) {
                            System.out.println("  Latlng: ");
                            for (double latlng : myCountry.latlng) {
                                System.out.println("    " + latlng);
                            }
                        }

                        System.out.println("Landlocked: " + myCountry.landlocked);

                        if (myCountry.borders != null) {
                            System.out.println("  Borders: ");
                            for (String borders : myCountry.borders) {
                                System.out.println("    " + borders);
                            }
                        }
                        System.out.println("Area: " + myCountry.area);
                        System.out.println("Demonyms:");
                        if (myCountry.demonyms != null && !myCountry.demonyms.isEmpty()) {
                            for (Entry<String, Demonyms> entry : myCountry.demonyms.entrySet()) {
                                String key = entry.getKey();
                                Demonyms value = entry.getValue();
                                System.out.println("  *" + key + "*");
                                System.out.println("    " + "f: " + value.f);
                                System.out.println("    " + "m: " + value.m);
                            }
                        }
                        System.out.println(" Flag: " + myCountry.flag);
                        System.out.println("Map: ");
                        System.out.println("googleMaps:       " + myCountry.maps.googleMaps);
                        System.out.println("openStreetMaps:   " + myCountry.maps.openStreetMaps);

                        System.out.println("Population: " + myCountry.population);

                        System.out.println("fifa: " + myCountry.fifa);

                        if (myCountry.car.signs != null) {
                            System.out.println("Car: ");
                            System.out.println("Signs: ");
                            for (String sign : myCountry.car.signs) {
                                System.out.println("      "+ sign);
                            }
                            System.out.println("side: ");
                            System.out.println("      "+ myCountry.car.side);
                        }

                        if (myCountry.timezones != null) {
                            System.out.println("Timezones: ");
                            for (String timezones : myCountry.timezones) {
                                System.out.println("    " + timezones);
                            }
                        }

                        if (myCountry.continents != null) {
                            System.out.println("Continents: ");
                            for (String continents : myCountry.continents) {
                                System.out.println("    " + continents);
                            }
                        }

                        System.out.println("Flags: ");
                        System.out.println("png:   " + myCountry.flags.png);
                        System.out.println("svg:   " + myCountry.flags.svg);
                        System.out.println("alt:   " + myCountry.flags.alt);

                        System.out.println("CoatOfArms: ");
                        System.out.println("png:   " + myCountry.coatOfArms.png);
                        System.out.println("svg:   " + myCountry.coatOfArms.svg);

                        System.out.println("StartOfWeek: " + myCountry.startOfWeek);

                        System.out.println("CapitalInfo: " + myCountry.startOfWeek);
                        if (myCountry.capitalInfo.latlng != null) {
                            System.out.println("   Latlng: ");
                            for (double latlng : myCountry.capitalInfo.latlng) {
                                System.out.println("     " + latlng);
                            }
                        }

                        System.out.println("PostalCode: ");
                        System.out.println("Format:   " + myCountry.postalCode.format);
                        System.out.println("Regex:   " + myCountry.postalCode.regex);
                        System.out.println("=============================================================================");

                        countries.add(myCountry);
                    }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
