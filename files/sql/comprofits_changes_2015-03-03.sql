CREATE SEQUENCE idanswer_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 48
  CACHE 1;
ALTER TABLE idanswer_seq
  OWNER TO comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO postgres;

ALTER TABLE "edrNotes"
   ADD COLUMN author_idemployee integer NOT NULL;

ALTER TABLE "edrNotes"
   ADD CONSTRAINT edrnotes_idemployee_fkey FOREIGN KEY (author_idemployee) 
      REFERENCES employee (idemployee) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE INDEX edrNotes_author_idemployee_idx
  ON "edrNotes"
  USING btree
  (author_idemployee);

ALTER TABLE edr ADD COLUMN last_changed date NOT NULL;
ALTER TABLE question_answer DROP COLUMN answer;
ALTER TABLE question_answer DROP COLUMN edr_idedr;

ALTER TABLE question_answer RENAME TO question;

CREATE TABLE answer(
  idanswer integer NOT NULL DEFAULT nextval('idanswer_seq'::regclass),
  answer text,
  question_idquestion integer NOT NULL,
  edr_idedr integer NOT NULL,
  CONSTRAINT answer_pkey PRIMARY KEY (idanswer),
  CONSTRAINT answer_edr_idedr_fkey FOREIGN KEY (edr_idedr)
      REFERENCES edr (idedr) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT answer_question_idquestion_fkey FOREIGN KEY (question_idquestion)
      REFERENCES question (idquestion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE answer
  OWNER TO comprofits;
GRANT ALL ON TABLE answer TO comprofits;

CREATE INDEX answer_edr_idedr_idx
  ON answer
  USING btree
  (edr_idedr);

CREATE INDEX answer_question_idquestion_idx
  ON answer
  USING btree
  (question_idquestion);
 