/**
 * 
 */
package brooms;

import java.lang.reflect.Field;

import brooms.actions.Insert;
import brooms.actions.Print;
import brooms.conditions.Assign;
import brooms.conditions.InstanceOf;
import brooms.nodes.Alpha;
import brooms.nodes.Entry;
import brooms.nodes.Leaf;
import brooms.session.Session;


/**
 * @author stefano
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, ClassNotFoundException {

		// When Person, Assign Field Then Insert Field

		// When String Then Print

		Class<?> type = Class.forName("brooms.Person");
		Field field = type.getDeclaredField("name");
		field.setAccessible(true);
		
		Leaf leaf1 = new Leaf.Builder().add(Print.get()).build();
		Alpha alpha1 = new Alpha.Builder(new InstanceOf.Builder(String.class).build()).add(leaf1).build();

		Leaf leaf2 = new Leaf.Builder().add(Insert.get()).build();
		Alpha alpha2 = new Alpha.Builder(new InstanceOf.Builder(type).build()).add(leaf2).build();
		Alpha alpha3 = new Alpha.Builder(new Assign.Builder("$s", field).build()).add(alpha2).build();

		Entry entry = new Entry.Builder().add(alpha1).add(alpha3).build();

		Session session = new Session.Builder(entry).build();

		session.insert("Hello, World!");
		session.insert(new Person("Pippo"));

		System.out.println("Done.");

	}

}
