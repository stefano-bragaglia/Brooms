/**
 *
 */
package brooms.session;

import java.util.HashMap;

import brooms.core.Buildable;
import brooms.nodes.Entry;

/**
 * @author stefano
 *
 */
public class Session {

	public static class Builder implements Buildable<Session> {

		private Entry entry;

		public Builder(Entry entry) {
			if (null == entry)
				throw new IllegalArgumentException("Illegal 'entry' argument in Session.Builder(Entry): " + entry);
			this.entry = entry;
		}

		@Override
		public Session build() {
			return new Session(this);
		}

		public Builder set(Entry entry) {
			if (null == entry)
				throw new IllegalArgumentException("Illegal 'entry' argument in Session.Builder.set(Entry): " + entry);
			this.entry = entry;
			return this;
		}

	}

	private final Entry entry;

	private Session(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in Session(Session.Builder): " + builder);
		this.entry = builder.entry;
	}

	public void insert(Object object) {
		if (null == object)
			throw new IllegalArgumentException("Illegal 'object' argument in Session.insert(Object): " + object);
		entry.propagate(object, new HashMap<String, Object>(), this);
	}

}
