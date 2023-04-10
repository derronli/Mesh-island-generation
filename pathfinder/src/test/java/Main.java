import LectureTestSuite.TestFactory;
import LectureTestSuite.TestReport;
import LectureTestSuite.tags.Test;
import Tests.BaseCaseTest;
import Tests.InductiveCaseTest;
import Tests.NegativeEdgeTest;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("# Derron - A4 Tests");

        TestReport report = new TestReport();
        TestFactory scanner = new TestFactory();
        List<Class> tests = new ArrayList<>();

        addTests(tests);

        for (Class test : tests) {
            scanner.build(test).run(report);
        }

        System.out.println(report);
    }
    private static void addTests(List<Class> tests) {
        tests.add(BaseCaseTest.class);
        tests.add(InductiveCaseTest.class);
        tests.add(NegativeEdgeTest.class);
    }
}
