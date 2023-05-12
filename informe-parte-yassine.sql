--Solicitará al usuario un equipo y una temporada y mostrará el número total de partidos ganados,
--perdidos y empatados de ese equipo.

select codigo, equipo1, sum(ganados)as ganados
from (


select e.codigo, e.nombre as equipo1, count(puntuacion_visitante) as ganados
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_visitante
where e.codigo = 2
and puntuacion_visitante > puntuacion_local
UNION all

select e.codigo as codigo, e.nombre as equipo1, count(puntuacion_local) as ganados
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_local
where e.codigo = 2
and puntuacion_local > puntuacion_visitante


);

select codigo, equipo1, sum(perdidos)as perdidos
from (



select e.codigo as codigo, e.nombre as equipo1, count(puntuacion_local) as perdidos
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_visitante
where e.codigo = 2
and puntuacion_local > puntuacion_visitante
UNION all

select e.codigo as codigo, e.nombre as equipo1, count(puntuacion_visitante) as perdidos
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_local
where e.codigo = 2
and puntuacion_local < puntuacion_visitante
);

select codigo, equipo1, sum(empate)as empate
from (
select e.codigo as codigo, e.nombre as equipo1, count(puntuacion_visitante) as empate
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_local
where e.codigo = 2
and puntuacion_local = puntuacion_visitante

UNION all

select e.codigo as codigo, e.nombre as equipo1, count(puntuacion_local) as empate
FROM equipo e join partido p
on e.codigo = p.codigo_equipo_visitante
where e.codigo = 2
and puntuacion_local = puntuacion_visitante

);

--------------------
--Solicitará al usuario un equipo y sacará el número de jugadores que ha tenido en cada temporada,
--ordenados por temporada.ugadores que ha tenido en cada temporada,
--ordenados por temporada.

