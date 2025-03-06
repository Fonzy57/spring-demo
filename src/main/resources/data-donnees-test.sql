INSERT INTO etat (nom)
VALUES ('neuf'),
       ('occasion'),
       ('bon');

INSERT INTO produit (nom, code, description, prix, etat_id)
VALUES ('Pepsi', 'pep', 'Bouteille de 1.5 L', 1.89, 1),
       ('Coca', 'coc', 'Bouteille de 2.0 L', 2.50, 2),
       ('Sprite', 'spr', 'Cannette de 33 cL', 1.25, 3),
       ('7up', 'zup', 'Bouteille 1.5 L', 1.75, 1);