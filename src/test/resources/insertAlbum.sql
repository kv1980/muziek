insert into albums(artiestid,naam) 
values ((select id from artiesten where naam = 'hans'),'Vlaamse slagers'),
	   ((select id from artiesten where naam = 'hans'),'Olijke liedjes'),
	   ((select id from artiesten where naam = 'hans'),'Fantastische meezingers');