CREATE TABLE users (
  id INT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255)
);

CREATE TABLE goals (
  id INT PRIMARY KEY,
  userId INT,
  title VARCHAR(255),
  description TEXT,
  startDate DATE,
  endDate DATE,
  progress INT,
  FOREIGN KEY (userId) REFERENCES users(id)
);

{
  "userId": 123, // Replace with the actual userId of the logged-in user
  "title": "My Goal",
  "description": "Goal Description",
  "startDate": "2023-07-10",
  "endDate": "2023-12-31",
  "progress": 0
}
