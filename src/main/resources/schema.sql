
CREATE TABLE public.ator (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	sexo varchar(255) NOT NULL,
	CONSTRAINT ator_pkey PRIMARY KEY (id)
);

CREATE TABLE public.oscar (
	id bigserial NOT NULL,
	ano int4 NOT NULL,
	idade_ator int4 NOT NULL,
	nome_filme varchar(255) NOT NULL,
	CONSTRAINT oscar_pkey PRIMARY KEY (id)
);

CREATE TABLE public.ator_vencedor (
	id bigserial NOT NULL,
	ator_id int8 NULL,
	oscar_id int8 NULL,
	CONSTRAINT ator_vencedor_pkey PRIMARY KEY (id)
);


-- public.ator_vencedor foreign keys

ALTER TABLE public.ator_vencedor ADD CONSTRAINT fkcbsq4ce6h8oeiw2cgvfb5hugd FOREIGN KEY (ator_id) REFERENCES public.ator(id);
ALTER TABLE public.ator_vencedor ADD CONSTRAINT fkfj2castqvhti4fx15pflgh1ve FOREIGN KEY (oscar_id) REFERENCES public.oscar(id);