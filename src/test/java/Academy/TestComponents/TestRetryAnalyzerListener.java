package Academy.TestComponents;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class TestRetryAnalyzerListener implements IAnnotationTransformer {
	
	@Override

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

	// TODO I need to tell this IAnnotationTransformer that I want to retry my test case and my Retry logic

	// is present in Retry.java class

	annotation.setRetryAnalyzer(Retry.class);

	
	
	
	}
	
}
