SELECT *

FROM (

    SELECT 
  
        `wp_posts`.`post_title` AS `Title`,
        
        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "file_name"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `File Name`,
        
        `wp_posts`.`ID` , 
        
        `wp_posts`.`post_date` AS `Date`,
        

        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "download_url"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `720p`,

        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "directory"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `Directory`,

        `wt`.`name` AS Category,
        
        MAX( CASE WHEN `wp_postmeta`.`meta_key` = "guid"
        THEN `wp_postmeta`.`meta_value`
        END ) AS `GUID`


    FROM `wp_posts`

        INNER JOIN `wp_term_relationships` wtr ON (wp_posts.`ID` = wtr.`object_id`)
        INNER JOIN `wp_term_taxonomy` wtt ON (wtr.`term_taxonomy_id` = wtt.`term_taxonomy_id`)
        INNER JOIN `wp_terms` wt ON (wt.`term_id` = wtt.`term_id`)

    LEFT JOIN `wp_postmeta` ON ( `wp_posts`.`ID` = `wp_postmeta`.`post_id` )

    WHERE 
    #`wp_posts`.`post_status` = "publish"
	 `wp_posts`.`post_type` = "post"
    #AND `wt`.`slug` = "released" 
    #AND `wtt`.`taxonomy` = "post_tag"
    AND `wtt`.`taxonomy` = "category" 
    AND `wt`.`slug`IN ("2014-mormon-messages"
                                                        ,"2013-mormon-messages"
                                                        ,"2012-mormon-messages"
                                                        ,"2011-mormon-messages"
                                                       ,"2010-mormon-messages"
                                                       ,"2009-mormon-messages")

    GROUP BY `wp_posts`.`ID`

    ORDER BY `wt`.`name` DESC

) AS `t` WHERE 1 =1