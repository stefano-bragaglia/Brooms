/**
 * 
 */
package brooms.nodes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import brooms.core.Buildable;
import brooms.core.Node;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class Entry implements Node {

	public static class Builder implements Buildable<Entry> {

		private Set<Node> registry;

		public Builder() {
			this.registry = new HashSet<>();
		}

		public Builder add(Node node) {
			if (null == node)
				throw new IllegalArgumentException("Illegal 'gate' argument in Entry.Builder.add(Node): " + node);
			this.registry.add(node);
			return this;
		}

		@Override
		public Entry build() {
			return new Entry(this);
		}

		public Builder clear() {
			this.registry.clear();
			return this;
		}

		public Builder remove(Node node) {
			if (null == node)
				throw new IllegalArgumentException("Illegal 'gate' argument in Entry.Builder.remove(Node): " + node);
			this.registry.remove(node);
			return this;
		}

	}

	private final Node[] registry;

	private Entry(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in Entry(Entry.Builder): " + builder);
		this.registry = builder.registry.toArray(new Node[builder.registry.size()]);
	}

	@Override
	public synchronized void propagate(Object object, Map<String, Object> table, Session session) {
		for (Node node : registry)
			node.propagate(object, table, session);
	}

}
