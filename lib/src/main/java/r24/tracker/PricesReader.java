package r24.tracker;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PricesReader {

	@Autowired
	private Executor reader;
	
	private Map<Long, Project> projects = Collections.synchronizedMap(new HashMap<>());
	
	@Scheduled(fixedRate = 120000)
	public void readReInvestPrices() throws IOException {
		Document doc = Jsoup.connect("https://www.reinvest24.com/en/browse/secondary-market").get();
		Elements links = doc.select("a[href*='/en/project']");
		
		List<ProjectDesc> projectDescs = new ArrayList<>();
		links.forEach(link -> {
			String address = link.parent().select("p.item-address").text();
			String country = address.substring(address.lastIndexOf(",") + 1).trim();
			projectDescs.add(new ProjectDesc(link.attr("href"), address, country));
		});
		
		projectDescs.forEach(projectDesc -> {
			String projectHref = projectDesc.getLink();
			String projectUrl = "https://reinvest24.com/" + projectHref;
			reader.execute(() -> {
				try {
					String[] tokens = projectUrl.split("/");
					Long projectId = Long.parseLong(tokens[tokens.length - 1]);
					String projectSlug = tokens[tokens.length - 2];
					Project project = readProject(projectSlug, projectDesc.getCountry(), projectId);
					projects.put(project.getId(), project);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		});
	}
	
	public List<Project> getProjects() {
		return new ArrayList<>(projects.values());
	}

	public Project readProject(String projectSlug, String country, Long projectId) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		
		String resourceUrl = "https://api-frontend.reinvest24.com/graphql";

		InvestmentCalculatorQuery q = new InvestmentCalculatorQuery();
		q.getVariables().put("id", projectId);
		ResponseEntity<InvestmentCalculatorResponse> response = restTemplate.postForEntity(resourceUrl, q, InvestmentCalculatorResponse.class);
		
		return new Project(projectId, projectSlug, response.getBody().getData().getProperty().getTitle(), country, response.getBody().getData().getAggregatedShareSales(), "", BigDecimal.ZERO, BigDecimal.ZERO);
		
		
	}
	
}
