package Client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class getCountries
 */
public class getCountries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCountries() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get method of get countries called");
		Client client=ClientBuilder.newClient();
//		WebTarget wt= client.target("https://restcountries.eu/rest/v1/all");
		WebTarget wt= client.target("https://restcountries.eu/rest/v1/all");
		
		String json_as_string=wt.request(MediaType.APPLICATION_JSON).get(String.class);
		//Object []  ar=WsConverter.parseJson(json_as_string);
		
		//System.out.println("mydata=="+ar[0]);
		
		//request.setAttribute("mydata", ar);
		
		
		
		response.setContentType("text/plain");
	    PrintWriter out=response.getWriter();
	    out.print(json_as_string);
	    out.flush();
	    out.close();
	    
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
