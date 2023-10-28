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
    password character varying(256) NOT NULL,
    marital_status character(1) DEFAULT NULL::bpchar,
    number_of_children smallint,
    department_iddepartment integer,
    current_in_company_employment_id integer,
    role character varying(50),
    is_active boolean DEFAULT true NOT NULL
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
    password character varying(256) NOT NULL,
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
          WHERE (employee.is_active = true)
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
    conclusion text,
    issue_date date
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
    verdict text DEFAULT NULL::character varying,
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
    place_employment_idplace_employment integer,
    job_status boolean
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
-- Data for Name: applicant_competence_assessment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY applicant_competence_assessment (idapplicant_competence_assessment, value, competence_id, job_application_id) FROM stdin;
\.


--
-- Data for Name: applicant_professional_experience_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY applicant_professional_experience_record (idapplicant_professional_experience_record, date_started, date_finished, company, role, job_title, field_of_work, place_of_employment, immediate_manager, business_area, division, job_applicant_idjob_applicant) FROM stdin;
\.


--
-- Data for Name: applicant_study_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY applicant_study_record (idstudy_record, title, title_type, institution, date_started, date_acquired, job_applicant_idjob_applicant) FROM stdin;
\.


--
-- Data for Name: assessment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY assessment (idassessment, date_created, assessee_idemployee, immediate_manager_idemployee, colleague1_idemployee, colleague2_idemployee, colleague3_idemployee, deadline, completed, conclusion, issue_date) FROM stdin;
4	2014-09-18	5	3	19	20	22	2014-09-30	t	<h2>Final Score: = 3.5</h2>< br/><emph>Immediate Manager Assessment (Kakarontzas, George)</emph><br />Competence The ability of leading the way Average: 5.0<br />Competence Teambuilding Average: 5.0<br />Competence The ability of creating involvement Average: 3.0<br />Competence Flexibility Average: 1.0<br />Assessment Status Completed<br />Average: 3.5<br /><hr><a href="mailto:david@teilar.gr"><b>Lemons David</b></a> (Click to Email)<br />Competence The ability of leading the way Average: Not Completed Yet<br />Competence Teambuilding Average: Not Completed Yet<br />Competence The ability of creating involvement Average: Not Completed Yet<br />Competence Flexibility Average: Not Completed Yet<br />Assessment Status Not Completed Yet<hr><a href="mailto:fonda@fonda.com"><b>Fonda Jane</b></a> (Click to Email)<br />Competence The ability of leading the way Average: Not Completed Yet<br />Competence Teambuilding Average: Not Completed Yet<br />Competence The ability of creating involvement Average: Not Completed Yet<br />Competence Flexibility Average: Not Completed Yet<br />Assessment Status Not Completed Yet<hr><a href="mailto:robert@redford.com"><b>Redford Robert</b></a> (Click to Email)<br />Competence The ability of leading the way Average: Not Completed Yet<br />Competence Teambuilding Average: Not Completed Yet<br />Competence The ability of creating involvement Average: Not Completed Yet<br />Competence Flexibility Average: Not Completed Yet<br />Assessment Status Not Completed Yet<hr><a href="mailto:tselios@teilar.gr"><b>Τσέλιος Δημήτρης</b></a> (Click to Email)<br />Competence The ability of leading the way Average: Not Completed Yet<br />Competence Teambuilding Average: Not Completed Yet<br />Competence The ability of creating involvement Average: Not Completed Yet<br />Competence Flexibility Average: Not Completed Yet<br />Assessment Status Not Completed Yet	\N
3	2014-08-28	5	3	19	20	4	2014-09-22	t	<h2>Final Score: = 4.0</h2>< br/><emph>Immediate Manager Assessment (Kakarontzas, George)</emph><br />Competence The ability of leading the way Average: 5.0<br />Competence The ability of creating involvement Average: 5.0<br />Assessment Status Completed<br />Average: 5.0<br /><hr><a href="mailto:david@teilar.gr"><b>Lemons David</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 5.0<br />Competence The ability of creating involvement Average: 5.0<br />Assessment Status Completed<br />Average: 5.0<br /><hr><a href="mailto:fonda@fonda.com"><b>Fonda Jane</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 1.0<br />Competence The ability of creating involvement Average: 3.0<br />Assessment Status Completed<br />Average: 2.0<br /><hr><a href="mailto:robert@redford.com"><b>Redford Robert</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 3.0<br />Competence The ability of creating involvement Average: 3.0<br />Assessment Status Completed<br />Average: 3.0<br /><hr><a href="mailto:sales@nataliasilver.com"><b>Norena Natalia</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 5.0<br />Competence The ability of creating involvement Average: 5.0<br />Assessment Status Completed<br />Average: 5.0<br />	\N
2	2014-08-28	20	4	3	19	5	2014-09-22	t	<h2>Final Score: = 3.4</h2>< br/><emph>Norena Natalia</emph><br />Competence The ability of leading the way Average: 5.0<br />Competence Teambuilding Average: 5.0<br />Assessment Status Completed<br />Average: 5.0<br /><hr><a href="mailto:robert@redford.com"><b>Redford Robert</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 1.0<br />Competence Teambuilding Average: 1.0<br />Assessment Status Completed<br />Average: 1.0<br /><hr><a href="mailto:gkakaron@teilar.gr"><b>Kakarontzas George</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 3.0<br />Competence Teambuilding Average: 1.0<br />Assessment Status Completed<br />Average: 2.0<br /><hr><a href="mailto:fonda@fonda.com"><b>Fonda Jane</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 3.0<br />Competence Teambuilding Average: 5.0<br />Assessment Status Completed<br />Average: 4.0<br /><hr><a href="mailto:david@teilar.gr"><b>Lemons David</b></a> (Click to Email)<br />Competence The ability of leading the way Average: 5.0<br />Competence Teambuilding Average: 5.0<br />Assessment Status Completed<br />Average: 5.0<br />	\N
5	2014-09-21	19	3	4	5	20	2014-10-15	f	\N	\N
6	2014-09-23	4	3	5	20	19	2014-10-15	f	\N	\N
7	2014-11-01	19	3	20	4	5	2014-11-30	f	\N	\N
\.


--
-- Data for Name: business_area; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY business_area (idbusiness_area, name, description, division_iddivision) FROM stdin;
\.


--
-- Name: business_area_idbusiness_area_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('business_area_idbusiness_area_seq', 1, false);


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY company (idcompany, company_name1, company_name2, company_address1, company_address2, postal_code, province, country, phone_number, e_mail, website) FROM stdin;
1	Tecnalia	Tecnalia Corporación Tecnológica	Parque Tecnológico de Bizkaia 	C/ Geldo Edif. 700	E-48160 Derio	Bizkaia	Spain	+34 944041444	info@tecnalia.es	http://www.tecnalia.es
\.


--
-- Name: company_idcompany_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('company_idcompany_seq', 1, true);


--
-- Data for Name: competence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY competence (idcompetence, competence_name, parent_id) FROM stdin;
12	Innovative Competences	\N
11	Professional Competences	\N
13	Social Competences	\N
14	Managerial Competences	11
15	Business Orientation	11
16	Job Related Skills	11
17	Oral & Written Communication Languages	11
19	Creativity and holistic thinking 	12
20	Entrepreneurship 	12
21	Proactivity 	12
22	Readiness for changes	12
23	Teamwork 	13
24	Professionalism 	13
25	Interpersonal skills	13
26	Motivation for learning 	13
27	The ability of leading the way	14
28	The ability of creating involvement	14
29	Teambuilding	14
30	Flexibility	14
31	Skills and abilities	24
\.


--
-- Data for Name: competence_goal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY competence_goal (idcompetence_goal, next_year_goal_value, comments, edr_idedr, competence_idcompetence) FROM stdin;
\.


--
-- Data for Name: competences_requirement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY competences_requirement (idcompetences_requirement, weight, job_idjob, competence_idcompetence) FROM stdin;
\.


--
-- Data for Name: current_competence_assessment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY current_competence_assessment (idcurrent_competence_assessment, assessment, employee_idemployee, competence_idcompetence) FROM stdin;
\.


--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY department (iddepartment, department_name, company_idcompany, head_of_department_idemployee) FROM stdin;
2	IT Department	1	3
4	Accounting Dept.	1	7
7	XYZ Department	1	18
8	Sales Department	1	24
9	Research and Development Department	1	\N
10	HRM Department	1	26
11	Business Administration Dept.	1	27
\.


--
-- Data for Name: division; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY division (iddivision, name, description, head_of_division_employee) FROM stdin;
\.


--
-- Name: division_iddivision_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('division_iddivision_seq', 1, false);


--
-- Data for Name: edr; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY edr (idedr, year, status, verdict, reviewed_employee_idemployee, immediate_manager_idemployee, previous_edr_idedr) FROM stdin;
2	2014	0	Awsome	4	3	\N
3	2014	0	<!--[if gte mso 9]><xml>\r\n <o:OfficeDocumentSettings>\r\n  <o:AllowPNG/>\r\n </o:OfficeDocumentSettings>\r\n</xml><![endif]-->\r\n\r\n<p class="MsoNormal" style="margin-bottom:0mm;margin-bottom:.0001pt;line-height:\r\nnormal"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;,&quot;serif&quot;;\r\nmso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:EN-US;mso-fareast-language:\r\nEL" lang="EN-US">This is an excellent employee all around. <br>\r\nSpecific strengths include the following:</span></p>\r\n\r\n<ol start="1" type="1"><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;,&quot;serif&quot;;mso-fareast-font-family:\r\n     &quot;Times New Roman&quot;;mso-fareast-language:EL">Persistence</span></li><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;,&quot;serif&quot;;mso-fareast-font-family:\r\n     &quot;Times New Roman&quot;;mso-fareast-language:EL">Hard working</span></li><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;,&quot;serif&quot;;mso-fareast-font-family:\r\n     &quot;Times New Roman&quot;;mso-fareast-language:EL">Collaborative</span></li></ol>\r\n\r\n<p class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\nline-height:normal"><span style="font-size:12.0pt;font-family:&quot;Times New Roman&quot;,&quot;serif&quot;;\r\nmso-fareast-font-family:&quot;Times New Roman&quot;;mso-ansi-language:EN-US;mso-fareast-language:\r\nEL" lang="EN-US">A weak spot is related to his lack of knowledge in .NET framework. But\r\nhopefully he will be able to get some more training in this.</span></p>\r\n\r\n<p class="MsoNormal"><span style="mso-ansi-language:EN-US" lang="EN-US">&nbsp;</span></p>\r\n\r\n<!--[if gte mso 9]><xml>\r\n <w:WordDocument>\r\n  <w:View>Normal</w:View>\r\n  <w:Zoom>0</w:Zoom>\r\n  <w:TrackMoves/>\r\n  <w:TrackFormatting/>\r\n  <w:DoNotShowPropertyChanges/>\r\n  <w:PunctuationKerning/>\r\n  <w:ValidateAgainstSchemas/>\r\n  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>\r\n  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>\r\n  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>\r\n  <w:DoNotPromoteQF/>\r\n  <w:LidThemeOther>EL</w:LidThemeOther>\r\n  <w:LidThemeAsian>X-NONE</w:LidThemeAsian>\r\n  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>\r\n  <w:Compatibility>\r\n   <w:BreakWrappedTables/>\r\n   <w:SnapToGridInCell/>\r\n   <w:WrapTextWithPunct/>\r\n   <w:UseAsianBreakRules/>\r\n   <w:DontGrowAutofit/>\r\n   <w:SplitPgBreakAndParaMark/>\r\n   <w:EnableOpenTypeKerning/>\r\n   <w:DontFlipMirrorIndents/>\r\n   <w:OverrideTableStyleHps/>\r\n  </w:Compatibility>\r\n  <m:mathPr>\r\n   <m:mathFont m:val="Cambria Math"/>\r\n   <m:brkBin m:val="before"/>\r\n   <m:brkBinSub m:val="&#45;-"/>\r\n   <m:smallFrac m:val="off"/>\r\n   <m:dispDef/>\r\n   <m:lMargin m:val="0"/>\r\n   <m:rMargin m:val="0"/>\r\n   <m:defJc m:val="centerGroup"/>\r\n   <m:wrapIndent m:val="1440"/>\r\n   <m:intLim m:val="subSup"/>\r\n   <m:naryLim m:val="undOvr"/>\r\n  </m:mathPr></w:WordDocument>\r\n</xml><![endif]--><!--[if gte mso 9]><xml>\r\n <w:LatentStyles DefLockedState="false" DefUnhideWhenUsed="true"\r\n  DefSemiHidden="true" DefQFormat="false" DefPriority="99"\r\n  LatentStyleCount="267">\r\n  <w:LsdException Locked="false" Priority="0" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Normal"/>\r\n  <w:LsdException Locked="false" Priority="9" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="heading 1"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 2"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 3"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 4"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 5"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 6"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 7"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 8"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 9"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 1"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 2"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 3"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 4"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 5"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 6"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 7"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 8"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 9"/>\r\n  <w:LsdException Locked="false" Priority="35" QFormat="true" Name="caption"/>\r\n  <w:LsdException Locked="false" Priority="10" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Title"/>\r\n  <w:LsdException Locked="false" Priority="1" Name="Default Paragraph Font"/>\r\n  <w:LsdException Locked="false" Priority="11" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtitle"/>\r\n  <w:LsdException Locked="false" Priority="22" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Strong"/>\r\n  <w:LsdException Locked="false" Priority="20" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="59" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Table Grid"/>\r\n  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Placeholder Text"/>\r\n  <w:LsdException Locked="false" Priority="1" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="No Spacing"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 1"/>\r\n  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Revision"/>\r\n  <w:LsdException Locked="false" Priority="34" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="List Paragraph"/>\r\n  <w:LsdException Locked="false" Priority="29" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Quote"/>\r\n  <w:LsdException Locked="false" Priority="30" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Quote"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="19" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtle Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="21" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="31" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtle Reference"/>\r\n  <w:LsdException Locked="false" Priority="32" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Reference"/>\r\n  <w:LsdException Locked="false" Priority="33" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Book Title"/>\r\n  <w:LsdException Locked="false" Priority="37" Name="Bibliography"/>\r\n  <w:LsdException Locked="false" Priority="39" QFormat="true" Name="TOC Heading"/>\r\n </w:LatentStyles>\r\n</xml><![endif]--><!--[if gte mso 10]>\r\n<style>\r\n /* Style Definitions */\r\n table.MsoNormalTable\r\n\t{mso-style-name:"Κανονικός πίνακας";\r\n\tmso-tstyle-rowband-size:0;\r\n\tmso-tstyle-colband-size:0;\r\n\tmso-style-noshow:yes;\r\n\tmso-style-priority:99;\r\n\tmso-style-parent:"";\r\n\tmso-padding-alt:0mm 5.4pt 0mm 5.4pt;\r\n\tmso-para-margin-top:0mm;\r\n\tmso-para-margin-right:0mm;\r\n\tmso-para-margin-bottom:10.0pt;\r\n\tmso-para-margin-left:0mm;\r\n\tline-height:115%;\r\n\tmso-pagination:widow-orphan;\r\n\tfont-size:11.0pt;\r\n\tfont-family:"Calibri","sans-serif";\r\n\tmso-ascii-font-family:Calibri;\r\n\tmso-ascii-theme-font:minor-latin;\r\n\tmso-hansi-font-family:Calibri;\r\n\tmso-hansi-theme-font:minor-latin;\r\n\tmso-fareast-language:EN-US;}\r\n</style>\r\n<![endif]-->	5	3	\N
4	2014	0	<!--[if gte mso 9]><xml>\r\n <o:OfficeDocumentSettings>\r\n  <o:AllowPNG/>\r\n </o:OfficeDocumentSettings>\r\n</xml><![endif]-->\r\n\r\n<p class="MsoNormal" style="margin-bottom:0mm;margin-bottom:.0001pt;line-height:\r\nnormal"><span style="font-size:12.0pt;font-family:"Times New Roman","serif";\r\nmso-fareast-font-family:"Times New Roman";mso-ansi-language:EN-US;mso-fareast-language:\r\nEL" lang="EN-US">This is an excellent employee all around. <br>\r\nSpecific strengths include the following:</span></p>\r\n\r\n<ol start="1" type="1"><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:"Times New Roman","serif";mso-fareast-font-family:\r\n     "Times New Roman";mso-fareast-language:EL">Persistence</span></li><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:"Times New Roman","serif";mso-fareast-font-family:\r\n     "Times New Roman";mso-fareast-language:EL">Hard working</span></li><li class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\n     line-height:normal;mso-list:l0 level1 lfo1;tab-stops:list 36.0pt"><span style="font-size:12.0pt;font-family:"Times New Roman","serif";mso-fareast-font-family:\r\n     "Times New Roman";mso-fareast-language:EL">Collaborative</span></li></ol>\r\n\r\n<p class="MsoNormal" style="mso-margin-top-alt:auto;mso-margin-bottom-alt:auto;\r\nline-height:normal"><span style="font-size:12.0pt;font-family:"Times New Roman","serif";\r\nmso-fareast-font-family:"Times New Roman";mso-ansi-language:EN-US;mso-fareast-language:\r\nEL" lang="EN-US">A weak spot is related to his lack of knowledge in .NET framework. But\r\nhopefully he will be able to get some more training in this.</span></p>\r\n\r\n<p class="MsoNormal"><span style="mso-ansi-language:EN-US" lang="EN-US"> </span></p>\r\n\r\n<!--[if gte mso 9]><xml>\r\n <w:WordDocument>\r\n  <w:View>Normal</w:View>\r\n  <w:Zoom>0</w:Zoom>\r\n  <w:TrackMoves/>\r\n  <w:TrackFormatting/>\r\n  <w:DoNotShowPropertyChanges/>\r\n  <w:PunctuationKerning/>\r\n  <w:ValidateAgainstSchemas/>\r\n  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>\r\n  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>\r\n  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>\r\n  <w:DoNotPromoteQF/>\r\n  <w:LidThemeOther>EL</w:LidThemeOther>\r\n  <w:LidThemeAsian>X-NONE</w:LidThemeAsian>\r\n  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>\r\n  <w:Compatibility>\r\n   <w:BreakWrappedTables/>\r\n   <w:SnapToGridInCell/>\r\n   <w:WrapTextWithPunct/>\r\n   <w:UseAsianBreakRules/>\r\n   <w:DontGrowAutofit/>\r\n   <w:SplitPgBreakAndParaMark/>\r\n   <w:EnableOpenTypeKerning/>\r\n   <w:DontFlipMirrorIndents/>\r\n   <w:OverrideTableStyleHps/>\r\n  </w:Compatibility>\r\n  <m:mathPr>\r\n   <m:mathFont m:val="Cambria Math"/>\r\n   <m:brkBin m:val="before"/>\r\n   <m:brkBinSub m:val="--"/>\r\n   <m:smallFrac m:val="off"/>\r\n   <m:dispDef/>\r\n   <m:lMargin m:val="0"/>\r\n   <m:rMargin m:val="0"/>\r\n   <m:defJc m:val="centerGroup"/>\r\n   <m:wrapIndent m:val="1440"/>\r\n   <m:intLim m:val="subSup"/>\r\n   <m:naryLim m:val="undOvr"/>\r\n  </m:mathPr></w:WordDocument>\r\n</xml><![endif]--><!--[if gte mso 9]><xml>\r\n <w:LatentStyles DefLockedState="false" DefUnhideWhenUsed="true"\r\n  DefSemiHidden="true" DefQFormat="false" DefPriority="99"\r\n  LatentStyleCount="267">\r\n  <w:LsdException Locked="false" Priority="0" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Normal"/>\r\n  <w:LsdException Locked="false" Priority="9" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="heading 1"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 2"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 3"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 4"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 5"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 6"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 7"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 8"/>\r\n  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 9"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 1"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 2"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 3"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 4"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 5"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 6"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 7"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 8"/>\r\n  <w:LsdException Locked="false" Priority="39" Name="toc 9"/>\r\n  <w:LsdException Locked="false" Priority="35" QFormat="true" Name="caption"/>\r\n  <w:LsdException Locked="false" Priority="10" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Title"/>\r\n  <w:LsdException Locked="false" Priority="1" Name="Default Paragraph Font"/>\r\n  <w:LsdException Locked="false" Priority="11" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtitle"/>\r\n  <w:LsdException Locked="false" Priority="22" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Strong"/>\r\n  <w:LsdException Locked="false" Priority="20" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="59" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Table Grid"/>\r\n  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Placeholder Text"/>\r\n  <w:LsdException Locked="false" Priority="1" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="No Spacing"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 1"/>\r\n  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Revision"/>\r\n  <w:LsdException Locked="false" Priority="34" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="List Paragraph"/>\r\n  <w:LsdException Locked="false" Priority="29" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Quote"/>\r\n  <w:LsdException Locked="false" Priority="30" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Quote"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 1"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 2"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 3"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 4"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 5"/>\r\n  <w:LsdException Locked="false" Priority="60" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Shading Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="61" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="62" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Light Grid Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="63" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="64" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="65" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="66" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium List 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="67" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="68" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="69" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="70" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Dark List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="71" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Shading Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="72" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful List Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="73" SemiHidden="false"\r\n   UnhideWhenUsed="false" Name="Colorful Grid Accent 6"/>\r\n  <w:LsdException Locked="false" Priority="19" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtle Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="21" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Emphasis"/>\r\n  <w:LsdException Locked="false" Priority="31" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Subtle Reference"/>\r\n  <w:LsdException Locked="false" Priority="32" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Intense Reference"/>\r\n  <w:LsdException Locked="false" Priority="33" SemiHidden="false"\r\n   UnhideWhenUsed="false" QFormat="true" Name="Book Title"/>\r\n  <w:LsdException Locked="false" Priority="37" Name="Bibliography"/>\r\n  <w:LsdException Locked="false" Priority="39" QFormat="true" Name="TOC Heading"/>\r\n </w:LatentStyles>\r\n</xml><![endif]--><!--[if gte mso 10]>\r\n<style>\r\n /* Style Definitions */\r\n table.MsoNormalTable\r\n\t{mso-style-name:"Κανονικός πίνακας";\r\n\tmso-tstyle-rowband-size:0;\r\n\tmso-tstyle-colband-size:0;\r\n\tmso-style-noshow:yes;\r\n\tmso-style-priority:99;\r\n\tmso-style-parent:"";\r\n\tmso-padding-alt:0mm 5.4pt 0mm 5.4pt;\r\n\tmso-para-margin-top:0mm;\r\n\tmso-para-margin-right:0mm;\r\n\tmso-para-margin-bottom:10.0pt;\r\n\tmso-para-margin-left:0mm;\r\n\tline-height:115%;\r\n\tmso-pagination:widow-orphan;\r\n\tfont-size:11.0pt;\r\n\tfont-family:"Calibri","sans-serif";\r\n\tmso-ascii-font-family:Calibri;\r\n\tmso-ascii-theme-font:minor-latin;\r\n\tmso-hansi-font-family:Calibri;\r\n\tmso-hansi-theme-font:minor-latin;\r\n\tmso-fareast-language:EN-US;}\r\n</style>\r\n<![endif]-->	5	3	\N
\.


--
-- Data for Name: edrHistory; Type: TABLE DATA; Schema: public; Owner: comprofits
--

COPY "edrHistory" (note, date, "time", idemployee, idedr, "idedrHistory") FROM stdin;
\.


--
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE SET; Schema: public; Owner: comprofits
--

SELECT pg_catalog.setval('"edrHistory_idedrHistory_seq"', 1, false);


--
-- Data for Name: edrNotes; Type: TABLE DATA; Schema: public; Owner: comprofits
--

COPY "edrNotes" (idnote, idedr, note, date) FROM stdin;
\.


--
-- Name: edrNotes_idnotes_seq; Type: SEQUENCE SET; Schema: public; Owner: comprofits
--

SELECT pg_catalog.setval('"edrNotes_idnotes_seq"', 1, false);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee (idemployee, identity_card_number, social_security_number, first_name, last_name, gender, province, address, postal_code, city, country, date_of_birth, phone_private, phone_mobile, email, photo_path, username, password, marital_status, number_of_children, department_iddepartment, current_in_company_employment_id, role, is_active) FROM stdin;
3	AK966290	072303177	George	Kakarontzas	1	Thessalia	Em. Manousaki 3A	41447	Larissa	Greece	1966-10-05	+302410251752	+306974722417	gkakaron@teilar.gr	GeorgeKakarontzas_8303174826490623038.png	george	0522a55e2d5f0993a3d66d28864b2862a7218a75ea7968b075333434404485c3	M	1	2	9	depthead	t
5	F234567	092334245	David	Lemons	1	Thessalia	TEI of Larissa	41110	Larissa	Greece	1962-04-20	+302410684459	+306973765789	david@teilar.gr	robert_8064800178828342190.jpg	david	07d046d5fac12b3f82daf5035b9aae86db5adc8275ebfbf05ec83005a4a8ba3e	M	2	2	\N	administrator	t
18	11111111	2222222	Julian	Julius	1	Attica	Patision 15	444444	Athens	Greece	1984-08-15	+307777777	+306874555555	julian@julius.com	ee92e05c-b4c5-11e3-9123-22000ab82dd9-large_9198915337726349861.jpeg	julian	ce0fee7e61f9c74f1110f0e5940a80b4f059f189217d0c3d26bb41960d4bf597	M	1	7	\N	employee	t
24	7474748	4747474	Gerome	Best	1	Thessalia	Em. Manousaki 3A	41447	Larissa	Greece	1984-04-05	+302410622414	+3067676767	gerome@best.com	robert_3414530008515652181.jpg	gerome	7436f3ea043611a6d0ec4dc3c7c4387d7610eecdd42f77b80c87e195c995db3f	M	2	8	\N	employee	t
20	56575657	646464646	Robert	Remis	1	NY	Manhatan Bvd 10	67676767	NY	USA	1920-09-15	+45767676776	+4576667767	robert@remis.com	robert_7406551894109096966.jpg	robert	4007d46292298e83da10d0763d95d5139fe0c157148d0587aa912170414ccba6	M	7	2	\N	employee	t
19	565656565	46447474	Jane	Hughes	2	Attica	Patision 10	56666	Athens	Greece	1956-10-15	+4578787878	+45667767667	jane@xyz.com	scarlet_673899980915629684.jpg	jane	81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2	M	4	2	34	employee	t
26	7878738	6383789	Jamess	Buffet	1	Attica	Patision 10	4545456	Athens	Greece	1987-10-10	+306767888	+307878787878	james@buffet.com	Foto0015_6081853721828443097.jpg	james	119c9ae6f9ca741bd0a76f87fba0b22cab5413187afb2906aa2875c38e213603	M	2	10	\N	hrrecruiter	t
27	647547	44444	George	Lion	1	T	df	fd	fds	g	2014-09-24		4444	george@net.com	\N	g1	711430f6164e93803d93428bc1fab80f41e213bb197689307de8606d437c3038	M	2	11	\N	depthead	t
22	45454	4545454	Δημήτρης	Τσέλιος	1	Θεσσαλία	Αγίου Θωμά	434343	Λάρισα	Ελλάδα	1966-10-15	3434343	4444545	tselios@teilar.gr	projectFolder_6950626867198578910.png	tselios	b5eb653070ca6737505f680ee006ed5c76b8b2ff417c1551c2539885175bd511	M	\N	11	\N	employee	t
4	M863817	019233876	Natalia	Norena	2	Thessalia	Em. Manousaki 3A	41447	Larissa	Greece	1967-03-20	+302410251752	+306974573364	sales@nataliasilver.com	Belvedere_3489255593062338537.jpg	natalia	5183d093e6153294550841731a95acacadca6fe133bb36e0f7a3bbe6342e8dc7	M	1	2	27	employee	t
7	C4333444	098223332	Simin	Julian	1	London	Jame Str.	43343	London	UK	1975-10-23	+4402020342	+4467887889	simon@bean.uk	\N	simon	0a5d17d3b19f82f8340d3977609aa9e86b4ad8b9bd71bd9eced9271f1d5b2e4a	m	1	4	\N	depthead	t
\.


--
-- Data for Name: employee_competence_assessment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee_competence_assessment (idemployee_competence_assessment, assessment, assessor_idemployee, assessment_idassessment, competence_idcompetence, statement_idstatement) FROM stdin;
48	1	19	3	27	15
49	3	19	3	28	1
28	5	19	2	29	4
35	5	19	2	27	3
43	1	19	2	27	9
91	\N	19	5	27	3
92	\N	19	5	27	9
93	\N	19	5	28	1
94	\N	19	5	28	2
99	\N	5	5	27	3
100	\N	5	5	27	9
101	\N	5	5	28	1
102	\N	5	5	28	2
103	\N	20	5	27	3
104	\N	20	5	27	9
42	3	3	2	27	9
105	\N	20	5	28	1
106	\N	20	5	28	2
107	\N	3	5	27	3
108	\N	3	5	27	9
27	1	3	2	29	4
33	3	3	2	27	3
54	5	3	3	27	15
109	\N	3	5	28	1
55	5	3	3	28	1
30	5	4	2	29	4
39	5	4	2	27	3
45	5	4	2	27	9
46	5	5	3	27	15
47	5	5	3	28	1
29	5	5	2	29	4
37	5	5	2	27	3
44	5	5	2	27	9
110	\N	3	5	28	2
26	1	20	2	29	4
31	1	20	2	27	3
41	1	20	2	27	9
50	3	20	3	27	15
51	3	20	3	28	1
56	\N	5	4	27	3
57	\N	5	4	27	9
58	\N	5	4	28	1
59	\N	5	4	28	2
60	\N	5	4	29	4
61	\N	5	4	30	7
62	\N	5	4	30	8
63	\N	19	4	27	3
64	\N	19	4	27	9
65	\N	19	4	28	1
66	\N	19	4	28	2
67	\N	19	4	29	4
68	\N	19	4	30	7
69	\N	19	4	30	8
70	\N	20	4	27	3
71	\N	20	4	27	9
72	\N	20	4	28	1
73	\N	20	4	28	2
74	\N	20	4	29	4
75	\N	20	4	30	7
76	\N	20	4	30	8
77	\N	22	4	27	3
78	\N	22	4	27	9
79	\N	22	4	28	1
80	\N	22	4	28	2
81	\N	22	4	29	4
82	\N	22	4	30	7
83	\N	22	4	30	8
84	5	3	4	27	3
85	5	3	4	27	9
86	3	3	4	28	1
87	3	3	4	28	2
88	5	3	4	29	4
89	1	3	4	30	7
90	1	3	4	30	8
52	5	4	3	27	15
53	5	4	3	28	1
95	5	4	5	27	3
96	5	4	5	27	9
97	3	4	5	28	1
98	4	4	5	28	2
111	\N	4	6	27	3
112	\N	4	6	27	9
113	\N	4	6	28	1
114	\N	5	6	27	3
115	\N	5	6	27	9
116	\N	5	6	28	1
117	\N	20	6	27	3
118	\N	20	6	27	9
119	\N	20	6	28	1
120	\N	19	6	27	3
121	\N	19	6	27	9
122	\N	19	6	28	1
123	5	3	6	27	3
124	3	3	6	27	9
125	5	3	6	28	1
126	\N	19	7	27	3
127	\N	19	7	27	9
128	\N	19	7	28	1
129	\N	19	7	28	2
130	\N	20	7	27	3
131	\N	20	7	27	9
132	\N	20	7	28	1
133	\N	20	7	28	2
134	\N	4	7	27	3
135	\N	4	7	27	9
136	\N	4	7	28	1
137	\N	4	7	28	2
138	\N	5	7	27	3
139	\N	5	7	27	9
140	\N	5	7	28	1
141	\N	5	7	28	2
142	\N	3	7	27	3
143	\N	3	7	27	9
144	\N	3	7	28	1
145	\N	3	7	28	2
\.


--
-- Name: employee_idemployee_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employee_idemployee_seq', 27, true);


--
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('essional_experience_record_idprofessional_experience_record_seq', 5, true);


--
-- Name: idapplicant_competence_assessment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idapplicant_competence_assessment_seq', 1, false);


--
-- Name: idapplicant_study_record_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idapplicant_study_record_seq', 1, false);


--
-- Name: idassessment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idassessment_seq', 7, true);


--
-- Name: idcompetence_goal_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idcompetence_goal_seq', 1, false);


--
-- Name: idcompetence_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idcompetence_seq', 31, true);


--
-- Name: idcompetences_requirement; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idcompetences_requirement', 1, false);


--
-- Name: idcurrent_competence_assessment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idcurrent_competence_assessment_seq', 1, false);


--
-- Name: iddepartment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('iddepartment_seq', 11, true);


--
-- Name: idedr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idedr_seq', 4, true);


--
-- Name: idemployee_competence_assessment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idemployee_competence_assessment_seq', 145, true);


--
-- Name: idin_company_employment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idin_company_employment_seq', 36, true);


--
-- Name: idjob_advertisement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idjob_advertisement_seq', 2, true);


--
-- Name: idjob_application_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idjob_application_seq', 1, true);


--
-- Name: idjob_study_min_requirements_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idjob_study_min_requirements_seq', 1, false);


--
-- Name: idprofessional_experience_min_requirements_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idprofessional_experience_min_requirements_seq', 1, false);


--
-- Name: idquestion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idquestion_seq', 1, false);


--
-- Name: idstudy_record_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('idstudy_record_seq', 6, true);


--
-- Data for Name: importHistory; Type: TABLE DATA; Schema: public; Owner: comprofits
--

COPY "importHistory" (date, "time", idemployee, idedr, comment, file, "idimportHistory") FROM stdin;
\.


--
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE SET; Schema: public; Owner: comprofits
--

SELECT pg_catalog.setval('"importHistory_idimportHistory_seq"', 1, false);


--
-- Data for Name: in_company_employment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY in_company_employment (idin_company_employment, start_date, end_date, job_idjob, employee_idemployee) FROM stdin;
29	2014-08-01	2014-08-08	2	4
9	2014-08-11	\N	2	3
32	2014-08-08	2014-08-10	2	3
33	2014-08-01	2014-08-12	2	4
34	2014-09-21	\N	7	19
35	2005-09-01	2014-09-20	6	19
27	2014-08-13	\N	7	4
36	2014-08-13	2014-09-23	2	4
\.


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job (idjob, job_title, job_description, organisational_position_idorganisational_position, reporting_to_idemployee, business_area_idbusiness_area, place_employment_idplace_employment, job_status) FROM stdin;
2	Head of Department	sdsdfsf	\N	\N	\N	\N	\N
6	Programmer	dfdsds	\N	\N	\N	\N	\N
7	Analys/Designer	sdfsdf	\N	\N	\N	\N	\N
8	Java Developer	An experienced Java developer is wanted	\N	\N	\N	\N	t
\.


--
-- Data for Name: job_advertisement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job_advertisement (idjob_advertisement, job_title, fields_of_responsibility, job_description, job_idjob) FROM stdin;
2	Java developer	programming next gen pos 	Java developer	8
\.


--
-- Data for Name: job_applicant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job_applicant (idjob_applicant, date_of_birth, first_name, last_name, gender, address, postal_code, city, country, province, phone_private, phone_mobile, email, photo_path, username, password, marital_status, number_of_children, present_idapplicant_professional_experience_record) FROM stdin;
1	2014-09-02	b	b	1	b	b	b	b	b	b	b	b	\N	b	3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d	S	0	\N
2	2014-09-10	c	c	2	c	c	c	c	c	c	c	c	KakarontzasAntonis_4291560103111832114.jpg	z	594e519ae499312b29433b7dd8a97ff068defcba9755b6d5d00e84c524d67b06	S	3	\N
3	1966-10-15	George	Kakarontzas	1	Em Manousaki 3A	41447	Larissa	Greece	Thessalia	2410684459	6974722417	kakarontzas@gmail.com	gk_135934789804985439.jpg	gapplicant	856a9ab2e745942df557af43bf11df6108b61c001961da0c06e2faa0309e06c3	M	1	\N
4	2014-09-03	N	N	2	n	n	n	n	n	n	n	n	Family_in_Disneyland_8562072560714729499.JPG	n	1b16b1df538ba12dc3f97edbb85caa7050d46c148134290feba80f8236c83db9	M	1	\N
6	2014-09-09	g	g	2	g	g	g	g	g	g	g	g	\N	g	cd0aa9856147b6c5b4ff2b7dfee5da20aa38253099ef1b4a64aced233c9afe29	S	\N	\N
5	1984-09-01	Athanasios	Kakarontzas	1	Filippou 8	41114	Larissa	Greece	Thessalia	+302410536342	+30455555858	gkakaron@teilar.gr	projectFolder_7548949085017628998.png	athanasios	e0e019c6ca00372330488a2ba3b0188fe857bdab3d5d6a6ab13f3cf5e092f778	M	3	\N
9	2014-09-11	f	f	1	f	f	f	f	f		f	f	\N	f	252f10c83610ebca1a059c0bae8255eba2f95be4d1d7bcfa89d7248a82d9f111	M	\N	\N
10	1967-09-01	Jim	Tselios	1	Larissa str	333333	Larissa	Greece	Thessaly	+302410626400	+3087878787878	tselios@teilar.gr	\N	tse	b5eb653070ca6737505f680ee006ed5c76b8b2ff417c1551c2539885175bd511	M	\N	\N
8	2004-04-18	Anastasia	Kakarontza	2	Em. Manousaki 3A	41447	Larissa	Greece	thessalia	+302410251752	+306974722417	akakarontza@gmail.com	chef3_6611962713573090445.jpg	anastasia	73423c7707c42ee62b43044e3e417d14f09ddc92fdab633562a11ed50ff7ea1e	S	\N	\N
11	2014-09-02	h	h	1	h	h	h	h	h	h	h	h	cow2_1391932415738184758.jpeg	h	aaa9402664f1a41f40ebbc52c9993eb66aeb366602958fdfaa283b71e64db123	M	\N	\N
12	1969-09-02	Jeniffer 	Lopez	2	Florida Avenue 4	4343434	LA	USA	California	009909900	99939939	jlo@jlo.com	o-JENNIFER-LOPEZ-570_540973199585139712.jpg	jlo	ba230a849ab88f722f892322ff6dd997914bf458170a81a78b67ff8d7765fdbb	M	2	\N
13	2014-09-01	x	x	1	x	x	x	x	x	x	x	x@x.x	LayersLarmannBook_6770140308442063648.png	x	2d711642b726b04401627ca9fbac32f5c8530fb1903cc4db02258717921a4881	M	1	\N
17	1967-09-01	George	Katsouras	1	Fillipou 8	41114	Larissa	Greece	Thessalia	+302410251752	+306974722417	george@xyz.com	Schonsburn7_3080183321745694494.jpg	gkats	76cca966dbaea5a6e9ad2f637969e4ae73684aeaa78999190f11f6c5055a4acc	M	2	\N
\.


--
-- Name: job_applicant_idjob_applicant_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('job_applicant_idjob_applicant_seq', 17, true);


--
-- Data for Name: job_application; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job_application (idjob_application, date, job_applicant_idjob_applicant, job_advertisement_idjob_advertisement) FROM stdin;
1	2014-11-28	17	2
\.


--
-- Name: job_idjob_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('job_idjob_seq', 8, true);


--
-- Data for Name: job_study_min_requirements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY job_study_min_requirements (idjob_study_min_requirements, required_title_type, job_idjob) FROM stdin;
\.


--
-- Data for Name: organisational_position; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organisational_position (idorganisational_position, organisational_position_name, organisational_position_description, company_idcompany) FROM stdin;
2	Managerial Level	Employees at the managerial level of the company	1
\.


--
-- Name: organisational_position_idorganisational_position_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organisational_position_idorganisational_position_seq', 3, true);


--
-- Data for Name: place_employment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY place_employment (idplace_employment, name, address, postal_code, city, province, country) FROM stdin;
3	TEI of Larissa	Larissa	41110	Larissa	Thessalia	Greece
4	AUTH	Thessaloniki	55533	Thessaloniki	Macedonia	Greece
\.


--
-- Name: place_employment_idplace_employment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('place_employment_idplace_employment_seq', 4, true);


--
-- Data for Name: professional_experience_min_requirements; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY professional_experience_min_requirements (idprofessional_experience_min_requirements, required_experience_years, required_prof_experience_description, job_idjob) FROM stdin;
\.


--
-- Data for Name: professional_experience_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY professional_experience_record (idprofessional_experience_record, date_started, date_finished, company, role, job_title, field_of_work, place_of_employment, immediate_manager, business_area, division, employee_idemployee) FROM stdin;
3	2004-02-02	2014-08-11	TEI of Larissa	Teacher	Lecturer	Education	Larissa, Greece	Takis Hartonas	Education	Computer Science Department	3
4	2006-09-01	2009-09-03	Businesoft	.NET Developer	Programmer/Analyst	Information Technology	Athens offices of Businesoft at Sygrou Ave.	Antonis Babilis	Information Technology	Software Development Division	3
5	2014-09-01	2014-09-02	BigSoft	Progerammer	sfsaf	sa	asf	asf	asf	afs	3
\.


--
-- Data for Name: question_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY question_answer (idquestion, question_category, question, answer, edr_idedr) FROM stdin;
\.


--
-- Data for Name: statement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY statement (idstatement, statement_text, competence_id) FROM stdin;
4	Is she/he a good team member?	29
5	Is he/she flexible in relation to others?	30
6	flex statement 1	30
7	flex 2	30
8	flex 3	30
3	Is she/he influential to other people from his/her working environment ?	27
9	Can she/he set strategic goals for the future of the department?	27
15	Is she/he knowledgeable in new technologies and developments in the area of IT?	27
1	Can she inspire others?	28
2	Can she add measurable, easy understandable goals for the others?	28
\.


--
-- Name: statement_idstatement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('statement_idstatement_seq', 15, true);


--
-- Data for Name: study_record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY study_record (idstudy_record, title, title_type, institution, date_started, date_acquired, employee_idemployee) FROM stdin;
2	MSc in Object-Oriented Software Technology	2	University of Brighton, UK	1998-09-01	1999-10-30	3
1	Degree in Informatics	1	Athens University of Business & Economics	1985-09-02	1990-06-30	3
4	Computer Science	1	Athens University of Business & Economics	1988-09-01	1992-06-30	4
5	Object-Oriented Software Technology	2	University of Brighton	1988-09-01	1999-10-01	4
6	Software Engineering	3	AUTH	2008-09-01	2012-09-04	3
\.


--
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('xperience_record_idapplicant_professional_experience_record_seq', 1, false);


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

