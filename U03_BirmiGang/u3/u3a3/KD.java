package u3a3;

/**
 * @since a long time
 * @author Andrea-Pascal Willi
 */
public class KD {

    /**
     * global string ref to access param of parse(String kd)
     */
    private static String tree;

    /**
     * Parse a "Klammerdarstellung" (KD) of a tree.
     * 
     * <ul>
     * <li>An empty tree is encoded as '-'.</li>
     * <li>A node is encoded as an uppercase letter, i.e. everything accepted by {@link Character#isUpperCase(char)}.</li>
     * <li>Children are appended to the father as a ','-separated list of nodes enclosed in round brackets.</li>
     * </ul> 
     * 
     * @param kd: tree encoded in KD
     * @throws ParseException if the given String is not a valid KD of a tree.
     */
    public static void parse(String kd) throws ParseException{
        tree = kd; // initialize global string-ref to kd
        if(tree.length() == 0) throw new ParseException("empty string", 0);
        if(tree.length() > 1 && tree.charAt(1) != '(') throw new ParseException("noRoot", 0);
        tree(0); // check if kd is a valid tree
    }

    /**
     *
     * @param index: where the tree is located in the string
     * @return number of chars in tree (node + '(' + number of chars in subtree + ')')
     * @throws ParseException if there is an error in the tree format
     */
    private static int tree(int index) throws ParseException
    {
        if(tree.charAt(index) == '-') // if tree is empty return 1
        {	
            if(tree.length() > index+1 && tree.charAt(index+1) == '(')
                throw new ParseException("no root but children", 0);		
            return 1;
        }
        
        if(!Character.isUpperCase(tree.charAt(index))) throw new ParseException("illegal node", index);
        
        if((tree.length() > index+1) && (tree.charAt(index+1) == '('))
        {
            if(tree.length() < index+4) throw new ParseException("treeing ends abruptly", index);
            int subelements = next(index+2);
            if(tree.charAt(subelements+index+2) == ')')
            {
                if(index == 0 && tree.length() > subelements+3) throw new ParseException("garbage", index);
                return 3 + subelements;
            }
            else throw new ParseException("no closing braces", index);
        }
        return 1;
    }
    
    /**
     *
     * @param index: where the subtree is located in the string
     * @return
     * @throws ParseException
     */
    private static int next(int index) throws ParseException
    {
        int subelements = tree(index);
        while(tree.charAt(index+subelements) == ',')
        {
            subelements = (subelements + tree(index+subelements+1) + 1);            
        }
        return subelements;
    }
}