-- Dumping database structure for concretepage
-- Dumping structure for table concretepage.articles
CREATE TABLE IF NOT EXISTS `userTable` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_profile` varchar(100) NOT NULL,
  `user_email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=UTF8;

-- Dumping data for table concretepage.articles: ~3 rows (approximately)
/*INSERT INTO `userTable` (`id`, `user_name`, `user_password`, `user_profile`, `user_email`) VALUES
  (1, 'firstName1', 'pass1', 'name1', 'name1@yahoo.com'),
  (2, 'firstName2', 'pass2', 'name2', 'name2@yahoo.com'),
  (3, 'firstName3', 'pass3', 'name3', 'name3@yahoo.com'),
  (4, 'firstName4', 'pass4', 'name4', 'name4@yahoo.com'),
  (5, 'firstName5', 'pass5', 'name5', 'name5@yahoo.com'),
  (6, 'firstName6', 'pass6', 'name6', 'name6@yahoo.com'),
  (7, 'firstName7', 'pass7', 'name7', 'name7@yahoo.com'),
  (8, 'firstName8', 'pass8', 'name8', 'name8@yahoo.com'),
  (9, 'firstName9', 'pass9', 'name9', 'name9@yahoo.com'),
  (10, 'firstName10', 'pass10', 'name10', 'name10@yahoo.com');*/
