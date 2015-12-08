
package tester;

import facades.CallableFacade;



/**
 *
 * @author AlexanderNielsen
 */
public class mainTest {

    
    public static void main(String[] args) throws Exception {
        CallableFacade cf = new CallableFacade("", "CPH", "SXF", "2016-01-01T00:00:00.000Z", "2");
        
        cf.runThreads();
        
    }
}
