package org.mdissjava.mdisscore.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mdissjava.mdisscore.model.dao.hibernate.HibernateUtil;
import org.mdissjava.mdisscore.model.dao.impl.UserDaoImpl;
import org.mdissjava.mdisscore.model.pojo.Address;
import org.mdissjava.mdisscore.model.pojo.Configuration;
import org.mdissjava.mdisscore.model.pojo.User;
import org.mdissjava.mdisscore.model.pojo.User.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDaoImplTest {

	private Session session ;
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before
	public void setUp() throws Exception {
		HibernateUtil.openSessionFactory();
		session = HibernateUtil.getSession();
	}

	@Test
	public void addUserTest(){		
		this.logger.info("TEST(UserDao) addUser");
	
		Address address = new Address();		
		address.setStreet("Madariaga 6");		
		address.setCity("Bilbao");
		address.setState("Vizcaya");
		address.setCountry("Spain");
		
		Configuration conf = new Configuration();
										
		User user = new User();
		user.setNick("jess");
		user.setActive(true);
		user.setName("Jessica");		
		user.setSurname("Smith");
		user.setPhone(944655877);
		user.setBirthdate(new Date());
		user.setGender(Gender.Female);
		user.setAddress(address);
		user.setConfiguration(conf);		
		user.addPreference("nature");
		user.addPreference("horses");
		user.addPreference("sunsets");
		user.setEmail("prueba@prueba.com");
		user.setPass("prueba");

		UserDao dao = new UserDaoImpl();
		dao.addUser(user);				
		assertEquals(user, session.get(User.class, user.getId()));				
	}
	
	@Test
	public void deleteUserTest(){

		
		this.logger.info("TEST(UserDao) deleteUser");
		
		Address address = new Address();		
		address.setStreet("Madariaga 6");		
		address.setCity("Bilbao");
		address.setState("Vizcaya");
		address.setCountry("Spain");
		
		Configuration conf = new Configuration();
										
		User user = new User();
		user.setNick("Prueba");
		user.setName("Prueba");		
		user.setSurname("Smith");
		user.setPhone(944655877);
		user.setBirthdate(new Date());
		user.setGender(Gender.Female);
		user.setAddress(address);
		user.setConfiguration(conf);		
		user.addPreference("nature");
		user.addPreference("horses");
		user.addPreference("sunsets");
		user.setEmail("prueba@prueba.com");
		user.setPass("prueba");
		
		UserDao dao = new UserDaoImpl();
		
		dao.addUser(user);
		assertEquals(user, session.get(User.class, user.getId()));				

		dao.deleteUser(user);				
		assertNull(session.get(User.class, user.getId()));		
		
		
	}

	@Test
	public void getByIdTest(){
		
		this.logger.info("TEST(UserDao) getUserByID");

		Address address = new Address();		
		address.setStreet("Avda Universidades");		
		address.setCity("Bilbao");
		address.setState("Vizcaya");
		address.setCountry("Spain");
		
		Configuration conf = new Configuration();
										
		User user = new User();
		user.setNick("MDISS");
		user.setName("Java");		
		user.setSurname("Master");
		user.setPhone(944655877);
		user.setBirthdate(new Date());
		user.setGender(Gender.Female);
		user.setAddress(address);
		user.setConfiguration(conf);		
		user.addPreference("java");
		user.addPreference("programming");
		user.addPreference("pojos");
		user.setEmail("prueba@prueba.com");
		user.setPass("mdiss");
		
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
				
		assertEquals(user,  dao.getUserById(user.getId()));
		
		
	}

	@Test
	public void updateUserTest(){
		
		this.logger.info("TEST(UserDao) updateUser");
		
		Address address = new Address();		
		address.setStreet("Madariaga 64");		
		address.setCity("Bilbao");
		address.setState("Vizcaya");
		address.setCountry("Spain");
		
		Configuration conf = new Configuration();
		
		User user = new User();
		user.setNick("jessAgain");
		user.setActive(true);
		user.setName("JessicaAgain");		
		user.setSurname("SmithAgain");
		user.setPhone(944655877);
		user.setBirthdate(new Date());
		user.setGender(Gender.Female);
		user.setAddress(address);
		user.setConfiguration(conf);		
		user.addPreference("nature");
		user.addPreference("horses");
		user.addPreference("sunsets");
		user.setEmail("pruebaUpdate@prueba.com");
		user.setPass("prueba");
		
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
		
		user.setEmail("pruebaUpdateCorrecta@prueba.com");
		
		dao.updateUser(user);
		
		assertEquals(user, session.get(User.class, user.getId()));
		
	}
	
	@Test
	public void updateUserAdressTest(){
		this.logger.info("TEST(UserDao) addUser");
		
		Address address = new Address();		
		address.setStreet("Madariaga 6");		
		address.setCity("Bilbao");
		address.setState("Vizcaya");
		address.setCountry("Spain");
		
		Configuration conf = new Configuration();
										
		User user = new User();
		user.setNick("javi");
		user.setActive(true);
		user.setName("Javier");		
		user.setSurname("Gonzalez");
		user.setPhone(944655877);
		user.setBirthdate(new Date());
		user.setGender(Gender.Male);
		user.setAddress(address);
		user.setConfiguration(conf);		
		user.addPreference("Jamon");
		user.addPreference("Cocina");
		user.addPreference("Paisajes");
		user.setEmail("Javier@prueba.com");
		user.setPass("javi");

		UserDao dao = new UserDaoImpl();
		dao.addUser(user);				
		assertEquals(user, session.get(User.class, user.getId()));	
		
		user.getAddress().setCity("Tudela");
		user.getAddress().setCountry("España");
		user.getAddress().setState("Navarra");
		
		user.getConfiguration().setShowName(false);
		user.getConfiguration().setShowPhone(true);
		user.getConfiguration().setShowEmail(true);
		
		dao.updateUser(user);
		
		assertEquals(user, session.get(User.class, user.getId()));
	
	
	
	}

}
