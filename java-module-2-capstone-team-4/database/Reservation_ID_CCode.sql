select *
from site
where site_id in (select site.site_id from site where site.site_id not in
                 (select reservation.site_id
                         from reservation
                         where '2020-02-18' < to_date and '2020-02-22' > from_date))
and campground_id in (select site.campground_id from site where site.campground_id in
                     (select campground_id
                      from campground
                      where 2 > cast(open_from_mm as Integer) and 2 < cast(open_to_mm as Integer)))
and campground_id = 1 
limit 5                     
                      ;                         
            
select campground_id
from campground
where name = 'Blackwoods';             
                         
                         
select *
from reservation
where '2020-02-16' < to_date and '2020-02-18' > from_date;


select campground_id
from campground
where '02'::Integer > cast(open_from_mm as Integer) and '02'::Integer < cast(open_to_mm as Integer);

