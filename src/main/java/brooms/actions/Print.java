/**
 * 
 */
package brooms.actions;

import java.util.Map;

import brooms.core.Action;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class Print implements Action {

	private static Print instance;

	public static Print get() {
		if (null == instance)
			instance = new Print();
		return instance;
	}

	private Print() {
	}

	@Override
	public void execute(Object object, Map<String, Object> table, Session session) {
		System.out.print(object);
	}

}
