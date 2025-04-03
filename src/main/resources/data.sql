-- Populate inventory table with 100 products
-- Products will have IDs like PROD001, PROD002, etc.
-- Quantities will range from 10 to 500

-- First batch of inventory items (1-25)
INSERT INTO inventory (product_id, quantity) VALUES
('PROD001', 150), ('PROD002', 200), ('PROD003', 75), ('PROD004', 300), ('PROD005', 125),
('PROD006', 50), ('PROD007', 175), ('PROD008', 225), ('PROD009', 100), ('PROD010', 350),
('PROD011', 80), ('PROD012', 220), ('PROD013', 90), ('PROD014', 180), ('PROD015', 270),
('PROD016', 120), ('PROD017', 190), ('PROD018', 210), ('PROD019', 85), ('PROD020', 310),
('PROD021', 65), ('PROD022', 145), ('PROD023', 235), ('PROD024', 95), ('PROD025', 275);

-- Second batch of inventory items (26-50)
INSERT INTO inventory (product_id, quantity) VALUES
('PROD026', 130), ('PROD027', 250), ('PROD028', 70), ('PROD029', 320), ('PROD030', 110),
('PROD031', 290), ('PROD032', 60), ('PROD033', 170), ('PROD034', 240), ('PROD035', 105),
('PROD036', 330), ('PROD037', 55), ('PROD038', 195), ('PROD039', 265), ('PROD040', 115),
('PROD041', 285), ('PROD042', 45), ('PROD043', 165), ('PROD044', 255), ('PROD045', 135),
('PROD046', 305), ('PROD047', 40), ('PROD048', 160), ('PROD049', 230), ('PROD050', 125);

-- Third batch of inventory items (51-75)
INSERT INTO inventory (product_id, quantity) VALUES
('PROD051', 295), ('PROD052', 35), ('PROD053', 155), ('PROD054', 245), ('PROD055', 140),
('PROD056', 315), ('PROD057', 30), ('PROD058', 150), ('PROD059', 260), ('PROD060', 120),
('PROD061', 280), ('PROD062', 25), ('PROD063', 145), ('PROD064', 215), ('PROD065', 130),
('PROD066', 325), ('PROD067', 20), ('PROD068', 140), ('PROD069', 270), ('PROD070', 110),
('PROD071', 290), ('PROD072', 15), ('PROD073', 135), ('PROD074', 225), ('PROD075', 105);

-- Fourth batch of inventory items (76-100)
INSERT INTO inventory (product_id, quantity) VALUES
('PROD076', 335), ('PROD077', 10), ('PROD078', 130), ('PROD079', 280), ('PROD080', 100),
('PROD081', 300), ('PROD082', 50), ('PROD083', 125), ('PROD084', 235), ('PROD085', 95),
('PROD086', 345), ('PROD087', 45), ('PROD088', 120), ('PROD089', 290), ('PROD090', 90),
('PROD091', 310), ('PROD092', 40), ('PROD093', 115), ('PROD094', 245), ('PROD095', 85),
('PROD096', 355), ('PROD097', 35), ('PROD098', 110), ('PROD099', 250), ('PROD100', 80);

-- Insert 5 sample orders
-- Note: We're now using auto-generated IDs with the IDENTITY column type
INSERT INTO orders (username, product, quantity) VALUES
('user1@example.com', 'PROD010', 5),
('user2@example.com', 'PROD025', 3),
('user3@example.com', 'PROD042', 8),
('user1@example.com', 'PROD078', 2),
('user4@example.com', 'PROD096', 10);
