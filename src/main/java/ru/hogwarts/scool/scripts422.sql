CREATE TABLE human
(
    id             INTEGER,
    name           TEXT PRIMARY KEY,
    age            INTEGER,
    driver_license BOOLEAN,
    car_id         TEXT REFERENCES car(id)
);
CREATE TABLE car
(
    id INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER

)