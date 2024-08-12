package com.sample.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

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

    @Autowired
    private DocumentService documentService;

    @PostMapping(value = { "/upload" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadBinary(
    		@RequestPart("file") MultipartFile file,
            @RequestParam("name") String name, 
            @RequestParam("title") String title,
            @RequestParam(value = "createdBy", defaultValue = "SYSTEM", required = false) String createdBy,
            @RequestParam(value = "createdDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate) throws IOException {

        if (createdDate == null) {
            createdDate = LocalDate.now();
        }

        Document document = new Document();
        document.setName(name);
        document.setTitle(title);
        document.setCreatedBy(createdBy);
        document.setCreatedDate(java.sql.Date.valueOf(createdDate));

        Document respDoc = documentService.saveDocument(document);
        return ResponseEntity.ok(respDoc);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") String id) throws IOException, DocumentNotFoundException {

        UUID uuid = UUID.fromString(id);
        Document document = documentService.getDocumentById(uuid);

        Path path = Paths.get(document.getFilePath());
        if (!Files.exists(path)) {
            throw new DocumentNotFoundException("File not found at path: " + document.getFilePath());
        }
        byte[] fileContent = Files.readAllBytes(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", document.getName());
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
