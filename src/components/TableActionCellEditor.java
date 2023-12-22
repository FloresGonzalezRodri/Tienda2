package components;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RAVEN
 */
public class TableActionCellEditor extends DefaultCellEditor 
{   
    public TableActionCellEditor() 
    {
        super(new JCheckBox());

    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) 
    {
        if(row==jtable.getRowCount()-1)
            return null;
        PanelAction action = new PanelAction();
        action.initEvent((AbstractTableModel)jtable.getModel(),row);
        action.setBackground(jtable.getSelectionBackground());
        
        return action;
    }
}
