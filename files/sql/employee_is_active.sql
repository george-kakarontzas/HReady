--
-- Add a boolean column on the employee databale
-- in order to set an employee as active or not
--

ALTER TABLE employee ADD is_active BOOLEAN NOT NULL DEFAULT TRUE;


--
-- Alter the view to include only those employess that are active
-- Only these should be able to login
--

CREATE OR REPLACE VIEW all_users AS 
         SELECT employee.username AS uname, employee.password AS pwd, employee.role AS rol
           FROM employee WHERE employee.is_active = TRUE
UNION ALL 
         SELECT job_applicant.username AS uname, job_applicant.password AS pwd, 'APPLICANT'::character varying AS rol
           FROM job_applicant;