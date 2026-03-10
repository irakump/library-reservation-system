INSERT INTO reservation (user_id, isbn)
VALUES
(1, '9789513162641'),
(1, '9781472259059'),
(1, '9780984782857'),
(2, '0575039434'),
(2, '9789513111465'),
(2, '9780241600948');

INSERT INTO reservation (status, user_id, isbn)
VALUES
('not_active', 1, '0575039434'),
('not_active', 1, '9789513111465'),
('not_active', 1, '9780241600948'),
('not_active', 2, '9789510514986'),
('not_active', 2, '9780349437026');
