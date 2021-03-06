-- SQL Manager Lite for PostgreSQL 6.3.1.54838
-- ---------------------------------------
-- Host      : localhost
-- Database  : hookahstore
-- Version   : PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit



SET check_function_bodies = false;
--
-- Structure for table product (OID = 24937) :
--
SET search_path = public, pg_catalog;
CREATE TABLE public.product (
    id bigint NOT NULL,
    name varchar(100) NOT NULL,
    id_producer bigint NOT NULL,
    id_category bigint NOT NULL,
    image_link varchar(255) NOT NULL,
    description text NOT NULL,
    price numeric(6,0) NOT NULL,
    count smallint
)
WITH (oids = false);
ALTER TABLE ONLY public.product ALTER COLUMN id_category SET STATISTICS 0;
--
-- Structure for table producer (OID = 24945) :
--
CREATE TABLE public.producer (
    id bigint NOT NULL,
    name varchar(50) NOT NULL,
    product_count integer NOT NULL
)
WITH (oids = false);
--
-- Structure for table category (OID = 24950) :
--
CREATE TABLE public.category (
    id bigint NOT NULL,
    name varchar(50) NOT NULL,
    product_count integer NOT NULL,
    url varchar(100) NOT NULL
)
WITH (oids = false);
--
-- Structure for table account (OID = 24965) :
--
CREATE TABLE public.account (
    id integer NOT NULL,
    name varchar(60) NOT NULL,
    email varchar(100) NOT NULL
)
WITH (oids = false);
--
-- Structure for table order (OID = 24972) :
--
CREATE TABLE public."order" (
    id bigint NOT NULL,
    id_account integer NOT NULL,
    created timestamp(0) without time zone DEFAULT now() NOT NULL
)
WITH (oids = false);
--
-- Structure for table order_item (OID = 24983) :
--
CREATE TABLE public.order_item (
    id bigint NOT NULL,
    id_order bigint NOT NULL,
    id_product integer NOT NULL,
    count integer NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence account_seq (OID = 24998) :
--
CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence order_seq (OID = 25000) :
--
CREATE SEQUENCE public.order_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence order_item_seq (OID = 25002) :
--
CREATE SEQUENCE public.order_item_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for index product_pkey (OID = 24943) :
--
ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey
    PRIMARY KEY (id);
--
-- Definition for index producer_pkey (OID = 24948) :
--
ALTER TABLE ONLY producer
    ADD CONSTRAINT producer_pkey
    PRIMARY KEY (id);
--
-- Definition for index category_pkey (OID = 24953) :
--
ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey
    PRIMARY KEY (id);
--
-- Definition for index product_fk (OID = 24955) :
--
ALTER TABLE ONLY product
    ADD CONSTRAINT product_fk
    FOREIGN KEY (id_category) REFERENCES category(id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Definition for index product_fk1 (OID = 24960) :
--
ALTER TABLE ONLY product
    ADD CONSTRAINT product_fk1
    FOREIGN KEY (id_producer) REFERENCES producer(id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Definition for index account_pkey (OID = 24968) :
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey
    PRIMARY KEY (id);
--
-- Definition for index account_email_key (OID = 24970) :
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_email_key
    UNIQUE (email);
--
-- Definition for index order_pkey (OID = 24976) :
--
ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_pkey
    PRIMARY KEY (id);
--
-- Definition for index order_fk (OID = 24978) :
--
ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_fk
    FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Definition for index order_item_pkey (OID = 24986) :
--
ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_pkey
    PRIMARY KEY (id);
--
-- Definition for index order_item_fk (OID = 24988) :
--
ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk
    FOREIGN KEY (id_order) REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index order_item_fk1 (OID = 24993) :
--
ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_item_fk1
    FOREIGN KEY (id_product) REFERENCES product(id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Data for sequence public.account_seq (OID = 24998)
--
SELECT pg_catalog.setval('account_seq', 1, false);
--
-- Data for sequence public.order_seq (OID = 25000)
--
SELECT pg_catalog.setval('order_seq', 1, false);
--
-- Data for sequence public.order_item_seq (OID = 25002)
--
SELECT pg_catalog.setval('order_item_seq', 1, false);
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
