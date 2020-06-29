package me.limeglass.skip.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.limeglass.skip.lang.sections.OptionalSection;

public class EffEndSection extends Effect {

	static {
		Skript.registerEffect(EffEndSection.class, "$ end skip section");
	}

	@Override
	protected void execute(Event event) {}

	@Override
	public String toString(Event event, boolean b) {
		return "$ end skip section";
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		OptionalSection.removeCurrentSection();
		return true;
	}

}
