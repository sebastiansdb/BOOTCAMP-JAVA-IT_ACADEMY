	-- 																Queries UNIVERSIDAD

		USE universidad;
	-- 1. Retorna un llistat amb el primer cognom, segon cognom i el nom de tots els/les alumnes. El llistat haurà d'estar ordenat alfabèticament de 
	-- 	  menor a major pel primer cognom, segon cognom i nom.
		SELECT nombre, apellido1, apellido2 
		FROM persona 
		WHERE tipo = 'alumno'
		ORDER BY apellido1, apellido2, nombre;
    
	-- 2. Esbrina el nom i els dos cognoms dels alumnes que no han donat d'alta el seu número de telèfon en la base de dades.
		SELECT nombre, apellido1, apellido2 
		FROM persona
		WHERE tipo = 'alumno' AND telefono IS NULL ;
    
	-- 3. Retorna el llistat dels alumnes que van néixer en 1999.
		SELECT nombre, apellido1, apellido2 
		FROM persona
		WHERE tipo = 'alumno' AND YEAR(fecha_nacimiento) = '1999';
    
	-- 4. Retorna el llistat de professors/es que no han donat d'alta el seu número de telèfon en la base de dades i a més el seu NIF acaba en K.
		SELECT *
		FROM persona 
		WHERE tipo = 'profesor' AND telefono IS NULL AND NIF LIKE '%k';
    
	-- 5.   Retorna el llistat de les assignatures que s'imparteixen en el primer quadrimestre, en el tercer curs del grau que té l'identificador 7.
		SELECT * 
		FROM asignatura 
		WHERE cuatrimestre = 1 AND id_grado = 7;
    
	-- 6. Retorna un llistat dels professors/es juntament amb el nom del departament al qual estan vinculats. El llistat ha de retornar quatre columnes,
	-- 	  primer cognom, segon cognom, nom i nom del departament. El resultat estarà ordenat alfabèticament de menor a major pels cognoms i el nom.
		SELECT p.apellido1, p.apellido2, p.nombre, d.nombre AS 'Departamento' 
		FROM departamento d
		JOIN profesor prof 
		ON prof.id_departamento = d.id 
		JOIN persona p ON p.id = prof.id_profesor AND p.tipo = 'profesor';
    
	-- 7. Retorna un llistat amb el nom de les assignatures, any d'inici i any de fi del curs escolar de l'alumne/a amb NIF 26902806M.
		SELECT a.nombre,ce.anyo_inicio, ce.anyo_fin 
		FROM alumno_se_matricula_asignatura alMat
		JOIN persona p 
			ON p.id = alMat.id_alumno
		JOIN asignatura a
			ON alMat.id_asignatura = a.id
		JOIN curso_escolar ce
			ON alMat.id_curso_escolar = ce.id
		WHERE p.NIF = '26902806M';
    
	-- 8. Retorna un llistat amb el nom de tots els departaments que tenen professors/es que imparteixen alguna assignatura en el Grau en Enginyeria
	-- Informàtica (Pla 2015).
		SELECT DISTINCT d.nombre AS 'Departamento'
		FROM departamento d 
		JOIN profesor p 
			ON d.id = p.id_departamento 
		JOIN asignatura a 
			ON p.id_profesor = a.id_profesor 
		JOIN grado 
			ON a.id_grado = grado.id 
		WHERE grado.nombre = 'Grado en Ingeniería Informática (Plan 2015)';
    
	-- 9. Retorna un llistat amb tots els alumnes que s'han matriculat en alguna assignatura durant el curs escolar 2018/2019.
		SELECT DISTINCT p.nombre, p.apellido1, p.apellido2 FROM alumno_se_matricula_asignatura am 
		JOIN  persona p
			ON p.id = am.id_alumno
		JOIN  curso_escolar ce ON am.id_curso_escolar =  ce.id 
		WHERE am.id_curso_escolar = 5;	-- curso escolar 2018/2019 tiene id = 5
    
    -- 								-----------------------------------------------------------------------------
    
-- Resol les 6 següents consultes utilitzant les clàusules LEFT JOIN i RIGHT JOIN.

	-- 1. Retorna un llistat amb els noms de tots els professors/es i els departaments que tenen vinculats. El llistat també ha de mostrar aquells 
	-- professors/es que no tenen cap departament associat. El llistat ha de retornar quatre columnes, nom del departament, primer cognom, segon 
   -- cognom i nom del professor/a. El resultat estarà ordenat alfabèticament de menor a major pel nom del departament, cognoms i el nom.
		SELECT d.nombre AS 'Departamento', p.apellido1, p.apellido2, p.nombre  
		FROM persona p 
		LEFT JOIN profesor prof ON p.id = prof.id_profesor 
		LEFT JOIN departamento d ON prof.id_departamento = d.id 
		ORDER BY d.nombre, p.apellido1, p.apellido2, p.nombre;
    
   -- 2. Retorna un llistat amb els professors/es que no estan associats a un departament.
		SELECT d.nombre AS 'Departamento', p.apellido1, p.apellido2, p.nombre  
		FROM persona p 
		LEFT JOIN profesor prof ON p.id = prof.id_profesor 
		LEFT JOIN departamento d ON prof.id_departamento = d.id 
		WHERE  d.id IS NULL
		ORDER BY d.nombre, p.apellido1, p.apellido2, p.nombre;
    
   -- 3. Retorna un llistat amb els departaments que no tenen professors/es associats.
		SELECT d.nombre AS 'Departamento'
		FROM departamento d
		LEFT JOIN profesor prof ON prof.id_departamento = d.id 
		WHERE prof.id_departamento IS NULL;

   -- 4. Retorna un llistat amb els professors/es que no imparteixen cap assignatura.
		SELECT *
		FROM persona p
		LEFT JOIN profesor pr ON p.id = pr.id_profesor
		LEFT JOIN asignatura a ON pr.id_profesor = a.id_profesor;

   -- 5. Retorna un llistat amb les assignatures que no tenen un professor/a assignat.
		SELECT a.id, a.nombre AS 'Asignatura'  
		FROM  asignatura a
		LEFT JOIN profesor prof 
			ON a.id_profesor = prof.id_profesor 
		WHERE a.id_profesor IS NULL;
    
   -- 6. Retorna un llistat amb tots els departaments que no han impartit assignatures en cap curs escolar.
		SELECT DISTINCT d.nombre AS 'Departamento'  
		FROM departamento d 
		LEFT JOIN profesor p 
		ON d.id = p.id_departamento
		LEFT JOIN asignatura a
			ON a.id_profesor = p.id_profesor
		WHERE a.id IS NULL;

				-- 								-----------------------------------------------------------------------------								--
    
    -- 1.  Retorna el nombre total d'alumnes que hi ha.
		SELECT COUNT(*) AS 'Cantidad Alumnos ' 
        FROM persona p
		WHERE p.tipo = 'alumno'; 

    -- 2.  Calcula quants alumnes van néixer en 1999.
		SELECT COUNT(*) AS 'Cantidad Alumnos 1990' 
        FROM persona p
		WHERE YEAR(p.fecha_nacimiento) = '1990';
    
    -- 3.  Calcula quants professors/es hi ha en cada departament. El resultat només ha de mostrar dues columnes, una amb el nom del departament i una
    --     altra amb el nombre de professors/es que hi ha en aquest departament. El resultat només ha d'incloure els departaments que tenen professors/es 
    --     associats i haurà d'estar ordenat de major a menor pel nombre de professors/es.
		SELECT COUNT(*) AS 'Cantidad Profesores', d.nombre AS 'Departamento' 
        FROM profesor p 
        JOIN departamento d
			ON p.id_departamento = d.id
		GROUP BY d.nombre
        ORDER BY COUNT(*), d.nombre;
        
    -- 4.  Retorna un llistat amb tots els departaments i el nombre de professors/es que hi ha en cadascun d'ells. Tingui en compte que poden existir 
    --     departaments que no tenen professors/es associats. Aquests departaments també han d'aparèixer en el llistat.
		SELECT d.nombre AS 'Departamento', COUNT(prof.id_profesor) AS 'Cantidad Profesores'  
        FROM departamento d 
        LEFT JOIN profesor prof 
	 		ON d.id = prof.id_departamento
		LEFT JOIN persona p
			 ON prof.id_profesor = p.id
		GROUP BY d.nombre;

    -- 5.  Retorna un llistat amb el nom de tots els graus existents en la base de dades i el nombre d'assignatures que té cadascun. Tingues en compte que 
    --     poden existir graus que no tenen assignatures associades. Aquests graus també han d'aparèixer en el llistat. El resultat haurà d'estar ordenat de major 
    --     a menor pel nombre d'assignatures.
		SELECT g.nombre ,COUNT(a.id_grado) AS Cantidad_Asignaturtas 
        FROM grado g
        LEFT JOIN asignatura a
			ON g.id = a.id_grado
		GROUP BY g.id
        ORDER BY Cantidad_Asignaturtas DESC;
            
    -- 6.  Retorna un llistat amb el nom de tots els graus existents en la base de dades i el nombre d'assignatures que té cadascun, dels graus que tinguin 
    --     més de 40 assignatures associades.
		SELECT DISTINCT g.nombre, COUNT(a.id) AS Cantidad_Asignaturas 
        FROM grado g
		LEFT JOIN asignatura a
			ON g.id = a.id_grado
		GROUP BY g.nombre
		HAVING Cantidad_Asignaturas > 40;
    
    -- 7.  Retorna un llistat que mostri el nom dels graus i la suma del nombre total de crèdits que hi ha per a cada tipus d'assignatura. El resultat ha 
    --     de tenir tres columnes: nom del grau, tipus d'assignatura i la suma dels crèdits de totes les assignatures que hi ha d'aquest tipus.
        SELECT g.nombre, a.tipo , SUM(a.creditos) AS Cantidad_creditos
        FROM grado g
		JOIN asignatura a
			ON g.id = a.id_grado
		GROUP BY g.id , a.tipo;

    -- 8.  Retorna un llistat que mostri quants alumnes s'han matriculat d'alguna assignatura en cadascun dels cursos escolars. El resultat haurà de 
    --     mostrar dues columnes, una columna amb l'any d'inici del curs escolar i una altra amb el nombre d'alumnes matriculats.
        SELECT ce.anyo_inicio AS Ano_Inicio, COUNT(am.id_alumno) 
        FROM alumno_se_matricula_asignatura am 
		JOIN curso_escolar ce
			ON am.id_curso_escolar = ce.id
		GROUP BY ce.anyo_inicio;
            
    -- 9.  Retorna un llistat amb el nombre d'assignatures que imparteix cada professor/a. El llistat ha de tenir en compte aquells professors/es que no
    --     imparteixen cap assignatura. El resultat mostrarà cinc columnes: id, nom, primer cognom, segon cognom i nombre d'assignatures. El resultat estarà 
    --     ordenat de major a menor pel nombre d'assignatures.
		SELECT * 
        FROM persona p
        LEFT JOIN profesor prof
			ON prof.id_profesor = p.id
        LEFT JOIN  asignatura a
			ON a.id_profesor = prof.id_profesor
		WHERE p.tipo = 'profesor';
		
			
-- 								-----------------------------------------------------------------------------								--
-- Esto no va, es una explicacion de como lo estaba haciendo y no funcionaba.
-- 
		SELECT p.id, p.nombre, p.apellido1, p.apellido2, COUNT(a.id) AS Cantidad_Asignaturas 
        FROM persona p
        LEFT JOIN profesor prof
			ON prof.id_profesor = p.id
        LEFT JOIN  asignatura a
			ON a.id_profesor = prof.id_profesor
		WHERE p.tipo = 'profesor'
		GROUP BY p.id
        ORDER BY Cantidad_Asignaturas DESC;
        
        SELECT p.id, p.nombre, p.apellido1, p.apellido2, COUNT(a.id) AS 'Cantidad Asignaturas' 
         FROM persona p 
         -- LEFT--  JOIN profesor prof			 el 'LEFT' lo impedia 	--      		EXPLICAR!!
			ON prof.id_profesor = p.id
        LEFT JOIN  asignatura a
			ON a.id_profesor = prof.id_profesor
		WHERE p.tipo = 'profesor'
		GROUP BY p.id, prof.id_profesor; -- ¿ Por que con 'prof.id_profesor' NO FUNCIONA?
        
-- 								-----------------------------------------------------------------------------								--
-- CONTINUA
--

	-- 10. Retorna totes les dades de l'alumne/a més jove.
		SELECT * FROM persona 
        WHERE tipo = 'alumno'
        ORDER BY fecha_nacimiento DESC
        LIMIT 1;
        
    -- 11. Retorna un llistat amb els professors/es que tenen un departament associat i que no imparteixen cap assignatura.
		SELECT p.* FROM persona p
		JOIN profesor prof
			ON p.id = prof.id_profesor
		LEFT JOIN asignatura a
			ON prof.id_profesor = a.id_profesor
		WHERE prof.id_departamento IS NOT NULL AND a.id IS NULL;