package test;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import rpc.NameTest;

public class NameTestTestCase extends TestCase {
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Override
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testFullName() throws IOException, ServletException {
		when(request.getParameter("fn")).thenReturn("Josh");
		when(request.getParameter("ln")).thenReturn("Foer");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		when(response.getWriter()).thenReturn(pw);
		
		NameTest servlet = new NameTest();
		servlet.doGet(request, response);
		String result = sw.getBuffer().toString().trim();
		
		String expectedResult = "Full Name: Josh Foer1";
		assertEquals(result, expectedResult);
	}
}
