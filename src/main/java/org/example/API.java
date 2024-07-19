package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class API {
    private CloseableHttpClient client;
    public static final String url = "https://app.seker.live/fm1/";
    public static final String SEND = "send-message";
    public static final String CLEAR = "clear-history";
    public static final String CHECK = "check-balance";
    //private String responseFromChat;

    public API(){
        this.client = HttpClients.createDefault();

    }


      public String sendMessege(String id,String text){
          String responseFromChat="";
            try {
                URI uri = new URIBuilder(url+SEND)
                        .addParameter("id",id)
                        .addParameter("text",text)
                        .build();
                HttpGet request = new HttpGet(uri);
                CloseableHttpResponse response = client.execute(request);
                String json = EntityUtils.toString (response.getEntity());
                JsonParser jsonParser = new ObjectMapper().readValue(json,JsonParser.class);
                responseFromChat=jsonParser.getExtra();
            } catch (URISyntaxException | IOException e  ) {
                e.printStackTrace();
            }
            return responseFromChat;
    }


          public String clearHistory(String id){
              String responseFromChat="";
              try {
                  URI uri = new URIBuilder(url+CLEAR)
                          .addParameter("id",id)
                          .build();
                  HttpGet request = new HttpGet(uri);
                  CloseableHttpResponse response = client.execute(request);
                  String json = EntityUtils.toString (response.getEntity());
                  JsonParser jsonParser = new ObjectMapper().readValue(json,JsonParser.class);
                  responseFromChat=jsonParser.getExtra();
              } catch (URISyntaxException | IOException e  ) {
                  e.printStackTrace();
             }
              return responseFromChat;
        }


    public String checkBalance(String id){
        String responseFromChat="";
        try {
            URI uri = new URIBuilder(url+CHECK)
                    .addParameter("id",id)
                    .build();
            HttpGet request = new HttpGet(uri);
            CloseableHttpResponse response = client.execute(request);
            String json = EntityUtils.toString (response.getEntity());
            JsonParser jsonParser = new ObjectMapper().readValue(json,JsonParser.class);
            responseFromChat =jsonParser.getExtra();
        } catch (URISyntaxException | IOException e  ) {
            e.printStackTrace();
        }
        return responseFromChat;
    }

}
