package com.edumo.wordcounter.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

/**
 * 
 * @author Eduardo Moriana
 *
 */

@Component
@Log
public class LocalFileRepository implements FileRepository {

	@Value("${word.counter.filepath}")
	private String path;

	@Override
	public List<String> findByFileName(String fileName) {

		// always return emnpty, less info for attacks as possible
		List<String> words = new ArrayList<>();

		File file = new File(path + File.separator+ fileName);
		List<String> lines = null;
		if (!file.exists()) {
			log.warning("The file doesn't exists " + file.getAbsolutePath());
			return words;
		}

		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (lines != null) {

			for (String line : lines) {
				String[] wordsArray = line.split(" ");
				for (String word : wordsArray) {
					words.add(word);
				}
			}
		}

		return words;
	}

}
