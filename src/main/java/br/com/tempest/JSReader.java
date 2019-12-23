package br.com.tempest;

import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class JSReader
{
    // Matches generic URI patterns
    public static Pattern uriPattern = Pattern.compile("\\w+:(//)?(\\w*[\\./]?)+");
    // Matches common string association patterns in JavaScript
    public static Pattern stringAssociationPattern = Pattern.compile(
        "((var|let|const)\\s)?\\w+\\s?\\+?=\\s?(('([^'])*')|(\"[^\"]*\"))"
    );
    // Matches common string patterns
    public static Pattern stringPattern = Pattern.compile("(('([^'])*')|(\"[^\"]*\"))");
    // Matches generic local path strings including string with fragment identified portions
    public static Pattern localPathPattern = Pattern.compile(
        "['\"]((/[\\w\\?=&]+/?)*|([\\w\\?=&]*/([\\w\\?=&]*/?)*))(#.*)?['\"]"
    );
    public static String helloWorld(){
        return "Olá Mundo!";
    }

    public static List<String> lookForMatches(String script, Pattern searchPattern)
    {
        Matcher matcher = searchPattern.matcher(script);
        LinkedList<String> results = new LinkedList<String>();
        while (matcher.find())
        {
            String match = script.substring(matcher.start(), matcher.end());
            String[] ignore = {"''", "\"\"", "'/'", "\"/\""};
            List<String> listOfIgnoredMatches = Arrays.asList(ignore);
            if (!listOfIgnoredMatches.contains(match))
            {
                results.add(match);
            }
        }
        return results;
    }

    public static List<String> parseHtmlBody(String htmlText)
    {
        Document doc = Jsoup.parse(htmlText);
        Elements scripts = doc.getElementsByTag("script");
        return scripts.eachText();

    }

    public List<String> parseJavaScriptBody(String scriptText)
    {
        return null;
    }
}