create schema if not exists megatravel;

create table address
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	city varchar(128) null,
	country varchar(128) null,
	latitude double null,
	longitude double null,
	street varchar(512) null,
	zip_code varchar(16) null
);

create table image
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	entity_id varchar(255) not null,
	entity_type varchar(255) not null,
	url varchar(255) not null
);

create table rating
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	comment varchar(1024) null,
	is_comment_approved bit not null,
	is_comment_reviewed bit not null,
	overall_rating int not null,
	services_rating int not null,
	staff_rating int not null,
	reservation_id varchar(255) not null
);

create table service
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	name varchar(128) null,
	constraint UK_SERVICE_NAME
		unique (name)
);

create table type
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	name varchar(255) null,
	constraint UK_TYPE_NAME
		unique (name)
);

create table user
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	category varchar(255) not null,
	email varchar(255) not null,
	first_name varchar(128) null,
	is_active bit null,
	last_name varchar(128) null,
	password varchar(64) null,
	role varchar(255) not null,
	username varchar(32) null,
	profile_picture_id varchar(255) null,
	constraint UK_USER_EMAIL
		unique (email),
	constraint UK_USER_USERNAME
		unique (username),
	constraint FK_USER_IMAGE
		foreign key (profile_picture_id) references image (id)
);

create table agent
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	pib varchar(64) not null,
	address_id varchar(255) not null,
	user_id varchar(255) not null,
	constraint FK_AGENT_USER
		foreign key (user_id) references user (id),
	constraint FK_AGENT_ADDRESS
		foreign key (address_id) references address (id)
);

create table message
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	content varchar(1024) not null,
	entity_id varchar(255) not null,
	sent_to_entity varchar(255) not null,
	sender_id varchar(255) not null,
	constraint FK_MESSAGE_USER
		foreign key (sender_id) references user (id)
);

create table property
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	description varchar(1024) null,
	name varchar(128) null,
	category varchar(255) not null,
	stars int,
	number_of_people int not null,
	number_of_cancellation_days int not null,
	summer_price double not null,
	winter_price double not null,
	spring_price double not null,
	autumn_price double not null,
	address_id varchar(255) not null,
	agent_id varchar(255) not null,
	type_id varchar(255) not null,
	constraint FK_PROPERTY_ADDRESS
		foreign key (address_id) references address (id),
	constraint FK_PROPERTY_TYPE
		foreign key (type_id) references type (id),
	constraint FK_PROPERTY_AGENT
		foreign key (agent_id) references agent (id)
);

create table property_service
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	property_id varchar(255) not null,
	service_id varchar(255) not null,
	constraint FK_PS_SERVICE
		foreign key (service_id) references service (id),
	constraint FK_PS_PROPERTY
		foreign key (property_id) references property (id)
);

create table price
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	amount double null,
	price_season varchar(255) not null,
	property_id varchar(255) not null,
	constraint FK_PRICE_PROPERTY
		foreign key (property_id) references property (id)
);

create table reservation
(
	id varchar(255) not null
		primary key,
	is_deleted bit not null,
	time_created datetime not null,
	time_updated datetime null,
	end_date datetime null,
	number_of_people int null,
	price double not null,
	reservation_status varchar(255) not null,
	start_date datetime null,
	property_id varchar(255) not null,
	rating_id varchar(255) null,
	user_id varchar(255) null,
	constraint FK_RESERVATION_USER
		foreign key (user_id) references user (id),
	constraint FK_RESERVATION_PROPERTY
		foreign key (property_id) references property (id),
	constraint FK_RESERVATION_RATING
		foreign key (rating_id) references rating (id)
);

alter table rating
	add constraint FK_RATING_RESERVATION
		foreign key (reservation_id) references reservation (id);

