CREATE DATABASE IF NOT EXISTS max_zvaryh_db_lab3;
USE max_zvaryh_db_lab3;
CREATE USER 'Masyanya'@'%' IDENTIFIED BY 'Max0993319943';
GRANT SELECT ON *.* TO 'Masyanya'@'%';

DROP TABLE IF EXISTS chat_has_message;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS user_has_chat;
DROP TABLE IF EXISTS media_file;
DROP TABLE IF EXISTS user_has_message;
DROP TABLE IF EXISTS audio_file;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS chat_style;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS Discord;

CREATE TABLE user (
`id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`nickname` VARCHAR(50) NOT NULL,
`Discord_version` INT NOT NULL,
 PRIMARY KEY (`id`, `nickname`),
 UNIQUE INDEX `nickname_idx` (`nickname` ASC) VISIBLE)
 ENGINE = INNODB;

CREATE TABLE  Discord (
  `version` INT NOT NULL AUTO_INCREMENT UNIQUE,
  `update_available` TINYINT NOT NULL,
   `settings` VARCHAR(45) NULL,
  PRIMARY KEY (`version`))
ENGINE = InnoDB;

CREATE TABLE chat (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  `Discord_version` INT NOT NULL,
  `chat_style_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `chat_style_name`))
ENGINE = InnoDB;

CREATE TABLE chat_style (
  `name` VARCHAR(45) UNIQUE NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;

CREATE TABLE audio_file (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `is_voice_message` TINYINT NOT NULL,
  `chat_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE media_file (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `file_type` VARCHAR(45) NOT NULL,
  `chat_id` INT NOT NULL,
  PRIMARY KEY (`id`, `file_type`))
ENGINE = InnoDB;

CREATE TABLE user_has_chat (
  `user_id` INT NOT NULL ,
  `user_nickname` VARCHAR(45) NOT NULL,
  `chat_id` INT NOT NULL,
  `chat_name` VARCHAR(45) NOT NULL,
  `chat_style_name` VARCHAR(45) NOT NULL,
  INDEX fk_user_has_chat_chat1_idx (`chat_id` ASC, `chat_style_name` ASC) VISIBLE,
  INDEX fk_user_has_chat_user1_idx (`user_id` ASC, `user_nickname` ASC) VISIBLE,
  PRIMARY KEY (`user_id`, `user_nickname`, `chat_id`,`chat_style_name`))
ENGINE = InnoDB;

CREATE TABLE message (
  `id` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `addressee` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `addressee`),
  UNIQUE INDEX `addressee_idx` (`addressee` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE `chat_has_message` (
  `chat_id` INT NOT NULL,
  `chat_name` VARCHAR(45) NOT NULL,
  `message_id` INT NOT NULL,
  `chat_style_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`chat_id`, `message_id`, `chat_style_name`),
  INDEX fk_chat_has_message_message1_idx (`message_id` ASC) VISIBLE,
  INDEX fk_chat_has_message_chat1_idx (`chat_id` ASC, `chat_style_name` ASC) VISIBLE)
ENGINE = InnoDB;

ALTER TABLE user
	ADD CONSTRAINT fk_user_Discord1
	FOREIGN KEY (Discord_version)
	REFERENCES Discord (version );


ALTER TABLE chat
	ADD CONSTRAINT fk_chat_Discord1
	FOREIGN KEY (Discord_version)
	REFERENCES Discord (version),
    ADD CONSTRAINT fk_chat_chat_style1
	FOREIGN KEY (chat_style_name)
	REFERENCES chat_style (name);






ALTER TABLE media_file
	ADD CONSTRAINT fk_media_file_chat1
	FOREIGN KEY (chat_id)
	REFERENCES chat (id);

ALTER TABLE audio_file
	ADD CONSTRAINT fk_audio_file_chat1
	FOREIGN KEY (chat_id)
	REFERENCES chat (id);

   ALTER TABLE user_has_chat
	ADD CONSTRAINT fk_user_has_chat_chat1
	FOREIGN KEY (`chat_id` , `chat_style_name`)
	REFERENCES `chat` (`id` , `chat_style_name`),
    ADD CONSTRAINT fk_user_has_chat_user1
	FOREIGN KEY (`user_id` , `user_nickname`)
	REFERENCES `user` (`id` , `nickname`);

    ALTER TABLE chat_has_message
	ADD CONSTRAINT fk_chat_has_message_message1
	FOREIGN KEY (`message_id`)
	REFERENCES `message` (`id`),
    ADD CONSTRAINT fk_chat_has_message_chat1
	FOREIGN KEY (`chat_id` , `chat_style_name`)
	REFERENCES `chat` (`id` , `chat_style_name`);

INSERT INTO Discord(update_available,settings) VALUES
('0', 'first_version'),('1', 'first_version'),('0', 'first_version'),('1', 'first_version'),('0', 'first_version'),('1', 'first_version'),
('0', 'first_version'),('1', 'first_version'),('0', 'first_version'),('0', 'first_version');

INSERT INTO user( nickname, Discord_version) VALUES
('Masyanya#0895', '1'),('Taras#0887', '1'),('Petro#0895', '1'),('Olexa', '2'),('Mariya#', '3'),('Kazyavka#0895', '5'),( 'Kazinak#0895', '6'),('Lucifer#0895', '6'),
('Bangladesh#0895', '7'),('Casino#0895', '8'),('Member#0895', '9');

INSERT INTO chat_style(name) VALUES
('private'),('public');

INSERT INTO chat(name, region, discord_version,chat_style_name) VALUES
('NULP Game Server', 'ua', '1','private'), ('LPML1', 'usa', '2','private'),('LPML2', 'ua', '3','private'),('LPML3', 'ua', '2','private'),('LPML4', 'usa', '5','private'),
('LPML5', 'usa', '6','private'),('LPML6', 'md', '8','private'),('LPML7', 'ua', '8','private'),('LPML8', 'cz', '7','private'),('LPML8', 'cz', '10','private');

INSERT INTO message(addressee) VALUES
('Max'),('Vova'),('Vasya'),('Olesya'),('Vitya'),('Petro'),('Vincent'),('Lexa'),('Roman'),('Petya');

INSERT INTO audio_file(is_voice_message, chat_id) VALUES
('1','8'),('0','8'),('0','8'),('1','2'),('0','1'),('0','2'),('1','2'),('0','7'),('1','6'),('1','2');

INSERT INTO chat_has_message(chat_id,chat_name,message_id,chat_style_name) VALUES
('2', 'LPML1', '1','private'),('2', 'LPML1', '2','private'),('4', 'LPML1', '3','private'),('2', 'LPML1', '4','private'),('1', 'LPML1', '5','private'),
('7', 'LPML1', '6','private'),('8', 'LPML1', '7','private'),('3', 'LPML1', '8','private'),('5', 'LPML1', '9','private'),('9', 'LPML1', '10','private');

INSERT INTO media_file(file_type,chat_id) VALUES
('zip', '9'),('zip', '9'),('zip', '9'),('zip', '9'),('zip', '3'),('zip', '2'),('zip', '1'),('zip', '4'),('zip', '5'),('zip', '7');

INSERT INTO user_has_chat(user_id, user_nickname, chat_id, chat_name,chat_style_name) VALUES
('1', 'Masyanya#0895', '1', 'LPML2','private'),('2', 'Taras#0887', '5', 'LPML3','private'),('3', 'Petro#0895', '2', 'LPML4','private'),('4', 'Olexa', '6', 'LPML5','private');
