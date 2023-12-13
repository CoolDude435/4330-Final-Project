import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
public class ViewableExamples {
    private Examples examples = new Examples();

    //Everything here is not really a test, but just examples you can run and see

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printPalindromeCFG() {
        System.out.println(examples.palindromeCFG());
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printCNFPalindrome() {
        CFG palindrome = examples.palindromeCFG();
        palindrome.convertToCNF();
        System.out.println(palindrome);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printCFGtoPDA_Palindrome() {
        CFG palindrome = examples.palindromeCFG();
        PDA pda = palindrome.convertToPDA();
        System.out.println(pda);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEPlusECFG() {
        System.out.println(examples.EPlusE());
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEPlusEPDA() {
        System.out.println(examples.EPlusE().convertToPDA());
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGraphVizEPlusE() {
        PDA EPlusE_PDA = examples.EPlusE().convertToPDA();
        PDA_Visulization EPlusEVis = new PDA_Visulization(EPlusE_PDA, "EPlusE");
        EPlusEVis.createGraphFile();
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEPlusECFG_CNF() {
        CFG EPlusE = examples.EPlusE();
        EPlusE.convertToCNF();
        System.out.println(EPlusE);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGraphVizEPlusECNFtoPDA() {
        CFG EPlusE = examples.EPlusE();
        EPlusE.convertToCNF();
        PDA EPlusE_CNF_PDA = EPlusE.convertToPDA();
        PDA_Visulization EPlusE_CNF_PDAVis = new PDA_Visulization(EPlusE_CNF_PDA, "EPlusE_CNFtoPDA");
        EPlusE_CNF_PDAVis.createGraphFile();
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEZMultiEmptyProdCFG() {
        CFG EZMultiEmptyProd = examples.EZMultiEmptyProds();
        System.out.println(EZMultiEmptyProd);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEZMultiEmptyProdCNF_CFG() {
        CFG EZMultiEmptyProd = examples.EZMultiEmptyProds();
        EZMultiEmptyProd.convertToCNF();
        System.out.println(EZMultiEmptyProd);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printEZMultiEmptyProdPDA() {
        System.out.println(examples.EZMultiEmptyProds().convertToPDA());
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGraphVizFileEZMultiEmptyProd() {
        PDA_Visulization EZMultiEmptyProd = new PDA_Visulization(examples.EZMultiEmptyProds().convertToPDA(), "EZMultiEmptyProd");
        EZMultiEmptyProd.createGraphFile();
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printMultiEmptyProdCFG() {
        CFG MultiEmptyProd = examples.MultiEmptyProds();
        System.out.println(MultiEmptyProd);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printMultiEmptyProdCNF_CFG() {
        CFG MultiEmptyProd = examples.MultiEmptyProds();
        MultiEmptyProd.convertToCNF();
        System.out.println(MultiEmptyProd);
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void printMultiEmptyProdPDA() {
        System.out.println(examples.MultiEmptyProds().convertToPDA());
    }

     @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void createGraphVizFileMultiEmptyProds() {
        PDA_Visulization MultiEmptyProds = new PDA_Visulization(examples.MultiEmptyProds().convertToPDA(), "MultiEmptyProds");
        MultiEmptyProds.createGraphFile();
    }
}
