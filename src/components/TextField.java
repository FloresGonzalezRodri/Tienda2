/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.awt.event.KeyAdapter;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class TextField extends JTextField
{
    public TextField()
    {
        addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {
                String value=getText()+evt.getKeyChar();
                if(value.matches("^(\\d{0,3})$ "))       
                    return;
                if(value.matches("^(\\d{0,3}\\.\\d?)$"))       
                    return;
                evt.consume();
            }
        });       
    }
}
