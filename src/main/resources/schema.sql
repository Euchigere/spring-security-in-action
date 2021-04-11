-- Data base schema for excercise 3
-- CREATE TABLE IF NOT EXISTS `users` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `username` VARCHAR(45) NOT NULL,
--     `password` VARCHAR(45) NOT NULL,
--     `enabled` INT NOT NULL,
--     PRIMARY KEY (`id`));
--
-- CREATE TABLE IF NOT EXISTS `authorities` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `username` VARCHAR(45) NOT NULL,
--     `authority` VARCHAR(45) NOT NULL,
--     PRIMARY KEY (`id`));


-- Data base data for exercise 4
-- CREATE TABLE IF NOT EXISTS `user` (
--    `id` INT NOT NULL AUTO_INCREMENT,
--    `username` VARCHAR(45) NOT NULL,
--     `password` TEXT NOT NULL,
--     `algorithm` VARCHAR(45) NOT NULL,
--     PRIMARY KEY (`id`));
--
-- CREATE TABLE IF NOT EXISTS `authority` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(45) NOT NULL,
--     `user` INT NOT NULL,
--     PRIMARY KEY (`id`));
--
-- CREATE TABLE IF NOT EXISTS `product` (
--     `id` INT NOT NULL AUTO_INCREMENT,
--     `name` VARCHAR(45) NOT NULL,
--     `price` VARCHAR(45) NOT NULL,
--     `currency` VARCHAR(45) NOT NULL,
--     PRIMARY KEY (`id`));

-- Data base for exercise 5
CREATE TABLE IF NOT EXISTS `user` (
    `username` VARCHAR(45) NULL,
    `password` TEXT NULL,
    PRIMARY KEY (`username`));

CREATE TABLE IF NOT EXISTS `otp` (
    `username` VARCHAR(45) NOT NULL,
    `code` VARCHAR(45) NULL,
    PRIMARY KEY (`username`));