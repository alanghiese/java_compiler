package java_compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import utilities.CTNInformation;
import utilities.FileManager;
import utilities.IDInformation;
import utilities.SymbolInformation;

public class SymbolTable {

	HashMap<String, SymbolInformation> symbolTable;

	public SymbolTable() {
		this.symbolTable = new HashMap<>();
	}

	public void addID(String name, IDInformation idi) {
		if (!this.symbolTable.containsKey(name))
			this.symbolTable.put(name, idi);
	}

	public void addCTN(String name, CTNInformation ctni) {
		if (!this.symbolTable.containsKey(name))
			this.symbolTable.put(name, ctni);
		((CTNInformation)this.symbolTable.get(name)).increaseCounter();
	}

	public SymbolInformation getLexeme(String name) {
		SymbolInformation si = this.symbolTable.get(name);
		if (si == null)
			si = new SymbolInformation();
		return si;
	}
	
	public void removeCTN(String key) {
		this.symbolTable.remove(key);
	}
	
	
	public Set<String> getAll() {
		return this.symbolTable.keySet();
	}

	public void genOutput(String path) {

		StringBuilder sb = new StringBuilder();
		ArrayList<String> outLine = new ArrayList<>();
		Set<String> keys = getAll();
		Iterator<String> it = keys.iterator();

		while (it.hasNext()) {

			String key = it.next().toString();
			sb.append(key);
			sb.append(" ");
			sb.append(getLexeme(key).toString());
			outLine.add(sb.toString());
			sb.setLength(0);
		}

		FileManager.writeFile(path, outLine);
	}

}
