package twitterproject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
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
    Query query;
    QueryResult result;
    List<Status> tweets;
    String url;
    String keyword;
    String filter = "";

    public APISearch() throws TwitterException, IOException {
        setAPIKey();
        System.out.println("-------------------------------------");
        System.out.println("API search");
        System.out.println("-------------------------------------");
        checkConnection();
        System.out.println("-------------------------------------");
        System.out.println("Filter Search to seperate your results");
        System.out.println("Choose number to exclude it");
        System.out.println("1 for Retweet");
        System.out.println("2 for Replies");
        System.out.println("3 for Mentions");
        System.out.println("Other numbers for include all of this filter");
        System.out.println("-------------------------------------");
        System.out.print("Input your option : ");
        optionSearch(sc.nextInt());
        this.printResult();
        super.continuesearch();
    }

    public void setAPIKey() {
        this.obj = new ConfigurationBuilder();
        this.obj.setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
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
        String[] modifier = new String[]{" +exclude:retweets", " +exclude:replies", " +exclude:mentions"}; //option for simple search
        switch (option) {
            case 1:
                if (filter.contains(modifier[0])) {
                    System.out.println("-------------------------------------");
                    System.out.println("Already Exclude Retweets");
                    System.out.println("-------------------------------------");
                    break;
                } else {
                    System.out.println("-------------------------------------");
                    System.out.println("Exclude Retweets");
                    filter = filter.concat(modifier[0]);
                    break;
                }
            case 2:
                if (filter.contains(modifier[1])) {
                    System.out.println("-------------------------------------");
                    System.out.println("Already Exclude Replies");
                    System.out.println("-------------------------------------");
                    break;
                } else {
                    System.out.println("-------------------------------------");
                    System.out.println("Exclude Replies");
                    filter = filter.concat(modifier[1]);
                    break;
                }
            case 3:
                if (filter.contains(modifier[2])) {
                    System.out.println("-------------------------------------");
                    System.out.println("Already Exclude Mentions");
                    System.out.println("-------------------------------------");
                } else {
                    System.out.println("-------------------------------------");
                    System.out.println("Exclude Mentions");
                    filter = filter.concat(modifier[2]);
                    break;
                }
            default:
                System.out.println("-------------------------------------");
                System.out.println("Non Exclude");
                System.out.print("Input your keyword : ");
                search(sc.nextLine());
                this.printResult();
                super.continuesearch();
                break;
        }

        System.out.println("-------------------------------------");
        System.out.println("Continue Filtering? Y/N");
        System.out.println("-------------------------------------");
        System.out.print("Input : ");
        checker = sc.next().toUpperCase();
        if (checker.equalsIgnoreCase("Y")) {
            System.out.println("-------------------------------------");
            System.out.println("Filter Search to seperate your results");
            System.out.println("Choose number to exclude it");
            System.out.println("1 for Retweet");
            System.out.println("2 for Replies");
            System.out.println("3 for Mentions");
            System.out.println("Other numbers for include all of this filter");
            System.out.println("-------------------------------------");
            System.out.print("Input your option : ");
            optionSearch(sc.nextInt());
        } else if (checker.equalsIgnoreCase("N")) {
            System.out.println(sc.nextLine()); //clear input
            System.out.print("Input your keyword : ");
            search(sc.nextLine().concat(filter));
            this.printResult();
            super.continuesearch();
        } else {
            System.out.println(sc.nextLine()); // clear input
            System.out.print("Input your keyword : ");
            search(sc.nextLine().concat(filter));
            this.printResult();
            super.continuesearch();
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
