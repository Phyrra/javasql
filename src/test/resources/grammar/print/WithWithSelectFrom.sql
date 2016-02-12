with cte1 as (
    select * from t1
), cte2 as (
    select * from t2
)
select *
from cte1
join cte2 on cte2.key = cte1.key