-- 
-- Employee social security number should be unique in the database
-- Identity card should be too, except that it allows null values
-- therefore making it unique at the database level will not work
-- for the application
--



ALTER TABLE employee
  ADD CONSTRAINT social_security_number_unique UNIQUE (social_security_number);

