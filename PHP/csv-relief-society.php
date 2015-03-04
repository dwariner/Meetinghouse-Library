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
        $filepath = "" . __DIR__ . "/wp-content/uploads/download-manager-files/Relief Society.csv";
        //echo $filepath;

        // create a file pointer connected to the output stream
        $output = fopen("$filepath", "w");


        // output the column headings
        fputcsv($output, array('Title','Directory','1080p','720p','360p','File Name','Category','GUID'));

        // fetch the data
        mysql_connect($hostname, $username, $password);
        mysql_select_db($dbname);
        $rows = mysql_query('SELECT *
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

) AS `t` WHERE 1 =1');

        // loop over the rows, outputting them
        while ($row = mysql_fetch_assoc($rows)) fputcsv($output, $row);

        //exit;    

?>