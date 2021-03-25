package sensorval;

import java.util.List;

public class SensorValidator {

    private static final double MAX_SOC_VALUE = 0.05;
    private static final double MAX_CURRENT_VALUE = 0.1;

    private SensorValidator() {
    }

    private static boolean differenceInIndexValueGreaterThanMaxThreshold(
          double value,
          double nextValue,
          double maxDelta
    )
    {
        return (nextValue - value > maxDelta);
    }

    private static boolean validateReadings(
          List<Double> values,
          double maxDeltaValue
    )
    {
        int lastButOneIndex = values.size() - 1;
        for (int i = 0; i < lastButOneIndex; i++) {
            if (differenceInIndexValueGreaterThanMaxThreshold(values.get(i), values.get(i + 1), maxDeltaValue)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateSOCReadings(List<Double> values) {
        throwExceptionForInvalidInput(values);
        return validateReadings(values, MAX_SOC_VALUE);
    }

    public static boolean validateCurrentReadings(List<Double> values) {
        throwExceptionForInvalidInput(values);
        return validateReadings(values, MAX_CURRENT_VALUE);
    }

    private static void throwExceptionForInvalidInput(List<Double> values) {
        if (Validator.isListOrContentsEmpty(values)) {
            throw new IllegalArgumentException("Input list or it's contents cannot be null");
        }
    }
}
