CREATE TABLE tb_product_sold (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_id BIGINT NULL,
   price DECIMAL NOT NULL,
   price_total DECIMAL NOT NULL,
   quantity INT NOT NULL,
   CONSTRAINT pk_tb_product_sold PRIMARY KEY (id)
);

ALTER TABLE tb_product_sold ADD CONSTRAINT FK_TB_PRODUCT_SOLD_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);