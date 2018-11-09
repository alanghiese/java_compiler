package utilities;

import java_compiler.Parser;

public class IDInformation extends SymbolInformation {

	String scope=Parser.GLOBAL_SCOPE;
	
	@Override
	public String toString() {
		return "ID - " + super.getType() + " alcance: " + this.getScope();
	}
        
        @Override
        public boolean isVar() {
            return !(super.type.equals(SymbolInformation._DEFAULT_TYPE));
        }
        
        public void setScope(String scope) {
        	this.scope=scope;
        }
        
        public String getScope() {
        	return this.scope;
        }
	
}
