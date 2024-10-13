package calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome.");
        Scanner scanner = new Scanner (System.in);
        Double $last = 0.0;
        Double firstNumber = 0.0;
        Double secondNumber = 0.0;
     
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            
            if (line.equalsIgnoreCase("exit")) {
                scanner.close();
                break;
            }
            String[] lineArray = line.split(" ");
            //System.out.println(lineArray[1]);

            
            if (lineArray[0].equals("$last") && lineArray[2].equals("$last")) {
                firstNumber = $last;
                secondNumber = $last;                
            } else if (lineArray[2].equals("$last")){
                firstNumber = Double.parseDouble(lineArray[0]);
                secondNumber = $last;
            } else if (lineArray[0].equals("$last")) {
                firstNumber = $last;
                secondNumber = Double.parseDouble(lineArray[2]);
            } else {
                firstNumber = Double.parseDouble(lineArray[0]);
                secondNumber = Double.parseDouble(lineArray[2]);
            }           
            
            

            String operator = lineArray[1];
            Double sum = 0.0;
            switch (operator) {
                case "+":
                    sum = firstNumber + secondNumber;

                    break;
                
                case "-":
                    sum = firstNumber - secondNumber;

                    break;

                case "*":
                case "x":
                    sum = firstNumber * secondNumber;

                    break;

                case "/":
                    sum = firstNumber / secondNumber;

                    break;

                default:
                    System.out.println("Please enter a valid equation");
                    break;
            }
            if (sum % 1== 0){
                System.out.println(sum.intValue());
            } else System.out.println(sum);
            $last = sum;
        }
        System.out.println("Bye bye");
        
    }
}