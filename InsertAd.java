import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class InsertAd {

    public String solution(String play_time, String adv_time, String[] logs) {
        int[] timeline    = new int[360000];
        final int advSec  = Times.asSeconds(adv_time);
        final int playSec = Times.asSeconds(play_time);

        Arrays.stream(logs)
                .map(Times::new)
                .sorted(Comparator.comparing(lhs -> lhs.start))
                .forEach(t-> IntStream.range(t.start, t.end).forEach(i-> timeline[i]++));

        AtomicLong viewers = new AtomicLong();
        IntStream.range(0, advSec).forEach(viewers::addAndGet);

        AtomicLong maxViewers = new AtomicLong(viewers.get());
        AtomicInteger maxStart = new AtomicInteger();
        IntStream.range(advSec, playSec).forEach(i-> {
            long currentViewers = viewers.addAndGet(timeline[i] - timeline[i - advSec]);
            if(currentViewers <= maxViewers.get())
                return;

            maxViewers.set(currentViewers);
            maxStart.set(i - advSec + 1);
        });

        int start = maxStart.get();
        return String.format("%02d:%02d:%02d", start/3600, start/60%60, start%60);
    }

    private static class Times {
        int start;
        int end;

        Times(String time) {
            String[] startAndEnd = time.split("-");
            start = asSeconds(startAndEnd[0]);
            end   = asSeconds(startAndEnd[1]);
        }

        static int asSeconds (String time) {
            String[] t = time.split(":");
            return Integer.parseInt(t[0])*3600 +
                   Integer.parseInt(t[1])*60 +
                   Integer.parseInt(t[2]);
        }
    }

}
