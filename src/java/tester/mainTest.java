
package tester;

import facades.CallableFacade;
import facades.FlightFacade;
import java.util.List;



/**
 *
 * @author AlexanderNielsen
 */
public class mainTest {

    
    public static void main(String[] args) throws Exception {
        FlightFacade f = new FlightFacade();
        List<String> a = f.getUrls();
        CallableFacade cf = new CallableFacade(a ,"", "CPH", "SXF", "2016-01-01T00:00:00.000Z", "2");
        cf.runThreads();
        
    }
}
