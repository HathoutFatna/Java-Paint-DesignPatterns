import java.util.ArrayList;

public class GroupedShape implements GeneralShape{
    private ArrayList<GeneralShape> GroupedShape;

    @Override
    public void paste() {
        GroupedShape Shape= new GroupedShape();
        for(GeneralShape f: GroupedShape){
            f.paste();
    }
    }
}

