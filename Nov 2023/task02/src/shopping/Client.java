package shopping;

import java.net.Socket;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        int port = 0;
        String serverName = "localhost";
        if (args.length == 0) {
            port = 3000;
        } else if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2) {
            serverName = args[0];
            port = Integer.parseInt(args[1]);
        }

        Socket clientconn = new Socket(serverName, port);

        InputStream is = clientconn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        
        String line;
        String requestID = "";
        int item_count = 0;
        int budget = 0;
        List <Integer> productID = new ArrayList<>();
        List <String> title = new ArrayList<>();
        List<Integer> price = new ArrayList<>();
        List <Integer> rating = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            //System.out.println("after line");
            if (line.contains(":")) {
                String[] dataRead = line.split(":");
                //System.out.println(dataRead[0]);
                String dataToAdd = dataRead[1].trim();
                switch (dataRead[0]) {
                    case "request_id":
                    requestID = dataToAdd;
                    break;
                    
                    case "item_count":
                        item_count = Integer.parseInt(dataToAdd);
                        break;
            
                    case "budget":
                        budget = Integer.parseInt(dataToAdd);
                        break;
            
                    case "prod_id":
                        productID.add(Integer.parseInt(dataToAdd));
                        break;
            
                    case "title":
                        title.add(dataToAdd);
                        break;
            
                    case "price":
                        price.add(Integer.parseInt(dataToAdd));
                        break;
            
                    case "rating":
                        rating.add(Integer.parseInt(dataToAdd));
                        //System.out.println(rating);
                        break;
            
                    default:
                    System.out.println("in default");
                        break;
                    
                }

            }
            if (rating.size() == item_count && line.equals("prod_end")){
                break;
            }
        }

        //System.out.println(rating);
        
        ArrayList<Product> pdtReceived = new ArrayList<>();
        for (int i = 0; i < item_count; i++) {
            Product pdt = new Product(productID.get(i), title.get(i), price.get(i), rating.get(i));
            pdtReceived.add(pdt);
        }

        //System.out.println("before comparing");
        Compare comparator = new Compare();
        pdtReceived.sort(comparator);
        //System.out.println(pdtReceived);
        ArrayList<Product> chosenProducts = new ArrayList<>();
        for (Product p : pdtReceived) {
            if (p.getPrice() <= budget) {
                chosenProducts.add(p);
                budget -= p.getPrice();
            }
        }

        Console console = System.console();
        String name = console.readLine("Please enter your name as per nric: ");
        String email = console.readLine("Please enter your email address: ");
                
        OutputStream os = clientconn.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        

        bw.write("request_id: " + requestID + "\n");
        bw.write("name: " + name + "\n");
        bw.write("email: " + email + "\n");
        String items = "";
        int spent = 0;
        String remaining = String.valueOf(budget);
        for (Product pro : chosenProducts) {
            items += "," + String.valueOf(pro.getProdID());
            spent += pro.getPrice();
        }
        items = items.replaceFirst(",", "");
        
        bw.write("items: " + items + "\n");
        bw.write("spent: " + spent + "\n");
        bw.write("remaining: " + remaining + "\n");
        bw.write("client_end\n");
        bw.flush();

        br.readLine();
        String statusMessage = br.readLine();
        System.out.println(statusMessage);
    }
    
}
