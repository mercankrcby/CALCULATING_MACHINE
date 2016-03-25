/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10_mercan_karacabey_131044034;
/**
 *
 * @author mercankaracabey
 */
/**
 * Kullanicidan girilen +,-,*,/ gibi operatorleri alarak onlar uzerinde get
 * set gibi islemler gerceklestirir
 * @author mercankaracabey
 */
public class Operator extends Expression{
    
    private String operator;
    /**
     * Constructorlar
     * @param operat :gelen deger
     */
    public Operator(String operat)
    {
        operator=operat;
    }
    /**
     *getter ve setter methodlari
     * @param operat :gelen deger
     */
    public void setOperator(String operat)
    {
        operator=operat;
    } 

    /**
     *
     * @return :operator
     */
    public String getOperator()
    {
        return operator;
    }
    /**
     * toString methodu
     * @return :class a ait string
     */
    public String toString()
    {
        return getOperator();
    }
}
