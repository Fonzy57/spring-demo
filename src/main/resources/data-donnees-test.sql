-- Insérer les états des motos
INSERT INTO etat (nom)
VALUES ('Neuf'),
       ('Occasion'),
       ('Reconditionné');

-- Insérer les étiquettes des motos
INSERT INTO etiquette (nom)
VALUES ('Promotion'),
       ('Édition limitée'),
       ('Customisée'),
       ('Véhicule électrique'),
       ('Soldes');

-- Insérer des motos avec des états appropriés
INSERT INTO produit (nom, code, description, prix, etat_id)
VALUES ('Yamaha MT-07', 'yammt07', 'Moto roadster 689cc - 2024', 7599, 1),
       ('Honda CBR500R', 'hondacbr', 'Sportive 471cc - 2023', 6799, 1),
       ('Ducati Panigale V2', 'ducpani', 'Superbike 955cc - 2024', 18490, 1),
       ('Harley-Davidson Iron 883', 'hd883', 'Custom 883cc - 2020', 8990, 2),
       ('Kawasaki Ninja 650', 'kawn650', 'Sportive 649cc - 2021', 7490, 2),
       ('BMW R 1250 GS', 'bmwgs', 'Trail 1254cc - 2022', 17990, 2),
       ('Zero SR/F', 'zerosrf', 'Moto électrique - 2024', 20990, 1),
       ('Triumph Bonneville T120 Custom', 'triumpht120', 'Custom 1200cc - 2019', 12490, 3);

-- Associer des motos aux étiquettes de manière logique
INSERT INTO etiquette_produit (etiquette_id, produit_id)
VALUES (1, 1), -- Yamaha MT-07 en promotion
       (1, 2), -- Honda CBR500R en promotion

       (2, 3), -- Ducati Panigale en édition limitée
       (1, 3), -- Ducati Panigale aussi en promotion

       (3, 4), -- Harley-Davidson customisée
       (5, 4), -- Harley-Davidson en soldes

       (5, 5), -- Kawasaki Ninja 650 en soldes
       (1, 5), -- Kawasaki Ninja 650 aussi en promotion

       (5, 6), -- BMW R 1250 GS en soldes

       (4, 7), -- Zero SR/F est un véhicule électrique
       (1, 7), -- Zero SR/F aussi en promotion (pour inciter à l'achat)

       (3, 8), -- Triumph Bonneville customisée
       (2, 8), -- Triumph Bonneville en édition limitée
       (5, 8); -- Triumph Bonneville aussi en soldes
