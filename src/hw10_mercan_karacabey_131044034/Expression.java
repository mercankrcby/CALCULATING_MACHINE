/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10_mercan_karacabey_131044034;

import java.lang.IllegalArgumentException;

/**
 *
 * @author mercankaracabey
 */
import java.util.*;


public class Expression {
    /**
     * gelen ifadeler bir array list icinde 
     * farkli turden objeler yaratilarak tutulur
     */
    private ArrayList<Expression> exp;
    /**
     * Constructorlar
     */
    public Expression() {
        exp = new ArrayList<>();
    }

    /**
     *
     * @param newArray :arrayList adi
     */
    public Expression(ArrayList<String> newArray) {

        exp = new ArrayList<>();
        /**
         * gelen degere baglı olarak iflerle kontrol edildi ona 
         * uygun objeler olusturuldu
         */
        for (int i = 0; i < newArray.size(); ++i) {
            if (newArray.get(i).equals("(")) {
                Paranthesis paranth = new Paranthesis("(");
                exp.add(paranth);
            } else if (newArray.get(i).equals(")")) {
                Paranthesis paranth1 = new Paranthesis(")");
                exp.add(paranth1);
            } else if (newArray.get(i).equals("+")) {
                Operator op1 = new Operator("+");
                exp.add(op1);
            } else if (newArray.get(i).equals("-")) {
                Operator op2 = new Operator("-");
                exp.add(op2);
            } else if (newArray.get(i).equals("*")) {
                Operator op3 = new Operator("*");
                exp.add(op3);
            } else if (newArray.get(i).equals("/")) {
                /**
                 * Bolumden sonra denom degeri 0 gelirse 
                 * exception firlatir
                 */
                if (newArray.get(i + 1).equals("0")) {
                    throw new IllegalArgumentException("0 a bolunemez!!!");
                }
                Operator op4 = new Operator("/");
                exp.add(op4);

            } else {
                /**
                 * once kullanici tarafindan girilen deger olan string ifade 
                 * integer a cast edilir daha sonra bu ifade dogruysa yani sayi
                 * degilse exception firlatir
                 */
                try {
                    int temp = Integer.parseInt(newArray.get(i));
                    Operand operand1 = new Operand(temp);
                    exp.add(operand1);
                } catch (NumberFormatException e) {
                    System.out.println("Hatali karakter");
                    System.exit(1);
                }
            }
        }
    }
    /**
     * genel method diyebiliriz parantezlerin bulunup aradaki islemler icin 
     * cagirilar yaparak kullanilir
     */
    public void calculate() {
        boolean done = false;

        while (!done) {
            /**
             * baslangic degerleri
             */
            int close = -1;
            int open = -1;
            int index = 0;

            // ilk kapanan parantezi bul
            while (close == -1) {
                if (index == exp.size()) {
                    close = 0;
                } else if (exp.get(index) instanceof Paranthesis) {
                    Paranthesis para = (Paranthesis) exp.get(index);
                    if (para.getParanthesis() == ")") {
                        close = index;
                    }
                }
                ++index;
            }

            index = close;
            /**
             * index degerinin 0 dan buyuk olup olmadigina bakar gelen 
             * degerin parantez olup olmadigini kontrol eder
             * ona uygun obje olusturur
             */
            if (index > 0) {
                while (open == -1) {
                    if (index < 0) {
                        open = 0;
                    } else if (exp.get(index) instanceof Paranthesis) {
                        Paranthesis para2 = (Paranthesis) exp.get(index);
                        if (para2.getParanthesis() == "(") {
                            open = index;
                        }
                    }

                    --index;

                }
            } else {
                open = 0;
            }
            /**
             * degerlere bagli olarak return mode fonksiyonu cagirilir
             */
            if (open != 0 && close != 0) {

                return_mode(open + 1, close - 1);
                exp.remove(open);
                exp.remove(open + 1);
            } else {
                done = true;
                return_mode(0, exp.size() - 1);
            }

        }
        return_mode(0, exp.size() - 1);

    }
    /**
     * Islemi yapan methoddur
     * @param start :baslangic indexi
     * @param end   :bitis indexi
     */
    public void return_mode(int start, int end) {

        int j = start;
        Operand num1 = null;
        Operand num2 = null;

        while (j <= end) {
            /**once gelen deger kontrol edilir
             * operator olup olmadigi instance ifadesiyle degerlendirilir
             */
            if (exp.get(j) instanceof Operator) {
                Operator op1 = (Operator) exp.get(j);
                //gelen deger operandsa ve "*" operatoru ise 
                if (op1.getOperator() == "*") {
                    System.out.println(this);
                    /**
                     * teker teker kontrol edilir sagindaki ve solundaki ifade
                     * kontrol edilir uygun deger gelmemisse exception firlatir
                     */
                    if (exp.get(j - 1) instanceof Operand) {
                        num1 = (Operand) exp.get(j - 1);
                    } else {
                        throw new IllegalArgumentException("Left of star not number!!!");
                    }

                    if (exp.get(j + 1) instanceof Operand) {
                        num2 = (Operand) exp.get(j + 1);
                    } else {
                        throw new IllegalArgumentException("Right of star not number!!!");
                    }
                    /**
                     * bunlara uygun olarak exp arrayine degerler giris yapilir
                     */
                    exp.set(j - 1, new Operand(num1.getOperand() * num2.getOperand()));
                    exp.remove(j);
                    exp.remove(j);
                    end -= 2;
                    --j;
                }
            }
            ++j;
            
        }

        j = start;
        while (j <= end) {
            /**
             * gelen deger operator mi diye kontrol edilir
             */
            if (exp.get(j) instanceof Operator) {
                Operator op1 = (Operator) exp.get(j);
                
                /**
                 * "/" operatoru ise islem yapar
                 */
                if (op1.getOperator() == "/") {
                    System.out.println(this);
                    /**
                     * sagindaki ve solundaki ifade kontrol edilir uygun olmayan durumda 
                     * exception firlatilir
                     */
                    if (exp.get(j - 1) instanceof Operand) {
                        num1 = (Operand) exp.get(j - 1);
                    } else {
                        throw new IllegalArgumentException("Left of divide not number!!!");
                    }

                    if (exp.get(j + 1) instanceof Operand) {
                        num2 = (Operand) exp.get(j + 1);
                    } else {
                        throw new IllegalArgumentException("Right of divide not number!!!");
                    }
                    /**
                     * uygun degerler exp array list ine set edilir
                     */
                    exp.set(j - 1, new Operand(num1.getOperand() / num2.getOperand()));
                    exp.remove(j);
                    exp.remove(j);
                    end -= 2;
                    --j;
                }

            }

            ++j;
        }

        j = start;
        while (j <= end) {
            /**
             * oncelikle gelen degerin operator olup olmadigi kontrol edilir
             */
            if (exp.get(j) instanceof Operator) {
                Operator op1 = (Operator) exp.get(j);
                /**
                 * gelen operator "+" ise islem yapar
                 */
                if (op1.getOperator() == "+") {
                    System.out.println(this);
                    /**
                     * sagina ve soluna bakilarak uygunsuz yerlerde exception firlatilir
                     */
                    if (exp.get(j - 1) instanceof Operand) {
                        num1 = (Operand) exp.get(j - 1);
                    } else {
                        throw new IllegalArgumentException("Left of plus not number!!!");
                    }

                    if (exp.get(j + 1) instanceof Operand) {
                        num2 = (Operand) exp.get(j + 1);
                    } else {
                        throw new IllegalArgumentException("Right of plus not number!!!");
                    }
                    /**
                     * uygun degerler exp array listine set edilir
                     */
                    exp.set(j - 1, new Operand(num1.getOperand() + num2.getOperand()));
                    exp.remove(j);
                    exp.remove(j);
                    end -= 2;
                    --j;
                }
            }
            ++j;
        }

        j = start;
        while (j <= end) {
            /**
             * ilk islem gelen degerin operator olup olmadigini kontrol etmektir
             */
            if (exp.get(j) instanceof Operator) {
                Operator op1 = (Operator) exp.get(j);
                /**
                 * gelen operator "-" ise islem yapmaya baslar
                 */
                if (op1.getOperator() == "-") {
                    System.out.println(this);
                    /**
                     * sagini ve solunu kontrol ederek uygunsuz yerlerde exception firlatilir
                     */
                    if (exp.get(j - 1) instanceof Operand) {
                        num1 = (Operand) exp.get(j - 1);
                    } else {
                        throw new IllegalArgumentException("Left of minus not number!!!");
                    }

                    if (exp.get(j + 1) instanceof Operand) {
                        num2 = (Operand) exp.get(j + 1);
                    } else {
                        throw new IllegalArgumentException("Right of minus not number!!!");
                    }
                    /**
                     * uygun degerler exp array listine set edilir
                     */
                    exp.set(j - 1, new Operand(num1.getOperand() - num2.getOperand()));
                    exp.remove(j);
                    exp.remove(j);
                    end -= 2;
                    --j;
                }
            }
            ++j;
        }
        System.out.println(this);

    }
    /**
     * ")" parantezi bularak yerini return eden methoddur
     * @param start :baslangic indexi
     * @param end   :bitis indexi
     * @return :i
     */
    public int getLast(int start, int end) {

        for (int i = end - 1; i >= start; --i) {
            if (exp.get(i) instanceof Paranthesis) {
                Paranthesis para2 = (Paranthesis) exp.get(i);
                if (para2.getParanthesis() == ")") {
                    return i;
                }
            }
        }

        return -1;
    }
    /**
     * "(" parantezi bularak yerini return eden methoddur
     * @param start :baslangic indexi
     * @param end   :bitis indexi
     * @return :i
     */

    public int getFirst(int start, int end) {

        for (int i = start; i < end; ++i) {
            if (exp.get(i) instanceof Paranthesis) {
                Paranthesis para2 = (Paranthesis) exp.get(i);
                if (para2.getParanthesis() == "(") {
                    return i;
                }
            }

        }

        return -1;
    }
    /**
     * Getter
     * @param i :index
     * @return :i
     */
    public Expression getExpression(int i) {
        return exp.get(i);
    }
    /**
     * toString methodu
     * @return :temp
     */
    public String toString() {
        String temp = " ";
        for (int i = 0; i < exp.size(); ++i) {
            temp += exp.get(i);
        }
        return temp;
    }
    /**
     * equal methodu
     * @param otherObject :object adi
     * @return :i
     */
    public boolean equals(Expression otherObject) {
        for (int i = 0; i < exp.size(); ++i) {
            if (otherObject instanceof Expression && ((Expression) otherObject).getExpression(i) == this.getExpression(i)) {
                return true;
            }
        }
        return false;

    }

}
