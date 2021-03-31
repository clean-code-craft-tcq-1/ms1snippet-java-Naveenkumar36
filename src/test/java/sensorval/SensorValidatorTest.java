package sensorval;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class SensorValidatorTest {

    @Test
    public void reportsErrorWhenSOCJumps()
    {
        Double[] readings = {0.0, 0.01, 0.5, 0.51};
        List<Double> socs = Arrays.asList(readings);
        assertFalse(SensorValidator.validateSOCReadings(socs));
    }

    @Test
    public void reportsErrorWhenCurrentJumps()
    {
        Double[] readings = {0.03, 0.03, 0.03, 0.33};
        List<Double> currents = Arrays.asList(readings);
        assertFalse(SensorValidator.validateCurrentReadings(currents));
    }

    @Test
    public void throwsExceptionForNullInput() {
        assertFalse(SensorValidator.validateCurrentReadings(null));
    }

    @Test
    public void throwsExceptionForEmptyInput() {
        assertFalse(SensorValidator.validateCurrentReadings(Collections.EMPTY_LIST));
    }

    @Test
    public void throwsExceptionForNullInputContents() {
        Double[] readings = {0.03, null, 0.03, 0.33};
        List<Double> currents = Arrays.asList(readings);
        assertFalse(SensorValidator.validateCurrentReadings(currents));
    }
}
