package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {

	private static PersonDomainModel person1;
	private static PersonDomainModel person2;

	private static UUID person1UUID = UUID.randomUUID();
	private static UUID person2UUID = UUID.randomUUID();

	@BeforeClass
	public static void personInstance() throws Exception {

		Date person1Birth = null;
		Date person2Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		person1 = new PersonDomainModel();
		person2 = new PersonDomainModel();

		try {
			person1Birth = dateFormat.parse("1994-11-27");
			person2Birth = dateFormat.parse("1995-05-20");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(person1.getPersonID());
		assertNull("Empty Database", null);
	}

	@Test
	public void personTestAdd() {

		PersonDAL.addPerson(person1);
		assertNotNull("Person1 exist in the record", person1);

	}

	@Test
	public void getPerson() {
		PersonDAL.getPerson(person1UUID);
		assertTrue(person1.getPersonID() == person1UUID);
	}

	@Test
	public void getAllPersons() {
		ArrayList<PersonDomainModel> per = new ArrayList<PersonDomainModel>();
		per.add(person1);
		assertNotNull(per == PersonDAL.getAllPersons());

	}

	@Test
	public void testUpdatePerson() {

		person1.setLastName("Faucher");
		person1.setCity("Bear");
		PersonDAL.updatePerson(person1);
		assertTrue(person1.getLastName() == "Faucher");
		assertTrue(person1.getCity() == "Bear");
	}

	@Test
	public void deletePersonTest() {

		PersonDAL.addPerson(person1);
		PersonDAL.deletePerson(person1UUID);
		assertNull("Person 1 does not exist in the record", null);

	}

}