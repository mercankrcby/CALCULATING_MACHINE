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
 * Operand classi kullanicidan girilecek olan sayilari tutar Expressiondan turemistir
 * @author mercankaracabey
 */
public class Operand extends Expression{
    
    private int operand;
    /**
     * Constructor
     * @param operandString :gelen deger
     */
    public Operand(int operandString)
    {
        operand=operandString;
    }
    /**
     * getter ve setter methodlari
     * @param operandString :gelen deger
     */
    public void setOperand(int operandString)
    {
        operand=operandString;
    }

    /**
     *
     * @return :operand
     */
    public int getOperand()
    {
        return operand;
    }
    /**
     * toString methodu
     * @return :class a ait string
     */
    public String toString()
    {
        return String.format("%d",getOperand());
    }
    
}
