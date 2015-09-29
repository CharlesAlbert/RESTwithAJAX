/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author sabre
 */
public class QuoteNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>QuoteNotFoundException</code> without
     * detail message.
     */
    public QuoteNotFoundException() {
    }

    /**
     * Constructs an instance of <code>QuoteNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public QuoteNotFoundException(String msg) {
        super(msg);
    }
}
