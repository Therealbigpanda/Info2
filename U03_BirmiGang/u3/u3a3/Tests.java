package u3a3;

import org.junit.Test;

/**
 *
 * @author AWilli
 */
public class Tests {
	void testCase(String lkd) throws ParseException {
		KD.parse(lkd);
	}
	
    /**
     *
     * @throws ParseException
     */
    @Test public void empyTree() throws ParseException { testCase("-"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void onlyRoot() throws ParseException { testCase("A"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void balancedTree() throws ParseException { testCase("A(B,C)"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void listLeft() throws ParseException { testCase("A(B(C,-),-)"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void listLeftImplicit() throws ParseException { testCase("A(B(C))"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void listRight() throws ParseException { testCase("A(-,B(-,C))"); }

    /**
     *
     * @throws ParseException
     */
    @Test public void missingSibling() throws ParseException { testCase("A(B,-,C)");}
	@Test public void noChildren() throws ParseException { testCase("A(-,-)");}

    /**
     *
     * @throws ParseException
     */
    @Test public void genericTree() throws ParseException { testCase("A(-,B(C),-,D(E(-,F(G,H)),I))"); }
	
    /**
     *
     * @throws ParseException
     */
    @Test(expected = ParseException.class)
	public void empyString() throws ParseException { testCase(""); }

	@Test(expected = ParseException.class)
	public void noChild()throws ParseException { testCase("A()"); }

    /**
     *
     * @throws ParseException
     */
    @Test(expected = ParseException.class)
	public void noSibling()throws ParseException { testCase("A(B,)"); }
	
    /**
     *
     * @throws ParseException
     */
    @Test(expected = ParseException.class)
	public void twoCommas()throws ParseException { testCase("A(B,,C)"); }

    /**
     *
     * @throws ParseException
     */
    @Test(expected = ParseException.class)
	public void missingOpenBrace()throws ParseException { testCase("AB)"); }

    @Test(expected = ParseException.class)
	public void missingCloseBrace()throws ParseException { testCase("A(B"); }

    @Test(expected = ParseException.class)
	public void missingComma()throws ParseException { testCase("A(BC)"); }
	
    @Test(expected = ParseException.class)
	public void garbage()throws ParseException { testCase("A(B)C"); }

    @Test(expected = ParseException.class)
	public void garbage2()throws ParseException { testCase("A(B)(C)"); }
	
    @Test(expected = ParseException.class)
	public void noRoot()throws ParseException { testCase("-(A)"); }

    @Test(expected = ParseException.class)
	public void invalidNode()throws ParseException { testCase("A(1)"); }

    @Test(expected = ParseException.class)
	public void listofTreesSimple() throws ParseException { testCase("A,B,C,D");}

    @Test(expected = ParseException.class)
	public void listofTrees() throws ParseException { testCase("A(C,D),B,C(E,F)");}
}
