/**
 * 
 */
package brooms;

import brooms.actions.PrintPar;
import brooms.actions.PrintStr;
import brooms.conditions.Assign;
import brooms.conditions.InstanceOf;
import brooms.core.Action;
import brooms.core.Condition;
import brooms.nodes.Alpha;
import brooms.nodes.Entry;
import brooms.nodes.Leaf;
import brooms.session.Session;

/**
 * @author stefano
 *
 */
public class MWE {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Object object = new Person("Adam");

		String par = "$s";
		Action action = PrintPar.get(par);
		Condition condition1 = new InstanceOf.Builder(Person.class).build();
		Condition condition2 = new Assign.Builder(par, Person.class.getDeclaredField("name")).build();

		Leaf leaf = new Leaf.Builder().add(PrintStr.get("Hello, ")).add(action).add(PrintStr.get("!\n")).build();
		Alpha alpha2 = new Alpha.Builder(condition2).add(leaf).build();
		Alpha alpha1 = new Alpha.Builder(condition1).add(alpha2).build();
		Entry entry = new Entry.Builder().add(alpha1).build();

		Session session = new Session.Builder(entry).build();

		session.insert(object);
		session.insert(new Integer(-5));
		session.insert(new Person("Stefano"));

		System.out.println("Done.");
	}

}
