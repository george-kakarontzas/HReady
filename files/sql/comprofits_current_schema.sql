--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.0
-- Dumped by pg_dump version 9.4.0
-- Started on 2015-10-13 15:46:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE comprofits;
--
-- TOC entry 2465 (class 1262 OID 29913)
-- Name: comprofits; Type: DATABASE; Schema: -; Owner: comprofits
--

CREATE DATABASE comprofits WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'German_Germany.1252' LC_CTYPE = 'German_Germany.1252';


ALTER DATABASE comprofits OWNER TO comprofits;

\connect comprofits

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2466 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 239 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2468 (class 0 OID 0)
-- Dependencies: 239
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 172 (class 1259 OID 29914)
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
-- TOC entry 173 (class 1259 OID 29916)
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
    division_iddivision integer
);


ALTER TABLE employee OWNER TO comprofits;

--
-- TOC entry 174 (class 1259 OID 29929)
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
-- TOC entry 175 (class 1259 OID 29931)
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
-- TOC entry 176 (class 1259 OID 29942)
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
-- TOC entry 235 (class 1259 OID 30592)
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
-- TOC entry 237 (class 1259 OID 30602)
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
-- TOC entry 177 (class 1259 OID 29946)
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
-- TOC entry 178 (class 1259 OID 29948)
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
-- TOC entry 179 (class 1259 OID 29952)
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
-- TOC entry 180 (class 1259 OID 29954)
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
-- TOC entry 181 (class 1259 OID 29967)
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
-- TOC entry 182 (class 1259 OID 29969)
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
-- TOC entry 183 (class 1259 OID 29975)
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
-- TOC entry 184 (class 1259 OID 29977)
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
-- TOC entry 185 (class 1259 OID 29984)
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
-- TOC entry 186 (class 1259 OID 29986)
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
-- TOC entry 187 (class 1259 OID 29995)
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
-- TOC entry 188 (class 1259 OID 29997)
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
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.idcompany; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.idcompany IS 'The company ID';


--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.company_name1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name1 IS 'First line of company address\n';


--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.company_name2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name2 IS 'Second line of company name (can be empty)';


--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.company_address1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address1 IS 'First line of company address';


--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.company_address2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address2 IS 'Second line of company address (can be empty)';


--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.postal_code; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.postal_code IS 'Postal code of company address (can be empty)';


--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.province; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.province IS 'Province of company address (can be empty)';


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.country; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.country IS 'Country (can be empty)';


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.phone_number; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.phone_number IS 'Phone number of company';


--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.e_mail; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.e_mail IS 'E-mail of company';


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN company.website; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.website IS 'Company website.';


--
-- TOC entry 189 (class 1259 OID 30009)
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
-- TOC entry 190 (class 1259 OID 30011)
-- Name: competence; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competence (
    idcompetence integer DEFAULT nextval('idcompetence_seq'::regclass) NOT NULL,
    competence_name character varying(100) DEFAULT NULL::character varying,
    parent_id integer
);


ALTER TABLE competence OWNER TO comprofits;

--
-- TOC entry 191 (class 1259 OID 30016)
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
-- TOC entry 192 (class 1259 OID 30018)
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
-- TOC entry 193 (class 1259 OID 30022)
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
-- TOC entry 194 (class 1259 OID 30024)
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
-- TOC entry 195 (class 1259 OID 30028)
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
-- TOC entry 196 (class 1259 OID 30030)
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
-- TOC entry 197 (class 1259 OID 30034)
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
-- TOC entry 198 (class 1259 OID 30036)
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
-- TOC entry 199 (class 1259 OID 30041)
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
-- TOC entry 200 (class 1259 OID 30043)
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
-- TOC entry 201 (class 1259 OID 30049)
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
-- TOC entry 202 (class 1259 OID 30051)
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
-- TOC entry 203 (class 1259 OID 30060)
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
-- TOC entry 204 (class 1259 OID 30066)
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
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 204
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "edrHistory_idedrHistory_seq" OWNED BY "edrHistory"."idedrHistory";


--
-- TOC entry 205 (class 1259 OID 30068)
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
-- TOC entry 206 (class 1259 OID 30070)
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
-- TOC entry 207 (class 1259 OID 30074)
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
-- TOC entry 208 (class 1259 OID 30076)
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
-- TOC entry 209 (class 1259 OID 30080)
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
-- TOC entry 210 (class 1259 OID 30082)
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
-- TOC entry 211 (class 1259 OID 30084)
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
-- TOC entry 212 (class 1259 OID 30086)
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
-- TOC entry 213 (class 1259 OID 30088)
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
-- TOC entry 214 (class 1259 OID 30090)
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
-- TOC entry 215 (class 1259 OID 30092)
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
-- TOC entry 236 (class 1259 OID 30594)
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
-- TOC entry 216 (class 1259 OID 30094)
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
-- TOC entry 217 (class 1259 OID 30096)
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
-- TOC entry 218 (class 1259 OID 30102)
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
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 218
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "importHistory_idimportHistory_seq" OWNED BY "importHistory"."idimportHistory";


--
-- TOC entry 219 (class 1259 OID 30104)
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
-- TOC entry 220 (class 1259 OID 30111)
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
-- TOC entry 221 (class 1259 OID 30113)
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
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 221
-- Name: COLUMN job.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job.job_title IS 'The title of  the job';


--
-- TOC entry 222 (class 1259 OID 30117)
-- Name: job_advertisement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_advertisement (
    idjob_advertisement integer DEFAULT nextval('idjob_advertisement_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    fields_of_responsibility character varying(255) NOT NULL,
    job_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE job_advertisement OWNER TO comprofits;

--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 222
-- Name: COLUMN job_advertisement.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job_advertisement.job_title IS 'The title of  the job';


--
-- TOC entry 223 (class 1259 OID 30124)
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
-- TOC entry 224 (class 1259 OID 30128)
-- Name: job_study_min_requirements; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_study_min_requirements (
    idjob_study_min_requirements integer DEFAULT nextval('idjob_study_min_requirements_seq'::regclass) NOT NULL,
    required_title_type integer,
    job_idjob integer NOT NULL
);


ALTER TABLE job_study_min_requirements OWNER TO comprofits;

--
-- TOC entry 225 (class 1259 OID 30132)
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
-- TOC entry 226 (class 1259 OID 30134)
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
-- TOC entry 227 (class 1259 OID 30138)
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
-- TOC entry 228 (class 1259 OID 30140)
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
-- TOC entry 229 (class 1259 OID 30150)
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
-- TOC entry 230 (class 1259 OID 30154)
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
-- TOC entry 231 (class 1259 OID 30166)
-- Name: question; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE question (
    idquestion integer DEFAULT nextval('idquestion_seq'::regclass) NOT NULL,
    question_category_idquestioncat integer NOT NULL,
    question text
);


ALTER TABLE question OWNER TO comprofits;

--
-- TOC entry 238 (class 1259 OID 30621)
-- Name: question_category; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE question_category (
    idquestioncat integer DEFAULT nextval('idquestioncat_seq'::regclass) NOT NULL,
    category text
);


ALTER TABLE question_category OWNER TO comprofits;

--
-- TOC entry 232 (class 1259 OID 30173)
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
-- TOC entry 233 (class 1259 OID 30175)
-- Name: statement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE statement (
    idstatement integer DEFAULT nextval('statement_idstatement_seq'::regclass) NOT NULL,
    statement_text text NOT NULL,
    competence_id integer NOT NULL
);


ALTER TABLE statement OWNER TO comprofits;

--
-- TOC entry 234 (class 1259 OID 30182)
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
-- TOC entry 2140 (class 2604 OID 30188)
-- Name: idedrHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory" ALTER COLUMN "idedrHistory" SET DEFAULT nextval('"edrHistory_idedrHistory_seq"'::regclass);


--
-- TOC entry 2143 (class 2604 OID 30189)
-- Name: idimportHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory" ALTER COLUMN "idimportHistory" SET DEFAULT nextval('"importHistory_idimportHistory_seq"'::regclass);


--
-- TOC entry 2290 (class 2606 OID 30610)
-- Name: answer_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (idanswer);


--
-- TOC entry 2188 (class 2606 OID 30191)
-- Name: applicant_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment_pkey PRIMARY KEY (idapplicant_competence_assessment);


--
-- TOC entry 2190 (class 2606 OID 30193)
-- Name: applicant_professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT applicant_professional_experience_record_pkey PRIMARY KEY (idapplicant_professional_experience_record);


--
-- TOC entry 2194 (class 2606 OID 30195)
-- Name: applicant_study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT applicant_study_record_pkey PRIMARY KEY (idstudy_record);


--
-- TOC entry 2201 (class 2606 OID 30197)
-- Name: assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (idassessment);


--
-- TOC entry 2204 (class 2606 OID 30199)
-- Name: business_area_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT business_area_pkey PRIMARY KEY (idbusiness_area);


--
-- TOC entry 2206 (class 2606 OID 30201)
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (idcompany);


--
-- TOC entry 2213 (class 2606 OID 30203)
-- Name: competence_goal_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_pkey PRIMARY KEY (idcompetence_goal);


--
-- TOC entry 2209 (class 2606 OID 30205)
-- Name: competence_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (idcompetence);


--
-- TOC entry 2217 (class 2606 OID 30207)
-- Name: competences_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_pkey PRIMARY KEY (idcompetences_requirement);


--
-- TOC entry 2221 (class 2606 OID 30209)
-- Name: current_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_pkey PRIMARY KEY (idcurrent_competence_assessment);


--
-- TOC entry 2224 (class 2606 OID 30211)
-- Name: department_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (iddepartment);


--
-- TOC entry 2228 (class 2606 OID 30213)
-- Name: division_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY division
    ADD CONSTRAINT division_pkey PRIMARY KEY (iddivision);


--
-- TOC entry 2239 (class 2606 OID 30215)
-- Name: edrNotes_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT "edrNotes_pkey" PRIMARY KEY (idnote);


--
-- TOC entry 2232 (class 2606 OID 30217)
-- Name: edr_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_pkey PRIMARY KEY (idedr);


--
-- TOC entry 2245 (class 2606 OID 30219)
-- Name: employee_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_pkey PRIMARY KEY (idemployee_competence_assessment);


--
-- TOC entry 2174 (class 2606 OID 30221)
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (idemployee);


--
-- TOC entry 2237 (class 2606 OID 30223)
-- Name: idedrHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "idedrHistory_pkey" PRIMARY KEY ("idedrHistory");


--
-- TOC entry 2247 (class 2606 OID 30225)
-- Name: idimportHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "idimportHistory_pkey" PRIMARY KEY ("idimportHistory");


--
-- TOC entry 2251 (class 2606 OID 30227)
-- Name: in_company_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_pkey PRIMARY KEY (idin_company_employment);


--
-- TOC entry 2260 (class 2606 OID 30229)
-- Name: job_advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_pkey PRIMARY KEY (idjob_advertisement);


--
-- TOC entry 2181 (class 2606 OID 30231)
-- Name: job_applicant_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_pkey PRIMARY KEY (idjob_applicant);


--
-- TOC entry 2183 (class 2606 OID 30233)
-- Name: job_applicant_username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_username_unique_constraint UNIQUE (username);


--
-- TOC entry 2264 (class 2606 OID 30235)
-- Name: job_application_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_pkey PRIMARY KEY (idjob_application);


--
-- TOC entry 2255 (class 2606 OID 30237)
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (idjob);


--
-- TOC entry 2267 (class 2606 OID 30239)
-- Name: job_study_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_pkey PRIMARY KEY (idjob_study_min_requirements);


--
-- TOC entry 2270 (class 2606 OID 30241)
-- Name: organisational_position_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_pkey PRIMARY KEY (idorganisational_position);


--
-- TOC entry 2272 (class 2606 OID 30243)
-- Name: place_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY place_employment
    ADD CONSTRAINT place_employment_pkey PRIMARY KEY (idplace_employment);


--
-- TOC entry 2275 (class 2606 OID 30245)
-- Name: professional_experience_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_pkey PRIMARY KEY (idprofessional_experience_min_requirements);


--
-- TOC entry 2278 (class 2606 OID 30247)
-- Name: professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_pkey PRIMARY KEY (idprofessional_experience_record);


--
-- TOC entry 2280 (class 2606 OID 30249)
-- Name: question_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_answer_pkey PRIMARY KEY (idquestion);


--
-- TOC entry 2293 (class 2606 OID 30629)
-- Name: questioncat_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY question_category
    ADD CONSTRAINT questioncat_pkey PRIMARY KEY (idquestioncat);


--
-- TOC entry 2177 (class 2606 OID 30251)
-- Name: social_security_number_unique; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT social_security_number_unique UNIQUE (social_security_number);


--
-- TOC entry 2284 (class 2606 OID 30253)
-- Name: statement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement_pkey PRIMARY KEY (idstatement);


--
-- TOC entry 2287 (class 2606 OID 30255)
-- Name: study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_pkey PRIMARY KEY (idstudy_record);


--
-- TOC entry 2179 (class 2606 OID 30257)
-- Name: username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT username_unique_constraint UNIQUE (username);


--
-- TOC entry 2288 (class 1259 OID 30635)
-- Name: answer_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX answer_edr_idedr_idx ON answer USING btree (edr_idedr);


--
-- TOC entry 2291 (class 1259 OID 30636)
-- Name: answer_question_idquestion_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX answer_question_idquestion_idx ON answer USING btree (question_idquestion);


--
-- TOC entry 2185 (class 1259 OID 30258)
-- Name: ant_competence_assessment_job_application_idjob_application_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX ant_competence_assessment_job_application_idjob_application_idx ON applicant_competence_assessment USING btree (job_application_id);


--
-- TOC entry 2186 (class 1259 OID 30259)
-- Name: applicant_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_competence_assessment_competence_idcompetence_idx ON applicant_competence_assessment USING btree (competence_id);


--
-- TOC entry 2192 (class 1259 OID 30260)
-- Name: applicant_study_record_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_study_record_job_applicant_idjob_applicant_idx ON applicant_study_record USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2195 (class 1259 OID 30261)
-- Name: assessment_assessee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_assessee_idemployee_idx ON assessment USING btree (assessee_idemployee);


--
-- TOC entry 2196 (class 1259 OID 30262)
-- Name: assessment_colleague1_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague1_idemployee_idx ON assessment USING btree (colleague1_idemployee);


--
-- TOC entry 2197 (class 1259 OID 30263)
-- Name: assessment_colleague2_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague2_idemployee_idx ON assessment USING btree (colleague2_idemployee);


--
-- TOC entry 2198 (class 1259 OID 30264)
-- Name: assessment_colleague3_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague3_idemployee_idx ON assessment USING btree (colleague3_idemployee);


--
-- TOC entry 2199 (class 1259 OID 30265)
-- Name: assessment_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_immediate_manager_idemployee_idx ON assessment USING btree (immediate_manager_idemployee);


--
-- TOC entry 2202 (class 1259 OID 30266)
-- Name: business_area_division_iddivision_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX business_area_division_iddivision_idx ON business_area USING btree (division_iddivision);


--
-- TOC entry 2210 (class 1259 OID 30267)
-- Name: competence_goal_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_competence_idcompetence_idx ON competence_goal USING btree (competence_idcompetence);


--
-- TOC entry 2211 (class 1259 OID 30268)
-- Name: competence_goal_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_edr_idedr_idx ON competence_goal USING btree (edr_idedr);


--
-- TOC entry 2207 (class 1259 OID 30269)
-- Name: competence_parent_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_parent_id_idx ON competence USING btree (parent_id);


--
-- TOC entry 2214 (class 1259 OID 30270)
-- Name: competences_requirement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_competence_idcompetence_idx ON competences_requirement USING btree (competence_idcompetence);


--
-- TOC entry 2215 (class 1259 OID 30271)
-- Name: competences_requirement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_job_idjob_idx ON competences_requirement USING btree (job_idjob);


--
-- TOC entry 2218 (class 1259 OID 30272)
-- Name: current_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_competence_idcompetence_idx ON current_competence_assessment USING btree (competence_idcompetence);


--
-- TOC entry 2219 (class 1259 OID 30273)
-- Name: current_competence_assessment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_employee_idemployee_idx ON current_competence_assessment USING btree (employee_idemployee);


--
-- TOC entry 2222 (class 1259 OID 30275)
-- Name: department_head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX department_head_of_department_idemployee_idx ON department USING btree (head_of_department_idemployee);


--
-- TOC entry 2226 (class 1259 OID 30276)
-- Name: division_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX division_employee_idemployee_idx ON division USING btree (head_of_division_employee);


--
-- TOC entry 2230 (class 1259 OID 30277)
-- Name: edr_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_immediate_manager_idemployee_idx ON edr USING btree (immediate_manager_idemployee);


--
-- TOC entry 2233 (class 1259 OID 30278)
-- Name: edr_previous_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_previous_edr_idedr_idx ON edr USING btree (previous_edr_idedr);


--
-- TOC entry 2234 (class 1259 OID 30279)
-- Name: edr_reviewed_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_reviewed_employee_idemployee_idx ON edr USING btree (reviewed_employee_idemployee);


--
-- TOC entry 2240 (class 1259 OID 30601)
-- Name: edrnotes_author_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edrnotes_author_idemployee_idx ON edrnotes USING btree (author_idemployee);


--
-- TOC entry 2241 (class 1259 OID 30280)
-- Name: employee_competence_assessment_assessment_idassessment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessment_idassessment_idx ON employee_competence_assessment USING btree (assessment_idassessment);


--
-- TOC entry 2242 (class 1259 OID 30281)
-- Name: employee_competence_assessment_assessor_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessor_idemployee_idx ON employee_competence_assessment USING btree (assessor_idemployee);


--
-- TOC entry 2243 (class 1259 OID 30282)
-- Name: employee_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_competence_idcompetence_idx ON employee_competence_assessment USING btree (competence_idcompetence);


--
-- TOC entry 2171 (class 1259 OID 30283)
-- Name: employee_current_in_company_employment_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_current_in_company_employment_id_idx ON employee USING btree (current_in_company_employment_id);


--
-- TOC entry 2172 (class 1259 OID 30284)
-- Name: employee_department_iddepartment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_department_iddepartment_idx ON employee USING btree (department_iddepartment);


--
-- TOC entry 2229 (class 1259 OID 30581)
-- Name: fki_company_idcompany_TO_company; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX "fki_company_idcompany_TO_company" ON division USING btree (company_idcompany);


--
-- TOC entry 2225 (class 1259 OID 30580)
-- Name: fki_department_division_iddivision_fkey; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX fki_department_division_iddivision_fkey ON department USING btree (division_iddivision);


--
-- TOC entry 2175 (class 1259 OID 30579)
-- Name: fki_division_iddivision_TO_division; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX "fki_division_iddivision_TO_division" ON employee USING btree (division_iddivision);


--
-- TOC entry 2235 (class 1259 OID 30643)
-- Name: head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX head_of_department_idemployee_idx ON edr USING btree (head_of_department_idemployee);


--
-- TOC entry 2248 (class 1259 OID 30285)
-- Name: in_company_employment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_employee_idemployee_idx ON in_company_employment USING btree (employee_idemployee);


--
-- TOC entry 2249 (class 1259 OID 30286)
-- Name: in_company_employment_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_job_idjob_idx ON in_company_employment USING btree (job_idjob);


--
-- TOC entry 2258 (class 1259 OID 30287)
-- Name: job_advertisement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_advertisement_job_idjob_idx ON job_advertisement USING btree (job_idjob);


--
-- TOC entry 2261 (class 1259 OID 30288)
-- Name: job_application_job_advertisement_idjob_advertisement_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_advertisement_idjob_advertisement_idx ON job_application USING btree (job_advertisement_idjob_advertisement);


--
-- TOC entry 2262 (class 1259 OID 30289)
-- Name: job_application_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_applicant_idjob_applicant_idx ON job_application USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2252 (class 1259 OID 30290)
-- Name: job_business_area_idbusiness_area_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_business_area_idbusiness_area_idx ON job USING btree (business_area_idbusiness_area);


--
-- TOC entry 2253 (class 1259 OID 30291)
-- Name: job_organisational_position_idorganisational_position_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_organisational_position_idorganisational_position_idx ON job USING btree (organisational_position_idorganisational_position);


--
-- TOC entry 2256 (class 1259 OID 30292)
-- Name: job_place_employment_idplace_employment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_place_employment_idplace_employment_idx ON job USING btree (place_employment_idplace_employment);


--
-- TOC entry 2257 (class 1259 OID 30293)
-- Name: job_reporting_to_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_reporting_to_idemployee_idx ON job USING btree (reporting_to_idemployee);


--
-- TOC entry 2265 (class 1259 OID 30294)
-- Name: job_study_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_study_min_requirements_job_idjob_idx ON job_study_min_requirements USING btree (job_idjob);


--
-- TOC entry 2268 (class 1259 OID 30295)
-- Name: organisational_position_company_idcompany_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX organisational_position_company_idcompany_idx ON organisational_position USING btree (company_idcompany);


--
-- TOC entry 2184 (class 1259 OID 30296)
-- Name: pplicant_present_idapplicant_professional_experience_record_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX pplicant_present_idapplicant_professional_experience_record_idx ON job_applicant USING btree (present_idapplicant_professional_experience_record);


--
-- TOC entry 2273 (class 1259 OID 30297)
-- Name: professional_experience_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_min_requirements_job_idjob_idx ON professional_experience_min_requirements USING btree (job_idjob);


--
-- TOC entry 2276 (class 1259 OID 30298)
-- Name: professional_experience_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_employee_idemployee_idx ON professional_experience_record USING btree (employee_idemployee);


--
-- TOC entry 2191 (class 1259 OID 30299)
-- Name: professional_experience_record_job_applicant_idjob_applicant_id; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_job_applicant_idjob_applicant_id ON applicant_professional_experience_record USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2281 (class 1259 OID 30637)
-- Name: question_category_idquestioncat_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX question_category_idquestioncat_idx ON question USING btree (question_category_idquestioncat);


--
-- TOC entry 2282 (class 1259 OID 30301)
-- Name: statement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX statement_competence_idcompetence_idx ON statement USING btree (competence_id);


--
-- TOC entry 2285 (class 1259 OID 30302)
-- Name: study_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX study_record_employee_idemployee_idx ON study_record USING btree (employee_idemployee);


--
-- TOC entry 2349 (class 2606 OID 30611)
-- Name: answer_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2350 (class 2606 OID 30616)
-- Name: answer_question_idquestion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_question_idquestion_fkey FOREIGN KEY (question_idquestion) REFERENCES question(idquestion);


--
-- TOC entry 2298 (class 2606 OID 30303)
-- Name: applicant_competence_assessment1; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment1 FOREIGN KEY (job_application_id) REFERENCES job_application(idjob_application);


--
-- TOC entry 2299 (class 2606 OID 30308)
-- Name: applicant_competence_assessment2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2300 (class 2606 OID 30313)
-- Name: applicant_professional_experience_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT "applicant_professional_experience_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2301 (class 2606 OID 30318)
-- Name: applicant_study_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT "applicant_study_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2302 (class 2606 OID 30323)
-- Name: assessee_id_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "assessee_id_employee_TO_employee" FOREIGN KEY (assessee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2307 (class 2606 OID 30328)
-- Name: business_area_TO_Division; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT "business_area_TO_Division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- TOC entry 2303 (class 2606 OID 30333)
-- Name: colleague1_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague1_idemployee_TO_employee" FOREIGN KEY (colleague1_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2304 (class 2606 OID 30338)
-- Name: colleague2_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague2_idemployee_TO_employee" FOREIGN KEY (colleague2_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2305 (class 2606 OID 30343)
-- Name: colleague3_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague3_idemployee_TO_employee" FOREIGN KEY (colleague3_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2318 (class 2606 OID 30574)
-- Name: company_idcompany_TO_company; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "company_idcompany_TO_company" FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- TOC entry 2309 (class 2606 OID 30348)
-- Name: competence_goal_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2310 (class 2606 OID 30353)
-- Name: competence_goal_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2308 (class 2606 OID 30358)
-- Name: competence_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2311 (class 2606 OID 30363)
-- Name: competences_requirement_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2313 (class 2606 OID 30368)
-- Name: current_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2294 (class 2606 OID 30373)
-- Name: current_in_company_employment_id; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT current_in_company_employment_id FOREIGN KEY (current_in_company_employment_id) REFERENCES in_company_employment(idin_company_employment);


--
-- TOC entry 2316 (class 2606 OID 30569)
-- Name: department_division_iddivision_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_division_iddivision_fkey FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- TOC entry 2296 (class 2606 OID 30564)
-- Name: division_iddivision_TO_division; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "division_iddivision_TO_division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- TOC entry 2323 (class 2606 OID 30383)
-- Name: edrHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2324 (class 2606 OID 30388)
-- Name: edrHistory.employee_idEmployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.employee_idEmployee_fkey" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2325 (class 2606 OID 30393)
-- Name: edrNotes.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT "edrNotes.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2319 (class 2606 OID 30398)
-- Name: edr_previous_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_previous_edr_idedr_fkey FOREIGN KEY (previous_edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2326 (class 2606 OID 30596)
-- Name: edrnotes_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edrnotes
    ADD CONSTRAINT edrnotes_idemployee_fkey FOREIGN KEY (author_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2327 (class 2606 OID 30403)
-- Name: employee_competence_assessment_assessment_idassessment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessment_idassessment_fkey FOREIGN KEY (assessment_idassessment) REFERENCES assessment(idassessment);


--
-- TOC entry 2328 (class 2606 OID 30408)
-- Name: employee_competence_assessment_assessor_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessor_idemployee_fkey FOREIGN KEY (assessor_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2329 (class 2606 OID 30413)
-- Name: employee_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2330 (class 2606 OID 30418)
-- Name: employee_competence_assessment_statement_idstatement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_statement_idstatement_fkey FOREIGN KEY (statement_idstatement) REFERENCES statement(idstatement);


--
-- TOC entry 2295 (class 2606 OID 30423)
-- Name: employee_department_iddepartment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_department_iddepartment_fkey FOREIGN KEY (department_iddepartment) REFERENCES department(iddepartment);


--
-- TOC entry 2314 (class 2606 OID 30428)
-- Name: employee_id_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT "employee_id_TO_employee" FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2315 (class 2606 OID 30433)
-- Name: head_of_department_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT "head_of_department_idemployee_TO_employee" FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2322 (class 2606 OID 30638)
-- Name: head_of_department_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT head_of_department_idemployee_fkey FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2317 (class 2606 OID 30438)
-- Name: head_of_division_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "head_of_division_employee_TO_employee" FOREIGN KEY (head_of_division_employee) REFERENCES employee(idemployee);


--
-- TOC entry 2320 (class 2606 OID 30443)
-- Name: immediate_manager_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "immediate_manager_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2306 (class 2606 OID 30448)
-- Name: immediate_manager_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "immediate_manager_idemployee_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2331 (class 2606 OID 30453)
-- Name: importHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2332 (class 2606 OID 30458)
-- Name: importHistory.employee_idEmployee_fky; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.employee_idEmployee_fky" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2333 (class 2606 OID 30463)
-- Name: in_company_employment_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2339 (class 2606 OID 30468)
-- Name: job_advertisement_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2297 (class 2606 OID 30473)
-- Name: job_applicant_present_idapplicant_professional_experience__fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_present_idapplicant_professional_experience__fkey FOREIGN KEY (present_idapplicant_professional_experience_record) REFERENCES applicant_professional_experience_record(idapplicant_professional_experience_record);


--
-- TOC entry 2340 (class 2606 OID 30478)
-- Name: job_application_job_advertisement_idjob_advertisement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_advertisement_idjob_advertisement_fkey FOREIGN KEY (job_advertisement_idjob_advertisement) REFERENCES job_advertisement(idjob_advertisement);


--
-- TOC entry 2341 (class 2606 OID 30483)
-- Name: job_application_job_applicant_idjob_applicant_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_applicant_idjob_applicant_fkey FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2335 (class 2606 OID 30488)
-- Name: job_business_area_idbusiness_area_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_business_area_idbusiness_area_fkey FOREIGN KEY (business_area_idbusiness_area) REFERENCES business_area(idbusiness_area);


--
-- TOC entry 2334 (class 2606 OID 30493)
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2312 (class 2606 OID 30498)
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2336 (class 2606 OID 30503)
-- Name: job_reporting_to_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_reporting_to_idemployee_fkey FOREIGN KEY (reporting_to_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2342 (class 2606 OID 30508)
-- Name: job_study_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2343 (class 2606 OID 30513)
-- Name: organisational_position_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- TOC entry 2337 (class 2606 OID 30518)
-- Name: organisational_position_id_TO_organisational_position; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "organisational_position_id_TO_organisational_position" FOREIGN KEY (organisational_position_idorganisational_position) REFERENCES organisational_position(idorganisational_position);


--
-- TOC entry 2338 (class 2606 OID 30523)
-- Name: place_employment_id_TO_place_employment; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "place_employment_id_TO_place_employment" FOREIGN KEY (place_employment_idplace_employment) REFERENCES place_employment(idplace_employment);


--
-- TOC entry 2344 (class 2606 OID 30528)
-- Name: professional_experience_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2345 (class 2606 OID 30533)
-- Name: professional_experience_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2346 (class 2606 OID 30630)
-- Name: question_category_idquestioncat_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY question
    ADD CONSTRAINT question_category_idquestioncat_fkey FOREIGN KEY (question_category_idquestioncat) REFERENCES question_category(idquestioncat);


--
-- TOC entry 2321 (class 2606 OID 30543)
-- Name: reviewed_employee_TO_empoyee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "reviewed_employee_TO_empoyee" FOREIGN KEY (reviewed_employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2347 (class 2606 OID 30548)
-- Name: statement2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2348 (class 2606 OID 30553)
-- Name: study_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2467 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 2469 (class 0 OID 0)
-- Dependencies: 172
-- Name: employee_idemployee_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM comprofits;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO comprofits;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO postgres;


--
-- TOC entry 2470 (class 0 OID 0)
-- Dependencies: 173
-- Name: employee; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee FROM PUBLIC;
REVOKE ALL ON TABLE employee FROM comprofits;
GRANT ALL ON TABLE employee TO comprofits;


--
-- TOC entry 2471 (class 0 OID 0)
-- Dependencies: 174
-- Name: job_applicant_idjob_applicant_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM comprofits;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO comprofits;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO postgres;


--
-- TOC entry 2472 (class 0 OID 0)
-- Dependencies: 175
-- Name: job_applicant; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_applicant FROM PUBLIC;
REVOKE ALL ON TABLE job_applicant FROM comprofits;
GRANT ALL ON TABLE job_applicant TO comprofits;


--
-- TOC entry 2473 (class 0 OID 0)
-- Dependencies: 235
-- Name: idanswer_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idanswer_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idanswer_seq FROM comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO comprofits;
GRANT ALL ON SEQUENCE idanswer_seq TO postgres;


--
-- TOC entry 2474 (class 0 OID 0)
-- Dependencies: 237
-- Name: answer; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE answer FROM PUBLIC;
REVOKE ALL ON TABLE answer FROM comprofits;
GRANT ALL ON TABLE answer TO comprofits;


--
-- TOC entry 2475 (class 0 OID 0)
-- Dependencies: 177
-- Name: idapplicant_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO postgres;


--
-- TOC entry 2476 (class 0 OID 0)
-- Dependencies: 178
-- Name: applicant_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE applicant_competence_assessment FROM comprofits;
GRANT ALL ON TABLE applicant_competence_assessment TO comprofits;


--
-- TOC entry 2477 (class 0 OID 0)
-- Dependencies: 179
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO comprofits;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO postgres;


--
-- TOC entry 2478 (class 0 OID 0)
-- Dependencies: 180
-- Name: applicant_professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_professional_experience_record FROM comprofits;
GRANT ALL ON TABLE applicant_professional_experience_record TO comprofits;


--
-- TOC entry 2479 (class 0 OID 0)
-- Dependencies: 181
-- Name: idapplicant_study_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO comprofits;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO postgres;


--
-- TOC entry 2480 (class 0 OID 0)
-- Dependencies: 182
-- Name: applicant_study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_study_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_study_record FROM comprofits;
GRANT ALL ON TABLE applicant_study_record TO comprofits;


--
-- TOC entry 2481 (class 0 OID 0)
-- Dependencies: 183
-- Name: idassessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idassessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idassessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idassessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idassessment_seq TO postgres;


--
-- TOC entry 2482 (class 0 OID 0)
-- Dependencies: 184
-- Name: assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE assessment FROM PUBLIC;
REVOKE ALL ON TABLE assessment FROM comprofits;
GRANT ALL ON TABLE assessment TO comprofits;


--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 185
-- Name: business_area_idbusiness_area_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM comprofits;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO comprofits;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO postgres;


--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 186
-- Name: business_area; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE business_area FROM PUBLIC;
REVOKE ALL ON TABLE business_area FROM comprofits;
GRANT ALL ON TABLE business_area TO comprofits;


--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 187
-- Name: company_idcompany_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE company_idcompany_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE company_idcompany_seq FROM comprofits;
GRANT ALL ON SEQUENCE company_idcompany_seq TO comprofits;
GRANT ALL ON SEQUENCE company_idcompany_seq TO postgres;


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 188
-- Name: company; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE company FROM PUBLIC;
REVOKE ALL ON TABLE company FROM comprofits;
GRANT ALL ON TABLE company TO comprofits;


--
-- TOC entry 2498 (class 0 OID 0)
-- Dependencies: 189
-- Name: idcompetence_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcompetence_seq TO comprofits;
GRANT ALL ON SEQUENCE idcompetence_seq TO postgres;


--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 190
-- Name: competence; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence FROM PUBLIC;
REVOKE ALL ON TABLE competence FROM comprofits;
GRANT ALL ON TABLE competence TO comprofits;


--
-- TOC entry 2500 (class 0 OID 0)
-- Dependencies: 191
-- Name: idcompetence_goal_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO comprofits;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO postgres;


--
-- TOC entry 2501 (class 0 OID 0)
-- Dependencies: 192
-- Name: competence_goal; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence_goal FROM PUBLIC;
REVOKE ALL ON TABLE competence_goal FROM comprofits;
GRANT ALL ON TABLE competence_goal TO comprofits;


--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 193
-- Name: idcompetences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetences_requirement FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetences_requirement FROM comprofits;
GRANT ALL ON SEQUENCE idcompetences_requirement TO comprofits;
GRANT ALL ON SEQUENCE idcompetences_requirement TO postgres;


--
-- TOC entry 2503 (class 0 OID 0)
-- Dependencies: 194
-- Name: competences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competences_requirement FROM PUBLIC;
REVOKE ALL ON TABLE competences_requirement FROM comprofits;
GRANT ALL ON TABLE competences_requirement TO comprofits;


--
-- TOC entry 2504 (class 0 OID 0)
-- Dependencies: 195
-- Name: idcurrent_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO postgres;


--
-- TOC entry 2505 (class 0 OID 0)
-- Dependencies: 196
-- Name: current_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE current_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE current_competence_assessment FROM comprofits;
GRANT ALL ON TABLE current_competence_assessment TO comprofits;


--
-- TOC entry 2506 (class 0 OID 0)
-- Dependencies: 197
-- Name: iddepartment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE iddepartment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE iddepartment_seq FROM comprofits;
GRANT ALL ON SEQUENCE iddepartment_seq TO comprofits;
GRANT ALL ON SEQUENCE iddepartment_seq TO postgres;


--
-- TOC entry 2507 (class 0 OID 0)
-- Dependencies: 198
-- Name: department; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE department FROM PUBLIC;
REVOKE ALL ON TABLE department FROM comprofits;
GRANT ALL ON TABLE department TO comprofits;


--
-- TOC entry 2508 (class 0 OID 0)
-- Dependencies: 199
-- Name: division_iddivision_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE division_iddivision_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE division_iddivision_seq FROM comprofits;
GRANT ALL ON SEQUENCE division_iddivision_seq TO comprofits;
GRANT ALL ON SEQUENCE division_iddivision_seq TO postgres;


--
-- TOC entry 2509 (class 0 OID 0)
-- Dependencies: 200
-- Name: division; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE division FROM PUBLIC;
REVOKE ALL ON TABLE division FROM comprofits;
GRANT ALL ON TABLE division TO comprofits;


--
-- TOC entry 2510 (class 0 OID 0)
-- Dependencies: 201
-- Name: idedr_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idedr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idedr_seq FROM comprofits;
GRANT ALL ON SEQUENCE idedr_seq TO comprofits;
GRANT ALL ON SEQUENCE idedr_seq TO postgres;


--
-- TOC entry 2511 (class 0 OID 0)
-- Dependencies: 202
-- Name: edr; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE edr FROM PUBLIC;
REVOKE ALL ON TABLE edr FROM comprofits;
GRANT ALL ON TABLE edr TO comprofits;


--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 207
-- Name: idemployee_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO comprofits;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO postgres;


--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 208
-- Name: employee_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE employee_competence_assessment FROM comprofits;
GRANT ALL ON TABLE employee_competence_assessment TO comprofits;


--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 209
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO comprofits;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO postgres;


--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 210
-- Name: idin_company_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM comprofits;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO comprofits;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO postgres;


--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 211
-- Name: idjob_advertisement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO postgres;


--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 212
-- Name: idjob_application_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_application_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_application_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_application_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_application_seq TO postgres;


--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 213
-- Name: idjob_study_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM comprofits;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO comprofits;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO postgres;


--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 214
-- Name: idprofessional_experience_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM comprofits;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO comprofits;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO postgres;


--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 215
-- Name: idquestion_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idquestion_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestion_seq FROM comprofits;
GRANT ALL ON SEQUENCE idquestion_seq TO comprofits;
GRANT ALL ON SEQUENCE idquestion_seq TO postgres;


--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 236
-- Name: idquestioncat_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idquestioncat_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestioncat_seq FROM comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO comprofits;
GRANT ALL ON SEQUENCE idquestioncat_seq TO postgres;


--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 216
-- Name: idstudy_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idstudy_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idstudy_record_seq FROM comprofits;
GRANT ALL ON SEQUENCE idstudy_record_seq TO comprofits;
GRANT ALL ON SEQUENCE idstudy_record_seq TO postgres;


--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 219
-- Name: in_company_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE in_company_employment FROM PUBLIC;
REVOKE ALL ON TABLE in_company_employment FROM comprofits;
GRANT ALL ON TABLE in_company_employment TO comprofits;


--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 220
-- Name: job_idjob_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_idjob_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_idjob_seq FROM comprofits;
GRANT ALL ON SEQUENCE job_idjob_seq TO comprofits;
GRANT ALL ON SEQUENCE job_idjob_seq TO postgres;


--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 221
-- Name: job; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job FROM PUBLIC;
REVOKE ALL ON TABLE job FROM comprofits;
GRANT ALL ON TABLE job TO comprofits;


--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 222
-- Name: job_advertisement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_advertisement FROM PUBLIC;
REVOKE ALL ON TABLE job_advertisement FROM comprofits;
GRANT ALL ON TABLE job_advertisement TO comprofits;


--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 223
-- Name: job_application; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_application FROM PUBLIC;
REVOKE ALL ON TABLE job_application FROM comprofits;
GRANT ALL ON TABLE job_application TO comprofits;


--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 224
-- Name: job_study_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_study_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE job_study_min_requirements FROM comprofits;
GRANT ALL ON TABLE job_study_min_requirements TO comprofits;


--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 225
-- Name: organisational_position_idorganisational_position_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM comprofits;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO comprofits;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO postgres;


--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 226
-- Name: organisational_position; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE organisational_position FROM PUBLIC;
REVOKE ALL ON TABLE organisational_position FROM comprofits;
GRANT ALL ON TABLE organisational_position TO comprofits;


--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 227
-- Name: place_employment_idplace_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM comprofits;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO comprofits;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO postgres;


--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 228
-- Name: place_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE place_employment FROM PUBLIC;
REVOKE ALL ON TABLE place_employment FROM comprofits;
GRANT ALL ON TABLE place_employment TO comprofits;


--
-- TOC entry 2537 (class 0 OID 0)
-- Dependencies: 229
-- Name: professional_experience_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_min_requirements FROM comprofits;
GRANT ALL ON TABLE professional_experience_min_requirements TO comprofits;


--
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 230
-- Name: professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_record FROM comprofits;
GRANT ALL ON TABLE professional_experience_record TO comprofits;


--
-- TOC entry 2539 (class 0 OID 0)
-- Dependencies: 231
-- Name: question; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE question FROM PUBLIC;
REVOKE ALL ON TABLE question FROM comprofits;
GRANT ALL ON TABLE question TO comprofits;


--
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 238
-- Name: question_category; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE question_category FROM PUBLIC;
REVOKE ALL ON TABLE question_category FROM comprofits;
GRANT ALL ON TABLE question_category TO comprofits;


--
-- TOC entry 2541 (class 0 OID 0)
-- Dependencies: 232
-- Name: statement_idstatement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM comprofits;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO comprofits;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO postgres;


--
-- TOC entry 2542 (class 0 OID 0)
-- Dependencies: 233
-- Name: statement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE statement FROM PUBLIC;
REVOKE ALL ON TABLE statement FROM comprofits;
GRANT ALL ON TABLE statement TO comprofits;


--
-- TOC entry 2543 (class 0 OID 0)
-- Dependencies: 234
-- Name: study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE study_record FROM PUBLIC;
REVOKE ALL ON TABLE study_record FROM comprofits;
GRANT ALL ON TABLE study_record TO comprofits;


-- Completed on 2015-10-13 15:46:24

--
-- PostgreSQL database dump complete
--

