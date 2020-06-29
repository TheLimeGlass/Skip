package me.limeglass.skip.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;

@Name("Excluding List")
@Description("Returns a list without the defiend objects.")
@Examples("set {_list::*} to diamond, gold ingot and redstone without the diamond")
public class ExprExclude extends SimpleExpression<Object> {

	static {
		Skript.registerExpression(ExprExclude.class, Object.class, ExpressionType.SIMPLE, "%objects% (excluding|but not|[but] without) [(the|a)] %objects%");
	}

	private Expression<?> list, remove;

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		list = LiteralUtils.defendExpression(exprs[0]);
		remove = LiteralUtils.defendExpression(exprs[1]);
		return LiteralUtils.canInitSafely(list, remove);
	}

	@Override
	@Nullable
	protected Object[] get(Event e) {
		list.change(e, remove.getArray(e), ChangeMode.REMOVE_ALL);
		return list.getArray(e);
	}

	@Override
	public Class<? extends Object> getReturnType() {
		return list.getReturnType();
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "list excluding " + list.toString(e, debug);
	}

}
