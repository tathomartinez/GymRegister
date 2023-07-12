DROP TABLE menu;
CREATE TABLE IF NOT EXISTS menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    title TEXT NOT NULL,
    subtitle TEXT,
    parent_menu_id INTEGER,
    role TEXT NOT NULL,
    route TEXT,
    icon_route TEXT
);

INSERT INTO menu (id, title, subtitle, parent_menu_id, role) VALUES (1, 'Conexiones de red e internet', NULL, NULL, '0');
INSERT INTO menu (id, title, subtitle, parent_menu_id, role, route) VALUES (2, 'Config. Wifi', NULL, 1, '0', 'wifi');
INSERT INTO menu (id, title, subtitle, parent_menu_id, role) VALUES (3, 'Config. Eternet', NULL, 1, '0');
INSERT INTO menu (id, title, subtitle, parent_menu_id, role, route, icon_route) VALUES (4, 'Body Measurement', NULL, NULL, '0', 'register', 'conexion');
