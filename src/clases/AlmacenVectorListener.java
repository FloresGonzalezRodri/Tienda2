/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author LENOVO
 */
public class AlmacenVectorListener extends AlmacenVector implements TableModelListener
{      
    Object[] defaultValues;
    
    public AlmacenVectorListener(Vector<Vector> dataVector, Object[] defaultValues)
    {
        super(dataVector);
        this.defaultValues=defaultValues;
    }
    
    @Override
    public void tableChanged(TableModelEvent e)
    {               
        if(e.getType()==TableModelEvent.UPDATE)
        {
            Object dato=((TableModel)e.getSource()).getValueAt(e.getFirstRow(), e.getColumn());
            get(e.getFirstRow()).set(e.getColumn(),dato);  
        }
              
        else if(e.getType()==TableEvent.LAST_ROW_IS_FULLFILLED)  
        {
             add(convertToVector(defaultValues)); 
             System.out.println(this.size());
        }            
        
        else if(e.getType()==TableEvent.CONFIRMED_DELETE) 
        {
            int row=Integer.valueOf(((TableModel)e.getSource()).getValueAt(e.getFirstRow(), idColumn).toString());
            remove(row);
        }             
    }
    
    protected static Vector<Object> convertToVector(Object[] anArray)
    {
        if (anArray == null) 
            return null;
        
        Vector<Object> v = new Vector<>(anArray.length);
        for (Object o : anArray) 
            v.addElement(o);        
        return v;
    }
}
