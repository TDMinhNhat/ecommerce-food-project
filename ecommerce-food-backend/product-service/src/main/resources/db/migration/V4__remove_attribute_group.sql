-- Version 4: Removing attribute group and attribute group items, add a new table and column

-- Drop table
DROP TABLE attribute_group_items;
DROP TABLE attribute_groups;

-- Create attribute_type
CREATE TABLE attribute_types
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    note               VARCHAR(255),
    created_date       TIMESTAMP WITH TIME ZONE            NOT NULL,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    created_by         VARCHAR(255)                        NOT NULL,
    last_modified_by   VARCHAR(255),
    deleted            BOOLEAN DEFAULT FALSE               NOT NULL,
    version            BIGINT                              NOT NULL,
    type_name          VARCHAR(100)                        NOT NULL,
    CONSTRAINT pk_attribute_types PRIMARY KEY (id)
);

-- Add a column and foreign key
ALTER TABLE attributes
    ADD COLUMN attribute_type_id BIGINT NOT NULL;

ALTER TABLE attributes
    ADD CONSTRAINT fk_attribute_type_id FOREIGN KEY (attribute_type_id) REFERENCES attribute_types(id);