INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (1, 'admin', '增删改查', 'userInfo:view', 'menu', '/findbyId');
INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (2, 'admin', '增删改查', 'userInfo:add', 'button', '/addUser');
INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (3, 'test', '增删改查', 'userInfo:del', 'button', '/updateUser');
INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (4, 'admin', '查询角色模块', 'selectRole', 'role', '/findRoleById');
INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (5, 'test', '查询角色模块', 'role', 'role', '/findRoleById');
INSERT INTO `sys_permission` (`id`, `rolename`, `model`, `permission`, `resource_type`, `url`) VALUES (6, 'test', 'role', 'role', 'role', '/updateRoleById');
