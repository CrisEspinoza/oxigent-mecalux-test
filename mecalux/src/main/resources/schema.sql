-- Insertar datos de ejemplo en warehouses
INSERT INTO warehouses (uuid, client, family, size, start_date, update_date, end_date) VALUES
('123e4567-e89b-12d3-a456-42661417400', 'Cliente 1', 'EST', 10, '2024-05-20 10:00:00', '2024-05-20 10:00:00', '2024-05-20 10:00:00'),
('223e4567-e89b-12d3-a456-42661417400', 'Cliente 2', 'EST', 20, '2024-05-21 11:00:00', '2024-05-21 11:00:00', '2024-05-21 11:00:00'),
('323e4567-e89b-12d3-a456-42661417400', 'Cliente 3', 'ROB', 30, '2024-05-22 12:00:00', '2024-05-22 12:00:00', '2024-05-22 12:00:00');

-- Insertar datos de ejemplo en racks
INSERT INTO racks (uuid, type, warehouses) VALUES
 ('1a2b3c4d-5e6f-7g8h-9i10-jk11lm12no3', 'A', 1),
 ('2b3c4d5e-6f7g-8h9i-10j1-k11l12m13no4', 'B', 2),
 ('3c4d5e6f-7g8h-9i1j-10k11-l12m13n14o5', 'C', 3);