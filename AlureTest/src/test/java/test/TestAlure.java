package test;

import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
//import org.testng.annotations.Test;


/**
 *
 * @author asus 123
 */
public class TestAlure {

  

    @Test
    public void hello() {
       assertTrue("Result not equals to 4", 2 * 2 == 4);
    }
}
