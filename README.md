Есть база данных Skillbox состоящая из таблиц:

students(id, name, age, registration_date) courses(id, name, duration, type, description, teacher_id, students_count, price, price_per_hour) teachers(id, name, salary, age) subscription(student_id, course_id, subscription_date) purchaseList(student_name, course_name, price, subscription_date, id, name, student_id, course_id)

Задание 1. Cоздайте entity таблицы Курсы в вашем проекте и напишите код, выводящий информацию о каком-нибудь из курсов.

Задание 2. Создать все Entity таблиц и сделать связи между ними для всей базы данных.

Задание 3. Есть таблица PurchaseList, в которой указано, какие студенты какие курсы купили, но там указаны только имена студентов и названия курсов. Нужно создать таблицу связку course_id и student_id и написать код, который ее заполнит на основании таблицы PurchaseList.