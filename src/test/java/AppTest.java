import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.CommandLine;

import io.javalin.Javalin;

public class AppTest 
{
    Javalin app = JavalinSingleton.getInstance();

    /**
     * Starts the server on port 9001, and pauses thread for 3 seconds to let it spin up.
     * we use port 9001 so that the test and application can both run simultaneously (they could not be able to run on the same port)
     * @throws InterruptedException
     */
    @Before
    public void beforeEach() throws InterruptedException{
        app.start(9001);
        //wait 3 seconds so the server starts up
        Thread.sleep(3000);
    }

    /**
     * Stops the server.
     */
    @After
    public void afterEach(){
        app.stop();
    }

    /**
     * This test will use curl to send a GET request to the Javalin server looking for the "Hello, World" response.
     * Curl is a command that can be used in the terminal to send HTTP requests.
     * we use port 9001 so that the test and application can both run simultaneously (they could not be able to run on the same port)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String expectedResult = "Hello World";

        String actualResult = CommandLine.executeCommandPrompt("curl http://localhost:9001/hello");


        if(actualResult.isEmpty()){
            Assert.fail("No response from server");
        }

        Assert.assertEquals(expectedResult, actualResult);
    }
}
