package oonine;

import com.oocourse.specs1.models.Path;

import java.util.ArrayList;
import java.util.Iterator;

public class MyPath implements Path {
    private ArrayList<Integer> nodes = new ArrayList<Integer>();

    public MyPath(int... nodeList){
        int i;
        for(i=0;i<nodeList.length;i++){
            nodes.add(new Integer(nodeList[i]));
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public int getNode(int index) {
        return ;
    }

    @Override
    public boolean containsNode(int node) {
        return false;
    }

    @Override
    public int getDistinctNodeCount() {
        return 0;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public int compareTo(Path o) {
        return 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ArrayList<Integer>().iterator();
    }
}
