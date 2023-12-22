 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Vector;

/**
 *
 * @author LENOVO
 */
public class AlmacenVector extends Vector<Vector>
{
    public int idColumn;
    public int stockColumn;        

    public AlmacenVector(Vector<Vector> dataVector)
    {
        super(dataVector);
        idColumn=0;  
    }
    
    public void setStockColumn(int stockColumn)
    {
        this.stockColumn = stockColumn;
    }
    
    public boolean hayStock(int cantidad, int row)
    {
        System.out.println(get(row).get(stockColumn));
        return cantidad<Integer.valueOf(get(row).get(stockColumn).toString());
    }

    public void restarDeStock(int cantidad,int row)
    {
        get(row).set(stockColumn, (Short)get(row).get(stockColumn)-cantidad);
    }
    
    public int getRowOf(Object id)
    {
        for(int row=0;row<size();row++)
            if(id.equals(get(row).get(idColumn)))
                return row;
        return -1;
    }
    
    public Object getValueAt(int row, int column)
    {
        return get(row).get(column);
    }
}
