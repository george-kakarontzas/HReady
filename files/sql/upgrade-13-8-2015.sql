
ALTER TABLE department
	DROP CONSTRAINT department_company_idcompany_fkey;

DROP INDEX department_company_idcompany_idx;

ALTER TABLE employee
	ADD COLUMN division_iddivision integer;

ALTER TABLE department
	DROP COLUMN company_idcompany,
	ADD COLUMN division_iddivision integer;

ALTER TABLE division
	ADD COLUMN company_idcompany integer,
	ALTER COLUMN description TYPE text /* TYPE change - table: division original: character varying(140) new: text */;

ALTER TABLE employee
	ADD CONSTRAINT "division_iddivision_TO_division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);

ALTER TABLE department
	ADD CONSTRAINT department_division_iddivision_fkey FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);

ALTER TABLE division
	ADD CONSTRAINT "company_idcompany_TO_company" FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);

CREATE INDEX "fki_division_iddivision_TO_division" ON employee USING btree (division_iddivision);

CREATE INDEX fki_department_division_iddivision_fkey ON department USING btree (division_iddivision);

CREATE INDEX "fki_company_idcompany_TO_company" ON division USING btree (company_idcompany);
