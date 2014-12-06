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
public class PrintPar implements Action {

	private static Map<String, PrintPar> table;

	public static PrintPar get(String name) {
		if (null == table)
			table = new HashMap<>();
		PrintPar result = table.get(name);
		if (null == result) {
			result = new PrintPar(name);
			table.put(name, result);
		}
		return result;
	}

	private final String name;

	private PrintPar(String name) {
		this.name = name;
	}

	@Override
	public void execute(Object object, Map<String, Object> table, Session session) {
		System.out.print(table.get(name));
	}

}
