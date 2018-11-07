package java_compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import utilities.CTNInformation;
import utilities.Constants;
import utilities.FNCInformation;
import utilities.FileManager;
import utilities.IDInformation;
import utilities.SymbolInformation;
import utilities.Token;

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
		((CTNInformation) this.symbolTable.get(name)).increaseCounter();
	}

	public void addFNC(String name, FNCInformation fnc) {
		if (!this.symbolTable.containsKey(name))
			this.symbolTable.put(name, fnc);
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

	public String getType(String name) {
		if (this.symbolTable.containsKey(name))
			return this.symbolTable.get(name).getType();
		return "Undefined";
	}

	public void fixCTE(Token t) {
		Long iAux = Long.parseLong(t.getLex()) + 1;// +1
		if (((CTNInformation) this.symbolTable.get(iAux.toString())).getCounter() > 1) {
			((CTNInformation) this.symbolTable.get(iAux.toString())).decreaseCounter();
			CTNInformation ctni = new CTNInformation();
			ctni.setType(Constants.L_INT);
			iAux = iAux - 1;
			this.symbolTable.put(iAux.toString(), ctni);
		}
	}

	public void setType(String symbol, String type) {
		SymbolInformation info = symbolTable.get(symbol);
		if (info != null) {
			info.setType(type);
		}
	}

	public void removeID(String lex) {
		this.symbolTable.remove(lex);
	}

	public boolean isFunction(String name) {
		if (this.symbolTable.containsKey(name))
			return this.symbolTable.get(name).isFunction();
		return false;
	}

	public boolean isVar(String name) {
		if (this.symbolTable.containsKey(name))
			return this.symbolTable.get(name).isVar();
		return false;
	}

	public boolean paramAllow(String functionName, int allow) {
		switch (allow) {
		case Constants.FUNC_ALLOW_PASS:
			return ((FNCInformation) this.symbolTable.get(functionName)).isPass()
					|| ((FNCInformation) this.symbolTable.get(functionName)).isReadonly();
		case Constants.FUNC_ALLOW_WRITE:
			return ((FNCInformation) this.symbolTable.get(functionName)).isWrite()
					|| ((FNCInformation) this.symbolTable.get(functionName)).isReadonly();
		case Constants.FUNC_ALLOW_READ:
			return ((FNCInformation) this.symbolTable.get(functionName)).isReadonly();
		case Constants.FUNC_ALLOW_WRITEPASS:
			return ((FNCInformation) this.symbolTable.get(functionName)).isWritePass()
					|| ((FNCInformation) this.symbolTable.get(functionName)).isWrite()
					|| ((FNCInformation) this.symbolTable.get(functionName)).isPass()
					|| ((FNCInformation) this.symbolTable.get(functionName)).isReadonly();

		}
		return false;
	}

	public String getParamType(String name) {
		if (this.isFunction(name))
			return ((FNCInformation) this.symbolTable.get(name)).getParamType();
		return "Undefined";
	}

}
