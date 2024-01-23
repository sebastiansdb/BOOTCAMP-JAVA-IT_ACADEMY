USE Optica;

-- Llista el total de factures d'un client/a en un període determinat

SELECT 	v.MarcaAnteojo,
		v.Fecha,
        c.IdCliente,
        c.Nombre
FROM Ventas v
JOIN Clientes c
	ON v.Clientes_Cliente_Id = C.IdCliente
WHERE c.IdCliente = 1 AND YEAR(v.Fecha)=2022;

-- Llista els diferents models d'ulleres que ha venut un empleat/da durant un any.

SELECT  v.MarcaAnteojo,
		V.Fecha,
        vend.NombreVendedor
FROM Ventas v 
JOIN Vendedor vend
	ON v.Vendedor_Id = vend.idVendedor
WHERE Vendedor_Id = 1 AND YEAR(v.Fecha)=2022;

-- Llista els diferents proveïdors que han subministrat ulleres venudes amb èxit per l'òptica.

SELECT 	v.MarcaAnteojo,
		v.Fecha,
        p.Nombre AS 'Proveedor'
FROM Anteojos a
JOIN Ventas v
	ON a.Anteojo_Id = v.Anteojos_Anteojo_Id
JOIN Proveedores p
	ON a.Proveedores_Proveedor_Id = p.Proveedor_Id
ORDER BY Proveedor;
	






