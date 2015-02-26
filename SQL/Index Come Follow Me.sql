SELECT *
FROM (
    SELECT 
        concat('<a href="','http://meetinghouselibrary.com/?p=',`wp_posts`.`ID`,'" target="_blank">',`wp_posts`.`post_title`,'</>') AS `Title`,

        MAX( CASE WHEN `wp_postmeta`.`meta_key` = 'directory'
        THEN `wp_postmeta`.`meta_value`
        END ) AS `Directory`,
        
        wt.name AS Category,
		
        MAX( CASE WHEN `wp_postmeta`.`meta_key` = 'download_url'
        THEN concat('<a href="',`wp_postmeta`.`meta_value`,'">Download</>')
        END ) AS `720p`

    FROM `wp_posts`

	INNER JOIN `wp_term_relationships` wtr ON (wp_posts.`ID` = wtr.`object_id`)
	INNER JOIN `wp_term_taxonomy` wtt ON (wtr.`term_taxonomy_id` = wtt.`term_taxonomy_id`)
	INNER JOIN `wp_terms` wt ON (wt.`term_id` = wtt.`term_id`)
    LEFT JOIN `wp_postmeta` ON ( `wp_posts`.`ID` = `wp_postmeta`.`post_id` )
    WHERE `wp_posts`.`post_status` = 'publish'
    AND `wp_posts`.`post_type` = 'post'
    AND wtt.taxonomy = 'category' AND wt.`slug`IN ('january-come-follow-me'
													,'february-come-follow-me'
                                                    ,'march-come-follow-me'
                                                    ,'april-come-follow-me'
                                                    ,'may-come-follow-me'
                                                    ,'june-come-follow-me'
                                                    ,'july-come-follow-me'
                                                    ,'august-come-follow-me'
                                                    ,'september-come-follow-me'
                                                    ,'october-come-follow-me'
                                                    ,'november-come-follow-me'
                                                    ,'december-come-follow-me')

    GROUP BY `wp_posts`.`ID`

    ORDER BY `wt`.`name`

) AS `t` WHERE 1 =1