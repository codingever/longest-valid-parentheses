import java.util.ArrayList;
import java.util.List;

/** Find the size of longest valid parentheses in a string, which contains only the parentheses.
 * Example:
 * () should return 2;
 * )( should return 0, because it is not valid;
 * ()(()))() should return 6.
 *
 * @author codingever
 */
public class LongestValidParentheses {

    public int findLVPSize(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int index = -1;
        List<Integer> parenthesesSizes = new ArrayList<Integer>();
        int size = 0;

        for (char symbol : str.toCharArray()) {
            if (symbol == '(') {
                parenthesesSizes.add(1);
                index = parenthesesSizes.size() - 1;
            } else {
                if (index != -1 && !parenthesesSizes.isEmpty() && parenthesesSizes.get(index) == 1) {
                    parenthesesSizes.set(index, 2);
                    index = parenthesesSizes.lastIndexOf(1);
                } else {
                    size = calculateSize(parenthesesSizes, size);
                    parenthesesSizes.clear();
                }
            }
        }
        size = calculateSize(parenthesesSizes, size);
        return size;
    }

    private int calculateSize(List<Integer> parentheseSizes, int size) {
        int tempSize = 0;
        for (int i = parentheseSizes.size() - 1; i >= 0; i--) {
            int currentSize = parentheseSizes.get(i);
            if (currentSize != 1) {
                tempSize += currentSize;
            } else {
                if (size < tempSize) {
                    size = tempSize;
                }
                tempSize = 0;
            }
        }
        if (size < tempSize) {
            size = tempSize;
        }
        return size;
    }
}
