/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author AlexanderNielsen
 */
public class CallableFacade implements Callable<String>
{

    private String url;
    private String from;
    private String to;
    private String date;
    private String persons;

    public CallableFacade( String url, String from, String to, String date, String persons )
    {
        this.url = url;
        this.from = from;
        this.to = to;
        this.date = date;
        this.persons = persons;
    }

    @Override
    public String call() throws Exception
    {
        String status = getFromUrl();
        return url + "\t:\t " + status + "\t:\t" + Thread.currentThread().getName();
    }

    static String[] hostList =
    {
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/",
        "http://angularairline-plaul.rhcloud.com/api/flightinfo/"
    };

    public void runThreads()
    {
        ExecutorService executor = Executors.newFixedThreadPool( 4 );
        List<Future<String>> list = new ArrayList<>();

        for ( int i = 0; i < hostList.length; i++ )
        {
            String url = hostList[i];
            Callable<String> callable = new CallableFacade( url, from, to, date, persons );
            Future<String> future = executor.submit( callable );
            list.add( future );
        }
        for ( Future<String> fut : list )
        {
            try
            {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println( fut.get() );
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

    }

    public String getFromUrl() throws IOException
    {
        URL url1 = new URL( url + from + "/" + to + "/" + date + "/" + persons );

        if ( to == null || to == "" )
        {
            url1 = new URL( url + from + "/" + date + "/" + persons );
        }

        String output = "";
        String output2 = "";
        try
        {

            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod( "GET" );
            conn.setRequestProperty( "Accept", "application/json" );

            if ( conn.getResponseCode() != 200 )
            {
                throw new RuntimeException( "Failed : HTTP error code : "
                        + conn.getResponseCode() );
            }

            BufferedReader br = new BufferedReader( new InputStreamReader(
                    (conn.getInputStream()) ) );

            // System.out.println("Output from Server .... \n");
            while ( (output = br.readLine()) != null )
            {
                //System.out.println(output);
                output2 += output;
            }

            conn.disconnect();

        }
        catch (MalformedURLException e)
        {
            //System.err.println(e.getMessage());
            e.printStackTrace();

        }
        catch (IOException e)
        {

            e.printStackTrace();

        }
        return output2;
    }

}
