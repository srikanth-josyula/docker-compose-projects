package com.sample.serivces;

import java.io.IOException;
import java.util.UUID;

import com.sample.exception.DocumentNotFoundException;
import com.sample.model.Document;

public interface DocumentService {
	public Document saveDocument(Document document) throws IOException;
	public Document getDocumentById(UUID uuid) throws DocumentNotFoundException;
}
