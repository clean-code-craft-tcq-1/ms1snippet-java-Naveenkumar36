package sensorval;

import java.util.List;
import java.util.Objects;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class Validator {

    private Validator() {
    }

    public static <T> boolean isListOrContentsEmpty(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return list.stream().anyMatch(Objects::isNull);
        }
        return true;
    }
}
