INSERT INTO tb_technologies (name, img_url) VALUES ('Java', 'java_image_url');
INSERT INTO tb_technologies (name, img_url) VALUES ('Spring Boot', 'spring_image_url');
INSERT INTO tb_technologies (name, img_url) VALUES ('ReactJS', 'react_image_url');
INSERT INTO tb_technologies (name, img_url) VALUES ('Vue.js', 'vue_image_url');
INSERT INTO tb_technologies (name, img_url) VALUES ('MySQL', 'mysql_image_url');

INSERT INTO tb_projects (name, description, img_url) VALUES ('E-commerce Platform', 'Online shopping platform', 'ecommerce_image_url');
INSERT INTO tb_projects (name, description, img_url) VALUES ('Social Media App', 'Connect with friends and share posts', 'social_media_image_url');
INSERT INTO tb_projects (name, description, img_url) VALUES ('Task Management App', 'Organize and manage tasks efficiently', 'task_management_image_url');
INSERT INTO tb_projects (name, description, img_url) VALUES ('Financial Tracker', 'Track and manage personal finances', 'financial_tracker_image_url');
INSERT INTO tb_projects (name, description, img_url) VALUES ('Travel Planning App', 'Plan and schedule trips', 'travel_planning_image_url');

-- Relacionamento entre Project e Technology
INSERT INTO tb_project_technology (project, technology) VALUES (1, 1); -- E-commerce Platform com Java
INSERT INTO tb_project_technology (project, technology) VALUES (1, 2); -- E-commerce Platform com Spring Boot
INSERT INTO tb_project_technology (project, technology) VALUES (2, 3); -- Social Media App com ReactJS
INSERT INTO tb_project_technology (project, technology) VALUES (2, 4); -- Social Media App com Vue.js
INSERT INTO tb_project_technology (project, technology) VALUES (3, 1); -- Task Management App com Java
INSERT INTO tb_project_technology (project, technology) VALUES (3, 2); -- Task Management App com Spring Boot
INSERT INTO tb_project_technology (project, technology) VALUES (4, 5); -- Financial Tracker com MySQL
INSERT INTO tb_project_technology (project, technology) VALUES (5, 1); -- Travel Planning App com Java
INSERT INTO tb_project_technology (project, technology) VALUES (5, 3); -- Travel Planning App com ReactJS
