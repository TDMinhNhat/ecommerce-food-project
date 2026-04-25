-- Version 1: Initialize some tables in ecommerce-food-product-service database

CREATE TABLE attribute_group_items
(
    id                 BIGINT                      NOT NULL,
    note               VARCHAR(255),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    created_by         VARCHAR(255)                NOT NULL,
    last_modified_by   VARCHAR(255),
    deleted            BOOLEAN DEFAULT FALSE       NOT NULL,
    version            BIGINT                      NOT NULL,
    attribute_id       BIGINT                      NOT NULL,
    attribute_group_id BIGINT                      NOT NULL,
    CONSTRAINT pk_attribute_group_items PRIMARY KEY (id)
);

CREATE TABLE attribute_groups
(
    id                 BIGINT                      NOT NULL,
    note               VARCHAR(255),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    created_by         VARCHAR(255)                NOT NULL,
    last_modified_by   VARCHAR(255),
    deleted            BOOLEAN DEFAULT FALSE       NOT NULL,
    version            BIGINT                      NOT NULL,
    group_name         VARCHAR(150)                NOT NULL,
    group_type         VARCHAR(100)                NOT NULL,
    CONSTRAINT pk_attribute_groups PRIMARY KEY (id)
);

CREATE TABLE attributes
(
    id                 BIGINT                      NOT NULL,
    note               VARCHAR(255),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    created_by         VARCHAR(255)                NOT NULL,
    last_modified_by   VARCHAR(255),
    deleted            BOOLEAN DEFAULT FALSE       NOT NULL,
    version            BIGINT                      NOT NULL,
    attribute_name     VARCHAR(100)                NOT NULL,
    description        VARCHAR(255),
    CONSTRAINT pk_attributes PRIMARY KEY (id)
);

CREATE TABLE product_categories
(
    id                      BIGINT                      NOT NULL,
    note                    VARCHAR(255),
    created_date            TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_date      TIMESTAMP WITH TIME ZONE,
    created_by              VARCHAR(255)                NOT NULL,
    last_modified_by        VARCHAR(255),
    deleted                 BOOLEAN DEFAULT FALSE       NOT NULL,
    version                 BIGINT                      NOT NULL,
    product_category_parent BIGINT,
    category_name           VARCHAR(100)                NOT NULL,
    description             VARCHAR(255),
    image_id                VARCHAR(50),
    CONSTRAINT pk_product_categories PRIMARY KEY (id)
);

ALTER TABLE attribute_groups
    ADD CONSTRAINT uc_attribute_groups_group_name UNIQUE (group_name);

ALTER TABLE attribute_groups
    ADD CONSTRAINT uc_attribute_groups_group_type UNIQUE (group_type);

ALTER TABLE attributes
    ADD CONSTRAINT uc_attributes_attribute_name UNIQUE (attribute_name);

ALTER TABLE product_categories
    ADD CONSTRAINT uc_product_categories_category_name UNIQUE (category_name);

ALTER TABLE product_categories
    ADD CONSTRAINT uc_product_categories_image UNIQUE (image_id);

ALTER TABLE attribute_group_items
    ADD CONSTRAINT FK_ATTRIBUTE_GROUP_ITEMS_ON_ATTRIBUTE FOREIGN KEY (attribute_id) REFERENCES attributes (id);

ALTER TABLE attribute_group_items
    ADD CONSTRAINT FK_ATTRIBUTE_GROUP_ITEMS_ON_ATTRIBUTE_GROUP FOREIGN KEY (attribute_group_id) REFERENCES attribute_groups (id);

ALTER TABLE product_categories
    ADD CONSTRAINT FK_PRODUCT_CATEGORIES_ON_PRODUCT_CATEGORY_PARENT FOREIGN KEY (product_category_parent) REFERENCES product_categories (id);