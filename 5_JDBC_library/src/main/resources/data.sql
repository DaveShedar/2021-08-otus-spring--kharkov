insert into authors (id, `name`) values (1, 'Гоголь');
insert into authors (id, `name`) values (2, 'Пушкин');
insert into authors (id, `name`) values (3, 'Ильф');
insert into authors (id, `name`) values (4, 'Петров');
insert into authors (id, `name`) values (5, 'Толкин');

insert into genres (id, `name`) values (1, 'Сказка');
insert into genres (id, `name`) values (2, 'Мистика');
insert into genres (id, `name`) values (3, 'Приключение');
insert into genres (id, `name`) values (4, 'Философия');

insert into books (id, `title`, genre_id) values (1, 'Вечера на хуторе близ Диканьки', 2);
insert into books (id, `title`, genre_id) values (2, 'Двенадцать стульев', 4);
insert into books (id, `title`, genre_id) values (3, 'Кукумбер', 1);
insert into books (id, `title`, genre_id) values (4, 'Хоббит', 3);

insert into books_authors (book_id, author_id) values (1, 1);
insert into books_authors (book_id, author_id) values (2, 3);
insert into books_authors (book_id, author_id) values (2, 4);
insert into books_authors (book_id, author_id) values (3, 2);
insert into books_authors (book_id, author_id) values (4, 5);
