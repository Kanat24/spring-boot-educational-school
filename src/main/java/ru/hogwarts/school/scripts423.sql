SELECT student.age, student.name, faculty.name
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

SELECT  student.name, student.age, avatar.data
FROM avatar
INNER JOIN student on avatar.student_id = student.id;