/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import clases.FileCollection;
import clases.TableEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author LENOVO
 */
public class Table extends JTable
{       
    public void init(String fileName)
    {               
        File New_File = new File(fileName);
        try 
        {
            New_File.createNewFile();
        }catch (IOException ex) 
        {
           Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }
        new FileCollection(fileName).loadData(getColumnCount(),getDefaultModel().getDataVector());
        
        getColumnModel().getColumn(getColumnCount()-1).setCellEditor(new TableActionCellEditor());
        getColumnModel().getColumn(getColumnCount()-1).setCellRenderer(new TableActionCellRender());
        
        //System.out.println("table row count: " + getColumnCount());
        rowHeight=40;        
        
        dataModel.addTableModelListener((TableModelEvent e) ->
        {
            
            if(e.getType()==TableModelEvent.UPDATE && e.getFirstRow()==getRowCount()-1)
            {
                for(int column=0;column<getColumnCount()-1;column++)
                    if(getValueAt(e.getFirstRow(),column)==null)
                        return;
                ((AbstractTableModel) e.getSource()).fireTableChanged(new TableModelEvent((TableModel) e.getSource(),e.getFirstRow(),e.getFirstRow(),TableModelEvent.ALL_COLUMNS,TableEvent.LAST_ROW_IS_FULLFILLED));                
            }
            
        });
        
        getColumnModel().getColumn(getColumnCount()-1).setCellRenderer(new TableActionCellRender());
        getColumnModel().getColumn(getColumnCount()-1).setCellEditor(new TableActionCellEditor());
        
        if(getRowCount()==0)
            getDefaultModel().addRow(new Object[]{null,null,null,"g",null,0,null});
    }

    public DefaultTableModel getDefaultModel() 
    {
        return (DefaultTableModel) dataModel;
    }
}    
 
    
