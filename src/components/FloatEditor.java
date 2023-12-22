/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;  
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author LENOVO
 */
//extends AbstractCellEditor implements TableCellEditor
public class FloatEditor extends DefaultCellEditor
{
    int row;
    int column;
    JTable table;
  
    public FloatEditor()
    {
        super(new JTextField());
        setClickCountToStart(1);
        addCellEditorListener(new CellEditorListener() 
        {
            @Override
            public void editingStopped(ChangeEvent e) 
            {
                if(table.getValueAt(row, column).equals(""))
                    table.setValueAt(null,row, column);
                if(table.getValueAt(row, column)!=null)       
                    table.setValueAt(new DecimalFormat("##0.#", new DecimalFormatSymbols(Locale.US)).format(Float.valueOf(table.getValueAt(row, column).toString())),row,column);        
            }            

            @Override
            public void editingCanceled(ChangeEvent e) {}
        });
            
        editorComponent.addKeyListener(new java.awt.event.KeyAdapter() 
        {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) 
            {    
                if(!matches(((JTextField)editorComponent).getText()+evt.getKeyChar()))
                    evt.consume();                   
            }
        });
           
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {      
        this.column=column;
        this.row=row;
        this.table=table;
        
        if(value==null)
            ((JTextField)editorComponent).setText("");
        else
            ((JTextField)editorComponent).setText(value.toString());

        return ((JTextField)editorComponent);
    }
    
    public boolean matches(String value)
    {
        if(value.matches("^(\\d{0,3})$"))
        
            return true;
        
        if(value.matches("^(\\d{0,3}\\.\\d?)$"))
        
            return true;
        
        return false;
    }

}

