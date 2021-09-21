
package karate.rest;

import com.intuit.karate.junit5.Karate;

public class RestRunner {

    @Karate.Test
    Karate runAll() {
        return Karate.run().relativeTo(getClass());
    }
}