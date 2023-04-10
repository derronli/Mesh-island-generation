package LectureTestSuite;

public class Assertions {
    public static void assertTrue(boolean condition) {
        if (!condition)
            throw new AssertionError();
    }

    public static void assertFalse(boolean condition) {
        assertTrue(!condition);
    }

    public static void assertEquals(Object left, Object right) {
        assertTrue(left.equals(right));
    }

    public static void assertNotEquals(Object left, Object right) {
        assertFalse(left.equals(right));
    }
}
