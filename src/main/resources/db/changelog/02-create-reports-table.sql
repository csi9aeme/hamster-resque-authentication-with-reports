create table reports (
date_of_measure date,
weight float(53) not null,
host_id bigint,
id bigint not null,
hamster_name varchar(255),
report_text varchar(255),
primary key (id)) engine=InnoDB
