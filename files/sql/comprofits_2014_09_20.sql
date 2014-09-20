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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: employee_idemployee_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE employee_idemployee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_idemployee_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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
    password character varying(65) NOT NULL,
    marital_status character(1) DEFAULT NULL::bpchar,
    number_of_children smallint,
    department_iddepartment integer,
    current_in_company_employment_id integer,
    role character varying(50)
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: job_applicant_idjob_applicant_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE job_applicant_idjob_applicant_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_applicant_idjob_applicant_seq OWNER TO postgres;

--
-- Name: job_applicant; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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
    password character varying(65) NOT NULL,
    marital_status character(1) DEFAULT NULL::bpchar,
    number_of_children smallint,
    present_idapplicant_professional_experience_record integer
);


ALTER TABLE public.job_applicant OWNER TO postgres;

--
-- Name: all_users; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW all_users AS
         SELECT employee.username AS uname,
            employee.password AS pwd,
            employee.role AS rol
           FROM employee
UNION ALL
         SELECT job_applicant.username AS uname,
            job_applicant.password AS pwd,
            'APPLICANT'::character varying AS rol
           FROM job_applicant;


ALTER TABLE public.all_users OWNER TO postgres;

--
-- Name: idapplicant_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idapplicant_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idapplicant_competence_assessment_seq OWNER TO postgres;

--
-- Name: applicant_competence_assessment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE applicant_competence_assessment (
    idapplicant_competence_assessment integer DEFAULT nextval('idapplicant_competence_assessment_seq'::regclass) NOT NULL,
    value integer,
    competence_id integer NOT NULL,
    job_application_id integer NOT NULL
);


ALTER TABLE public.applicant_competence_assessment OWNER TO postgres;

--
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE xperience_record_idapplicant_professional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.xperience_record_idapplicant_professional_experience_record_seq OWNER TO postgres;

--
-- Name: applicant_professional_experience_record; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.applicant_professional_experience_record OWNER TO postgres;

--
-- Name: idapplicant_study_record_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idapplicant_study_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idapplicant_study_record_seq OWNER TO postgres;

--
-- Name: applicant_study_record; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.applicant_study_record OWNER TO postgres;

--
-- Name: idassessment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idassessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idassessment_seq OWNER TO postgres;

--
-- Name: assessment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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
    conclusion text
);


ALTER TABLE public.assessment OWNER TO postgres;

--
-- Name: business_area_idbusiness_area_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE business_area_idbusiness_area_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.business_area_idbusiness_area_seq OWNER TO postgres;

--
-- Name: business_area; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE business_area (
    idbusiness_area integer DEFAULT nextval('business_area_idbusiness_area_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    description character varying(10) DEFAULT NULL::character varying,
    division_iddivision integer NOT NULL
);


ALTER TABLE public.business_area OWNER TO postgres;

--
-- Name: company_idcompany_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE company_idcompany_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_idcompany_seq OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: COLUMN company.idcompany; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.idcompany IS 'The company ID';


--
-- Name: COLUMN company.company_name1; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.company_name1 IS 'First line of company address';


--
-- Name: COLUMN company.company_name2; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.company_name2 IS 'Second line of company name (can be empty)';


--
-- Name: COLUMN company.company_address1; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.company_address1 IS 'First line of company address';


--
-- Name: COLUMN company.company_address2; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.company_address2 IS 'Second line of company address (can be empty)';


--
-- Name: COLUMN company.postal_code; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.postal_code IS 'Postal code of company address (can be empty)';


--
-- Name: COLUMN company.province; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.province IS 'Province of company address (can be empty)';


--
-- Name: COLUMN company.country; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.country IS 'Country (can be empty)';


--
-- Name: COLUMN company.phone_number; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.phone_number IS 'Phone number of company';


--
-- Name: COLUMN company.e_mail; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.e_mail IS 'E-mail of company';


--
-- Name: COLUMN company.website; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN company.website IS 'Company website.';


--
-- Name: idcompetence_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idcompetence_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetence_seq OWNER TO postgres;

--
-- Name: competence; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE competence (
    idcompetence integer DEFAULT nextval('idcompetence_seq'::regclass) NOT NULL,
    competence_name character varying(100) DEFAULT NULL::character varying,
    parent_id integer
);


ALTER TABLE public.competence OWNER TO postgres;

--
-- Name: idcompetence_goal_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idcompetence_goal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetence_goal_seq OWNER TO postgres;

--
-- Name: competence_goal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE competence_goal (
    idcompetence_goal integer DEFAULT nextval('idcompetence_goal_seq'::regclass) NOT NULL,
    next_year_goal_value integer,
    comments character varying(255),
    edr_idedr integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.competence_goal OWNER TO postgres;

--
-- Name: idcompetences_requirement; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idcompetences_requirement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetences_requirement OWNER TO postgres;

--
-- Name: competences_requirement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE competences_requirement (
    idcompetences_requirement integer DEFAULT nextval('idcompetences_requirement'::regclass) NOT NULL,
    weight integer NOT NULL,
    job_idjob integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.competences_requirement OWNER TO postgres;

--
-- Name: idcurrent_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idcurrent_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcurrent_competence_assessment_seq OWNER TO postgres;

--
-- Name: current_competence_assessment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE current_competence_assessment (
    idcurrent_competence_assessment integer DEFAULT nextval('idcurrent_competence_assessment_seq'::regclass) NOT NULL,
    assessment integer NOT NULL,
    employee_idemployee integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.current_competence_assessment OWNER TO postgres;

--
-- Name: iddepartment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE iddepartment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.iddepartment_seq OWNER TO postgres;

--
-- Name: department; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE department (
    iddepartment integer DEFAULT nextval('iddepartment_seq'::regclass) NOT NULL,
    department_name character varying(45) DEFAULT NULL::character varying,
    company_idcompany integer NOT NULL,
    head_of_department_idemployee integer
);


ALTER TABLE public.department OWNER TO postgres;

--
-- Name: division_iddivision_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE division_iddivision_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.division_iddivision_seq OWNER TO postgres;

--
-- Name: division; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE division (
    iddivision integer DEFAULT nextval('division_iddivision_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    description character varying(140) DEFAULT NULL::character varying,
    head_of_division_employee integer
);


ALTER TABLE public.division OWNER TO postgres;

--
-- Name: idedr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idedr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idedr_seq OWNER TO postgres;

--
-- Name: edr; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE edr (
    idedr integer DEFAULT nextval('idedr_seq'::regclass) NOT NULL,
    year character varying(45) DEFAULT NULL::character varying,
    status integer,
    verdict character varying(45) DEFAULT NULL::character varying,
    reviewed_employee_idemployee integer NOT NULL,
    immediate_manager_idemployee integer NOT NULL,
    previous_edr_idedr integer
);


ALTER TABLE public.edr OWNER TO postgres;

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


ALTER TABLE public."edrHistory" OWNER TO comprofits;

--
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE "edrHistory_idedrHistory_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."edrHistory_idedrHistory_seq" OWNER TO comprofits;

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


ALTER TABLE public."edrNotes_idnotes_seq" OWNER TO comprofits;

--
-- Name: edrNotes; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE "edrNotes" (
    idnote integer DEFAULT nextval('"edrNotes_idnotes_seq"'::regclass) NOT NULL,
    idedr integer,
    note character varying(255) NOT NULL,
    date date
);


ALTER TABLE public."edrNotes" OWNER TO comprofits;

--
-- Name: idemployee_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idemployee_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idemployee_competence_assessment_seq OWNER TO postgres;

--
-- Name: employee_competence_assessment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employee_competence_assessment (
    idemployee_competence_assessment integer DEFAULT nextval('idemployee_competence_assessment_seq'::regclass) NOT NULL,
    assessment integer,
    assessor_idemployee integer NOT NULL,
    assessment_idassessment integer NOT NULL,
    competence_idcompetence integer NOT NULL,
    statement_idstatement integer NOT NULL
);


ALTER TABLE public.employee_competence_assessment OWNER TO postgres;

--
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essional_experience_record_idprofessional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.essional_experience_record_idprofessional_experience_record_seq OWNER TO postgres;

--
-- Name: idin_company_employment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idin_company_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idin_company_employment_seq OWNER TO postgres;

--
-- Name: idjob_advertisement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idjob_advertisement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_advertisement_seq OWNER TO postgres;

--
-- Name: idjob_application_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idjob_application_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_application_seq OWNER TO postgres;

--
-- Name: idjob_study_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idjob_study_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_study_min_requirements_seq OWNER TO postgres;

--
-- Name: idprofessional_experience_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idprofessional_experience_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idprofessional_experience_min_requirements_seq OWNER TO postgres;

--
-- Name: idquestion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idquestion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idquestion_seq OWNER TO postgres;

--
-- Name: idstudy_record_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE idstudy_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idstudy_record_seq OWNER TO postgres;

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


ALTER TABLE public."importHistory" OWNER TO comprofits;

--
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE "importHistory_idimportHistory_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."importHistory_idimportHistory_seq" OWNER TO comprofits;

--
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "importHistory_idimportHistory_seq" OWNED BY "importHistory"."idimportHistory";


--
-- Name: in_company_employment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE in_company_employment (
    idin_company_employment integer DEFAULT nextval('idin_company_employment_seq'::regclass) NOT NULL,
    start_date date,
    end_date date,
    job_idjob integer NOT NULL,
    employee_idemployee integer NOT NULL
);


ALTER TABLE public.in_company_employment OWNER TO postgres;

--
-- Name: job_idjob_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE job_idjob_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_idjob_seq OWNER TO postgres;

--
-- Name: job; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE job (
    idjob integer DEFAULT nextval('job_idjob_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    job_description character varying(45) NOT NULL,
    organisational_position_idorganisational_position integer,
    reporting_to_idemployee integer,
    business_area_idbusiness_area integer,
    place_employment_idplace_employment integer
);


ALTER TABLE public.job OWNER TO postgres;

--
-- Name: COLUMN job.job_title; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN job.job_title IS 'The title of  the job';


--
-- Name: job_advertisement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE job_advertisement (
    idjob_advertisement integer DEFAULT nextval('idjob_advertisement_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    fields_of_responsibility character varying(255) NOT NULL,
    job_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE public.job_advertisement OWNER TO postgres;

--
-- Name: COLUMN job_advertisement.job_title; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN job_advertisement.job_title IS 'The title of  the job';


--
-- Name: job_application; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE job_application (
    idjob_application integer DEFAULT nextval('idjob_application_seq'::regclass) NOT NULL,
    date date,
    job_applicant_idjob_applicant integer NOT NULL,
    job_advertisement_idjob_advertisement integer NOT NULL
);


ALTER TABLE public.job_application OWNER TO postgres;

--
-- Name: job_study_min_requirements; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE job_study_min_requirements (
    idjob_study_min_requirements integer DEFAULT nextval('idjob_study_min_requirements_seq'::regclass) NOT NULL,
    required_title_type integer,
    job_idjob integer NOT NULL
);


ALTER TABLE public.job_study_min_requirements OWNER TO postgres;

--
-- Name: organisational_position_idorganisational_position_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organisational_position_idorganisational_position_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organisational_position_idorganisational_position_seq OWNER TO postgres;

--
-- Name: organisational_position; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organisational_position (
    idorganisational_position integer DEFAULT nextval('organisational_position_idorganisational_position_seq'::regclass) NOT NULL,
    organisational_position_name character varying(45) NOT NULL,
    organisational_position_description character varying(255) NOT NULL,
    company_idcompany integer NOT NULL
);


ALTER TABLE public.organisational_position OWNER TO postgres;

--
-- Name: place_employment_idplace_employment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE place_employment_idplace_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_employment_idplace_employment_seq OWNER TO postgres;

--
-- Name: place_employment; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.place_employment OWNER TO postgres;

--
-- Name: professional_experience_min_requirements; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE professional_experience_min_requirements (
    idprofessional_experience_min_requirements integer DEFAULT nextval('idprofessional_experience_min_requirements_seq'::regclass) NOT NULL,
    required_experience_years integer NOT NULL,
    required_prof_experience_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE public.professional_experience_min_requirements OWNER TO postgres;

--
-- Name: professional_experience_record; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.professional_experience_record OWNER TO postgres;

--
-- Name: question-answer; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE "question-answer" (
    idquestion integer DEFAULT nextval('idquestion_seq'::regclass) NOT NULL,
    question_category integer,
    question text,
    answer text,
    edr_idedr integer NOT NULL
);


ALTER TABLE public."question-answer" OWNER TO comprofits;

--
-- Name: question_answer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE question_answer (
    idquestion integer DEFAULT nextval('idquestion_seq'::regclass) NOT NULL,
    question_category integer,
    question text,
    answer text,
    edr_idedr integer NOT NULL
);


ALTER TABLE public.question_answer OWNER TO postgres;

--
-- Name: statement_idstatement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE statement_idstatement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.statement_idstatement_seq OWNER TO postgres;

--
-- Name: statement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE statement (
    idstatement integer DEFAULT nextval('statement_idstatement_seq'::regclass) NOT NULL,
    statement_text text NOT NULL,
    competence_id integer NOT NULL
);


ALTER TABLE public.statement OWNER TO postgres;

--
-- Name: study_record; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.study_record OWNER TO postgres;

--
-- Name: idedrHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory" ALTER COLUMN "idedrHistory" SET DEFAULT nextval('"edrHistory_idedrHistory_seq"'::regclass);


--
-- Name: idimportHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory" ALTER COLUMN "idimportHistory" SET DEFAULT nextval('"importHistory_idimportHistory_seq"'::regclass);


--
-- Name: applicant_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment_pkey PRIMARY KEY (idapplicant_competence_assessment);


--
-- Name: applicant_professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT applicant_professional_experience_record_pkey PRIMARY KEY (idapplicant_professional_experience_record);


--
-- Name: applicant_study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT applicant_study_record_pkey PRIMARY KEY (idstudy_record);


--
-- Name: assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (idassessment);


--
-- Name: business_area_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT business_area_pkey PRIMARY KEY (idbusiness_area);


--
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (idcompany);


--
-- Name: competence_goal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_pkey PRIMARY KEY (idcompetence_goal);


--
-- Name: competence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (idcompetence);


--
-- Name: competences_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_pkey PRIMARY KEY (idcompetences_requirement);


--
-- Name: current_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_pkey PRIMARY KEY (idcurrent_competence_assessment);


--
-- Name: department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (iddepartment);


--
-- Name: division_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY division
    ADD CONSTRAINT division_pkey PRIMARY KEY (iddivision);


--
-- Name: edrNotes_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "edrNotes"
    ADD CONSTRAINT "edrNotes_pkey" PRIMARY KEY (idnote);


--
-- Name: edr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_pkey PRIMARY KEY (idedr);


--
-- Name: employee_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_pkey PRIMARY KEY (idemployee_competence_assessment);


--
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
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
-- Name: in_company_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_pkey PRIMARY KEY (idin_company_employment);


--
-- Name: job_advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_pkey PRIMARY KEY (idjob_advertisement);


--
-- Name: job_applicant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_pkey PRIMARY KEY (idjob_applicant);


--
-- Name: job_applicant_username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_username_unique_constraint UNIQUE (username);


--
-- Name: job_application_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_pkey PRIMARY KEY (idjob_application);


--
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (idjob);


--
-- Name: job_study_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_pkey PRIMARY KEY (idjob_study_min_requirements);


--
-- Name: organisational_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_pkey PRIMARY KEY (idorganisational_position);


--
-- Name: place_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY place_employment
    ADD CONSTRAINT place_employment_pkey PRIMARY KEY (idplace_employment);


--
-- Name: professional_experience_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_pkey PRIMARY KEY (idprofessional_experience_min_requirements);


--
-- Name: professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_pkey PRIMARY KEY (idprofessional_experience_record);


--
-- Name: question_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT question_answer_pkey PRIMARY KEY (idquestion);


--
-- Name: statement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement_pkey PRIMARY KEY (idstatement);


--
-- Name: study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_pkey PRIMARY KEY (idstudy_record);


--
-- Name: username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT username_unique_constraint UNIQUE (username);


--
-- Name: ant_competence_assessment_job_application_idjob_application_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX ant_competence_assessment_job_application_idjob_application_idx ON applicant_competence_assessment USING btree (job_application_id);


--
-- Name: applicant_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX applicant_competence_assessment_competence_idcompetence_idx ON applicant_competence_assessment USING btree (competence_id);


--
-- Name: applicant_study_record_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX applicant_study_record_job_applicant_idjob_applicant_idx ON applicant_study_record USING btree (job_applicant_idjob_applicant);


--
-- Name: assessment_assessee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX assessment_assessee_idemployee_idx ON assessment USING btree (assessee_idemployee);


--
-- Name: assessment_colleague1_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX assessment_colleague1_idemployee_idx ON assessment USING btree (colleague1_idemployee);


--
-- Name: assessment_colleague2_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX assessment_colleague2_idemployee_idx ON assessment USING btree (colleague2_idemployee);


--
-- Name: assessment_colleague3_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX assessment_colleague3_idemployee_idx ON assessment USING btree (colleague3_idemployee);


--
-- Name: assessment_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX assessment_immediate_manager_idemployee_idx ON assessment USING btree (immediate_manager_idemployee);


--
-- Name: business_area_division_iddivision_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX business_area_division_iddivision_idx ON business_area USING btree (division_iddivision);


--
-- Name: competence_goal_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX competence_goal_competence_idcompetence_idx ON competence_goal USING btree (competence_idcompetence);


--
-- Name: competence_goal_edr_idedr_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX competence_goal_edr_idedr_idx ON competence_goal USING btree (edr_idedr);


--
-- Name: competence_parent_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX competence_parent_id_idx ON competence USING btree (parent_id);


--
-- Name: competences_requirement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX competences_requirement_competence_idcompetence_idx ON competences_requirement USING btree (competence_idcompetence);


--
-- Name: competences_requirement_job_idjob_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX competences_requirement_job_idjob_idx ON competences_requirement USING btree (job_idjob);


--
-- Name: current_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX current_competence_assessment_competence_idcompetence_idx ON current_competence_assessment USING btree (competence_idcompetence);


--
-- Name: current_competence_assessment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX current_competence_assessment_employee_idemployee_idx ON current_competence_assessment USING btree (employee_idemployee);


--
-- Name: department_company_idcompany_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX department_company_idcompany_idx ON department USING btree (company_idcompany);


--
-- Name: department_head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX department_head_of_department_idemployee_idx ON department USING btree (head_of_department_idemployee);


--
-- Name: division_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX division_employee_idemployee_idx ON division USING btree (head_of_division_employee);


--
-- Name: edr_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX edr_immediate_manager_idemployee_idx ON edr USING btree (immediate_manager_idemployee);


--
-- Name: edr_previous_edr_idedr_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX edr_previous_edr_idedr_idx ON edr USING btree (previous_edr_idedr);


--
-- Name: edr_reviewed_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX edr_reviewed_employee_idemployee_idx ON edr USING btree (reviewed_employee_idemployee);


--
-- Name: employee_competence_assessment_assessment_idassessment_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessment_idassessment_idx ON employee_competence_assessment USING btree (assessment_idassessment);


--
-- Name: employee_competence_assessment_assessor_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessor_idemployee_idx ON employee_competence_assessment USING btree (assessor_idemployee);


--
-- Name: employee_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX employee_competence_assessment_competence_idcompetence_idx ON employee_competence_assessment USING btree (competence_idcompetence);


--
-- Name: employee_current_in_company_employment_id_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX employee_current_in_company_employment_id_idx ON employee USING btree (current_in_company_employment_id);


--
-- Name: employee_department_iddepartment_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX employee_department_iddepartment_idx ON employee USING btree (department_iddepartment);


--
-- Name: in_company_employment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX in_company_employment_employee_idemployee_idx ON in_company_employment USING btree (employee_idemployee);


--
-- Name: in_company_employment_job_idjob_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX in_company_employment_job_idjob_idx ON in_company_employment USING btree (job_idjob);


--
-- Name: job_advertisement_job_idjob_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_advertisement_job_idjob_idx ON job_advertisement USING btree (job_idjob);


--
-- Name: job_application_job_advertisement_idjob_advertisement_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_application_job_advertisement_idjob_advertisement_idx ON job_application USING btree (job_advertisement_idjob_advertisement);


--
-- Name: job_application_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_application_job_applicant_idjob_applicant_idx ON job_application USING btree (job_applicant_idjob_applicant);


--
-- Name: job_business_area_idbusiness_area_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_business_area_idbusiness_area_idx ON job USING btree (business_area_idbusiness_area);


--
-- Name: job_organisational_position_idorganisational_position_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_organisational_position_idorganisational_position_idx ON job USING btree (organisational_position_idorganisational_position);


--
-- Name: job_place_employment_idplace_employment_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_place_employment_idplace_employment_idx ON job USING btree (place_employment_idplace_employment);


--
-- Name: job_reporting_to_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_reporting_to_idemployee_idx ON job USING btree (reporting_to_idemployee);


--
-- Name: job_study_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX job_study_min_requirements_job_idjob_idx ON job_study_min_requirements USING btree (job_idjob);


--
-- Name: organisational_position_company_idcompany_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX organisational_position_company_idcompany_idx ON organisational_position USING btree (company_idcompany);


--
-- Name: pplicant_present_idapplicant_professional_experience_record_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX pplicant_present_idapplicant_professional_experience_record_idx ON job_applicant USING btree (present_idapplicant_professional_experience_record);


--
-- Name: professional_experience_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX professional_experience_min_requirements_job_idjob_idx ON professional_experience_min_requirements USING btree (job_idjob);


--
-- Name: professional_experience_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX professional_experience_record_employee_idemployee_idx ON professional_experience_record USING btree (employee_idemployee);


--
-- Name: professional_experience_record_job_applicant_idjob_applicant_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX professional_experience_record_job_applicant_idjob_applicant_id ON applicant_professional_experience_record USING btree (job_applicant_idjob_applicant);


--
-- Name: question_answer_edr_idedr_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX question_answer_edr_idedr_idx ON question_answer USING btree (edr_idedr);


--
-- Name: statement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX statement_competence_idcompetence_idx ON statement USING btree (competence_id);


--
-- Name: study_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX study_record_employee_idemployee_idx ON study_record USING btree (employee_idemployee);


--
-- Name: applicant_competence_assessment1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment1 FOREIGN KEY (job_application_id) REFERENCES job_application(idjob_application);


--
-- Name: applicant_competence_assessment2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- Name: applicant_professional_experience_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT "applicant_professional_experience_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: applicant_study_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT "applicant_study_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: assessee_id_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "assessee_id_employee_TO_employee" FOREIGN KEY (assessee_idemployee) REFERENCES employee(idemployee);


--
-- Name: business_area_TO_Division; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT "business_area_TO_Division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- Name: colleague1_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague1_idemployee_TO_employee" FOREIGN KEY (colleague1_idemployee) REFERENCES employee(idemployee);


--
-- Name: colleague2_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague2_idemployee_TO_employee" FOREIGN KEY (colleague2_idemployee) REFERENCES employee(idemployee);


--
-- Name: colleague3_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague3_idemployee_TO_employee" FOREIGN KEY (colleague3_idemployee) REFERENCES employee(idemployee);


--
-- Name: competence_goal_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: competence_goal_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- Name: competence_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES competence(idcompetence);


--
-- Name: competences_requirement_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: current_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: current_in_company_employment_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT current_in_company_employment_id FOREIGN KEY (current_in_company_employment_id) REFERENCES in_company_employment(idin_company_employment);


--
-- Name: department_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


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

ALTER TABLE ONLY "edrNotes"
    ADD CONSTRAINT "edrNotes.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- Name: edr_previous_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_previous_edr_idedr_fkey FOREIGN KEY (previous_edr_idedr) REFERENCES edr(idedr);


--
-- Name: employee_competence_assessment_assessment_idassessment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessment_idassessment_fkey FOREIGN KEY (assessment_idassessment) REFERENCES assessment(idassessment);


--
-- Name: employee_competence_assessment_assessor_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessor_idemployee_fkey FOREIGN KEY (assessor_idemployee) REFERENCES employee(idemployee);


--
-- Name: employee_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- Name: employee_competence_assessment_statement_idstatement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_statement_idstatement_fkey FOREIGN KEY (statement_idstatement) REFERENCES statement(idstatement);


--
-- Name: employee_department_iddepartment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_department_iddepartment_fkey FOREIGN KEY (department_iddepartment) REFERENCES department(iddepartment);


--
-- Name: employee_id_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT "employee_id_TO_employee" FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: head_of_department_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY department
    ADD CONSTRAINT "head_of_department_idemployee_TO_employee" FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- Name: head_of_division_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "head_of_division_employee_TO_employee" FOREIGN KEY (head_of_division_employee) REFERENCES employee(idemployee);


--
-- Name: immediate_manager_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "immediate_manager_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- Name: immediate_manager_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
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
-- Name: in_company_employment_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: job_advertisement_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_applicant_present_idapplicant_professional_experience__fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_present_idapplicant_professional_experience__fkey FOREIGN KEY (present_idapplicant_professional_experience_record) REFERENCES applicant_professional_experience_record(idapplicant_professional_experience_record);


--
-- Name: job_application_job_advertisement_idjob_advertisement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_advertisement_idjob_advertisement_fkey FOREIGN KEY (job_advertisement_idjob_advertisement) REFERENCES job_advertisement(idjob_advertisement);


--
-- Name: job_application_job_applicant_idjob_applicant_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_applicant_idjob_applicant_fkey FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- Name: job_business_area_idbusiness_area_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_business_area_idbusiness_area_fkey FOREIGN KEY (business_area_idbusiness_area) REFERENCES business_area(idbusiness_area);


--
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: job_reporting_to_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_reporting_to_idemployee_fkey FOREIGN KEY (reporting_to_idemployee) REFERENCES employee(idemployee);


--
-- Name: job_study_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: organisational_position_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- Name: organisational_position_id_TO_organisational_position; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "organisational_position_id_TO_organisational_position" FOREIGN KEY (organisational_position_idorganisational_position) REFERENCES organisational_position(idorganisational_position);


--
-- Name: place_employment_id_TO_place_employment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "place_employment_id_TO_place_employment" FOREIGN KEY (place_employment_idplace_employment) REFERENCES place_employment(idplace_employment);


--
-- Name: professional_experience_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- Name: professional_experience_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: question_answer_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT question_answer_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- Name: reviewed_employee_TO_empoyee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "reviewed_employee_TO_empoyee" FOREIGN KEY (reviewed_employee_idemployee) REFERENCES employee(idemployee);


--
-- Name: statement2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- Name: study_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
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
-- Name: employee_idemployee_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM postgres;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO postgres;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO comprofits_accessor_role;


--
-- Name: employee; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE employee FROM PUBLIC;
REVOKE ALL ON TABLE employee FROM postgres;
GRANT ALL ON TABLE employee TO postgres;
GRANT ALL ON TABLE employee TO comprofits_accessor_role;


--
-- Name: job_applicant_idjob_applicant_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM postgres;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO postgres;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO comprofits_accessor_role;


--
-- Name: job_applicant; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE job_applicant FROM PUBLIC;
REVOKE ALL ON TABLE job_applicant FROM postgres;
GRANT ALL ON TABLE job_applicant TO postgres;
GRANT ALL ON TABLE job_applicant TO comprofits_accessor_role;


--
-- Name: all_users; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE all_users FROM PUBLIC;
REVOKE ALL ON TABLE all_users FROM postgres;
GRANT ALL ON TABLE all_users TO postgres;
GRANT ALL ON TABLE all_users TO comprofits_accessor_role;


--
-- Name: idapplicant_competence_assessment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO postgres;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO comprofits_accessor_role;


--
-- Name: applicant_competence_assessment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE applicant_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE applicant_competence_assessment FROM postgres;
GRANT ALL ON TABLE applicant_competence_assessment TO postgres;
GRANT ALL ON TABLE applicant_competence_assessment TO comprofits_accessor_role;


--
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM postgres;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO postgres;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO comprofits_accessor_role;


--
-- Name: applicant_professional_experience_record; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE applicant_professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_professional_experience_record FROM postgres;
GRANT ALL ON TABLE applicant_professional_experience_record TO postgres;
GRANT ALL ON TABLE applicant_professional_experience_record TO comprofits_accessor_role;


--
-- Name: idapplicant_study_record_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM postgres;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO postgres;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO comprofits_accessor_role;


--
-- Name: applicant_study_record; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE applicant_study_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_study_record FROM postgres;
GRANT ALL ON TABLE applicant_study_record TO postgres;
GRANT ALL ON TABLE applicant_study_record TO comprofits_accessor_role;


--
-- Name: idassessment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idassessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idassessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idassessment_seq TO postgres;
GRANT ALL ON SEQUENCE idassessment_seq TO comprofits_accessor_role;


--
-- Name: assessment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE assessment FROM PUBLIC;
REVOKE ALL ON TABLE assessment FROM postgres;
GRANT ALL ON TABLE assessment TO postgres;
GRANT ALL ON TABLE assessment TO comprofits_accessor_role;


--
-- Name: business_area_idbusiness_area_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM postgres;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO postgres;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO comprofits_accessor_role;


--
-- Name: business_area; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE business_area FROM PUBLIC;
REVOKE ALL ON TABLE business_area FROM postgres;
GRANT ALL ON TABLE business_area TO postgres;
GRANT ALL ON TABLE business_area TO comprofits_accessor_role;


--
-- Name: company_idcompany_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE company_idcompany_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE company_idcompany_seq FROM postgres;
GRANT ALL ON SEQUENCE company_idcompany_seq TO postgres;
GRANT ALL ON SEQUENCE company_idcompany_seq TO comprofits_accessor_role;


--
-- Name: company; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE company FROM PUBLIC;
REVOKE ALL ON TABLE company FROM postgres;
GRANT ALL ON TABLE company TO postgres;
GRANT ALL ON TABLE company TO comprofits_accessor_role;


--
-- Name: idcompetence_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idcompetence_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_seq FROM postgres;
GRANT ALL ON SEQUENCE idcompetence_seq TO postgres;
GRANT ALL ON SEQUENCE idcompetence_seq TO comprofits_accessor_role;


--
-- Name: competence; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE competence FROM PUBLIC;
REVOKE ALL ON TABLE competence FROM postgres;
GRANT ALL ON TABLE competence TO postgres;
GRANT ALL ON TABLE competence TO comprofits_accessor_role;


--
-- Name: idcompetence_goal_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM postgres;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO postgres;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO comprofits_accessor_role;


--
-- Name: competence_goal; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE competence_goal FROM PUBLIC;
REVOKE ALL ON TABLE competence_goal FROM postgres;
GRANT ALL ON TABLE competence_goal TO postgres;
GRANT ALL ON TABLE competence_goal TO comprofits_accessor_role;


--
-- Name: idcompetences_requirement; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idcompetences_requirement FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetences_requirement FROM postgres;
GRANT ALL ON SEQUENCE idcompetences_requirement TO postgres;
GRANT ALL ON SEQUENCE idcompetences_requirement TO comprofits_accessor_role;


--
-- Name: competences_requirement; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE competences_requirement FROM PUBLIC;
REVOKE ALL ON TABLE competences_requirement FROM postgres;
GRANT ALL ON TABLE competences_requirement TO postgres;
GRANT ALL ON TABLE competences_requirement TO comprofits_accessor_role;


--
-- Name: idcurrent_competence_assessment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO postgres;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO comprofits_accessor_role;


--
-- Name: current_competence_assessment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE current_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE current_competence_assessment FROM postgres;
GRANT ALL ON TABLE current_competence_assessment TO postgres;
GRANT ALL ON TABLE current_competence_assessment TO comprofits_accessor_role;


--
-- Name: iddepartment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE iddepartment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE iddepartment_seq FROM postgres;
GRANT ALL ON SEQUENCE iddepartment_seq TO postgres;
GRANT ALL ON SEQUENCE iddepartment_seq TO comprofits_accessor_role;


--
-- Name: department; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE department FROM PUBLIC;
REVOKE ALL ON TABLE department FROM postgres;
GRANT ALL ON TABLE department TO postgres;
GRANT ALL ON TABLE department TO comprofits_accessor_role;


--
-- Name: division_iddivision_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE division_iddivision_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE division_iddivision_seq FROM postgres;
GRANT ALL ON SEQUENCE division_iddivision_seq TO postgres;
GRANT ALL ON SEQUENCE division_iddivision_seq TO comprofits_accessor_role;


--
-- Name: division; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE division FROM PUBLIC;
REVOKE ALL ON TABLE division FROM postgres;
GRANT ALL ON TABLE division TO postgres;
GRANT ALL ON TABLE division TO comprofits_accessor_role;


--
-- Name: idedr_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idedr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idedr_seq FROM postgres;
GRANT ALL ON SEQUENCE idedr_seq TO postgres;
GRANT ALL ON SEQUENCE idedr_seq TO comprofits_accessor_role;


--
-- Name: edr; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE edr FROM PUBLIC;
REVOKE ALL ON TABLE edr FROM postgres;
GRANT ALL ON TABLE edr TO postgres;
GRANT ALL ON TABLE edr TO comprofits_accessor_role;


--
-- Name: idemployee_competence_assessment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO postgres;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO comprofits_accessor_role;


--
-- Name: employee_competence_assessment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE employee_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE employee_competence_assessment FROM postgres;
GRANT ALL ON TABLE employee_competence_assessment TO postgres;
GRANT ALL ON TABLE employee_competence_assessment TO comprofits_accessor_role;


--
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM postgres;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO postgres;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO comprofits_accessor_role;


--
-- Name: idin_company_employment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM postgres;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO postgres;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO comprofits_accessor_role;


--
-- Name: idjob_advertisement_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO postgres;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO comprofits_accessor_role;


--
-- Name: idjob_application_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idjob_application_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_application_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_application_seq TO postgres;
GRANT ALL ON SEQUENCE idjob_application_seq TO comprofits_accessor_role;


--
-- Name: idjob_study_min_requirements_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO postgres;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO comprofits_accessor_role;


--
-- Name: idprofessional_experience_min_requirements_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM postgres;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO postgres;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO comprofits_accessor_role;


--
-- Name: idquestion_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idquestion_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestion_seq FROM postgres;
GRANT ALL ON SEQUENCE idquestion_seq TO postgres;
GRANT ALL ON SEQUENCE idquestion_seq TO comprofits_accessor_role;


--
-- Name: idstudy_record_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE idstudy_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idstudy_record_seq FROM postgres;
GRANT ALL ON SEQUENCE idstudy_record_seq TO postgres;
GRANT ALL ON SEQUENCE idstudy_record_seq TO comprofits_accessor_role;


--
-- Name: in_company_employment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE in_company_employment FROM PUBLIC;
REVOKE ALL ON TABLE in_company_employment FROM postgres;
GRANT ALL ON TABLE in_company_employment TO postgres;
GRANT ALL ON TABLE in_company_employment TO comprofits_accessor_role;


--
-- Name: job_idjob_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE job_idjob_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_idjob_seq FROM postgres;
GRANT ALL ON SEQUENCE job_idjob_seq TO postgres;
GRANT ALL ON SEQUENCE job_idjob_seq TO comprofits_accessor_role;


--
-- Name: job; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE job FROM PUBLIC;
REVOKE ALL ON TABLE job FROM postgres;
GRANT ALL ON TABLE job TO postgres;
GRANT ALL ON TABLE job TO comprofits_accessor_role;


--
-- Name: job_advertisement; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE job_advertisement FROM PUBLIC;
REVOKE ALL ON TABLE job_advertisement FROM postgres;
GRANT ALL ON TABLE job_advertisement TO postgres;
GRANT ALL ON TABLE job_advertisement TO comprofits_accessor_role;


--
-- Name: job_application; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE job_application FROM PUBLIC;
REVOKE ALL ON TABLE job_application FROM postgres;
GRANT ALL ON TABLE job_application TO postgres;
GRANT ALL ON TABLE job_application TO comprofits_accessor_role;


--
-- Name: job_study_min_requirements; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE job_study_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE job_study_min_requirements FROM postgres;
GRANT ALL ON TABLE job_study_min_requirements TO postgres;
GRANT ALL ON TABLE job_study_min_requirements TO comprofits_accessor_role;


--
-- Name: organisational_position_idorganisational_position_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM postgres;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO postgres;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO comprofits_accessor_role;


--
-- Name: organisational_position; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE organisational_position FROM PUBLIC;
REVOKE ALL ON TABLE organisational_position FROM postgres;
GRANT ALL ON TABLE organisational_position TO postgres;
GRANT ALL ON TABLE organisational_position TO comprofits_accessor_role;


--
-- Name: place_employment_idplace_employment_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM postgres;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO postgres;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO comprofits_accessor_role;


--
-- Name: place_employment; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE place_employment FROM PUBLIC;
REVOKE ALL ON TABLE place_employment FROM postgres;
GRANT ALL ON TABLE place_employment TO postgres;
GRANT ALL ON TABLE place_employment TO comprofits_accessor_role;


--
-- Name: professional_experience_min_requirements; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE professional_experience_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_min_requirements FROM postgres;
GRANT ALL ON TABLE professional_experience_min_requirements TO postgres;
GRANT ALL ON TABLE professional_experience_min_requirements TO comprofits_accessor_role;


--
-- Name: professional_experience_record; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_record FROM postgres;
GRANT ALL ON TABLE professional_experience_record TO postgres;
GRANT ALL ON TABLE professional_experience_record TO comprofits_accessor_role;


--
-- Name: question_answer; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE question_answer FROM PUBLIC;
REVOKE ALL ON TABLE question_answer FROM postgres;
GRANT ALL ON TABLE question_answer TO postgres;
GRANT ALL ON TABLE question_answer TO comprofits_accessor_role;


--
-- Name: statement_idstatement_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM postgres;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO postgres;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO comprofits_accessor_role;


--
-- Name: statement; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE statement FROM PUBLIC;
REVOKE ALL ON TABLE statement FROM postgres;
GRANT ALL ON TABLE statement TO postgres;
GRANT ALL ON TABLE statement TO comprofits_accessor_role;


--
-- Name: study_record; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE study_record FROM PUBLIC;
REVOKE ALL ON TABLE study_record FROM postgres;
GRANT ALL ON TABLE study_record TO postgres;
GRANT ALL ON TABLE study_record TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON SEQUENCES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON SEQUENCES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON SEQUENCES  TO postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON SEQUENCES  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR TYPES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TYPES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TYPES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TYPES  TO postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TYPES  TO PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TYPES  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR TYPES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TYPES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TYPES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TYPES  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON FUNCTIONS  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON FUNCTIONS  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON FUNCTIONS  TO postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON FUNCTIONS  TO PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON FUNCTIONS  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON FUNCTIONS  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TABLES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES  TO comprofits_accessor_role;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES  TO comprofits_accessor_role;


--
-- PostgreSQL database dump complete
--
