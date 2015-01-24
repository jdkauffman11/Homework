import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractListModel;


public class DataModel extends AbstractListModel{
	ArrayList<String> list;
	
	public DataModel() throws IOException
	{
		list = new ArrayList<String>();
		
		FileReader f;
		try {
			f = new FileReader("companies.txt");
			BufferedReader read = new BufferedReader(f);
		
		String s = read.readLine();
		while(s != null)
		{
			list.add(s);
			s = read.readLine();
			
		}
		
		read.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int getSize() {
		if(list != null)
		{
			return list.size();
		}
		
		return 0;
	}

	@Override
	public Object getElementAt(int index) {
	
		return list.get(index);
	}
	
	public void AddToList(String name)
	{
		list.add(name);
		try {
			
			File file = new File("companies.txt");
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fileWriter = new FileWriter(file, true);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    bufferedWriter.write("\n" + name);
		    bufferedWriter.close();
			
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RemoveFromList(int index)
	{
		list.remove(index);
		try {
			
			File file = new File("companies.txt");
			
			FileWriter fileWriter = new FileWriter(file);

		    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		    for(int i = 0; i < list.size(); i++)
		    {
		    	bufferedWriter.write(list.get(i));
		    	if(i != (list.size() - 1))
		    	{
		    		bufferedWriter.write("\n");
		   		}
		    }
		    
		    bufferedWriter.close();
			
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

