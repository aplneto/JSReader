package burp;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
// import br.com.tempest.JSReader;

public class BurpExtender implements IBurpExtender, IHttpListener//, IScannerCheck
{
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    PrintWriter stdout;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        this.callbacks = callbacks;

        this.callbacks.setExtensionName("JavaScript Reader");
        this.callbacks.registerHttpListener(this);
        // this.callbacks.registerScannerCheck(this);

        this.helpers = callbacks.getHelpers();

        stdout = new PrintWriter(this.callbacks.getStdout(), true);
    }

    @Override
    public void processHttpMessage( int toolFlag,
    boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
        if (!messageIsRequest)
        {
            byte[] response = messageInfo.getResponse();
            IResponseInfo responseInfo = helpers.analyzeResponse(response);
            int bodyOffset = responseInfo.getBodyOffset();
            byte[] responseBody = Arrays.copyOfRange(response, bodyOffset, response.length);
            String body = new String(responseBody);
            stdout.println(body);
        }
    }
    
    // public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue){
    //     return 0;
    // }
    

    // public List<IScanIssue> doActiveScan(IHttpRequestResponse basRequestResponse,
    //                                     IScannerInsertionPoint insertionPoint)
    // {
    //     return null;
    // }

    // public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse)
    // {
    //     String response = baseRequestResponse.getResponse().toString();
    //     List<String> matches;
    //     matches = JSReader.lookForMatches(response, JSReader.uriPattern);
    //     return null;
    // }
    
}
