package xslt;

import javax.xml.transform.TransformerFactory;

public class XsltTest {

    public static void main(String args[]) {
        TransformerFactory tFactory = TransformerFactory.newDefaultInstance();

        System.out.println("hello " + tFactory);
    }

}
