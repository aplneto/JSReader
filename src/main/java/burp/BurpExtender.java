package burp;

import java.util.List;

public class BurpExtender implements IBurpExtender, IScannerCheck
{
    IBurpExtenderCallbacks callbacks;
    IExtensionHelpers helpers;

    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();

        this.callbacks.setExtensionName("JavaScript Reader");
    }
    
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue){
        return 0;
    }
    

    public List<IScanIssue> doActiveScan(IHttpRequestResponse basRequestResponse,
                                        IScannerInsertionPoint insertionPoint)
    {
        return null;
    }

    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse)
    {
        return null;
    }
    
}
