package csci.proj.lcs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Surabhi
 */
public class FileIO {

	private int DEBUG = 0;
	private int RUN = 1;
    private String filename;
    private String formatter;
    private String data;
    private String dir = System.getProperty("user.dir") + "\\src\\csci\\proj\\lcs\\";
	private int mode = 0;
    private StringBuilder tempData;
    
    public void init(String filename, String... headers)
    {
    	this.filename = filename;
    	tempData = new StringBuilder();
    	if (mode == DEBUG)
    		System.out.println("FILENAME: "+this.filename);
    	StringBuilder sb = new StringBuilder();
        for(int i=0;i<headers.length;i++)
        {
            sb.append("%20s");
        }
        formatter = sb.append("%n").toString();
        
        tempData.append( String.format(formatter, headers) );
        if(mode == DEBUG)
        	System.out.format(formatter, headers);
        
    }
    
    public boolean save()
    {
    	data = tempData.toString();
        try(  PrintWriter out = new PrintWriter(dir + filename)  ){
           out.print( data );
           return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void write(String... values){
    	tempData.append(String.format(formatter, values));
    	if (mode == DEBUG)
    		System.out.format(formatter, values);
    }
 
}
