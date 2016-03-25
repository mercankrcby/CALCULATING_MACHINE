/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10_mercan_karacabey_131044034;

import java.util.Scanner;
import java.util.ArrayList;


/**
 *
 * @author mercankaracabey
 */
public class HW10_MERCAN_KARACABEY_131044034 {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here

        /**
         * Kullanicidan alinan veriler icin array list tutuldu
         */
        ArrayList<String> input = new ArrayList<>();

        System.out.println("Enter your expression, after each operator or operand press enter, to end the expression press =");
        int i = 1;
        Scanner scan = new Scanner(System.in);
        String temp = scan.nextLine();

        while (!"=".equals(temp)) {
            input.add(temp);
            System.out.println("Enter your expression element");
            temp = scan.nextLine();
        }
        //yanlis girilen degerler icin exception firlatildi
        try {
            Expression a = new Expression(input);
            a.calculate();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
