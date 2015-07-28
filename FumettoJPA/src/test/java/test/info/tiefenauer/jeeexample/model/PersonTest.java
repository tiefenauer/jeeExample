package test.info.tiefenauer.jeeexample.model;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.tiefenauer.jeeexample.model.Person;

@RunWith(Arquillian.class)
public class PersonTest {

	@Inject
	Person person;
	
	@Deployment
	public static JavaArchive createDeployment(){
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
								.addClass(Person.class)
								.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}
	
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
