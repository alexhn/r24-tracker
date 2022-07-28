package r24.tracker;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class PricesReaderTest {

	@Test
	public void test() throws IOException {
		PricesReader reader = new PricesReader();
		Document doc = Jsoup.connect("https://www.reinvest24.com/en/browse/secondary-market").get();
		Elements links = doc.select("a[href*='/en/project']");
	}
	
}
