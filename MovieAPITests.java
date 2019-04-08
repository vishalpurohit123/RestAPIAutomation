package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tmdb.movie.test.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetDetailsTest.class,
        GetPopularTest.class,
})
public class MovieAPITests {	

}
