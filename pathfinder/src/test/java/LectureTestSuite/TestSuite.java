package LectureTestSuite;

import java.util.HashSet;
import java.util.Set;

public class TestSuite implements Test {
    private final Set<Test> contents = new HashSet<>();
    @Override
    public final void run(TestReport collector) {
        for(Test t: contents) {
            t.run(collector);
        }
    }

    public final void add(Test t) {
        this.contents.add(t);
    }

}
