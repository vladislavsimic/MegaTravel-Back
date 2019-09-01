alter table address drop zip_code;
alter table message
  change entity_id receiver_id varchar(255) not null,
  add reservation_id varchar(255) not null,
  add constraint FK_MESSAGE_RESERVATION
    foreign key (reservation_id) references reservation (id);
