-- ***********************
-- Name: Hsueh Chih Liu
-- ID: 116131186
-- Date: 5/21/2019
-- Purpose: Lab 2 DBS301
-- ***********************

-- Q1 
-- Display the employee_id, last name and salary of employees 
-- earning in the range of $8,000 to $10000 inclusive. 
-- Sort the output by top salaries first and then by last name.

select employee_id, last_name, salary
from employees
where salary between 8000 and 10000 
order by salary desc, last_name  ;


-- Q2
--	Modify previous query (#1) so that additional condition is to display
--  only if they work as Programmers or Sales Representatives. Use same sorting as before.
select employee_id, last_name, salary
from employees
where salary between 8000 and 10000
    and  (job_id like 'IT%'
    or  job_id like 'SA_R%')
order by salary desc, last_name  ;

-- Q3
--	The Human Resources department wants to find high salary and low salary employees. 
-- Modify previous query (#2) so that it displays the same job titles but for 
-- people who earn outside the range of $8,000 to $11000 exclusive. 
--Use same sorting as before.
select employee_id, last_name, salary
from employees
where (salary < 8000 or
      salary > 11000 )
    and  (job_id like 'IT%'
      or  job_id like 'SA_R%')
order by salary desc, last_name ;
-- Q4
-- The company needs a list of long term employees,
-- in order to give them a thank you dinner. 
-- Display the last name, job_id and salary of employees hired after 2016. 
-- List the most recently hired employees first.

select last_name, job_id, salary, hire_date
from employees
where hire_date >= '01-JAN-16'
order by  hire_date desc ;
-- Q5
--	Modify previous query (#4) so that it displays only employees
-- earning more than $12,000 and hired before 2017. \
-- List the output by job title alphabetically and then by highest paid employees.
select last_name, job_id, salary, hire_date
from employees
where hire_date < '01-JAN-17' and 
        salary > 12000
order by  job_id, salary desc ;
-- Q6
--Display the job titles and full names of employees 
--whose first name contains an ‘c’ or ‘C’ anywhere. 

select job_id "Job title", first_name ||' '|| last_name "Full name"
from employees
where lower(first_name) like '%c%'; 
-- Q7
-- Create a report to display last name, 
-- salary, and commission percent for all 
-- employees that earn a commission and a salary 
-- less than 9000.
select  last_name, salary, commission_pct
from employees
where salary < 9000 and 
        commission_pct is not null;

-- Q8
-- Do the same as question 7, 
-- but put the report in order of descending salaries.
select  last_name, salary, commission_pct
from employees
where salary < 9000 and 
        commission_pct is not null
order by salary desc;
-- Q9
--•	Do the same as 8, 
-- but use a numeric value instead of a 
-- column name to do the sorting.
select  last_name, salary, commission_pct
from employees
where salary < 9000 and 
        commission_pct is not null
order by 2 desc ;
