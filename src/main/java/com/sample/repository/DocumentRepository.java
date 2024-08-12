package com.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.model.Document;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
}
