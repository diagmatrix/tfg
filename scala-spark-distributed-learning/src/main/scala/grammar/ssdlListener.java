// Generated from ssdl.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ssdlParser}.
 */
public interface ssdlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ssdlParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ssdlParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ssdlParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ssdlParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(ssdlParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(ssdlParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ssdlParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(ssdlParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(ssdlParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ssdlParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(ssdlParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(ssdlParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ssdlParser#ann_argument}.
	 * @param ctx the parse tree
	 */
	void enterAnn_argument(ssdlParser.Ann_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#ann_argument}.
	 * @param ctx the parse tree
	 */
	void exitAnn_argument(ssdlParser.Ann_argumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ssdlParser#dapso_argument}.
	 * @param ctx the parse tree
	 */
	void enterDapso_argument(ssdlParser.Dapso_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ssdlParser#dapso_argument}.
	 * @param ctx the parse tree
	 */
	void exitDapso_argument(ssdlParser.Dapso_argumentContext ctx);
}