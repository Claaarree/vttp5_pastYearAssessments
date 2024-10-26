package mailMerge;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<String> dataRead;
    private String name;

    public Data(String name) {
        this.name = name;
        dataRead = new ArrayList<>();
    }

    public List<String> getDataRead() {
        return dataRead;
    }

    public String getName() {
        return name;
    }

    public void addToList(String words){
        dataRead.add(words);
    }
    
    
}
