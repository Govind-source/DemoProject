import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payload.CoursePrice());
	// Print number of courses created by API
	int count =	js.getInt("courses.size()");
	System.out.println(count);
			
	// Print Purchase amount
	int totalAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	
	 
	// Print title of the first course
	 String titleFirstCourse=js.get("courses[2].title");
	  System.out.println(titleFirstCourse);
	  
	// Print all courses titles and their respective prices
	  for(int i=0; i<count; i++) {
		String couresetitles =  js.get("courses["+i+"].title");
		System.out.println(js.get("courses["+i+"].price").toString());
		System.out.println(couresetitles);
	  }
		
		
		System.out.println("Print no of copies sold by RPA course");
		for(int i=0; i<count; i++) {
			String coureseTitles =  js.get("courses["+i+"].title");
			if(coureseTitles.equalsIgnoreCase("RPA")) {
				
				int copies =  js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		  
	  }
	
	}

}
