package packt.jee.eclipse.rest.ws.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is a simple test class for invoking REST web service using JAX-RS client APIs
 * @author sundalei
 *
 */
public class CourseManagementClient {
	
	public static void main(String[] args) {
		//testGetCourseJSON();
		//testAddCourseJSON();
		testAddCourseForm();
	}
	
	public static void testGetCourseJSON() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course");
		webTarget = webTarget.path("get").path("10");
		
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
		if(response.getStatus() != 200) {
			System.out.println("Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase());
			return;
		}
		System.out.println(response.readEntity(String.class));
	}
	
	public static void testAddCourseJSON() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course/add");
		
		String courseJSON = "{\"name\":\"Course-4\", \"credits\" : 5}";
		
		Response response = webTarget.request().post(Entity.entity(courseJSON, MediaType.APPLICATION_JSON));
		if(response.getStatus() != 200) {
			System.out.println("Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase()
					+ ", Error Code : " + response.getStatus());
			return;
		}
		System.out.println(response.readEntity(String.class));
	}
	
	public static void testAddCourseForm() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/CourseManagementREST/services/course/add");
		
		Form form = new Form();
		form.param("name", "Course-5");
		form.param("credits", "1");
		
		Response response = webTarget.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		if(response.getStatus() != 200) {
			System.out.println("Error invoking REST Web Service - " + response.getStatusInfo().getReasonPhrase()
					+ ", Error Code : " + response.getStatus());
			return;
		}
		System.out.println(response.readEntity(String.class));
	}
}
