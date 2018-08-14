create table SHIRO_USER
(
  id        VARCHAR2(64) not null,
  user_no   VARCHAR2(64),
  password  VARCHAR2(100),
  nick_name VARCHAR2(64)
);

insert into shiro_user (ID, USER_NO, PASSWORD, NICK_NAME)
values ('7393c613206842e9898dabd6d9aff83f', 'DT1705177', '5ceec6edee9e235ca79a9701d6e538f4', '最最亲爱的森哥3');

insert into shiro_user (ID, USER_NO, PASSWORD, NICK_NAME)
values ('901247af6e664721ba542265e646b024', 'DT1705178', 'd309d3bcdbb7a2152f88fd77468f0e3f', '我是管理员');

insert into shiro_user (ID, USER_NO, PASSWORD, NICK_NAME)
values ('656e0caa27b24aeea66262ec27587286', 'DT1705179', '92f8d4c743cba7f0cbab1cb8dc552ea9', '我是管理员');

commit;

create table SHIRO_USER_ROLE
(
  id      VARCHAR2(64) not null,
  user_no VARCHAR2(64),
  role    VARCHAR2(64)
);

insert into shiro_user_role (ID, USER_NO, ROLE)
values ('3a32763e667840a6a03386514b43dcfc', 'DT1705177', 'admin');

insert into shiro_user_role (ID, USER_NO, ROLE)
values ('6b7adc9369d44efc86ee1cb69158d2ad', 'DT1705177', 'normal');

insert into shiro_user_role (ID, USER_NO, ROLE)
values ('0ba6ba9dbee8478ca899df236d90c130', 'DT1705178', 'vip');

insert into shiro_user_role (ID, USER_NO, ROLE)
values ('fc22abdb5b0545c9a178b69e507b63d9', 'DT1705178', 'normal');

insert into shiro_user_role (ID, USER_NO, ROLE)
values ('de58048c7471499db5cd328e48f7d45d', 'DT1705179', 'normal');

commit;

create table SHIRO_ROLE_PERMISSION
(
  id         VARCHAR2(64) not null,
  role       VARCHAR2(64),
  permission VARCHAR2(64)
);

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('ff1ffdf519f34d0eb0b2894a31e15fe0', 'admin', 'view');

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('9e161d03982448cb8cb0e73bd4cebf0c', 'admin', 'update');

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('0288063056c046859bea3542fe860ee6', 'admin', 'delete');

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('e66433c8933e4184b52faf5e7a3c6470', 'vip', 'update');

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('a71c274799ba45d88a35715eada079a8', 'vip', 'view');

insert into shiro_role_permission (ID, ROLE, PERMISSION)
values ('51213be7ea12428abdbd321d64edf35a', 'normal', 'view');

commit;