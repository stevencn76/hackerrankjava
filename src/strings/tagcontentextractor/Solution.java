package strings.tagcontentextractor;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution{
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String line = in.nextLine();

            //Write your code here
            List<String> contentList = extractTagContents(line);
            if(contentList.size() == 0) {
                System.out.println("None");
            } else {
                for(String content : contentList) {
                    System.out.println(content);
                }
            }

            testCases--;
        }
    }

    private static List<String> extractTagContents(String input) {
        List<String> contentList = new LinkedList<>();

        if(input == null) {
            return contentList;
        }

        Pattern pattern = Pattern.compile("<.+?>");
        Matcher matcher = pattern.matcher(input);

        class Tag {
            public int start;
            public int end;
            public String name;
            public boolean endTag;
        }

        Tag lastTag = null;
        while(matcher.find()) {
            Tag newTag = new Tag();
            newTag.start = matcher.start();
            newTag.end = matcher.end();
            newTag.endTag = input.charAt(newTag.start + 1) == '/';
            // <h1> </h1>
            int tagNameStart = newTag.endTag? newTag.start + 2 : newTag.start + 1;
            int tagNameEnd = newTag.end - 1;
            newTag.name = input.substring(tagNameStart, tagNameEnd);

            if(lastTag == null) {
                lastTag = newTag;
            } else {
                if(!lastTag.endTag && newTag.endTag && lastTag.name.equals(newTag.name) && newTag.start - lastTag.end > 1) {
                    contentList.add(input.substring(lastTag.end, newTag.start));
                }

                lastTag = newTag;
            }
        }

        return contentList;
    }

}
