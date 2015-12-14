-- Ushbu patchlarni apply qilishdan oldin, databasedagi
-- barcha jadvallarni o`chiring keyin projectni run qiling
insert into tag(id, tag_name) values(1, 'java');
insert into tag(id, tag_name) values(2, 'javascript');
insert into tag(id, tag_name) values(3, 'ajax');
insert into tag(id, tag_name) values(4, 'spring');
insert into tag(id, tag_name) values(5, 'hibernate');
insert into tag(id, tag_name) values(6, 'angular-js');

insert into my_user(id, full_name, user_name, password) values(1, 'Administrator', 'admin', '12345');

insert into question(id, question_title, question_content, question_owner_id) values(1, 'Test Question 1', 'Lorem ipsum dolor...', 1);
insert into question(id, question_title, question_content, question_owner_id) values(2, 'Test Question 2', 'Lorem ipsum dolor...', 1);
insert into question(id, question_title, question_content, question_owner_id) values(3, 'Test Question 3', 'Lorem ipsum dolor...', 1);
insert into question(id, question_title, question_content, question_owner_id) values(4, 'Test Question 4', 'Lorem ipsum dolor...', 1);

insert into question_tags(question_id, tags_id) values(1, 1);
insert into question_tags(question_id, tags_id) values(1, 3);
insert into question_tags(question_id, tags_id) values(1, 4);
insert into question_tags(question_id, tags_id) values(2, 2);
insert into question_tags(question_id, tags_id) values(2, 5);
insert into question_tags(question_id, tags_id) values(2, 6);
insert into question_tags(question_id, tags_id) values(3, 2);
insert into question_tags(question_id, tags_id) values(3, 5);
insert into question_tags(question_id, tags_id) values(4, 4);
insert into question_tags(question_id, tags_id) values(4, 6);
insert into question_tags(question_id, tags_id) values(4, 3);

insert into answer(id) values(1);
insert into answer(id) values(2);
insert into answer(id) values(3);
insert into answer(id) values(4);

insert into question_answers(question_id, answers_id) values(1, 1);
insert into question_answers(question_id, answers_id) values(1, 2);
insert into question_answers(question_id, answers_id) values(2, 3);
insert into question_answers(question_id, answers_id) values(4, 4);
