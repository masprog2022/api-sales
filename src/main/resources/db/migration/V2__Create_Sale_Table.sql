CREATE TABLE sale (
  id BIGINT AUTO_INCREMENT NOT NULL,
   total_amount DECIMAL NOT NULL,
   amount_paid DECIMAL NOT NULL,
   difference DECIMAL NOT NULL,
   payment INT NOT NULL,
   created_at datetime NOT NULL,
   update_at datetime NULL,
   CONSTRAINT pk_sale PRIMARY KEY (id)
);