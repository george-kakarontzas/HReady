--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2014-10-29 10:18:00 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 233 (class 3079 OID 12018)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2636 (class 0 OID 0)
-- Dependencies: 233
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 170 (class 1259 OID 26411)
-- Name: employee_idemployee_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
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
-- TOC entry 171 (class 1259 OID 26413)
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
    role character varying(50)
);


ALTER TABLE public.employee OWNER TO comprofits;

--
-- TOC entry 172 (class 1259 OID 26425)
-- Name: job_applicant_idjob_applicant_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE job_applicant_idjob_applicant_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_applicant_idjob_applicant_seq OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 26427)
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


ALTER TABLE public.job_applicant OWNER TO comprofits;

--
-- TOC entry 174 (class 1259 OID 26438)
-- Name: all_users; Type: VIEW; Schema: public; Owner: comprofits
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
-- TOC entry 175 (class 1259 OID 26442)
-- Name: idapplicant_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idapplicant_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idapplicant_competence_assessment_seq OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 26444)
-- Name: applicant_competence_assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE applicant_competence_assessment (
    idapplicant_competence_assessment integer DEFAULT nextval('idapplicant_competence_assessment_seq'::regclass) NOT NULL,
    value integer,
    competence_id integer NOT NULL,
    job_application_id integer NOT NULL
);


ALTER TABLE public.applicant_competence_assessment OWNER TO comprofits;

--
-- TOC entry 177 (class 1259 OID 26448)
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE xperience_record_idapplicant_professional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.xperience_record_idapplicant_professional_experience_record_seq OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 26450)
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


ALTER TABLE public.applicant_professional_experience_record OWNER TO comprofits;

--
-- TOC entry 179 (class 1259 OID 26463)
-- Name: idapplicant_study_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idapplicant_study_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idapplicant_study_record_seq OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 26465)
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


ALTER TABLE public.applicant_study_record OWNER TO comprofits;

--
-- TOC entry 181 (class 1259 OID 26471)
-- Name: idassessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idassessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idassessment_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 26473)
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
    conclusion text
);


ALTER TABLE public.assessment OWNER TO comprofits;

--
-- TOC entry 183 (class 1259 OID 26477)
-- Name: business_area_idbusiness_area_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE business_area_idbusiness_area_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.business_area_idbusiness_area_seq OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 26479)
-- Name: business_area; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE business_area (
    idbusiness_area integer DEFAULT nextval('business_area_idbusiness_area_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    description character varying(10) DEFAULT NULL::character varying,
    division_iddivision integer NOT NULL
);


ALTER TABLE public.business_area OWNER TO comprofits;

--
-- TOC entry 185 (class 1259 OID 26485)
-- Name: company_idcompany_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE company_idcompany_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.company_idcompany_seq OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 26487)
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


ALTER TABLE public.company OWNER TO comprofits;

--
-- TOC entry 2652 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.idcompany; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.idcompany IS 'The company ID';


--
-- TOC entry 2653 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.company_name1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name1 IS 'First line of company address\n';


--
-- TOC entry 2654 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.company_name2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_name2 IS 'Second line of company name (can be empty)';


--
-- TOC entry 2655 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.company_address1; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address1 IS 'First line of company address';


--
-- TOC entry 2656 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.company_address2; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.company_address2 IS 'Second line of company address (can be empty)';


--
-- TOC entry 2657 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.postal_code; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.postal_code IS 'Postal code of company address (can be empty)';


--
-- TOC entry 2658 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.province; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.province IS 'Province of company address (can be empty)';


--
-- TOC entry 2659 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.country; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.country IS 'Country (can be empty)';


--
-- TOC entry 2660 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.phone_number; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.phone_number IS 'Phone number of company';


--
-- TOC entry 2661 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.e_mail; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.e_mail IS 'E-mail of company';


--
-- TOC entry 2662 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN company.website; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN company.website IS 'Company website.';


--
-- TOC entry 187 (class 1259 OID 26499)
-- Name: idcompetence_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetence_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetence_seq OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 26501)
-- Name: competence; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competence (
    idcompetence integer DEFAULT nextval('idcompetence_seq'::regclass) NOT NULL,
    competence_name character varying(100) DEFAULT NULL::character varying,
    parent_id integer
);


ALTER TABLE public.competence OWNER TO comprofits;

--
-- TOC entry 189 (class 1259 OID 26506)
-- Name: idcompetence_goal_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetence_goal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetence_goal_seq OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 26508)
-- Name: competence_goal; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competence_goal (
    idcompetence_goal integer DEFAULT nextval('idcompetence_goal_seq'::regclass) NOT NULL,
    next_year_goal_value integer,
    comments character varying(255),
    edr_idedr integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.competence_goal OWNER TO comprofits;

--
-- TOC entry 191 (class 1259 OID 26512)
-- Name: idcompetences_requirement; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcompetences_requirement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcompetences_requirement OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 26514)
-- Name: competences_requirement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE competences_requirement (
    idcompetences_requirement integer DEFAULT nextval('idcompetences_requirement'::regclass) NOT NULL,
    weight integer NOT NULL,
    job_idjob integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.competences_requirement OWNER TO comprofits;

--
-- TOC entry 193 (class 1259 OID 26518)
-- Name: idcurrent_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idcurrent_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idcurrent_competence_assessment_seq OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 26520)
-- Name: current_competence_assessment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE current_competence_assessment (
    idcurrent_competence_assessment integer DEFAULT nextval('idcurrent_competence_assessment_seq'::regclass) NOT NULL,
    assessment integer NOT NULL,
    employee_idemployee integer NOT NULL,
    competence_idcompetence integer NOT NULL
);


ALTER TABLE public.current_competence_assessment OWNER TO comprofits;

--
-- TOC entry 195 (class 1259 OID 26524)
-- Name: iddepartment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE iddepartment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.iddepartment_seq OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 26526)
-- Name: department; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE department (
    iddepartment integer DEFAULT nextval('iddepartment_seq'::regclass) NOT NULL,
    department_name character varying(45) DEFAULT NULL::character varying,
    company_idcompany integer NOT NULL,
    head_of_department_idemployee integer
);


ALTER TABLE public.department OWNER TO comprofits;

--
-- TOC entry 197 (class 1259 OID 26531)
-- Name: division_iddivision_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE division_iddivision_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.division_iddivision_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 26533)
-- Name: division; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE division (
    iddivision integer DEFAULT nextval('division_iddivision_seq'::regclass) NOT NULL,
    name character varying(45) DEFAULT NULL::character varying,
    description character varying(140) DEFAULT NULL::character varying,
    head_of_division_employee integer
);


ALTER TABLE public.division OWNER TO comprofits;

--
-- TOC entry 199 (class 1259 OID 26539)
-- Name: idedr_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idedr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idedr_seq OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 26541)
-- Name: edr; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
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


ALTER TABLE public.edr OWNER TO comprofits;

--
-- TOC entry 201 (class 1259 OID 26547)
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
-- TOC entry 202 (class 1259 OID 26553)
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
-- TOC entry 2678 (class 0 OID 0)
-- Dependencies: 202
-- Name: edrHistory_idedrHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "edrHistory_idedrHistory_seq" OWNED BY "edrHistory"."idedrHistory";


--
-- TOC entry 203 (class 1259 OID 26555)
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
-- TOC entry 204 (class 1259 OID 26557)
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
-- TOC entry 205 (class 1259 OID 26561)
-- Name: idemployee_competence_assessment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idemployee_competence_assessment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idemployee_competence_assessment_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 26563)
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


ALTER TABLE public.employee_competence_assessment OWNER TO comprofits;

--
-- TOC entry 207 (class 1259 OID 26567)
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE essional_experience_record_idprofessional_experience_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.essional_experience_record_idprofessional_experience_record_seq OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 26569)
-- Name: idin_company_employment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idin_company_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idin_company_employment_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26571)
-- Name: idjob_advertisement_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_advertisement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_advertisement_seq OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 26573)
-- Name: idjob_application_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_application_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_application_seq OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26575)
-- Name: idjob_study_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idjob_study_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idjob_study_min_requirements_seq OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26577)
-- Name: idprofessional_experience_min_requirements_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idprofessional_experience_min_requirements_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idprofessional_experience_min_requirements_seq OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 26579)
-- Name: idquestion_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idquestion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idquestion_seq OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 26581)
-- Name: idstudy_record_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE idstudy_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idstudy_record_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26583)
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
-- TOC entry 216 (class 1259 OID 26589)
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
-- TOC entry 2689 (class 0 OID 0)
-- Dependencies: 216
-- Name: importHistory_idimportHistory_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: comprofits
--

ALTER SEQUENCE "importHistory_idimportHistory_seq" OWNED BY "importHistory"."idimportHistory";


--
-- TOC entry 217 (class 1259 OID 26591)
-- Name: in_company_employment; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE in_company_employment (
    idin_company_employment integer DEFAULT nextval('idin_company_employment_seq'::regclass) NOT NULL,
    start_date date,
    end_date date,
    job_idjob integer NOT NULL,
    employee_idemployee integer NOT NULL
);


ALTER TABLE public.in_company_employment OWNER TO comprofits;

--
-- TOC entry 218 (class 1259 OID 26595)
-- Name: job_idjob_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE job_idjob_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_idjob_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26597)
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


ALTER TABLE public.job OWNER TO comprofits;

--
-- TOC entry 2692 (class 0 OID 0)
-- Dependencies: 219
-- Name: COLUMN job.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job.job_title IS 'The title of  the job';


--
-- TOC entry 220 (class 1259 OID 26601)
-- Name: job_advertisement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_advertisement (
    idjob_advertisement integer DEFAULT nextval('idjob_advertisement_seq'::regclass) NOT NULL,
    job_title character varying(100) NOT NULL,
    fields_of_responsibility character varying(255) NOT NULL,
    job_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE public.job_advertisement OWNER TO comprofits;

--
-- TOC entry 2694 (class 0 OID 0)
-- Dependencies: 220
-- Name: COLUMN job_advertisement.job_title; Type: COMMENT; Schema: public; Owner: comprofits
--

COMMENT ON COLUMN job_advertisement.job_title IS 'The title of  the job';


--
-- TOC entry 221 (class 1259 OID 26608)
-- Name: job_application; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_application (
    idjob_application integer DEFAULT nextval('idjob_application_seq'::regclass) NOT NULL,
    date date,
    job_applicant_idjob_applicant integer NOT NULL,
    job_advertisement_idjob_advertisement integer NOT NULL
);


ALTER TABLE public.job_application OWNER TO comprofits;

--
-- TOC entry 222 (class 1259 OID 26612)
-- Name: job_study_min_requirements; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE job_study_min_requirements (
    idjob_study_min_requirements integer DEFAULT nextval('idjob_study_min_requirements_seq'::regclass) NOT NULL,
    required_title_type integer,
    job_idjob integer NOT NULL
);


ALTER TABLE public.job_study_min_requirements OWNER TO comprofits;

--
-- TOC entry 223 (class 1259 OID 26616)
-- Name: organisational_position_idorganisational_position_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE organisational_position_idorganisational_position_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organisational_position_idorganisational_position_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 26618)
-- Name: organisational_position; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE organisational_position (
    idorganisational_position integer DEFAULT nextval('organisational_position_idorganisational_position_seq'::regclass) NOT NULL,
    organisational_position_name character varying(45) NOT NULL,
    organisational_position_description character varying(255) NOT NULL,
    company_idcompany integer NOT NULL
);


ALTER TABLE public.organisational_position OWNER TO comprofits;

--
-- TOC entry 225 (class 1259 OID 26622)
-- Name: place_employment_idplace_employment_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE place_employment_idplace_employment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_employment_idplace_employment_seq OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 26624)
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


ALTER TABLE public.place_employment OWNER TO comprofits;

--
-- TOC entry 227 (class 1259 OID 26634)
-- Name: professional_experience_min_requirements; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE professional_experience_min_requirements (
    idprofessional_experience_min_requirements integer DEFAULT nextval('idprofessional_experience_min_requirements_seq'::regclass) NOT NULL,
    required_experience_years integer NOT NULL,
    required_prof_experience_description character varying(255) NOT NULL,
    job_idjob integer NOT NULL
);


ALTER TABLE public.professional_experience_min_requirements OWNER TO comprofits;

--
-- TOC entry 228 (class 1259 OID 26638)
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


ALTER TABLE public.professional_experience_record OWNER TO comprofits;

--
-- TOC entry 229 (class 1259 OID 26657)
-- Name: question_answer; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE question_answer (
    idquestion integer DEFAULT nextval('idquestion_seq'::regclass) NOT NULL,
    question_category integer,
    question text,
    answer text,
    edr_idedr integer NOT NULL
);


ALTER TABLE public.question_answer OWNER TO comprofits;

--
-- TOC entry 230 (class 1259 OID 26664)
-- Name: statement_idstatement_seq; Type: SEQUENCE; Schema: public; Owner: comprofits
--

CREATE SEQUENCE statement_idstatement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.statement_idstatement_seq OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 26666)
-- Name: statement; Type: TABLE; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE TABLE statement (
    idstatement integer DEFAULT nextval('statement_idstatement_seq'::regclass) NOT NULL,
    statement_text text NOT NULL,
    competence_id integer NOT NULL
);


ALTER TABLE public.statement OWNER TO comprofits;

--
-- TOC entry 232 (class 1259 OID 26673)
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


ALTER TABLE public.study_record OWNER TO comprofits;

--
-- TOC entry 2330 (class 2604 OID 26679)
-- Name: idedrHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory" ALTER COLUMN "idedrHistory" SET DEFAULT nextval('"edrHistory_idedrHistory_seq"'::regclass);


--
-- TOC entry 2333 (class 2604 OID 26680)
-- Name: idimportHistory; Type: DEFAULT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory" ALTER COLUMN "idimportHistory" SET DEFAULT nextval('"importHistory_idimportHistory_seq"'::regclass);


--
-- TOC entry 2373 (class 2606 OID 26682)
-- Name: applicant_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment_pkey PRIMARY KEY (idapplicant_competence_assessment);


--
-- TOC entry 2375 (class 2606 OID 26684)
-- Name: applicant_professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT applicant_professional_experience_record_pkey PRIMARY KEY (idapplicant_professional_experience_record);


--
-- TOC entry 2379 (class 2606 OID 26686)
-- Name: applicant_study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT applicant_study_record_pkey PRIMARY KEY (idstudy_record);


--
-- TOC entry 2386 (class 2606 OID 26688)
-- Name: assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (idassessment);


--
-- TOC entry 2389 (class 2606 OID 26690)
-- Name: business_area_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT business_area_pkey PRIMARY KEY (idbusiness_area);


--
-- TOC entry 2391 (class 2606 OID 26692)
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (idcompany);


--
-- TOC entry 2398 (class 2606 OID 26694)
-- Name: competence_goal_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_pkey PRIMARY KEY (idcompetence_goal);


--
-- TOC entry 2394 (class 2606 OID 26696)
-- Name: competence_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_pkey PRIMARY KEY (idcompetence);


--
-- TOC entry 2402 (class 2606 OID 26698)
-- Name: competences_requirement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_pkey PRIMARY KEY (idcompetences_requirement);


--
-- TOC entry 2406 (class 2606 OID 26700)
-- Name: current_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_pkey PRIMARY KEY (idcurrent_competence_assessment);


--
-- TOC entry 2410 (class 2606 OID 26702)
-- Name: department_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_pkey PRIMARY KEY (iddepartment);


--
-- TOC entry 2413 (class 2606 OID 26704)
-- Name: division_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY division
    ADD CONSTRAINT division_pkey PRIMARY KEY (iddivision);


--
-- TOC entry 2422 (class 2606 OID 26706)
-- Name: edrNotes_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "edrNotes"
    ADD CONSTRAINT "edrNotes_pkey" PRIMARY KEY (idnote);


--
-- TOC entry 2416 (class 2606 OID 26708)
-- Name: edr_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_pkey PRIMARY KEY (idedr);


--
-- TOC entry 2427 (class 2606 OID 26710)
-- Name: employee_competence_assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_pkey PRIMARY KEY (idemployee_competence_assessment);


--
-- TOC entry 2362 (class 2606 OID 26712)
-- Name: employee_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (idemployee);


--
-- TOC entry 2420 (class 2606 OID 26714)
-- Name: idedrHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "idedrHistory_pkey" PRIMARY KEY ("idedrHistory");


--
-- TOC entry 2429 (class 2606 OID 26716)
-- Name: idimportHistory_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "idimportHistory_pkey" PRIMARY KEY ("idimportHistory");


--
-- TOC entry 2433 (class 2606 OID 26718)
-- Name: in_company_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_pkey PRIMARY KEY (idin_company_employment);


--
-- TOC entry 2442 (class 2606 OID 26720)
-- Name: job_advertisement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_pkey PRIMARY KEY (idjob_advertisement);


--
-- TOC entry 2366 (class 2606 OID 26722)
-- Name: job_applicant_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_pkey PRIMARY KEY (idjob_applicant);


--
-- TOC entry 2368 (class 2606 OID 34606)
-- Name: job_applicant_username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_username_unique_constraint UNIQUE (username);


--
-- TOC entry 2446 (class 2606 OID 26724)
-- Name: job_application_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_pkey PRIMARY KEY (idjob_application);


--
-- TOC entry 2437 (class 2606 OID 26726)
-- Name: job_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_pkey PRIMARY KEY (idjob);


--
-- TOC entry 2449 (class 2606 OID 26728)
-- Name: job_study_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_pkey PRIMARY KEY (idjob_study_min_requirements);


--
-- TOC entry 2452 (class 2606 OID 26730)
-- Name: organisational_position_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_pkey PRIMARY KEY (idorganisational_position);


--
-- TOC entry 2454 (class 2606 OID 26732)
-- Name: place_employment_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY place_employment
    ADD CONSTRAINT place_employment_pkey PRIMARY KEY (idplace_employment);


--
-- TOC entry 2457 (class 2606 OID 26734)
-- Name: professional_experience_min_requirements_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_pkey PRIMARY KEY (idprofessional_experience_min_requirements);


--
-- TOC entry 2460 (class 2606 OID 26736)
-- Name: professional_experience_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_pkey PRIMARY KEY (idprofessional_experience_record);


--
-- TOC entry 2463 (class 2606 OID 26738)
-- Name: question_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT question_answer_pkey PRIMARY KEY (idquestion);


--
-- TOC entry 2466 (class 2606 OID 26740)
-- Name: statement_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement_pkey PRIMARY KEY (idstatement);


--
-- TOC entry 2469 (class 2606 OID 26742)
-- Name: study_record_pkey; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_pkey PRIMARY KEY (idstudy_record);


--
-- TOC entry 2364 (class 2606 OID 26744)
-- Name: username_unique_constraint; Type: CONSTRAINT; Schema: public; Owner: comprofits; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT username_unique_constraint UNIQUE (username);


--
-- TOC entry 2370 (class 1259 OID 26745)
-- Name: ant_competence_assessment_job_application_idjob_application_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX ant_competence_assessment_job_application_idjob_application_idx ON applicant_competence_assessment USING btree (job_application_id);


--
-- TOC entry 2371 (class 1259 OID 26746)
-- Name: applicant_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_competence_assessment_competence_idcompetence_idx ON applicant_competence_assessment USING btree (competence_id);


--
-- TOC entry 2377 (class 1259 OID 26747)
-- Name: applicant_study_record_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX applicant_study_record_job_applicant_idjob_applicant_idx ON applicant_study_record USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2380 (class 1259 OID 26748)
-- Name: assessment_assessee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_assessee_idemployee_idx ON assessment USING btree (assessee_idemployee);


--
-- TOC entry 2381 (class 1259 OID 26749)
-- Name: assessment_colleague1_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague1_idemployee_idx ON assessment USING btree (colleague1_idemployee);


--
-- TOC entry 2382 (class 1259 OID 26750)
-- Name: assessment_colleague2_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague2_idemployee_idx ON assessment USING btree (colleague2_idemployee);


--
-- TOC entry 2383 (class 1259 OID 26751)
-- Name: assessment_colleague3_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_colleague3_idemployee_idx ON assessment USING btree (colleague3_idemployee);


--
-- TOC entry 2384 (class 1259 OID 26752)
-- Name: assessment_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX assessment_immediate_manager_idemployee_idx ON assessment USING btree (immediate_manager_idemployee);


--
-- TOC entry 2387 (class 1259 OID 26753)
-- Name: business_area_division_iddivision_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX business_area_division_iddivision_idx ON business_area USING btree (division_iddivision);


--
-- TOC entry 2395 (class 1259 OID 26754)
-- Name: competence_goal_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_competence_idcompetence_idx ON competence_goal USING btree (competence_idcompetence);


--
-- TOC entry 2396 (class 1259 OID 26755)
-- Name: competence_goal_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_goal_edr_idedr_idx ON competence_goal USING btree (edr_idedr);


--
-- TOC entry 2392 (class 1259 OID 26756)
-- Name: competence_parent_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competence_parent_id_idx ON competence USING btree (parent_id);


--
-- TOC entry 2399 (class 1259 OID 26757)
-- Name: competences_requirement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_competence_idcompetence_idx ON competences_requirement USING btree (competence_idcompetence);


--
-- TOC entry 2400 (class 1259 OID 26758)
-- Name: competences_requirement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX competences_requirement_job_idjob_idx ON competences_requirement USING btree (job_idjob);


--
-- TOC entry 2403 (class 1259 OID 26759)
-- Name: current_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_competence_idcompetence_idx ON current_competence_assessment USING btree (competence_idcompetence);


--
-- TOC entry 2404 (class 1259 OID 26760)
-- Name: current_competence_assessment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX current_competence_assessment_employee_idemployee_idx ON current_competence_assessment USING btree (employee_idemployee);


--
-- TOC entry 2407 (class 1259 OID 26761)
-- Name: department_company_idcompany_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX department_company_idcompany_idx ON department USING btree (company_idcompany);


--
-- TOC entry 2408 (class 1259 OID 26762)
-- Name: department_head_of_department_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX department_head_of_department_idemployee_idx ON department USING btree (head_of_department_idemployee);


--
-- TOC entry 2411 (class 1259 OID 26763)
-- Name: division_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX division_employee_idemployee_idx ON division USING btree (head_of_division_employee);


--
-- TOC entry 2414 (class 1259 OID 26764)
-- Name: edr_immediate_manager_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_immediate_manager_idemployee_idx ON edr USING btree (immediate_manager_idemployee);


--
-- TOC entry 2417 (class 1259 OID 26765)
-- Name: edr_previous_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_previous_edr_idedr_idx ON edr USING btree (previous_edr_idedr);


--
-- TOC entry 2418 (class 1259 OID 26766)
-- Name: edr_reviewed_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX edr_reviewed_employee_idemployee_idx ON edr USING btree (reviewed_employee_idemployee);


--
-- TOC entry 2423 (class 1259 OID 26767)
-- Name: employee_competence_assessment_assessment_idassessment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessment_idassessment_idx ON employee_competence_assessment USING btree (assessment_idassessment);


--
-- TOC entry 2424 (class 1259 OID 26768)
-- Name: employee_competence_assessment_assessor_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_assessor_idemployee_idx ON employee_competence_assessment USING btree (assessor_idemployee);


--
-- TOC entry 2425 (class 1259 OID 26769)
-- Name: employee_competence_assessment_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_competence_assessment_competence_idcompetence_idx ON employee_competence_assessment USING btree (competence_idcompetence);


--
-- TOC entry 2359 (class 1259 OID 26770)
-- Name: employee_current_in_company_employment_id_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_current_in_company_employment_id_idx ON employee USING btree (current_in_company_employment_id);


--
-- TOC entry 2360 (class 1259 OID 26771)
-- Name: employee_department_iddepartment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX employee_department_iddepartment_idx ON employee USING btree (department_iddepartment);


--
-- TOC entry 2430 (class 1259 OID 26772)
-- Name: in_company_employment_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_employee_idemployee_idx ON in_company_employment USING btree (employee_idemployee);


--
-- TOC entry 2431 (class 1259 OID 26773)
-- Name: in_company_employment_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX in_company_employment_job_idjob_idx ON in_company_employment USING btree (job_idjob);


--
-- TOC entry 2440 (class 1259 OID 26774)
-- Name: job_advertisement_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_advertisement_job_idjob_idx ON job_advertisement USING btree (job_idjob);


--
-- TOC entry 2443 (class 1259 OID 26775)
-- Name: job_application_job_advertisement_idjob_advertisement_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_advertisement_idjob_advertisement_idx ON job_application USING btree (job_advertisement_idjob_advertisement);


--
-- TOC entry 2444 (class 1259 OID 26776)
-- Name: job_application_job_applicant_idjob_applicant_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_application_job_applicant_idjob_applicant_idx ON job_application USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2434 (class 1259 OID 26777)
-- Name: job_business_area_idbusiness_area_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_business_area_idbusiness_area_idx ON job USING btree (business_area_idbusiness_area);


--
-- TOC entry 2435 (class 1259 OID 26778)
-- Name: job_organisational_position_idorganisational_position_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_organisational_position_idorganisational_position_idx ON job USING btree (organisational_position_idorganisational_position);


--
-- TOC entry 2438 (class 1259 OID 26779)
-- Name: job_place_employment_idplace_employment_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_place_employment_idplace_employment_idx ON job USING btree (place_employment_idplace_employment);


--
-- TOC entry 2439 (class 1259 OID 26780)
-- Name: job_reporting_to_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_reporting_to_idemployee_idx ON job USING btree (reporting_to_idemployee);


--
-- TOC entry 2447 (class 1259 OID 26781)
-- Name: job_study_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX job_study_min_requirements_job_idjob_idx ON job_study_min_requirements USING btree (job_idjob);


--
-- TOC entry 2450 (class 1259 OID 26782)
-- Name: organisational_position_company_idcompany_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX organisational_position_company_idcompany_idx ON organisational_position USING btree (company_idcompany);


--
-- TOC entry 2369 (class 1259 OID 26783)
-- Name: pplicant_present_idapplicant_professional_experience_record_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX pplicant_present_idapplicant_professional_experience_record_idx ON job_applicant USING btree (present_idapplicant_professional_experience_record);


--
-- TOC entry 2455 (class 1259 OID 26784)
-- Name: professional_experience_min_requirements_job_idjob_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_min_requirements_job_idjob_idx ON professional_experience_min_requirements USING btree (job_idjob);


--
-- TOC entry 2458 (class 1259 OID 26785)
-- Name: professional_experience_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_employee_idemployee_idx ON professional_experience_record USING btree (employee_idemployee);


--
-- TOC entry 2376 (class 1259 OID 26786)
-- Name: professional_experience_record_job_applicant_idjob_applicant_id; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX professional_experience_record_job_applicant_idjob_applicant_id ON applicant_professional_experience_record USING btree (job_applicant_idjob_applicant);


--
-- TOC entry 2461 (class 1259 OID 26787)
-- Name: question_answer_edr_idedr_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX question_answer_edr_idedr_idx ON question_answer USING btree (edr_idedr);


--
-- TOC entry 2464 (class 1259 OID 26788)
-- Name: statement_competence_idcompetence_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX statement_competence_idcompetence_idx ON statement USING btree (competence_id);


--
-- TOC entry 2467 (class 1259 OID 26789)
-- Name: study_record_employee_idemployee_idx; Type: INDEX; Schema: public; Owner: comprofits; Tablespace: 
--

CREATE INDEX study_record_employee_idemployee_idx ON study_record USING btree (employee_idemployee);


--
-- TOC entry 2473 (class 2606 OID 26790)
-- Name: applicant_competence_assessment1; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment1 FOREIGN KEY (job_application_id) REFERENCES job_application(idjob_application);


--
-- TOC entry 2474 (class 2606 OID 26795)
-- Name: applicant_competence_assessment2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_competence_assessment
    ADD CONSTRAINT applicant_competence_assessment2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2475 (class 2606 OID 26800)
-- Name: applicant_professional_experience_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_professional_experience_record
    ADD CONSTRAINT "applicant_professional_experience_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2476 (class 2606 OID 26805)
-- Name: applicant_study_record_TO_job_applicant; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY applicant_study_record
    ADD CONSTRAINT "applicant_study_record_TO_job_applicant" FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2477 (class 2606 OID 26810)
-- Name: assessee_id_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "assessee_id_employee_TO_employee" FOREIGN KEY (assessee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2482 (class 2606 OID 26815)
-- Name: business_area_TO_Division; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY business_area
    ADD CONSTRAINT "business_area_TO_Division" FOREIGN KEY (division_iddivision) REFERENCES division(iddivision);


--
-- TOC entry 2478 (class 2606 OID 26820)
-- Name: colleague1_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague1_idemployee_TO_employee" FOREIGN KEY (colleague1_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2479 (class 2606 OID 26825)
-- Name: colleague2_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague2_idemployee_TO_employee" FOREIGN KEY (colleague2_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2480 (class 2606 OID 26830)
-- Name: colleague3_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "colleague3_idemployee_TO_employee" FOREIGN KEY (colleague3_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2484 (class 2606 OID 26835)
-- Name: competence_goal_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2485 (class 2606 OID 26840)
-- Name: competence_goal_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence_goal
    ADD CONSTRAINT competence_goal_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2483 (class 2606 OID 26845)
-- Name: competence_parent_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competence
    ADD CONSTRAINT competence_parent_id_fkey FOREIGN KEY (parent_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2486 (class 2606 OID 26850)
-- Name: competences_requirement_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT competences_requirement_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2488 (class 2606 OID 26855)
-- Name: current_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT current_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2470 (class 2606 OID 26860)
-- Name: current_in_company_employment_id; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT current_in_company_employment_id FOREIGN KEY (current_in_company_employment_id) REFERENCES in_company_employment(idin_company_employment);


--
-- TOC entry 2490 (class 2606 OID 26865)
-- Name: department_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT department_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- TOC entry 2496 (class 2606 OID 26870)
-- Name: edrHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2497 (class 2606 OID 26875)
-- Name: edrHistory.employee_idEmployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrHistory"
    ADD CONSTRAINT "edrHistory.employee_idEmployee_fkey" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2498 (class 2606 OID 26880)
-- Name: edrNotes.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "edrNotes"
    ADD CONSTRAINT "edrNotes.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2493 (class 2606 OID 26885)
-- Name: edr_previous_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT edr_previous_edr_idedr_fkey FOREIGN KEY (previous_edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2499 (class 2606 OID 26890)
-- Name: employee_competence_assessment_assessment_idassessment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessment_idassessment_fkey FOREIGN KEY (assessment_idassessment) REFERENCES assessment(idassessment);


--
-- TOC entry 2500 (class 2606 OID 26895)
-- Name: employee_competence_assessment_assessor_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_assessor_idemployee_fkey FOREIGN KEY (assessor_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2501 (class 2606 OID 26900)
-- Name: employee_competence_assessment_competence_idcompetence_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_competence_idcompetence_fkey FOREIGN KEY (competence_idcompetence) REFERENCES competence(idcompetence);


--
-- TOC entry 2502 (class 2606 OID 26905)
-- Name: employee_competence_assessment_statement_idstatement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee_competence_assessment
    ADD CONSTRAINT employee_competence_assessment_statement_idstatement_fkey FOREIGN KEY (statement_idstatement) REFERENCES statement(idstatement);


--
-- TOC entry 2471 (class 2606 OID 26910)
-- Name: employee_department_iddepartment_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT employee_department_iddepartment_fkey FOREIGN KEY (department_iddepartment) REFERENCES department(iddepartment);


--
-- TOC entry 2489 (class 2606 OID 26915)
-- Name: employee_id_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY current_competence_assessment
    ADD CONSTRAINT "employee_id_TO_employee" FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2491 (class 2606 OID 26920)
-- Name: head_of_department_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY department
    ADD CONSTRAINT "head_of_department_idemployee_TO_employee" FOREIGN KEY (head_of_department_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2492 (class 2606 OID 26925)
-- Name: head_of_division_employee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY division
    ADD CONSTRAINT "head_of_division_employee_TO_employee" FOREIGN KEY (head_of_division_employee) REFERENCES employee(idemployee);


--
-- TOC entry 2494 (class 2606 OID 26930)
-- Name: immediate_manager_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "immediate_manager_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2481 (class 2606 OID 26935)
-- Name: immediate_manager_idemployee_TO_employee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY assessment
    ADD CONSTRAINT "immediate_manager_idemployee_TO_employee" FOREIGN KEY (immediate_manager_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2503 (class 2606 OID 26940)
-- Name: importHistory.edr_idEdr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.edr_idEdr_fkey" FOREIGN KEY (idedr) REFERENCES edr(idedr);


--
-- TOC entry 2504 (class 2606 OID 26945)
-- Name: importHistory.employee_idEmployee_fky; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY "importHistory"
    ADD CONSTRAINT "importHistory.employee_idEmployee_fky" FOREIGN KEY (idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2505 (class 2606 OID 26950)
-- Name: in_company_employment_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT in_company_employment_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2511 (class 2606 OID 26955)
-- Name: job_advertisement_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_advertisement
    ADD CONSTRAINT job_advertisement_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2472 (class 2606 OID 26960)
-- Name: job_applicant_present_idapplicant_professional_experience__fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_applicant
    ADD CONSTRAINT job_applicant_present_idapplicant_professional_experience__fkey FOREIGN KEY (present_idapplicant_professional_experience_record) REFERENCES applicant_professional_experience_record(idapplicant_professional_experience_record);


--
-- TOC entry 2512 (class 2606 OID 26965)
-- Name: job_application_job_advertisement_idjob_advertisement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_advertisement_idjob_advertisement_fkey FOREIGN KEY (job_advertisement_idjob_advertisement) REFERENCES job_advertisement(idjob_advertisement);


--
-- TOC entry 2513 (class 2606 OID 26970)
-- Name: job_application_job_applicant_idjob_applicant_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_application
    ADD CONSTRAINT job_application_job_applicant_idjob_applicant_fkey FOREIGN KEY (job_applicant_idjob_applicant) REFERENCES job_applicant(idjob_applicant);


--
-- TOC entry 2507 (class 2606 OID 26975)
-- Name: job_business_area_idbusiness_area_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_business_area_idbusiness_area_fkey FOREIGN KEY (business_area_idbusiness_area) REFERENCES business_area(idbusiness_area);


--
-- TOC entry 2487 (class 2606 OID 26980)
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY competences_requirement
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2506 (class 2606 OID 26985)
-- Name: job_idjob_TO_job; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY in_company_employment
    ADD CONSTRAINT "job_idjob_TO_job" FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2508 (class 2606 OID 26990)
-- Name: job_reporting_to_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT job_reporting_to_idemployee_fkey FOREIGN KEY (reporting_to_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2514 (class 2606 OID 26995)
-- Name: job_study_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job_study_min_requirements
    ADD CONSTRAINT job_study_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2515 (class 2606 OID 27000)
-- Name: organisational_position_company_idcompany_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY organisational_position
    ADD CONSTRAINT organisational_position_company_idcompany_fkey FOREIGN KEY (company_idcompany) REFERENCES company(idcompany);


--
-- TOC entry 2509 (class 2606 OID 27005)
-- Name: organisational_position_id_TO_organisational_position; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "organisational_position_id_TO_organisational_position" FOREIGN KEY (organisational_position_idorganisational_position) REFERENCES organisational_position(idorganisational_position);


--
-- TOC entry 2510 (class 2606 OID 27010)
-- Name: place_employment_id_TO_place_employment; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY job
    ADD CONSTRAINT "place_employment_id_TO_place_employment" FOREIGN KEY (place_employment_idplace_employment) REFERENCES place_employment(idplace_employment);


--
-- TOC entry 2516 (class 2606 OID 27015)
-- Name: professional_experience_min_requirements_job_idjob_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_min_requirements
    ADD CONSTRAINT professional_experience_min_requirements_job_idjob_fkey FOREIGN KEY (job_idjob) REFERENCES job(idjob);


--
-- TOC entry 2517 (class 2606 OID 27020)
-- Name: professional_experience_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY professional_experience_record
    ADD CONSTRAINT professional_experience_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2518 (class 2606 OID 27025)
-- Name: question_answer_edr_idedr_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY question_answer
    ADD CONSTRAINT question_answer_edr_idedr_fkey FOREIGN KEY (edr_idedr) REFERENCES edr(idedr);


--
-- TOC entry 2495 (class 2606 OID 27030)
-- Name: reviewed_employee_TO_empoyee; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY edr
    ADD CONSTRAINT "reviewed_employee_TO_empoyee" FOREIGN KEY (reviewed_employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2519 (class 2606 OID 27035)
-- Name: statement2; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY statement
    ADD CONSTRAINT statement2 FOREIGN KEY (competence_id) REFERENCES competence(idcompetence);


--
-- TOC entry 2520 (class 2606 OID 27040)
-- Name: study_record_employee_idemployee_fkey; Type: FK CONSTRAINT; Schema: public; Owner: comprofits
--

ALTER TABLE ONLY study_record
    ADD CONSTRAINT study_record_employee_idemployee_fkey FOREIGN KEY (employee_idemployee) REFERENCES employee(idemployee);


--
-- TOC entry 2635 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: comprofits
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 2637 (class 0 OID 0)
-- Dependencies: 170
-- Name: employee_idemployee_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE employee_idemployee_seq FROM postgres;
GRANT ALL ON SEQUENCE employee_idemployee_seq TO postgres;


--
-- TOC entry 2638 (class 0 OID 0)
-- Dependencies: 171
-- Name: employee; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee FROM PUBLIC;
REVOKE ALL ON TABLE employee FROM comprofits;
GRANT ALL ON TABLE employee TO comprofits;


--
-- TOC entry 2639 (class 0 OID 0)
-- Dependencies: 172
-- Name: job_applicant_idjob_applicant_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_applicant_idjob_applicant_seq FROM postgres;
GRANT ALL ON SEQUENCE job_applicant_idjob_applicant_seq TO postgres;


--
-- TOC entry 2640 (class 0 OID 0)
-- Dependencies: 173
-- Name: job_applicant; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_applicant FROM PUBLIC;
REVOKE ALL ON TABLE job_applicant FROM comprofits;
GRANT ALL ON TABLE job_applicant TO comprofits;


--
-- TOC entry 2641 (class 0 OID 0)
-- Dependencies: 175
-- Name: idapplicant_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idapplicant_competence_assessment_seq TO postgres;


--
-- TOC entry 2642 (class 0 OID 0)
-- Dependencies: 176
-- Name: applicant_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE applicant_competence_assessment FROM comprofits;
GRANT ALL ON TABLE applicant_competence_assessment TO comprofits;


--
-- TOC entry 2643 (class 0 OID 0)
-- Dependencies: 177
-- Name: xperience_record_idapplicant_professional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq FROM postgres;
GRANT ALL ON SEQUENCE xperience_record_idapplicant_professional_experience_record_seq TO postgres;


--
-- TOC entry 2644 (class 0 OID 0)
-- Dependencies: 178
-- Name: applicant_professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_professional_experience_record FROM comprofits;
GRANT ALL ON TABLE applicant_professional_experience_record TO comprofits;


--
-- TOC entry 2645 (class 0 OID 0)
-- Dependencies: 179
-- Name: idapplicant_study_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idapplicant_study_record_seq FROM postgres;
GRANT ALL ON SEQUENCE idapplicant_study_record_seq TO postgres;


--
-- TOC entry 2646 (class 0 OID 0)
-- Dependencies: 180
-- Name: applicant_study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE applicant_study_record FROM PUBLIC;
REVOKE ALL ON TABLE applicant_study_record FROM comprofits;
GRANT ALL ON TABLE applicant_study_record TO comprofits;


--
-- TOC entry 2647 (class 0 OID 0)
-- Dependencies: 181
-- Name: idassessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idassessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idassessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idassessment_seq TO postgres;


--
-- TOC entry 2648 (class 0 OID 0)
-- Dependencies: 182
-- Name: assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE assessment FROM PUBLIC;
REVOKE ALL ON TABLE assessment FROM comprofits;
GRANT ALL ON TABLE assessment TO comprofits;


--
-- TOC entry 2649 (class 0 OID 0)
-- Dependencies: 183
-- Name: business_area_idbusiness_area_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE business_area_idbusiness_area_seq FROM postgres;
GRANT ALL ON SEQUENCE business_area_idbusiness_area_seq TO postgres;


--
-- TOC entry 2650 (class 0 OID 0)
-- Dependencies: 184
-- Name: business_area; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE business_area FROM PUBLIC;
REVOKE ALL ON TABLE business_area FROM comprofits;
GRANT ALL ON TABLE business_area TO comprofits;


--
-- TOC entry 2651 (class 0 OID 0)
-- Dependencies: 185
-- Name: company_idcompany_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE company_idcompany_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE company_idcompany_seq FROM postgres;
GRANT ALL ON SEQUENCE company_idcompany_seq TO postgres;


--
-- TOC entry 2663 (class 0 OID 0)
-- Dependencies: 186
-- Name: company; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE company FROM PUBLIC;
REVOKE ALL ON TABLE company FROM comprofits;
GRANT ALL ON TABLE company TO comprofits;


--
-- TOC entry 2664 (class 0 OID 0)
-- Dependencies: 187
-- Name: idcompetence_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_seq FROM postgres;
GRANT ALL ON SEQUENCE idcompetence_seq TO postgres;


--
-- TOC entry 2665 (class 0 OID 0)
-- Dependencies: 188
-- Name: competence; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence FROM PUBLIC;
REVOKE ALL ON TABLE competence FROM comprofits;
GRANT ALL ON TABLE competence TO comprofits;


--
-- TOC entry 2666 (class 0 OID 0)
-- Dependencies: 189
-- Name: idcompetence_goal_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetence_goal_seq FROM postgres;
GRANT ALL ON SEQUENCE idcompetence_goal_seq TO postgres;


--
-- TOC entry 2667 (class 0 OID 0)
-- Dependencies: 190
-- Name: competence_goal; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competence_goal FROM PUBLIC;
REVOKE ALL ON TABLE competence_goal FROM comprofits;
GRANT ALL ON TABLE competence_goal TO comprofits;


--
-- TOC entry 2668 (class 0 OID 0)
-- Dependencies: 191
-- Name: idcompetences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcompetences_requirement FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcompetences_requirement FROM postgres;
GRANT ALL ON SEQUENCE idcompetences_requirement TO postgres;


--
-- TOC entry 2669 (class 0 OID 0)
-- Dependencies: 192
-- Name: competences_requirement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE competences_requirement FROM PUBLIC;
REVOKE ALL ON TABLE competences_requirement FROM comprofits;
GRANT ALL ON TABLE competences_requirement TO comprofits;


--
-- TOC entry 2670 (class 0 OID 0)
-- Dependencies: 193
-- Name: idcurrent_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idcurrent_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idcurrent_competence_assessment_seq TO postgres;


--
-- TOC entry 2671 (class 0 OID 0)
-- Dependencies: 194
-- Name: current_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE current_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE current_competence_assessment FROM comprofits;
GRANT ALL ON TABLE current_competence_assessment TO comprofits;


--
-- TOC entry 2672 (class 0 OID 0)
-- Dependencies: 195
-- Name: iddepartment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE iddepartment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE iddepartment_seq FROM postgres;
GRANT ALL ON SEQUENCE iddepartment_seq TO postgres;


--
-- TOC entry 2673 (class 0 OID 0)
-- Dependencies: 196
-- Name: department; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE department FROM PUBLIC;
REVOKE ALL ON TABLE department FROM comprofits;
GRANT ALL ON TABLE department TO comprofits;


--
-- TOC entry 2674 (class 0 OID 0)
-- Dependencies: 197
-- Name: division_iddivision_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE division_iddivision_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE division_iddivision_seq FROM postgres;
GRANT ALL ON SEQUENCE division_iddivision_seq TO postgres;


--
-- TOC entry 2675 (class 0 OID 0)
-- Dependencies: 198
-- Name: division; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE division FROM PUBLIC;
REVOKE ALL ON TABLE division FROM comprofits;
GRANT ALL ON TABLE division TO comprofits;


--
-- TOC entry 2676 (class 0 OID 0)
-- Dependencies: 199
-- Name: idedr_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idedr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idedr_seq FROM postgres;
GRANT ALL ON SEQUENCE idedr_seq TO postgres;


--
-- TOC entry 2677 (class 0 OID 0)
-- Dependencies: 200
-- Name: edr; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE edr FROM PUBLIC;
REVOKE ALL ON TABLE edr FROM comprofits;
GRANT ALL ON TABLE edr TO comprofits;


--
-- TOC entry 2679 (class 0 OID 0)
-- Dependencies: 205
-- Name: idemployee_competence_assessment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idemployee_competence_assessment_seq FROM postgres;
GRANT ALL ON SEQUENCE idemployee_competence_assessment_seq TO postgres;


--
-- TOC entry 2680 (class 0 OID 0)
-- Dependencies: 206
-- Name: employee_competence_assessment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE employee_competence_assessment FROM PUBLIC;
REVOKE ALL ON TABLE employee_competence_assessment FROM comprofits;
GRANT ALL ON TABLE employee_competence_assessment TO comprofits;


--
-- TOC entry 2681 (class 0 OID 0)
-- Dependencies: 207
-- Name: essional_experience_record_idprofessional_experience_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq FROM postgres;
GRANT ALL ON SEQUENCE essional_experience_record_idprofessional_experience_record_seq TO postgres;


--
-- TOC entry 2682 (class 0 OID 0)
-- Dependencies: 208
-- Name: idin_company_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idin_company_employment_seq FROM postgres;
GRANT ALL ON SEQUENCE idin_company_employment_seq TO postgres;


--
-- TOC entry 2683 (class 0 OID 0)
-- Dependencies: 209
-- Name: idjob_advertisement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_advertisement_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_advertisement_seq TO postgres;


--
-- TOC entry 2684 (class 0 OID 0)
-- Dependencies: 210
-- Name: idjob_application_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_application_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_application_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_application_seq TO postgres;


--
-- TOC entry 2685 (class 0 OID 0)
-- Dependencies: 211
-- Name: idjob_study_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idjob_study_min_requirements_seq FROM postgres;
GRANT ALL ON SEQUENCE idjob_study_min_requirements_seq TO postgres;


--
-- TOC entry 2686 (class 0 OID 0)
-- Dependencies: 212
-- Name: idprofessional_experience_min_requirements_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idprofessional_experience_min_requirements_seq FROM postgres;
GRANT ALL ON SEQUENCE idprofessional_experience_min_requirements_seq TO postgres;


--
-- TOC entry 2687 (class 0 OID 0)
-- Dependencies: 213
-- Name: idquestion_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idquestion_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idquestion_seq FROM postgres;
GRANT ALL ON SEQUENCE idquestion_seq TO postgres;


--
-- TOC entry 2688 (class 0 OID 0)
-- Dependencies: 214
-- Name: idstudy_record_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE idstudy_record_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE idstudy_record_seq FROM postgres;
GRANT ALL ON SEQUENCE idstudy_record_seq TO postgres;


--
-- TOC entry 2690 (class 0 OID 0)
-- Dependencies: 217
-- Name: in_company_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE in_company_employment FROM PUBLIC;
REVOKE ALL ON TABLE in_company_employment FROM comprofits;
GRANT ALL ON TABLE in_company_employment TO comprofits;


--
-- TOC entry 2691 (class 0 OID 0)
-- Dependencies: 218
-- Name: job_idjob_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE job_idjob_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE job_idjob_seq FROM postgres;
GRANT ALL ON SEQUENCE job_idjob_seq TO postgres;


--
-- TOC entry 2693 (class 0 OID 0)
-- Dependencies: 219
-- Name: job; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job FROM PUBLIC;
REVOKE ALL ON TABLE job FROM comprofits;
GRANT ALL ON TABLE job TO comprofits;


--
-- TOC entry 2695 (class 0 OID 0)
-- Dependencies: 220
-- Name: job_advertisement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_advertisement FROM PUBLIC;
REVOKE ALL ON TABLE job_advertisement FROM comprofits;
GRANT ALL ON TABLE job_advertisement TO comprofits;


--
-- TOC entry 2696 (class 0 OID 0)
-- Dependencies: 221
-- Name: job_application; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_application FROM PUBLIC;
REVOKE ALL ON TABLE job_application FROM comprofits;
GRANT ALL ON TABLE job_application TO comprofits;


--
-- TOC entry 2697 (class 0 OID 0)
-- Dependencies: 222
-- Name: job_study_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE job_study_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE job_study_min_requirements FROM comprofits;
GRANT ALL ON TABLE job_study_min_requirements TO comprofits;


--
-- TOC entry 2698 (class 0 OID 0)
-- Dependencies: 223
-- Name: organisational_position_idorganisational_position_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE organisational_position_idorganisational_position_seq FROM postgres;
GRANT ALL ON SEQUENCE organisational_position_idorganisational_position_seq TO postgres;


--
-- TOC entry 2699 (class 0 OID 0)
-- Dependencies: 224
-- Name: organisational_position; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE organisational_position FROM PUBLIC;
REVOKE ALL ON TABLE organisational_position FROM comprofits;
GRANT ALL ON TABLE organisational_position TO comprofits;


--
-- TOC entry 2700 (class 0 OID 0)
-- Dependencies: 225
-- Name: place_employment_idplace_employment_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE place_employment_idplace_employment_seq FROM postgres;
GRANT ALL ON SEQUENCE place_employment_idplace_employment_seq TO postgres;


--
-- TOC entry 2701 (class 0 OID 0)
-- Dependencies: 226
-- Name: place_employment; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE place_employment FROM PUBLIC;
REVOKE ALL ON TABLE place_employment FROM comprofits;
GRANT ALL ON TABLE place_employment TO comprofits;


--
-- TOC entry 2702 (class 0 OID 0)
-- Dependencies: 227
-- Name: professional_experience_min_requirements; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_min_requirements FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_min_requirements FROM comprofits;
GRANT ALL ON TABLE professional_experience_min_requirements TO comprofits;


--
-- TOC entry 2703 (class 0 OID 0)
-- Dependencies: 228
-- Name: professional_experience_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE professional_experience_record FROM PUBLIC;
REVOKE ALL ON TABLE professional_experience_record FROM comprofits;
GRANT ALL ON TABLE professional_experience_record TO comprofits;


--
-- TOC entry 2704 (class 0 OID 0)
-- Dependencies: 229
-- Name: question_answer; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE question_answer FROM PUBLIC;
REVOKE ALL ON TABLE question_answer FROM comprofits;
GRANT ALL ON TABLE question_answer TO comprofits;


--
-- TOC entry 2705 (class 0 OID 0)
-- Dependencies: 230
-- Name: statement_idstatement_seq; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE statement_idstatement_seq FROM postgres;
GRANT ALL ON SEQUENCE statement_idstatement_seq TO postgres;


--
-- TOC entry 2706 (class 0 OID 0)
-- Dependencies: 231
-- Name: statement; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE statement FROM PUBLIC;
REVOKE ALL ON TABLE statement FROM comprofits;
GRANT ALL ON TABLE statement TO comprofits;


--
-- TOC entry 2707 (class 0 OID 0)
-- Dependencies: 232
-- Name: study_record; Type: ACL; Schema: public; Owner: comprofits
--

REVOKE ALL ON TABLE study_record FROM PUBLIC;
REVOKE ALL ON TABLE study_record FROM comprofits;
GRANT ALL ON TABLE study_record TO comprofits;


-- Completed on 2014-10-29 10:18:02 CET

--
-- PostgreSQL database dump complete
--

