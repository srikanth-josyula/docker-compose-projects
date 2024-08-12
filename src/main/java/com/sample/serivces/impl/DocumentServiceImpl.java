package com.sample.serivces.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.exception.DocumentNotFoundException;
import com.sample.model.Document;
import com.sample.repository.DocumentRepository;
import com.sample.serivces.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	private final String UPLOAD_DIR = "/Users/srikanthjosyula/Documents/GitHub-Projects/springboot-docker-compose"; // Change to your path

	@Transactional
	@Override
	public Document saveDocument(Document document) throws IOException {

		Path uploadPath = Paths.get(UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		Path filePath = Paths.get(UPLOAD_DIR, document.getName());

		document.setFilePath(filePath.toString());
		return documentRepository.save(document);
	}

	@Override
	public Document getDocumentById(UUID id) throws DocumentNotFoundException {
		return documentRepository.findById(id)
				.orElseThrow(() -> new DocumentNotFoundException("Document not found for id: " + id));
	}
}
