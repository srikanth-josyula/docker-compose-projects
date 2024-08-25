package com.sample.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.exception.DocumentNotFoundException;
import com.sample.model.Document;
import com.sample.serivces.DocumentService;

@RestController
public class DocumentController {

	private static final Logger logger = LogManager.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@PostMapping(value = { "/upload" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Document> uploadBinary(@RequestPart("file") MultipartFile file,
			@RequestParam("name") String name, @RequestParam("title") String title,
			@RequestParam(value = "createdBy", defaultValue = "SYSTEM", required = false) String createdBy,
			@RequestParam(value = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate)
			throws IOException {

		logger.info("Upload request received for file: {}, name: {}, title: {}", file.getOriginalFilename(), name,
				title);

		if (createdDate == null) {
			createdDate = LocalDate.now();
			logger.info("Created date not provided, using current date: {}", createdDate);
		}

		Document document = new Document();
		document.setName(name);
		document.setTitle(title);
		document.setCreatedBy(createdBy);
		document.setCreatedDate(java.sql.Date.valueOf(createdDate));
		logger.info("Saving document: {}", document);
		Document respDoc = documentService.saveDocument(document);

		logger.info("Document saved successfully with ID: {}", respDoc.getId());
		return ResponseEntity.ok(respDoc);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id)
			throws IOException, DocumentNotFoundException {

		logger.info("Download request received for document ID: {}", id);

		UUID uuid = UUID.fromString(id);
		Document document = documentService.getDocumentById(uuid);

		logger.info("Document retrieved: {}", document);
		Path path = Paths.get(document.getFilePath());
		if (!Files.exists(path)) {
			logger.error("File not found at path: {}", document.getFilePath());
			throw new DocumentNotFoundException("File not found at path: " + document.getFilePath());
		}
		byte[] fileContent = Files.readAllBytes(path);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", document.getName());

		logger.info("Returning file content for download with name: {}", document.getName());
		return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}
}
