CREATE TABLE `java_script_framework` (
  `id` int(30) NOT NULL,
  `name` varchar(150) NOT NULL,
  `version` varchar(30) NOT NULL,
  `deprecation_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hype_level` enum('MINIMUM','MEDIUM','MAXIMUM') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;