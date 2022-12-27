CREATE TABLE human
(
    id             INTEGER PRIMARY KEY,
    name           TEXT,
    age            INTEGER,
    driver_license BOOLEAN,
    car_id         INTEGER REFERENCES car(id)
);
CREATE TABLE car
(
    id bigserial PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price DECIMAL

)