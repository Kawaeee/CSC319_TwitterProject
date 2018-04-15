public class Searcher{
    public static int count;
    private File file;
    private String word;
    
    public Searcher(String keyword){
      this.word = keyword;
      this.file = new File();
      this.count = 0;
    }

    public void search(String word){
       //add code below here.
    }
    public void printResult(){
       //add code below here.
       System.out.println(count);
    }
}
