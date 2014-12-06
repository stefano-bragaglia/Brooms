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
public interface Node {

	public void propagate(Object object, Map<String, Object> table, Session session);

}
