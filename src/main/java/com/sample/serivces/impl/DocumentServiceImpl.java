package com.sample.serivces.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.exception.DocumentNotFoundException;
import com.sample.model.Document;
import com.sample.repository.DocumentRepository;
import com.sample.serivces.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger logger = LogManager.getLogger(DocumentServiceImpl.class);

	@Autowired
	private DocumentRepository documentRepository;

	// private final String UPLOAD_DIR =
	// "/Users/srikanthjosyula/Documents/GitHub-Projects/springboot-docker-compose";
	// // Change to your path

	// Use system property or default path
	private static final String UPLOAD_DIR = System.getProperty("upload.path", "/uploads");

	@Transactional
	@Override
	public Document saveDocument(Document document) throws IOException {

		logger.info("Saving document: {}", document);

		Path uploadPath = Paths.get(UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			logger.info("Upload directory does not exist. Creating directories at: {}", uploadPath.toString());
			Files.createDirectories(uploadPath);
		}

		Path filePath = Paths.get(UPLOAD_DIR, document.getName());
		logger.info("Document path set to: {}", filePath.toString());
		document.setFilePath(filePath.toString());

		Document savedDocument = documentRepository.save(document);
		logger.info("Document saved successfully with ID: {}", savedDocument.getId());

		return savedDocument;
	}

	@Override
	public Document getDocumentById(UUID id) throws DocumentNotFoundException {
		logger.info("Fetching document with ID: {}", id);
		Document document = documentRepository.findById(id).orElseThrow(() -> {
			logger.error("Document not found for ID: {}", id);
			return new DocumentNotFoundException("Document not found for id: " + id);
		});

		// Logging the retrieved document details
		logger.info("Document retrieved: {}", document);

		return document;
	}
}
