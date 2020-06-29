package me.limeglass.skip.elements.expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;

@Name("Reversed List")
@Description("Reverses the given list.")
@Examples("set {_list::*} to reversed {_list::*}")
public class ExprReversedList extends SimpleExpression<Object> {

	static {
		Skript.registerExpression(ExprReversedList.class, Object.class, ExpressionType.SIMPLE, "(reverse[(d| of)]|flip[ped]|(invertmirror|)[(ed| of)]) %objects/string%");
	}

	private Expression<?> list;
	private boolean string;

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		list = LiteralUtils.defendExpression(exprs[0]);
		if (list.isSingle() || list.getReturnType().equals(String.class))
			string = true;
		return LiteralUtils.canInitSafely(list);
	}

	@Override
	@Nullable
	protected Object[] get(Event event) {
		if (string)
			return new String[] {new StringBuilder(((String)list.getSingle(event))).reverse().toString()};
		Object[] origin = list.getArray(event);
		List<Object> reversed = Arrays.asList(origin.clone());
		Collections.reverse(reversed);
		return reversed.toArray();
	}

	@Override
	public Class<? extends Object> getReturnType() {
		return list.getReturnType();
	}

	@Override
	public boolean isSingle() {
		return string;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "reversed " + list.toString(e, debug);
	}

}
