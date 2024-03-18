CREATE USER 'master-slave1'@'master-slave1' IDENTIFIED BY 'master-slave1';
grant replication slave, replication client on *.* to 'master-slave1'@'master-slave1';
CREATE USER 'master-slave2'@'master-slave2' IDENTIFIED BY 'master-slave2';
grant replication slave, replication client on *.* to 'master-slave2'@'master-slave2';
-- CREATE USER 'canal'@'172.18.0.5' IDENTIFIED BY 'canal';
-- grant select, replication slave, replication client on *.* to 'canal'@'172.18.0.5';
FLUSH PRIVILEGES;

-- SHOW GRANTS FOR 'master1-slave1'@'172.18.0.3';
-- SHOW GRANTS FOR 'master1-slave2'@'172.18.0.4';
-- SHOW GRANTS FOR 'canal'@'172.18.0.5';
--
-- SHOW MASTER STATUS;
