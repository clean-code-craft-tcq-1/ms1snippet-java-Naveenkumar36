package sensorval;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SensorValidator {

    private static final double MAX_SOC_VALUE = 0.05;
    private static final double MAX_CURRENT_VALUE = 0.1;
    private static final Function<List<Double>, Boolean> inValidInput = Validator::isListOrContentsEmpty;
    private static BiFunction<List<Double>, Double, Boolean> validateReadings = SensorValidator::validateReadings;

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
        return !inValidInput.apply(values) && validateReadings.apply(values, MAX_SOC_VALUE);
    }

    public static boolean validateCurrentReadings(List<Double> values) {
        return !inValidInput.apply(values) && validateReadings.apply(values, MAX_CURRENT_VALUE);
    }
}
