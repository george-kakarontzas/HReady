ALTER TABLE "edrNotes" RENAME TO edrnotes;
ALTER TABLE edrnotes RENAME COLUMN note TO message;
ALTER TABLE edrnotes ADD COLUMN subject VARCHAR(80);
