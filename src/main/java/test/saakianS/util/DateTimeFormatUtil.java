package test.saakianS.util;

import lombok.experimental.UtilityClass;
import test.saakianS.service.TimeUnits;

@UtilityClass
public class DateTimeFormatUtil {
    public String minutesToPrettyString(int minutes) {
        return minutes / TimeUnits.MINUTES_PER_HOUR.getTimeUnits() + " hours "
                + minutes % TimeUnits.MINUTES_PER_HOUR.getTimeUnits() + " min.";
    }
}
