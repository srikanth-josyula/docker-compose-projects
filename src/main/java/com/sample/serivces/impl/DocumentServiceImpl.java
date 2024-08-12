package com.sample.serivces.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.exception.DocumentNotFoundException;
import com.sample.model.Document;
import com.sample.repository.DocumentRepository;

@Service
public class DocumentServiceImpl {

	@Autowired
	private DocumentRepository documentRepository;

	private final String UPLOAD_DIR = "/path/to/upload/dir"; // Change to your path

	public Document saveDocument(Document document) throws IOException {
		// Save file to location
		Path filePath = Paths.get(UPLOAD_DIR, document.getName());
		Files.write(filePath, document.getDocumentContent().getBytes());

		// Update document file path
		document.setFilePath(filePath.toString());
		return documentRepository.save(document);
	}

	public Document getDocumentById(UUID id) throws DocumentNotFoundException {
		return documentRepository.findById(id)
				.orElseThrow(() -> new DocumentNotFoundException("Document not found for id: " + id));
	}
}
