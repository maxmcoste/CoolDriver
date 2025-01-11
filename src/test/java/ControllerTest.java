import org.junit.Before;
import org.junit.Test;
import org.maxmcold.errors.RuleSyntaxError;
import org.mockito.Mockito;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import static org.mockito.Mockito.*;
import org.maxmcold.*;
import org.maxmcold.models.Area;
import org.maxmcold.models.Sensor;
import org.maxmcold.rules.Rule;
import org.maxmcold.rules.RuleFactory;
import org.maxmcold.utils.CoolProperties;
import static org.mockito.ArgumentMatchers.contains;


public class ControllerTest {

    private Controller controller;
    private CoolProperties coolPropertiesMock;
    private Area areaMock;
    private Sensor sensorMock;
    private RuleFactory ruleFactoryMock;
    private Rule ruleMock;
    private Logger loggerMock;

    @Before
    public void setUp() throws Exception {
        // Mock CoolProperties
        coolPropertiesMock = mock(CoolProperties.class);
        CoolProperties.loadProperties(); // load properties in the static block

        // Mock other dependencies
        areaMock = mock(Area.class);
        sensorMock = mock(Sensor.class);
        ruleFactoryMock = mock(RuleFactory.class);
        ruleMock = mock(Rule.class);
        loggerMock = mock(Logger.class);

        // Create a new instance of Controller, which will use these mocks
        controller = new Controller();

        // Mock necessary behaviors
        when(coolPropertiesMock.getProperties()).thenReturn(mock(Properties.class));
        when(coolPropertiesMock.delay).thenReturn("10"); // Set default delay for testing

        // Mock area, sensor, and rule behaviors
        when(areaMock.getName()).thenReturn("Salotto");
        when(areaMock.getSensors()).thenReturn(mock(HashMap.class));

        when(ruleFactoryMock.getRule()).thenReturn(ruleMock);
    }

    @Test
    public void testExecuteRunsSuccessfully() {
        // Test the execute method to ensure it schedules the tasks correctly
        controller.execute();

        // Verify that the scheduler was triggered
        verify(loggerMock).debug("Starting controller execution...");
    }

    @Test
    public void testRunFetchesSensorData() {
        // Mock a sample sensor and its readable values
        HashMap<String, Object> sensorValues = new HashMap<>();
        sensorValues.put("temperature", 22.5);
        when(sensorMock.getReadable().getValues()).thenReturn(sensorValues);

        // Test the run method directly
        controller.run();

        // Verify that the sensor data was fetched and logged
        verify(loggerMock).debug(contains("getting sensor list for area Salotto"));
        verify(loggerMock).debug(contains("beep"));
    }

    @Test
    public void testRuleExecution() {
        // Test that rules are executed when the controller runs
        controller.run();

        // Verify that the rule's executeAll method was called
        try {
            verify(ruleMock).executeAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RuleSyntaxError e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testErrorHandling() {
        // Simulate an exception in the run method
        doThrow(new RuntimeException("Test exception")).when(areaMock).getSensors();

        // Run the method and ensure the exception is logged
        controller.run();

        // Verify that the error was logged
        verify(loggerMock).error(contains("Test exception"));
    }
}