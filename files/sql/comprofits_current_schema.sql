--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: comprofits; Type: DATABASE; Schema: -; Owner: comprofits
--

CREATE DATABASE comprofits WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE comprofits OWNER TO comprofits;

\connect comprofits

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: employee_idemployee_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE employee_idemployee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE employee_idemployee_seq OWNER TO comprofits;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE employee (
    idemployee integer DEFAULT nextval('employee_idemployee_seq'::regclass) NOT NULL,
    identity_card_number character varying(20) DEFAULT NULL::character varying,
    social_security_number character varying(50) NOT NULL,
    first_name character varying(45) NOT NULL,
    last_name character varying(45) NOT NULL,
    gender integer,
    province character varying(45) DEFAULT NULL::character varying,
    address character varying(100) NOT NULL,
    postal_code character varying(20) NOT NULL,
    city character varying(45) NOT NULL,
    country character varying(45) NOT NULL,
    date_of_birth date NOT NULL,
    phone_private character varying(45) DEFAULT NULL::character varying,
    phone_mobile character varying(45) NOT NULL,
    email character varying(100) NOT NULL,
    photo_path character varying(255) DEFAULT NULL::character varying,
    username character varying(20) NOT NULL,
    password character varying(256) NOT NULL,
    marital_status character(1) DEFAULT NULL::bpchar,
    number_of_children smallint,
    department_iddepartment integer,
    current_in_company_employment_id integer,
    role character varying(50),
    is_active boolean DEFAULT true NOT NULL,
    division_iddivision integer,
    cv_path character varying(255)
);


ALTER TABLE employee OWNER TO comprofits;

--
-- Name: job_applicant_idjob_applicant_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE job_applicant_idjob_applicant_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_applicant_idjob_applicant_seq OWNER TO comprofits;

--
-- Name: job_applicant; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_applicant (
    idjob_applicant integer DEFAULT nextval('job_applicant_idjob_applicant_seq'::regclass) NOT NULL,
    date_of_birth date NOT NULL,
    first_name character varying(45) NOT NULL,
    last_name character varying(45) NOT NULL,
    gender integer,
    address character varying(100) NOT NULL,
    postal_code character varying(20) NOT NULL,
    city character varying(45) NOT NULL,
    country character varying(45) NOT NULL,
    province character varying(45) DEFAULT NULL::character varying,
    phone_private character varying(45) DEFAULT NULL::character varying,
    phone_mobile character varying(45) NOT NULL,
    email character varying(100) NOT NULL,
    photo_path character varying(255) DEFAULT NULL::character varying,
    username character varying(20) NOT NULL,
    password character varying(256) NOT NULL,
    marital_status character(1) DEFAULT NULL::bpchar,
    number_of_children smallint,
    present_idapplicant_professional_experience_record integer
);


ALTER TABLE job_applicant OWNER TO comprofits;

--
-- Name: all_users; Type: VIEW; Schema: public; Owner: comprofits
--

CREATE VIEW all_users AS
 SELECT employee.username AS uname,
    employee.password AS pwd,
    employee.role AS rol
   FROM employee
  WHERE (employee.is_active = true)
UNION ALL
 SELECT job_applicant.username AS uname,
    job_applicant.password AS pwd,
    'APPLICANT'::character varying AS rol
   FROM job_applicant;


ALTER TABLE all_users OWNER TO comprofits;

--
-- Name: idanswer_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idanswer_seq
    START WITH 48
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idanswer_seq OWNER TO comprofits;

--
-- Name: answer; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE answer (
    idanswer integer DEFAULT nextval('idanswer_seq'::regclass) NOT NULL,
    answer text,
    question_idquestion integer NOT NULL,
    edr_idedr integer NOT NULL
);


ALTER TABLE answer OWNER TO comprofits;

--
-- Name: idapplicant_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idapplicant_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idapplicant_competence_assessment_seq OWNER TO comprofits;

--
-- Name: applicant_competence_assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE applicant_competence_assessment (
    idapplicant_competence_assessment integer DEFAULT nextval('idapplicant_competence_assessment_seq'::regclass) NOT NULL,
    value integer,
    competence_id integer NOT NULL,
    job_application_id integer NOT NULL
);


ALTER TABLE applicant_competence_assessment OWNER TO comprofits;

--
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE xperience_record_idapplicant_professional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE xperience_record_idapplicant_professional_experience_record_seq OWNER TO comprofits;

--
-- Name: applicant_professional_experience_record; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE applicant_professional_experience_record (
    idapplicant_professional_experience_record integer DEFAULT nextval('xperience_record_idapplicant_professional_experience_record_seq'::regclass) NOT NULL,
    date_started date NOT NULL,
    date_finished date NOT NULL,
    company character varying(45) NOT NULL,
    role character varying(255) NOT NULL,
    job_title character varying(45) DEFAULT NULL::character varying,
    field_of_work character varying(45) DEFAULT NULL::character varying,
    place_of_employment character varying(45) DEFAULT NULL::character varying,
    immediate_manager character varying(100) DEFAULT NULL::character varying,
    business_area character varying(45) DEFAULT NULL::character varying,
    division character varying(45) DEFAULT NULL::character varying,
    job_applicant_idjob_applicant integer NOT NULL
);


ALTER TABLE applicant_professional_experience_record OWNER TO comprofits;

--
-- Name: idapplicant_study_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idapplicant_study_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idapplicant_study_record_seq OWNER TO comprofits;

--
-- Name: applicant_study_record; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE applicant_study_record (
    idstudy_record integer DEFAULT nextval('idapplicant_study_record_seq'::regclass) NOT NULL,
    title character varying(45) DEFAULT NULL::character varying,
    title_type integer,
    institution character varying(45) DEFAULT NULL::character varying,
    date_started date,
    date_acquired date,
    job_applicant_idjob_applicant integer NOT NULL
);


ALTER TABLE applicant_study_record OWNER TO comprofits;

--
-- Name: idassessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idassessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idassessment_seq OWNER TO comprofits;

--
-- Name: assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE assessment (
    idassessment integer DEFAULT nextval('idassessment_seq'::regclass) NOT NULL,
    date_created date,
    assessee_idemployee integer NOT NULL,
    immediate_manager_idemployee integer NOT NULL,
    colleague1_idemployee integer NOT NULL,
    colleague2_idemployee integer NOT NULL,
    colleague3_idemployee integer NOT NULL,
    deadline date NOT NULL,
    completed boolean,
    conclusion text,
    issue_date date
);


ALTER TABLE assessment OWNER TO comprofits;

--
-- Name: business_area_idbusiness_area_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE business_area_idbusiness_area_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE business_area_idbusiness_area_seq OWNER TO comprofits;

--
-- Name: business_area; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE business_area (
    idbusiness_area integer DEFAULT nextval('business_area_idbusiness_area_seq'::regclass) NOT NULL,
    name character varying(255) DEFAULT NULL::character varying,
    description character varying(255) DEFAULT NULL::character varying,
    division_iddivision integer NOT NULL
);


ALTER TABLE business_area OWNER TO comprofits;

--
-- Name: company_idcompany_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE company_idcompany_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE company_idcompany_seq OWNER TO comprofits;

--
-- Name: company; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE company (
    idcompany integer DEFAULT nextval('company_idcompany_seq'::regclass) NOT NULL,
    company_name1 character varying(100) NOT NULL,
    company_name2 character varying(100) DEFAULT NULL::character varying,
    company_address1 character varying(100) NOT NULL,
    company_address2 character varying(100) DEFAULT NULL::character varying,
    postal_code character varying(30) DEFAULT NULL::character varying,
    province character varying(45) DEFAULT NULL::character varying,
    country character varying(45) DEFAULT NULL::character varying,
    phone_number character varying(45) NOT NULL,
    e_mail character varying(100) NOT NULL,
    website character varying(100) NOT NULL
);


ALTER TABLE company OWNER TO comprofits;

--
-- Name: COLUMN company.idcompany; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.idcompany IS 'The company ID';


--
-- Name: COLUMN company.company_name1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name1 IS 'First line of company address\n';


--
-- Name: COLUMN company.company_name2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name2 IS 'Second line of company name (can be empty)';


--
-- Name: COLUMN company.company_address1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address1 IS 'First line of company address';


--
-- Name: COLUMN company.company_address2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address2 IS 'Second line of company address (can be empty)';


--
-- Name: COLUMN company.postal_code; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.postal_code IS 'Postal code of company address (can be empty)';


--
-- Name: COLUMN company.province; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.province IS 'Province of company address (can be empty)';


--
-- Name: COLUMN company.country; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.country IS 'Country (can be empty)';


--
-- Name: COLUMN company.phone_number; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.phone_number IS 'Phone number of company';


--
-- Name: COLUMN company.e_mail; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.e_mail IS 'E-mail of company';


--
-- Name: COLUMN company.website; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.website IS 'Company website.';


--
-- Name: idcompetence_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetence_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idcompetence_seq OWNER TO comprofits;

--
-- Name: competence; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competence (
    idcompetence integer DEFAULT nextval('idcompetence_seq'::regclass) NOT NULL,
    competence_name character varying(100) DEFAULT NULL::character varying,
    parent_id integer
);


ALTER TABLE competence OWNER TO comprofits;

--
-- Name: idcompetence_goal_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetence_goal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idcompetence_goal_seq OWNER TO comprofits;

--
-- Name: competence_goal; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competence_goal (
    idcompetence_goal integer DEFAULT nextval('idcompetence_goal_seq'::regclass) NOT NULL,
    next_year_goal_value integer,
    comments character varying(255),
    edr_idedr integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE competence_goal OWNER TO comprofits;

--
-- Name: idcompetences_requirement; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetences_requirement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idcompetences_requirement OWNER TO comprofits;

--
-- Name: competences_requirement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competences_requirement (
    idcompetences_requirement integer DEFAULT nextval('idcompetences_requirement'::regclass) NOT NULL,
    weight smallint NOT NULL,
    importance smallint NOT NULL,
    job_idjob integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE competences_requirement OWNER TO comprofits;

--
-- Name: idcurrent_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcurrent_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idcurrent_competence_assessment_seq OWNER TO comprofits;

--
-- Name: current_competence_assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE current_competence_assessment (
    idcurrent_competence_assessment integer DEFAULT nextval('idcurrent_competence_assessment_seq'::regclass) NOT NULL,
    assessment integer NOT NULL,
    employee_idemployee integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE current_competence_assessment OWNER TO comprofits;

--
-- Name: iddepartment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE iddepartment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE iddepartment_seq OWNER TO comprofits;

--
-- Name: department; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE department (
    iddepartment integer DEFAULT nextval('iddepartment_seq'::regclass) NOT NULL,
    department_name character varying(255) DEFAULT NULL::character varying,
    head_of_department_idemployee integer,
    division_iddivision integer
);


ALTER TABLE department OWNER TO comprofits;

--
-- Name: division_iddivision_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE division_iddivision_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE division_iddivision_seq OWNER TO comprofits;

--
-- Name: division; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE division (
    iddivision integer DEFAULT nextval('division_iddivision_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    description text DEFAULT NULL::character varying,
    head_of_division_employee integer,
    company_idcompany integer
);


ALTER TABLE division OWNER TO comprofits;

--
-- Name: idedr_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idedr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idedr_seq OWNER TO comprofits;

--
-- Name: edr; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE edr (
    idedr integer DEFAULT nextval('idedr_seq'::regclass) NOT NULL,
    year character varying(45) DEFAULT NULL::character varying,
    status integer,
    verdict text DEFAULT NULL::character varying,
    reviewed_employee_idemployee integer NOT NULL,
    immediate_manager_idemployee integer NOT NULL,
    previous_edr_idedr integer,
    last_changed date NOT NULL,
    head_of_department_idemployee integer NOT NULL
);


ALTER TABLE edr OWNER TO comprofits;

--
-- Name: edrHistory; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE "edrHistory" (
    note character varying(255)[],
    date date,
    "time" timestamp without time zone,
    idemployee integer,
    idedr integer,
    "idedrHistory" integer NOT NULL
);


ALTER TABLE "edrHistory" OWNER TO comprofits;

--
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE "edrHistory_idedrHistory_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "edrHistory_idedrHistory_seq" OWNER TO comprofits;

--
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "edrHistory_idedrHistory_seq" OWNED BY "edrHistory"."idedrHistory";


--
-- Name: edrNotes_idnotes_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE "edrNotes_idnotes_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "edrNotes_idnotes_seq" OWNER TO comprofits;

--
-- Name: edrnotes; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE edrnotes (
    idnote integer DEFAULT nextval('"edrNotes_idnotes_seq"'::regclass) NOT NULL,
    idedr integer,
    message character varying(255) NOT NULL,
    date date,
    author_idemployee integer NOT NULL,
    subject character varying(80)
);


ALTER TABLE edrnotes OWNER TO comprofits;

--
-- Name: idemployee_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idemployee_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idemployee_competence_assessment_seq OWNER TO comprofits;

--
-- Name: employee_competence_assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE employee_competence_assessment (
    idemployee_competence_assessment integer DEFAULT nextval('idemployee_competence_assessment_seq'::regclass) NOT NULL,
    assessment integer,
    assessor_idemployee integer NOT NULL,
    assessment_idassessment integer NOT NULL,
    competence_idcompetence integer NOT NULL,
    statement_idstatement integer NOT NULL
);


ALTER TABLE employee_competence_assessment OWNER TO comprofits;

--
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE essional_experience_record_idprofessional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE essional_experience_record_idprofessional_experience_record_seq OWNER TO comprofits;

--
-- Name: idin_company_employment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idin_company_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idin_company_employment_seq OWNER TO comprofits;

--
-- Name: idjob_advertisement_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_advertisement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idjob_advertisement_seq OWNER TO comprofits;

--
-- Name: idjob_application_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_application_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idjob_application_seq OWNER TO comprofits;

--
-- Name: idjob_study_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_study_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idjob_study_min_requirements_seq OWNER TO comprofits;

--
-- Name: idprofessional_experience_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idprofessional_experience_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idprofessional_experience_min_requirements_seq OWNER TO comprofits;

--
-- Name: idquestion_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idquestion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idquestion_seq OWNER TO comprofits;

--
-- Name: idquestioncat_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idquestioncat_seq
    START WITH 48
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idquestioncat_seq OWNER TO comprofits;

--
-- Name: idstudy_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idstudy_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE idstudy_record_seq OWNER TO comprofits;

--
-- Name: importHistory; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE "importHistory" (
    date date,
    "time" timestamp without time zone,
    idemployee integer,
    idedr integer,
    comment character varying,
    file bytea,
    "idimportHistory" integer NOT NULL
);


ALTER TABLE "importHistory" OWNER TO comprofits;

--
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE "importHistory_idimportHistory_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "importHistory_idimportHistory_seq" OWNER TO comprofits;

--
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "importHistory_idimportHistory_seq" OWNED BY "importHistory"."idimportHistory";


--
-- Name: in_company_employment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE in_company_employment (
    idin_company_employment integer DEFAULT nextval('idin_company_employment_seq'::regclass) NOT NULL,
    start_date date,
    end_date date,
    job_idjob integer NOT NULL,
    employee_idemployee integer NOT NULL,
    department_name character varying
);


ALTER TABLE in_company_employment OWNER TO comprofits;

--
-- Name: job_idjob_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE job_idjob_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job_idjob_seq OWNER TO comprofits;

--
-- Name: job; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job (
    idjob integer DEFAULT nextval('job_idjob_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    job_description character varying(45) NOT NULL,
    organisational_position_idorganisational_position integer,
    reporting_to_idemployee integer,
    business_area_idbusiness_area integer,
    place_employment_idplace_employment integer,
    job_status boolean
);


ALTER TABLE job OWNER TO comprofits;

--
-- Name: COLUMN job.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job.job_title IS 'The title of  the job';


--
-- Name: job_advertisement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_advertisement (
    idjob_advertisement integer DEFAULT nextval('idjob_advertisement_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    fields_of_responsibility text NOT NULL,
    job_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);


ALTER TABLE job_advertisement OWNER TO comprofits;

--
-- Name: COLUMN job_advertisement.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job_advertisement.job_title IS 'The title of  the job';


--
-- Name: job_application; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_application (
    idjob_application integer DEFAULT nextval('idjob_application_seq'::regclass) NOT NULL,
    date date,
    job_applicant_idjob_applicant integer NOT NULL,
    job_advertisement_idjob_advertisement integer NOT NULL
);


ALTER TABLE job_application OWNER TO comprofits;

--
-- Name: job_study_min_requirements; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_study_min_requirements (
    idjob_study_min_requirements integer DEFAULT nextval('idjob_study_min_requirements_seq'::regclass) NOT NULL,
    required_title_type integer,
    job_idjob integer NOT NULL
);


ALTER TABLE job_study_min_requirements OWNER TO comprofits;

--
-- Name: organisational_position_idorganisational_position_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE organisational_position_idorganisational_position_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE organisational_position_idorganisational_position_seq OWNER TO comprofits;

--
-- Name: organisational_position; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE organisational_position (
    idorganisational_position integer DEFAULT nextval('organisational_position_idorganisational_position_seq'::regclass) NOT NULL,
    organisational_position_name character varying(45) NOT NULL,
    organisational_position_description character varying(255) NOT NULL,
    company_idcompany integer NOT NULL
);


ALTER TABLE organisational_position OWNER TO comprofits;

--
-- Name: place_employment_idplace_employment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE place_employment_idplace_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE place_employment_idplace_employment_seq OWNER TO comprofits;

--
-- Name: place_employment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE place_employment (
    idplace_employment integer DEFAULT nextval('place_employment_idplace_employment_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    address character varying(140) DEFAULT NULL::character varying,
    postal_code character varying(20) DEFAULT NULL::character varying,
    city character varying(45) DEFAULT NULL::character varying,
    province character varying(45) DEFAULT NULL::character varying,
    country character varying(45) DEFAULT NULL::character varying
);


ALTER TABLE place_employment OWNER TO comprofits;

--
-- Name: professional_experience_min_requirements; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE professional_experience_min_requirements (
    idprofessional_experience_min_requirements integer DEFAULT nextval('idprofessional_experience_min_requirements_seq'::regclass) NOT NULL,
    required_experience_years integer NOT NULL,
    required_prof_experience_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE professional_experience_min_requirements OWNER TO comprofits;

--
-- Name: professional_experience_record; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE professional_experience_record (
    idprofessional_experience_record integer DEFAULT nextval('essional_experience_record_idprofessional_experience_record_seq'::regclass) NOT NULL,
    date_started date NOT NULL,
    date_finished date NOT NULL,
    company character varying(45) NOT NULL,
    role character varying(255) NOT NULL,
    job_title character varying(45) DEFAULT NULL::character varying,
    field_of_work character varying(45) DEFAULT NULL::character varying,
    place_of_employment character varying(45) DEFAULT NULL::character varying,
    immediate_manager character varying(100) DEFAULT NULL::character varying,
    business_area character varying(45) DEFAULT NULL::character varying,
    division character varying(100),
    employee_idemployee integer NOT NULL
);


ALTER TABLE professional_experience_record OWNER TO comprofits;

--
-- Name: question; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE question (
    idquestion integer DEFAULT nextval('idquestion_seq'::regclass) NOT NULL,
    question_category_idquestioncat integer NOT NULL,
    question text
);


ALTER TABLE question OWNER TO comprofits;

--
-- Name: question_category; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE question_category (
    idquestioncat integer DEFAULT nextval('idquestioncat_seq'::regclass) NOT NULL,
    category text
);


ALTER TABLE question_category OWNER TO comprofits;

--
-- Name: statement_idstatement_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE statement_idstatement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE statement_idstatement_seq OWNER TO comprofits;

--
-- Name: statement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE statement (
    idstatement integer DEFAULT nextval('statement_idstatement_seq'::regclass) NOT NULL,
    statement_text text NOT NULL,
    competence_id integer NOT NULL
);


ALTER TABLE statement OWNER TO comprofits;

--
-- Name: study_record; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE study_record (
    idstudy_record integer DEFAULT nextval('idstudy_record_seq'::regclass) NOT NULL,
    title character varying(45) DEFAULT NULL::character varying,
    title_type integer,
    institution character varying(45) DEFAULT NULL::character varying,
    date_started date,
    date_acquired date,
    employee_idemployee integer NOT NULL
);


ALTER TABLE study_record OWNER TO comprofits;

--
-- Name: idedrHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory" ALTER COLUMN "idedrHistory" SET DEFAULT nextval('"edrHistory_idedrHistory_seq"'::regclass);


--
-- Name: idimportHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory" ALTER COLUMN "idimportHistory" SET DEFAULT nextval('"importHistory_idimportHistory_seq"'::regclass);


--
-- Name: answer_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (idanswer);


--
-- Name: applicant_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment_pkey PRIMARY KEY (idapplicant_competence_assessment);


--
-- Name: applicant_professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT applicant_professional_experience_record_pkey PRIMARY KEY (idapplicant_professional_experience_record);


--
-- Name: applicant_study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT applicant_study_record_pkey PRIMARY KEY (idstudy_record);


--
-- Name: assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (idassessment);


--
-- Name: business_area_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT business_area_pkey PRIMARY KEY (idbusiness_area);


--
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (idcompany);


--
-- Name: competence_goal_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_pkey PRIMARY KEY (idcompetence_goal);


--
-- Name: competence_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (idcompetence);


--
-- Name: competences_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_pkey PRIMARY KEY (idcompetences_requirement);


--
-- Name: current_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_pkey PRIMARY KEY (idcurrent_competence_assessment);


--
-- Name: department_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (iddepartment);


--
-- Name: division_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY division
    ADD CONSTRAINT division_pkey PRIMARY KEY (iddivision);


--
-- Name: edrNotes_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT "edrNotes_pkey" PRIMARY KEY (idnote);


--
-- Name: edr_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_pkey PRIMARY KEY (idedr);


--
-- Name: employee_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_pkey PRIMARY KEY (idemployee_competence_assessment);


--
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (idemployee);


--
-- Name: idedrHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "idedrHistory_pkey" PRIMARY KEY ("idedrHistory");


--
-- Name: idimportHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "idimportHistory_pkey" PRIMARY KEY ("idimportHistory");


--
-- Name: in_company_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_pkey PRIMARY KEY (idin_company_employment);


--
-- Name: job_advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_pkey PRIMARY KEY (idjob_advertisement);


--
-- Name: job_applicant_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_pkey PRIMARY KEY (idjob_applicant);


--
-- Name: job_applicant_username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_username_unique_constraint UNIQUE (username);


--
-- Name: job_application_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_pkey PRIMARY KEY (idjob_application);


--
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (idjob);


--
-- Name: job_study_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_pkey PRIMARY KEY (idjob_study_min_requirements);


--
-- Name: organisational_position_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_pkey PRIMARY KEY (idorganisational_position);


--
-- Name: place_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY place_employment
    ADD CONSTRAINT place_employment_pkey PRIMARY KEY (idplace_employment);


--
-- Name: professional_experience_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_pkey PRIMARY KEY (idprofessional_experience_min_requirements);


--
-- Name: professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_pkey PRIMARY KEY (idprofessional_experience_record);


--
-- Name: question_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_answer_pkey PRIMARY KEY (idquestion);


--
-- Name: questioncat_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY question_category
    ADD CONSTRAINT questioncat_pkey PRIMARY KEY (idquestioncat);


--
-- Name: social_security_number_unique; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT social_security_number_unique UNIQUE (social_security_number);


--
-- Name: statement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement_pkey PRIMARY KEY (idstatement);


--
-- Name: study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_pkey PRIMARY KEY (idstudy_record);


--
-- Name: username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT username_unique_constraint UNIQUE (username);


--
-- Name: answer_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX answer_edr_idedr_idx ON answer USING btree (edr_idedr);


--
-- Name: answer_question_idquestion_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX answer_question_idquestion_idx ON answer USING btree (question_idquestion);


--
-- Name: ant_competence_assessment_job_application_idjob_application_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX ant_competence_assessment_job_application_idjob_application_idx ON applicant_competence_assessment USING btree (job_application_id);


--
-- Name: applicant_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_competence_assessment_competence_idcompetence_idx ON applicant_competence_assessment USING btree (competence_id);


--
-- Name: applicant_study_record_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_study_record_job_applicant_idjob_applicant_idx ON applicant_study_record USING btree (job_applicant_idjob_applicant);


--
-- Name: assessment_assessee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_assessee_idemployee_idx ON assessment USING btree (assessee_idemployee);


--
-- Name: assessment_colleague1_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague1_idemployee_idx ON assessment USING btree (colleague1_idemployee);


--
-- Name: assessment_colleague2_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague2_idemployee_idx ON assessment USING btree (colleague2_idemployee);


--
-- Name: assessment_colleague3_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague3_idemployee_idx ON assessment USING btree (colleague3_idemployee);


--
-- Name: assessment_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_immediate_manager_idemployee_idx ON assessment USING btree (immediate_manager_idemployee);


--
-- Name: business_area_division_iddivision_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX business_area_division_iddivision_idx ON business_area USING btree (division_iddivision);


--
-- Name: competence_goal_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_competence_idcompetence_idx ON competence_goal USING btree (competence_idcompetence);


--
-- Name: competence_goal_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_edr_idedr_idx ON competence_goal USING btree (edr_idedr);


--
-- Name: competence_parent_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_parent_id_idx ON competence USING btree (parent_id);


--
-- Name: competences_requirement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_competence_idcompetence_idx ON competences_requirement USING btree (competence_idcompetence);


--
-- Name: competences_requirement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_job_idjob_idx ON competences_requirement USING btree (job_idjob);


--
-- Name: current_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_competence_idcompetence_idx ON current_competence_assessment USING btree (competence_idcompetence);


--
-- Name: current_competence_assessment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_employee_idemployee_idx ON current_competence_assessment USING btree (employee_idemployee);


--
-- Name: department_head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX department_head_of_department_idemployee_idx ON department USING btree (head_of_department_idemployee);


--
-- Name: division_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX division_employee_idemployee_idx ON division USING btree (head_of_division_employee);


--
-- Name: edr_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_immediate_manager_idemployee_idx ON edr USING btree (immediate_manager_idemployee);


--
-- Name: edr_previous_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_previous_edr_idedr_idx ON edr USING btree (previous_edr_idedr);


--
-- Name: edr_reviewed_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_reviewed_employee_idemployee_idx ON edr USING btree (reviewed_employee_idemployee);


--
-- Name: edrnotes_author_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edrnotes_author_idemployee_idx ON edrnotes USING btree (author_idemployee);


--
-- Name: employee_competence_assessment_assessment_idassessment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessment_idassessment_idx ON employee_competence_assessment USING btree (assessment_idassessment);


--
-- Name: employee_competence_assessment_assessor_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessor_idemployee_idx ON employee_competence_assessment USING btree (assessor_idemployee);


--
-- Name: employee_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_competence_idcompetence_idx ON employee_competence_assessment USING btree (competence_idcompetence);


--
-- Name: employee_current_in_company_employment_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_current_in_company_employment_id_idx ON employee USING btree (current_in_company_employment_id);


--
-- Name: employee_department_iddepartment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_department_iddepartment_idx ON employee USING btree (department_iddepartment);


--
-- Name: fki_company_idcompany_TO_company; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX "fki_company_idcompany_TO_company" ON division USING btree (company_idcompany);


--
-- Name: fki_department_division_iddivision_fkey; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX fki_department_division_iddivision_fkey ON department USING btree (division_iddivision);


--
-- Name: fki_division_iddivision_TO_division; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX "fki_division_iddivision_TO_division" ON employee USING btree (division_iddivision);


--
-- Name: head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX head_of_department_idemployee_idx ON edr USING btree (head_of_department_idemployee);


--
-- Name: in_company_employment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_employee_idemployee_idx ON in_company_employment USING btree (employee_idemployee);


--
-- Name: in_company_employment_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_job_idjob_idx ON in_company_employment USING btree (job_idjob);


--
-- Name: job_advertisement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_advertisement_job_idjob_idx ON job_advertisement USING btree (job_idjob);


--
-- Name: job_application_job_advertisement_idjob_advertisement_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_advertisement_idjob_advertisement_idx ON job_application USING btree (job_advertisement_idjob_advertisement);


--
-- Name: job_application_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_applicant_idjob_applicant_idx ON job_application USING btree (job_applicant_idjob_applicant);


--
-- Name: job_business_area_idbusiness_area_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_business_area_idbusiness_area_idx ON job USING btree (business_area_idbusiness_area);


--
-- Name: job_organisational_position_idorganisational_position_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_organisational_position_idorganisational_position_idx ON job USING btree (organisational_position_idorganisational_position);


--
-- Name: job_place_employment_idplace_employment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_place_employment_idplace_employment_idx ON job USING btree (place_employment_idplace_employment);


--
-- Name: job_reporting_to_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_reporting_to_idemployee_idx ON job USING btree (reporting_to_idemployee);


--
-- Name: job_study_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_study_min_requirements_job_idjob_idx ON job_study_min_requirements USING btree (job_idjob);


--
-- Name: organisational_position_company_idcompany_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX organisational_position_company_idcompany_idx ON organisational_position USING btree (company_idcompany);


--
-- Name: pplicant_present_idapplicant_professional_experience_record_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX pplicant_present_idapplicant_professional_experience_record_idx ON job_applicant USING btree (present_idapplicant_professional_experience_record);


--
-- Name: professional_experience_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_min_requirements_job_idjob_idx ON professional_experience_min_requirements USING btree (job_idjob);


--
-- Name: professional_experience_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_employee_idemployee_idx ON professional_experience_record USING btree (employee_idemployee);


--
-- Name: professional_experience_record_job_applicant_idjob_applicant_id; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_job_applicant_idjob_applicant_id ON applicant_professional_experience_record USING btree (job_applicant_idjob_applicant);


--
-- Name: question_category_idquestioncat_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX question_category_idquestioncat_idx ON question USING btree (question_category_idquestioncat);


--
-- Name: statement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX statement_competence_idcompetence_idx ON statement USING btree (competence_id);


--
-- Name: study_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX study_record_employee_idemployee_idx ON study_record USING btree (employee_idemployee);


--
-- Name: answer_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- Name: answer_question_idquestion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_question_idquestion_fkey FOREIGN KEY (question_idquestion) REFERENCES question(idquestion);


--
-- Name: applicant_competence_assessment1; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment1 FOREIGN KEY (job_application_id) REFERENCES job_application(idjob_application);


--
-- Name: applicant_competence_assessment2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- Name: applicant_professional_experience_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT "applicant_professional_experience_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: applicant_study_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT "applicant_study_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: assessee_id_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "assessee_id_employee_TO_employee" FOREIGN KEY (assessee_idemployee) REFERENCES employee(idemployee);


--
-- Name: business_area_TO_Division; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT "business_area_TO_Division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- Name: colleague1_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague1_idemployee_TO_employee" FOREIGN KEY (colleague1_idemployee) REFERENCES employee(idemployee);


--
-- Name: colleague2_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague2_idemployee_TO_employee" FOREIGN KEY (colleague2_idemployee) REFERENCES employee(idemployee);


--
-- Name: colleague3_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague3_idemployee_TO_employee" FOREIGN KEY (colleague3_idemployee) REFERENCES employee(idemployee);


--
-- Name: company_idcompany_TO_company; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "company_idcompany_TO_company" FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- Name: competence_goal_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: competence_goal_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- Name: competence_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES competence(idcompetence);


--
-- Name: competences_requirement_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: current_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: current_in_company_employment_id; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT current_in_company_employment_id FOREIGN KEY (current_in_company_employment_id) REFERENCES in_company_employment(idin_company_employment);


--
-- Name: department_division_iddivision_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_division_iddivision_fkey FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- Name: division_iddivision_TO_division; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "division_iddivision_TO_division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- Name: edrHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- Name: edrHistory.employee_idEmployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.employee_idEmployee_fkey" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- Name: edrNotes.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT "edrNotes.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- Name: edr_previous_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_previous_edr_idedr_fkey FOREIGN KEY (previous_edr_idedr) REFERENCES edr(idedr);


--
-- Name: edrnotes_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT edrnotes_idemployee_fkey FOREIGN KEY (author_idemployee) REFERENCES employee(idemployee);


--
-- Name: employee_competence_assessment_assessment_idassessment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessment_idassessment_fkey FOREIGN KEY (assessment_idassessment) REFERENCES assessment(idassessment);


--
-- Name: employee_competence_assessment_assessor_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessor_idemployee_fkey FOREIGN KEY (assessor_idemployee) REFERENCES employee(idemployee);


--
-- Name: employee_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: employee_competence_assessment_statement_idstatement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_statement_idstatement_fkey FOREIGN KEY (statement_idstatement) REFERENCES statement(idstatement);


--
-- Name: employee_department_iddepartment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_department_iddepartment_fkey FOREIGN KEY (department_iddepartment) REFERENCES department(iddepartment);


--
-- Name: employee_id_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT "employee_id_TO_employee" FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: head_of_department_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT "head_of_department_idemployee_TO_employee" FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- Name: head_of_department_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT head_of_department_idemployee_fkey FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- Name: head_of_division_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "head_of_division_employee_TO_employee" FOREIGN KEY (head_of_division_employee) REFERENCES employee(idemployee);


--
-- Name: immediate_manager_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "immediate_manager_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- Name: immediate_manager_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "immediate_manager_idemployee_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- Name: importHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- Name: importHistory.employee_idEmployee_fky; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.employee_idEmployee_fky" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- Name: in_company_employment_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: job_advertisement_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_applicant_present_idapplicant_professional_experience__fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_present_idapplicant_professional_experience__fkey FOREIGN KEY (present_idapplicant_professional_experience_record) REFERENCES applicant_professional_experience_record(idapplicant_professional_experience_record);


--
-- Name: job_application_job_advertisement_idjob_advertisement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_advertisement_idjob_advertisement_fkey FOREIGN KEY (job_advertisement_idjob_advertisement) REFERENCES job_advertisement(idjob_advertisement);


--
-- Name: job_application_job_applicant_idjob_applicant_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_applicant_idjob_applicant_fkey FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: job_business_area_idbusiness_area_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_business_area_idbusiness_area_fkey FOREIGN KEY (business_area_idbusiness_area) REFERENCES department(iddepartment);


--
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_reporting_to_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_reporting_to_idemployee_fkey FOREIGN KEY (reporting_to_idemployee) REFERENCES employee(idemployee);


--
-- Name: job_study_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: organisational_position_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- Name: organisational_position_id_TO_organisational_position; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "organisational_position_id_TO_organisational_position" FOREIGN KEY (organisational_position_idorganisational_position) REFERENCES organisational_position(idorganisational_position);


--
-- Name: place_employment_id_TO_place_employment; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "place_employment_id_TO_place_employment" FOREIGN KEY (place_employment_idplace_employment) REFERENCES place_employment(idplace_employment);


--
-- Name: professional_experience_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: professional_experience_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: question_category_idquestioncat_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_category_idquestioncat_fkey FOREIGN KEY (question_category_idquestioncat) REFERENCES question_category(idquestioncat);


--
-- Name: reviewed_employee_TO_empoyee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "reviewed_employee_TO_empoyee" FOREIGN KEY (reviewed_employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: statement2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- Name: study_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: employee_idemployee_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM comprofits;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO comprofits;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO postgres;


--
-- Name: employee; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee FROM PUBLIC;
REVOKE ALL ON TABLE employee FROM comprofits;
GRANT ALL ON TABLE employee TO comprofits;


--
-- Name: job_applicant_idjob_applicant_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM comprofits;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO comprofits;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO postgres;


--
-- Name: job_applicant; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_applicant FROM PUBLIC;
REVOKE ALL ON TABLE job_applicant FROM comprofits;
GRANT ALL ON TABLE job_applicant TO comprofits;


--
-- Name: idanswer_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idanswer_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idanswer_seq FROM comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO postgres;


--
-- Name: answer; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE answer FROM PUBLIC;
REVOKE ALL ON TABLE answer FROM comprofits;
GRANT ALL ON TABLE answer TO comprofits;


--
-- Name: idapplicant_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO postgres;


--
-- Name: applicant_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE applicant_competence_assessment FROM comprofits;
GRANT ALL ON TABLE applicant_competence_assessment TO comprofits;


--
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO comprofits;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO postgres;


--
-- Name: applicant_professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_professional_experience_record FROM comprofits;
GRANT ALL ON TABLE applicant_professional_experience_record TO comprofits;


--
-- Name: idapplicant_study_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO comprofits;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO postgres;


--
-- Name: applicant_study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_study_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_study_record FROM comprofits;
GRANT ALL ON TABLE applicant_study_record TO comprofits;


--
-- Name: idassessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idassessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idassessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idassessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idassessment_seq TO postgres;


--
-- Name: assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE assessment FROM PUBLIC;
REVOKE ALL ON TABLE assessment FROM comprofits;
GRANT ALL ON TABLE assessment TO comprofits;


--
-- Name: business_area_idbusiness_area_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM comprofits;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO comprofits;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO postgres;


--
-- Name: business_area; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE business_area FROM PUBLIC;
REVOKE ALL ON TABLE business_area FROM comprofits;
GRANT ALL ON TABLE business_area TO comprofits;


--
-- Name: company_idcompany_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE company_idcompany_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE company_idcompany_seq FROM comprofits;
GRANT ALL ON SEQUENCE company_idcompany_seq TO comprofits;
GRANT ALL ON SEQUENCE company_idcompany_seq TO postgres;


--
-- Name: company; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE company FROM PUBLIC;
REVOKE ALL ON TABLE company FROM comprofits;
GRANT ALL ON TABLE company TO comprofits;


--
-- Name: idcompetence_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcompetence_seq TO comprofits;
GRANT ALL ON SEQUENCE idcompetence_seq TO postgres;


--
-- Name: competence; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence FROM PUBLIC;
REVOKE ALL ON TABLE competence FROM comprofits;
GRANT ALL ON TABLE competence TO comprofits;


--
-- Name: idcompetence_goal_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO comprofits;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO postgres;


--
-- Name: competence_goal; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence_goal FROM PUBLIC;
REVOKE ALL ON TABLE competence_goal FROM comprofits;
GRANT ALL ON TABLE competence_goal TO comprofits;


--
-- Name: idcompetences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetences_requirement FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetences_requirement FROM comprofits;
GRANT ALL ON SEQUENCE idcompetences_requirement TO comprofits;
GRANT ALL ON SEQUENCE idcompetences_requirement TO postgres;


--
-- Name: competences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competences_requirement FROM PUBLIC;
REVOKE ALL ON TABLE competences_requirement FROM comprofits;
GRANT ALL ON TABLE competences_requirement TO comprofits;


--
-- Name: idcurrent_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO postgres;


--
-- Name: current_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE current_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE current_competence_assessment FROM comprofits;
GRANT ALL ON TABLE current_competence_assessment TO comprofits;


--
-- Name: iddepartment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE iddepartment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE iddepartment_seq FROM comprofits;
GRANT ALL ON SEQUENCE iddepartment_seq TO comprofits;
GRANT ALL ON SEQUENCE iddepartment_seq TO postgres;


--
-- Name: department; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE department FROM PUBLIC;
REVOKE ALL ON TABLE department FROM comprofits;
GRANT ALL ON TABLE department TO comprofits;


--
-- Name: division_iddivision_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE division_iddivision_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE division_iddivision_seq FROM comprofits;
GRANT ALL ON SEQUENCE division_iddivision_seq TO comprofits;
GRANT ALL ON SEQUENCE division_iddivision_seq TO postgres;


--
-- Name: division; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE division FROM PUBLIC;
REVOKE ALL ON TABLE division FROM comprofits;
GRANT ALL ON TABLE division TO comprofits;


--
-- Name: idedr_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idedr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idedr_seq FROM comprofits;
GRANT ALL ON SEQUENCE idedr_seq TO comprofits;
GRANT ALL ON SEQUENCE idedr_seq TO postgres;


--
-- Name: edr; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE edr FROM PUBLIC;
REVOKE ALL ON TABLE edr FROM comprofits;
GRANT ALL ON TABLE edr TO comprofits;


--
-- Name: idemployee_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO postgres;


--
-- Name: employee_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE employee_competence_assessment FROM comprofits;
GRANT ALL ON TABLE employee_competence_assessment TO comprofits;


--
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO comprofits;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO postgres;


--
-- Name: idin_company_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO comprofits;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO postgres;


--
-- Name: idjob_advertisement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO postgres;


--
-- Name: idjob_application_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_application_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_application_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_application_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_application_seq TO postgres;


--
-- Name: idjob_study_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO postgres;


--
-- Name: idprofessional_experience_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM comprofits;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO comprofits;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO postgres;


--
-- Name: idquestion_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idquestion_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestion_seq FROM comprofits;
GRANT ALL ON SEQUENCE idquestion_seq TO comprofits;
GRANT ALL ON SEQUENCE idquestion_seq TO postgres;


--
-- Name: idquestioncat_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idquestioncat_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestioncat_seq FROM comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO postgres;


--
-- Name: idstudy_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idstudy_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idstudy_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE idstudy_record_seq TO comprofits;
GRANT ALL ON SEQUENCE idstudy_record_seq TO postgres;


--
-- Name: in_company_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE in_company_employment FROM PUBLIC;
REVOKE ALL ON TABLE in_company_employment FROM comprofits;
GRANT ALL ON TABLE in_company_employment TO comprofits;


--
-- Name: job_idjob_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_idjob_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_idjob_seq FROM comprofits;
GRANT ALL ON SEQUENCE job_idjob_seq TO comprofits;
GRANT ALL ON SEQUENCE job_idjob_seq TO postgres;


--
-- Name: job; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job FROM PUBLIC;
REVOKE ALL ON TABLE job FROM comprofits;
GRANT ALL ON TABLE job TO comprofits;


--
-- Name: job_advertisement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_advertisement FROM PUBLIC;
REVOKE ALL ON TABLE job_advertisement FROM comprofits;
GRANT ALL ON TABLE job_advertisement TO comprofits;


--
-- Name: job_application; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_application FROM PUBLIC;
REVOKE ALL ON TABLE job_application FROM comprofits;
GRANT ALL ON TABLE job_application TO comprofits;


--
-- Name: job_study_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_study_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE job_study_min_requirements FROM comprofits;
GRANT ALL ON TABLE job_study_min_requirements TO comprofits;


--
-- Name: organisational_position_idorganisational_position_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM comprofits;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO comprofits;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO postgres;


--
-- Name: organisational_position; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE organisational_position FROM PUBLIC;
REVOKE ALL ON TABLE organisational_position FROM comprofits;
GRANT ALL ON TABLE organisational_position TO comprofits;


--
-- Name: place_employment_idplace_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM comprofits;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO comprofits;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO postgres;


--
-- Name: place_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE place_employment FROM PUBLIC;
REVOKE ALL ON TABLE place_employment FROM comprofits;
GRANT ALL ON TABLE place_employment TO comprofits;


--
-- Name: professional_experience_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_min_requirements FROM comprofits;
GRANT ALL ON TABLE professional_experience_min_requirements TO comprofits;


--
-- Name: professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_record FROM comprofits;
GRANT ALL ON TABLE professional_experience_record TO comprofits;


--
-- Name: question; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE question FROM PUBLIC;
REVOKE ALL ON TABLE question FROM comprofits;
GRANT ALL ON TABLE question TO comprofits;


--
-- Name: question_category; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE question_category FROM PUBLIC;
REVOKE ALL ON TABLE question_category FROM comprofits;
GRANT ALL ON TABLE question_category TO comprofits;


--
-- Name: statement_idstatement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM comprofits;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO comprofits;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO postgres;


--
-- Name: statement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE statement FROM PUBLIC;
REVOKE ALL ON TABLE statement FROM comprofits;
GRANT ALL ON TABLE statement TO comprofits;


--
-- Name: study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE study_record FROM PUBLIC;
REVOKE ALL ON TABLE study_record FROM comprofits;
GRANT ALL ON TABLE study_record TO comprofits;

--
-- Create default Admin user 
--

INSERT INTO employee (idemployee, identity_card_number, social_security_number, first_name, last_name, gender, province, address, postal_code, city, country, date_of_birth, phone_private, phone_mobile, email, photo_path, username, password, marital_status, number_of_children, department_iddepartment, current_in_company_employment_id, role, is_active, division_iddivision, cv_path) VALUES (1, 123456789, 123456789, 'John', 'Doe', 1, 'Rhineland-Palatinate', 'Hoelderlinstr. 3', 57076, 'Siegen', 'DE', '1900-01-01', 123456789, 123456789, 'webmaster@comprofits.eu', NULL, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'M', NULL, NULL, NULL, 'administrator', 't', NULL, NULL);

--
-- PostgreSQL database dump complete
--

