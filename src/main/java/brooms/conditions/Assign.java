/**
 * 
 */
package brooms.conditions;

import java.lang.reflect.Field;
import java.util.Map;

import brooms.core.Buildable;
import brooms.core.Condition;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class Assign implements Condition {

	public static class Builder implements Buildable<Assign> {

		private Field field;

		private String name;

		public Builder(String name, Field field) {
			if (null == name || (name = name.trim()).isEmpty())
				throw new IllegalArgumentException("Illegal 'name' argument in Assign.Builder.set(String, Field): " + name);
			if (null == field)
				throw new IllegalArgumentException("Illegal 'field' argument in Assign.Builder.set(String, Field): " + field);
			this.field = field;
			this.name = name;
		}

		public Builder set(String name) {
			if (null == name || (name = name.trim()).isEmpty())
				throw new IllegalArgumentException("Illegal 'name' argument in Assign.Builder.set(String): " + name);
			this.name = name;
			return this;
		}

		public Builder set(Field field) {
			if (null == field)
				throw new IllegalArgumentException("Illegal 'field' argument in Assign.Builder.set(String, Field): " + field);
			this.field = field;
			return this;
		}

		@Override
		public Assign build() {
			return new Assign(this);
		}

	}

	private final Field field;

	private final String name;

	private Assign(Builder builder) {
		if (null == builder)
			throw new IllegalArgumentException("Illegal 'builder' argument in Assign(Assign.Builder): " + builder);
		this.field = builder.field;
		this.field.setAccessible(true);
		this.name = builder.name;
	}

	@Override
	public boolean check(Object object, Map<String, Object> table, Session session) {
		// TODO Auto-generated method stub

		try {
			Object result = field.get(object);
			table.put(name, result);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
