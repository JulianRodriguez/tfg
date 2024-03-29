-- PRIVILEGIOS
INSERT INTO PRIVILEGE VALUES
(8,'DELETE_PRIVILEGE'),
(23,'DELETE_RESTAURANT'),
(12,'DELETE_RESTAURANT_USER'),
(4,'DELETE_ROLE'),
(17,'DELETE_USER'),
(5,'GET_PRIVILEGE'),
(19,'GET_PRODUCT'),
(9,'GET_PRODUCT_RESTAURANT'),
(20,'GET_RESTAURANT'),
(11,'GET_RESTAURANT_USER'),
(1,'GET_ROLE'),
(14,'GET_USER'),
(6,'POST_PRIVILEGE'),
(10,'POST_PRODUCT_RESTAURANT'),
(21,'POST_RESTAURANT'),
(13,'POST_RESTAURANT_USER'),
(2,'POST_ROLE'),
(15,'POST_USER'),
(18,'PUT_EMAIL'),
(7,'PUT_PRIVILEGE'),
(22,'PUT_RESTAURANT'),
(3,'PUT_ROLE'),
(16,'PUT_USER');

-- ROLES
INSERT INTO ROLE VALUES (1,'ADMIN'),(2,'RESTAURANT'),(3,'USER');

-- ROLE_PRIVILEGE
INSERT INTO ROLE_PRIVILEGE VALUES (1,1),(1,2),(1,3),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(2,1),(2,2),(2,3),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(3,9),(3,11),(3,14),(3,19),(3,20);

-- USUARIOS
insert into USER VALUES
(1,'tfg.qr.julian@gmail.com',NULL,'Julian Rodriguez Carave','7c4a8d09ca3762af61e59520943dc26494f8941b','667884995',NULL,'admin',1),
(2,'sarazarzu@yopmail.com',NULL,'Sara Zaruela','7c4a8d09ca3762af61e59520943dc26494f8941b','435243124',NULL,'sarita04',2),
(3,'Paco@gmail.com',NULL,'Paco Perez','5b1d5b1569de26f631ba3edf0d89f65be5af4e3d','4352431240',NULL,'Pacome0',3),
(4,'ElianaAragonsValle@gmail.com',NULL,'Eliana Aragonés Valle','ede927f8e42318a8db02c0f74adc2d9e16770339','4352431241',NULL,'ElianaAragonésValle',3),
(5,'AscensinPeraltaBoix@gmail.com',NULL,'Ascensión Peralta Boix','72571665d1184a5889ec08a1ea4c6345b35634f0','4352431242',NULL,'AscensiónPeraltaBoix',3),
(6,'VitoLenRodriguez@gmail.com',NULL,'Vito León Rodriguez','3f4f87319b39809df40a147f7615328b1454317c','4352431243',NULL,'VitoLeónRodriguez',3),
(7,'LoretoPurificacio@gmail.com',NULL,'Loreto Purificación Mariño Escudero','01eb84f052ba857d610a26815628d5339386c8e9','4352431244',NULL,'LoretoPurificaciónMariño',3),
(8,'SeveroVerdejoTalavera@gmail.com',NULL,'Severo Verdejo Talavera','645b8fbaef16c732ce0d5d1b18e228ae871a51ce','4352431245',NULL,'SeveroVerdejoTalavera',3),
(9,'LeticiaBorrsValera@gmail.com',NULL,'Leticia Borrás-Valera','7c4a8d09ca3762af61e59520943dc26494f8941b','4352431246',NULL,'LeticiaBorrásValera',3),
(10,'RuyVillegasNoguera@gmail.com',NULL,'Ruy Villegas Noguera','908f704ccaadfd86a74407d234c7bde30f2744fe','4352431247',NULL,'RuyVillegasNoguera',3),
(11,'JuanEscribanoJoseCarlos@gmail.com',NULL,'Juan Escribano','118a43489e2f9ab66823eabdada672c906bb387f','4352431248',NULL,'JuanEscribanoJoseCarlos',3),
(12,'HeraclioBustamanteArce@gmail.com',NULL,'Jose Carlos Chamorro Oliveras','a47cd3afb2fe2091c917d6b2283d69137f8ab7ab','4352431249',NULL,'HeraclioBustamanteArce',3),
(13,'EmmaPereiraGibert@gmail.com',NULL,'Heraclio Bustamante Arce','2a54bcfaa18ed8055d844968b4cd810e2a677058','43524312410',NULL,'EmmaPereiraGibert',3),
(14,'IrisLeonVallejo@gmail.com',NULL,'Emma Pereira Gibert','84214c863a9fb68ff6973b2d1a3927ea003f4607','43524312411',NULL,'IrisLeonVallejo',3),
(15,'RodrigoBautistaJenaroRogelioAtienza@gmail.com',NULL,'Iris Leon Vallejo','014a95c071794d5bf2e474ea11cbe59a28ee504a','43524312412',NULL,'RodrigoBautistaJenaroRogelioAtienza',3),
(16,'AndrsFelipeBernad@gmail.com',NULL,'Rodrigo Bautista','46a8caa2d6da315a2cb7c4508bba0455438251a1','43524312413',NULL,'AndrésFelipeBernad',3),
(17,'AroaLenPerera@gmail.com',NULL,'Jenaro Rogelio Atienza Iglesias','fdaf8b24531324607c5065c7e10ad05a51fae900','43524312414',NULL,'AroaLeónPerera',3),
(18,'JulietaAndrsOda@gmail.com',NULL,'Andrés Felipe Bernad','039431ab6b55332ac59b8f73603b9a65c2e99363','43524312415',NULL,'JulietaAndrésOdalysLlanoAndrés',3),
(19,'NorbertoUgarteAngulo@gmail.com',NULL,'Aroa León Perera','ab35463e98d1d3d087c08d81c48d6068676bdc75','43524312416',NULL,'NorbertoUgarteAngulo',3),
(20,'GoyoMateoSureda@gmail.com',NULL,'Julieta Andrés','bc1735863f8c05b36fc38dadb7727e604ac13f0d','43524312417',NULL,'GoyoMateoSureda',3),
(21,'JacintaCastelol@gmail.com',NULL,'Odalys Llano Andrés','871639a4cb3cf4508eafb2b8d7ef9d48aad5e1bc','43524312418',NULL,'JacintaCastellanosEspañol',3),
(22,'FtimaBonillaPeñalver@gmail.com',NULL,'Norberto Ugarte Angulo','944ca07ac079ec47a77c8b5b28d97d44fb7359da','43524312419',NULL,'FátimaBonillaPeñalver',3),
(23,'XiomaradelToro@gmail.com',NULL,'Goyo Mateo Sureda','a141f1e13439115a3fd25eb0393e9894ec55f7bd','43524312420',NULL,'XiomaradelToro',3),
(24,'TadeoEscolanoCarpio@gmail.com',NULL,'Jacinta Castellanos Español','c084cc263f903cdc5481d2a438fa2bf300d1d932','43524312421',NULL,'TadeoEscolanoCarpio',3),
(25,'JavierMontalbnSaia@gmail.com',NULL,'Fátima Bonilla Peñalver','77fe548835b5fabab9c71589acd3e09183ed5133','43524312422',NULL,'JavierMontalbánSantamaria',3),
(26,'MireiaTomasLillo@gmail.com',NULL,'Xiomara del Toro','936830fe18a66f08a11d846dfa21a1f679ac626d','43524312423',NULL,'MireiaTomasLillo',3),
(27,'ChusTamaritManso@gmail.com',NULL,'Tadeo Escolano-Carpio','039e8fdcb3d3155ddfec3a1c0e9f52034a17711f','43524312424',NULL,'ChusTamaritManso',3),
(28,'MaiteManceboGel@gmail.com',NULL,'Javier Montalbán Santamaria','27308bd77fd29dcea72ce8faa8acce46ea45bf8b','43524312425',NULL,'MaiteManceboGelabert',3),
(29,'AurelianoVerdas@gmail.com',NULL,'Mireia Tomas Lillo','95ab63767029bd0c91d44a862412fb1984ceed9c','43524312426',NULL,'AurelianoVerdugoCasanovas',3),
(30,'CarmelaIsidoraRibas@gmail.com',NULL,'Chus Tamarit Manso','6e3cebef0b01fd781b708209335f3dbc74cadf07','43524312427',NULL,'CarmelaIsidoraRibas',3);



-- RESTAURANTES
INSERT INTO RESTAURANT VALUES
(1,'Restaurante romántico con cocina andaluza creativa y repostería casera','Restaurante El Faro',2),
(2,'Tradicional bodega local con vino jerez, que ofrece visitas guiadas y catas.','Bodega Tío Pepe',2),
(3,'Restaurante en el corazón de las Bodegas Tío Pepe','Restaurante Pedro Nolasco',3);

-- PRODUCTOS
INSERT INTO PRODUCT VALUES
(1,'"Septiembre refrescante": Whisky, arándanos y limón.','El cóctel de El Faro',NULL,1,1),
(2,'Procedente de la destilación de vinos y con un proceso de crianza único, estamos ante un espirituoso de una nobleza y carácter difícilmente comparables.','Brandy de Jerez',NULL,2,2),
(3,'Infinitos en edad pero finitos en vida, estos vinos fueron deliberadamente “olvidados” en nuestras bodegas para, también deliberadamente, redescubrirlos hoy. ','Vinos Finitos',NULL,2,2),
(4,' realizados por el horno La Cremita de Chiclana, rellenos de pringá que se acompañan con unos pequeños vasos de caldo del puchero aromatizados con el famoso amontillado Viña AB de González Byass. La pringá no se presenta de la forma habitual, sino con la carne de cerdo deshilachada al estilo del pulled pork americano y aromatizada con especias.','Brioche de berza con vistas a la Catedral',NULL,3,3);