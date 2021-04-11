-- Data base data for excercise 3
-- INSERT INTO `authorities` VALUES (NULL, 'john', 'write');
-- INSERT INTO `users` VALUES (NULL, 'john', '12345', '1');

-- Data base data for exercise 4
-- INSERT INTO `user` (`id`, `username`, `password`, `algorithm`) VALUES ('1', 'john', '$2y$10$JuR0.KJZCwZJkQGt8GfxOeERlkak8ZCKNv6MdtAUXcfLKTScLjfOe', 'BCRYPT');
-- INSERT INTO `authority` (`id`, `name`, `user`) VALUES ('1', 'READ', '1');
-- INSERT INTO `authority` (`id`, `name`, `user`) VALUES ('2', 'WRITE', '1');
-- INSERT INTO `product` (`id`, `name`, `price`, `currency`) VALUES ('1', 'Chocolate', '10', 'USD');

-- Data base data for exercise 5
INSERT INTO `user` (`username`, `password`) VALUES ('john', '$2y$10$JuR0.KJZCwZJkQGt8GfxOeERlkak8ZCKNv6MdtAUXcfLKTScLjfOe');