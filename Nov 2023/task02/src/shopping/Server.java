package shopping;

import java.net.*;
import java.util.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 0;
        if (args.length == 0) {
            port = 3000;
        } else if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket server = new ServerSocket(port);
        
        //ArrayList<Product> productList = Product.getProductList();
        int itemCount = Product.getItemcount();
        //System.out.println(itemCount);
        int budget = 20;
        ArrayList<Product> genProductList = Product.getGenProductList(itemCount);
        //System.out.println(genProductList.get(1));

        int count = 0;
        while (true) {
            Socket clientconn = server.accept();
            count++;
            String request_id = "abc" + count;
            System.out.println("Got a client connection!");
            
            OutputStream os = clientconn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            bw.write("request_id: " + request_id + "\n");
            bw.write("item_count: " + itemCount + "\n");
            bw.write("budget: " + budget + "\n");
            bw.write("prod_list\n");
            for (Product p : genProductList){
                bw.write("prod_start\n");
                bw.write("prod_id: " + p.getProdID() + "\n");
                bw.write("title: " + p.getTitle() + "\n");
                bw.write("price: " + p.getPrice() + "\n");
                bw.write("rating: " + p.getRating() + "\n");
                bw.write("prod_end\n\n");
            }
            bw.flush();
            osw.flush();
            os.flush();
            
            
            InputStream is = clientconn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                if (line.equals("client_end")) {
                    break;
                }
            }
            bw.write("success\n");
            bw.flush();
            osw.flush();
            os.flush();

            clientconn.close();
            break;
        }
        server.close();
        
        
       

    }
    
}
