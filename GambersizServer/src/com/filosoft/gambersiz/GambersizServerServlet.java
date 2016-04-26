package com.filosoft.gambersiz;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GambersizServerServlet extends AbstractRequestHandler {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
