package org.example.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

    int maxCount = 2;
    int retryCount = 0;
    @Override
    public boolean retry(ITestResult result) {
        if(retryCount<maxCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
