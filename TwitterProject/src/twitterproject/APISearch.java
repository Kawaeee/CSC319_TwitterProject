package twitterproject;

//import java.util.ArrayList;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class APISearch extends SearchType {

    ConfigurationBuilder obj;
    //ArrayList<Tweet> data = new ArrayList<Tweet>();
    Query query;
    QueryResult result;
    List<Status> tweets;
    String url;

    /*
    public APISearch() {
        this.obj = new ConfigurationBuilder();
          this.obj.setDebugEnabled(true)
                //key;
    }
     */
    public APISearch() throws TwitterException, IOException {
        this.obj = new ConfigurationBuilder();
        this.obj.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
        sc = new Scanner(System.in);
        checkConnection();
        System.out.println("-------------------------------------");
        System.out.print("Input your keyword : ");
        search(sc.nextLine());
        printResult();

    }

    public void search(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        query = new Query(keyword);
        query.setCount(100);
        //query.setLocale("th");
        //query.setLang("en");
        word = keyword;
        result = twitter.search(query);
        tweets = result.getTweets();
        for (Status tweet : tweets) {
            url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
            data.add(new Tweet(tweet.getUser().getScreenName(), tweet.getCreatedAt(), tweet.getText(), url));
        }

        while (result.hasNext()) {
            if (result.hasNext() == false) {
                break;
            }
            query = result.nextQuery();
            result = twitter.search(query);
            tweets = result.getTweets();

            for (Status tweet : tweets) {
                url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
                data.add(new Tweet(tweet.getUser().getScreenName(), tweet.getCreatedAt(), tweet.getText(), url));
            }
        }
    }

    @Override
    public void printResult() {
        super.printResult();
    }

    public boolean checkConnection() throws IOException {
        try {
            final URL check = new URL("http://www.google.com");
            final URLConnection conn = check.openConnection();
            conn.connect();
            conn.getInputStream().close();
            System.out.println("Connection Connected");
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Connection Failed");
            System.exit(-1);
            return false;
        }
    }

    public void getRatelimit() {
        try {
            TwitterFactory tf = new TwitterFactory(obj.build());
            twitter4j.Twitter twitter = tf.getInstance();
            Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
            for (String endpoint : rateLimitStatus.keySet()) {
                RateLimitStatus status = rateLimitStatus.get(endpoint);
                System.out.println("Endpoint: " + endpoint);
                System.out.println(" Limit: " + status.getLimit());
                System.out.println(" Remaining: " + status.getRemaining());
                System.out.println(" ResetTimeInSeconds: " + status.getResetTimeInSeconds());
                System.out.println(" SecondsUntilReset: " + status.getSecondsUntilReset());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get rate limit status: " + te.getMessage());
        }
    }
}
