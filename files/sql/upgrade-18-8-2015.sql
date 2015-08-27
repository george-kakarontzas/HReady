
ALTER TABLE job DROP CONSTRAINT job_business_area_idbusiness_area_fkey;

ALTER TABLE job
  ADD CONSTRAINT job_business_area_idbusiness_area_fkey FOREIGN KEY (business_area_idbusiness_area)
      REFERENCES department (iddepartment) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;