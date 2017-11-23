# CRUDjdbc
 
Консольное приложение, которое использует БД и позволяет выполнять CRUD (CREATE, READ, UPDATE, DELETE) операции для таблиц:
- developers
- skills
- companies
- customers
- projects
 
Пример: 
Создать разработчика, добавить ему навыки. 
Создать проект, и добавить в данный проект разработчиков. 
 

Необходимо придерживаться паттерна MVC.
Необходимо обработать исключительные ситуации - выход из приложения только по команде пользователя.
Все классы должны быть грамотно проименованы и разложены по пакетам.
Рекомендуется активно использовать интерфейсы, абстрактные классы, generics и шаблоны проектирования (Factory method, Builder, etc.)

## Инструкция по разворачиванию
1. Создать схему базы данных с помощью [sql скрипта](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/resources/init.sql);
2. Заполнить ip адрес базы данных, имя пользователя, пароль в [файле](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/java/dao/utils/ConnectionUtil.java)
3. Запустить [приложение](https://github.com/kurotkin/CRUDjdbc/blob/master/src/main/java/Program.java) 
