SELECT
    *
FROM PLACES places
WHERE
        (
            SELECT COUNT(*)
            FROM PLACES sub_places
            WHERE places.HOST_ID = sub_places.HOST_ID
            GROUP BY HOST_ID
        ) > 1;
