package karate.rest.read;

import com.intuit.karate.junit5.Karate;

public class ReadRunner {

    @Karate.Test
    Karate runRead() {
        return Karate.run().relativeTo(getClass());
    }
}
