-- テーブルが重複しないよう存在チェック、あれば削除します
DROP TABLE IF EXISTS currency;

-- 簡易なマスタテーブル
CREATE TABLE `currency` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `symbol` VARCHAR(10) NOT NULL,
  `amount` DECIMAL(40, 20),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 材料マスタ
CREATE TABLE `materials` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `tag1` VARCHAR(255),
  `tag2` VARCHAR(255),
  `tag3` VARCHAR(255),
  `maker_name` VARCHAR(255),
  `maker_charge` VARCHAR(255),
  `maker_contact` VARCHAR(255),
  `remarks` VARCHAR(1024),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;