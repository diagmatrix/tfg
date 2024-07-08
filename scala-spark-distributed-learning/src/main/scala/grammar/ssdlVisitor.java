// Generated from ssdl.g4 by ANTLR 4.13.1
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ssdlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ssdlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ssdlParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ssdlParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ssdlParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(ssdlParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ssdlParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(ssdlParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ssdlParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(ssdlParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ssdlParser#ann_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnn_argument(ssdlParser.Ann_argumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ssdlParser#dapso_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDapso_argument(ssdlParser.Dapso_argumentContext ctx);
}