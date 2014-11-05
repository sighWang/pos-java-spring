DROP DATABASE IF EXISTS pos_java_jdbc;
CREATE DATABASE pos_java_jdbc DEFAULT CHARACTER SET utf8;
USE pos_java_jdbc;

## 创建商品列表
CREATE TABLE item (
  id INT AUTO_INCREMENT PRIMARY KEY ,
  barcode VARCHAR(50) NOT NULL ,
  name VARCHAR(50) NOT NULL ,
  unit VARCHAR(50) NOT NULL ,
  price DOUBLE NOT NULL ,
  category varchar (50) NOT NULL
);

## 创建优惠信息表
CREATE TABLE promotion (
  id INT AUTO_INCREMENT PRIMARY KEY ,
  type VARCHAR(50) NOT NULL ,
  level INT NOT NULL
);

## 创建商品_优惠连接表
CREATE TABLE promotion_item (
  promotion_id INT REFERENCES promotion(id),
  item_id INT REFERENCES item(id),
  discount INT,
  UNIQUE (promotion_id,item_id)
);

## 插入初始数据
INSERT INTO item (id,barcode,name,unit,price,category)
    VALUES(1,'ITEM000001','雪碧','瓶',3.5,'饮料'),
      (2,'ITEM000002','苹果','斤',10.00,'水果'),
      (3,'ITEM000003','香蕉','斤',5.00,'水果'),
      (4,'ITEM000004','方便面','包',1.50,'食品'),
      (5,'ITEM000005','电池','个',5.00,'日用品'),
      (6,'ITEM000006','荔枝','斤',10.00,'水果'),
      (7,'ITEM000007','羽毛球拍','副',100.00,'运动器材'),
      (8,'ITEM000008','袜子','双',5.00,'日用品'),
      (9,'ITEM000009','牙刷','个',5.00,'日用品');

INSERT INTO promotion (id, type, level)
    VALUES (1, 'buy_two_get_one_free',1),
      (2, 'second_half_price_promotion',2),
      (3, 'discount',3);

INSERT INTO promotion_item (promotion_id, item_id)
    VALUES (1,1),
      (1,4),
      (1,9),
      (2,5),
      (2,7),
      (2,8);

INSERT INTO promotion_item (promotion_id, item_id,discount)
    VALUES (3,1,50),
      (3,2,50),
      (3,3,50),
      (3,4,50),
      (3,5,50),
      (3,6,50),
      (3,7,50),
      (3,8,50);
COMMIT;