/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 
 * @author FLORES GONZALEZ RODRIGO
 */
public class FileCollection
{    
    private String fileName;    
    
    public FileCollection(String fileName)
    {
        this.fileName=fileName;
    }
    
    public void loadData(int columnCount,Vector<Vector> dataVector) 
    {
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileName))) 
        {   
            while(true)
            {   
                Vector rowVector=new Vector(); 
                for(int column=0;column<columnCount;column++)
                    rowVector.add(ois.readObject());
                dataVector.add(rowVector);
            }
                            
        }        
        catch(EOFException ex){}
        catch(ClassNotFoundException | IOException ex)
        {
            Logger.getLogger(FileCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveData(Vector<Vector> dataVector)
    {
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(fileName));)    
        {
         
            for(Vector dataRow:dataVector)
                for(Object data:dataRow)                   
                    oos.writeObject(data);
            
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo " + fileName, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}