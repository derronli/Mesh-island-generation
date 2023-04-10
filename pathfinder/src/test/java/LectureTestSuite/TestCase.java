package LectureTestSuite;

import LectureTestSuite.tags.After;
import LectureTestSuite.tags.Before;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public final class TestCase implements Test {
    private final Method testMethod;
    private final Class klass;

    public TestCase(Class klass, Method testMethod) {
        this.testMethod = testMethod;
        this.klass = klass;
    }

    public final void run(TestReport collector) {
        String title = klass.getCanonicalName() + "::" + testMethod.getName();
        TestResult result = new TestResult(title);
        Object context = null;
        try {
            context =  klass.getDeclaredConstructor().newInstance();
            runSetUp(context);
            runTest(context);
            result.record(STATUS.PASSED);
        } catch(InvocationTargetException ite) {
            try {
                throw ite.getTargetException();
            } catch (AssertionError ae) {
                result.record(STATUS.FAILED);
            } catch(Throwable e) {
                result.record(STATUS.ERRORED);
            }
        } catch (Exception e) {
            result.record(STATUS.ERRORED);
        } finally {
            try {
                runTearDown(context);
            } catch (Exception e) {
                result.record(STATUS.ERRORED);
            }
        }
        collector.collect(result);
    }

    private void runSetUp(Object context)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for(Method candidate: klass.getMethods()) {
            if (candidate.isAnnotationPresent(Before.class)) {
                candidate.invoke(context);
            }
        }
    }

    private void runTearDown(Object context)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for(Method candidate: klass.getMethods()) {
            if (candidate.isAnnotationPresent(After.class)) {
                candidate.invoke(context);
            }
        }
    }

    private void runTest(Object context)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.testMethod.invoke(context);

    }
}
