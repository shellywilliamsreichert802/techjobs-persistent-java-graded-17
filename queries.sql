--Part 1
/*
Table: job
Columns:
- id: int (Primary Key)
- employer: varchar(255)
- name: varchar(255)
- skills: varchar(255)
*/

--Part 2

SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3

DROP TABLE job;

--Part 4

--SELECT DISTINCT s.name
--FROM skill s
--JOIN job_skill js ON s.id = js.skill_id
--ORDER BY s.name ASC;

SELECT * FROM skill
[LEFT|INNER] JOIN job_skills ON (skill.id = job_skills.skills_id OR job_skills.skills_id = skill.id)
[WHERE job_skills.jobs_id IS NOT NULL]
ORDER BY name ASC;
