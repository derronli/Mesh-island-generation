package LectureTestSuite;

public class TestResult {
    private final String testName;
    private STATUS status;

    public TestResult(String name) {
        this.testName = name;
    }

    public void record(STATUS status){
        this.status = status;
    }

    public STATUS status() {
        return this.status;
    }

    @Override
    public String toString() {
        return "# " + status + " " + testName;
    }
}
