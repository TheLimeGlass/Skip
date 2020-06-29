package me.limeglass.skip.objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MappableOccurrence<F, T> {

	protected final Collection<F> collection;

	public MappableOccurrence(Collection<F> collection) {
		this.collection = collection;
	}

	public MappableOccurrence<F, T> filter(Predicate<F> filter) {
		collection.removeIf(filter);
		return this;
	}

	public Entry<T, Integer> getMostOccurring(Function<F, T> function) {
		Map<T, Integer> map = new HashMap<>();
		for (F value : collection) {
			T transformed = function.apply(value);
			int amount = Optional.ofNullable(map.get(transformed)).orElse(0);
			map.put(transformed, amount + 1);
		}
		Entry<T, Integer> max = null;
		for (Entry<T, Integer> entry : map.entrySet()) {
			if (max == null || entry.getValue() > max.getValue())
				max = entry;
		}
		return max;
	}

}
