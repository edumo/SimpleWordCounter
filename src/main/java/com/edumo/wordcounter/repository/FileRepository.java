package com.edumo.wordcounter.repository;

import java.util.List;

/**
 * 
 * @author Eduardo Moriana
 *
 */

public interface FileRepository {

	List<String> findByFileName(String fileName);

}