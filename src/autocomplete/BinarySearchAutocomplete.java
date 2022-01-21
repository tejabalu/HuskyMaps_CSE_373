package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
    }


    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
//        sort terms in alphabetical order
        terms.sort(null);
        System.out.print(terms);

        List<CharSequence> matches = new ArrayList<>();

        int low = 0;
        int high = terms.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println(terms.get(mid));

            while (prefix.length() > terms.get(mid).length()) {
                mid++;
            }
            while (prefix.length() > terms.get(mid).length()) {
                mid--;
            }
            int cmp = terms.get(mid).subSequence(0, prefix.length()).toString().compareTo(prefix.toString());


            System.out.println(terms.get(mid).subSequence(0, prefix.length()));

            if (cmp < 0) {
                low = mid + 1;
            }

            else if (cmp > 0) {
                high = mid - 1;
            }
            else {
                matches.add(terms.get(mid));
                int midLow = mid - 1;
                int midHigh = mid + 1;

                while (terms.get(midLow).length()>prefix.length() && terms.get(midLow).subSequence(0, prefix.length()).toString().equals(prefix.toString())) {
                    matches.add(terms.get(midLow));
                    midLow--;
                }
                while (terms.get(midHigh).length()>prefix.length() && terms.get(midHigh).subSequence(0, prefix.length()).toString().equals(prefix.toString())) {
                    matches.add(terms.get(midHigh));
                    midHigh++;
                }
                return matches;
            }
        }
        return matches;
    }
}
