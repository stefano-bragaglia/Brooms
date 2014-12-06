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
public interface Condition {

	public boolean check(Object object, Map<String, Object> table, Session session);

}
