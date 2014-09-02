package eu.comprofits.cdibeans;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
 
@ManagedBean
public class MultiSelectView {
     
    private List<SelectItem> categories;   
    private String selection;
 
    @PostConstruct
    public void init() {
        categories = new ArrayList<SelectItem>();
        SelectItemGroup group1 = new SelectItemGroup("Managerial Competences");
        SelectItemGroup group2 = new SelectItemGroup("Business orientation");
        SelectItemGroup group3 = new SelectItemGroup("Job related skills");
        SelectItemGroup group4 = new SelectItemGroup("Oral and written communication / languages");
         
        SelectItem option11 = new SelectItem("The ability of leading the way", "The ability of leading the way");
        SelectItem option12 = new SelectItem("The ability of creating involvement", "The ability of creating involvement");
        SelectItem option13 = new SelectItem("Teambuilding", "Teambuilding");
        SelectItem option14 = new SelectItem("Flexibility", "Flexibility");
        
        SelectItem option21 = new SelectItem("Business acumen", "Business acumen");
        SelectItem option22 = new SelectItem("Market orientation", "Market orientation");
        SelectItem option23 = new SelectItem("Customer Orientation", "Customer Orientation");
        SelectItem option24 = new SelectItem("Financial Awareness", "Financial Awareness");
         
        SelectItem option31 = new SelectItem("Commitment to results", "Commitment to results");
        SelectItem option32 = new SelectItem("Technical Knowledge", "Technical Knowledge");
        SelectItem option33 = new SelectItem("Training and development", "Training and development");
        SelectItem option34 = new SelectItem("Technology", "Technology");
         
        SelectItem option41 = new SelectItem("Communication and service", "Communication and service");
        SelectItem option42 = new SelectItem("Oral communication", "Oral communication");
        SelectItem option43 = new SelectItem("Written communication", "Written communication");
        SelectItem option44 = new SelectItem("Culture, tact and diplomacy", "Culture, tact and diplomacy");
         
        group1.setSelectItems(new SelectItem[]{option11, option12, option13, option14});
        group2.setSelectItems(new SelectItem[]{option21, option22, option23, option24});
        group3.setSelectItems(new SelectItem[]{option31, option32, option33, option34});
        group4.setSelectItems(new SelectItem[]{option41, option42, option43, option44});
         
        categories.add(group1);
        categories.add(group2);
        categories.add(group3);
        categories.add(group4);
    }
     
    public List<SelectItem> getCategories() {
        return categories;
    }   
 
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }
}