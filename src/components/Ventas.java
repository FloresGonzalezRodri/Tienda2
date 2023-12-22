/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import clases.AlmacenVector;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Ventas extends JTable
{
    public int idColumn;
    public int precioventaColumn;
    public int cantidadColumn;   
    public int subtotalColumn;
   
    public void init(int subtotalColumn)
    {
        if(getColumnCount()>3)
        {
            idColumn = 0;
            this.subtotalColumn = subtotalColumn;
            cantidadColumn = subtotalColumn-1;
            precioventaColumn = subtotalColumn-2;
        }

        dataModel.addTableModelListener((TableModelEvent e) ->
        {
            if(e.getType()==TableModelEvent.UPDATE && e.getColumn()==cantidadColumn)
                calcularSubtotal(e.getFirstRow());
        });
    }
    
    public void añadirProducto(Vector dataRow)
    {               
        dataRow.set(cantidadColumn, 1);
        ((DefaultTableModel)getModel()).addRow(dataRow);
        
    }
    
    public void aumentarCantidad(int row)
    {
        setValueAt((Short)getValueAt(row, cantidadColumn)+1, row, cantidadColumn);
    }
    
    public void calcularSubtotal(int row)
    {
        System.out.println("hola");
        short cantidad=Short.valueOf(getValueAt(row, cantidadColumn).toString());
        float precioVenta=Float.valueOf(getValueAt(row, precioventaColumn).toString());
        setValueAt(cantidad*precioVenta, row, subtotalColumn);   
    }  
    
    public float calcularTotal(AlmacenVector almacen)
    {
        float total=0;       
        for(int row=0;row<getRowCount();row++)
        {
            total+=(Float)getValueAt(row, subtotalColumn);
            almacen.restarDeStock((Short)getValueAt(row, cantidadColumn), (Integer)getValueAt(row, idColumn)); 
        }
        return total;
    }
    
    public int getRowOf(Object id)
    {
        for(int row=0;row<getRowCount();row++)
            if(id.equals(getValueAt(row,idColumn)))
                return row;
        return -1;
    }
}
