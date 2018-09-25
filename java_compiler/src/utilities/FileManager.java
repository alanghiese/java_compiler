package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	private File sourceCode;
	private BufferedReader rd;

	public FileManager(String path) {

		sourceCode = new File(path);
		rd = null;
		try {
			// Open the file for reading.
			rd = new BufferedReader(new FileReader(sourceCode));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public StringBuilder getLine() {
		String code = null;
		try {
			code = rd.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (code == null)
			code = "$";
		return new StringBuilder(code);
	}

	public static void writeFile(String path, ArrayList<String> code) {
		try {
			File file = new File(path);
			// if the file not exists
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			for (String line : code) {
				fw.write(line + '\n');
			}
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
