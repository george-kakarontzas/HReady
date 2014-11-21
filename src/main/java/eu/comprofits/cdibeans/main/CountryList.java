/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.cdibeans.main;

import java.io.Serializable;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 *
 * @author ckopanos
 */
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
 
public class CountryList {
    
    private List<Country> countries;
    
    public CountryList(Locale locale) {
        //
        // A collection to store our country object
        //
        countries = new ArrayList<Country>();
 
        //
        // Get ISO countries, create Country object and
        // store in the collection.
        //
        String[] isoCountries = Locale.getISOCountries();
        for (String country : isoCountries) {
            Locale baselocale = new Locale("en", country);
            String code = baselocale.getCountry();
            String name = baselocale.getDisplayCountry(locale);
 
            if (!"".equals(code)
                    && !"".equals(name)) {
                countries.add(new Country(code, name));
            }
        }
 
        //
        // Sort the country by their name and then display the content
        // of countries collection object.
        //
        Collections.sort(countries, new CountryComparator());
 
    }

    public List<Country> getCountries() {
        return countries;
    }
    
    
 

    static class Country {
        private String code;
        private String name;
 
        Country(String code, String name) {
            this.code = code;
            this.name = name;
        }



        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
 
        
    }
 
    /**
     * CountryComparator class.
     */
    static class CountryComparator implements Comparator<Country> {
        private Comparator comparator;
 
        CountryComparator() {
            comparator = Collator.getInstance();
        }
 
        @SuppressWarnings("unchecked")
        public int compare(Country c1, Country c2) {
            return comparator.compare(c1.name, c2.name);
        }
    }
}