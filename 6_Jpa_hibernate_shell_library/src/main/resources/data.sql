insert into authors (id, `name`) values (1, 'Гоголь');
insert into authors (id, `name`) values (2, 'Пушкин');
insert into authors (id, `name`) values (3, 'Ильф');
insert into authors (id, `name`) values (4, 'Петров');
insert into authors (id, `name`) values (5, 'Толкин');
insert into authors (id, `name`) values (6, 'Автор-для-удаления');

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

insert into comments (id, `comment`, book_id) values (1, 'Гоголь гениален!', 1);
insert into comments (id, `comment`, book_id) values (2, 'Паночка - гори в специальном котле(((((', 1);
insert into comments (id, `comment`, book_id) values (3, 'Сколько по книге снято гениальных фильмов', 2);
insert into comments (id, `comment`, book_id) values (4, 'Кто знает такую книгу?', 3);
insert into comments (id, `comment`, book_id) values (5, 'Первый раз слышу', 3);
insert into comments (id, `comment`, book_id) values (6, 'Я тожу((((', 3);
insert into comments (id, `comment`, book_id) values (7, 'Толкин фореве))))', 4);
insert into comments (id, `comment`, book_id) values (8, 'Думаю также', 4);