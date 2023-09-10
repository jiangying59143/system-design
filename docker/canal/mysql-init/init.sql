CREATE USER canal@'172.20.0.3' IDENTIFIED BY 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'172.20.0.3';
FLUSH PRIVILEGES;

drop database if exists `test`;
CREATE DATABASE `test`;

USE test;

drop table if exists test;

CREATE TABLE `test` (
                        `id` INT(11) NOT NULL,
                        `name` VARCHAR(50) NOT NULL
)
    ENGINE=InnoDB
;