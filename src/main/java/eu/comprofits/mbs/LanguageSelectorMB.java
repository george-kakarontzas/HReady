/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.comprofits.mbs;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author george
 */
@ManagedBean(name = "languageSelector")
@SessionScoped
public class LanguageSelectorMB implements Serializable {
    private static final Map<String, Object> languages;
    private String locale;
    static {
        languages = new LinkedHashMap<String, Object>();
        languages.put("English", Locale.ENGLISH); 
        languages.put("Español", new Locale("es"));
    }
    public Map<String, Object> getCountriesInMap() {
        return languages;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    //value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : languages.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());

            }
        }

    }
}
