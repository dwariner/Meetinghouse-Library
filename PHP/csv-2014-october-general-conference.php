 <?php       

        include "db.php";
        // current directory
        //echo getcwd() . "\n";

        //Connecting to my database
        mysql_connect($hostname, $username, $password) OR DIE ("Unable to connect to database! Please try again later.");
        mysql_select_db($dbname);               


        // output headers so that the file is downloaded rather than displayed
        //header('Content-Type: text/csv; charset=utf-8');
        //header('Content-Disposition: attachment; filename=pikaObs_'.date("Ymd").'.csv');            
        $filepath = "" . __DIR__ . "/wp-content/uploads/download-manager-files/2014 October General Conference.csv";
        //echo $filepath;

        // create a file pointer connected to the output stream
        $output = fopen("$filepath", "w");


        // output the column headings
        fputcsv($output, array('Title','Video Directory','1080p URL','720p URL','360p URL','File Name 720p','Audio Directory','Audio URL','Document Directory','Document URL','Category','Release Date','YouTube URL','GUID'));

        // fetch the data
        mysql_connect($hostname, $username, $password);
        mysql_select_db($dbname);
        $rows = mysql_query('SELECT *
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
    AND `wt`.`slug`IN ("general-womens-session-september-2014"
                      ,"october-2014-general-conference-highlights"
                      ,"priesthood-session-october-2014"
                      ,"saturday-afternoon-session-october-2014"
                      ,"saturday-morning-session-october-2014"
                      ,"sunday-afternoon-session-october-2014"
                      ,"sunday-morning-session-october-2014")
    AND     (   SELECT COUNT(*) FROM wp_postmeta
                WHERE wp_postmeta.post_id = wp_posts.ID 
                AND wp_postmeta.meta_key = "release_date"
                AND wp_postmeta.meta_value != ""
                ) >= 1

    GROUP BY `wp_posts`.`ID`

    ORDER BY `wt`.`name`

) AS `t` WHERE 1 =1');

        // loop over the rows, outputting them
        while ($row = mysql_fetch_assoc($rows)) fputcsv($output, $row);

        //exit;

?>