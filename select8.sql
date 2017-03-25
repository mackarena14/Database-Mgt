select * from Person
where PID in (select PID
              from Director
              where DID in (select DID
                            from Directs
                            where MPAANumber in (select MPAANumber
                                                 from ActsIn
                                                 where AID in (select AID
                                                               from Actor
                                                               where PID in (select PID 
                                                                             from Person 
                                                                             where FirstName='Sean' and LastName='Connery'
                                                                             )
                                                               )
                                                 )
                            )
              );
              
                                                                            
                                                                   
                                 
                    