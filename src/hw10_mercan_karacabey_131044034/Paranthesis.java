/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10_mercan_karacabey_131044034;
/**
 *Paranthesis classi kullaci tarafindan girilen "(",")" girilen
 * ifadeleri tutar,Expression Classindan turemistir
 * @author mercankaracabey
 */
public class Paranthesis extends Expression {
    private String paranthesis;
    /**
     * Constructor
     * @param paranth :gelen deger
     */
    public Paranthesis(String paranth)
    {
        paranthesis=paranth;
    }
    /**
     * getter ve
     * setter
     * @param paranth :gelen deger
     */
    //exception fırlatılacak gerekirse constructor icinde throw kullan
    public void setParanthesis(String paranth)
    {
        paranthesis=paranth;
    }

    /**
     *
     * @return :paranthesis
     */
    public String getParanthesis()
    {
        return paranthesis;
    }
    /** 
     * toString methodu
     * @return :class a ait string
     */
    public String toString()
    {
        return getParanthesis();
    }
    
}
