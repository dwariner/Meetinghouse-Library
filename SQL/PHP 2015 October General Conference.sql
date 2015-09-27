SELECT *
FROM (
    SELECT 
        wp_posts.post_title AS `Title`,

        MAX( CASE WHEN wp_postmeta.meta_key = "directory_video"
        THEN wp_postmeta.meta_value
        END ) AS `Video Directory`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_1080p"
        THEN wp_postmeta.meta_value
        END ) AS `1080p URL`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_720p"
        THEN wp_postmeta.meta_value
        END ) AS `720p URL`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "download_url_360p"
        THEN wp_postmeta.meta_value
        END ) AS `360p URL`,
         
        MAX( CASE WHEN wp_postmeta.meta_key = "file_name_720p"
        THEN wp_postmeta.meta_value
        END ) AS `File Name 720p`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "directory_audio"
        THEN wp_postmeta.meta_value
        END ) AS `Audio Directory`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "audio_url"
        THEN wp_postmeta.meta_value
        END ) AS `Audio URL`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "directory_doc"
        THEN wp_postmeta.meta_value
        END ) AS `Document Directory`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "document_url"
        THEN wp_postmeta.meta_value
        END ) AS `Document URL`,
        
        wt.name AS Category,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "release_date"
        THEN wp_postmeta.meta_value
        END ) AS `Release Date`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "video_url"
        THEN wp_postmeta.meta_value
        END ) AS `YouTube URL`,
        
        MAX( CASE WHEN wp_postmeta.meta_key = "guid"
        THEN wp_postmeta.meta_value
        END ) AS `GUID`


    FROM `wp_posts`

    INNER JOIN `wp_term_relationships` wtr ON (wp_posts.`ID` = wtr.`object_id`)
    INNER JOIN `wp_term_taxonomy` wtt ON (wtr.`term_taxonomy_id` = wtt.`term_taxonomy_id`)
    INNER JOIN `wp_terms` wt ON (wt.`term_id` = wtt.`term_id`)
    LEFT JOIN `wp_postmeta` ON ( `wp_posts`.`ID` = `wp_postmeta`.`post_id` )

    WHERE `wp_posts`.`post_status` = "publish"
	AND `wp_posts`.`post_type` = "post"
    AND `wtt`.`taxonomy` = "category" 
    AND `wt`.`slug`IN ("general-womens-session-october-2015"
					  ,"saturday-morning-session-october-2015"
                      ,"saturday-afternoon-session-october-2015"
                      ,"priesthood-session-october-2015"
                      ,"sunday-morning-session-october-2015"
                      ,"sunday-afternoon-session-october-2015"
                      ,"october-2015-general-conference-highlights"
                      ,"october-2015-general-conference-video-quotes")
	AND     (   SELECT COUNT(*) FROM wp_postmeta
                WHERE wp_postmeta.post_id = wp_posts.ID 
                AND wp_postmeta.meta_key = "release_date"
                AND wp_postmeta.meta_value != ""
                ) >= 1

    GROUP BY `wp_posts`.`ID`

    ORDER BY `wt`.`name`

) AS `t` WHERE 1 =1