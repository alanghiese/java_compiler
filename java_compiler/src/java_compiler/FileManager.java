package java_compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	
	
	public ArrayList<String> loadAndGetSourceCode(String path) {
		ArrayList<String> code = new ArrayList<>();
		File sourceCode = new File(path);
		BufferedReader rd = null;
		try {
			// Open the file for reading.
			rd = new BufferedReader(new FileReader(sourceCode));		             	
			// Read all contents of the file.
			String inputLine = null;
		
			while((inputLine = rd.readLine()) != null) {
				code.add(inputLine);
			}
		}
		catch(IOException ex) {
			System.err.println(Constants.ERR_UPS);
			ex.printStackTrace();
		}
		finally {
			// Close the file.
			try {
				rd.close();
			}
			catch (IOException ex) {
				System.err.println(Constants.ERR_UPS);
				ex.printStackTrace();
			}
		}			
		return code;
	}
		
	public void writeFile(String path, ArrayList<String> code) {
		try {
			File file = new File(path);
			// if the file not exists
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			for(String line: code) {
				fw.write(line + '\n');
			}
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
