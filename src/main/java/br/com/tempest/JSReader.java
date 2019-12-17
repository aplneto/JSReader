package br.com.tempest;

import java.util.regex.*;
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
    // Matches generic local path strings
    public static Pattern localPathPattern = Pattern.compile(
        "['\"](\\w+[\\\\/]?)+['\"]"
    );
    // Matches
    public static List<String> lookForMatches(String script, Pattern searchPattern)
    {
        Matcher matcher = searchPattern.matcher(script);
        LinkedList<String> results = new LinkedList<String>();
        while (matcher.find())
        {
            results.add(script.substring(matcher.start(), matcher.end()));
        }
        return results;
    }

    public static List<String> parse(String script)
    {
        return null;
    }
}