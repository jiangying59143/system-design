CREATE USER 'master-slave1'@'master-slave1' IDENTIFIED BY 'master-slave1';
grant replication slave, replication client on *.* to 'master-slave1'@'master-slave1';
CREATE USER 'master-slave2'@'master-slave2' IDENTIFIED BY 'master-slave2';
grant replication slave, replication client on *.* to 'master-slave2'@'master-slave2';
-- CREATE USER 'canal'@'canal' IDENTIFIED BY 'canal';
-- grant select, replication slave, replication client on *.* to 'canal'@'canal';
FLUSH PRIVILEGES;

-- SHOW GRANTS FOR 'master-slave1'@'master-slave1';
-- SHOW GRANTS FOR 'master-slave2'@'master-slave2';
-- SHOW GRANTS FOR 'canal'@'canal';
--
-- SHOW MASTER STATUS;
