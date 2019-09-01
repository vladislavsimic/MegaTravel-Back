INSERT IGNORE INTO type(id, is_deleted, time_created, time_updated, name) VALUES ('3ce0e551-24d5-407b-a244-b5a4871ebcad', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Hotel');
INSERT IGNORE INTO type(id, is_deleted, time_created, time_updated, name) VALUES ('b08ea6fb-552e-4303-bd91-ed3b640eaac3', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Bed & Breakfast');
INSERT IGNORE INTO type(id, is_deleted, time_created, time_updated, name) VALUES ('25b43e26-dec6-4500-94a9-0226a4868525', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Apartment');

INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('363ea5a8-d0fd-4430-8155-7738732637ba', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Parking');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('4d8a1e46-3d57-4fb3-a32d-3253b5f1c81f', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'WiFi');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('08c36489-f641-4be9-aac6-6d5a553c420e', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Breakfast');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('13fe2550-270c-414b-be13-f0e4851bc094', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Half Board');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('31fcaa6e-69a7-4b4f-8ce0-e599bca0491e', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Room and Board');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('7ce43dd6-7ca5-4425-8274-a304714f2e9d', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'All Inclusive');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('4be83d86-4083-4531-af87-90e3a167fafc', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Pet Friendly');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('688685d0-56ba-4532-93bb-964d82618a13', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'TV');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('3087e97b-e7d3-4aa8-93d0-f72330017bf5', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Mini Kitchen');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('6b88bdf2-5c46-4b6f-bf50-4cb281fb2ebd', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Kitchen');
INSERT IGNORE INTO service(id, is_deleted, time_created, time_updated, name) VALUES ('3e1e1578-b5db-4d8f-832d-58b826d4aa5d', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Private Bathroom');

INSERT IGNORE INTO address(id, is_deleted, time_created, time_updated, city, country, latitude, longitude, street)
VALUES ('e6eed766-4396-4414-a2f9-ece1ef181d60', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'Novi Sad', 'Serbia', 21.222, 33.221, 'Danila Kisa');

INSERT IGNORE INTO user(id, is_deleted, time_created, time_updated, category, email, first_name, is_active, last_name, password, role, username, profile_picture_id)
VALUES ('c43e9fd8-6c0a-424d-8e84-a7f0e93044a0', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'NONE', 'admin@megatravel.com', 'ADMIN', 1, 'Admin', '$2a$10$14EMwQ64AmPGBjGW3QM59OHiLjQSvwjIwVPSihk5HlblBtc9h9q4q', 'ADMIN', 'admin', null);

INSERT IGNORE INTO user(id, is_deleted, time_created, time_updated, category, email, first_name, is_active, last_name, password, role, username, profile_picture_id)
VALUES ('6cc7914c-a3b2-45fa-8e41-7905c98a18a2', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'NONE', 'agent@agent.com', 'AGENT', 1, 'Agent', '$2a$10$14EMwQ64AmPGBjGW3QM59OHiLjQSvwjIwVPSihk5HlblBtc9h9q4q', 'AGENT', 'agent', null);
INSERT IGNORE INTO agent(id, is_deleted, time_created, time_updated, pib, address_id, user_id)
VALUES ('4c4afa18-29d3-4ad8-81d5-f9abde3a79f5', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '123456', 'e6eed766-4396-4414-a2f9-ece1ef181d60', '6cc7914c-a3b2-45fa-8e41-7905c98a18a2');

INSERT IGNORE INTO user(id, is_deleted, time_created, time_updated, category, email, first_name, is_active, last_name, password, role, username, profile_picture_id)
VALUES ('fa4cd8fa-a488-4688-bc5b-b133637ccc62', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'NONE', 'user@user.com', 'USER', 1, 'User', '$2a$10$14EMwQ64AmPGBjGW3QM59OHiLjQSvwjIwVPSihk5HlblBtc9h9q4q', 'USER', 'user', null);
