CREATE TABLE tb_sale (
  id BIGINT AUTO_INCREMENT NOT NULL,
   amount DECIMAL NOT NULL,
   amount_paid DECIMAL NOT NULL,
   difference DECIMAL NOT NULL,
   payment INT NOT NULL,
   created_at datetime NOT NULL,
   update_at datetime NULL,
   CONSTRAINT pk_tb_sale PRIMARY KEY (id)
);

CREATE TABLE tb_sale_product_sold (
  sale_id BIGINT NOT NULL,
   product_sold_id BIGINT NOT NULL
);

ALTER TABLE tb_sale_product_sold ADD CONSTRAINT uc_tb_sale_product_sold_productsold UNIQUE (product_sold_id);

ALTER TABLE tb_sale_product_sold ADD CONSTRAINT fk_tbsalprosol_on_product_sold FOREIGN KEY (product_sold_id) REFERENCES tb_product_sold (id);

ALTER TABLE tb_sale_product_sold ADD CONSTRAINT fk_tbsalprosol_on_sale FOREIGN KEY (sale_id) REFERENCES tb_sale (id);