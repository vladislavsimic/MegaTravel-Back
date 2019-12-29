INSERT INTO property(id, is_deleted, time_created, time_updated, description, name, category, stars, number_of_people, number_of_cancellation_days, price, address_id, agent_id, type_id)
VALUES ('b3dd36e0-d0c8-4279-b9d6-5b64e183ed90', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'description 1', 'Property 1', 'NONE', 3, 7, 7, 4599, 'c8c205dc-46db-4f83-af4e-8ad63d8f1bf9', '4c4afa18-29d3-4ad8-81d5-f9abde3a79f5', '3ce0e551-24d5-407b-a244-b5a4871ebcad');

INSERT INTO property(id, is_deleted, time_created, time_updated, description, name, category, stars, number_of_people, number_of_cancellation_days, price, address_id, agent_id, type_id)
VALUES ('851e3ae7-1254-4f41-bb65-2586bbdecefe', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'description 2', 'Property 2', 'NONE', 3, 5, 7, 4599, 'df8d9f04-5896-4b56-ac4a-f7b652c2fd2c', '4c4afa18-29d3-4ad8-81d5-f9abde3a79f5', 'b08ea6fb-552e-4303-bd91-ed3b640eaac3');

INSERT INTO property(id, is_deleted, time_created, time_updated, description, name, category, stars, number_of_people, number_of_cancellation_days, price, address_id, agent_id, type_id)
VALUES ('70a29d48-80da-49b4-b76d-137e8f4eeb98', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', 'description 3', 'Property 3', 'NONE', 3, 4, 7, 4599, '4dc85c81-44b8-4fc6-b710-6e2d0bb7b4cd', '4c4afa18-29d3-4ad8-81d5-f9abde3a79f5', '25b43e26-dec6-4500-94a9-0226a4868525');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('0281e002-e9c2-4ecf-87fc-4a570a458b4e', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-06-15 08:06:56', 6, 30, 'SUCCESSFUL', '2019-06-01 08:06:56', 'b3dd36e0-d0c8-4279-b9d6-5b64e183ed90', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('19f3138f-dd92-4338-a673-5cf87f4fb3dd', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-06-10 08:06:56', 7, 30, 'SUCCESSFUL', '2019-06-06 08:06:56', 'b3dd36e0-d0c8-4279-b9d6-5b64e183ed90', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('8a416c6c-80ef-45f6-b270-e5d1d145dea2', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-06-30 08:06:56', 7, 30, 'CANCELED', '2019-06-27 08:06:56', 'b3dd36e0-d0c8-4279-b9d6-5b64e183ed90', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('7dc16ca1-0d80-43e1-9a3b-63cda624503e', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-06-20 08:06:56', 4, 30, 'PENDING', '2019-06-17 08:06:56', '851e3ae7-1254-4f41-bb65-2586bbdecefe', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('e492063b-c8b4-45e9-94d0-fb0fa16a1b3b', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-07-03 08:06:56', 5, 30, 'CANCELED', '2019-06-20 08:06:56', '851e3ae7-1254-4f41-bb65-2586bbdecefe', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');

INSERT INTO reservation(ID, IS_DELETED, TIME_CREATED, TIME_UPDATED, END_DATE, NUMBER_OF_PEOPLE, PRICE, RESERVATION_STATUS, START_DATE, PROPERTY_ID, RATING_ID, USER_ID)
VALUES ('e1aacb58-6322-49fe-a669-add559fd8f43', 0, '2019-06-23 08:06:56', '2019-06-23 08:06:56', '2019-07-17 08:06:56', 2, 30, 'PENDING', '2019-07-15 08:06:56', '851e3ae7-1254-4f41-bb65-2586bbdecefe', null, 'fa4cd8fa-a488-4688-bc5b-b133637ccc62');
