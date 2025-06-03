package com.udea.virtualfaker;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VirtualfakerApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	public void healthCheck() {
		assert(dataController.healthCheck()
				.equals("HEALTH CHECK OK"));
	}

	@Test
	public void version() {
		assert(dataController.version()
				.equals("Version is 1.0.0"));
	}

	@Test
	public void nationsLength() {
		Integer nationsLength = dataController.getRandomNations().size();
		assertEquals(10, nationsLength);
	}

	@Test
	public void currenciesLength() {
		Integer currenciesLength = dataController.getRandomCurrencies().size();
		assertEquals(20, currenciesLength);
	}

	@Test
	public void textRandomCurrenciesCodeFormat() {
		DataController dataController = new DataController();
		JsonNode response = dataController.getRandomCurrencies();
		for(JsonNode currency : response){
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}"));
		}
	}

	@Test
	public void testRandomNationsPerformance() {
		DataController dataController = new DataController();
		long start = System.currentTimeMillis();
		dataController.getRandomNations();
		long end = System.currentTimeMillis();
		long timeElapsed = end - start;
		assertTrue(timeElapsed < 2000);
	}

	@Test
	public void aviationLength() {
		Integer aviationLength = dataController.getRandomAviation().size();
		assertEquals(20, aviationLength);
	}

}
