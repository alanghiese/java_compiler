package semantic_actions;

import java_compiler.SymbolTable;
import utilities.Token;

public interface SemanticAction {
	public void execute(StringBuilder buffer, StringBuilder line, Token token, SymbolTable st);
}
