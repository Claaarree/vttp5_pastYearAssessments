package mailMerge;

import java.io.*;

public class FileProcessor {
    private File csvfile;   
    private File textfile; 
    Data firstName;
    Data lastName;
    Data address;
    Data years; 
    Data template;
    
    public FileProcessor(File csvfile, File textfile) {
        this.csvfile = csvfile;
        this.textfile = textfile;
    }

    public void readCSV(){
        try {
            FileReader fr = new FileReader(csvfile);
            BufferedReader br = new BufferedReader(fr);
            //Processing data names i.e first line of csv
            String heading = br.readLine();
            String[] headingarr = heading.split(",");
            this.firstName = new Data(headingarr[0]);
            this.lastName = new Data(headingarr[1]);
            this.address = new Data(headingarr[2]);
            this.years = new Data(headingarr[3]);

            String line;
            while ((line = br.readLine()) != null){
                String[] linearr = line.split(",");
                //System.out.println(linearr[0]);
                this.firstName.addToList(linearr[0]);
                this.lastName.addToList(linearr[1]);
                this.address.addToList(linearr[2]);
                this.years.addToList(linearr[3]);

            }

            // Data data = null;
            // for (int i = 0; i < linearr.length; i++){
            //     data = new Data(linearr[i]);
            // }
            // //Reading actual data
            // while (line != null){
            //     switch (data.getName()) {
            //         case "first_name":
            //             data.addToList(linearr[0]);
            //             break;
            //         case "last_name":
            //             data.addToList(linearr[1]);
                    
            //             break; 
            //         case "address":
            //             data.addToList(linearr[2]);
                    
            //             break; 
            //         case "years":
            //             data.addToList(linearr[3]);
                    
            //             break;
            //         default:
            //             break;
            //     }
            // }
            br.close();
            fr.close();   
            
        } catch (IOException e) {
            System.out.println("Error in reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void readTemplate(){
        try {
            FileReader fr = new FileReader(textfile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            template = new Data("template");
            while ((line = br.readLine()) != null){
                //System.out.println(line);
                template.addToList(line);
            }
            //System.err.println(template.getDataRead());
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error in reading text file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void mergePrintData(){
        for (int i = 0; i < firstName.getDataRead().size(); i++){
            for (int j = 0; j < template.getDataRead().size(); j++){
                String lineToCheck = template.getDataRead().get(j);
                String replacedLine = "";
                if (lineToCheck.contains("__address__")){
                    replacedLine = lineToCheck.replace("__address__", address.getDataRead().get(i));
                    
                } else if (lineToCheck.contains("__first_name__")){
                    replacedLine = lineToCheck.replace("__first_name__", firstName.getDataRead().get(i));

                } else if (lineToCheck.contains("__years__")){
                    replacedLine = lineToCheck.replace("__years__", years.getDataRead().get(i));

                }
                if (replacedLine.contains("\\n")){
                    //System.out.println("in if");
                    replacedLine = replacedLine.replace("\\n", ",");
                    String[] addressarr = replacedLine.split(",");
                    for (int x = 0; x < addressarr.length; x++){
                        System.out.println(addressarr[x]);
                    }
                    System.out.println();

                } else System.out.println(replacedLine);
            }
            System.out.println();
        }
    }
}
