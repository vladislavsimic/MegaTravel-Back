ALTER TABLE message
DROP FOREIGN KEY FK_MESSAGE_USER;

ALTER TABLE message
DROP COLUMN receiver_id;

ALTER TABLE message
DROP COLUMN sender_id;
