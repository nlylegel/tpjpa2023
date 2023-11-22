# Template de projet pour le TP JPA 2021 UniR

Pour cette serie de TP j'ai choisi de garder le modele d'un Doctolib pour enseignant.

J'ai fusionné le TP1 et TP2 car je les ai jugé complementaire.

# J'ai suivi la structure de données suivante : 

Une classe Customer, correspondant à un élève par exemple

Une classe Teacher, representant le modele des enseignants.

Une classe Slot qui correspond à un creneau. 

Rapidement, l'idee est d'avoir un customer qui peut reserver un slot. Ce slot est préalablement créé par un teacher.

Les classes d'objet sont definies comme des entités grace aux annotations Jakarta.

J'ai intégré deux relations bi directionnelles (ManyToOne Et OneToMany)

# Pour lancer le projet, 

Il faut démarrer le RestServer, situé dans src/main/java

Le projet fonctionne avec mySQL (lancé avec docker).

L'application tourne sur le port 8080.

Les urls de requete sont visibles au niveau des controllers. Par exemple http://localhost:8080/api/customer/XXX retournera le customer possedant l'id XXX.

Le projet est structuré en 3 couches : DAO, Services, Controllers.
Cette structure sera gardée pour les prochains TPs.

Les controllers reçoivent la requête http, l'information circule jusqu'aux DAO en passant par les services (sans traitement majeur). 

Les DAO revoient l'information extraite de la BDD aux services. Les services effectuent des traitements sur l'information (changement de structure par exemple, avec les DTO), cette nouvelle information est alors envoyée aux controllers qui se charge de créer la reponse http.




