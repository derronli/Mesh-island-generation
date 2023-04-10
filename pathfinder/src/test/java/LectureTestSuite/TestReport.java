package LectureTestSuite;

import java.util.*;
import java.util.stream.Collectors;

public class TestReport {
    private final List<TestResult> results = new ArrayList<>();
    private final Map<STATUS, Integer> counter = new HashMap<>();

    public void collect(TestResult result) {
        this.results.add(result);
        Integer count = counter.getOrDefault(result.status(),0) + 1;
        this.counter.put(result.status(), count);
    }

    @Override
    public String toString() {
        String details = results.stream()
                .map(TestResult::toString)
                .collect(Collectors.joining("\n"));
        return details + "\nSummary: " + counter.toString();
    }

}
