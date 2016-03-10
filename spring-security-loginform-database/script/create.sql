create database `my_hours_report`;

use my_hours_report;

create table `country` (
  `id` int(11) not null,
  `country` varchar(100) default null,
  primary key (`id`)
) engine=innodb default charset=latin1;

create table `manager` (
  `id` int(11) not null auto_increment,
  `name` varchar(100) default null,
  primary key (`id`)
) engine=innodb default charset=latin1;

CREATE TABLE `employee` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `id_country` int(11) DEFAULT NULL,
  `sector` varchar(100) DEFAULT NULL,
  `jrss` varchar(100) DEFAULT NULL,
  `id_manager` int(11) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `employee_manager_idx` (`id_manager`),
  KEY `employee_country_idx` (`id_country`),
  CONSTRAINT `employee_country` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `employee_manager` FOREIGN KEY (`id_manager`) REFERENCES `manager` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


create table `week` (
  `id` int(10) not null auto_increment,
  `num_week` int(10) default null,
  `start_date` date not null,
  `end_date` date not null,
  primary key (`id`) using btree
) engine=innodb auto_increment=23 default charset=latin1;

create table `assignment` (
  `id` int(11) not null auto_increment,
  `project_name` varchar(100) default null,
  `client_name` varchar(100) default null,
  `id_country` int(11) default null,
  `industry` varchar(100) default null,
  `category` varchar(100) default null,
  primary key (`id`),
  unique key `id_unique` (`id`),
  key `assignment_country_idx` (`id_country`),
  constraint `assignment_country` foreign key (`id_country`) references `country` (`id`) on delete no action on update no action
) engine=innodb auto_increment=9 default charset=latin1;

CREATE TABLE `work` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_employee` varchar(10) NOT NULL,
  `id_week` int(10) NOT NULL,
  `id_assignment` int(10) NOT NULL,
  `hours_x_week` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `work_employee_idx` (`id_employee`),
  KEY `work_week_idx` (`id_week`),
  KEY `work_assignment_idx` (`id_assignment`),
  CONSTRAINT `work_assignment` FOREIGN KEY (`id_assignment`) REFERENCES `assignment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `work_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `work_week` FOREIGN KEY (`id_week`) REFERENCES `week` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31556 DEFAULT CHARSET=latin1;


create table `work_history` (
  `id` int(10) not null auto_increment,
  `id_employee` varchar(10) not null,
  `id_week` int(10) not null,
  `id_assignment` int(10) not null,
  `hours_x_week` int(10) not null,
  primary key (`id`) using btree,
  key `work_his_employee_idx` (`id_employee`),
  key `work_his_week_idx` (`id_week`),
  key `work_his_assignment_idx` (`id_assignment`),
  constraint `work_his_assignment` foreign key (`id_assignment`) references `assignment` (`id`) on delete no action on update no action,
  constraint `work_his_employee` foreign key (`id_employee`) references `employee` (`id`) on delete no action on update no action,
  constraint `work_his_week` foreign key (`id_week`) references `week` (`id`) on delete no action on update no action
) engine=innodb auto_increment=1 default charset=latin1;

create table `users` (
  `id` int(12) not null auto_increment,
  `user` varchar(50) not null,
  `user_name` varchar(20) not null,
  `user_password` varchar(10) not null,
  `enabled` binary(1) default null,
  `mail` varchar(100) DEFAULT NULL,
  primary key (`id`)
) engine=innodb default charset=latin1;

create table `roles` (
  `id` int(12) not null auto_increment,
  `roles` varchar(20) not null,
  primary key (`id`)
) engine=innodb default charset=latin1;

create table `users_roles` (
  `id_user` int(12) not null,
  `id_roles` int(12) not null,
  key `id_user` (`id_user`),
  key `id_roles` (`id_roles`),
  constraint `users_roles_ibfk_1` foreign key (`id_user`) references `users` (`id`),
  constraint `users_roles_ibfk_2` foreign key (`id_roles`) references `roles` (`id`)
) engine=innodb default charset=latin1; 

create table `holiday`(
    `id` int not null unique auto_increment primary key,
    `holiday` varchar(100) not null,
    `date` date not null
);

create table country_has_holiday(
    `id_country` int not null,
    `id_holiday` int not null,
    foreign key (id_country) references country (id),
    foreign key (id_holiday) references holiday (id)    
); 

DELIMITER //
CREATE PROCEDURE `load_work_history`(in fecha date)
BEGIN
	SET SQL_SAFE_UPDATES = 0;
	
    DELETE from work_history 
	where work_history.id_week in (SELECT week.id
	from week
	where week.end_date >= fecha);

    insert into work_history (id_employee,id_week,id_assignment,hours_x_week)
    select id_employee,id_week,id_assignment,hours_x_week from work;

    delete from work;
    
    SET SQL_SAFE_UPDATES = 1;
END
// DELIMITER INSERT INTO `my_hours_report`.`users`
