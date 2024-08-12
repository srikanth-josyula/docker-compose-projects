package com.sample.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("doc_id")
	private UUID id;

	@Column(name = "doc_name", nullable = false)
	@JsonProperty("doc_name")
	private String name;

	@Column(name = "doc_title", nullable = false)
	@JsonProperty("doc_title")
	private String title;

	@Column(name = "created_by", nullable = false)
	@JsonProperty("created_by")
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	@JsonProperty("created_date")
	private Date createdDate;

	@Column(name = "document_content")
	@JsonProperty("document_content")
	private String documentContent;

	@Column(name = "file_path")
	@JsonProperty("file_path")
	private String filePath;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
