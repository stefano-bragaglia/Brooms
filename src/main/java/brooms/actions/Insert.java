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
public class Insert implements Action {

	private static Insert instance;

	public static Insert get() {
		if (null == instance)
			instance = new Insert();
		return instance;
	}

	private Insert() {
	}

	@Override
	public void execute(Object object, Map<String, Object> table, Session session) {

		session.insert(table.get("$s"));
//		session.insert(object);

	}

}
