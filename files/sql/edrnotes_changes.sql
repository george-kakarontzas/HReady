-- Table: edrnotes

-- DROP TABLE edrnotes;

CREATE TABLE edrnotes
(
  idnote integer NOT NULL DEFAULT nextval('"edrNotes_idnotes_seq"'::regclass),
  edr_idedr integer,
  note character varying(255),
  date date,
  CONSTRAINT edrnotes_pkey PRIMARY KEY (idnote),
  CONSTRAINT "edrnotes.edr_idedr_fkey" FOREIGN KEY (edr_idedr)
      REFERENCES edr (idedr) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE edrnotes
  OWNER TO comprofits;

-- Index: edrnotes_edr_idedr_idx

-- DROP INDEX edrnotes_edr_idedr_idx;

CREATE INDEX edrnotes_edr_idedr_idx
  ON edrnotes
  USING btree
  (edr_idedr);

