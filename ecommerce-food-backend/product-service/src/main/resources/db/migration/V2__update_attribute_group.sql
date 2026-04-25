-- Version 2: Removing the constraint unique of the column group_type

ALTER TABLE pg.attribute_groups
DROP CONSTRAINT uc_attribute_groups_group_type