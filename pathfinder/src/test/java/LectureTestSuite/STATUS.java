package LectureTestSuite;

public enum STATUS {
    PASSED('X'), FAILED(' '), ERRORED('!');

    private char symbol;

    private STATUS(char s) { this.symbol = s; }

    @Override
    public String toString() { return "[" + symbol + ']';}
}
