drop schema if exists dat107oblig2 cascade;

create schema dat107oblig2;
set search_path to dat107oblig2;

create table address
(
    id      serial,
    street  varchar(45),
    zipcode int,
    city    varchar(45),
    constraint address_pk primary key (id)
);

create table rentaloffice
(
    id          serial,
    address     int,
    phonenumber varchar(45),
    constraint rentaloffice_pk primary key (id),
    constraint address_fk foreign key (address) references address
);

create type cargroup as enum ('A', 'B', 'C', 'D');

create table car
(
    registrationnumber int,
    mileage            int,
    brand              varchar(45),
    color              varchar(45),
    cargroup           cargroup,
    rentaloffice       int,
    constraint car_pk primary key (registrationnumber),
    constraint rentaloffice_fk foreign key (rentaloffice) references rentaloffice
);

create table customer
(
    id       serial,
    name     varchar(45),
    email    varchar(45),
    password varchar(45),
    address  int,
    constraint customer_pk primary key (id),
    constraint address_fk foreign key (address) references address
);

create table reservation
(
    id               serial,
    cargroup         cargroup,
    car              int,
    customer         int,
    creditcardnumber int,

    startdate        timestamp,
    startmilage      int,

    enddate          timestamp,
    endmilage        int,


    constraint reservation_pk primary key (id),
    constraint car_fk foreign key (car) references car,
    constraint customer_fk foreign key (customer) references customer
);

