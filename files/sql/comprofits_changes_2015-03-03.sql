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

CREATE SEQUENCE idquestioncat_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 48
  CACHE 1;
ALTER TABLE idquestioncat_seq
  OWNER TO comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO postgres;

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

CREATE TABLE question_category(
  idquestioncat integer NOT NULL DEFAULT nextval('idquestioncat_seq'::regclass),
  category text,
  CONSTRAINT questioncat_pkey PRIMARY KEY (idquestioncat)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE question_category
  OWNER TO comprofits;
GRANT ALL ON TABLE question_category TO comprofits;

ALTER TABLE question RENAME COLUMN question_category TO question_category_idquestioncat;
ALTER TABLE question ALTER COLUMN question_category_idquestioncat SET NOT NULL;

ALTER TABLE question add CONSTRAINT question_category_idquestioncat_fkey FOREIGN KEY (question_category_idquestioncat) REFERENCES question_category (idquestioncat) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE INDEX answer_edr_idedr_idx
  ON answer
  USING btree
  (edr_idedr);

CREATE INDEX answer_question_idquestion_idx
  ON answer
  USING btree
  (question_idquestion);

CREATE INDEX question_category_idquestioncat_idx
  ON question
  USING btree
  (question_category_idquestioncat);
 