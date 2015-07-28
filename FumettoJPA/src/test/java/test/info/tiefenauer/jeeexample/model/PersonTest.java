package test.info.tiefenauer.jeeexample.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import info.tiefenauer.jeeexample.model.Person;

public class PersonTest {

	private Person person;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDefaults(){
		person = new Person();
		assertNull(person.getId());
		assertEquals(0, person.getVersion());
		assertNull(person.getFirstName());
		assertNull(person.getLastName());
		assertNull(person.getStreet());
		assertEquals(0,  person.getZip());
		assertNull(person.getCity());
		assertNull(person.getCreated());
		assertNull(person.getUpdated());
	}

}
