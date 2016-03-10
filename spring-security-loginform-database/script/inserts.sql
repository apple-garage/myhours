use my_hours_report;

/* paises */
insert into country values (1,'Argentina');
insert into country values (2,'Chile');
insert into country values (3,'Colombia');
insert into country values (4,'Ecuador');
insert into country values (5,'Peru');
insert into country values (6,'Uruguay');
insert into country values (7,'Venezuela');

/* semanas */
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (1,1,'2015-12-26','2016-01-01');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (2,2,'2016-01-02','2016-01-08');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (3,3,'2016-01-09','2016-01-15');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (4,4,'2016-01-16','2016-01-22');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (5,5,'2016-01-23','2016-01-29');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (6,6,'2016-01-30','2016-02-05');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (7,7,'2016-02-06','2016-02-12');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (8,8,'2016-02-13','2016-02-19');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (9,9,'2016-02-20','2016-02-26');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (10,10,'2016-02-27','2016-03-04');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (11,11,'2016-03-05','2016-03-11');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (12,12,'2016-03-12','2016-03-18');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (13,13,'2016-03-19','2016-03-25');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (14,14,'2016-03-26','2016-04-01');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (15,15,'2016-04-02','2016-04-08');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (16,16,'2016-04-09','2016-04-15');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (17,17,'2016-04-16','2016-04-22');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (18,18,'2016-04-23','2016-04-29');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (19,19,'2016-04-30','2016-05-06');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (20,20,'2016-05-07','2016-05-13');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (21,21,'2016-05-14','2016-05-20');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (22,22,'2016-05-21','2016-05-27');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (23,23,'2016-05-28','2016-06-03');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (24,24,'2016-06-04','2016-06-10');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (25,25,'2016-06-11','2016-06-17');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (26,26,'2016-06-18','2016-06-24');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (27,27,'2016-06-25','2016-07-01');
insert into `week` (`id`,`num_week`,`start_date`,`end_date`) values (28,28,'2016-07-02','2016-07-08');

/* holiday */
insert into holiday (holiday, date) values ('año nuevo', '2016-01-01');
insert into holiday (holiday, date) values ('carnaval', '2016-02-08');
insert into holiday (holiday, date) values ('carnaval', '2016-02-09');
insert into holiday (holiday, date) values ('dia de la memoria - jueves santo', '2016-03-24');
insert into holiday (holiday, date) values ('viernes santo', '2016-03-25');

/* country_has_holiday */
insert into country_has_holiday values (1,1);
insert into country_has_holiday values (1,2); 

/* users */
INSERT INTO `my_hours_report`.`users`(`user`,`user_name`,`user_password`,`enabled`,`mail`)
VALUES("admin","Usuario administrador","admin",1,"admin@ar.ibm.com");
INSERT INTO `my_hours_report`.`users`(`user`,`user_name`,`user_password`,`enabled`,`mail`)
VALUES("config","Usuario configurador","config",1,"prueba@ar.ibm.com");
INSERT INTO `my_hours_report`.`users`(`user`,`user_name`,`user_password`,`enabled`,`mail`)
VALUES("user","Usuario usuario","user",1,"prueba@ar.ibm.com");

/* roles */
INSERT INTO roles (`roles`) VALUES ("ROLE_ADMIN");
INSERT INTO roles (`roles`) VALUES ("ROLE_USER");
INSERT INTO roles (`roles`) VALUES ("ROLE_CONFIG");

/* users_roles */

