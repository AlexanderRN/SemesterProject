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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author AlexanderNielsen
 */
public class CallableFacade implements Callable<String> {

    private String url;
    private String from;
    private String to;
    private String date;
    private String persons;
    private List<String> urls;
    
    
    public CallableFacade(List urls, String url, String from, String to, String date, String persons) {
        this.urls = urls;
        this.url = url;
        this.from = from;
        this.to = to;
        this.date = date;
        this.persons = persons;
        
    }
    
    
    

    @Override
    public String call() throws Exception {
        String status = getFromUrl();
        return status;
    }


    public String runThreads() {
    String status = "";
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<String>> list = new ArrayList<>();

        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            Callable<String> callable = new CallableFacade(urls,url, from, to, date, persons);
            Future<String> future = executor.submit(callable);
            list.add(future);
        }
        int counter = 0;
        for (Future<String> fut : list) {
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(fut.get());
                
                if(counter > 0){
                    status += "," + fut.get();
                }
                else
                {
                    status += fut.get();
                }
                
                counter++;
                
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public String getFromUrl() throws IOException {
        URL url1 = new URL(url + from + "/" + to + "/" + date + "/" + persons);

        if (to == null || to == "") {
            url1 = new URL(url + from + "/" + date + "/" + persons);
        }

        String output = "";
        String output2 = "";
        try {

            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            // System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                output2 += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            //System.err.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return output2;
    }
    
   
}
