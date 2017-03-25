DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Actor;
DROP TABLE IF EXISTS Director;
DROP TABLE IF EXISTS ActsIn;
DROP TABLE IF EXISTS Directs;
DROP TABLE IF EXISTS Movie;

CREATE TABLE Person (
    PID char(4) not null,
    FirstName text not null,
    LastName text not null,
    Address text,
    SpouseName text,
    primary key(PID)
);

CREATE TABLE Actor(
    AID char(4) not null,
    PID char(4) not null references Person(PID),
    Birthdate date not null,
    hairColor text not null,
    eyeColor text not null,
    height_in integer not null,
    weight_lbs integer not null,
    favoriteColor text,
    screenActorsGuildDate date,
    primary key(AID)
);

CREATE TABLE Director(
    DID char(4) not null,
    PID char(4) not null references Person(PID),
    FilmSchoolAttended text,
    DirectorsGuildDate date,
    FavoriteLensMaker text,
    primary key (DID)
);
CREATE TABLE Movie(
    MPAANumber int not null,
    Name text not null,
    YearReleased int not null,
    Domestic_Sales_USD int,
    Foreign_Sales_USD int,
    DVD_BluRay_Sales_USD int,
    primary key (MPAANumber)
);

CREATE TABLE ActsIn(
    AID char(4) not null references Actor(AID),
    MPAANumber int not null references Movie(MPAANumber),
    primary key (AID, MPAANumber)
);

CREATE TABLE Directs (
    DID char(4) not null references Director(DID),
    MPAANumber int not null references Movie(MPAANumber),
    primary key (DID, MPAANumber)
);
