SELECT *
FROM (
    SELECT 
        wp_posts.post_title AS `Title`,

        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "directory"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `Directory`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_1080p"
        THEN wp_postmeta.meta_value
        END ) AS `1080p`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_720p"
        THEN wp_postmeta.meta_value
        END ) AS `720p`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_360p"
        THEN wp_postmeta.meta_value
        END ) AS `360p`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "file_name"
        THEN wp_postmeta.meta_value
        END ) AS `File Name`,
        
        wt.name AS Category,
        
        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "guid"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `GUID`

    FROM `wp_posts`

	INNER JOIN `wp_term_relationships` wtr ON (wp_posts.`ID` = wtr.`object_id`)
	INNER JOIN `wp_term_taxonomy` wtt ON (wtr.`term_taxonomy_id` = wtt.`term_taxonomy_id`)
	INNER JOIN `wp_terms` wt ON (wt.`term_id` = wtt.`term_id`)
    LEFT JOIN `wp_postmeta` ON ( `wp_posts`.`ID` = `wp_postmeta`.`post_id` )
    WHERE `wp_posts`.`post_status` = "publish"
    AND `wp_posts`.`post_type` = "post"
    AND wtt.taxonomy = "category" AND wt.`slug`IN ("relief-society")

    GROUP BY `wp_posts`.`ID`

    ORDER BY `wt`.`name`

) AS `t` WHERE 1 =1