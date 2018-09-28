package alpha;
import org.apache.logging.log4j.*;




public class Demo {
	
	private static Logger log = LogManager.getLogger(Demo.class.getName());
	
	public static void main(String[] args) {
		// To do Auto-generated method
		
		log.debug("I am debugging");
		//if (5>4)
		log.info("Object is present");
		log.error("object is present");
		
		
		log.fatal("This is fatal");
		
	}
		

}
