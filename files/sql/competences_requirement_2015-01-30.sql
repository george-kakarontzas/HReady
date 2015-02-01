ALTER TABLE competences_requirement DROP COLUMN weight;
ALTER TABLE competences_requirement ADD COLUMN weight numeric(3,2);
ALTER TABLE competences_requirement DROP COLUMN importance numeric(3,2);
