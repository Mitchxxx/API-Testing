package beta;
import org.apache.logging.log4j.*;




public class Demo1 {
	
	private static Logger log = LogManager.getLogger(Demo1.class.getName());
	
	public static void main(String[] args) {
		// To do Auto-generated method
		
		log.debug("I have clicked on button");
		log.info("Button is displayed");
		//if (5>4)
		log.info("Object is present");
		log.error("Button is not displayed");
		
		
		log.fatal("Button is missing");
		
	}
		

}
