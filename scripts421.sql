ALTER TABLE student AND CONSTRAINT  age_more_than_15 (age >15);
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE student ALTER COLUMN name SET not null;
ALTER TABLE faculty AND CONSTRAINT name_color_unique UNIQUE (name, color)
ALTER TABLE student ALTER COLUMN name SET DEFAULT 20;


