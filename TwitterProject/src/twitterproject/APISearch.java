package twitterproject;

//import java.util.ArrayList;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
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

    public APISearch() throws TwitterException, IOException {

        this.obj = new ConfigurationBuilder();
        this.obj.setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        System.out.println("-------------------------------------");
        System.out.println("API search");
        System.out.println("-------------------------------------");
        checkConnection();
        System.out.println("-------------------------------------");
        System.out.println("Option Search");
        System.out.println("1 for Only Tweets");
        System.out.println("2 for Tweets and Replies");
        System.out.println("3 for Tweets and Retweets");
        System.out.println("Other numbers for All Tweets,Retweets,Replies");
        System.out.println("-------------------------------------");
        System.out.print("Input your option : ");
        optionSearch(sc.nextInt());
        this.printResult();
        super.continuesearch();
    }

    public void search(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        query = new Query(keyword);
        query.setCount(100);
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

    public void optionSearch(int option) throws TwitterException, IOException {
        System.out.print(sc.nextLine()); //clear input
        String[] modifier = new String[]{" +exclude:retweets +exclude:replies", " +exclude:retweets", " +exclude:replies"};
        switch (option) {
            case 1:
                System.out.println("-------------------------------------");
                System.out.println("Only Tweets");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                search(sc.nextLine() + modifier[0]);
                this.printResult();
                super.continuesearch();
                break;
            case 2:
                System.out.println("-------------------------------------");
                System.out.println("Tweets and Replies");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                search(sc.nextLine() + modifier[1]);
                this.printResult();
                super.continuesearch();
                break;
            case 3:
                System.out.println("-------------------------------------");
                System.out.println("Tweets and Retweets");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                search(sc.nextLine() + modifier[2]);
                this.printResult();
                super.continuesearch();
                break;
            default:
                System.out.println("-------------------------------------");
                System.out.println("All Tweets,Retweets,Replies");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                search(sc.nextLine());
                this.printResult();
                super.continuesearch();
                break;
        }
    }

    /*
     All of these words: word1 word2
     Any of these words: word3 word4
     None of these words: word5
     From these accounts: user1 user2
     Mentioning these accounts: user3
     Query query = new Query("word1 word2 word3 OR word4 -word5 from:user1 OR from:user2 @user3");
     */
    public void advancedSearch() {
        System.out.println("-------------------------------------");
        System.out.println("Advanced Search");
        System.out.println("All of these words : ");
        System.out.println("Any of these words : ");
        System.out.println("None of these words : ");
        System.out.println("From these accounts : ");
        System.out.println("Mentioning these accounts: : ");
        System.out.println("-------------------------------------");
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
            System.out.println("Connection : Success");
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Connection : Failed");
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
            System.out.println("Failed to get rate limit status: " + te.getMessage());
        }
    }
}
