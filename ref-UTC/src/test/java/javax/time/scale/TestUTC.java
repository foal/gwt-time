package javax.time.scale;

import javax.time.Instant;
import javax.time.scale.TimeScale;
import static javax.time.scale.TestScale.*;

/**
 * Created by IntelliJ IDEA.
 * User: mthornton
 * Date: 31-Dec-2008
 * Time: 16:59:13
 * To change this template use File | Settings | File Templates.
 */
public class TestUTC {
    public static void main(String[] args) {
        long epochSeconds = date(2008, 12, 31)+time(23,59,59)+33;
        TimeScale[] scales = {TAI.SCALE, UTC.SCALE, UTC_NoEpochLeaps.SCALE, UTC_NoLeaps.SCALE};
        for (int i=0; i<13; i++) {
            AbstractInstant tai = TAI.SCALE.instant(epochSeconds + i / 4, (i % 4) * 250000000);
            Instant t = Instant.instant(tai);
            System.out.println();
            System.out.println("t="+t);
            for (TimeScale ts: scales) {
                AbstractInstant tsi = ts.instant(t);
                System.out.print("Scale="+ts.getName()+": "+ tsi.toString() +"; "+tsi.getEpochSeconds()+"s + "+tsi.getNanoOfSecond()+"ns");
                if (tsi.getEpochSeconds() != tsi.getSimpleEpochSeconds()) {
                    System.out.print(", includedLeaps="+(tsi.getEpochSeconds()-tsi.getSimpleEpochSeconds()));
                }
                if (tsi.getLeapSecond() != 0) {
                    System.out.print(", leap="+tsi.getLeapSecond());
                }
                System.out.println();
            }
        }
    }
}
