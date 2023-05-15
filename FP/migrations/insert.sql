insert into "user" (chat_id, username, first_name, last_name)
values (42, 'testUser42', 'test', 'testov'),
       (34, 'testUser34', 'test', 'testov'),
       (65, 'testUser65', 'test', 'testov'),
       (45, 'testUser45', 'test', 'testov'),
       (523, 'testUser523', 'test', 'testov'),
       (645, 'testUser645', 'test', 'testov'),
       (7452, 'testUser7452', 'test', 'testov'),
       (44562, 'testUser44562', 'test', 'testov'),
       (423452, 'testUser423452', 'test', 'testov'),
       (2, 'testUser2', 'test', 'testov');

insert into "link" (url, updated_at)
values ('https://stackoverflow.com/questions/14141266/postgresql-foreign-key-on-delete-cascade', '2022-05-19 15:13:27'),
       ('https://github.com/linus/doctest','2022-01-31 13:13:50'),
       ('https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file','2023-03-27 09:58:45');

insert into "user_link" (link_id, chat_id)
values (1,42),
       (1,45),
       (1,2),
       (1,7452),
       (2,44562),
       (2,423452),
       (2,645),
       (2,523),
       (3,34),
       (3,65),
       (3,7452),
       (3,45),
       (4,42),
       (4,45),
       (4,2),
       (4,7452);



