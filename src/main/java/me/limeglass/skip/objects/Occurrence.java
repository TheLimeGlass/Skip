package me.limeglass.skip.objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class Occurrence<T> {

	protected final Collection<T> collection;

	public Occurrence(Collection<T> collection) {
		this.collection = collection;
	}

	public Occurrence<T> filter(Predicate<T> filter) {
		collection.removeIf(filter);
		return this;
	}

	public Entry<T, Integer> getMostOccurring() {
		Map<T, Integer> map = new HashMap<>();
		for (T value : collection) {
			int amount = Optional.ofNullable(map.get(value)).orElse(0);
			map.put(value, amount + 1);
		}
		Entry<T, Integer> max = null;
		for (Entry<T, Integer> entry : map.entrySet()) {
			if (max == null || entry.getValue() > max.getValue())
				max = entry;
		}
		return max;
	}

}
