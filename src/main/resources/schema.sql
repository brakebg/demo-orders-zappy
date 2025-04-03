-- Drop tables if they exist to ensure clean initialization
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS inventory;

-- Create inventory table
CREATE TABLE inventory (
    product_id VARCHAR(255) PRIMARY KEY,
    quantity INT NOT NULL,
    version BIGINT DEFAULT 0
);

-- Create order table
CREATE TABLE orders (
    id IDENTITY PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    product VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);
