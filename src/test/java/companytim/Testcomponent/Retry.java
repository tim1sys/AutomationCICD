package companytim.Testcomponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count=0;
	int maxTry= 2;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}
// used this method to retry flaky methods that might fail due to some reasons or so 
	
	
}
