public class FuzzySearch{
    
    private static FuzzySearch theInstance = new FuzzySearch();

    private FuzzySearch(){};

    public static FuzzySearch getInstance()
    {
        return theInstance;
    }

    void Search(String searchterm)
    {
        System.out.printf("Searching with term ->%s<-", searchterm);
    }



}
