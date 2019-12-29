ALTER TABLE property
DROP COLUMN summer_price;

ALTER TABLE property
DROP COLUMN winter_price;

ALTER TABLE property
DROP COLUMN spring_price;

ALTER TABLE property
DROP COLUMN autumn_price;

ALTER TABLE property
ADD COLUMN price double;
