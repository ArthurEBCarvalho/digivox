--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 9.6.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: aluguel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aluguel (
    id bigint NOT NULL,
    fim timestamp without time zone NOT NULL,
    inicio timestamp without time zone NOT NULL,
    valor numeric(10,2),
    cliente_id bigint,
    item_id bigint,
    tipo character varying(255) NOT NULL
);


ALTER TABLE public.aluguel OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    tipo_item_id bigint
);


ALTER TABLE public.item OWNER TO postgres;

--
-- Name: tipo_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_item (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.tipo_item OWNER TO postgres;

--
-- Name: tipo_item_itens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_item_itens (
    tipo_item_id bigint NOT NULL,
    itens_id bigint NOT NULL
);


ALTER TABLE public.tipo_item_itens OWNER TO postgres;

--
-- Data for Name: aluguel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aluguel (id, fim, inicio, valor, cliente_id, item_id, tipo) FROM stdin;
21	2019-04-30 00:00:00	2019-04-25 00:00:00	2.50	15	10	Reserva
23	2019-04-26 00:00:00	2019-04-24 00:00:00	1500.25	15	12	Aluguel
28	2019-04-26 00:00:00	2019-04-25 00:00:00	200.00	27	25	Reserva
29	2019-04-28 00:00:00	2019-04-27 00:00:00	300.00	27	25	Reserva
30	2019-04-25 00:00:00	2019-04-24 00:00:00	1.25	27	25	Aluguel
31	2019-05-02 00:00:00	2019-05-01 00:00:00	1.00	15	10	Reserva
32	2019-04-25 00:00:00	2019-04-24 00:00:00	1.50	15	26	Aluguel
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome) FROM stdin;
15	Cliente 1
27	Cliente Teste
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 32, true);


--
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item (id, nome, tipo_item_id) FROM stdin;
10	Feijão	9
12	Sabonete	3
25	Item Teste	24
26	Item Teste 2	24
\.


--
-- Data for Name: tipo_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_item (id, nome) FROM stdin;
3	Limpeza
9	Alimento
24	Tipo de Item Teste
\.


--
-- Data for Name: tipo_item_itens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_item_itens (tipo_item_id, itens_id) FROM stdin;
\.


--
-- Name: aluguel aluguel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluguel
    ADD CONSTRAINT aluguel_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- Name: tipo_item tipo_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_item
    ADD CONSTRAINT tipo_item_pkey PRIMARY KEY (id);


--
-- Name: tipo_item_itens uk_hg0j43d3rbjhkbvq9dc8mqenj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_item_itens
    ADD CONSTRAINT uk_hg0j43d3rbjhkbvq9dc8mqenj UNIQUE (itens_id);


--
-- Name: aluguel fk748cpvt1o259iu7a4sexhj127; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluguel
    ADD CONSTRAINT fk748cpvt1o259iu7a4sexhj127 FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: tipo_item_itens fkcxk4lt2wpkx1m5f13ylf7aajs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_item_itens
    ADD CONSTRAINT fkcxk4lt2wpkx1m5f13ylf7aajs FOREIGN KEY (tipo_item_id) REFERENCES public.tipo_item(id);


--
-- Name: item fkrg10txwl9iabrs9noqvsn21yx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT fkrg10txwl9iabrs9noqvsn21yx FOREIGN KEY (tipo_item_id) REFERENCES public.tipo_item(id);


--
-- Name: tipo_item_itens fksokb26aaqu0c2siq7aja4kxer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_item_itens
    ADD CONSTRAINT fksokb26aaqu0c2siq7aja4kxer FOREIGN KEY (itens_id) REFERENCES public.item(id);


--
-- Name: aluguel fkt8b6fc9r7yit60pmx1824543x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aluguel
    ADD CONSTRAINT fkt8b6fc9r7yit60pmx1824543x FOREIGN KEY (item_id) REFERENCES public.item(id);


--
-- PostgreSQL database dump complete
--

