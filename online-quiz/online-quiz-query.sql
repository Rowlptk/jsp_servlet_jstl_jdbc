create database if not exists `online_quiz`;

use online_quiz;

drop table if exists questions;

create table questions (
`id` int(11) not null auto_increment,
`question` varchar(255) default null,
`option_one` varchar(100) default null,
`option_two` varchar(100) default null,
`option_three` varchar(100) default null,
`option_Four` varchar(100) default null,
`answer` varchar(100) default null,
primary key(`id`)
) engine=InnoDB auto_increment=6 default charset=latin1; 

lock tables `questions` write;
insert into `questions` values
(1, 'Extreme old age when a man behaves like a fool', 
'Imbecility', 'Senility', 'Dotage', 'Superannuation','Dotage'),

(2, 'That which cannot be corrected',
'Unintelligible', 'Indelible', 'Illegible', 'Incorrigible', 'Incorrigible'),

(3, 'The study of ancient societies',
'Anthropology', 'Archaeology', 'History', 'Ethnology', 'Archaeology'),

(4, 'A person of good understanding knowledge and reasoning power',
'Expert', 'Intellectual', 'Snob', 'Literate', 'Intellectual'),

(5, 'A person who insists on something',
'Disciplinarian', 'Stickler', 'Instantaneous', 'Boaster', 'Stickler');
unlock tables;

select * from questions;
