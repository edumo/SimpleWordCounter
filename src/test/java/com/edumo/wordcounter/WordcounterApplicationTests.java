package com.edumo.wordcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edumo.wordcounter.service.WordCounterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordcounterApplicationTests {

	static String[] COUNTRY_NAMES = { "palabra1", "palabra2", "palabra2.", "palabra3", "palabra3;", "palabra3",
			"palabra4", "palabra4", "palabra4", "palabra4,", "palabrá5", "Palabra5", "Palabra5", "Palabra5",
			" Palabra6" };

	@Autowired
	WordCounterService wordCounter;

	@Test
	public void testWords() {

		List<String> listNames = new ArrayList<String>(Arrays.asList(COUNTRY_NAMES));

		Map<String, Integer> counterMap = wordCounter.countWordsOnList(listNames);

		assertEquals(counterMap.get("palabra1").intValue(), 1);
		assertEquals(counterMap.get("palabra2").intValue(), 2);
		assertEquals(counterMap.get("palabra3").intValue(), 3);
		assertEquals(counterMap.get("palabra4").intValue(), 4);

	}

	@Test
	public void testTrim() {

		List<String> listNames = new ArrayList<String>(Arrays.asList(COUNTRY_NAMES));

		Map<String, Integer> counterMap = wordCounter.countWordsOnList(listNames);

		assertEquals(counterMap.get("Palabra6").intValue(), 1);

	}

	@Test
	public void testFile() {

		Map<String, Integer> result = wordCounter.countWordsOnFile("words.txt");
		assertFalse(result.isEmpty());
		assertEquals(result.get("adipiscing").intValue(), 5);
		assertEquals(result.get("aliqua").intValue(), 4);

	}

}
