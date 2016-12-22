select ProfessorName, AnzahlStudenten, SummeSWS 
from ( 
select p.Name as ProfessorName, count(s.MatrNr) 
as AnzahlStudenten 
from Professoren p 
join Vorlesungen v on v.gelesenVon = p.PersNr
join hoeren h on h.VorlNr = v.VorlNr 
join Studenten s on s.MatrNr = h.MatrNr 
group by p.Name 
) A 
join 
( 
select p.Name as ProfessorName,  sum(SWS) 
as summeSWS 
from Professoren p 
join Vorlesungen v on v.gelesenVon = p.PersNr 
group by p.Name 
) B using(ProfessorName)