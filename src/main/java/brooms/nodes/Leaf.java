/**
 * 
 */
package brooms.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import brooms.core.Action;
import brooms.core.Buildable;
import brooms.core.Node;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class Leaf implements Node {

	public static class Builder implements Buildable<Leaf> {

		private List<Action> actions;

		public Builder() {
			this.actions = new ArrayList<>();
		}

		@Override
		public Leaf build() {
			return new Leaf(this);
		}

		public Builder add(Action action) {
			if (null == action)
				throw new IllegalArgumentException("Illegal 'action' argument in Leaf.Builder.add(Action): " + action);
			this.actions.add(action);
			return this;
		}

		public Builder clear() {
			this.actions.clear();
			return this;
		}

		public Builder remove(Action action) {
			if (null == action)
				throw new IllegalArgumentException("Illegal 'action' argument in Leaf.Builder.remove(Action): " + action);
			this.actions.remove(action);
			return this;
		}

	}

	private final Action[] actions;

	private Leaf(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in Leaf(Leaf.Builder): " + builder);
		this.actions = builder.actions.toArray(new Action[builder.actions.size()]);
	}

	@Override
	public void propagate(Object object, Map<String, Object> table, Session session) {
		if (null == object)
			throw new IllegalArgumentException("Illegal 'fact' argument in Leaf.propagate(Object, Map<String, Object>, Session): " + object);
		if (null == table)
			throw new IllegalArgumentException("Illegal 'table' argument in Leaf.propagate(Object, Map<String, Object>, Session): " + table);
		if (null == session)
			throw new IllegalArgumentException("Illegal 'session' argument in Leaf.propagate(Object, Map<String, Object>, Session): " + session);
		for (Action action : actions)
			action.execute(object, table, session);
	}

}
