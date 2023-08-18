CREATE TABLE product (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(50) NOT NULL,
   price DECIMAL NOT NULL,
   active BIT(1) NOT NULL,
   created_at datetime NOT NULL,
   update_at datetime NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT uc_product_name UNIQUE (name);