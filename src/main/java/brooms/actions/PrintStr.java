/**
 * 
 */
package brooms.actions;

import java.util.HashMap;
import java.util.Map;

import brooms.core.Action;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class PrintStr implements Action {

	private static Map<String, PrintStr> table;

	public static PrintStr get(String string) {
		if (null == table)
			table = new HashMap<>();
		PrintStr result = table.get(string);
		if (null == result) {
			result = new PrintStr(string);
			table.put(string, result);
		}
		return result;
	}

	private final String string;

	private PrintStr(String string) {
		this.string = string;
	}

	@Override
	public void execute(Object object, Map<String, Object> table, Session session) {
		System.out.print(string);
	}

}
