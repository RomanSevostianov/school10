CREATE TABLE cars
(
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR (15) NOT NULL,
    model VARCHAR (35) NOT NULL,
    price INT CHECK (price > 0 ) NOT NULL
);


CREATE TABLE owners
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR (15) NOT NULL,
    age INT CHECK  (age>17) NOT NULL,
    has_drive_licens BOOLEAN DEFAULT true NOT NULL,
    cars_id BIGINT REFERENCES cars (id) NOT NULL
);


INSERT INTO cars (brand, model, price)
    VALUES ('Lada','X-Ray',140000),
    VALUES ('KIA','RIO',220000);

    INSERT INTO owners (name, age, cars_id)
    VALUES ('Roman',25,1),
    VALUES ('Roman',25,1);