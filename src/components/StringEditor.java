/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author LENOVO
 */
public class StringEditor extends DefaultCellEditor
{ 
    public StringEditor() 
    {
        super(new JTextField());
        setClickCountToStart(1);
        editorComponent.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {    
                if(((JTextComponent)editorComponent).getText().length()>15)
                    evt.consume();                   
            }
        });
        
        
    }    
}
