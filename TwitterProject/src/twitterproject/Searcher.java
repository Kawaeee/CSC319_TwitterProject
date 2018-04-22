package twitterproject;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Searcher {

    public static int count;
    private File file;
    ConfigurationBuilder obj;
    ArrayList<String> data = new ArrayList<>();

    public Searcher() {
        this.obj = new ConfigurationBuilder();
          this.obj.setDebugEnabled(true)
                //key;
    }

    public void search(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        Query query = new Query(keyword);
        QueryResult result;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        for (Status tweet : tweets) {
            String info = "@" + tweet.getUser().getScreenName() + " -- " + tweet.getText();
            data.add(info);
        }
    }

    public void printResult() {
        for(int i =0; i<data.size(); i++){
            System.out.println(data.get(i));
            System.out.println("");
        }
    }
}
