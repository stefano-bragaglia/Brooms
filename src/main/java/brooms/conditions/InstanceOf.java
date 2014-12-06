/**
 * 
 */
package brooms.conditions;

import java.util.Map;

import brooms.core.Buildable;
import brooms.core.Condition;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class InstanceOf implements Condition {

	public static class Builder implements Buildable<InstanceOf> {

		private Class<?> type;

		public Builder(Class<?> type) {
			if (null == type)
				throw new IllegalArgumentException("Illegal 'type' argument in InstanceOf.Builder(Class): " + type);
			this.type = type;
		}

		public Builder set(Class<?> type) {
			if (null == type)
				throw new IllegalArgumentException("Illegal 'type' argument in InstanceOf.Builder.set(Class): " + type);
			this.type = type;
			return this;
		}

		@Override
		public InstanceOf build() {
			return new InstanceOf(this);
		}

	}

	private final Class<?> type;

	private InstanceOf(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in InstanceOf(InstanceOf.Builder): " + builder);
		this.type = builder.type;
	}

	@Override
	public boolean check(Object object, Map<String, Object> table, Session session) {
		if (null == object)
			throw new IllegalArgumentException("Illegal 'object' argument in InstanceOf.check(Object, Map<String, Object>, Session): " + object);
		if (null == table)
			throw new IllegalArgumentException("Illegal 'table' argument in InstanceOf.check(Object, Map<String, Object>, Session): " + table);
		if (null == session)
			throw new IllegalArgumentException("Illegal 'session' argument in InstanceOf.check(Object, Map<String, Object>, Session): " + session);
		return type.isInstance(object);
	}

}
