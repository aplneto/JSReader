package burp;

import java.io.PrintWriter;
import java.util.Arrays;
<<<<<<< HEAD
// import br.com.tempest.JSReader;
=======
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.tempest.JSReader;
>>>>>>> 3de0de823adb3deecfb92abf120a711e5728f81e

public class BurpExtender implements IBurpExtender, IHttpListener//, IScannerCheck
{
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    PrintWriter stdout;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        this.callbacks = callbacks;

        this.callbacks.setExtensionName("JavaScript Scanner");
        this.callbacks.registerHttpListener(this);
        // this.callbacks.registerScannerCheck(this);

        this.helpers = callbacks.getHelpers();

        stdout = new PrintWriter(this.callbacks.getStdout(), true);
    }

    @Override
    public void processHttpMessage( int toolFlag,
    boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
        boolean shouldContinue = true;
        if (shouldContinue && !messageIsRequest)
        {
            byte[] response = messageInfo.getResponse();
            IResponseInfo responseInfo = helpers.analyzeResponse(response);
            IRequestInfo requestInfo = helpers.analyzeRequest(messageInfo);
            int bodyOffset = responseInfo.getBodyOffset();
            byte[] responseBody = Arrays.copyOfRange(response, bodyOffset, response.length);
            String body = new String(responseBody);
            if (responseInfo.getStatedMimeType() == "script")
            {
                stdout.println("Script @ "+requestInfo.getUrl().toString());
                try
                {
                    
                }
                catch (Exception ex)
                {

                }
            }
            else if (responseInfo.getStatedMimeType() != "")
            {
                stdout.println(responseInfo.getStatedMimeType() +
                " @ "+ requestInfo.getUrl().toString());
                Document dom = Jsoup.parse(body);
                Elements scriptTags = dom.getElementsByTag("Script");
                if (scriptTags.size() > 0)
                {
                    stdout.println("Total Script Tags found:" + scriptTags.size());
                    for(Element scriptTag: scriptTags)
                    {
                        List<String> routes = JSReader.lookForMatches(scriptTag.html(), JSReader.localPathPattern);
                        if (routes.size() > 0)
                        {
                            stdout.println("Localroutes found: " + routes.size());
                            for (String route: routes)
                            {
                                stdout.println(route);
                            }
                        }
                    }
                }
            }
            else
            {

            }
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
