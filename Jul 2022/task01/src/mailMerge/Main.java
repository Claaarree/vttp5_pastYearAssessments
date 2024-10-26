package mailMerge;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        
        if (args.length < 2){
            System.out.println("Please input file names!");
            System.out.println("Usage: java -cp classes mailMerge/Main <csv file> <template file>");
            return;
        } 
        File csv = new File(args[0]);
        File template = new File(args[1]);

        
        // File csv = new File("C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Jul 2022\\task01\\testData.csv");
        // File template = new File("C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Jul 2022\\task01\\testTemplate.txt");

        FileProcessor fp = new FileProcessor(csv, template);

        fp.readCSV();
        fp.readTemplate();
        fp.mergePrintData();
    }
}
