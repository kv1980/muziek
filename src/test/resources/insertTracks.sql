insert into tracks(albumid,naam,tijd) 
values ((select id from albums where naam = 'Vlaamse slagers'),'Eviva Espana',5),
	   ((select id from albums where naam = 'Vlaamse slagers'),'J aime la vie',6),
	   ((select id from albums where naam = 'Vlaamse slagers'),'Anne',4);