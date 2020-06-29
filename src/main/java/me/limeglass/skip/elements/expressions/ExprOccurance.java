package me.limeglass.skip.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;

@Name("Occurance")
@Description("Counts the amount of times an object exists in a list.")
public class ExprOccurance extends SimpleExpression<Number> {

	static {
		//Skript.registerExpression(ExprOccurance.class, Number.class, ExpressionType.SIMPLE, "occurrences of %object% (out of|from|in) %objects%");
	}

	private Expression<?> list, object;

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		object = exprs[0];
		list = LiteralUtils.defendExpression(exprs[1]);
		return LiteralUtils.canInitSafely(list);
	}

	@Override
	@Nullable
	protected Number[] get(Event event) {
		return new Number[] {HashMultiset.create(Lists.newArrayList(list.getArray(event))).count(object.getSingle(event))};
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "occurrences" + object.toString(e, debug) + " from " + list.toString(e, debug);
	}

}
