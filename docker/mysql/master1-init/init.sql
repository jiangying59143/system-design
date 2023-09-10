CREATE USER 'master1-slave1'@'172.18.0.3' IDENTIFIED BY 'master1-slave1';
grant replication slave, replication client on *.* to 'master1-slave1'@'172.18.0.3';
CREATE USER 'master1-slave2'@'172.18.0.4' IDENTIFIED BY 'master1-slave2';
grant replication slave, replication client on *.* to 'master1-slave2'@'172.18.0.4';
CREATE USER 'canal'@'172.18.0.5' IDENTIFIED BY 'canal';
grant select, replication slave, replication client on *.* to 'canal'@'172.18.0.5';
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'master1-slave1'@'172.18.0.3';
SHOW GRANTS FOR 'master1-slave2'@'172.18.0.4';
SHOW GRANTS FOR 'canal'@'172.18.0.5';

SHOW MASTER STATUS;