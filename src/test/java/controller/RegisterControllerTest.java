package controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controllers.RegisterController;
import utils.Enduro;

public class RegisterControllerTest {
	
	private RegisterController regCont;

	@Before
	public void setUp() throws Exception {
		regCont = new RegisterController(Enduro
				.getInstance().getResourcePath(
						"registrationOutputTest.txt"));
	}

	@Test
	public void testFormatResults() {
		String respons = regCont.formatResults("","2","12.13.14");
		assertEquals(respons, "2;12.13.14\n");
	}
	
	@Test
	public void testWeirdFormatsWithWhiteSpaces() {
		String respons = regCont.formatResults("","   2   ","12.13.14");
		assertEquals(respons, "2;12.13.14\n");
	}
	
	@Test
	public void testMassRegistrationWithWeirdFormats() {
		String respons = regCont.formatResults(""," 1,  2 ,    3,   4     , 5    ","12.13.14");
		assertEquals(respons, 
				"1;12.13.14\n" +
				"2;12.13.14\n" +
				"3;12.13.14\n" +
				"4;12.13.14\n" +
				"5;12.13.14\n");
	}
}
