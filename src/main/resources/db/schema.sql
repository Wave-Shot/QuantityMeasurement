CREATE TABLE IF NOT EXISTS quantity_measurements (

    id INT AUTO_INCREMENT PRIMARY KEY,

    operation VARCHAR(100),

    result VARCHAR(255),

    error BOOLEAN,

    error_message VARCHAR(255)
);