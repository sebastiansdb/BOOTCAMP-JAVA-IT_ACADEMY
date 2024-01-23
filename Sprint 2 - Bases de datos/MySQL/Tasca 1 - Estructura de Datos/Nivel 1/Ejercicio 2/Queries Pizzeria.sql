USE Pizzeria;
    -- Llista quants productes de categoria 'Begudes' s'han venut en una determinada localitat.
    SELECT  l.nombre AS 'Localidad', SUM(cp.cantidad) AS 'Cantidad Bebidas Vendidas' 
    FROM categoria c
    JOIN productos p 
		ON c.idcategoria = p.id_categoria
    JOIN comanda_productos cp 
		ON p.id_categoria = cp.idProducto
    JOIN comanda com
		ON cp.id_comanda = com.idComanda
	JOIN tienda t
         ON com.id_tienda = t.id
	JOIN localidad l
		ON 	t.idLocalidad = l.idLocalidad
	WHERE l.nombre = 'barcelona'
    GROUP BY l.idLocalidad;
    
    -- Llista quantes comandes ha efectuat un determinat empleat/da.
    
    SELECT e.idEmpleado, e.nombre AS 'Empleado', COUNT(c.idComanda) AS 'Cantidad de comandas'
    FROM comanda c
    JOIN empleado e
		ON c.idEmpleado = e.idEmpleado
	WHERE e.idEmpleado = 1;
    
