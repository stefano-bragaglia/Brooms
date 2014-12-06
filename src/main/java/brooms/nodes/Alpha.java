/**
 * 
 */
package brooms.nodes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import brooms.core.Buildable;
import brooms.core.Condition;
import brooms.core.Node;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class Alpha implements Node {

	public static class Builder implements Buildable<Alpha> {

		private Condition condition;
		private Set<Node> registry;

		public Builder(Condition condition) {
			if (null == condition)
				throw new IllegalArgumentException("Illegal 'condition' argument in Alpha.Builder(Condition): " + condition);
			this.condition = condition;
			this.registry = new HashSet<>();
		}

		public Builder add(Node node) {
			if (null == node)
				throw new IllegalArgumentException("Illegal 'gate' argument in Alpha.Builder.add(Node): " + node);
			this.registry.add(node);
			return this;
		}

		@Override
		public Alpha build() {
			return new Alpha(this);
		}

		public Builder clear() {
			this.registry.clear();
			return this;
		}

		public Builder remove(Node node) {
			if (null == node)
				throw new IllegalArgumentException("Illegal 'gate' argument in Alpha.Builder.remove(Node): " + node);
			this.registry.remove(node);
			return this;
		}

		public Builder set(Condition condition) {
			if (null == condition)
				throw new IllegalArgumentException("Illegal 'condition' argument in Alpha.Builder.set(Condition): " + condition);
			this.condition = condition;
			return this;
		}

	}

	private final Condition condition;

	private final Node[] registry;

	private Alpha(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in Alpha(Alpha.Builder): " + builder);
		this.condition = builder.condition;
		this.registry = builder.registry.toArray(new Node[builder.registry.size()]);
	}

	@Override
	public synchronized void propagate(Object object, Map<String, Object> table, Session session) {
		if (null == object)
			throw new IllegalArgumentException("Illegal 'object' argument in Alpha.propagate(Object, Map<String, Object>, Session): " + object);
		if (null == table)
			throw new IllegalArgumentException("Illegal 'table' argument in Alpha.propagate(Object, Map<String, Object>, Session): " + table);
		if (null == session)
			throw new IllegalArgumentException("Illegal 'session' argument in Alpha.propagate(Object, Map<String, Object>, Session): " + session);
		for (Node node : registry)
			if (condition.check(object, table, session))
				node.propagate(object, table, session);
	}

}
