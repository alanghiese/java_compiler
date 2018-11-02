package utilities;

public class IDInformation extends SymbolInformation {

	@Override
	public String toString() {
		return "ID";
	}
        
        @Override
        public boolean isVar() {
            return !(super.type.equals(SymbolInformation._DEFAULT_TYPE));
        }
	
}
