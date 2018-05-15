package twitterproject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class APISearch extends SearchType {

    private ConfigurationBuilder obj;
    private Query query;
    private QueryResult result;
    private List<Status> tweets;
    private String url;
    private String filter = "";
    private String checker;
    private final String[] modifier = new String[]{" +exclude:retweets", " +exclude:replies", " +exclude:mentions", " +exclude:hashtags"}; //option for simple search
    private int type;
    private String user;
    private int pageno;

    public APISearch() throws TwitterException, IOException {
        tweetparameter = 4;
        setAPIKey();
        //This part for Text-based user interface
        /*
        System.out.println("-------------------------------------");
        System.out.println("API search");
        System.out.println("-------------------------------------");
        checkConnection();
        setSearch();
         */
    }

    public void setAPIKey() {
        this.obj = new ConfigurationBuilder();
        this.obj.setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        //.setTweetModeExtended(true);
    }

    public void setSearch() throws TwitterException, IOException {
        System.out.println("-------------------------------------");
        System.out.println("API Search type");
        System.out.println("-------------------------------------");
        System.out.println("Choose 1 for Normal API search with simple filter");
        System.out.println("Choose 2 for Latest 100 tweets search");
        System.out.println("Choose 3 for User search");
        System.out.println("-------------------------------------");
        System.out.print("Choose one : ");
        type = SC.nextInt();
        switch (type) {
            case 1:
                System.out.println("-------------------------------------");
                System.out.println("Normal API search with simple filter");
                System.out.println("-------------------------------------");
                System.out.println("Filter Search to seperate your results");
                System.out.println("Choose number to exclude it");
                System.out.println("1 for Retweet");
                System.out.println("2 for Replies");
                System.out.println("3 for Mentions");
                System.out.println("4 for Hashtags");
                System.out.println("Other numbers for include all of this filter");
                System.out.println("-------------------------------------");
                System.out.print("Input your option : ");
                optionSearch(SC.nextInt());
                this.printResult();
                super.continuesearch();
                break;
            case 2:
                System.out.println(SC.nextLine()); // clear input
                System.out.println("-------------------------------------");
                System.out.println("Latest 100 tweets search");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                getLatestTweet(SC.nextLine());
                this.printResult();
                super.continuesearch();
                break;
            case 3:
                System.out.println(SC.nextLine());
                System.out.println("-------------------------------------");
                System.out.println("User search");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                userSearch(SC.nextLine());
                this.printResult();
                super.continuesearch();
                break;
            default:
                System.out.println("-------------------------------------");
                System.out.println("Mismatch Input , Try again.");
                super.startsearch();
                break;
        }
    }

    public void search(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        query = new Query(keyword);
        query.setCount(100);
        word = keyword;
        do {
            result = twitter.search(query);
            tweets = result.getTweets();

            for (Status tweet : tweets) {
                url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
                data.add(new Tweet(tweet.getUser().getScreenName(), String.valueOf(tweet.getCreatedAt()), tweet.getText(), url));
            }

        } while ((query = result.nextQuery()) != null);
    }

    public void optionSearch(int option) throws TwitterException, IOException {
        System.out.print(SC.nextLine()); //clear input

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
            case 4:
                if (filter.contains(modifier[3])) {
                    System.out.println("-------------------------------------");
                    System.out.println("Already Exclude Hashtags");
                    System.out.println("-------------------------------------");
                } else {
                    System.out.println("-------------------------------------");
                    System.out.println("Exclude Hashtags");
                    filter = filter.concat(modifier[3]);
                    break;
                }

            default:
                System.out.println("-------------------------------------");
                System.out.println("Non Exclude");
                System.out.println("-------------------------------------");
                System.out.print("Input your keyword : ");
                search(SC.nextLine());
                this.printResult();
                super.continuesearch();
                break;
        }

        System.out.println("-------------------------------------");
        System.out.println("Continue Filtering? Y/N");
        System.out.println("-------------------------------------");
        System.out.print("Input : ");
        checker = SC.next();
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
            optionSearch(SC.nextInt());
        } else {
            System.out.println(SC.nextLine()); // clear input
            System.out.print("Input your keyword : ");
            search(SC.nextLine().concat(filter));
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
    public void advancedSearch() throws TwitterException, IOException{
        System.out.println("-------------------------------------");
        System.out.println("Advanced Search");
        System.out.println("All of these words : ");
        String w1 ="alice snowwhite sinoalice";
        System.out.println("Any of these words : ");
        String w3 =null;
        String w4 =null;
        System.out.println("None of these words : ");
        String w5 =null;
        System.out.println("From these accounts : ");
        String u1 =null;
        //String u2 ="";
        System.out.println("Mentioning these accounts: : ");
        String u3 =null;
        System.out.println("-------------------------------------");
        String all = w1;
        search(all);
        this.printResult();
        super.continuesearch();
    }

    public void getLatestTweet(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        query = new Query(keyword);
        query.setCount(100);
        word = keyword;
        result = twitter.search(query);
        tweets = result.getTweets();

        for (Status tweet : tweets) {
            url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
            data.add(new Tweet(tweet.getUser().getScreenName(), String.valueOf(tweet.getCreatedAt()), tweet.getText(), url));
        }
    }

    public void userSearch(String keyword) {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        pageno = 1;
        user = keyword;
        word = keyword;
        List statuses = new ArrayList();
        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                statuses.addAll(twitter.getUserTimeline(user, page));
                for (Status tweet : twitter.getUserTimeline(user, page)) {
                    url = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
                    data.add(new Tweet(tweet.getUser().getScreenName(), String.valueOf(tweet.getCreatedAt()), tweet.getText(), url));
                }
                if (statuses.size() == size) {
                    break;
                }
            } catch (TwitterException e) {
                e.printStackTrace();
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
