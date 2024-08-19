import java.io.File;
import java.io.InputStream;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;
import org.junit.jupiter.api.Test;

public class XsltTest {

	@Test
	void shouldTransform() {
		Processor proc = new Processor(false);
		XsltCompiler comp = proc.newXsltCompiler();

		ClassLoader classLoader = XsltTest.class.getClassLoader();
		// load xsl file from java project
		InputStream xsl = classLoader.getResourceAsStream("transformation.xsl");
		// load xml file from java project
		InputStream xmlInput = classLoader.getResourceAsStream("input.xml");
		try {
			XsltExecutable exp = comp.compile(new StreamSource(xsl));

			XdmNode source = proc.newDocumentBuilder().build(new StreamSource(xmlInput));

			Serializer out = proc.newSerializer(new File( "result.xml"));
			//out.setOutputProperty(Serializer.Property.METHOD, "html");
			//out.setOutputProperty(Serializer.Property.INDENT, "yes");
			out.setOutputProperty(Serializer.Property.INDENT, "yes");
			XsltTransformer trans = exp.load();
			trans.setInitialContextNode(source);
			trans.setDestination(out);
			trans.transform();
		} catch (SaxonApiException e) {
			e.printStackTrace();
		}
	}
}
