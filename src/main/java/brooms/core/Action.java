/**
 * 
 */
package brooms.core;

import java.util.Map;

import brooms.session.Session;

/**
 * @author stefano
 *
 */
public interface Action {

	/**
	 * @param object
	 * @param table
	 * @param session
	 */
	public void execute(Object object, Map<String, Object> table, Session session);

}
