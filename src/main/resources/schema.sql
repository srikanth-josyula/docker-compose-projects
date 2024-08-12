-- Enable the uuid-ossp extension for UUID generation
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the documents table if it does not exist
CREATE TABLE IF NOT EXISTS documents (
    doc_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    doc_name VARCHAR(255) NOT NULL,
    doc_title VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    created_date DATE NOT NULL,
    file_path TEXT
);
