import java.util.ArrayList;
import java.util.List;

public class Itemlists<L> {
 private List<L> allitems = new ArrayList<>();


public void addAllitems(L item) {
    allitems.add(item);
}

public L getitem(int index) {
    return allitems.get(index);
}

public int getSize(){ 
    return allitems.size();
}
}
