create database todolist; 

use todolist; 

create table user(
    user_id VARCHAR(8) NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    name VARCHAR(50)
);

create table task (
    task_id INT auto_increment NOT NULL,
    description VARCHAR(255),
    priority INT NOT NULL, 
    due_date DATE NOT NULL, 
    user_id VARCHAR(8) NOT NULL, 
    primary key(task_id),
    constraint fk_user_id 
    foreign key (user_id) 
    references user (user_id)
); 