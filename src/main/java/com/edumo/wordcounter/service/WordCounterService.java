package com.edumo.wordcounter.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edumo.wordcounter.repository.FileRepository;

import lombok.extern.java.Log;


/**
 * 
 * @author Eduardo Moriana
 *
 */

@Component
@Log
public class WordCounterService {

	@Autowired
	FileRepository fileRepository;

	/**
	 * Count Words frecuency from file
	 * 
	 * @param fileName
	 * @return
	 */
	public Map<String, Integer> countWordsOnFile(String fileName) {

		List<String> allWords = null;
		if (fileName.contains(File.separator)) {
			log.warning("fileName with separator not allowed, security and relative paths " + fileName);
			allWords = new ArrayList<>();
		} else {
			log.info("counting words on file " + fileName);
			allWords = fileRepository.findByFileName(fileName);
			log.info("words on file " + allWords);
		}

		return countWordsOnList(allWords);
	}

	/**
	 * count words frecuency from list
	 * 
	 * @param allWords
	 * @return
	 */

	public Map<String, Integer> countWordsOnList(List<String> allWords) {
		Map<String, Integer> counterMapWords = new HashMap<>();

		for (String word : allWords) {
			
			//replace all . , ;
			String tempWord = word.replaceAll(",","");
			tempWord = tempWord.replaceAll("\\.",""); 
			tempWord = tempWord.replaceAll(";","");
			tempWord = tempWord.trim();
			counterMapWords.compute(tempWord, (k, v) -> v == null ? 1 : v + 1);
		}

		log.info("words on file by frecuency " + counterMapWords);

		return counterMapWords;
	}

}
