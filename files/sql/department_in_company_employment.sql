-- 
-- Add department name in in_company_employment for past jobs
--


ALTER TABLE in_company_employment
ADD COLUMN department_name VARCHAR;